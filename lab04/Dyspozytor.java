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
          z.przypiszJednostke(s.get(0), "straz", rn.nextInt(w.getInjured()));
          break;
        }
      }
      for (Pogotowie unit : pog) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(pog.get(0), "pogotowie", rn.nextInt(w.getInjured()));
          break;
        }
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
            z.przypiszJednostke(s.get(0), "straz", rn.nextInt(w.getInjured()));
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
          z.przypiszJednostke(pol.get(0), "policja", rn.nextInt(w.getInjured()));
          break;
        }
      }
      for (Pogotowie unit : pog) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(pog.get(0), "pogotowie", rn.nextInt(w.getInjured()));
          break;
        }
      }
      rozpoczecieZgloszenia(w, z);
      return true;
    }

    if (w.getType() == "picie alkoholu w miejscu publicznym") {
      for (Policja unit : pol) {
        int wolne = 0;
        if (unit.getStatus() == "dostępny" ) {
          wolne++;
          if (wolne > 1) {
            Zgłoszenie z = new Zgłoszenie(w.getId(), w.getDate(), w.getLocation(), w.getInjured());
            unit.takeUnit(w.getLocation());
            z.przypiszJednostke(pol.get(0), "policja", rn.nextInt(w.getInjured()));
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
          z.przypiszJednostke(s.get(0), "straz", rn.nextInt(w.getInjured()));
          break;
        }
      }
      for (Pogotowie unit : pog) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(pog.get(0), "pogotowie", rn.nextInt(w.getInjured()));
          break;
        }
      }
      for (Policja unit : pol) {
        if (unit.getStatus() == "dostępny") {
          unit.takeUnit(w.getLocation());
          z.przypiszJednostke(pol.get(0), "policja", rn.nextInt(w.getInjured()));
          break;
        }
      }
      rozpoczecieZgloszenia(w, z);
      return true;
    }
    return false;
  }

  public void dodajWypadek(Wypadek w) {
    wypadkiAktywne.add(w);
  }

  public void rozpoczecieZgloszenia(Wypadek w, Zgłoszenie z) {
    wypadkiAktywne.remove(w);
    wypadkiHistoryczne.add(w);
    zgłoszenieAktywne.add(z);
  }

  public void zakonczenieZgloszenia(Zgłoszenie z) {
    zgłoszenieAktywne.remove(z);
    zgłoszenieHistoryczne.add(z);
  }

  public void tura(ArrayList<Straż> s, ArrayList<Policja> pol, ArrayList<Pogotowie> pog) {
    for (Wypadek w : wypadkiAktywne) {
      if (przjmij(w, s, pol, pog)) {break;}
    }
  }
}
