import java.util.HashMap;

/**
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @since 16.03.2016
 */
public class Test {

  public static void main(String[] args) { try {
    WordProcessing word = new WordProcessing();
    HashMap<Character, Character> replacement = new HashMap<>();
    for(int i='a'; i<='z'; i++) {
      if(i % 2 == 0) replacement.put((char)i, (char)(i-1));
      else replacement.put((char)i, (char)(i+1));
    }
    for(int i='A'; i<='Z'; i++) {
      if(i % 2 == 0) replacement.put((char)i, (char)(i-1));
      else replacement.put((char)i, (char)(i+1));
    }
    word.setReplacement(replacement);
    System.out.println(replacement);


    {
      header("Suche");
      char[] search = "ausgeschlossen ".toCharArray();
      System.out.println("Erstes Vorkommnis von \""+new String(search)+"\": "+word.findWord(search));
      System.out.println("Text ab erstem Vorkommnis:\n"); word.printText(word.findWord(search));
    }

    {
      header("Wort zählen");
      char[] search = "Laboratorien".toCharArray();
      System.out.println("Anzahl von \""+new String(search)+"\": "+word.countWord(search));
    }

    {
      header("Wörter zählen");
      System.out.println(word.countWords());
    }

    {
      header("Überflüssige Abstände entfernen");
      System.out.println("Remove multiple spaces and output 1/32 of the produced text, then decode\n");
      word.removeSpaces();
      word.printText(0, word.length()/32);
    }

    {
      header("Ersetzen");
      System.out.println("Replace all - with + and output 1/32 of the produced text");
      word.changeChar('-', '+');
      word.printText(0, word.length()/32);
    }

    {
      header("Text kodieren");
      System.out.println("Encode text and output 1/32 of the produced text, then decode\n");
      word.encode();
      System.out.println(WordProcessing.slice(word.getEncodedText(), 0, word.length()/32));
    }

    {
      header("Block-Text erzeugen");
      int width = 20;
      System.out.printf("Produce block text and output 1/32 of the produced text with a width of %d\n\n", width);
      word.produceBlockText(width);
      word.printText(0, word.length()/32);
    }
    {
      header("Häufigkeiten");
      System.out.println("Frequencies for every letter:");
      word.printFrequencies();
    }

  } catch(Exception e) { System.out.println(e.getMessage()); } }

  private static void header(String name) {
    System.out.println("\n\n === "+name.toUpperCase()+" === \n");
  }
}
