import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CsvAnalysis implements ICsvAnalysis{
    private String csvFileToString = "";
    private final ArrayList<String[]> csvData;
    private final ArrayList<String> csvCategories;

    CsvAnalysis(String filename){
        File dataFile = new File(filename);
        csvData = new ArrayList<>();
        csvCategories = new ArrayList<>();

        // Write the code to handle exceptions when working with Files and Scanners.
        // Warn the user if an exception is thrown and why. Use a specific exception class, not the Exception class.
        // Use the Finally block to ensure that the Scanner is closed even when an exception is thrown.

        Scanner scan = new Scanner(dataFile);
        String[] data;

        String input = scan.nextLine();     // First line contains the data categories,
        data = input.split(",");      // so it separates them into the csvCategories
        for(String myStr : data){           // ArrayList
            csvCategories.add( myStr );     //
        }                                   //

        StringBuilder strBuilder = new StringBuilder("");   // For the rest of the lines in the csv file
        while (scan.hasNextLine()) {                        // first the data are added in the strBuilder to
            input = scan.nextLine();                        // be converted into a string.
            strBuilder.append( input );                     // Then they are added in the csvData ArrayList
            data = input.split(",");                  // so each row of the ArrayList contains the
            csvData.add( data );                            // data of each dog breed.
        }
        csvFileToString = strBuilder.toString();

    }

    public CsvAnalysis createCsvAnalysis(String filename){ return new CsvAnalysis(filename); }

    public String getCsvFileAsString(){ return this.csvFileToString; };

    public ArrayList<String> getCsvCategories(){ return this.csvCategories; };

    public ArrayList<String[]> getCsvData() { return csvData; }
}
