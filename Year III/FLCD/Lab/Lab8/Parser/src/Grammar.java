import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Grammar {
    private Set<String> N = new HashSet<>();
    private Set<String> E = new HashSet<>();
    private final HashMap<Set<String>, Set<List<String>>> P = new HashMap<>();
    private String S = "";

    public Grammar(String filename) {
        readFromFile(filename);
    }

    private void readFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            this.N = new HashSet<>(Arrays.asList(parseLine(reader.readLine())));
            this.E = new HashSet<>(Arrays.asList(parseLine(reader.readLine())));
            this.S = reader.readLine().split("=")[1].trim();

            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null && !line.equals("}")) {
                parseProduction(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String[] parseLine(String line) {
        String[] parts = line.split("=", 2);
        if (parts.length < 2) {
            return new String[0]; // or throw an exception
        }
        return parts[1].trim().replaceAll("[{}]", "").split("\\s+");
    }

    private void parseProduction(String line) {
        String[] parts = line.split("->");
        if (parts.length < 2) {
            return; // or throw an exception
        }

        Set<String> lhs = new HashSet<>(Arrays.asList(parts[0].trim().split(",\\s*")));
        String[] rhsParts = parts[1].split("\\|");

        for (String rhs : rhsParts) {
            List<String> productionElements = Arrays.asList(rhs.trim().split("\\s+"));
            P.computeIfAbsent(lhs, k -> new HashSet<>()).add(productionElements);
        }
    }

    public Set<String> getN() {
        return N;
    }

    public Set<String> getE() {
        return E;
    }

    public HashMap<Set<String>, Set<List<String>>> getP() {
        return P;
    }

    public String getS() {
        return S;
    }

    public Set<List<String>> getProductionForNonterminal(String nonTerminal) {
        for (Set<String> lhs : P.keySet()) {
            if (lhs.contains(nonTerminal)) {
                return P.get(lhs);
            }
        }
        return new HashSet<>();
    }
}
