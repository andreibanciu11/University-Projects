import java.util.Hashtable;
import java.util.List;

public class SymbolTable {
    private Hashtable<String, List<KeyValuePair<String, Integer>>> hashtable;
    private int tableSize;

    public SymbolTable(int tableSize) {
        this.hashtable = new Hashtable<>(100);
        this.tableSize = tableSize;
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % tableSize;
    }

    public void put(String key) {
        int index = hash(key);
        List<KeyValuePair<String, Integer>> hashList = this.hashtable.get(Integer.toString(index));
        hashList.add(new KeyValuePair<>(key, hashList.size()));
    }

    public KeyValuePair<String, Integer> getKeyValuePair(String key) {
        int index = hash(key);
        List<KeyValuePair<String, Integer>> listKeyValuePair = this.hashtable.get(Integer.toString(index));
        return search(key);
    }

    public KeyValuePair<String, Integer> search(String key){
        int index = hash(key);
        if(this.hashtable.containsKey(Integer.toString(index))) {
            for(KeyValuePair<String, Integer> keyValuePair : this.hashtable.get(Integer.toString(index)))
                if(key == keyValuePair.getKey())
                    return keyValuePair;
            return null;
        }
        else
            return null;
    }

    public void printAll() {
        System.out.println(this.hashtable.toString());
    }
}
