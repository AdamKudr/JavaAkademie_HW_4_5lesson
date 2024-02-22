import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Scanner;

public class PlantList {

    private List<Plant> plants = new ArrayList<>();

    public void loadContentFromFile (String fileName) throws PlantException {
        int lineCounter = 0;
        plants.clear();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            while (scanner.hasNextLine()) {
                lineCounter++;
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                if (parts.length != 5) throw new PlantException
                        ("Nesprávný počet položek na řádku číslo: " +lineCounter+ "!");
                        String name = parts[0];
                        String notes = parts[1];
                        int frequencyOfWatering = Integer.parseInt(parts[2]);
                        LocalDate watering = LocalDate.parse(parts[3]);
                        LocalDate planted = LocalDate.parse(parts[4]);
                        Plant plant = new Plant(name, notes, planted, watering, frequencyOfWatering);
                        plants.add(plant);
            }

        } catch (FileNotFoundException e) {
            throw new PlantException("Soubor " +fileName+ " nenalezen");
        } catch (NumberFormatException | DateTimeParseException e) {
            throw new PlantException("Chyba formát dat v souboru: "+fileName+ " " +e.getLocalizedMessage());
        }
    }

    public void saveContentToFile (String fileName) throws PlantException {
        String delimiter = "\t";
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
            for (Plant plant : plants) {
                writer.println(plant.getName() + delimiter
                + plant.getNotes() + delimiter
                + plant.getFrequencyOfWatering() + delimiter
                + plant.getWatering() + delimiter
                + plant.getPlanted());
            }
        }
        catch (FileNotFoundException e) {
            throw new PlantException("Soubor: " +fileName+ " nenalezen.");
        }
        catch(IOException e) {
            throw new PlantException("Chyba zápisu dat do souboru: " +fileName+ "." + e.getLocalizedMessage());
        }
    }

    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public Plant getPlant (int index) {
        return plants.get(index);
    }

    public List<Plant> getPlants() {
        return plants;

    }

    @Override
    public String toString() {
        return "plants:" + "\n" + plants;
    }
}
