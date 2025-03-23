public class Kolo extends Ksztalt {
  public Kolo(String nazwa, double pole) {
    super(nazwa, pole);
  }

  public void oblicz_pole(double r) {
    this.nowe_pole((Math.PI*r*r));
  }
}
