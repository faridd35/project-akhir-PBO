package tugofwar.result;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

public class ResultLogger {

    private final String resultFilePath;

    public ResultLogger(String resultFilePath) {
        this.resultFilePath = resultFilePath;
    }

    public void saveResult(GameResult result) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(resultFilePath, true))) {
            pw.println(result.toCsvLine());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan hasil: " + e.getMessage());
        }
    }

    public List<GameResult> loadAllResults() {
        List<GameResult> results = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(resultFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                GameResult result = GameResult.fromCsvLine(line);
                if (result != null) {
                    results.add(result);
                }
            }
        } catch (IOException e) {
            // kalau file belum ada gapapa
        }
        return results;
    }
}
