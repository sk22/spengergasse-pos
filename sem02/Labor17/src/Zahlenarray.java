import java.util.Random;
import java.util.Scanner;

/**
 * @author samuel
 * @since 02.03.2016
 */
public class Zahlenarray {
  private float[] werte;
  private int freiePos;
  private int von;
  private int bis;

  /**
   * Instanziert ein Float-Array mit 10 Slots
   * Erlaubter Bereich: '&' bis '='
   */
  public Zahlenarray() {
    this(10);
  }

  /**
   * Instanziert ein Float-Array mit übergebener Größe.
   * Erlaubter Bereich: '&' bis '='
   * @param groesse Größe des zu instanzierenden Arrays
   */
  public Zahlenarray(int groesse) {
    this(groesse, '&', '=');
  }

  /**
   * Instanziert ein Float-Array mit übergebener Größe und Gültigkeitsbereich
   * @param groesse Größe des zu instanzierenden Arrays
   * @param von     Anfang des erlaubten Bereichs
   * @param bis     Ende des erlaubten Bereichs
   */
  public Zahlenarray(int groesse, int von, int bis) {
    setVonBis(von, bis);
    this.werte = new float[groesse];
    this.freiePos = 0;
  }

  private void setVon(int von) { if (von >= -100 && von <= 500) this.von = von; }
  private void setBis(int bis) { if (bis >= -100 && bis <= 500) this.bis = bis; }

  /**
   * Setzt den Bereich der erlaubten Zahlen im Array
   *
   * @param von Anfang des erlaubten Bereichs
   * @param bis Ende des erlaubten Bereichs
   */
  public void setVonBis(int von, int bis) {
    if (von <= bis) {
      setVon(von);
      setBis(bis);
    }
  }

  /**
   * Gibt alle im Zahlenarray enthaltenen Werte aus
   */
  public void ausgeben() {
    System.out.println(toString());
  }

  @Override
  public String toString() {
    String str = "";
    str+=("Freie Position: "+freiePos+", Länge: "+werte.length+", Befüllt: "+anzahl());
    str+=(" [ ");
    for (int i = 0; i < werte.length; i++)
      str+=(werte[i] + ((werte.length > 0 && i != werte.length - 1) ? ", " : ""));
    str+=(" ]");
    return str;
  }

  /**
   * @param wert Zu überprüfender Wert
   * @return Wert in Gültigkeitsbereich?
   */
  public boolean inBereich(float wert) { if(wert <= bis && wert >= von) return true; return false; }

  /**
   * @return Noch Platz im Array frei?
   */
  public boolean nochFrei() { return freiePos < werte.length; }

  /**
   * @return Anzahl der im Array enthaltenen Werte
   */
  public int size() { return werte.length; }

  /**
   * Fügt einen Wert an der nächsten freien Stelle ein, falls verfügbar, und gibt dann true zurück.
   * Wenn kein Platz mehr frei ist, wird false zurückgegeben.
   *
   * @param wert Der einzufügende Wert
   * @return     Einfügen erfolgreich?
   */
  public boolean einfuegen(float wert) {
    if(!inBereich(wert)) { System.err.println("Der Wert ist nicht im gültigen Bereich!"); return false; }
    if (freiePos < werte.length) werte[freiePos++] = wert;
    else return false;
    return true;
  }

  /**
   * Fügt einen Wert an eine beliebige Stelle im Array ein und verschiebt alle darauffolgenden Werte nach hinten.
   * @param wert Der einzufügende Wert
   * @param pos  Die Position, an der einzufügen ist
   * @return     Einfügen erfolgreich?
   */
  public boolean einfuegen(float wert, int pos) {
    if(!inBereich(wert)) { System.err.println("Der Wert ist nicht im gültigen Bereich!"); return false; }
    if (freiePos < werte.length) {
      if(pos<0) {
        System.err.println("Negative Indizes sind nicht erlaubt!");
        return false;
      } else if (pos > werte.length) {
        System.err.println("Der Wert ist größer als das Array!");
        return false;
      }
      for (int i = freiePos; i > pos; i--) {
        werte[i] = werte[i - 1];
      }
      werte[pos] = wert;
      freiePos = (pos > freiePos ? pos : freiePos) + 1;
      return true;
    } else {
      System.err.println("Im Array ist kein Platz frei!");
      return false;
    }
  }

  /**
   * Entfernt das letzte belegte Element im Array
   * @return Entfernen erfolgreich?
   */
  public boolean entfernen() {
    if(freiePos>0) {
      werte[freiePos-1] = 0;
      freiePos--;
      return true;
    } return false;
  }

  /**
   * Entfernt das Element an der gegebenen Position. Alle darauffolgenden Elemente werden nach vorne verschoben.
   * @param pos Die Position, deren Wert entfernt werden soll.
   * @return Entfernen erfolgreich?
   */
  public boolean entfernen(int pos) {
    System.out.println();
    if(pos < freiePos) {
      if(pos<0) {
        System.err.println("Negative Indizes sind nicht erlaubt!");
        return false;
      } else if (pos > werte.length) {
        System.err.println("Der Wert ist größer als das Array!");
        return false;
      }
      for (int i=pos; i<freiePos; i++) werte[i] = werte[i + 1];
      if(pos<freiePos) freiePos--;
      return true;
    } return false;
  }

  /**
   * Entfernt das erste Vorkommen eines Wertes
   * @param wert Zu suchender und entfernender Wert
   * @return Entfernen erfolgreich?
   */
  public boolean entfernenErsterFund(float wert) {
    int pos = suche(wert);
    return pos > -1 && entfernen(pos);
  }

  /**
   * Entfernt das letzte Vorkommen eines Wertes
   * @param wert Zu suchender und entfernender Wert
   * @return Entfernen erfolgreich?
   */
  public boolean entfernenLetzterFund(float wert) {
    int pos = sucheHinten(wert);
    return pos > -1 && entfernen(pos);
  }

  /**
   * Liest Werte in der Konsole ein und fügt sie zum Array hinzu, bis -999 eingegeben wurde,
   * oder bis das Array keinen Platz mehr hat.
   */
  public void einlesen() {
    System.out.println("Es wird eingelesen. -999 eingeben, um abzubrechen");
    Scanner scanner = new Scanner(System.in);
    float wert=0;
    do {
      if(!nochFrei()) { System.out.println("Im Array ist kein Platz mehr frei!"); return; }
      System.out.print("> ");
      if(scanner.hasNextFloat()) {
        wert = scanner.nextFloat();
        if(wert!=-999f) {
          if(inBereich(wert)) einfuegen(wert);
          else if(!inBereich(wert)) System.err.println("Der Wert muss zwischen "+von+" und "+bis+" liegen!");
        }
      } else System.err.println("Der gegebene Wert \""+scanner.next()+"\"-999 ist ungültig.");
      if(!nochFrei()) { System.out.println("Im Array ist kein Platz mehr frei!"); return; }
    } while(wert != -999f);
  }

  /**
   * Liest eine gewisse Anzahl an Werten ein und fügt sie zum Array hinzu.
   * @param n Anzahl einzugebender Werte
   */
  public void einlesen(int n) {
    System.out.println("Es wird eingelesen. Bitte "+n+" gültige Zahlen eingeben!");
    Scanner scanner = new Scanner(System.in);
    float wert=0;
    do {
      if(!nochFrei()) { System.out.println("Im Array ist kein Platz mehr frei!"); return; }
      System.out.print("> ");
      if(scanner.hasNextFloat()) {
        wert = scanner.nextFloat();
        if(wert!=999f) {
          if(inBereich(wert)) { einfuegen(wert); n--; }
          else if(!inBereich(wert)) System.err.println("Der Wert muss zwischen "+von+" und "+bis+" liegen!");
        }
      } else System.err.println("Der gegebene Wert \""+scanner.next()+"\" ist ungültig.");
      if(!nochFrei()) { System.out.println("Im Array ist kein Platz mehr frei!"); return; }
    } while(n>0);
  }

  /**
   * @return Anzahl der belegten Felder
   */
  public int anzahl() {
    return freiePos;
  }

  /**
   * @param min Minimum der zu zählenden Werte
   * @return Anzahl der gefundenen Werte, die mindestens so groß sind wie der Übergabewert
   */
  public int anzahl(float min) {
    int anzahl = 0;
    for(int i=0; i<freiePos; i++) {
      if(werte[i]>=min) anzahl++;
    } return anzahl;
  }

  /**
   * @param min Minimum der zu zählenden Werte
   * @param max Maximum der zu zählenden Werte
   * @return Anzahl der Werte im Bereich zwischen min und max
   */
  public int anzahl(float min, float max) {
    int anzahl = 0;
    if(min>max) {
      float x = min; min = max; max = x;
    }
    for(int i=0; i<freiePos; i++) {
      if(werte[i]>=min && werte[i]<=max) anzahl++;
    } return anzahl;
  }

  /**
   * @param wert Zu suchender Wert
   * @return Position des ersten Vorkommnisses des Übergabewertes
   */
  public int suche(float wert) {
    for(int i=0; i<freiePos; i++) {
      if(wert == werte[i]) return i;
    } return -1;
  }

  /**
   * @param wert Zu suchender Wert
   * @return Position des letzten Vorkommnisses des Übergabewertes
   */
  public int sucheHinten(float wert) {
    for(int i=freiePos-1; i>=0; i--) {
      if(wert == werte[i]) return i;
    } return -1;
  }

  /**
   * @param wert Zu suchender Wert
   * @return Anzahl der gesuchten Zahl im Array
   */
  public int suchenAnzahl(float wert) {
    int anzahl = 0;
    for(int i=0; i<freiePos; i++) {
      if(werte[i] == wert) anzahl++;
    } return anzahl;
  }

  /**
   * Tauscht die Werte zweier Positionen
   * @param pos1 Erste Position
   * @param pos2 Zweite Position
   */
  public void tauschen(int pos1, int pos2) {
    if(pos1>0 && pos2>0) {
      if(pos1<werte.length && pos2<werte.length) {
        float x = werte[pos1];
        werte[pos1] = werte[pos2];
        werte[pos2] = x;
        if(pos1>pos2 && pos1>freiePos) freiePos = pos1+1;
        else if(pos2>freiePos) freiePos = pos2+1;
        else if((pos1>pos2 && pos1 == freiePos-1) || (pos2>pos1 && pos2 == freiePos-1)) freiePos--;
      }
    }
  }

  /**
   * Generiert ein neues Array mit zufälligen ganzzahligen Werten zwischen dem minimalen und dem maximalen Wert.
   * @param min Minimaler Wert
   * @param max Maximaler Wert
   */
  public void zufallszahlen(float min, float max) {
    if(min>max) { float x = max; max = min; min = x; }
    for(int i=0; i<this.werte.length; i++) {
      double rand = Math.random();
      rand *= max-min;
      rand += min;
      this.werte[i] = (float)rand;
    } freiePos = werte.length;
  }

  /**
   * @return Summe aller Werte im Array
   */
  public float summe() {
    float summe = 0;
    for (float wert : werte) {
      summe+=wert;
    } return summe;
  }

  /**
   * @return Arithmetisches Mittel aller belegten Werte im Array
   */
  public float durchschnitt() {
    return summe() / anzahl();
  }

  /**
   * @return Position (int)[0] und Wert (float)[1] des maximalen Wertes des Arrays
   */
  public Number[] maxUndPos() {
    float max = werte[0];
    int maxPos = 0;
    for (int i=1; i < werte.length; i++) {
      if(werte[i] > max) { max = werte[i]; maxPos = i; }
    } return new Number[] {maxPos, max};
  }

  /**
   * @return Wert (float)[0] und Anzahl der Vorkommnisse (int)[1] des maximalen Werts im Array
   */
  public Number[] maxUndHaeufigkeit() {
    Number[] max = maxUndPos();
    return new Number[] { max[1].floatValue(), anzahl(max[1].floatValue()) };
  }

  public static void main(String[] args) {
    int v, n, p;
    float f;
    Zahlenarray array = new Zahlenarray();
// Teste richtiges Funktionieren des Konstruktors mit 3 Parametern
    {
      System.out.println("Neues Array:");
      array = new Zahlenarray(10, -100, 100);
      array.ausgeben(); System.out.println(); pause();
    }

    {
      v = 4; n = 3;
      System.out.println("Wert " + v + " " + n + "x einfügen:");
      for (int i = 0; i < n; i++) array.einfuegen(v);
      array.ausgeben(); System.out.println(); pause();
    }
    {
      v = 2; p = 1; n = 2;
      System.out.println("Bei " + p + " wird " + v + " " + n + "x eingefügt:");
      for (int i = 0; i < n; i++) array.einfuegen(v, p);
      array.ausgeben(); System.out.println(); pause();
    }

    {
      v = 6; p = 5; n = 1;
      System.out.println("Bei " + p + " wird " + v + " " + n + "x eingefügt:");
      for (int i = 0; i < n; i++) array.einfuegen(v, p);
      array.ausgeben(); System.out.println(); pause();
    }

    {
      n = 2;
      System.out.println("Einlesen:");
      array.einlesen();
      array.ausgeben(); System.out.println(); pause();
    }

    {
      System.out.println("Neues Array:");
      array = new Zahlenarray(10, -100, 100);
      array.ausgeben(); System.out.println(); pause();
    }

    {
      n = 10;
      System.out.println("Array befüllen:");
      array.einlesen(array.size());
      array.ausgeben(); System.out.println(); pause();
    }


    {
      System.out.println("Entferne letzten Wert: "+array.entfernen());
      array.ausgeben(); System.out.println(); pause();
    }

    {
      p=3;
      System.out.println("Entferne Index "+p+": "+array.entfernen(p));
      array.ausgeben(); System.out.println(); pause();
    }

    {
      f = 4f;
      System.out.println("Suche die letzte Position von "+f+": "+array.sucheHinten(f));
      array.ausgeben(); System.out.println(); pause();
    }

    {
      f = 4f;
      System.out.println("Entferne erstes Vorkommen von "+f+": "+array.entfernenErsterFund(f));
      array.ausgeben(); System.out.println(); pause();
    }

    {
      f = 4f;
      System.out.println("Entferne letztes Vorkommen von " + f + ": " + array.entfernenLetzterFund(f));
      array.ausgeben(); System.out.println(); pause();
    }

    {
      int pos1 = 1, pos2 = 5;
      System.out.printf("Tausche %d und %d: \n", pos1, pos2);
      array.tauschen(pos1, pos2); array.ausgeben(); System.out.println(); pause();
    }

    {
      int pos1 = 1, pos2 = 5;
      System.out.printf("Tausche %d und %d: \n", pos2, pos1);
      array.tauschen(pos2, pos1); array.ausgeben(); System.out.println(); pause();
    }

    {
      int minZ = 8, maxZ = 9;
      System.out.printf("Neues Array mit zufälligen Werten zwischen %d und %d.\n", minZ, maxZ);
      array.zufallszahlen(minZ, maxZ); array.ausgeben(); System.out.println(); pause();
    }

    {
      float min = 8.0f, max = 8.5f;
      System.out.println("Anzahl der Werte zwischen "+min+" und "+max+": "+array.anzahl(min, max));
      array.ausgeben(); System.out.println(); pause();
    }

    {
      System.out.println("Summe aller Werte: "+array.summe());
      array.ausgeben(); System.out.println(); pause();
    }

    {
      System.out.println("Arithmetisches Mittel der Werte: "+array.durchschnitt());
      array.ausgeben(); System.out.println(); pause();
    }

    {
      Number[] maxValue = array.maxUndPos();
      System.out.println("Maximaler Wert im Array: "+maxValue[1]+", an der Position "+ maxValue[0]);
      array.ausgeben(); System.out.println(); pause();
    }

    {
      System.out.println("Häufigkeit des maximalen Wertes "+array.maxUndPos()[1]+": "+array.maxUndHaeufigkeit()[1]);
      array.ausgeben(); System.out.println(); pause();
    }

  }

  private static void pause() {
    try {
      Thread.sleep(500);
    } catch (InterruptedException ignored) {}
  }
}
