package trafficElement.vehicle;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import enums.Direction;

public class Karetka extends Vehicle {
  public Karetka(List<Map<UUID, Direction>> r) {
    super(); // Wywołanie konstruktora klasy bazowej
    this.route = r;
    this.priority = 2; // Priorytet dla karetki
  }
}
