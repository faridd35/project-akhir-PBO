package tugofwar.ui;

import java.util.List;
import java.time.format.DateTimeFormatter;

import tugofwar.question.Question;
import tugofwar.question.QuizMode;
import tugofwar.result.GameResult;

public class CLIView {

    // Tambahkan formatter global
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void showWelcome() {
        System.out.println("====================================");
        System.out.println("   GAME KUIS TARIK TAMBANG  ");
        System.out.println("   MATERI ADVANCED ENGLISH        ");
        System.out.println("====================================");
    }

    public void showGoodbye() {
        System.out.println("Terima kasih, sampai jumpa lagi!");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showAdminMenu(String username) {
        System.out.println("\n==== MENU ADMIN (" + username + ") ====");
        System.out.println("1. Tambah soal");
        System.out.println("2. Lihat leaderboard");
        System.out.println("3. Kembali");
    }

    public void showStudentMenu(String username) {
        System.out.println("\n==== MENU SISWA (" + username + ") ====");
        System.out.println("1. Mulai permainan");
        System.out.println("2. Lihat riwayat pengerjaan");
        System.out.println("3. Lihat leaderboard");
        System.out.println("4. Kembali");
    }

    public void showQuestion(int number, Question q) {
        System.out.println("\nSoal " + number + ":");
        System.out.println(q.getQuestionText());
        System.out.println("A. " + q.getOptionA());
        System.out.println("B. " + q.getOptionB());
        System.out.println("C. " + q.getOptionC());
        System.out.println("D. " + q.getOptionD());
    }

    public void showTugOfWar(String field) {
        System.out.println(field);
    }

    public void showGameResult(GameResult result) {
        System.out.println("\n===== HASIL PERMAINAN =====");
        System.out.println("Nama: " + result.getUsername());
        System.out.println("Mode: " + result.getMode());
        System.out.println("Benar: " + result.getCorrectCount());
        System.out.println("Salah: " + result.getWrongCount());
        System.out.println("Skor: " + result.getScore());
        System.out.println("Hasil: " + result.getStatus());
        System.out.println("Waktu: " + result.getPlayedAt().format(formatter));
    }


    public void showLeaderboard(QuizMode mode, List<GameResult> results) {
        System.out.println("\n===== LEADERBOARD - " + mode + " =====");
        if (results.isEmpty()) {
            System.out.println("Belum ada data.");
            return;
        }
        System.out.println("Rank | Nama       | Benar | Salah | Skor | Hasil | Waktu");
        int rank = 1;
        for (GameResult r : results) {
            System.out.printf("%4d | %-10s | %5d | %5d | %4d | %-5s | %s%n",
                    rank++, r.getUsername(), r.getCorrectCount(), r.getWrongCount(), r.getScore(), r.getStatus(),
                    r.getPlayedAt().format(formatter));
        }
    }

    public void showHistory(String username, List<GameResult> results) {
        System.out.println("\n===== RIWAYAT - " + username + " =====");
        if (results.isEmpty()) {
            System.out.println("Belum ada riwayat untuk pengguna ini.");
            return;
        }
        System.out.println("Mode    | Benar | Salah | Skor | Hasil | Waktu");
        for (GameResult r : results) {
            System.out.printf("%-6s | %5d | %5d | %4d | %-5s | %s%n",
                    r.getMode(), r.getCorrectCount(), r.getWrongCount(), r.getScore(), r.getStatus(),
                    r.getPlayedAt().format(formatter));
        }
    }
}
