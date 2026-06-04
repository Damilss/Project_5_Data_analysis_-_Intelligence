import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String userInput = "dog_breeds.csv";
        ICsvAnalysis myDogs = ICsvAnalysis.createCsvAnalysis(userInput); // Imports the data from the csv file.
        ArrayList<IDog> dogs = new ArrayList<>();                        // ArrayList to store the IDog objects.
        ArrayList<IDog> emptyDogs = new ArrayList<>();                   // ArrayList to test the exceptions.

        ArrayList<String> categories =  myDogs.getCsvCategories();
        for ( int i = 0; i < categories.size(); i++ ){
//            System.out.println( " Index " + i + " data : " + categories.get(i) );
        }

        // Creates IDog objects from the data in the csv file and adds them in the dogs arraylist.
        for (int i = 0; i < myDogs.getCsvData().size(); i++) {
            dogs.add( IDog.createDog( myDogs.getCsvData().get(i)[0], myDogs.getCsvData().get(i)[1], myDogs.getCsvData().get(i)[2], myDogs.getCsvData().get(i)[3], myDogs.getCsvData().get(i)[4], myDogs.getCsvData().get(i)[5], myDogs.getCsvData().get(i)[6], myDogs.getCsvData().get(i)[7] ) );
        }

        // For debugging purposes. After the dog objects are added in the dogs ArrayList, uncomment any print statement to see the available choices.
        for( int i = 0; i < dogs.size(); i++ ){
            System.out.println( dogs.get(i).getBreed() );
            System.out.println( dogs.get(i).getCountryOfOrigin() );
            System.out.println( dogs.get(i).getLongevity() );
            System.out.println( dogs.get(i).getEyeColor() );
            System.out.println( dogs.get(i).getFurColor() );
            System.out.println( dogs.get(i).getHeight() );
            System.out.println( dogs.get(i).getCharacterTraits() );
            System.out.println( dogs.get(i).getCommonHealthProblems() );
        }

        userInput = "German Shepherd";

        // Print the summary for a specific breed of a dog.
        System.out.println("The characteristics of " + userInput + " are:\n" + dogs.getFirst().getBreedSummary(dogs, userInput) + '\n');

        userInput = "Germany";

        try {
            System.out.println("The dog breeds coming from " + userInput + " are:\n" + dogs.getFirst().getDogsFromCountry(dogs, userInput) + '\n');

            // Print all the bredds from a specific country.
            System.out.println("The dog breeds coming from " + userInput + " are:\n" + dogs.getFirst().getDogsFromCountry(emptyDogs, userInput) + '\n');

        } catch (EmptyListException | NoResultException e) {
            System.out.println("GetDogsFromCountry did not run. \n" + e.getMessage());
        }
        
        userInput = "Brown";

        // Print all the breed wit ha specific fur color.
        System.out.println("The dog breeds wit fur color " + userInput + " are:\n" + dogs.getFirst().getDogsFurColor(dogs, userInput) + '\n');

        // Print the average longevity for all the dog breeds.
        System.out.println( "The average lifespan of the dogs in the list is: " + dogs.getFirst().getAverageDogsLongevity(dogs) + " years." + '\n');

        // Print the average height for all the dog breeds.
        System.out.println( "The average height of the dogs in the list is: " + dogs.getFirst().getAverageDogsHeight(dogs) + " inches." + '\n');

        userInput = "Labrador Retriever";

        // Print the breed's traits.
        System.out.print( "The most common breed traits for a " + userInput + " are: ");
        dogs.getFirst().printBreedTraits(dogs, userInput);
        System.out.println( "");

        // Print the breed's eye color.
        System.out.print( "The most common eye color for a " + userInput + " are: ");
        dogs.getFirst().printBreedEyeColor(dogs, userInput);
        System.out.println( "");

        // Print the breed's health problems.
        System.out.print( "The most common health problems for a " + userInput + " are: ");
        dogs.getFirst().printBreedHealthProblems(dogs, userInput);
        System.out.println( "");

        // Uses ChatGPT to get an inside about the data in the csv. Specifically, the LLM will "judge" which breed is better a guard dog breed.
        System.out.println( "Asking ChatGPT for the best guard dog breed in the list...");

        // This builds a prompt from the data. Based on the LLM model, this may create errors because of token limitations. Below there is a smaller prompt for testing purposes.
        StringBuilder strBuilder = new StringBuilder( "Which of the following dog breeds would be good for guard dogs and why? " ); // Creates the initial prompt with the question for the LLM.
                                                                                                                                    // We use a StringBuilder object so we can change our string without
                                                                                                                                    // using too much memory.
        for (IDog dog : dogs) {                     // Concatenates the dog breeds into the prompt.
            strBuilder.append( dog.getBreed() );    //
            strBuilder.append( " or " );            //
        }                                           //
        String prompt = strBuilder.toString();      // Concatenates the dog breeds into the prompt.

        // For debugging purposes. Uncomment to see the prompt passed in the LLM.
//        System.out.println(prompt);

        // This gives a smaller prompt to test the agent
        prompt = "Which of the following dog breeds would be good for guard dogs and why? Labrador Retriever, German Shepherd, Bulldog, Poodle, Beagle, Chihuahua, Boxer, Golden Retriever";

//        ArrayList<String> myStringArray  = AI_Agent.askAI(prompt);  // Makes the API call with the prompt as the parameter.
//
//        for(String myString : myStringArray ){  // Prints out the response from ChatGPT.
//            System.out.println(myString);       //
//        }

    }
}