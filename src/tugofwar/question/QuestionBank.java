package tugofwar.question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import tugofwar.ui.CLIView;
import tugofwar.ui.InputHandler;

public class QuestionBank {

    private final String questionFilePath;
    private final List<Question> allQuestions = new ArrayList<>();

    public QuestionBank(String questionFilePath) {
        this.questionFilePath = questionFilePath;
    }

    public void loadQuestionsFromFile() {
        allQuestions.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(questionFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length < 7) continue;

                String type = parts[0].trim();
                String questionText = parts[1].trim();
                String optA = parts[2].trim();
                String optB = parts[3].trim();
                String optC = parts[4].trim();
                String optD = parts[5].trim();
                char correct = parts[6].trim().toUpperCase().charAt(0);

                if ("GRAMMAR".equalsIgnoreCase(type)) {
                    allQuestions.add(new GrammarQuestion(questionText, optA, optB, optC, optD, correct));
                } else if ("VOCAB".equalsIgnoreCase(type)) {
                    allQuestions.add(new VocabularyQuestion(questionText, optA, optB, optC, optD, correct));
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file soal: " + e.getMessage());
        }
    }

    public List<Question> getRandomQuestionsByMode(QuizMode mode, int limit) {
        List<Question> filtered = allQuestions.stream()
                .filter(q -> q.getMode() == mode)
                .collect(Collectors.toList());
        Collections.shuffle(filtered);
        if (filtered.size() > limit) {
            return filtered.subList(0, limit);
        }
        return filtered;
    }

    public void addQuestionFromInput(InputHandler input, CLIView view) {
        view.showMessage("Tambah soal baru");
        int modeChoice = input.readInt("Tipe soal: 1) Grammar  2) Vocabulary : ");
        QuizMode mode = modeChoice == 1 ? QuizMode.GRAMMAR : QuizMode.VOCAB;

        String questionText = input.readLine("Teks soal: ");
        String optA = input.readLine("Opsi A: ");
        String optB = input.readLine("Opsi B: ");
        String optC = input.readLine("Opsi C: ");
        String optD = input.readLine("Opsi D: ");
        char correct = input.readAnswerOption("Jawaban benar (A/B/C/D): ");

        String type = (mode == QuizMode.GRAMMAR) ? "GRAMMAR" : "VOCAB";

        try (PrintWriter pw = new PrintWriter(new FileWriter(questionFilePath, true))) {
            pw.println(type + ";" + questionText + ";" + optA + ";" + optB + ";" + optC + ";" + optD + ";" + correct);
            view.showMessage("Soal berhasil disimpan.");
        } catch (IOException e) {
            view.showMessage("Gagal menyimpan soal: " + e.getMessage());
        }

        loadQuestionsFromFile();
    }
}
