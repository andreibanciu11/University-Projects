import java.util.ArrayList;
import java.util.List;

public class ProgramInternalForm {
    private List<KeyValuePair<Integer, KeyValuePair<Integer, Integer>>> pif = new ArrayList<>();

    public void add(Integer code, KeyValuePair<Integer, Integer> value) {
        KeyValuePair<Integer, KeyValuePair<Integer, Integer>> pair = new KeyValuePair<>(code, value);
        pif.add(pair);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (KeyValuePair<Integer, KeyValuePair<Integer, Integer>> pair : pif) {
            result.append(pair.getKey()).append(" -> (").append(pair.getValue().getKey()).append(", ").append(pair.getValue().getValue()).append(")\n");
        }
        return result.toString();
    }
}
