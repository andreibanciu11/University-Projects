import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class SymbolTable {
    private Hashtable<String, List<KeyValuePair>> hashtable;
    private int tableSize;
    private int newIndex = 0;

    public SymbolTable(int tableSize) {
        this.tableSize = tableSize;
        this.hashtable = new Hashtable<>(tableSize);
    }

    public void put(String key) {
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

    public boolean search(String key) {
        List<KeyValuePair> hashList = this.hashtable.get(key);
        if (hashList != null) {
            for (KeyValuePair kvp : hashList) {
                if (kvp.getKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public KeyValuePair getPosition(String key) {
        List<KeyValuePair> hashList = this.hashtable.get(key);
        if (hashList != null) {
            for (int i = 0; i < hashList.size(); i++) {
                KeyValuePair kvp = hashList.get(i);
                if (kvp.getKey().equals(key)) {
                    return new KeyValuePair((key.hashCode() & 0x7fffffff) % tableSize, kvp.getValue());
                }
            }
        }
        return null;
    }

    public String toString() {
        String result = "";
        for (String key : this.hashtable.keySet()) {
            List<KeyValuePair> hashList = this.hashtable.get(key);
            if (!hashList.isEmpty()) {
                result += "Bucket " + (key.hashCode() & 0x7fffffff) % tableSize + ": ";
                for (KeyValuePair kvp : hashList) {
                    result += kvp.toString();
                }
                result += "\n";
            }
        }
        return result;
    }
}