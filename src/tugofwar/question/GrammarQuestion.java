package tugofwar.question;

public class GrammarQuestion extends Question {

    public GrammarQuestion(String questionText,
                           String optionA,
                           String optionB,
                           String optionC,
                           String optionD,
                           char correctOption) {
        super(questionText, optionA, optionB, optionC, optionD, correctOption, QuizMode.GRAMMAR);
    }
}
