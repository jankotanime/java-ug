package logic;
import trafficElement.Skrzyzowanie;
import trafficElement.vehicle.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import enums.Direction;
import enums.Status;

public class Analyzer {
  public List<Map<UUID, Integer>> analizeTime(List<Vehicle> vehicles) {
    return vehicles.stream()
      .<Map<UUID, Integer>>map(v -> Map.of(v.getId(), v.getWaitTime()))
      .collect(Collectors.toList());
  }
  
  public List<Map<Skrzyzowanie, List<Map<Direction, Status>>>> zobaczSkrzyzowania(List<Skrzyzowanie> skrzyzowania) {
    return skrzyzowania.stream()
      .map(s -> {
        List<Map<Direction, Integer>> info = s.getInfo();

        List<Map<Direction, Status>> statusy = info.stream()
          .map(map -> {
            Direction dir = map.keySet().iterator().next();
            Integer count = map.get(dir);
            Status status;
            if (count > 5) { status = Status.KOREK; } 
            else if (count < 3) { status = Status.NORMALNY; } 
            else { status = Status.WZMOZONY; }
            return Map.of(dir, status);
          }).collect(Collectors.toList());
        return Map.of(s, statusy);
      }).collect(Collectors.toList());
  }

  public List<Map<Integer, Integer>> zobaczLiczbePriorytetow(Skrzyzowanie skrzyzowanie) {
    return skrzyzowanie.getPojazdy().stream()
      .collect(Collectors.groupingBy(
        Vehicle::getPriority,
        Collectors.summingInt(v -> 1)
      ))
      .entrySet().stream()
      .map(entry -> Map.of(entry.getKey(), entry.getValue()))
      .collect(Collectors.toList());
  }

  public int getTotalWaitTime(Skrzyzowanie skrzyzowanie) {
    return skrzyzowanie.getPojazdy().stream()
      .mapToInt(Vehicle::getWaitTime)
      .sum();
  }
}
