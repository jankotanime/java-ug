public class Main {
  public static void main(String[] args) {
    SystemStudiow system = new SystemStudiow();
    Student student = new Student(1, "sigma", 12, 2000);

    system.dodajStudenta(student);

    student.dodajOcene(4);
    student.dodajOcene(4);
    student.dodajOcene(5);

    system.obliczSrednieStudentów();
    system.pobierzSrednia(student);
    system.dodajWydzial(new Wydzial(12, "sigma"));

    system.znajdzStudentówPoŚrednich(4.0, 4.4);

    system.pobierzStudentówPoWydziale("sigma");

    system.grupujStudentówPoŚrednich();
  }
}
