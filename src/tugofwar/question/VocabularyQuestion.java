package tugofwar.question;

public class VocabularyQuestion extends Question {

    public VocabularyQuestion(String questionText,
                              String optionA,
                              String optionB,
                              String optionC,
                              String optionD,
                              char correctOption) {
        super(questionText, optionA, optionB, optionC, optionD, correctOption, QuizMode.VOCAB);
    }
}
