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

        } catch(EOFException | FileNotFoundException e){
            System.err.println("An Error Occured");
            e.printStackTrace();


        } finally {
            /**
             * Checks scan to make sure it is type Scanner, and not null. Otherwise we would get a nullPointerException
             */
            if (scan != null) scan.close();

        }
    }

    public CsvAnalysis createCsvAnalysis(String filename){ return new CsvAnalysis(filename); }

    @Override
    public String getCsvFileAsString(){ return this.csvFileToString; };

    @Override
    public ArrayList<String> getCsvCategories(){ return this.csvCategories; };

    @Override
    public ArrayList<String[]> getCsvData() { return csvData; }
}
