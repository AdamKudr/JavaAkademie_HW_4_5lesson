import java.time.LocalDate;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {

        PlantList plants = new PlantList();

        //Načtení souboru
        try {
            plants.loadContentFromFile("kvetiny.txt");
        }
        catch (PlantException e) {
            System.err.println("Nastala chyba při čtení souboru. Zkontrolujte údaje o rostlinách!");
        }

        try {
            Plant plant1 = new Plant("Sakura");
            plants.addPlant(plant1);
        } catch (PlantException e) {
            System.err.println("Nastala chyba při přidávání rostliny do seznamu! " +e.getLocalizedMessage());
        }

        try {
            Plant plant2 = new Plant("Bonsai", "malý strom", LocalDate.now(), LocalDate.of(2024, 03, 03),5);
            plants.addPlant(plant2);
        } catch (PlantException e) {
            System.err.println("Nastala chyba při přidávání rostliny do seznamu! " +e.getLocalizedMessage());
        }

        plants.removePlant(plants.getPlant(0));

        //uložení do souboru
        try {
            plants.saveContentToFile("kvetiny_new.txt");
        }
        catch (PlantException e) {
            System.err.println("Nastala chyba při ukládání do souboru. Zkontrolujte údaje o rostlinách!");
        }

        try {
            plants.loadContentFromFile("kvetiny_new.txt");
        }
        catch (PlantException e) {
            System.err.println("Nastala chyba při čtení souboru. Zkontrolujte údaje o rostlinách!");
        }

        for (Plant plant : plants.getPlants()) {
            System.out.println(plant.getWateringInfo());
        }
        System.out.println("\n" + "\n");

        Collections.sort(plants.getPlants());
        for (Plant plant : plants.getPlants()) {
            System.out.println(plant.getWateringInfo());
        }
        System.out.println("\n" + "\n");

        Collections.sort(plants.getPlants(), new PlantsSortByWatering());
        for (Plant plant : plants.getPlants()) {
            System.out.println(plant.getWateringInfo());
        }

    }
}