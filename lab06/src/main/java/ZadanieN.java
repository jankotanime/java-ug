import java.util.Set;
import java.util.UUID;

public class ZadanieN extends ZadanieParent implements Zadanie { 
  static final String priorytet = "Niski";

  public ZadanieN(UUID id, String t, String o, String d, Status s, Set<String> tagi) {
    this.id = id;
    this.tytul = t;
    this.opis = o;
    this.data_wykonania = d;
    this.status = s;
    this.tagi = tagi;
  }
  
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
    return ZadanieFactory.getZadanie(ZadaniePriorytet.NISKI, tytul, opis, data_wykonania, nowyStatus, tagi);
  }
}