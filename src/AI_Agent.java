import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AI_Agent {

    public static ArrayList<String> askAI(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";      // The OpenAI URL

        // The OpenAI API Key. TO createa a key, login on the OpenAI platform, https://platform.openai.com/ and generate a key using the API Keys tab on the left part of the page,
        // and then click on the "Create a new secret key" button on the upper right side of the page.
        String apiKey = " Paste your API key here";

        // The OpenAI model. More models can be found here, https://developers.openai.com/api/docs/models
        String model = "gpt-5.4-mini-2026-03-17";

        HttpURLConnection connection = null;    // Creates the Http connection object to be used to connect with the OpenAI server.

        try {
            // Creates the connection with the ChatGPT http server through the URL.
            URL obj = new URL(url);
            connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json");

            // Makes the API call using the prompt.
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Reads the response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer response = new StringBuffer();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // For debug purposes. Uncomment to see the JSON response from ChatGPT.
//            System.out.println(response.toString());

            // Calls the method to extract the ChatGPT's response into a readable string.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {       // Handles any exception thrown by the connection.
            throw new RuntimeException(e);
        } finally {                     // Closes the connection and frees the network socket.
            if (connection != null) { connection.disconnect(); }
        }
    }

    public static ArrayList<String> extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;           // Returns the index where the "content" word starts in the LLM's response, which is what we are interested in.
                                                                // Then we move the file pointer 11 spaces to filter out the "content" word and the spaces.

        int end = response.indexOf("\"", start);            // Returns the index where the end of the response is located.

        String myString = response.substring(start, end);       // Returns the LLM's response as a string.

        ArrayList<String> myClearedStrings = new ArrayList<>(); // New ArrayList to hold the LLM's sentences.

        start = 0;                                              // Reset the starting point.

        // Splits the LLM's response into individual sentences when a \n character is met.
        // It checks if the end index returns an error or if it's beyond the length of the string. In that case, the whole unchanged string is returned.
        // If the end index is valid, then it adds the substring into the ArrayList.
        // Sometimes there may be two \n characters, so the if statement structure adds an empty line for every second \n character after checking if those indices are in bounds.
        while(start < myString.length()) {
            end = myString.indexOf("\\n", start);

            if( end == -1 || end >= myString.length() ) {   // Checks for errors.
                myClearedStrings.add( myString.substring( start ) );
                start = myString.length();      // Terminates the loop.
            } else {
                myClearedStrings.add(myString.substring(start, end));

                start = end + 2;

                if( start < myString.length() && myString.charAt( start ) == '\\' && myString.charAt( start + 1 ) == 'n') {
                    myClearedStrings.add( " " );
                    start += 2;
                }
            }
        }
        return myClearedStrings;
    }
}