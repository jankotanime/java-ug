import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// ? For restart gradlew
// export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
// export PATH=$JAVA_HOME/bin:$PATH
// ./gradlew clean build

public class Main {
  public static void main(String[] args) {
    Lista lista = new Lista();
    
    Set<String> mojeTagi = new HashSet<String>();
    mojeTagi.add("Sigma");
    var zadanie1 = ZadanieFactory.getZadanie(ZadaniePriorytet.NISKI, "sigma", "zadanie dla sigmy", "2025-05-31", Status.DO_ZROBIENIA, mojeTagi);
    
    mojeTagi = new HashSet<String>();
    mojeTagi.add("Szkola");
    var zadanie2 = ZadanieFactory.getZadanie(ZadaniePriorytet.SREDNI, "szkoła", "super zadanie do szkoly", "2025-05-31", Status.DO_ZROBIENIA, mojeTagi);

    mojeTagi = new HashSet<String>();
    mojeTagi.add("Sigma");
    mojeTagi.add("Szkola");
    var zadanie3 = ZadanieFactory.getZadanie(ZadaniePriorytet.WYSOKI, "sigma szkoła", "sigma do szkoły zadanie", "2025-06-03", Status.DO_ZROBIENIA, mojeTagi);
    
    UUID idPamiec = zadanie3.getId();

    lista = lista.dodajZadanie(zadanie1);
    lista = lista.dodajZadanie(zadanie2);
    lista = lista.dodajZadanie(zadanie3);
    
    lista.wypiszWszystkieZadania();

    mojeTagi = new HashSet<String>();
    mojeTagi.add("Szkola");
    lista.wypiszZadaniaDlaTagow(mojeTagi);
    
    lista = lista.zmienStatusDlaDaty("2025-05-31", Status.W_TRAKCIE);
    
    lista.wypiszWszystkieZadania();
    
    lista = lista.zmienPriorytetDlaId(lista.pobierzListe().get(0).getId(), ZadaniePriorytet.WYSOKI);
    lista = lista.zmienStatusDlaId(lista.pobierzListe().get(1).getId(), Status.ZROBIONE);
    lista = lista.usunZadanie(idPamiec);

    lista.wypiszWszystkieZadania();
  }
}