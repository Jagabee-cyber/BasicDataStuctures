/*
 * Name: Zachary Wang
 * Email: zaw001@ucsd.edu
 * PID: A177855519
 * Sources Used: JDK 17 Doc
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * The custom tester for PA5, which covers some basic test cases.
 */
public class CustomTester {
    // Optional: add test variables here
    MyHashMap<String, Integer> threeElementMap;
    MyHashSet<String> threeElementSet;
    MyHashMap<String, Integer> singleMap;

    /**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
    @Before
    public void setup() {
        // Optional: add setup here
        threeElementMap = new MyHashMap<String, Integer>();
        MyHashMap<String, Integer>.Node<String, Integer> mapElement1 =  
            threeElementMap.new Node<String, Integer>("A", 1);
        MyHashMap<String, Integer>.Node<String, Integer> mapElement2 =  
            threeElementMap.new Node<String, Integer>("B", 2);
        MyHashMap<String, Integer>.Node<String, Integer> mapElement3 =  
            threeElementMap.new Node<String, Integer>("F", 6);
        
        threeElementMap.hashTable[0] = mapElement1;
        threeElementMap.hashTable[1] = mapElement2;
        mapElement1.setNext(mapElement3);

        threeElementMap.size = 3;

        threeElementSet = new MyHashSet<String>();
        
        MyHashMap<String, Object>.Node<String, Object> setElement1 =  
            threeElementSet.hashMap.new Node<String, Object>
                ("A", MyHashSet.DEFAULT_OBJECT);
        MyHashMap<String, Object>.Node<String, Object> setElement2 =  
            threeElementSet.hashMap.new Node<String, Object>
                ("B", MyHashSet.DEFAULT_OBJECT);
        MyHashMap<String, Object>.Node<String, Object> setElement3 =  
            threeElementSet.hashMap.new Node<String, Object>
                ("F", MyHashSet.DEFAULT_OBJECT);

        threeElementSet.hashMap.hashTable[0] = setElement1;
        threeElementSet.hashMap.hashTable[1] = setElement2;
        setElement1.setNext(setElement3);

        threeElementSet.hashMap.size = 3;
    }

    // ----------------MyHashMap class----------------

    /**
     * Test MyHashMap constructor with an invalid initial capacity
     */
    @Test
    public void testMyHashMapConstructorInvalidCapacity() {
        boolean test1 = false;
        boolean test2 = false;
        try {
            MyHashMap<String, Integer> hashMap = 
                new MyHashMap<String, Integer>(0);
        } catch (IllegalArgumentException e) {
            test1 = true;
        }

        assertTrue(test1);

        try {
            MyHashMap<String, Integer> hashMap = 
                new MyHashMap<String, Integer>(-5);
        } catch (IllegalArgumentException e) {
            test2 = true;
        }

        assertTrue(test2);
    }

    /**
     * Test MyHashMap get with a null key
     */
    @Test
    public void testMyHashMapGetNullKey() {
        boolean test1 = false;

        try {
            threeElementMap.get(null);
        } catch (NullPointerException e) {
            test1 = true;
        }

        assertTrue(test1);
    }

    /**
     * Test MyHashMap get with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapGetKeyDoesNotExist() {
        assertEquals(null, threeElementMap.get("C"));
    }

    /**
     * Test MyHashMap put with a null key
     */
    @Test
    public void testMyHashMapPutNullKey() {
        boolean test1 = false;

        try {
            threeElementMap.put(null, 6);
        } catch (NullPointerException e) {
            test1 = true;
        }

        assertTrue(test1);
        assertEquals(3, threeElementMap.size);
    }

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashMapPutKeyAlreadyExists() {
        assertEquals(Integer.valueOf(2), threeElementMap.put("B", 7));
        assertTrue(threeElementMap.hashTable[1].equals(
            threeElementMap.new Node<String, Integer>("B", 7)));
        assertEquals(3, threeElementMap.size);
    }

    /**
     * Test MyHashMap remove with a null key
     */
    @Test
    public void testMyHashMapRemoveNullKey() {
        boolean test1 = false;

        try {
            threeElementMap.remove(null);
        } catch (NullPointerException e) {
            test1 = true;
        }

        assertTrue(test1);
        assertEquals(3, threeElementMap.size);
    }

    /**
     * Test MyHashMap remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapRemoveKeyDoesNotExist() {
        assertEquals(null, threeElementMap.remove("C"));
        assertEquals(3, threeElementMap.size);
    }

    /**
     * Test MyHashMap getHash with a null key
     */
    @Test
    public void testMyHashMapGetHashNullKey() {
        boolean test1 = false;

        try {
            threeElementMap.getHash(null, 5);
        } catch (NullPointerException e) {
            test1 = true;
        }

        assertTrue(test1);
    }

    // ----------------MyHashSet class----------------

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashSetAddAlreadyExists() {
        assertEquals(false, threeElementSet.add("B"));
        assertEquals(3, threeElementMap.size);
    }

    /**
     * Test MyHashSet remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashSetRemoveDoesNotExist() {
        assertEquals(false, threeElementSet.remove("C"));
        assertEquals(3, threeElementMap.size);
    }
}