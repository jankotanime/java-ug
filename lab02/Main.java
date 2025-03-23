public class Main {
  public static void main(String[] args) {
    Prostokat prostokat = new Prostokat("Prostokat", 14); 
    prostokat.wyswietl_nazwe();
    prostokat.wyswietl_pole();
    prostokat.oblicz_pole(4, 6);
    prostokat.wyswietl_pole();

    Trojkat trojkat = new Trojkat("Trojkat", 14); 
    trojkat.wyswietl_nazwe();
    trojkat.wyswietl_pole();
    trojkat.oblicz_pole(4, 4);
    trojkat.wyswietl_pole();

    Kolo kolo = new Kolo("Kolo", 14); 
    kolo.wyswietl_nazwe();
    kolo.wyswietl_pole();
    kolo.oblicz_pole(4);
    kolo.wyswietl_pole();
  }
}