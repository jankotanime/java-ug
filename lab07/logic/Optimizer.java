package logic;

import enums.Direction;
import enums.Status;
import trafficElement.Skrzyzowanie;
import trafficElement.vehicle.Vehicle;

import java.util.*;
import java.util.stream.Collectors;

public class Optimizer {

  private final Analyzer analyzer = new Analyzer();

  public Map<Skrzyzowanie, Map<Set<Direction>, Integer>> optimize(List<Skrzyzowanie> skrzyzowania, Map<Direction, Integer> baseTimes) {
    Map<Skrzyzowanie, Map<Set<Direction>, Integer>> result = new HashMap<>();

    for (Skrzyzowanie s : skrzyzowania) {
      Map<Direction, Integer> vehicleCounts = s.getPojazdy().stream()
        .collect(Collectors.groupingBy(
          Vehicle::getDirection,
          Collectors.summingInt(v -> 1)
        ));

      List<Map<Integer, Integer>> priorityAnalysis = analyzer.zobaczLiczbePriorytetow(s);
      System.out.println("Priorytety na skrzyżowaniu: " + priorityAnalysis);

      int totalWaitTime = analyzer.getTotalWaitTime(s);
      System.out.println("Całkowity czas oczekiwania na skrzyżowaniu: " + totalWaitTime);

      Map<Direction, Integer> priorityModifiers = calculatePriorityModifiers(s);

      Set<Direction> northSouth = Set.of(Direction.NORTH, Direction.SOUTH);
      Set<Direction> eastWest = Set.of(Direction.EAST, Direction.WEST);

      int nsTime = calculateGreenTime(northSouth, vehicleCounts, priorityModifiers, baseTimes);
      int ewTime = calculateGreenTime(eastWest, vehicleCounts, priorityModifiers, baseTimes);

      Map<Set<Direction>, Integer> optimized = new HashMap<>();
      optimized.put(northSouth, nsTime);
      optimized.put(eastWest, ewTime);

      result.put(s, optimized);
    }

    return result;
  }

  private Map<Direction, Integer> calculatePriorityModifiers(Skrzyzowanie s) {
    Map<Direction, Integer> modifiers = new EnumMap<>(Direction.class);
    for (Vehicle v : s.getPojazdy()) {
      int reduction;
      switch (v.getPriority()) {
        case 1:
          reduction = 5;
          break;
        case 2:
          reduction = 10;
          break;
        default:
          reduction = 0;
      }

      if (reduction > 0) {
        for (Direction dir : getOppositeGroup(v.getDirection())) {
          modifiers.merge(dir, -reduction, Integer::sum);
        }
      }
    }
    return modifiers;
  }

  private Set<Direction> getOppositeGroup(Direction dir) {
    switch (dir) {
      case NORTH:
      case SOUTH:
        return Set.of(Direction.EAST, Direction.WEST);
      case EAST:
      case WEST:
        return Set.of(Direction.NORTH, Direction.SOUTH);
      default:
        throw new IllegalArgumentException("Unexpected direction: " + dir);
    }
  }

  private int calculateGreenTime(Set<Direction> group, Map<Direction, Integer> vehicleCounts, Map<Direction, Integer> priorityModifiers, Map<Direction, Integer> baseTimes) {
    int time = 0;

    for (Direction dir : group) {
      int base = baseTimes.getOrDefault(dir, 20);
      int vehicles = vehicleCounts.getOrDefault(dir, 0);
      int mod = priorityModifiers.getOrDefault(dir, 0);
      time += base + vehicles + mod;
    }

    time = time / group.size();
    return Math.max(time, 10);
  }
}
