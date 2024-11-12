/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the MyLinkedListCustomeTester java file that implements 
   all the functions located in MyList. It sets the constructors, 
   Array list functions, and is What the PublicTester and 
   CustomTester run tests on.
 */

//Import Statements
import static org.junit.Assert.*;
import org.junit.*;

/**
 * This class holds all the tests that will be run against MyLinkedList.java
 * Testing each and every one of the methods provided.
 */
public class MyLinkedListCustomTester {

	// Optional: add test variables here
	private MyLinkedList<String> threeStringList;
	private MyLinkedList<Integer> emptyList;
	private String[] strData = {"Batman", "Superman", 
		"Aquaman", "Green Lantern"};

	/**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
	@Before
	public void setUp() throws Exception {
		threeStringList = new MyLinkedList<>();
		emptyList = new MyLinkedList<Integer>();
	}

	/**
     * Setting up the custom linked list that will be used
     */
    private void populateLinkedList() {
        MyLinkedList<String>.Node node0 =  
            this.threeStringList.new Node(this.strData[0]);
        MyLinkedList<String>.Node node1 =  
            this.threeStringList.new Node(this.strData[1]);
        MyLinkedList<String>.Node node2 =  
            this.threeStringList.new Node(this.strData[2]);

        this.threeStringList.head.next = node0;
        node0.prev = this.threeStringList.head;
        node0.next = node1;
        node1.prev = node0;
        node1.next = node2;
        node2.prev = node1;
        node2.next = this.threeStringList.tail;
        this.threeStringList.tail.prev = node2;
        this.threeStringList.size = 3;
    }

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		//Setting variables and populating the list
		boolean exception1 = false;
		this.populateLinkedList();
		MyLinkedList<String>.Node oldLastNode = this.threeStringList.tail.prev;

		//Exception testing for null
		try{
			threeStringList.add(null);
		}
		catch(NullPointerException e){
			exception1 = true;
		}
		assertEquals("Check input validity", true, exception1);

		//Normal operations testing
		assertEquals("Should always be true", true,
			threeStringList.add(this.strData[3]));
		assertEquals("Tail node should point back to the new node", 
            "Green Lantern", this.threeStringList.tail.prev.data);
        assertEquals("Previous last node should point to the new added node",
            "Green Lantern", oldLastNode.next.data);
        assertEquals("Check size is updated", 4, this.threeStringList.size);
        assertSame("Added node previous should be previous last node",
            oldLastNode, this.threeStringList.tail.prev.prev);
        assertSame("New added node next should point to tail",
            oldLastNode.next.next, this.threeStringList.tail);
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		//Setting variables and populating the list
		boolean exception1 = false;
		boolean exception2 = false;
		boolean exception3 = false;
		this.populateLinkedList();
		MyLinkedList<String>.Node oldFirstNode = 
			this.threeStringList.head.next;

		//Exception testing for null
		try{
			threeStringList.add(0, null);
		}
		catch(NullPointerException e){
			exception1 = true;
		}
		assertEquals("Check input validity", true, exception1);

		//Exception testing for indexoutofbounds
		try {
			threeStringList.add(-3, this.strData[3]);
		} catch (IndexOutOfBoundsException e) {
			exception2 = true;
		}
		assertEquals("Check input validity", true, exception2);
		try {
			threeStringList.add(4, this.strData[3]);
		} catch (IndexOutOfBoundsException e) {
			exception3 = true;
		}
		assertEquals("Check input validity", true, exception3);

		//Normal operations testing
		threeStringList.add(0, this.strData[3]);
		assertEquals("Head node should point to the new node", 
            "Green Lantern", this.threeStringList.head.next.data);
        assertEquals("Previous first node should point back", 
			"Green Lantern", oldFirstNode.prev.data);
        assertEquals("Check size is updated", 4, this.threeStringList.size);
        assertSame("Added node next should be 2 away from head node",
            oldFirstNode, this.threeStringList.head.next.next);
        assertSame("New added node prev should point to head",
            this.threeStringList.head.next.prev, this.threeStringList.head);
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		//Setting variables and populating the list
		boolean exception1 = false;
		boolean exception2 = false;
		boolean exception3 = false;
		this.populateLinkedList();
		MyLinkedList<String>.Node oldSecondNode = 
			this.threeStringList.head.next.next;
		MyLinkedList<String>.Node firstNode = 
			this.threeStringList.head.next;

		//Exception testing for null
		try{
			threeStringList.add(0, null);
		}
		catch(NullPointerException e){
			exception1 = true;
		}
		assertEquals("Check input validity", true, exception1);

		//Exception testing for indexoutofbounds
		try {
			threeStringList.add(-3, this.strData[3]);
		} catch (IndexOutOfBoundsException e) {
			exception2 = true;
		}
		assertEquals("Check input validity", true, exception2);
		try {
			threeStringList.add(4, this.strData[3]);
		} catch (IndexOutOfBoundsException e) {
			exception3 = true;
		}
		assertEquals("Check input validity", true, exception3);

		//Normal operations testing
		threeStringList.add(1, this.strData[3]);
		assertEquals("First node should point to the new node", 
            "Green Lantern", firstNode.next.data);
        assertEquals("Previous second node should point back", 
			"Green Lantern", oldSecondNode.prev.data);
        assertEquals("Check size is updated", 4, this.threeStringList.size);
        assertSame("Added node next should be old second node",
            oldSecondNode, this.threeStringList.head.next.next.next);
        assertSame("New added node prev should point to first node",
            this.threeStringList.head.next.next.prev, 
			firstNode);
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test
	public void testCustomRemoveFromEmpty() {
		//Setting of variables
		boolean exception1 = false;
		boolean exception2 = false;
		boolean exception3 = false;

		//Testing negative index
		try {
			emptyList.remove(-5);
		} catch (IndexOutOfBoundsException e) {
			exception1 = true;
		}
		assertEquals("Check input validity", true, exception1);

		//Testing way higher than size index
		try {
			emptyList.remove(6);
		} catch (IndexOutOfBoundsException e) {
			exception2 = true;
		}
		assertEquals("Check input validity", true, exception2);

		//Testing same as size index
		try {
			emptyList.remove(0);
		} catch (IndexOutOfBoundsException e) {
			exception3 = true;
		}
		assertEquals("Check input validity", true, exception3);
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		//Setting of variables
		boolean exception1 = false;
		boolean exception2 = false;
		boolean exception3 = false;
		this.populateLinkedList();
		MyLinkedList<String>.Node thirdNode = 
			this.threeStringList.head.next.next.next;
		MyLinkedList<String>.Node firstNode = 
			this.threeStringList.head.next;
		
		//Testing negative index
		try {
			threeStringList.remove(-5);
		} catch (IndexOutOfBoundsException e) {
			exception1 = true;
		}
		assertEquals("Check input validity", true, exception1);

		//Testing way higher than size index
		try {
			threeStringList.remove(6);
		} catch (IndexOutOfBoundsException e) {
			exception2 = true;
		}
		assertEquals("Check input validity", true, exception2);

		//Testing same as size index
		try {
			threeStringList.remove(3);
		} catch (IndexOutOfBoundsException e) {
			exception3 = true;
		}
		assertEquals("Check input validity", true, exception3);

		threeStringList.remove(1);
		assertEquals("First node should point to the prev third node", 
            "Aquaman", firstNode.next.data);
        assertEquals("Third node prev should be the first node", 
			"Batman", thirdNode.prev.data);
        assertEquals("Check size is updated", 2, this.threeStringList.size);
        assertSame("Head should point twice to the prev third node",
            thirdNode, this.threeStringList.head.next.next);
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test
	public void testCustomSetIdxOutOfBounds() {
		//Setting variables and populating the list
		boolean exception1 = false;
		boolean exception2 = false;
		boolean exception3 = false;
		boolean exception4 = false;
		this.populateLinkedList();
		MyLinkedList<String>.Node secondNode = 
			this.threeStringList.head.next.next;
		MyLinkedList<String>.Node firstNode = 
			this.threeStringList.head.next;

		//Exception testing for null
		try{
			threeStringList.set(0, null);
		}
		catch(NullPointerException e){
			exception1 = true;
		}
		assertEquals("Check input validity", true, exception1);

		//Exception testing for indexoutofbounds
		try {
			threeStringList.set(-3, this.strData[3]);
		} catch (IndexOutOfBoundsException e) {
			exception2 = true;
		}
		assertEquals("Check input validity", true, exception2);
		try {
			threeStringList.set(4, this.strData[3]);
		} catch (IndexOutOfBoundsException e) {
			exception3 = true;
		}
		assertEquals("Check input validity", true, exception3);
		try {
			threeStringList.set(3, this.strData[3]);
		} catch (IndexOutOfBoundsException e) {
			exception4 = true;
		}
		assertEquals("Check input validity", true, exception4);

		//Normal operations testing
		threeStringList.set(0, this.strData[3]);
		assertEquals("First node should have new data", 
            "Green Lantern", firstNode.data);
		assertEquals("First node should point to the second node", 
            "Superman", firstNode.next.data);
        assertEquals("Second node should point back", 
			"Green Lantern", secondNode.prev.data);
        assertEquals("Check size is unchanged", 3, this.threeStringList.size);
        assertSame("First node next should be second node",
            secondNode, this.threeStringList.head.next.next);
        assertSame("First node prev should point to head",
            this.threeStringList.head.next, 
			firstNode);
	}

	/**
	 * Aims to test the contains(E data, int start, int end) method.
	 * Data argument exists in the list but outside the given range. 
	 */
	@Test
	public void testCustomContainsExistsOutOfRange() {
		//Setting variables and populating the list
		boolean exception1 = false;
		boolean exception2 = false;
		boolean exception3 = false;
		boolean exception4 = false;
		boolean exception5 = false;
		this.populateLinkedList();

		//Testing negative start
		try {
			threeStringList.contains(this.strData[0], -5, 2);
		} catch (IndexOutOfBoundsException e) {
			exception1 = true;
		}
		assertEquals("Check input validity", true, exception1);

		//Testing start more than number of elem in list
		try {
			threeStringList.contains(this.strData[0], 4, 2);
		} catch (IndexOutOfBoundsException e) {
			exception2 = true;
		}
		assertEquals("Check input validity", true, exception2);

		//Testing start same as num of elem in list
		try {
			threeStringList.contains(this.strData[0], 3, 2);
		} catch (IndexOutOfBoundsException e) {
			exception3 = true;
		}
		assertEquals("Check input validity", true, exception3);

		//Testing negative end
		try {
			threeStringList.contains(this.strData[0], 2, -4);
		} catch (IndexOutOfBoundsException e) {
			exception4 = true;
		}
		assertEquals("Check input validity", true, exception4);
	
		//Testing end greater than num of elem
		try {
			threeStringList.contains(this.strData[0], 2, 4);
		} catch (IndexOutOfBoundsException e) {
			exception5 = true;
		}
		assertEquals("Check input validity", true, exception5);

		assertEquals("False result for end<start", false, 
			threeStringList.contains("Batman", 2, 1));

		assertEquals("False result for end=start", false,
			threeStringList.contains("Batman", 2, 2));

		assertEquals("Actual val not found", true,
			threeStringList.contains("Batman", 0, 3) );

		assertEquals("Null shouldn't be found", false,
			threeStringList.contains(null, 0, 3));
	}

	/**
	 * Aims to test the indexOfElement(E data) method.
	 * Data argument does not exist in the list.
	 */
	@Test
	public void testCustomIndexOfElementDoesNotExist() {
		//Setting variables and populating the list
		boolean exception1 = false;
		this.populateLinkedList();

		//Testing null data
		try {
			threeStringList.indexOfElement(null);
		} catch (NullPointerException e) {
			exception1 = true;
		}
		assertEquals("Check input validity", true, exception1);

		assertEquals("Data should not exist", -1,
			threeStringList.indexOfElement("Wonder Woman") );
		
		assertEquals("Data should exist", 1,
			threeStringList.indexOfElement("Superman") );
	}
}
