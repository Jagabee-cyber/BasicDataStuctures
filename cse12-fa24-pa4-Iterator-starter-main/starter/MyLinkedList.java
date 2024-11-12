/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the MyLinkedList java file that implements all the functions
   located in MyList. It sets the constructors, Array list functions, and is
   what the PublicTester and CustomTester run tests on.
 */

//Import statement
import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This class stores nodes in a linked list, allowing a user to perform
 * a variety of functions on it. These functions include adding, removing,
 * returning size values, and many more functions.
 * 
 * Instance variables: 
 * size - The number of non-sentinel nodes in the linked list
 * head - The head of the linked list which points to the head sentinel node
 * tail - The tail of the linked list which points to the tail sentinel node
 */
public class MyLinkedList<E> extends AbstractList<E> {

    //Constants, magic numbers
    private static final int NOT_FOUND = -1;
    //Instance variables
    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //  Implementation of the MyLinkedList Class

    /**
     * Default constructor for creating an empty linked list
     */
    public MyLinkedList() {
        this.head = new Node(null);
        this.tail = new Node(null);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = 0;
    }

    /**
     * Method used to get the size of the linked list not including
     * sentinel nodes
     * @return the size of the linked list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Method used to get the contents of the node at the specified index
     * @param index is the index where the node is located
     * @return the node's data
     */
    @Override
    public E get(int index) {

        if(index<0 || index>=this.size){
            throw new IndexOutOfBoundsException();
        }

        Node curr = this.head.next;
        for(int i=0; i < index; ++i){
            curr = curr.next;
        }
        return (E)curr.getElement();  
    }

    /**
     * Method used to insert a new node in the list based on index
     * @param index is the index where the node will be inserted
     * @param data is the contents of the node to be inserted
     */
    @Override
    public void add(int index, E data) {

        //Exception throwing
        if(data==null){
            throw new NullPointerException();
        }

        if(index<0 || index>this.size){
            throw new IndexOutOfBoundsException();
        }

        Node curr = this.head;
        for (int i=0; i<index; ++i){
            curr = curr.next;
        }

        //Cycle to index and reassign connections
        Node addition = new Node(data);
        curr.next.prev = addition;
        addition.next = curr.next;
        curr.next = addition;
        addition.prev = curr;

        this.size ++;
    }

    /**
     * Method used for adding a new node at the end of the list
     * @param data is the data within the new node
     * @return true at all times at it is a boolean method
     */
    @Override
    public boolean add(E data) {
        
        //Exception throwing
        if(data == null){
            throw new NullPointerException();
        }

        //Reassigning connections
        Node addition = new Node(data);
        this.tail.prev.next = addition;
        addition.prev = this.tail.prev;
        addition.next = this.tail;
        this.tail.prev = addition;

        this.size ++;
        return true; 
    }

    /**
     * Method used for setting the data of a node to an input and returning
     * the old data
     * @param index is the index of the node
     * @param data is the new data that will replace the old
     * @return the result which is the old data that was in the node
     */
    @Override
    public E set(int index, E data) {
        
        //Exception throwing
        if(data==null){
            throw new NullPointerException();
        }

        if(index<0 || index>=this.size){
            throw new IndexOutOfBoundsException();
        }

        //Replacment of the data
        Node curr = this.head.next;
        for (int i=0; i<index; i++){
            curr = curr.next;
        }
        E result = curr.data;
        curr.setElement(data); 

        return (E) result;
    }

    /**
     * Method used for removing a node from the linked list
     * @param index is the index of the node
     * @return the data from the removed node
     */
    @Override
    public E remove(int index) {
        
        //Exception throwing
        if(index<0 || index>=this.size){
            throw new IndexOutOfBoundsException();
        }

        //Node removal
        Node curr = this.head.next;
        for (int i=0; i<index; i++){
            curr = curr.next;
        }
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;

        this.size--;
        return (E) curr.data; 
    }

    /**
     * Method used for clearing all non-sentinel nodes
     */
    @Override
    public void clear() {
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.size = 0;
    }

    /**
     * Method used to determine if the list is empty
     */
    @Override
    public boolean isEmpty() {
        return !(this.size>0);
    }

    /**
     * Method that returns the node at a given index
     * @param index is the index of the node
     * @return the node that is retreived
     */
    protected Node getNth(int index) {
        
        //Exception throwing
        if(index<0 || index>=this.size){
            throw new IndexOutOfBoundsException();
        }

        //Returning the node
        Node curr = this.head.next;
        for(int i=0; i<index; i++){
            curr = curr.next;
        }
        
        return (Node) curr;  
    }

    /**
     * Method for checking if the list contains a node holding a specific data
     * @param data is the data that we will be checking for
     * @param start is the starting index for search, inclusive
     * @param end is the ending index for search, exclusive
     * @return whether or not the linked list contains a node with the data
     */
    public boolean contains(E data, int start, int end) {
        
        //Exception throwing
        if(start<0 || start>=this.size || end<0 || end>this.size){
            throw new IndexOutOfBoundsException();
        }
        if(data==null){
            return false;
        }

        if(end<=start){
            return false;
        }

        //Cycle to the right frame, and then comparrison checks
        Node curr = this.head.next;
        for(int i = 0; i<start; i++){
            curr = curr.next;
        }
        for(int i=start; i<end;i++){
            if(curr.data.equals(data)){
                return true;
            }
            curr = curr.next;
        }


        return false; 
    }
    
    /**
     * Method for finding the index of a node that holds given data
     * @param data is the data that will be used for comparrison
     * @return the index of the node found, -1 if it doesn't exist
     */
    public int indexOfElement(E data) {
        
        //Exception throwing
        if(data==null){
            throw new NullPointerException();
        }

        //Cycle through elem till found
        Node curr = this.head;
        for(int i=0; i<this.size; i++){
            curr = curr.next;
            if(curr.data.equals(data)){
                return i;
            }
        }

        return NOT_FOUND; 
    }

    /**
     * Added methods as specified by the README
     * @return A new MyListIterator object
     */
    @Override
    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }
    @Override
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    /**
     * An iterator class that will be used to traverse
     */
    protected class MyListIterator implements ListIterator<E> {

        //Static constants (magic numbers)
        private static final int START_INDEX = -1;
        // Instance Variables
        Node left;
        Node right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;


        // MyListIterator methods

        /**
         * MyListIterator constructor, initializes the iterator
         */
        public MyListIterator(){
            this.left = head;
            this.right = head.next;
            this.idx = 0;
            this.forward = false;
            this.canRemoveOrSet = false;
        }

        /**
         * Method for determining whether or not a node with an element exists
         * in the next movement
         * @return whether there is an elemental node
         */
        @Override
        public boolean hasNext() {
            return(idx < size);
        }
        
        /**
         * Method that shifts the iterator to the right
         * @return the element that is located in the node in left
         */
        @Override
        public E next() {
            //Exception throwing
            if(hasNext() == false){
                throw new NoSuchElementException();
            }
            this.left = left.next;
            this.right = this.left.next;
            this.idx ++;
            this.forward = true;
            this.canRemoveOrSet = true;

            return this.left.getElement();
        }

        /**
         * Method for determining if an elemental node exists in 
         * a prev movement
         * @return whether there is an elemental node
         */
        @Override
        public boolean hasPrevious(){
            return(idx != 0);
        }

        /**
         * Method that shifts the iterator to the left
         * @return the element that is located in the node in right
         */
        @Override
        public E previous() {
            //Exception handling
            if(hasPrevious() == false){
                throw new NoSuchElementException();
            }
            this.left = left.prev;
            this.right = this.left.next;
            this.idx --;
            this.forward = false;
            this.canRemoveOrSet = true;

            return this.right.getElement();
        }

        /**
         * Method for returning the index of the element that would've been 
         * called by next()
         * @return the index integer
         */
        @Override
        public int nextIndex(){
            if(this.idx == size){
                return size;
            }
            return this.idx ++;
        }

        /**
         * Method for returning the index of the element that would've been 
         * called by previous()
         * @return the index integer
         */
        @Override
        public int previousIndex(){
            if(this.idx == 0){
                return START_INDEX;
            }
            return this.idx -= 1;
        }

        /**
         * Method for adding an element right before the node that is pointed
         * to by this.right
         * @param element the element of the node that will be added
         */
        @Override
        public void add(E element){
            //Exception handling
            if(element == null){
                throw new NullPointerException();
            }

            Node added = new Node(element);
            added.prev = this.left;
            this.left.next = added;
            this.left = this.left.next;
            this.right.prev = this.left;
            this.left.next = this.right;

            this.idx ++;
            this.canRemoveOrSet = false;
        }

        /**
         * Method for setting the element in the last node called by either
         * next or previous as a new element
         * @param element is the new element that will replace the old 
         * element
         */
        @Override
        public void set(E element){

            //Exception handling
            if(element == null){
                throw new NullPointerException();
            }
            if(this.canRemoveOrSet == false){
                throw new IllegalStateException();
            }

            //Determining if the last call was next or prev
            if(this.forward == true){
                this.left.setElement(element);
            }
            else{
                this.right.setElement(element);
            }
        }

        /**
         * Method for removing an element using the iterator
         * Will remove element in left if next last called
         * and right if previous last called
         */
        @Override
        public void remove(){
            //Exception handling
            if(this.canRemoveOrSet == false){
                throw new IllegalStateException();
            }

            //Determining if the last call was next or prev
            if(this.forward == true){
                this.left = this.left.prev;
                this.left.next = this.right;
                this.right.prev = this.left;
                this.idx -=1;
            }
            else{
                this.right = this.right.next;
                this.right.prev = this.left;
                this.left.next = this.right;
            }

            
            this.canRemoveOrSet = false;
        }
    }
}
