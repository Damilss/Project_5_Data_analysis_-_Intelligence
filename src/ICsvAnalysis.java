import java.util.ArrayList;

public interface ICsvAnalysis {

    // Imports the data from the csv file.
    public static CsvAnalysis createCsvAnalysis(String filename){
        return new CsvAnalysis(filename);
    }

    // Returns a string with all the data from the csv file.
    public String getCsvFileAsString();

    // Returns aa arraylist with all the data categories in the csv.
    public ArrayList<String> getCsvCategories();

    // Returns a 2D string array with all the data from the csv file separated in their own columns.
    public ArrayList<String[]> getCsvData();
}
