import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.HashSet;

/**
 * Your implementation of HashMap.
 *
 * @author
 * @version 1.0
 * @userid
 * @GTID
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;


    public HashMap() {
        this(HashMapInterface.INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Neither key nor value can "
                    + "be null");
        }
        if ((double) (size + 1) / table.length
                > HashMapInterface.MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }
        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % table.length;

        //case 1: there is nothing in the spot
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            size++;

            //case 2 there is something in the spot. Have to iterate
            //through the array to determine if there is a matching
            //key
        } else {
            for (MapEntry<K, V> tempNode = table[index];
                 tempNode != null;
                 tempNode = tempNode.getNext()) {
                //found a key that matched so replaced its value
                if (tempNode.getKey().equals(key)) {
                    V tempValue = tempNode.getValue();
                    tempNode.setValue(value);
                    return tempValue;
                }
            }
            //No repeat key so we just need to add a new node to
            //the front of the external chain containing the data
            MapEntry<K, V> tempNode = table[index];
            //set new entry
            table[index] = new MapEntry<>(key, value, tempNode);
            size++;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % table.length;
        if (table[index] == null) {
            throw new NoSuchElementException("The key is not in the map");
        }

        V tempValue;
        MapEntry<K, V> previousNode = null;

        for (MapEntry<K, V> currentNode = table[index];
             currentNode != null;
             currentNode = currentNode.getNext()) {
            if (currentNode.getKey().equals(key)) {
                tempValue = currentNode.getValue();
                //case where key is in the first element
                if (previousNode == null) {
                    table[index] = currentNode.getNext();
                    //any other spot
                } else {
                    previousNode.setNext(currentNode.getNext());
                }
                size--;
                return tempValue;
            }
            previousNode = currentNode;
        }
        throw new NoSuchElementException("The key is not in the map");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % table.length;

        //if (table[index].getKey() == key) {
        //    throw new NoSuchElementException("The key is not in the map");
        //}
        V tempValue;
        for (MapEntry<K, V> currentNode = table[index];
             currentNode != null;
             currentNode = currentNode.getNext()) {
            if (currentNode.getKey().equals(key)) {
                tempValue = currentNode.getValue();
                return tempValue;
            }
        }
        throw new NoSuchElementException("The key is not in the map");
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % table.length;
        if (table[index] == null) {
            return false;
        }
        for (MapEntry<K, V> currentNode = table[index];
             currentNode != null;
             currentNode = currentNode.getNext()) {
            if (currentNode.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[HashMapInterface.INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (MapEntry<K, V> currentNode = table[i]; currentNode != null;
                     currentNode = currentNode.getNext()) {
                    keySet.add(currentNode.getKey());
                }
            }
        }
        return keySet;
    }

    @Override
    public List<V> values() {
        List<V> valueList = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (MapEntry<K, V> currentNode = table[i]; currentNode != null;
                     currentNode = currentNode.getNext()) {
                    valueList.add(currentNode.getValue());
                }
            }
        }
        return valueList;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException("Length cannot be less than "
                    + "the the size of the hashtable and cannot be less "
                    + "than zero");
        }
        MapEntry<K, V>[] temp = (MapEntry<K, V>[]) new MapEntry[length];

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (MapEntry<K, V> currentNode = table[i];
                     currentNode != null;
                     currentNode = currentNode.getNext()) {

                    temp = addHelper(currentNode.getKey(),
                            currentNode.getValue(), temp, length);
                }
            }
        }
        table = temp;
    }

    /**
     * A helper method for resize. Does the same thing as add but
     * doesn't increase the size of the backing array. Also does not check
     * the load factor.
     * @param key the key to insert
     * @param value the value of the entry
     * @param temp the backing array that we want to add elements to
     * @param length the length of the new array
     * @return the backing array for resizing
     */
    private MapEntry<K, V>[] addHelper(K key, V value,
                                       MapEntry<K, V>[] temp, int length) {
        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % length;

        //case 1: there is nothing in the spot
        if (temp[index] == null) {
            temp[index] = new MapEntry<>(key, value);
            //case 2: there is something in the spot so we
            //just need to stick something in front
        } else {
            temp[index] = new MapEntry<>(key, value, temp[index]);
        }
        return temp;
    }

    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}


//    /**
//     * Create a hash map with no entries. The backing array has an initial
//     * capacity of {@code INITIAL_CAPACITY}.
//     * <p>
//     * Do not use magic numbers!
//     * <p>
//     * Use constructor chaining.
//     */
//    public HashMap() {
//        table = (MapEntry<K, V>[]) new MapEntry[INITIAL_CAPACITY];
//        size = 0;
//    }
//
//    /**
//     * Create a hash map with no entries. The backing array has an initial
//     * capacity of {@code initialCapacity}.
//     * <p>
//     * You may assume {@code initialCapacity} will always be positive.
//     *
//     * @param initialCapacity initial capacity of the backing array
//     */
//    public HashMap(int initialCapacity) {
//        this.table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
//    }
//
//    @Override
//    public V put(K key, V value) {
//        if (key == null) {
//            throw new IllegalArgumentException("Key cannot be null");
//        }
//        if (value == null) {
//            throw new IllegalArgumentException("Value cannot be null");
//        } else {
//            return putHelper(key, value);
//        }
//    } // DONE
//
//    @Override
//    public V remove(K key) { // TODO
//        if (key == null) {
//            throw new IllegalArgumentException("Key cannot be null");
//        } else {
//            return removeHelper(key);
//        }
//    }
//
//    @Override
//    public V get(K key) {
//        if (key == null) {
//            throw new IllegalArgumentException("Key cannot be null");
//        }
//        if (getHelper(key) == null) {
//            throw new NoSuchElementException("Key is not in map");
//        } else {
//            return getHelper(key);
//        }
//    }
//
//    @Override
//    public boolean containsKey(K key) {
//        if (key == null) {
//            throw new IllegalArgumentException("Key cannot be null");
//        } else {
//            return table[hashCodeHelper(key)] != null;
//        }
//    } // DONE
//
//    @Override
//    public void clear() {
//        table = new MapEntry[INITIAL_CAPACITY];
//        size = 0;
//    } // DONE
//
//    @Override
//    public int size() {
//        // DO NOT MODIFY THIS METHOD!
//        return size;
//    }
//
//    @Override
//    public Set<K> keySet() {
//        Set<K> keySet = new HashSet<>();
//        for (int i = 0; i < table.length; i++) {
//            if (table[i] != null) {
//                for (MapEntry<K, V> node = table[i]; node != null;
//                     node = node.getNext()) {
//                    keySet.add(node.getKey());
//                }
//            }
//        }
//        return keySet;
//    } // DONE
//
//    @Override
//    public List<V> values() {
//        List<V> list = new ArrayList<>();
//        for (int i = 0; i < table.length; i++) {
//            if (table[i] != null) {
//                for (MapEntry<K, V> node = table[i]; node != null;
//                     node = node.getNext()) {
//                    list.add(node.getValue());
//                }
//            }
//        }
//        return list;
//    } // DONE
//
//    @Override
//    public void resizeBackingTable(int length) { // Ran out of time
//        if (length <= 0 || length < size) {
//            throw new IllegalArgumentException("Length cannot be less than "
//                    + "the the size of the hashtable and cannot be less "
//                    + "than zero");
//        }
//        MapEntry<K, V>[] temp = (MapEntry<K, V>[]) new MapEntry[length];
//
//        for (int i = 0; i < table.length; i++) {
//            if (table[i] != null) {
//                for (MapEntry<K, V> currentNode = table[i];
//                     currentNode != null;
//                     currentNode = currentNode.getNext()) {
//
//                    temp = addHelper(currentNode.getKey(),
//                            currentNode.getValue(), temp, length);
//                }
//            }
//        }
//        table = temp;
//
//    }
//
//    @Override
//    public MapEntry<K, V>[] getTable() {
//        // DO NOT EDIT THIS METHOD!
//        return table;
//    }
//
//    /**
//     * Returns the code for the given key
//     *
//     * @param key the key to calculate
//     * @return int of the place of the key
//     */
//    private int hashCodeHelper(K key) {
//        return Math.abs(key.hashCode()) % table.length;
//    }
//
//    /**
//     * calculates load factor
//     *
//     * @return load factor
//     */
//    private double loadFactor() {
//        return (size + 1) / table.length;
//    }
//
//    /**
//     * Put helper method
//     *
//     * @param key   the key put
//     * @param value the value to put
//     * @return the key value pair
//     */
//    private V putHelper(K key, V value) { // TODO
//        int code = hashCodeHelper(key);
//        if (loadFactor() > MAX_LOAD_FACTOR) {
//            resizeBackingTable(table.length);
//        }
//        if (table[code] == null) {
//            table[code] = new MapEntry<>(key, value);
//            size++;
//        } else if (table[code] != null) {
//            for (MapEntry<K, V> node = table[code]; node != null;
//                 node = node.getNext()) {
//                if (node.getKey().equals(key)) {
//                    V temp = node.getValue();
//                    node.setValue(value);
//                    return temp;
//                }
//            }
//            MapEntry<K, V> node = table[code];
//            table[code] = new MapEntry<>(key, value, node);
//            size++;
//        }
//        return null;
//    }
//
//    /**
//     * helper method for remove
//     *
//     * @param key the key of the element to be removed
//     * @return the element removed
//     */
//    private V removeHelper(K key) {
//        V value;
//        MapEntry<K, V> temp = null;
//
//        for (MapEntry<K, V> node = table[hashCodeHelper(key)];
//             node != null; node = node.getNext()) {
//            if (node.getKey().equals(key)) {
//                value = node.getValue();
//                if (temp == null) {
//                    table[hashCodeHelper(key)] = node.getNext();
//                } else {
//                    temp.setNext(node.getNext());
//                }
//                size--;
//                return value;
//            }
//            temp = node;
//        }
//        throw new NoSuchElementException("Key is not in the map");
//    }
//
//    /**
//     * Resize helper method
//     *
//     * @param length the table length
//     */
//    private void resizeHelper(int length) {
//        MapEntry<K, V>[] tableCopy = (MapEntry<K, V>[]) new MapEntry[length];
//        for (int i = 0; i < table.length; i++) {
//            if (table[i] != null) {
//                for (MapEntry<K, V> node = table[i]; node != null;
//                     node = node.getNext()) {
//
//                    if (tableCopy[i] == null) {
//                        tableCopy[i] = new MapEntry<>(table[i].getKey(),
//                                table[i].getValue());
//                    } else {
//                        tableCopy[i] = new MapEntry<>(table[i].getKey(),
//                                table[i].getValue(), table[i]);
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * helper method for get
//     *
//     * @param key the key of the value to get
//     * @return the value
//     */
//    private V getHelper(K key) {
//        int index = hashCodeHelper(key) % table.length;
//
//        V temp;
//        for (MapEntry<K, V> node = table[index]; node != null;
//             node = node.getNext()) {
//            if (node.getKey().equals(key)) {
//                temp = node.getValue();
//                return temp;
//            }
//        }
//        return null;
//    }
//}
