import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws PlantException {

        Plant plant1 = new Plant("Sakura");
        System.out.println(plant1.getWateringInfo());

        try {
            Plant plant2 = new Plant("Bonsai", LocalDate.now(), 0);
            System.out.println(plant2.getWateringInfo());
        }
        catch (PlantException e) {
            throw new PlantException("Chyba při zadávání rostliny! " + e.getLocalizedMessage());
        }
        Plant plant3 = new Plant ("Jabloň", "Kvete na jaře, sklízí se na podzim", LocalDate.of(2024, 6, 5), LocalDate.of(2024, 6, 4), 20);
        System.out.println(plant3.getWateringInfo());
    }
}