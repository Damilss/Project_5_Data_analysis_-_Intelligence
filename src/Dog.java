import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Dog implements Comparable<Dog>, IDog{
    private String breed;
    private String countryOfOrigin;
    private String furColor;
    private String height;
    private String eyeColor;
    private String longevity;
    private String characterTraits;
    private String commonHealthProblems;

    /* The stream returns the breed summary for a given dog breed */
    public String getBreedSummary(List<IDog> idogs, String dogBreed) {
        if( idogs.isEmpty() ) { return "The list is empty \n"; }                            // Checks if the input list is empty

        Predicate<IDog> dogBreedChar = (IDog myDog) -> myDog.getBreed().equals(dogBreed);   // Predicate to check if the dog's breed is the same as the one provided
        Object[] myObj = idogs.stream()                                                     // Start of the stream processing
                .filter(dogBreedChar)                                                       // Filters the stream based on the predicate. The result will be whichever object matches the Predicate
                .toArray();                                                                 // Converts the resulting stream into an array of objects

        if( myObj.length == 0 ) { return "No dogs of the breed " + dogBreed + " found. \n"; }   // Checks if the resulting list is empty

        return myObj[0].toString();                                                         // Converts the resulting object into a string. Since the method searches for a single breed, the list will be empty, or will have only one object in it.
    }

    /* Write a stream to return the dog breeds for a given country of origin
     * Make sure to check for an empty list as input and as a result.
     * If there is an empty input list throw an EmptyListException
     * If there is an empty list as a result throw a NoResultException */
    public String getDogsFromCountry(List<IDog> idogs, String country) throws EmptyListException, NoResultException {

        //FILL THIS
        return "o";

    }

    /* Write a stream to return the dog breeds for a given fur color
     *  Make sure to check for an empty list as input and as a result.
     *  If there is an empty list return a message to the user that there is an empty list. */
    public String getDogsFurColor(List<IDog> idogs, String furColor) {

        //FILL THIS
        return "o";

    }

    /* The stream returns the average longevity for the dog breeds in the list */
    public float getAverageDogsLongevity(List<IDog> idogs) {
        if( idogs.isEmpty() ) { return -1.0f; }                     // Checks if the input list is empty

        Function<IDog, Integer> getLongevity = (IDog myDog) -> Integer.valueOf(myDog.getLongevity());   // Function to be used in the map operation.
        Integer sum = idogs.parallelStream()                    // Start of the stream processing in parallel
                    .map(getLongevity)                          // Maps the Function on every element in the list and returns a stream with the result
                    .reduce(0, (result, current) -> result + current );           // Applies the accumulator operation to all the elements in the stream.
                                                                                                        // The first number in the stream is added to 0, which is the identity value of the operation.
        return (float) sum / idogs.size();                      // Divides the sum of the longevity with the number of the dog breeds and returns the result.
    }

    /* Write a stream to return the average dog height for the dogs in the list
     *  Make sure to check for an empty list as input and as a result.
     *  If there is an empty list return a message to the user that there is an empty list. */
    public float getAverageDogsHeight(List<IDog> idogs) {

        //FILL THIS
        return 0;

    }

    /* Write a stream to print the eye color of a specific breed
     * Make sure to check for an empty list as input and as a result.
     * If there is an empty list print a message to the user that there is an empty list. */
    public void printBreedEyeColor(List<IDog> idogs, String dogBreed) {

        //FILL THIS

    }

    /* Write a stream to print the traits of a specific breed
     * Make sure to check for an empty list as input and as a result.
     * If there is an empty list print a message to the user that there is an empty list. */
    public void printBreedTraits(List<IDog> idogs, String dogBreed) {

        //FILL THIS

    }

    /* Write a stream to print the health problems of a specific breed
     * Make sure to check for an empty list as input and as a result.
     * If there is an empty list print a message to the user that there is an empty list. */
    public void printBreedHealthProblems(List<IDog> idogs, String dogBreed) {

        //FILL THIS

    }

    Dog( String breed, String countryOfOrigin, String furColor, String height, String eyeColor, String longevity, String characterTraits, String commonHealthProblems ){

        //FILL THIS

    }

    @Override
    public int compareTo(Dog o) {

        //FILL THIS
        return 0;

    }

    @Override
    public String toString() {

        //FILL THIS
        return "";

    }

    @Override
    public boolean equals(Object o) {

        //FILL THIS
        return false;

    }

    @Override
    public int hashCode() {

        //FILL THIS
        return 0;

    }

    public String getBreed() {

        //FILL THIS
        return "";

    }

    public String getCountryOfOrigin() {

        //FILL THIS
        return "false";

    }

    public String getFurColor() {

        //FILL THIS
        return "false";

    }

    public String getHeight() {

        //FILL THIS
        return "false";

    }

    public String getEyeColor() {

        //FILL THIS
        return "false";

    }

    public String getLongevity() {

        //FILL THIS
        return "false";

    }

    public String getCharacterTraits() {

        //FILL THIS
        return "false";

    }

    public String getCommonHealthProblems() {

        //FILL THIS
        return "false";

    }
}
