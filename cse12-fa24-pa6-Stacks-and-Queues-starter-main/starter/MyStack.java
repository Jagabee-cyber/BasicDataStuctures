/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the MyStack java file that sets the constructors, 
   push and pop functions, and is what the PublicTester and CustomTester 
   run tests on.
 */

/**
 * This class implements the Stack ADT using a MyDeque instance variable called
 * theStack.
 * 
 * Instance Variables
 * theStack is the stack
 */
public class MyStack<E> implements StackInterface<E> {
    //Instance variables
    MyDeque<E> theStack;

    /**
     * Constructor to create new MyStack that holds a MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyStack(int initialCapacity) {
        this.theStack = new MyDeque(initialCapacity);
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if there are no elements in the stack, false otherwise.
     */
    @Override
    public boolean empty() {
        return size()==0;
    }

    /**
     * Adds the specified element to the top of this StackInterface.
     *
     * @param element the element to add to the stack
     */
    @Override
    public void push(E element) {
        this.theStack.addFirst(element);
    }

    /**
     * Removes the element at the top of this StackInterface.
     * Returns the element removed, or null if there was no such element.
     *
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E pop() {
        return this.theStack.removeFirst();
    }

    /**
     * Returns the element at the top of this stack, or null if there was no
     * such element.
     *
     * @return the element at the top, or null if the size was zero
     */
    @Override
    public E peek() {
        return this.theStack.peekFirst();
    }

    /**
     * Returns the number of elements in this stack.
     *
     * @return the number of elements in this stack.
     */
    @Override
    public int size() {
        return theStack.size();
    }

}
