package tugofwar.question;

public abstract class Question {

    protected String questionText;
    protected String optionA;
    protected String optionB;
    protected String optionC;
    protected String optionD;
    protected char correctOption; // a, b ,c ,d

    protected QuizMode mode; // GRAMMAR atau VOCAB

    public Question(String questionText,
                    String optionA,
                    String optionB,
                    String optionC,
                    String optionD,
                    char correctOption,
                    QuizMode mode) {
        this.questionText = questionText;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctOption = Character.toUpperCase(correctOption);
        this.mode = mode;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public QuizMode getMode() {
        return mode;
    }

    public boolean checkAnswer(char userAnswer) {
        return Character.toUpperCase(userAnswer) == correctOption;
    }
}
