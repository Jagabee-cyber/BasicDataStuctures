
/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the MyDeque java file that sets the constructors, 
   deque functions, and is what the PublicTester and CustomTester 
   run tests on.
 */

/**
 * This class stores values in a deque, allowing a user to perform
 * a variety of functions on it including adding values to the ends,
 * removing values from the ends, and peeking at the ends
 * 
 * Instance Variables
 * data ~ The data that is stored in the deque
 * size ~ Number of elements in the deque
 * rear ~ The index of the last element
 * front ~ The index of the first element
 */
public class MyDeque<E> implements DequeInterface<E>{
    //Constants
    private static final int EXPAND_CAPACITY_RATIO = 2;

    //Instance Variables
    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * Constructor for MyDeque that initializes Object array
     * with length of initial capacity
     * @param initialCapacity is the initial capacity 
     */
    public MyDeque (int initialCapacity){
        if (initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
        this.rear = 0;
        this.front = 0;
    }

    /**
     * Method for returning the size of the deque
     * @return the size of the deque
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * Method for expanding the capacity of the deque
     */
    @Override
    public void expandCapacity(){
        if (this.data.length == 0){
            this.data = new Object[10]; 
        }
        else{
            Object[] temp = new Object[this.data.length*EXPAND_CAPACITY_RATIO];
            for(int i=0; i<this.size; i++){
                if(this.front+i < this.data.length){
                    temp[i] = this.data[this.front+i];
                }
                else{
                    temp[i] = this.data[this.front+i-this.data.length];
                }
            }
            this.front = 0;
            this.rear = this.size-1;
            this.data = temp;
        }
    }

    /**
     * Method for adding an element to the front
     * @param element is the element to be added
     */
    @Override
    public void addFirst(E element){
        if(element == null){
            throw new NullPointerException();
        }

        if(this.size == this.data.length){
            expandCapacity();
        }

        if(this.size == 0){
            this.data[this.front] = element;
            this.size ++;
        }
        else{
            this.front--;
            if(this.front<0){
                this.front = this.data.length - 1;
            }

            this.data[this.front] = element;
            this.size ++;
        }
        
    }

    /**
     * Method for adding an element to the back
     * @param element is the element to be added
     */
    @Override
    public void addLast(E element){
        if(element == null){
            throw new NullPointerException();
        }

        if(this.size == this.data.length){
            expandCapacity();
        }
    
        if(this.rear == this.data.length-1 && this.size!=0){
            this.rear = 0;
        }
        else{
            if(this.size!=0){
                this.rear++;
            }
        }

        this.size++;
        this.data[this.rear] = element;
    }

    /**
     * Method for removing the first element
     * @return the original first element
     */
    @Override
    public E removeFirst(){
        if(this.size == 0){
            return null;
        }
        E result = (E)this.data[this.front];

        this.data[this.front] = null;
        if(this.size!=1){
            this.front ++;
            if(this.front > this.data.length-1){
                this.front = 0;
            }
        }

        this.size --;
        return result;
    }

    /**
     * Method for removing the last element
     * @return the original last element
     */
    @Override
    public E removeLast(){
        if(this.size == 0){
            return null;
        }
        E result = (E)this.data[this.rear];

        this.data[this.rear] = null;
        if(this.size!=1){
            this.rear --;
            if(this.rear < 0){
                this.rear = this.data.length-1;
            }
        }

        this.size --;
        return result;
    }

    /**
     * Method for peeking at the first element
     * @return the first element
     */
    @Override
    public E peekFirst(){
        if(this.size==0){
            return null;
        }
        
        return (E)this.data[this.front];
        
    }

    /**
     * Method for peeking at the last element
     * @return the last element
     */
    @Override
    public E peekLast(){
        if(this.size==0){
            return null;
        }
        
        return (E)this.data[this.rear];
        
    }
}
