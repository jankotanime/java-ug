package trafficElement;
import enums.Kolor;

public class Sygnalizator extends TrafficElement {
  private Kolor kolor;
  private int timeToChange;

  public Sygnalizator() {
    this.kolor = Kolor.ZIELONE;
    this.timeToChange = 20;
  }

  public Kolor getKolor() {
    return kolor;
  }

  public void updateStatus(Kolor k) {
    kolor = k;
  }

  public int getTimeToChange() {
    return timeToChange;
  }
}
