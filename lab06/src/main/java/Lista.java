import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Lista {
  private final List<Zadanie> zadania;

  public Lista() {
    this.zadania = new ArrayList<>();
  }
  
  public Lista(List<Zadanie> zadania) {
    this.zadania = List.copyOf(zadania);
  }

  public List<Zadanie> getZadania() {
    return List.copyOf(zadania);
  }

  public Lista dodajZadanie(Zadanie noweZadanie) {
    List<Zadanie> nowaLista = new java.util.ArrayList<>(this.zadania);
    nowaLista.add(noweZadanie);
    return new Lista(nowaLista);
  }

  public Lista zmienStatusDlaDaty(String data, Status nowyStatus) {
    List<Zadanie> nowaLista = zadania.stream()
      .map(zadanie -> zadanie.getData_wykonania().equals(data) 
        ? zadanie.zmienStatus(nowyStatus) 
        : zadanie)
      .collect(Collectors.toList());

    return new Lista(nowaLista);
  }

  public Lista zmienStatusDlaId(UUID id, Status nowyStatus) {
    List<Zadanie> nowaLista = zadania.stream()
      .map(zadanie -> zadanie.getId().equals(id) 
        ? zadanie.zmienStatus(nowyStatus) 
        : zadanie)
      .collect(Collectors.toList());
    return new Lista(nowaLista);
  }

  public Lista zmienPriorytetDlaId(UUID id, ZadaniePriorytet nowyPriorytet) {
    List<Zadanie> nowaLista = zadania.stream()
      .map(zadanie -> zadanie.getId().equals(id) 
        ? ZadanieFactory.getZadanie(nowyPriorytet, 
          zadanie.getTytul(), zadanie.getOpis(), 
          zadanie.getData_wykonania(), zadanie.getStatus(), 
          zadanie.getTagi())
        : zadanie)
      .collect(Collectors.toList());
    return new Lista(nowaLista);
  }

  public void wypiszZadaniaDlaTagow(Set<String> tagi) {
    System.out.println(String.format("\nWypisanie dla tagów %s:", tagi));
    zadania.stream()
      .filter(zadanie -> !Collections.disjoint(zadanie.getTagi(), tagi))
      .forEach(this::wypiszZadanie);
  }

  public void wypiszWszystkieZadania() {
    System.out.println("\nWypisanie wszystkich zadań:");
    zadania.forEach(this::wypiszZadanie);
  }

  private void wypiszZadanie(Zadanie zadanie) {
    System.out.println(String.format(
        "Zadanie o id %s o tytule %s z dnia %s, status %s, priorytet %s, opis %s, tagi %s",
        zadanie.getId(), zadanie.getTytul(), zadanie.getData_wykonania(), zadanie.getStatus(),
        zadanie.getPriorytet(), zadanie.getOpis(), zadanie.getTagi()
    ));
  }

  public Lista usunZadanie(UUID id) {
    List<Zadanie> nowaLista = zadania.stream()
      .filter(z -> !z.getId().equals(id))
      .collect(Collectors.toList());
    return new Lista(nowaLista);
  }

  // pomocnicze do Id
  public List<Zadanie> pobierzListe() {
    return this.zadania;
  }
}
