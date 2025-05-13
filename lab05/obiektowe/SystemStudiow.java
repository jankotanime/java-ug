package obiektowe;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class SystemStudiow implements MetodySystemu {
  private ArrayList<Wydzial> wydzialy = new ArrayList<>();
  private ArrayList<Student> studenci = new ArrayList<>();

  public SystemStudiow() {}

  public void dodajStudenta(Student student) {
    studenci.add(student);
  }

  public void dodajWydzial(Wydzial wydzial) {
    wydzialy.add(wydzial);
  }

  public ArrayList<Integer> pobierzStudentówPoWydziale(String w) {
    int wydzialId;
    ArrayList<Integer> studenciWydzialu = new ArrayList<>();
    for (Wydzial wydzial : wydzialy) {
    Map<String, Object> actWydzial = wydzial.pobierz();
      if (actWydzial.get("nazwa") == w) {
        wydzialId = (int) actWydzial.get("id");
        for (Student student : studenci) {
          Optional<Integer> id = student.pobierzPoWydziale(wydzialId);
          if (id.isPresent()) {
            studenciWydzialu.add(id.get());
          }
        }
        System.out.println(studenciWydzialu);
        return studenciWydzialu;
      }
    }
    System.out.println("Brak wydziału");
    return studenciWydzialu;
  }

  public void dodajOceneStudentowi(Student student, int ocena) {
    student.dodajOcene(ocena);
  }

  public void obliczSrednieStudentów() {
    for (Student student : studenci) {
      student.obliczSrednia();
    }
  }

  public void pobierzSrednia(Student student) {
    System.out.println(student.pobierzSrednia());
  }

  public void znajdzStudentówPoŚrednich(double min, double max) {
    if (max < min) {
      System.out.println("Źle dobrane wartości");
      return;
    }
    ArrayList<Integer> wybraniStudenci = new ArrayList<>();
    for (Student student : studenci) {
      float srednia = student.pobierzSrednia();
      if (srednia >= min && srednia <= max) {
        wybraniStudenci.add(student.pobierzIndeks());
      }
    }
    System.out.println(wybraniStudenci);
  }

  public void grupujStudentówPoŚrednich() {
    for (int i = 2; i < 6; i++) {
      ArrayList<Integer> wybraniStudenci = new ArrayList<>();
      for (Student student : studenci) {
        float srednia = student.pobierzSrednia();
        if (srednia >= i && srednia < i+1) {
          wybraniStudenci.add(student.pobierzIndeks());
        }
      }
      System.out.println(wybraniStudenci);
    }
  }
}
