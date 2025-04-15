import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.lang.Thread;

public class Main {
  public static String getLocation() {
    Random rn = new Random();
    ArrayList<String> zdarzenia = new ArrayList<>();
    zdarzenia.add("pozar");
    zdarzenia.add("kot na drzewie");
    zdarzenia.add("wypadek drogowy");
    zdarzenia.add("picie alkoholu w miejscu publicznym");
    zdarzenia.add("samobojstwo");
    return zdarzenia.get(rn.nextInt(5));
  }
  public static void main(String[] args) {
    Random rn = new Random();
    Dyspozytor dyspozytor = new Dyspozytor();
    ArrayList<Pogotowie> pogotowia = new ArrayList<>();
    ArrayList<Policja> policje = new ArrayList<>();
    ArrayList<Straż> straze = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      HashMap<String, Integer> location = new HashMap<>();
      location.put("x", rn.nextInt(10));
      location.put("y", rn.nextInt(10));
      pogotowia.add(new Pogotowie(i, location));
    }

    for (int i = 0; i < 3; i++) {
      HashMap<String, Integer> location = new HashMap<>();
      location.put("x", rn.nextInt(10));
      location.put("y", rn.nextInt(10));
      straze.add(new Straż(i, location));
    }

    for (int i = 0; i < 4; i++) {
      HashMap<String, Integer> location = new HashMap<>();
      location.put("x", rn.nextInt(10));
      location.put("y", rn.nextInt(10));
      policje.add(new Policja(i, location));
    }

    for (int i = 0; i < 10; i++) {
      HashMap<String, Integer> location = new HashMap<>();
      location.put("x",  rn.nextInt(10));
      location.put("y",  rn.nextInt(10));
      dyspozytor.dodajWypadek(new Wypadek(new Date(), location, getLocation(), rn.nextInt(8)+2));
    }

    while (true) {
      dyspozytor.tura(straze, policje, pogotowia);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }
  }
}