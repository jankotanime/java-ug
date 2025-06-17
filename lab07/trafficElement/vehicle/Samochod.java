package trafficElement.vehicle;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import enums.Direction;

public class Samochod extends Vehicle {
  public Samochod(List<Map<UUID, Direction>> r) {
    super(); // Wywołanie konstruktora klasy bazowej
    this.route = r;
    this.priority = 0; // Priorytet dla samochodu
  }
}
