package obiektowe;
import java.util.ArrayList;

public interface MetodySystemu {
  public void dodajStudenta(Student student);
  public void dodajWydzial(Wydzial wydzial);
  public ArrayList<Integer> pobierzStudentówPoWydziale(String w);
  public void dodajOceneStudentowi(Student student, int ocena);
  public void obliczSrednieStudentów();
  public void pobierzSrednia(Student student);
  public void znajdzStudentówPoŚrednich(double min, double max);
  public void grupujStudentówPoŚrednich();
}
