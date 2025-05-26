import java.util.Set;
import java.util.UUID;

public record ZadanieS(UUID id, String tytul, String opis, String data_wykonania, Status status, Set<String> tagi) implements Zadanie { 
  static final String priorytet = "Sredni";

  @Override
  public Status getStatus() {
    return this.status;
  }

  @Override
  public String getTytul() {
    return this.tytul;
  }

  @Override
  public String getPriorytet() {
    return priorytet;
  }

  @Override
  public String getOpis() {
    return this.opis;
  }

  @Override
  public String getData_wykonania() {
    return this.data_wykonania;
  }

  @Override
  public Set<String> getTagi() {
    return this.tagi;
  }
  
  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public Zadanie zmienStatus(Status nowyStatus) {
    return ZadanieFactory.getZadanie(ZadaniePriorytet.SREDNI, tytul, opis, data_wykonania, nowyStatus, tagi);
  }
}