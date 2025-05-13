package obiektowe;
import java.util.ArrayList;
import java.util.Optional;

public class Student extends Osoba {
  private int indeks;
  private int wydzialId;
  private float sredniaOcen;
  private int rokStudiow;
  private ArrayList<Integer> oceny = new ArrayList<>();
  
  public Student(int i, String n, int w, int r) {
    indeks = i;
    nazwisko = n;
    wydzialId = w;
    rokStudiow = r;
  }

  public Optional<Integer> pobierzPoWydziale(int w) {
    if (w == wydzialId) {
      return Optional.of(indeks);
    }
    return null;
  }

  public Integer pobierzIndeks() {
    return indeks;
  }

  public void dodajOcene(int ocena) {
    oceny.add(ocena);
  }

  public void obliczSrednia() {
    int suma = 0;
    for (Integer ocena : oceny) {
      suma += ocena;
    }
    sredniaOcen = (float) suma / oceny.size();
  }

  public float pobierzSrednia() {
    return sredniaOcen;
  }
}