import trafficElement.Skrzyzowanie;
import trafficElement.Sygnalizator;
import trafficElement.vehicle.Karetka;
import trafficElement.vehicle.Samochod;
import trafficElement.vehicle.Tramwaj;
import trafficElement.vehicle.Vehicle;
import enums.Direction;
import enums.Kolor;
import logic.Optimizer;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Skrzyzowanie skrzyzowanie1 = new Skrzyzowanie();
    Skrzyzowanie skrzyzowanie2 = new Skrzyzowanie();
    Skrzyzowanie skrzyzowanie3 = new Skrzyzowanie();

    List<Skrzyzowanie> skrzyzowania = List.of(skrzyzowanie1, skrzyzowanie2, skrzyzowanie3);

    Sygnalizator sygnalizator1 = new Sygnalizator();
    Sygnalizator sygnalizator2 = new Sygnalizator();
    Sygnalizator sygnalizator3 = new Sygnalizator();

    List<Sygnalizator> sygnalizatory = List.of(sygnalizator1, sygnalizator2, sygnalizator3);

    List<Vehicle> pojazdy = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      Vehicle karetka = new Karetka(generateRoute(skrzyzowania));
      pojazdy.add(karetka);
      Skrzyzowanie start = skrzyzowania.get(0);
      start.addPojazd(karetka, karetka.getDirection());
    }

    for (int i = 0; i < 10; i++) {
      Vehicle tramwaj = new Tramwaj(generateRoute(skrzyzowania));
      pojazdy.add(tramwaj);
      Skrzyzowanie start = skrzyzowania.get(1);
      start.addPojazd(tramwaj, tramwaj.getDirection());
    }

    for (int i = 0; i < 20; i++) {
      Vehicle samochod = new Samochod(generateRoute(skrzyzowania));
      pojazdy.add(samochod);
      Skrzyzowanie start = skrzyzowania.get(2);
      start.addPojazd(samochod, samochod.getDirection());
    }

    Optimizer optimizer = new Optimizer();
    Map<Direction, Integer> baseTimes = Map.of(
      Direction.NORTH, 5,
      Direction.SOUTH, 5,
      Direction.WEST, 5,
      Direction.EAST, 5
    );

    while (true) {
      System.out.println("Rozpoczynam turę...");
    
      Map<Skrzyzowanie, Map<Set<Direction>, Integer>> optymalizacje = optimizer.optimize(skrzyzowania, baseTimes);
    
      for (int i = 0; i < skrzyzowania.size(); i++) {
        Skrzyzowanie skrzyzowanie = skrzyzowania.get(i);
        Sygnalizator sygnalizator = sygnalizatory.get(i);
    
        if (sygnalizator.getTimeToChange() <= 0) {
          Kolor currentColor = sygnalizator.getKolor();
          Kolor nextColor;
          switch (currentColor) {
            case ZIELONE:
              nextColor = Kolor.ZOLTE;
              break;
            case ZOLTE:
              nextColor = Kolor.CZERWONE;
              break;
            case CZERWONE:
            default:
              nextColor = Kolor.ZIELONE;
              break;
          }
          sygnalizator.updateStatus(nextColor);
          System.out.println("Sygnalizator " + i + " zmienił światło na: " + nextColor);
    
          if (nextColor == Kolor.ZIELONE) {
            Map<Set<Direction>, Integer> opt = optymalizacje.get(skrzyzowanie);
            Set<Direction> activeGroup = i % 2 == 0 ? Set.of(Direction.NORTH, Direction.SOUTH)
                                                    : Set.of(Direction.EAST, Direction.WEST);
            int time = opt.getOrDefault(activeGroup, 5);
            sygnalizator.setTimeToChange(time);
            System.out.println("Ustawiono czas świecenia ZIELONEGO: " + time + " dla sygnalizatora " + i);
          }
        } else {
          sygnalizator.setTimeToChange(sygnalizator.getTimeToChange() - 1);
        }
      }
    
      for (Skrzyzowanie skrzyzowanie : skrzyzowania) {
        for (Direction kierunek : Direction.values()) {
          Sygnalizator sygnalizator = sygnalizatory.get(skrzyzowania.indexOf(skrzyzowanie));
          if (sygnalizator.getKolor() == Kolor.ZIELONE) {
            Vehicle pojazd = skrzyzowanie.getPojazdy().stream()
              .filter(v -> v.getDirection() == kierunek)
              .findFirst()
              .orElse(null);
    
            if (pojazd != null) {
              List<Map<UUID, Direction>> route = pojazd.getRoute();
              skrzyzowanie.removePojazd(pojazd);
              if (!route.isEmpty()) {
                Map<UUID, Direction> currentStep = route.remove(0);
                Direction nextKierunek = currentStep.values().iterator().next();
    
    
                System.out.println("Pojazd " + pojazd.getId() + " przejechał skrzyżowanie " + skrzyzowanie.getId() + " w kierunku: " + kierunek);
    
                if (!route.isEmpty()) {
                  Map<UUID, Direction> nextStep = route.get(0);
                  UUID nextSkrzyzowanieId = nextStep.keySet().iterator().next();
    
                  Skrzyzowanie nextSkrzyzowanie = skrzyzowania.stream()
                    .filter(s -> s.getId().equals(nextSkrzyzowanieId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Nie znaleziono skrzyżowania o ID: " + nextSkrzyzowanieId));
    
                  nextSkrzyzowanie.addPojazd(pojazd, nextKierunek);
                  System.out.println("Pojazd " + pojazd.getId() + " wjechał na skrzyżowanie " + nextSkrzyzowanie.getId() + " w kierunku: " + nextKierunek);
                }
              }
            }
          }
        }
      }

      pojazdy.removeIf(pojazd -> {
        if (pojazd.getRoute().isEmpty()) {
          skrzyzowania.forEach(skrzyzowanie -> skrzyzowanie.removePojazd(pojazd));
          return true;
        }
        return false;
      });
    
      TimeUnit.MILLISECONDS.sleep(500);
    }
  }

  private static List<Map<UUID, Direction>> generateRoute(List<Skrzyzowanie> skrzyzowania) {
    Random random = new Random();
    List<Map<UUID, Direction>> route = new ArrayList<>();
    for (int i = 0; i < random.nextInt(3) + 1; i++) {
      Skrzyzowanie skrzyzowanie = skrzyzowania.get(random.nextInt(skrzyzowania.size()));
      Direction direction = Direction.values()[random.nextInt(Direction.values().length)];
      route.add(Map.of(skrzyzowanie.getId(), direction));
    }
    if (route.isEmpty()) {
      Skrzyzowanie defaultSkrzyzowanie = skrzyzowania.get(0);
      route.add(Map.of(defaultSkrzyzowanie.getId(), Direction.NORTH));
    }
    return route;
  }
}