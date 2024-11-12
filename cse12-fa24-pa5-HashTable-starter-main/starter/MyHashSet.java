/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the MyHashSet java file that  sets the constructors, 
   Hash map functions, and is what the PublicTester and CustomTester 
   run tests on.
 */

/**
 * This class stores values in a hash set, allowing a user to perform
 * a variety of functions on it. These functions include adding, removing,
 * clearing, and a variety of other functions
 * 
 * Instance variables: 
 * DEFAULT_OBJECT ~ A default Object to use as a placeholder 
 *  value in the underlying hashMap
 * MyHashMap hashMap ~ TThe number of (key, value) pairs 
 *  currently in the hash table
 */

public class MyHashSet<E> {

    //Instance Variables
    public static final Object DEFAULT_OBJECT = new Object();
    MyHashMap<E, Object> hashMap;

    /**
     * Default constructor for the hash set
     */
    public MyHashSet() {
        this.hashMap = new MyHashMap();
    }

    /**
     * Constructor that sets an initial capacity
     * @param initialCapacity is the specified capacity
     */
    public MyHashSet(int initialCapacity) {
        if(initialCapacity<=0){
            throw new IllegalArgumentException();
        }
        this.hashMap = new MyHashMap(initialCapacity);
    }

    /**
     * Method for adding an element into the set
     * @param element is the element in question
     * @return whether or not the set did not contain the element
     */
    public boolean add(E element) {
        
        if(element == null){
            throw new NullPointerException();
        }

        if(this.hashMap.get(element) == null){
            this.hashMap.put(element, DEFAULT_OBJECT);
            return true;
        }
        return false; 
    }

    /**
     * Method for removing an element from the hash set
     * @param element is the element in analysis
     * @return whether or not the item was found and removed
     */
    public boolean remove(E element) {
        return hashMap.remove(element) != null;
    }

    /**
     * Method for determining the size of the set
     * @return the size of the hash set
     */
    public int size() {
        return hashMap.size; 
    }

    /**
     * Method for clearing the hash set
     */
    public void clear() {
        hashMap.clear();
    }

    /**
     * Method for determining if the hash set is empty
     * @return whether or not it is empty
     */
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }
}