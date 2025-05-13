import java.util.List;

public class Student {
  private final int indeks;
  private final String nazwisko;
  private final String wydzial;
  private final int rok;
  private final List<Integer> oceny;

  public Student(int indeks, String nazwisko, String wydzial, int rok, List<Integer> oceny) {
    this.indeks = indeks;
    this.nazwisko = nazwisko;
    this.wydzial = wydzial;
    this.rok = rok;
    this.oceny = List.copyOf(oceny);
  }

  public String getWydzial() {
    return wydzial;
  }

  public double obliczSrednia() {
    return oceny.stream().mapToInt(Integer::intValue).average().orElse(0.0);
  }

  public int getIndeks() {
    return indeks;
  }

  @Override
  public String toString() {
    return indeks + " " + nazwisko + " (" + wydzial + ") średnia: " + obliczSrednia();
  }
}
