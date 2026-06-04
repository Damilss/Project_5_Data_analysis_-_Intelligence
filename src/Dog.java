import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Dog implements Comparable<Dog>, IDog{

    private static final String NODOGFOUND = ("No Dogs Found");
    private static final String NOLISTFOUND = ("List is empty \n");
    private final String breed;
    private final String countryOfOrigin;
    private final String furColor;
    private final String height;
    private final String eyeColor;
    private final String longevity;
    private final String characterTraits;
    private final String commonHealthProblems;

    /* The stream returns the breed summary for a given dog breed */
    @Override
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
    @Override
    public String getDogsFromCountry(List<IDog> idogs, String country) throws EmptyListException, NoResultException {
        if (idogs.isEmpty()) throw new EmptyListException();

        Predicate <IDog> dogCountryOfOrg = (IDog dog) -> dog.getCountryOfOrigin().equals(country);
        Object[] myObj = idogs.stream()
                .filter(dogCountryOfOrg)
                .toArray();

        if (myObj.length == 0) throw new NoResultException();
        
        return myObj[0].toString();

    }

    /* Write a stream to return the dog breeds for a given fur color
     *  Make sure to check for an empty list as input and as a result.
     *  If there is an empty list return a message to the user that there is an empty list. */
    @Override
    public String getDogsFurColor(List<IDog> idogs, String furColor) {
        if (idogs.isEmpty()){return NOLISTFOUND;}

        Predicate <IDog> dogFurColor = (IDog dog) -> dog.getFurColor().equals(furColor);
        Object[] myObj = idogs.stream()
                .filter(dogFurColor)
                .toArray();
        
        if (myObj.length == 0){ return NODOGFOUND + "found with" + furColor + "\n";}

        return myObj[0].toString();

    }

    /* The stream returns the average longevity for the dog breeds in the list */
    @Override
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
    @Override
    public float getAverageDogsHeight(List<IDog> idogs) {
        if ( idogs.isEmpty() ) { return -1.0f;}

        Function<IDog, Integer> getDogHeight = (IDog myDog) -> Integer.valueOf(myDog.getHeight());
        Integer sum = idogs.parallelStream()
                .map(getDogHeight)
                .reduce(0, (result, current) -> result + current);

        return (float) sum / idogs.size();

    }

    /* Write a stream to print the eye color of a specific breed
     * Make sure to check for an empty list as input and as a result.
     * If there is an empty list print a message to the user that there is an empty list. */
    @Override
    public void printBreedEyeColor(List<IDog> idogs, String dogBreed) {
        if (idogs.isEmpty()) System.out.print(NOLISTFOUND);
        
        Predicate<IDog> getBreedPredicate = (IDog myDog) -> myDog.getBreed().equals(dogBreed);
        Function<IDog, String> getEyeFunction = (IDog myDog) -> myDog.getEyeColor();
        
        idogs.stream()
            .filter(getBreedPredicate)
            .map(getEyeFunction)
            .forEach(System.out::println);
    }               


    /* Write a stream to print the traits of a specific breed
     * Make sure to check for an empty list as input and as a result.
     * If there is an empty list print a message to the user that there is an empty list. */
    @Override
    public void printBreedTraits(List<IDog> idogs, String dogBreed) {

    Predicate <IDog> getBreedPredicate = (IDog myDog) -> myDog.getBreed().equals(dogBreed);

        idogs.stream()
            .filter(getBreedPredicate)
            .map(IDog::getCharacterTraits)
            .forEach(System.out::println);
    }

    /* Write a stream to print the health problems of a specific breed
     * Make sure to check for an empty list as input and as a result.
     * If there is an empty list print a message to the user that there is an empty list. */
    @Override
    public void printBreedHealthProblems(List<IDog> idogs, String dogBreed) {

       Predicate <IDog> getBreedHealthPredicate = (IDog myDog) -> myDog.getBreed().equals(dogBreed);

       idogs.stream()
            .filter(getBreedHealthPredicate)
            .map(IDog::getCommonHealthProblems)
            .forEach(System.out::println);

    }

    public Dog( String breed, String countryOfOrigin, String furColor, String height, String eyeColor, String longevity, String characterTraits, String commonHealthProblems ){
        this.breed = breed;
        this.countryOfOrigin = countryOfOrigin;
        this.furColor = furColor;
        this.height = height;
        this.eyeColor = eyeColor;
        this.longevity = longevity;
        this.characterTraits = characterTraits;
        this.commonHealthProblems = commonHealthProblems;
    }

    @Override
    public int compareTo(Dog o) {
        return this.getBreed().compareTo(o.getBreed());
    }

    @Override
    public String toString() {
    return "Dog{breed='" + breed + "', countryOfOrigin='" + countryOfOrigin + 
           "', furColor='" + furColor + "', height=" + height + 
           ", eyeColor='" + eyeColor + "', longevity='" + longevity + 
           "', characterTraits='" + characterTraits + 
           "', commonHealthProblems='" + commonHealthProblems + "'}";
    }          

    @Override
    public boolean equals(Object o) {
    if (o instanceof Dog && this.hashCode() == o.hashCode()) {
        Dog other = (Dog) o;
        return this.breed.equals(other.breed) &&
               this.countryOfOrigin.equals(other.countryOfOrigin) &&
               this.furColor.equals(other.furColor) &&
               this.height.equals(other.height) &&
               this.eyeColor.equals(other.eyeColor) &&
               this.longevity.equals(other.longevity) &&
               this.characterTraits.equals(other.characterTraits) &&
               this.commonHealthProblems.equals(other.commonHealthProblems);
    }
    return false;
    }

    @Override
    public int hashCode() {
        int result = 17*31;
        for (char c : this.getBreed().toCharArray()){
            result += c;
        }
        return result;
    }

    @Override
    public String getBreed() {return this.breed;}

    @Override
    public String getCountryOfOrigin() {return this.countryOfOrigin;}

    @Override
    public String getFurColor() {return this.furColor;}

    @Override
    public String getHeight() {return this.height;}

    @Override
    public String getEyeColor() {return this.eyeColor;}

    @Override
    public String getLongevity(){return this.longevity;}

    @Override
    public String getCharacterTraits() {return this.characterTraits;}

    @Override
    public String getCommonHealthProblems() {return this.commonHealthProblems;}
}
