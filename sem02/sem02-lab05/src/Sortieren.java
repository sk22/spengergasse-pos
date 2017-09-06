
import java.util.Arrays;
import java.util.Random;

/**
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @since 10.04.2016
 */
public class Sortieren {
  private static final int DEFAULT_LENGTH = 10;
  float[] zahlen;

  public Sortieren(int groesse, float von, float bis) {
    newArray(groesse);
    generiereZufallszahlen(von, bis);
  }

  public Sortieren(float... werte) {
    newArray(werte.length);
    System.arraycopy(werte, 0, zahlen, 0, werte.length);
  }

  public void newArray(int length) {
    if(length>0) zahlen = new float[length];
    else zahlen = new float[DEFAULT_LENGTH];
  }

  public void bubbleSort() { bubbleSort(false); }

  public void bubbleSort(boolean absteigend) {
    float[] array = zahlen;
    for(int wall=array.length; wall>0; wall--) {
      for(int i=0; i<wall-1; i++)
        if(absteigend) {
          if(array[i] < array[i+1]) {
            float x = array[i]; array[i] = array[i + 1]; array[i + 1] = x;
          }
        } else {
          if(array[i] > array[i+1]) {
            float x = array[i]; array[i] = array[i + 1]; array[i + 1] = x;
          }
        }
    } zahlen = array;
  }

  public void insertionSort() {
    for(int i=1; i<zahlen.length; i++) {
      float wert = zahlen[i];
      int j=i;
      while(j>0 && zahlen[j-1] > wert) {
        zahlen[j] = zahlen[--j];
      }
      zahlen[j] = wert;
    }
  }

  public void ausgeben() {
    System.out.println(Arrays.toString(zahlen));
  }

  private void generiereZufallszahlen(float von, float bis) {
    Random random = new Random();
    for(int i=0; i<zahlen.length; i++)
      zahlen[i] = (float) random.nextDouble() * (bis-von) + von;
  }

  public void shuffle(int anzahl) {
    Random random = new Random();
    for(int i=0; i<anzahl; i++) {
      int pos1 = random.nextInt(zahlen.length);
      int pos2 = random.nextInt(zahlen.length);
      tauschen(pos1, pos2);
    }
  }
  public void tauschen(int pos1, int pos2) {
    float x = zahlen[pos1];
    zahlen[pos1] = zahlen[pos2];
    zahlen[pos2] = x;
  }
}
