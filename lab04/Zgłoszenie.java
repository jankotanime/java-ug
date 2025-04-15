import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Zgłoszenie {
  private UUID id;
  private Date date;
  private Map<String, Integer> location;
  private boolean status;
  private HashMap<String, RescueUnit> rescueUnit;
  private int peopleInNeed;
  private int peopleHelped;

  public Zgłoszenie(UUID i, Date d, Map<String, Integer> l, int n) {
    id = i;
    date = d;
    location = l;
    status = true;
    RescueUnit policja = new RescueUnit();
    RescueUnit pogotowie = new RescueUnit();
    RescueUnit straz = new RescueUnit();
    rescueUnit = new HashMap<>();
    rescueUnit.put("policja", policja);
    rescueUnit.put("pogotowie", pogotowie);
    rescueUnit.put("straz", straz);
    peopleInNeed = n;
    peopleHelped = 0;
  }

  public void zmienStatus() {
    status = !status;
  }

  public void aktualizujDane(Map<String, Object> data) {
    if (data.containsKey("date")) {date = (Date) data.get("date");}
    if (data.containsKey("location")) {location = (Map<String, Integer>) data.get("location");}
    if (data.containsKey("status")) {status = (boolean) data.get("status");}
    if (data.containsKey("rescueUnit")) {rescueUnit = (HashMap<String, RescueUnit>) data.get("rescueUnit");}
    if (data.containsKey("peopleInNeed")) {peopleInNeed = (int) data.get("peopleInNeed");}
    if (data.containsKey("peopleHelped")) {peopleHelped = (int) data.get("peopleHelped");}
  }

  public void przypiszJednostke(Object j, String t, int n) {
    rescueUnit.get(t).przypiszJednostke(j, n);
  }
}
