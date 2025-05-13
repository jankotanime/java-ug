package obiektowe;
import java.util.HashMap;
import java.util.Map;

public class Wydzial {
  private int id;
  private String name;

  public Wydzial(int i, String n) {
    id = i;
    name = n;
  }

  public Map<String, Object> pobierz() {
    Map<String, Object> result = new HashMap<>();
    result.put("id", id);
    result.put("nazwa", name);
    return result;
  }
}
