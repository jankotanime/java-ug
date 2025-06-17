import trafficElement.Skrzyzowanie;
import trafficElement.Sygnalizator;
import trafficElement.vehicle.Karetka;
import trafficElement.vehicle.Samochod;
import trafficElement.vehicle.Tramwaj;
import trafficElement.vehicle.Vehicle;
import enums.Direction;
import enums.Kolor;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    // Tworzenie skrzyżowań
    Skrzyzowanie skrzyzowanie1 = new Skrzyzowanie();
    Skrzyzowanie skrzyzowanie2 = new Skrzyzowanie();
    Skrzyzowanie skrzyzowanie3 = new Skrzyzowanie();

    List<Skrzyzowanie> skrzyzowania = List.of(skrzyzowanie1, skrzyzowanie2, skrzyzowanie3);

    // Tworzenie sygnalizatorów
    Sygnalizator sygnalizator1 = new Sygnalizator();
    Sygnalizator sygnalizator2 = new Sygnalizator();
    Sygnalizator sygnalizator3 = new Sygnalizator();

    List<Sygnalizator> sygnalizatory = List.of(sygnalizator1, sygnalizator2, sygnalizator3);

    // Tworzenie pojazdów
    List<Vehicle> pojazdy = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      Vehicle karetka = new Karetka(generateRoute(skrzyzowania));
      pojazdy.add(karetka);
      Skrzyzowanie start = skrzyzowania.get(0); // Dodajemy pojazd na pierwsze skrzyżowanie
      start.addPojazd(karetka, karetka.getDirection());
    }

    for (int i = 0; i < 10; i++) {
      Vehicle tramwaj = new Tramwaj(generateRoute(skrzyzowania));
      pojazdy.add(tramwaj);
      Skrzyzowanie start = skrzyzowania.get(1); // Dodajemy pojazd na drugie skrzyżowanie
      start.addPojazd(tramwaj, tramwaj.getDirection());
    }

    for (int i = 0; i < 20; i++) {
      Vehicle samochod = new Samochod(generateRoute(skrzyzowania));
      pojazdy.add(samochod);
      Skrzyzowanie start = skrzyzowania.get(2); // Dodajemy pojazd na trzecie skrzyżowanie
      start.addPojazd(samochod, samochod.getDirection());
    }

    // Symulacja
    while (true) {
      System.out.println("Rozpoczynam turę...");

      for (Sygnalizator sygnalizator : sygnalizatory) {
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
              nextColor = Kolor.ZIELONE;
              break;
            default:
              nextColor = Kolor.ZIELONE;
              break;
          }
          sygnalizator.updateStatus(nextColor);
          System.out.println("Sygnalizator zmienił światło na: " + nextColor);
        }
      }

      for (Vehicle pojazd : pojazdy) {
        List<Map<UUID, Direction>> route = pojazd.getRoute();
        if (!route.isEmpty()) {
          Map<UUID, Direction> currentStep = route.remove(0);
          UUID skrzyzowanieId = currentStep.keySet().iterator().next();
          Direction kierunek = currentStep.values().iterator().next();

          Skrzyzowanie skrzyzowanie = skrzyzowania.stream()
            .filter(s -> s.getId().equals(skrzyzowanieId))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("Nie znaleziono skrzyżowania o ID: " + skrzyzowanieId));

          skrzyzowanie.removePojazd(pojazd);

          System.out.println("Pojazd " + pojazd.getId() + " przejechał skrzyżowanie " + skrzyzowanie.getId() + " w kierunku: " + kierunek);

          if (!route.isEmpty()) {
            Map<UUID, Direction> nextStep = route.get(0);
            UUID nextSkrzyzowanieId = nextStep.keySet().iterator().next();
            Direction nextKierunek = nextStep.values().iterator().next();

            Skrzyzowanie nextSkrzyzowanie = skrzyzowania.stream()
              .filter(s -> s.getId().equals(nextSkrzyzowanieId))
              .findFirst()
              .orElseThrow(() -> new IllegalStateException("Nie znaleziono skrzyżowania o ID: " + nextSkrzyzowanieId));

            nextSkrzyzowanie.addPojazd(pojazd, nextKierunek);
            System.out.println("Pojazd " + pojazd.getId() + " wjechał na skrzyżowanie " + skrzyzowanie.getId() + " w kierunku: " + nextKierunek);
          }
        }
      }

      TimeUnit.SECONDS.sleep(1);
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