import java.io.*;
import java.nio.file.FileAlreadyExistsException;
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

        String[] data;
        Scanner scan = null;

        /**
         * Exception handling working when working on Files and Scanners. Finally closes the Scanner block to ensure
         * the Scanner isn't left open.
         */
        try{
            scan = new Scanner(dataFile);

            /** 
             * This is for the first line for CSV categories. checks for next line, and scans it and put's it into csvCategories.
             */
            if (scan.hasNextLine()){
                String input = scan.nextLine();
                data = input.split(",");

                for (String str:data){
                    csvCategories.add(str);
                }
            /**
             * Else block throws EOFException to ensure that it doesn't pass through. 
             */
            } else { 
                throw new EOFException("No header line for file");
            }

            StringBuilder strBuilder = new StringBuilder(); 
            while (scan.hasNextLine()){
                String input = scan.nextLine();
                strBuilder.append(input);
                data = input.split(",");
                csvData.add (data);
            }

            csvFileToString = strBuilder.toString();

        } catch(EOFException | FileNotFoundException | FileAlreadyExistsException e){
            System.err.println("An Error Occured");
            e.printStackTrace();


        } finally {
            /**
             * Checks scan to make sure it is type Scanner, and not null. Otherwise we would get a nullPointerException
             */
            if (scan != null) scan.close();

        }
        
        StringBuilder strBuilder = new StringBuilder("");   // For the rest of the lines in the csv file
        while (scan.hasNextLine()) {                        // first the data are added in the strBuilder to
            input = scan.nextLine();                        // be converted into a string.
            strBuilder.append( input );                     // Then they are added in the csvData ArrayList
            data = input.split(",");                  // so each row of the ArrayList contains the
            csvData.add( data );                            // data of each dog breed.
        }
    }

    public CsvAnalysis createCsvAnalysis(String filename){ return new CsvAnalysis(filename); }

    public String getCsvFileAsString(){ return this.csvFileToString; };

    public ArrayList<String> getCsvCategories(){ return this.csvCategories; };

    public ArrayList<String[]> getCsvData() { return csvData; }
}
