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

  public Vehicle() {
    super();
    this.waitTime = 0;
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
