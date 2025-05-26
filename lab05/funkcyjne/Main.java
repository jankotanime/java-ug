import java.util.*;
import java.util.stream.*;

public class Main {
  public static void main(String[] args) {
    List<Student> studenci = List.of(
      new Student(1, "Nunerski", "Informatyka", 2, List.of(4, 5, 3)),
      new Student(2, "Sigma", "Matematyka", 3, List.of(5, 5, 4)),
      new Student(3, "Misio", "Informatyka", 1, List.of(3, 4, 4))
    );

    List<Student> informatycy = studenci.stream()
      .filter(s -> s.getWydzial().equals("Informatyka"))
      .collect(Collectors.toList());
    informatycy.forEach(System.out::println);

    studenci.stream()
      .forEach(student -> System.out.println(student.getIndeks() + ": " + student.obliczSrednia()));


    List<Student> sredniacy = studenci.stream()
      .filter(s -> {
        double sr = s.obliczSrednia();
        return sr >= 3.0 && sr <= 4.0;
      })
      .collect(Collectors.toList());
    sredniacy.forEach(System.out::println);

    Map<Integer, List<Student>> grupy = studenci.stream()
      .collect(Collectors.groupingBy(s -> (int) s.obliczSrednia()));
    System.out.println(grupy);
  }
}
