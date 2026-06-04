/**
 * Custom exception for Assignment! Throwing exception if the list is empty.
 */
public class EmptyListException extends RuntimeException {
    /**
     * Method overloarding to cover both instances of constructor. One with constuctor with arguments, 
     * and One constructor without arguments 
     * */
    public EmptyListException(){
        super("EmptyListException, List is empty!");
    }
    
    public EmptyListException(String errMessage){
        super(errMessage);
    }
}
