public class RescueUnit {
  private String status;
  private Object jednostka;
  private int round_needed;
  private int round_spent;

  public RescueUnit() {
    status = "Brak";
    round_needed = 0;
    round_spent = 0;
  }

  public void przypiszJednostke(Object j, int n) {
    jednostka = j;
    status = "oczekiwanie";
    round_needed = n;
  }
}
