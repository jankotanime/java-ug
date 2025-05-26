import java.util.UUID;
import java.util.Set;

public class ZadanieParent {
  UUID id = UUID.randomUUID();
  String tytul;
  String opis;
  String data_wykonania;
  String priorytet;
  Status status;
  Set<String> tagi;
}
