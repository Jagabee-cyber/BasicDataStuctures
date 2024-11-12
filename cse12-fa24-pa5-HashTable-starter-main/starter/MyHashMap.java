/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the MyHashMap java file that  sets the constructors, 
   Hash map functions, and is what the PublicTester and CustomTester 
   run tests on.
 */

/**
 * This class stores values in a hashmap, allowing a user to perform
 * a variety of functions on it. These functions include storing, removing,
 * getting, and a variety of other functions
 * 
 * Instance variables: 
 * hashTable ~ The underlying array for the hash table
 * size ~ TThe number of (key, value) pairs currently in the hash table
 */
public class MyHashMap<K,V> {

    //Constants (Magic numbers)
    private static final int DEFAULT_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.8;
    private static final int EXPAND_CAPACITY_RATIO = 2;

    //Instance Variables
    Node[] hashTable;
    int size;

    /**
     * Default constructor for the Hash Map, constructs an empty
     * Node array with 5 slots
     */
    public MyHashMap() {
        this.hashTable = new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Method to construct a Hash Map with the given capacity
     * @param initialCapacity is the given capacity
     */
    public MyHashMap(int initialCapacity) {
        if(initialCapacity <= 0){
           throw new IllegalArgumentException();
        }

        this.hashTable = new Node[initialCapacity];
        this.size = 0;
    }
    
    /**
     * Method to get a value from a key in the hash table
     * @param key is the key it will search for
     * @return either the value or null if no value found
     */
    public V get(K key) {
        if(key == null){
            throw new NullPointerException();
        }

        Node curr = this.hashTable[getHash(key, getCapacity())];
        while(curr != null){
            if(curr.key.equals(key)){
                return (V)curr.value;
            }
            
            curr = curr.next;
        }

        return null;
    }

    /**
     * Method that finds a key in the hashtable and replaces the
     * value of it if found in the hashtable
     * @param key is the key that is being found
     * @param value is the value housed in that bucket
     * @return null if key not found or the original value if found
     */
    public V put(K key, V value) {
        if(key == null || value == null){
            throw new NullPointerException();
        }

        if(this.size>=(LOAD_FACTOR*getCapacity())){
            expandCapacity();
        }

        int index = getHash(key, getCapacity());
        Node newInsert = new Node(key, value);

        if(this.hashTable[index] == null){
            this.hashTable[index] = newInsert;
            this.size ++;
        }
        else{
            Node curr = this.hashTable[index];
            while(curr.next!=null){
                if(curr.key.equals(key)){
                    V result = (V)curr.value;
                    curr.value = value;
                    return result;
                }
                curr = curr.next;
            }
            if(curr.key.equals(key)){
                    V result = (V)curr.value;
                    curr.value = value;
                    return result;
                }
            if(curr.next==null){
                curr.next = newInsert;
                this.size ++;
                return null;
            }
        }
        return null;
    }

    /**
     * Method for removing the mapping for a specific key
     * @param key is the key that will be removed
     * @return null if not found or the original value of the mapped key
     */
    public V remove(K key) {
        if(key == null){
            throw new NullPointerException();
        }

        int index = getHash(key, getCapacity());

        if(this.hashTable[index] == null){
            return null;
        }
        else{
            Node curr = this.hashTable[index];

            if(curr.key == key){
                V result = (V)curr.value;
                this.hashTable[index] = curr.next;
                this.size --;
                return result;
            }

            while(curr.next!=null){
                if(curr.next.key == key){
                    V result = (V)curr.next.value;
                    Node newNext = curr.next.next;
                    curr.next = newNext;
                    this.size --;
                    return result;
                }
                curr = curr.next;
            }
            
            
        }
        return null;
    }

    /**
     * Method for retrieving how many key, value pairs there are
     * @return the size of the hash table
     */
    public int size() {
        return this.size; 
    }

    /**
     * Method for retrieving the capacity of the hash map
     * @return the capacity
     */
    public int getCapacity() {
        return this.hashTable.length;
    }

    /**
     * Method for clearing the entire hash map
     */
    public void clear() {
        this.hashTable = new Node[getCapacity()];
        this.size = 0;
    }

    /**
     * Method for determining if the hash map is empty
     * @return whether or not it is empty
     */
    public boolean isEmpty() {
        return(this.size==0);
    }

    /**
     * Method that expands the capacity to double and rehashes everything
     */
    public void expandCapacity() {
        Node[] temp = new Node[getCapacity()*EXPAND_CAPACITY_RATIO];

        for(int i=0; i<getCapacity(); i++){
            Node curr = this.hashTable[i];
            while(curr!=null){
                Node nextOne = curr.next;
                int newIndex = getHash((K)curr.key, 
                    getCapacity()*EXPAND_CAPACITY_RATIO);

                curr.next = null;

                if(temp[newIndex]==null){
                    temp[newIndex] = curr;
                }
                else{
                    Node withinCurr = temp[newIndex];
                    while(withinCurr!=null){
                        withinCurr = withinCurr.next;
                    }
                    withinCurr.next = curr;
                }

                curr = nextOne;

            }
        }

        this.hashTable = temp;
            
    }

    /**
     * Get the hash value for a given key
     * @param key is the key
     * @param capacity is the current capacity of the hashmap
     * @return the hash value
     */
    public int getHash(K key, int capacity) {
        if(key == null){
            throw new NullPointerException();
        }

        if(capacity <= 0){
            throw new IllegalArgumentException();
        }

        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    /**
     * A Node class that holds (key, value) pairs and references 
     * to the next node in the linked list
     */
    protected class Node<K,V> {
        K key;
        V value;
        Node next;

        /**
         * Constructor to create a single node
         * @param key key to store in this node
         * @param value value to store in this node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Accessor to get the next node in the list
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the next node in the list
         * @param node the new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Accessor to get the node's key
         * @return this node's key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Set the node's key
         * @param key the new key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Accessor to get the node's value
         * @return this node's value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Set the node's value
         * @param value the new value
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Check if this node is equal to another node
         * @param other the other node to check equality with
         * @return whether or not this node is equal to the other node
         */
        public boolean equals(Node<K, V> other) {
            return this.key.equals(other.key) && 
                this.value.equals(other.value);
        }
    }
}
