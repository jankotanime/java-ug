import java.util.Set;
import java.util.UUID;

public class ZadanieFactory {
  public static Zadanie getZadanie(ZadaniePriorytet p, String t, String o, String d, Status s, Set<String> tagi) {
    UUID id = UUID.randomUUID();
    return p.getConstructor().apply(id, t, o, d, s, tagi);
  }

  public static Zadanie getZadanie(UUID id, ZadaniePriorytet p, String t, String o, String d, Status s, Set<String> tagi) {
    return p.getConstructor().apply(id, t, o, d, s, tagi);
  }
}
