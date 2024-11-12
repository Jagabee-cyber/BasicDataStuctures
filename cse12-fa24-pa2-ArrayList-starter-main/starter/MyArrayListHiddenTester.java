/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the MyArrayListHiddenTester java file that implements 
   all the hidden tests for MyArrayList.
 */



//Import statements
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class runs all the hidden tests on all functions of MyArrayList,
 * choosing to closely make sure that all results are valid
 */
public class MyArrayListHiddenTester {
    // Do not change the method signatures!

    //Constants
    static final int DEFAULT_CAPACITY = 5;

    Object[] nullArray = null;
    Object[] defaultArray = new Object[5];
    Integer[] testArray = new Integer[]{1,2,null,3,4};
    Object[] emptyArray = new Object[]{};
    Integer[] normalArray = new Integer[]{1,2,3};
    Integer[] multipleArray = new Integer[]{1,5,7,8,1};

    private MyArrayList ctrNullArg, listWithMix, emptyArrayList, 
        normalArrayList, multipleArrayList;


    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */
    @Before
    public void setUp() throws Exception {
        listWithMix = new MyArrayList(testArray);
        ctrNullArg = new MyArrayList(nullArray);
        emptyArrayList = new MyArrayList(emptyArray);
        normalArrayList = new MyArrayList(normalArray);
        multipleArrayList = new MyArrayList(multipleArray);
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){
        boolean exception = false;

        try {
            MyArrayList invalidCtrArg = new MyArrayList(-5);
        } catch (IllegalArgumentException e) {
            exception = true;
        }

        assertEquals("Missed Invalid Parameter", true, exception);
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        

        assertEquals("Check the contents", defaultArray, ctrNullArg.data);
        assertEquals("Check size", 0, ctrNullArg.size);
        assertEquals("Check capacity", 5, ctrNullArg.getCapacity());
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
        
        assertEquals("Check contents", new Integer[]{1,2,null, 3, 4}, 
            listWithMix.data);
        assertEquals("Check size", 5, listWithMix.size);
        assertEquals("Check capacity", 5, listWithMix.getCapacity());

    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        
        //Normal mixed array
        listWithMix.append(9);
        assertEquals("Check contents", new Integer[]{1,2,null,3,4,9, null,
            null, null, null}, listWithMix.data);
        assertEquals("Check size", 6, listWithMix.size);
        assertEquals("Check capacity", 10, listWithMix.getCapacity());

        //Empty array
        emptyArrayList.append(5);
        assertEquals("Check contents", new Integer[]{5, null, null, null, 
            null}, emptyArrayList.data);
        assertEquals("Check size", 1, emptyArrayList.size);
        assertEquals("Check capacity", 5, emptyArrayList.getCapacity());
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        
        //Normal mixed array
        listWithMix.append(null);
        assertEquals("Check contents", new Integer[]{1,2,null,3,4,null, null,
            null, null, null}, listWithMix.data);
        assertEquals("Check size", 6, listWithMix.size);
        assertEquals("Check capacity", 10, listWithMix.getCapacity());

        //Empty array
        emptyArrayList.append(null);
        assertEquals("Check contents", new Integer[]{null, null, null, null, 
            null}, emptyArrayList.data);
        assertEquals("Check size", 1, emptyArrayList.size);
        assertEquals("Check capacity", 5, emptyArrayList.getCapacity());
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        //Normal mixed array
        listWithMix.prepend(9);
        assertEquals("Check contents", new Integer[]{9,1,2,null,3,4, null,
            null, null, null}, listWithMix.data);
        assertEquals("Check size", 6, listWithMix.size);
        assertEquals("Check capacity", 10, listWithMix.getCapacity());

        //Empty array
        emptyArrayList.prepend(5);
        assertEquals("Check contents", new Integer[]{5, null, null, null, 
            null}, emptyArrayList.data);
        assertEquals("Check size", 1, emptyArrayList.size);
        assertEquals("Check capacity", 5, emptyArrayList.getCapacity());
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        //Normal mixed array
        listWithMix.prepend(null);
        assertEquals("Check contents", new Integer[]{null,1,2,null,3,4, null,
            null, null, null}, listWithMix.data);
        assertEquals("Check size", 6, listWithMix.size);
        assertEquals("Check capacity", 10, listWithMix.getCapacity());

        //Empty array
        emptyArrayList.prepend(null);
        assertEquals("Check contents", new Integer[]{null, null, null, null, 
            null}, emptyArrayList.data);
        assertEquals("Check size", 1, emptyArrayList.size);
        assertEquals("Check capacity", 5, emptyArrayList.getCapacity());
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBounds(){
        boolean exception1 = false;
        boolean exception2 = false;

        //Index lower than 0
       try {
           normalArrayList.insert(-1, 10);
       } catch (IndexOutOfBoundsException e) {
            exception1 = true;
       }
       assertEquals("Check input validity", true, exception1);
       assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
       assertEquals("Check size", 3, normalArrayList.size);
       assertEquals("Check capacity", 3, normalArrayList.getCapacity());

        //Index beyond size
       try {
           normalArrayList.insert(5, 10);
       } catch (IndexOutOfBoundsException e) {
            exception2 = true;
       }
       assertEquals("Check input validity", true, exception2);
       assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
       assertEquals("Check size", 3, normalArrayList.size);
       assertEquals("Check capacity", 3, normalArrayList.getCapacity());
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        normalArrayList.insert(1, 10);
        normalArrayList.insert(1, 9);
        normalArrayList.insert(0, 200);
        normalArrayList.insert(5, 35);

        assertEquals("Check contents", new Integer[]{200,1,9,10,2,35,3,
            null, null, null, null, null}, normalArrayList.data);
       assertEquals("Check size", 7, normalArrayList.size);
       assertEquals("Check capacity", 12, normalArrayList.getCapacity());
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        boolean exception1 = false;
        boolean exception2 = false;

        //Negative index
        try {
            normalArrayList.get(-4);
        } catch (IndexOutOfBoundsException e) {
            exception1 = true;
        }
        assertEquals("Check input validity", true, exception1);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());
        
        //Index much greater than size
        try {
            normalArrayList.get(5);
        } catch (IndexOutOfBoundsException e) {
            exception2 = true;
        }
        assertEquals("Check input validity", true, exception2);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
        boolean exception1 = false;
        boolean exception2 = false;

        //Negative index
        try {
            normalArrayList.set(-4, 5);
        } catch (IndexOutOfBoundsException e) {
            exception1 = true;
        }
        assertEquals("Check input validity", true, exception1);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());
        
        //Index much greater than size
        try {
            normalArrayList.set(5, 5);
        } catch (IndexOutOfBoundsException e) {
            exception2 = true;
        }
        assertEquals("Check input validity", true, exception2);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        assertEquals("Check removed element", null, listWithMix.remove(2));
        assertEquals("Check removed element", 2, listWithMix.remove(1));
        assertEquals("Check removed element", 1, listWithMix.remove(0));

        assertEquals("Check contents", new Integer[]{3,4,null,null,null},
            listWithMix.data);
        assertEquals("Check size", 2, listWithMix.size);
        assertEquals("Check capacity", 5, listWithMix.getCapacity());

    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        boolean exception1 = false;
        boolean exception2 = false;

        //Negative index
        try {
            normalArrayList.remove(-4);
        } catch (IndexOutOfBoundsException e) {
            exception1 = true;
        }
        assertEquals("Check input validity", true, exception1);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());
        
        //Index much greater than size
        try {
            normalArrayList.remove(5);
        } catch (IndexOutOfBoundsException e) {
            exception2 = true;
        }
        assertEquals("Check input validity", true, exception2);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
       boolean exception = false;

       try {
           normalArrayList.expandCapacity(2);
       } catch (IllegalArgumentException e) {
            exception = true;
       }
        assertEquals("Valid exception", true, exception);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity * 2 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        
        //Testing a filled array list
        normalArrayList.expandCapacity(9);
        assertEquals("Check contents", new Integer[]{1,2,3,null,null,null,
            null,null,null}, normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 9, normalArrayList.getCapacity());

        //Testing an empty array
        emptyArrayList.expandCapacity(9);
        assertEquals("Check contents", new Object[]{null,null,null,null,
            null,null,null,null,null}, emptyArrayList.data);
        assertEquals("Check size", 0, emptyArrayList.size);
        assertEquals("Check capacity", 9, emptyArrayList.getCapacity());
    }

    /**
     * Aims to test the rotate method when 
     * input numPositions is out of bounds
     */
    @Test
    public void testRotateOutOfBound() {
        boolean exception1 = false;
        boolean exception2 = false;
        boolean exception3 = false;

        //Negative index
        try {
            normalArrayList.rotate(-4);
        } catch (IndexOutOfBoundsException e) {
            exception1 = true;
        }
        assertEquals("Check input validity", true, exception1);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());
        
        //Index much greater than size
        try {
            normalArrayList.rotate(5);
        } catch (IndexOutOfBoundsException e) {
            exception2 = true;
        }
        assertEquals("Check input validity", true, exception2);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());

        //Index equal to size
        try {
            normalArrayList.rotate(3);
        } catch (IndexOutOfBoundsException e) {
            exception3 = true;
        }
        assertEquals("Check input validity", true, exception3);
        assertEquals("Check contents", new Integer[]{1,2,3}, 
            normalArrayList.data);
        assertEquals("Check size", 3, normalArrayList.size);
        assertEquals("Check capacity", 3, normalArrayList.getCapacity());
    }

    /**
     * Aims to test the find method when 
     * there are multiple of the input element
     */
    @Test
    public void testFindMultiple(){
	    assertEquals("Check index", 4, multipleArrayList.find(1));
        assertEquals("Check contents", new Integer[]{1,5,7,8,1},
            multipleArrayList.data);
        assertEquals("Check size", 5, multipleArrayList.size);
        assertEquals("Check capacity", 5, multipleArrayList.getCapacity());

    }
	
    /**
     * Aims to test the find method when 
     * input element does not exist in the list
     */
    @Test
    public void testFindDoesNotExist(){
        assertEquals("Check index", -1, multipleArrayList.find(2));
        assertEquals("Check contents", new Integer[]{1,5,7,8,1},
            multipleArrayList.data);
        assertEquals("Check size", 5, multipleArrayList.size);
        assertEquals("Check capacity", 5, multipleArrayList.getCapacity());
    }

}
