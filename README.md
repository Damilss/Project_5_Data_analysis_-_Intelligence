# Project 5 — Data Analysis & Intelligence

CSC 203 project. A small Java program that loads a CSV of dog breeds, answers
questions about the data using the Streams API.

## Requirements

- JDK 21 or newer (`List.getFirst()` is used)

## Build & run

Run from the project root, `Main` opens `dog_breeds.csv` by relative path.

```bash
javac -d out/production/Project_5_data_analysis src/*.java
java -cp out/production/Project_5_data_analysis Main
```

## Layout

| File | Purpose |
| --- | --- |
| `src/Main.java` | Driver: loads the CSV, builds the `Dog` list, runs each query |
| `src/ICsvAnalysis.java` / `src/CsvAnalysis.java` | CSV reader, header row into categories, remaining rows into `String[]` records |
| `src/IDog.java` / `src/Dog.java` | Dog model plus the stream-based analysis methods |
| `src/EmptyListException.java` | Thrown when a query gets an empty input list |
| `src/NoResultException.java` | Thrown when a query matches nothing |
| `src/AI_Agent.java` | OpenAI wrapper ( I didn't do this part )
| `dog_breeds.csv` | Dataset: 117 breeds, 8 columns |

## Data

`dog_breeds.csv` columns, in order:

`Breed, Country of Origin, Fur Color, Height (in), Color of Eyes, Longevity (yrs), Character Traits, Common Health Problems`

## Analysis methods (`Dog`)

- `getBreedSummary` — full record for one breed
- `getDogsFromCountry` — breeds from a country; throws `EmptyListException` / `NoResultException`
- `getDogsFurColor` — breeds with a given fur color
- `getAverageDogsLongevity` / `getAverageDogsHeight` — averages over the list (parallel streams); return `-1.0f` on an empty list
- `printBreedEyeColor` / `printBreedTraits` / `printBreedHealthProblems` — print a single field for one breed

## AI agent

I didn't use it but it is apart of the assignment as a bouns. 

