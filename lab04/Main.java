import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.lang.Thread;

public class Main {
  public static void main(String[] args) {
    Random rn = new Random();
    ArrayList<Pogotowie> pogotowia = new ArrayList<>();
    ArrayList<Policja> policje = new ArrayList<>();
    ArrayList<Straż> straze = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      HashMap<String, Integer> location = new HashMap<>();
      location.put("x", rn.nextInt(10));
      location.put("y", rn.nextInt(10));
      pogotowia.add(new Pogotowie(i, location));
    }

    for (int i = 0; i < 5; i++) {
      HashMap<String, Integer> location = new HashMap<>();
      location.put("x", rn.nextInt(10));
      location.put("y", rn.nextInt(10));
      straze.add(new Straż(i, location));
    }

    for (int i = 0; i < 5; i++) {
      HashMap<String, Integer> location = new HashMap<>();
      location.put("x", rn.nextInt(10));
      location.put("y", rn.nextInt(10));
      policje.add(new Policja(i, location));
    }

    HashMap<String, Integer> location = new HashMap<>();
    location.put("x", 4);
    location.put("y", 7);
    Wypadek wypadek1 = new Wypadek(new Date(), location, "pozar", 6);

    Dyspozytor dyspozytor = new Dyspozytor();
    dyspozytor.dodajWypadek(wypadek1);

    for (int i = 0; i < 10; i++) {
      dyspozytor.tura(straze, policje, pogotowia);
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }
  }
}