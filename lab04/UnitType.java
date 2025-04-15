import java.util.Map;

public class UnitType {
  private int id;
  private Map<String, Integer> station;
  private Map<String, Integer> location;
  private String status;

  public UnitType(int i, Map<String, Integer> l) {
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
    station = s;
    status = "zadysponowany";
  }

  public String getStatus() {
    return status;
  }

  public void zwolnij() {
    status = "dostępny";
  }

  public boolean tura() {
    if (status == "dostępny") {
      return false;
    }
    if (status == "zadysponowany") {
      if (location.get("x") < station.get("x")) {
        location.put("x", location.get("x")+1);
        return false;
      }
      if (location.get("x") > station.get("x")) {
        location.put("x", location.get("x")-1);
        return false;
      }
      if (location.get("y") < station.get("y")) {
        location.put("y", location.get("y")+1);
        return false;
      }
      if (location.get("y") > station.get("y")) {
        location.put("y", location.get("y")-1);
        return false;
      }
    }
    status = "zajęty";
    return true;
  }
}
