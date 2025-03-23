public class Trojkat extends Ksztalt {
  public Trojkat (String nazwa, double pole) {
    super(nazwa, pole);
  }

  public void oblicz_pole (double a, double h) {
    this.nowe_pole((a*h)/2);
  }
}
