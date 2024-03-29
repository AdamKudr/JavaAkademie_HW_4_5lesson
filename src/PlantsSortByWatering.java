
import java.util.Comparator;
public class PlantsSortByWatering implements Comparator <Plant> {

    @Override
    public int compare(Plant first, Plant second) {
        return first.getWatering().compareTo(second.getWatering());
    }
}
