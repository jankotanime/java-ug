public class Prostokat extends Ksztalt {
  public Prostokat (String nazwa, double pole) {
    super(nazwa, pole);
  }

  public void oblicz_pole (double a, double b) {
    this.nowe_pole(a*b);
  }
}
