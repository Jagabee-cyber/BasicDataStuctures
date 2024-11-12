/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the CustomTester java file that runs all the custom tests
 */
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class contains custom test cases for MyDeque, 
 * MyStack, MyQueue, and MyAlgorithm
 */
public class CustomTester {
    /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                          int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }

    // ------------ Deque ---------------

    //Testing Constructor with illegal initial capacity
    @Test
    public void testDequeConstructor() {
        boolean test = false;

        try {
            MyDeque<Integer> deque = new MyDeque<>(-10);
        } catch (IllegalArgumentException e) {
            test = true;
        }

        assertTrue("This is an illegal input", test);
    }

    //Testing expandCapacity with a capacity of 0
    @Test 
    public void testExpandCapacityZero(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] orig = {};
        initDeque(deque, orig, 0, 0, 0);

        deque.expandCapacity();

        assertEquals("Capacity should have set to default", 
            10, deque.data.length);
        assertEquals("Size should not have changed", 0, deque.size);
        assertEquals("Front should be 0", 0, deque.front);
        assertEquals("Rear should not have changed", 0, deque.rear);
    }

    //Testing expandCapacity with wrapped
    @Test 
    public void testExpandCapacityWrap(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = {3,4,null, null,5};
        initDeque(deque, orig, 3, 4, 1);

        deque.expandCapacity();

        assertEquals("Capacity should have set to default", 
            10, deque.data.length);
        assertEquals("Size should not have changed", 3, deque.size);
        assertEquals("Front should be 0 now", 0, deque.front);
        assertEquals("Rear should be 2", 2, deque.rear);
        assertEquals("Front should be 5", 5, deque.data[0]);
        assertEquals("Index 1 should be 3", 3, deque.data[1]);
        assertEquals("Index 2 should be 4", 4, deque.data[2]);
    }

    //Testing addFirst at the very front
    @Test
    public void testAddFirst() {
        MyDeque<Integer> deque = new MyDeque<>(3);
        Integer[] orig = {4,5,6};
        initDeque(deque, orig, 3, 0, 2);

        deque.addFirst(1);

        assertEquals("Capacity should've doubled", 6,
                deque.data.length);
        assertEquals("Should increment size", 4, deque.size);
        assertEquals("Front should be at end", 5, deque.front);
        assertEquals("Rear shouldn't change when calling addFirst", 2,
                deque.rear);
        assertEquals("1 should have been inserted into index 5",
                1, deque.data[5]);
        assertEquals("Index 0 should not have changed", 4,
                deque.data[0]);
        assertEquals("Index 1 should not have changed",
                5, deque.data[1]);
        assertEquals("Index 2 should not have changed", 6,
                deque.data[2]);
    }

    //Testing addFirst with a null element
    @Test 
    public void testAddFirstNull(){
        MyDeque<Integer> deque = new MyDeque<>(3);
        boolean test = false;

        try {
            deque.addFirst(null);
        } catch (NullPointerException e) {
            test = true;
        }

        assertTrue("Null pointer should be thrown", test);
    }

    //Testing addLast at the very end
    @Test
    public void testAddLast() {
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = {null, null, 4, 5, 1};
        initDeque(deque, orig, 3, 2, 4);

        deque.addLast(-1);

        assertEquals("Capacity should not change if deque not full", 5,
                deque.data.length);
        assertEquals("Should increment size", 4, deque.size);
        assertEquals("Front shouldn't change when called addLast", 2,
                deque.front);
        assertEquals("Rear should be set to 0 in a", 0, deque.rear);
        assertEquals("-1 should have been inserted into index 0",
                -1, deque.data[0]);
        assertEquals("Index 2 should not have changed", 4,
                deque.data[2]);
        assertEquals("Index 3 should not have changed", 5,
                deque.data[3]);
        assertEquals("Index 4 should not have changed", 1,
                deque.data[4]);
    }

    //Testing addLast with a null element
    @Test 
    public void testAddLastNull(){
        MyDeque<Integer> deque = new MyDeque<>(3);
        boolean test = false;

        try {
            deque.addLast(null);
        } catch (NullPointerException e) {
            test = true;
        }

        assertTrue("Null pointer should be thrown", test);
    }

    //Testing removeFirst with 0 elements
    @Test 
    public void testRemoveFirstZero(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] orig = {};
        initDeque(deque, orig, 0, 0, 0);

        
        assertEquals("Test case should be null", null,
            deque.removeLast());
    }

    //Testing removeLast with 0 elements
    @Test 
    public void testRemoveLastZero(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] orig = {};
        initDeque(deque, orig, 0, 0, 0);

        assertEquals("Test case should be null", null,
            deque.removeLast());
    }

    //Testing peekFirst with 0 elements
    @Test 
    public void testPeekFirst(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] orig = {};
        initDeque(deque, orig, 0, 0, 0);

        assertEquals("Test case should be null", null,
            deque.peekFirst());
    }

    //Testing peekLast with 0 elements
    @Test 
    public void testPeekLast(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        Integer[] orig = {};
        initDeque(deque, orig, 0, 0, 0);

        assertEquals("Test case should be null", null,
            deque.peekLast());
    }

    // ------------ Stack ---------------

    /** Test push on wrapped stack */
    @Test
    public void testStack() {
        MyStack<Integer> stack = new MyStack<>(3);
        Integer[] orig = {4, 5, 6};
        initDeque(stack.theStack, orig, 3, 0, 2);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals("Size should be 6", 6, stack.size());
        assertEquals("Front should be 3", Integer.valueOf(3), stack.peek());
        assertEquals("First element removed should be 3", 
            Integer.valueOf(3), stack.pop());
        assertEquals("Second element should be 2", 
            Integer.valueOf(2), stack.pop());
        assertEquals("Third should be 1", 
            Integer.valueOf(1), stack.pop());
        
        assertEquals("Size should be 3", 3, stack.size());
        
        assertFalse("Should not be empty", stack.empty());
        
    }

    // ------------ Queue ---------------

    /** Test enqueue on wrapped queue */
    @Test
    public void testQueue() {
        MyQueue<Integer> queue = new MyQueue<>(6);
        Integer[] orig = {null, null, null, 4, 5, 6};
        initDeque(queue.theQueue, orig, 3, 3, 5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        
        assertEquals("Size should be 6", 6, queue.size());
        
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        assertEquals("Size should be 6", 3, queue.size());

        assertEquals("Next element removed should be 1", 
            Integer.valueOf(1), queue.dequeue());
        assertEquals("Next element should be 2", 
            Integer.valueOf(2), queue.dequeue());
        assertEquals("Next should be 3", 
            Integer.valueOf(3), queue.dequeue());
        
        assertTrue("Should be empty", queue.empty());
        assertEquals("Should be null for peek", null, queue.peek());
    }

    // ------------ Algorithm ---------------

    //Complex bracket test
    @Test
    public void complexBrackets() {
        String input = "([]){[[({}[]())]]}";
        assertTrue("This is a valid string of brackets", 
            MyAlgorithm.isValidBrackets(input));
        input = "([]){[[({[})]]}";
        assertFalse("This is not a valid string of brackets", 
            MyAlgorithm.isValidBrackets(input));
    }
}