import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grammar {
    private static final String EPSILON = "epsilon";

    private List<String> N;
    private List<String> E;
    private String S;
    private Map<String, List<List<String>>> P;

    public Grammar() {
        N = new ArrayList<>();
        E = new ArrayList<>();
        S = "";
        P = new HashMap<>();
    }

    public List<String> getN() {
        return N;
    }

    public List<String> getE() {
        return E;
    }

    public Map<String, List<List<String>>> getP() {
        return P;
    }

    private List<String> processLine(String line) {
        // Get what comes after the first '='
        String[] parts = line.trim().split("=", 2);
            return List.of(parts[1].trim().split("\\s+"));
    }

    public void readFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            N = processLine(br.readLine());
            E = processLine(br.readLine());
            S = processLine(br.readLine()).get(0);

            br.readLine(); // P =

            P = new HashMap<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.trim().split("->");
                String source = split[0].trim();
                String sequence = split[1].trim();
                List<String> sequenceList = List.of(sequence.split("\\s+"));

                P.computeIfAbsent(source, k -> new ArrayList<>()).add(sequenceList);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCFG() {
        boolean hasStartingSymbol = false;
        for (String key : P.keySet()) {
            if (key.equals(S)) {
                hasStartingSymbol = true;
            }
            if (!N.contains(key)) {
                return false;
            }
        }
        if (!hasStartingSymbol) {
            return false;
        }
        for (List<List<String>> production : P.values()) {
            for (List<String> rhs : production) {
                for (String value : rhs) {
                    if (!N.contains(value) && !E.contains(value) && !value.equals(EPSILON)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "N = " + N + "\n" +
                "E = " + E + "\n" +
                "S = " + S + "\n" +
                "P = " + P + "\n";
    }
}