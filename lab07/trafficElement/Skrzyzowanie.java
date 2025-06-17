package trafficElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import enums.Direction;
import trafficElement.vehicle.Vehicle;

public class Skrzyzowanie extends TrafficElement {
  private List<Vehicle> pojazdy;
  private int toNorth = 0;
  private int toSouth = 0;
  private int toWest = 0;
  private int toEast = 0;

  public Skrzyzowanie() {
    this.pojazdy = new ArrayList<>();
  }

  public void updateStatus() {}

  public List<Map<Direction, Integer>> getInfo() {
    return List.of(
      Map.of(Direction.NORTH, toNorth),
      Map.of(Direction.SOUTH, toSouth),
      Map.of(Direction.WEST, toWest),
      Map.of(Direction.EAST, toEast)
    );
  }

  public void addPojazd(Vehicle v, Direction d) {
    pojazdy.add(v);
    switch (d) {
      case NORTH:
        toNorth++;
        break;
      case SOUTH:
        toSouth++;
        break;
      case WEST:
        toWest++;
        break;
      case EAST:
        toEast++;
        break;
    }
  }

  public void removePojazd(Vehicle v) {
    Direction d = v.getDirection();
    if (d != null && pojazdy.contains(v)) {
      pojazdy.remove(v);
      switch (d) {
        case NORTH:
          toNorth--;
          break;
        case SOUTH:
          toSouth--;
          break;
        case WEST:
          toWest--;
          break;
        case EAST:
          toEast--;
          break;
      }
    }
  }

  public List<Vehicle> getPojazdy() {
    return pojazdy;
  }
}
