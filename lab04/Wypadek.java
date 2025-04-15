import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Wypadek {
  private UUID id;
  private Date date;
  private Map<String, Integer> location;
  private boolean status;
  private String type;
  private int injured;

  public Wypadek(Date d, Map<String, Integer> l, String t, int i) {
    id = UUID.nameUUIDFromBytes((d.toString()+l).getBytes());
    date = d;
    location = l;
    status = true;
    type = t;
    injured = i;
  }

  public void zmienStatus() {
    status = !status;
  }

  public void aktualizujDane(Map<String, Object> data) {
    if (data.containsKey("date")) {date = (Date) data.get("date");}
    if (data.containsKey("location")) {location = (Map<String, Integer>) data.get("location");}
    if (data.containsKey("status")) {status = (boolean) data.get("status");}
    if (data.containsKey("type")) {type = (String) data.get("type");}
    if (data.containsKey("injured")) {injured = (int) data.get("injured");}
  }

  public UUID getId() {
    return id;
  }
  public Date getDate() {
    return date;
  }
  public Map<String, Integer> getLocation() {
    return location;
  }

  public String getType() {
    return type;
  }

  public boolean getStatus() {
    return status;
  }

  public int getInjured() {
    return injured;
  }
}