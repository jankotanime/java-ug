import java.util.Set;
import java.util.UUID;

@FunctionalInterface
public interface ZadanieConstructor {
  Zadanie apply(UUID id, String t, String o, String d, Status s, Set<String> tagi);
}