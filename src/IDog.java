import java.util.List;
import java.util.Objects;

public interface IDog {

    public static IDog createDog(String breed, String countryOfOrigin, String furColor, String height, String eyeColor, String longevity, String characterTraits, String commonHealthProblems) {
        return new Dog(breed, countryOfOrigin, furColor, height, eyeColor, longevity, characterTraits, commonHealthProblems);
    }

    public String getBreedSummary(List<IDog> idogs, String breed);

    public String getDogsFromCountry(List<IDog> idogs, String country) throws EmptyListException, NoResultException;

    public String getDogsFurColor(List<IDog> idogs, String furColor);

    public float getAverageDogsLongevity(List<IDog> idogs);

    public float getAverageDogsHeight(List<IDog> idogs);

    public void printBreedEyeColor(List<IDog> idogs, String breed);

    public void printBreedTraits(List<IDog> idogs, String breed);

    public void printBreedHealthProblems(List<IDog> idogs, String breed);

    public int compareTo(Dog o);

    public String toString();

    public boolean equals(Object o);

    public int hashCode();

    public String getBreed();

    public String getCountryOfOrigin();

    public String getFurColor();

    public String getHeight();

    public String getEyeColor();

    public String getLongevity();

    public String getCharacterTraits();

    public String getCommonHealthProblems();
}