package tugofwar.core;

import java.time.LocalDateTime;
import java.util.List;

import tugofwar.mechanic.ITugOfWarMechanic;
import tugofwar.mechanic.TugOfWarField;
import tugofwar.question.Question;
import tugofwar.question.QuestionBank;
import tugofwar.question.QuizMode;
import tugofwar.result.GameResult;
import tugofwar.result.ResultLogger;
import tugofwar.ui.CLIView;
import tugofwar.ui.InputHandler;
import tugofwar.user.Student;

public class GameSession {

    private final Student student;
    private final QuestionBank questionBank;
    private final ResultLogger resultLogger;
    private final CLIView view;
    private final InputHandler input;

    private final ITugOfWarMechanic tugOfWar;
    private int correctCount;
    private int wrongCount;
    private QuizMode mode;

    public GameSession(Student student,
                       QuestionBank questionBank,
                       ResultLogger resultLogger,
                       CLIView view,
                       InputHandler input) {
        this.student = student;
        this.questionBank = questionBank;
        this.resultLogger = resultLogger;
        this.view = view;
        this.input = input;
        this.tugOfWar = new TugOfWarField();
    }

    public void play() {
        // Pilih mode Grammar/Vocab
        int choice = input.readInt("Pilih mode: 1) Grammar  2) Vocabulary : ");
        if (choice == 1) {
            mode = QuizMode.GRAMMAR;
        } else {
            mode = QuizMode.VOCAB;
        }

        List<Question> questions = questionBank.getRandomQuestionsByMode(mode, 10);

        correctCount = 0;
        wrongCount = 0;

        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            view.showQuestion(i + 1, q);

            char answer = input.readAnswerOption("Jawaban (A/B/C/D): ");
            boolean correct = q.checkAnswer(answer);

            if (correct) {
                correctCount++;
                tugOfWar.onCorrectAnswer();
                view.showMessage("Jawaban benar.");
            } else {
                wrongCount++;
                tugOfWar.onWrongAnswer();
                view.showMessage("Jawaban salah.");
            }

            view.showTugOfWar(tugOfWar.renderField());
        }

        int score = correctCount * 10;
        String status;
        int pos = tugOfWar.getPosition();

        if (pos < 0) {
            status = "WIN";   
        } else if (pos > 0) {
            status = "LOSE";  
        } else {
            status = "DRAW";  //Pos=0
        }

        GameResult result = new GameResult(
                student.getUsername(),
                mode,
                correctCount, 
                wrongCount,
                score,
                status,
                LocalDateTime.now()
        );

        view.showGameResult(result);
        resultLogger.saveResult(result);
    }
}
