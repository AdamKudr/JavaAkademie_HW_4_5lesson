import java.math.BigDecimal;
import java.time.LocalDate;

public class Plant {

    private String name;
    private String notes;
    private LocalDate planted; //datum zasazení
    private LocalDate watering; //datum poslední zálivky
    private int frequencyOfWatering; //běžná frekvence zálivky


    //první konstruktor na vše
    public Plant(String name, String notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        setWatering(watering);
        setFrequencyOfWatering(frequencyOfWatering);
    }

    //druhý konstruktor bez poznámky s nastavením zálivky na dnešní datum
    public Plant(String name, LocalDate planted, int frequencyOfWatering) throws PlantException {
        this.name = name;
        this.notes = "";
        this.planted = planted;
        this.watering = LocalDate.now();
        setFrequencyOfWatering(frequencyOfWatering);
    }

    //třetí konstruktor, kde se zadává pouze jméno. Stejný jako druhý a navíc frekvenci zálivky nastaví na 7 dní a datum zasazení na dnešní datum
    public Plant(String name) throws PlantException {
        this(name, "", LocalDate.now(), LocalDate.now(), 7);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Zadáno datum: " +watering+ " ! Datum poslední zálivky nesmí předcházet datu zasazení rostliny!");
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Zadána chybná frekvence zalévání: " +frequencyOfWatering+ " ! Zadejte kladné číslo!");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public String getWateringInfo() {
        return "Název rostliny: " +name+ "\nDatum poslední zálivky: " +watering+ "\nDatum doporučené další zálivky: " +watering.plusDays(frequencyOfWatering);
    }
}
