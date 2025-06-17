package trafficElement.vehicle;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import enums.Direction;

public class Tramwaj extends Vehicle {
  public Tramwaj(List<Map<UUID, Direction>> r) {
    super();
    this.route = r;
    this.priority = 1;
  }
}
