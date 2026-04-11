package tugofwar.result;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import tugofwar.question.QuizMode;

public class GameResult {

    private final String username;
    private final QuizMode mode;
    private final int correctCount;
    private final int wrongCount;
    private final int score;
    private final String status; // WIN / DRAW / LOSE
    private final LocalDateTime playedAt;

    public GameResult(String username,
                      QuizMode mode,
                      int correctCount,
                      int wrongCount,
                      int score,
                      String status,
                      LocalDateTime playedAt) {
        this.username = username;
        this.mode = mode;
        this.correctCount = correctCount;
        this.wrongCount = wrongCount;
        this.score = score;
        this.status = status;
        this.playedAt = playedAt;
    }

    public String getUsername() { return username; }
    public QuizMode getMode() { return mode; }
    public int getCorrectCount() { return correctCount; }
    public int getWrongCount() { return wrongCount; }
    public int getScore() { return score; }
    public String getStatus() { return status; }
    public LocalDateTime getPlayedAt() { return playedAt; }

    public String toCsvLine() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return username + ";" + mode + ";" + correctCount + ";" + wrongCount + ";" + score + ";" + status + ";" + playedAt.format(formatter);
    }

    public static GameResult fromCsvLine(String line) {
        String[] parts = line.split(";");
        if (parts.length < 7) return null;

        String username = parts[0].trim();
        QuizMode mode = QuizMode.valueOf(parts[1].trim());
        int correct = Integer.parseInt(parts[3]);
        int wrong = Integer.parseInt(parts[4]);
        int score = Integer.parseInt(parts[2].trim());
        String status = parts[5].trim();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(parts[6].trim(), formatter);

        return new GameResult(username, mode, correct, wrong, score, status, time);
    }
}
