import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SymbolTable {
    private Hashtable<Character, List<KeyValuePair>> hashtable;
    private int tableSize;
    private int newIndex = 0;

    public SymbolTable(int tableSize, String input) {
        this.tableSize = tableSize;
        this.hashtable = new Hashtable<>(tableSize);
        this.splitAndStore(input);
    }

    public void splitAndStore(String input) {
        char[] characters = input.toCharArray();
        for (char character : characters) {
            put(character);
        }
    }

    public void put(char key) {
        List<KeyValuePair> hashList = this.hashtable.get(key);
        if (hashList == null) {
            hashList = new ArrayList<>();
            this.hashtable.put(key, hashList);
        }

        for (KeyValuePair kvp : hashList) {
            if (kvp.getKey().equals(key)) {
                return;
            }
        }

        int index = newIndex;
        newIndex++;
        hashList.add(new KeyValuePair(key, index));
    }

    public String search(char key) {
        List<KeyValuePair> hashList = this.hashtable.get(key);
        if (hashList != null) {
            for (KeyValuePair kvp : hashList) {
                if (kvp.getKey().equals(key)) {
                    return kvp.toString();
                }
            }
        }
        return "Key not found!";
    }

    public void printAll() {
        for (char key : this.hashtable.keySet()) {
            List<KeyValuePair> hashList = this.hashtable.get(key);
            if (!hashList.isEmpty()) {
                System.out.print("Bucket " + key + ": ");
                for (KeyValuePair kvp : hashList) {
                    System.out.print(kvp.toString());
                }
                System.out.println();
            }
        }
    }
}
