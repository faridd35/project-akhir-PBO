package tugofwar.core;

import tugofwar.user.Admin;
import tugofwar.user.Student;

import java.io.File;

import tugofwar.question.QuestionBank;
import tugofwar.result.LeaderboardService;
import tugofwar.result.ResultLogger;
import tugofwar.ui.CLIView;
import tugofwar.ui.InputHandler;

public class GameEngine {

    private final QuestionBank questionBank;
    private final ResultLogger resultLogger;
    private final LeaderboardService leaderboardService;
    private final CLIView view;
    private final InputHandler input;

    public GameEngine() {
        this.view = new CLIView();
        this.input = new InputHandler();
        this.questionBank = new QuestionBank(new File("questions.csv").getAbsolutePath());
        this.resultLogger = new ResultLogger("results.txt");
        this.leaderboardService = new LeaderboardService(resultLogger);
    }

    public void start() {
        //mengambil pertanyaan dari file
        questionBank.loadQuestionsFromFile();

        boolean running = true;
        while (running) {
            view.showWelcome();
            int choice = input.readInt("Pilih peran: 1) Admin  2) Siswa  3) Keluar : ");
            switch (choice) {
                case 1:
                    handleAdminLogin();
                    break;
                case 2:
                    handleStudentLogin();
                    break;
                case 3:
                    running = false;
                    view.showGoodbye();
                    break;
                default:
                    view.showMessage("Pilihan tidak valid.");
            }
        }
    }

    private void handleAdminLogin() {
        String username = input.readLine("Masukkan username admin: ");
        String password = input.readLine("Masukkan password admin: ");

        if ("admin".equals(username) && "admin123".equals(password)) {
            Admin admin = new Admin(username);
            runAdminMenu(admin);
        } else {
            view.showMessage("Login admin gagal.");
        }
    }

    private void handleStudentLogin() {
        String username = input.readLine("Masukkan username siswa: ");
        Student student = new Student(username);
        runStudentMenu(student);
    }

    private void runAdminMenu(Admin admin) {
        boolean back = false;
        while (!back) {
            view.showAdminMenu(admin.getUsername());
            int choice = input.readInt("Pilih menu: ");
            switch (choice) {
                case 1:
                    // Tambah soal
                    questionBank.addQuestionFromInput(input, view);
                    break;
                case 2:
                    // Lihat leaderboard
                    leaderboardService.showLeaderboard(view, input);
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    view.showMessage("Pilihan tidak valid.");
            }
        }
    }

    private void runStudentMenu(Student student) {
        boolean back = false;
        while (!back) {
            view.showStudentMenu(student.getUsername());
            int choice = input.readInt("Pilih menu: ");
            switch (choice) {
                case 1:
                    // Mulai game
                    GameSession session = new GameSession(student, questionBank, resultLogger, view, input);
                    session.play();
                    break;
                case 2:
                    // Lihat riwayat
                    leaderboardService.showHistoryForUser(student.getUsername(), view);
                    break;
                case 3:
                    // Lihat leaderboard
                    leaderboardService.showLeaderboard(view, input);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    view.showMessage("Pilihan tidak valid.");
            }
        }
    }
}
