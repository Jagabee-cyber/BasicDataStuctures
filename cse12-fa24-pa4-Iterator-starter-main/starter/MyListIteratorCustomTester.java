import java.util.NoSuchElementException;
import static org.junit.Assert.*;
import org.junit.*;

public class MyListIteratorCustomTester {

    //Instance Variables
    private MyLinkedList listLen3;
    private MyLinkedList.MyListIterator listLen3Iter;
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() throws Exception {
        listLen3 = new MyLinkedList();
        listLen3.add("Spongebob");
        listLen3.add("Patrick");
        listLen3.add("Squidward");
        listLen3Iter = listLen3.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        boolean testcase = false;

        listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
        listLen3Iter.right = 
            listLen3.head.getNext().getNext().getNext().getNext();
        listLen3Iter.idx = 3;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.forward = true;
        assertFalse("call hasNext when there isn't a next node",
                listLen3Iter.hasNext());
        
        try {
            listLen3Iter.next();
        } catch (NoSuchElementException e) {
            testcase = true;
        }
        assertTrue("call should throw an exception", 
            testcase);
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        boolean testcase = false;

        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = 
            listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.forward = false;

        try {
            listLen3Iter.previous();
        } catch (NoSuchElementException e) {
            testcase = true;
        }
        assertTrue("call should throw an exception", 
            testcase);
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {
        boolean testcase = false;

        try {
            listLen3Iter.add(null);
        } catch (NullPointerException e) {
            testcase = true;
        }
        assertTrue("call should throw a null pointer exception",
            testcase);
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {
        boolean testcase = false;

        listLen3Iter.left = listLen3.head.getNext();
        listLen3Iter.right = 
            listLen3.head.getNext().getNext();
        listLen3Iter.idx = 1;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.forward = false;
        listLen3Iter.remove();
         
        try {
            listLen3Iter.set("Krabs");
        } catch (IllegalStateException e) {
            testcase = true;
        }
        assertTrue("call should throw illegal state exception",
            testcase);

    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {
        boolean testcase = false;

        listLen3Iter.left = listLen3.head.getNext();
        listLen3Iter.right = 
            listLen3.head.getNext().getNext();
        listLen3Iter.idx = 1;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.forward = false;
        listLen3Iter.remove();
         
        try {
            listLen3Iter.remove();
        } catch (IllegalStateException e) {
            testcase = true;
        }
        assertTrue("call should throw illegal state exception",
            testcase);
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
        listLen3Iter.right = 
            listLen3.head.getNext().getNext().getNext().getNext();
        listLen3Iter.idx = 3;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.forward = true;
        assertFalse("call should return false for no node",
                listLen3Iter.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = 
            listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.forward = false;
        assertFalse("call should return false for no node",
                listLen3Iter.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        listLen3Iter.left = listLen3.head;
        listLen3Iter.right = 
            listLen3.head.getNext();
        listLen3Iter.idx = 0;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.forward = false;
        assertEquals("Should be -1 for start of the list",-1, 
            listLen3Iter.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen3Iter.left = listLen3.head.getNext().getNext().getNext();
        listLen3Iter.right = 
            listLen3.head.getNext().getNext().getNext().getNext();
        listLen3Iter.idx = 3;
        listLen3Iter.canRemoveOrSet = true;
        listLen3Iter.forward = true;
        assertEquals("Should be list size if end of the list",
            listLen3.size, listLen3Iter.nextIndex());
    }
}
