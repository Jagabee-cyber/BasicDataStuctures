
/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the MyArrayList java file that implements all the functions
   located in MyList. It sets the constructors, Array list functions, and is 
   what the PublicTester and HiddenTester run tests on. 
 */


/**
 * This class stores an array of objects, allowing a user to perform
 * a variety of functions on it. These functions include adding, removing,
 * returning size values, and many more functions.
 * 
 * Instance variables: 
 * data - The array of data itself
 * size - The number that represents the current size of the array list
 */
public class MyArrayList<E> implements MyList<E>{

    /** Constants (Magic Numbers) */
    //Default Capacity number
    private static final int DEFAULT_CAPACITY = 5;
    private static final int INVALID_INPUT = -1;


    /** Instance variables */
    Object[] data;
    int size;

    /**
     * This is the default constructor for MyArrayList. 
     * 
     * It initializes the instance variable data to an empty Object 
     * array of capacity 5. It also initializes the instance variable
     * size to 0.
     * 
     */
    public MyArrayList(){
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * The constructor initializes the data array and the size variable
     * 
     * @param initialCapacity Value to set the capacity of the instance 
     * variable data to. Instance variable is set to 0.
     */
    public MyArrayList(int initialCapacity){

        if(initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * The constructor initializes the data array and the size variable. 
     * It first checks if arr is null before committing to copying arr to 
     * data and setting the size for the length of arr.
     * 
     * @param arr Value to set the instance variable data to. The length of
     * arr is what instance variable size is set to as well.
     */
    public MyArrayList(E[] arr){
        if (arr == null){
            this.data = new Object[DEFAULT_CAPACITY];
            this.size = 0;
        }
        else {
            this.data = new Object[arr.length];
            for (int i=0; i<arr.length; ++i){
                this.data[i] = arr[i];
            }
            this.size = arr.length;
        }
    }

    /**
     * This method overrides MyList and returns the current capacity 
     *
     * @return length of instance variable data
     */
    @Override
    public int getCapacity(){
        return this.data.length;
    }

    /**
     * This method overrides MyList and expands the current capacity
     * to accomodate the required capacity.
     * 
     * The method first throws any illegal argument exceptions. It then 
     * proceeds to find the suitable size it should set by comparing
     * between the requiredCapacity and double of the current.
     * (Exception for capacities of 0 which are reset to default) 
     * 
     * The method then implements two deep copies to allow for construction
     * of an array with the new capacity and transferring the contents 
     * back from a temp.
     * 
     * @param requiredCapacity is the required capacity that is needed for the 
     * array
     */
    @Override
    public void expandCapacity(int requiredCapacity){
        int currentCapacity = getCapacity();

        /**
         * Throwing an exception if requiredCapacity is stricly smaller 
         * than currentCapacity
         */
        if (requiredCapacity < currentCapacity){
            throw new IllegalArgumentException();
        }


        /**
         * Finding the proposed size the new array should have. If the current
         * capacity is 0 then by default it is reset to 5, otherwise, it is
         * the max between the requiredCapacirty and double of the
         * currentCapcity
         */
        int newSize;

        if (currentCapacity == 0){
            newSize = Math.max(DEFAULT_CAPACITY, requiredCapacity);
        }

        else {
            newSize = Math.max(requiredCapacity, currentCapacity+currentCapacity);
        }


        /**
         * Copying of the original data into a temporary array temp before 
         * creating a new array for data with the new capacity and
         * copying the contents of temp back into data
         */
        Object[] temp = new Object[currentCapacity];

        for (int i=0; i<currentCapacity; ++i){
            temp[i] = this.data[i];
        }

        this.data = new Object[newSize];

        for (int i=0; i<currentCapacity; ++i){
            this.data[i] = temp[i];
        }
    }

    /**
     * This method overrides MyList and inserts an element into the array
     * at the requested index.
     * 
     * The method first throws any index our of bounds exceptions.
     * 
     * It then checks to see if an expansion of the capacity is required,
     * in case it is, the expandCapacity() method is called.
     * 
     * The method then first checks if the array is originally empty, if 
     * it is then it simply just adds the element as the first element. 
     * Else, the method shifts all elements downstream of the index up by 1
     * before inserting the element into the desired index.
     * 
     * Each succesful insertation will increment the instance variable size 
     * up by 1. 
     * 
     * @param index is the index that the element will be inserted to
     * @param element is the element that will be inserted
     */
    @Override
    public void insert(int index, E element){

        /**
         * Throws an exception if the index is not valid
         */
        if (index < 0 || index > this.size){
            throw new IndexOutOfBoundsException();
        }

        /**
         * Checks and activates capacity expansion if needed
         */
        if (getCapacity() == this.size){
            expandCapacity(this.size + 1);
        }

        /**
         * Resolves empty arrays first, before proceeding
         * with the shifting of elements and then the insertation
         * at the exact index
         */
        if (this.size == 0 && index == 0){
            this.data[0] = element;
            this.size ++;
        }
        else {
            for (int i=this.size; i>index; --i){
                this.data[i] = this.data[i-1];
            }
            this.data[index] = element;
            this.size ++;
        }
    }


    /**
     * This method overrides MyList and appends an element to the back
     * of the array.
     * 
     * If the element is valid, it runs a check on the capacity, running
     * the expansion method if it is not sufficient
     * 
     * Finally, the element is put at the back of the array and 
     * instance variable size is incremented up by 1
     * 
     * @param element is the element that will be appended
     */
    @Override
    public void append(E element){

        //Expansion check
        if (this.size == getCapacity()){
            expandCapacity(this.size+1);
        }

        //Appending the element at the back
        this.data[this.size] = element;
        this.size ++;

    }

    /**
     * This method overrides MyList and appends an element to the back
     * of the array.
     * 
     * If the element is valid, it runs a check on the capacity, running
     * the expansion method if it is not sufficient
     * 
     * Finally, the element is put at the back of the array and 
     * instance variable size is incremented up by 1
     * 
     * @param element is the element that will be prepended
     */
    @Override
    public void prepend(E element){

        //Expansion check
        if (this.size == getCapacity()){
            expandCapacity(this.size+1);
        }

        //Appending the element at the front
        for(int i = this.size; i>0; --i){
            this.data[i] = this.data[i-1];
        }
        this.data[0] = element;
        this.size ++;
    }

    /**
     * This method overrides MyList and returns an element
     * from the array given an index.
     * 
     * The method first takes care of any index out of bounds
     * exceptions and throws them
     * 
     * It then simply returns the object type casted to E generic.
     * 
     * @param index is the index for which the element will be drawn.
     */
    @Override
    public E get(int index){

        //Exception throwing
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }

        //Returning of type casted element
        return (E) this.data[index];
    }

    /**
     * This method overrides MyList and replaces an element in the array
     * given a specific index, the replaced index is returned.
     * 
     * First, the method takes care of any index out of bounds exceptions
     * and throws them.
     * 
     * It then saves the old value before replacing the index with the new
     * element and returning the old value as the variable result.
     * 
     * @param index is the index where the replacement will occur
     * @param element is the element that will replace the old content
     */
    @Override
    public E set(int index, E element){

        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }

        E result = (E) this.data[index];

        this.data[index] = element;

        return result;
    }

    /**
     * This method overrides MyList and removes an element in the array,
     * returning that element and shifting all other elements after it
     * down by 1.
     * 
     * The method first throws any index out of bounds exceptions for
     * the index variable.
     * 
     * It then saves the target element in a variable result, before 
     * proceeding to to shift all elements past the index down by 1
     * and setting the last item as null.
     * 
     * The method then returns the saved element and increments the
     * instance variable size down by 1.
     * 
     * @param index is the index which the element will be removed from
     */
    @Override
    public E remove(int index){
        
        //Throws the exception
        if(index < 0 || index >= this.size){
            throw new IndexOutOfBoundsException();
        }

        //Saves the element
        E result = (E) this.data[index];
        
        //Only if the index is not the last element 
        if(index != this.size - 1){
            //Shifts the elements down and sets the last as null
            for(int i=index; i < this.size-1; ++i){
                this.data[i] = this.data[i+1];
            }
        }

        //Sets the last element as null
        this.data[this.size-1] = null;

        //Decreases size by 1
        this.size --;

        //Returns the saved result
        return result;
    }

    /**
     * A simple method that returns the instance variable
     * size.
     */
    @Override
    public int size(){
        return this.size;
    }

    /**
     * This method overrides MyList and shifts all elements in the list
     * to the right as many times as numPositions asks.
     * 
     * The method first throws any index out of bounds exceptions.
     * 
     * It then saves a variable lastIndex which records the last index in the
     * array that may house an element.
     * 
     * The variable temp is declared and will be used to house the last element
     * of the array as it is shifted. The array is then shifted as many times
     * as numPositions asks of it, always placing the last element at the front
     * at the end of each iteration
     * 
     * @param numPositions is the number of rotations or shifts that will
     * be done
     */
    @Override
    public void rotate(int numPositions){

        //Throws the exception
        if(numPositions < 0 || numPositions >= this.size){
            throw new IndexOutOfBoundsException();
        }
        
        //Setting of the last index of the array 
        int lastIndex = this.size-1;

        //Setting of variable
        E temp;

        //Shifting of elements 
        for(int i=0; i < numPositions; ++i){
            temp = (E)this.data[lastIndex]; 
            for(int j=lastIndex; j > 0; --j){
                this.data[j] = this.data[j-1];
            }
            this.data[0] = temp;
        }
    }
    
    /**
     * This method overrides MyList and finds an element's location
     * in the array, returning the index value.
     * 
     * The method first sets the result to invalid input which is -1.
     * 
     * The method then searches throughout the whole array, if
     * any matches occur it will automatically save the index to the
     * result.
     * 
     * Finally the result is returned.
     */
    @Override
    public int find(E element){

        //Setting of the default result if no matches
        int result = INVALID_INPUT;

        //Searching the array and matching the indexes
        for(int i=0; i<this.size; ++i){
            if ((this.data[i]).equals(element)){
                result = i;
            }
        }

        //Returning the result
        return result;
    }
}