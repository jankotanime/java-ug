import java.util.ArrayList;
import java.util.Random;

public class Dyspozytor {
  private ArrayList<Wypadek> wypadkiAktywne;
  private ArrayList<Wypadek> wypadkiHistoryczne;
  private ArrayList<Zgłoszenie> zgłoszenieAktywne;
  private ArrayList<Zgłoszenie> zgłoszenieHistoryczne;

  public Dyspozytor() {
    wypadkiAktywne = new ArrayList<>();
    wypadkiHistoryczne = new ArrayList<>();
    zgłoszenieAktywne = new ArrayList<>();
    zgłoszenieHistoryczne = new ArrayList<>();
  }

  public boolean przjmij(Wypadek w, ArrayList<Straż> s, ArrayList<Policja> pol, ArrayList<Pogotowie> pog) {
    Random rn = new Random();
    if (w.getType() == "pozar") {
      Zgłoszenie z = new Zgłoszenie(w.getId(), w.getDate(), w.getLocation(), w.getInjured());
      for (Straż unit : s) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(unit, "straz", rn.nextInt(w.getInjured()));
          break;
        }
        z.jednostkaOczekuje("straz");
      }
      for (Pogotowie unit : pog) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(unit, "pogotowie", rn.nextInt(w.getInjured()));
          break;
        }
        z.jednostkaOczekuje("pogotowie");
      }
      rozpoczecieZgloszenia(w, z);
      return true;
    }

    if (w.getType() == "kot na drzewie") {
      int wolne = 0;
      for (Straż unit : s) {
        if (unit.getStatus() == "dostępny") {
          wolne++;
          if (wolne > 2) {
            Zgłoszenie z = new Zgłoszenie(w.getId(), w.getDate(), w.getLocation(), w.getInjured());
            unit.takeUnit(w.getLocation());
            z.przypiszJednostke(unit, "straz", rn.nextInt(w.getInjured()));
            rozpoczecieZgloszenia(w, z);
            return true;
          }
        }
      }
    }

    if (w.getType() == "wypadek drogowy") {
      Zgłoszenie z = new Zgłoszenie(w.getId(), w.getDate(), w.getLocation(), w.getInjured());
      for (Policja unit : pol) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(unit, "policja", rn.nextInt(w.getInjured()));
          break;
        }
        z.jednostkaOczekuje("policja");
      }
      for (Pogotowie unit : pog) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(unit, "pogotowie", rn.nextInt(w.getInjured()));
          break;
        }
        z.jednostkaOczekuje("pogotowie");
      }
      rozpoczecieZgloszenia(w, z);
      return true;
    }

    if (w.getType() == "picie alkoholu w miejscu publicznym") {
      int wolne = 0;
      for (Policja unit : pol) {
        if (unit.getStatus() == "dostępny" ) {
          wolne++;
          if (wolne > 1) {
            Zgłoszenie z = new Zgłoszenie(w.getId(), w.getDate(), w.getLocation(), w.getInjured());
            unit.takeUnit(w.getLocation());
            z.przypiszJednostke(unit, "policja", rn.nextInt(w.getInjured()));
            rozpoczecieZgloszenia(w, z);
            return true;
          }
        }
      }
    }

    if (w.getType() == "samobojstwo") {
      Zgłoszenie z = new Zgłoszenie(w.getId(), w.getDate(), w.getLocation(), w.getInjured());
      for (Straż unit : s) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(unit, "straz", rn.nextInt(w.getInjured()));
          break;
        }
        z.jednostkaOczekuje("straz");
      }
      for (Pogotowie unit : pog) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(unit, "pogotowie", rn.nextInt(w.getInjured()));
          break;
        }
        z.jednostkaOczekuje("pogotowie");
      }
      for (Policja unit : pol) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(unit, "policja", rn.nextInt(w.getInjured()));
          break;
        }
        z.jednostkaOczekuje("policja");
      }
      rozpoczecieZgloszenia(w, z);
      return true;
    }
    return false;
  }

  public void sprawdzDostepnoscJednostek( ArrayList<Straż> s, ArrayList<Policja> pol, ArrayList<Pogotowie> pog) {
    Random rn = new Random();
    for (Zgłoszenie z : zgłoszenieAktywne) {
      if (z.czyJednostkaOczekuje("straz")) {
        for (Straż u : s) {
          if (u.getStatus() == "dostępny") {
            u.takeUnit(z.getLocation());
            z.przypiszJednostke(u, "straz", rn.nextInt(z.getPeopleInNeed()+1));
            break;
          }
          z.jednostkaOczekuje("straz");
        }
      }
    }
    for (Zgłoszenie z : zgłoszenieAktywne) {
      if (z.czyJednostkaOczekuje("policja")) {
        for (Policja u : pol) {
          if (u.getStatus() == "dostępny") {
            u.takeUnit(z.getLocation());
            z.przypiszJednostke(u, "policja", rn.nextInt(z.getPeopleInNeed()+1));
            break;
          }
          z.jednostkaOczekuje("policja");
        }
      }
    }
    for (Zgłoszenie z : zgłoszenieAktywne) {
      if (z.czyJednostkaOczekuje("pogotowie")) {
        for (Pogotowie u : pog) {
          if (u.getStatus() == "dostępny") {
            u.takeUnit(z.getLocation());
            z.przypiszJednostke(u, "pogotowie", rn.nextInt(z.getPeopleInNeed()+1));
            break;
          }
          z.jednostkaOczekuje("pogotowie");
        }
      }
    }
  }

  public void dodajWypadek(Wypadek w) {
    System.out.println("Został zauważony wypadek "+w.getType()+" w czasie i miejscu "
    +w.getDate()+w.getLocation()+" z ilością "+w.getInjured()+" rannych");
    wypadkiAktywne.add(w);
  }

  public void rozpoczecieZgloszenia(Wypadek w, Zgłoszenie z) {;
    wypadkiHistoryczne.add(w);
    zgłoszenieAktywne.add(z);
  }

  public void tura(ArrayList<Straż> s, ArrayList<Policja> pol, ArrayList<Pogotowie> pog) {
    ArrayList<Wypadek> doZmiany = new ArrayList<>();
    ArrayList<Wypadek> doUsunieciaW = new ArrayList<>();

    for (Wypadek w : wypadkiAktywne) {
      if (przjmij(w, s, pol, pog)) {
        doUsunieciaW.add(w);
      } else {
        doZmiany.add(w);
      }
    }
    for (Wypadek w : doUsunieciaW) {
      wypadkiAktywne.remove(w);
    }
    for (Wypadek w : doZmiany) {
      wypadkiAktywne.remove(w);
      wypadkiAktywne.add(w);
    }

    ArrayList<Zgłoszenie> doUsuniecia = new ArrayList<>();
    for (Zgłoszenie z : zgłoszenieAktywne) {
      if (z.czyWszyscyOcaleni()) {
        doUsuniecia.add(z);
        System.out.println("Zgłoszenie zakończone; Raport: "+z.getRaport());
      }
    }
    for (Zgłoszenie usun : doUsuniecia) {
      zgłoszenieAktywne.remove(usun);
      zgłoszenieHistoryczne.add(usun);
    }

    sprawdzDostepnoscJednostek(s, pol, pog);
  }
}
