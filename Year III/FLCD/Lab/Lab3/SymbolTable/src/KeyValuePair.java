public class KeyValuePair<K,V> {
    private K key;
    private V value;

    public KeyValuePair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public String toString() {
        return "Key: " + this.getKey().toString() + ", Value: " + this.getValue().toString();
    }
}
