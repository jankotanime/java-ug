import java.util.Set;
import java.util.UUID;

public interface Zadanie {
  Zadanie zmienStatus(Status nowyStatus);
  UUID getId();
  String getTytul();
  String getOpis();
  String getData_wykonania();
  Status getStatus();
  Set<String> getTagi();
  String getPriorytet();
}
