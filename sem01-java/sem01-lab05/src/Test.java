/**
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @since 10.04.2016
 */
public class Test {
  public static void main(String... args) {
    Sortieren sort = new Sortieren(10, 0, 15);

    {
      System.out.println("Neues Array mit zufälligen Werten");
      sort.ausgeben();
    }
    {
      System.out.println("Aufsteigend sortieren");
      sort.bubbleSort();
    }
    {
      sort.ausgeben();
      System.out.println("Absteigend sortieren");
    }
    {
      sort.bubbleSort(true);
      sort.ausgeben();
    }
    {
      int anz = 10;
      System.out.printf("Werte %d-mal mischen\n", anz);
      sort.shuffle(anz);
      sort.ausgeben();
    }
    {
      System.out.println("Neues Array mit zufälligen Werten");
      sort = new Sortieren(10, 0, 9);
      sort.ausgeben();
    }
    {
      System.out.println("Insertionsort");
      sort.insertionSort();
      sort.ausgeben();
    }
  }
}
