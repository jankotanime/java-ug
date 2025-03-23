public class Ksztalt {
  private String nazwa;
  private Double pole;

  public Ksztalt(String nazwa, Double pole) {
    this.nazwa = nazwa;
    this.pole = pole;
}

  public void nowe_pole(double pole) {
    this.pole = pole;
  }

  public void nowa_nazwa(String nazwa) {
    this.nazwa = nazwa;
  }

  public void wyswietl_pole() {
    System.out.println(this.pole);
  }

  public void wyswietl_nazwe() {
    System.out.println(this.nazwa);
  }
}
