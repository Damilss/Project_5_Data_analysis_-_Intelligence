/**
 * Custom exception for Assignment! Throwing exception if the list is empty.
 */
public class NoResultException extends RuntimeException {
    /**
     * Method overloarding to cover both instances of constructor. One with constuctor with arguments, 
     * and One constructor without arguments 
     * */
    public NoResultException(){
        super("NoResultException. No result!");
    }

    public NoResultException(String errMessage){
        super(errMessage);
    }
}
