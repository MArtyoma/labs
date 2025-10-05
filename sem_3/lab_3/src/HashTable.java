import java.util.LinkedList;

public class HashTable<K, V> {
  private static final int DEFAULT_CAPACITY = 16;
  private LinkedList<Entry<K, V>>[] table;
  private int size;

  @SuppressWarnings("unchecked")
  public HashTable() {
    table = new LinkedList[DEFAULT_CAPACITY];
    size = 0;
  }

  @SuppressWarnings("unchecked")
  public HashTable(int capacity) {
    table = new LinkedList[capacity];
    size = 0;
  }

  private int hash(K key) {
    return Math.abs(key.hashCode()) % table.length;
  }

  public void put(K key, V value) {
    int index = hash(key);
    if (table[index] == null) {
      table[index] = new LinkedList<Entry<K, V>>();
    }

    for (Entry<K, V> entry : table[index]) {
      if (entry.getKey().equals(key)) {
        entry.setValue(value);
        return;
      }
    }

    table[index].add(new Entry<K, V>(key, value));
    size++;
  }

  public V get(K key) {
    int index = hash(key);
    if (table[index] == null) {
      return null;
    }

    for (Entry<K, V> entry : table[index]) {
      if (entry.getKey().equals(key)) {
        return entry.getValue();
      }
    }

    return null;
  }

  public V remove(K key) {
    int index = hash(key);
    if (table[index] == null) {
      return null;
    }

    for (Entry<K, V> entry : table[index]) {
      if (entry.getKey().equals(key)) {
        V value = entry.getValue();
        table[index].remove(entry);
        size--;
        return value;
      }
    }

    return null;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void printTable() {
    for (int i = 0; i < table.length; i++) {
      System.out.print("Bucket " + i + ": ");
      if (table[i] != null) {
        for (Entry<K, V> entry : table[i]) {
          System.out
              .print("[" + entry.getKey() + " -> " + entry.getValue() + "] ");
        }
      }
      System.out.println();
    }
  }
}
