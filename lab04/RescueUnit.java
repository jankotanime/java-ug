public class RescueUnit {
  private String status;
  private UnitType jednostka;
  private int round_needed;
  private int round_spent;

  public RescueUnit() {
    status = "Brak";
    round_needed = 0;
    round_spent = 0;
  }

  public void przypiszJednostke(UnitType j, int n) {
    jednostka = j;
    status = "zajęty";
    round_needed = n;
  }

  public void jednostkaOczekuje() {
    status = "oczekiwanie";
  }

  public boolean czyOczekuje() {
    return (status == "oczekiwanie");
  }

  public void zwolnijJednostke() {
    status = "Brak";
    if (jednostka != null) {
      jednostka.zwolnij();
      jednostka = null;
    }
  }

  public String getStatus() {
    return status;
  }

  public boolean tura() {
    if (status == "zajęty") {
      if (jednostka.tura()) {
        round_spent++;
        if (round_needed == round_spent) {
          round_needed = 0;
          round_spent = 0;
          zwolnijJednostke();
          return true;
        }
        return jednostka.tura();
      };
    }
    return false;
  }
}
