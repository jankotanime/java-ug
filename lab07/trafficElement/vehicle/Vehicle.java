package trafficElement.vehicle;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import enums.Direction;
import trafficElement.TrafficElement;

abstract public class Vehicle extends TrafficElement {
  protected int priority;
  private int waitTime;
  protected List<Map<UUID, Direction>> route;
  private final UUID id; // Unikalny identyfikator

  public Vehicle() {
    this.waitTime = 0;
    this.id = UUID.randomUUID(); // Generowanie unikalnego identyfikatora
  }

  public int getWaitTime() {
    return waitTime;
  }

  public int getPriority() {
    return priority;
  }

  public List<Map<UUID, Direction>> getRoute() {
    return route;
  }

  public UUID getId() {
    return id; // Zwracanie unikalnego identyfikatora
  }

  public Direction getDirection() {
    if (route != null && !route.isEmpty()) {
      Map<UUID, Direction> firstElement = route.get(0);
      if (!firstElement.isEmpty()) {
        return firstElement.values().iterator().next();
      }
    }
    return null;
  }
}
