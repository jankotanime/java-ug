import java.util.Map;

public class Straż {
  private int id;
  private Map<String, Integer> station;
  private Map<String, Integer> location;
  private String status;

  public Straż(int i, Map<String, Integer> l) {
    id = i;
    location = l;
    status = "dostępny";
  }

  public int getId() {
    return id;
  }

  public Map<String, Integer> getStation() {
    return station;
  }

  public Map<String, Integer> getLocation() {
    return location;
  }

  public void takeUnit(Map<String, Integer> s) {
    status = "zadysponowany";
  }

  public String getStatus() {
    return status;
  }
}
