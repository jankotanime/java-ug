import java.util.Map;

public class Policja {
  private int id;
  private Map<String, Integer> station;
  private Map<String, Integer> location;
  private String status;

  public Policja(int i, Map<String, Integer> l) {
    id = i;
    location = l;
    status = "dostępny";
  }

  public int getId() {
    return id;
  }

  public void takeUnit(Map<String, Integer> s) {
    status = "zadysponowany";
  }

  public Map<String, Integer> getStation() {
    return station;
  }

  public Map<String, Integer> getLocation() {
    return location;
  }

  public String getStatus() {
    return status;
  }

  public boolean 
}
