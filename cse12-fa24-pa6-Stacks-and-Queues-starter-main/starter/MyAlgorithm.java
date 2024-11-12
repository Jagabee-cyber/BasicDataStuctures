/*
   Name: Zachary Wang
   Email: zaw001@ucsd.edu
   PID: A17785519
   Sources used: Lecture Handout
  
   This file is the MyAlgorithm java file that sets the use
   of a stack in determining bracket parity.
 */

/**
 * This class contains an algorithm utilizing a stack or queue. 
 */
public class MyAlgorithm {
    /**
     * Returns whether or not the given string contains a 
     * valid arrangement of brackets
     * 
     * @param input the input string containing brackets
     * @throws NullPointerException if the given string is null
     * @return the whether or not the given string contains a 
     * valid arrangement of brackets
     */
    public static boolean isValidBrackets(String input) {
        MyStack stack = new MyStack(10);

        for(int i=0; i<input.length(); i++){

            char testChar = input.charAt(i);

            if(testChar == '(' || testChar == '[' || testChar == '{'){
                stack.push(testChar);
            }
            else{
                if(stack.empty()){
                    return false;
                }

                char b = (char)stack.peek();
                if(testChar==')' && b == '(' ||
                testChar==']' && b == '[' ||
                testChar == '}' && b == '{'){
                    stack.pop();
                }
            }
        }
        return stack.empty();
    }
}