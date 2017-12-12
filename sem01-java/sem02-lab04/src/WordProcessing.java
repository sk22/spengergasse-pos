import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

/**
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @since 16.03.2016
 */
public class WordProcessing {
  private char[] textToProcess;
  private boolean encoded;
  private char[] encodedText;
  private char[] decodedText;
  private Character[] punctuationMark = { '.', ',', ':', ' ', '(', ')', '-', '!', '?' };

  private HashMap<Character, Character> replacement = new HashMap<>();

  public WordProcessing() throws IOException { this(false); }
  public WordProcessing(boolean encoded) throws IOException {
    try {
      String text = "";
      FileReader fr = new FileReader("laborordnung.txt");
      int is = fr.read();
      while (is!=-1){
        text += (char)is;
        is = fr.read();
      }
      this.textToProcess=text.toCharArray();
    } catch(IOException ioe) { System.out.println(ioe.getMessage()); }
    this.encoded = encoded;
    if(encoded) this.encodedText = textToProcess;
    else this.decodedText = textToProcess;
    this.encodedText = new char[textToProcess.length];

  }

  public int length() {
    return textToProcess.length;
  }

  public char[] getEncodedText() {
    if(encodedText == null) return new char[0];
    return encodedText;
  }

  public char[] getDecodedText() {
    if(decodedText == null) return new char[0];
    return decodedText;
  }

  /**
   * Sets the replacements for encoding/decoding
   * @see #encode()
   * @see #decode()
   * @param replacement HashMap containing the original characters as the keys and the replacements as the values
   */
  public void setReplacement(HashMap<Character, Character> replacement) {
    this.replacement = replacement;
  }

  /**
   * @see #replacement
   * @param ch Character to encode
   * @return Encoded character
   */
  public char encodeChar(char ch) {
    if (replacement.containsKey(ch))
      return replacement.get(ch);
    return ch;
  }

  /**
   * @see #replacement
   * @param ch Character to decode
   * @return Decoded character
   */
  public char decodeChar(char ch) {
    Set<Map.Entry<Character, Character>> entrySet = replacement.entrySet();
    for(Map.Entry<Character, Character> entry : entrySet) {
      if(entry.getValue() == ch) return entry.getKey();
    }
    return ch;
  }

  /**
   * Prints the text
   */
  public void printText() {
    System.out.println(textToProcess);
  }

  /**
   * Prints text from the given position
   * @param position Starting position for the text to print
   */
  public void printText(int position) {
    System.out.println(toString(position));
  }

  public void printText(int start, int end) {
    System.out.println(toString(start, end));
  }

  /**
   * Changes all occurences of a character with its a replacement
   * @param character   Character to replace
   * @param replacement Replacement for the character
   */
  public void changeChar(char character, char replacement) {
    for(int i=0; i<textToProcess.length; i++) {
      if(textToProcess[i] == character) textToProcess[i] = replacement;
    }
  }

  /**
   * Transforms the text into a block text
   * @param width Maximum amount of characters in one line
   */
  public void produceBlockText(int width) {
    String text = "";
    for(int i=0, iLine=0; i<textToProcess.length; i++, iLine++) {
      if(textToProcess[i] == '\n') iLine=0;
      text+=textToProcess[i];
      if(iLine==width-1) {
        text += "\n";
        iLine=0;
      }
    }
    textToProcess = text.toCharArray();
  }

  /**
   * @param search Word to search for
   * @return       Word's first occurence's position
   */
  public int findWord(char[] search) {
    return findWord(search, 0);
  }

  /**
   * @param search Word to search for
   * @param skip   How many occurrences should be skipped
   * @return       Word's first occurrence's position after the skipped position
   */
  public int findWord(char[] search, int skip) {
    String str = new String(search).trim();
    for (int i=0, iWord=0, before=-1; i<textToProcess.length; i++) {
      if(iWord < str.length() && Character.toLowerCase(textToProcess[i]) == Character.toLowerCase(str.charAt(iWord))) {
        if(iWord==0) before = i;
        iWord++;
      } else if(iWord==str.length()) {
        if(i<textToProcess.length && Arrays.asList(punctuationMark).contains(textToProcess[i])) {
          if(skip!=0) skip--; else return before;
        } else if(i>=textToProcess.length) {
          if(skip!=0) skip--; else return before;
        } else { iWord=0; before=0; }
      } else {
        iWord=0;
        before=-1;
      }
    }
    return -1;
  }


  private void remove(int pos) {
    int i;
    for (i=pos; i<textToProcess.length; i++) textToProcess[i] = textToProcess[i+1];
    char[] oldText = textToProcess;
    textToProcess = new char[i-(i-pos)];
    for (i=0; i<textToProcess.length; i++) textToProcess[i] = oldText[i];
  }

  public int countWord(char[] search) {
    int i=0;
    while(findWord(search, i)>=0) i++;
    return i;
  }

  public int countWords() {
    int count=0;
    for (int i=0; i<textToProcess.length; i++) {
      if(Arrays.asList(punctuationMark).contains(textToProcess[i]) &&
         ( !Arrays.asList(punctuationMark).contains(textToProcess[i-1])
        || !Arrays.asList(punctuationMark).contains(textToProcess[i+1])))
        count++;
    }
    return count;
  }

  public void toUpperCase() {
    for(int i=0; i<textToProcess.length; i++) textToProcess[i] = Character.toUpperCase(textToProcess[i]);
  }

  public void toLowerCase() {
    for(int i=0; i<textToProcess.length; i++) textToProcess[i] = Character.toLowerCase(textToProcess[i]);
  }

  public void removeSpaces() {
    do textToProcess = new String(textToProcess).replaceAll("  +", " ").toCharArray();
      while(new String(textToProcess).matches("  +"));
    do textToProcess = new String(textToProcess).replaceAll(" \\r\\n?| \\n", "\n").toCharArray();
      while(new String(textToProcess).contains(" \n"));
  }

  public void encode() {
    for(int i=0; i<textToProcess.length; i++)
      encodedText[i] = encodeChar(textToProcess[i]);
    encoded = true;
  }

  public void printEncoded() {
    if(!encoded) encode();
    System.out.println(encodedText);
  }

  public void decode() {
    for(int i=0; i<textToProcess.length; i++)
      decodedText[i] = decodeChar(textToProcess[i]);
    encoded = false;
  }

  public int countChar(char ch) {
    int count=0;
    for(int i=0; i<textToProcess.length; i++) if(Character.toLowerCase(textToProcess[i]) == Character.toLowerCase(ch))
      count++;
    return count;
  }

  public void printFrequencies() {
    DecimalFormat format = new DecimalFormat("#.##");
    format.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
    for(int i=65; i<=90; i++) {
      System.out.println((char)i+": "+format.format(countChar((char)i)*100/(float)textToProcess.length)+"%");
    }
  }

  public static String slice(char[] text, int position) {
    return slice(text, position, text.length-1);
  }

  public static String slice(char[] text, int from, int to) {
    String str = "";
    if(from>=0 && from<text.length && to<text.length) for(int i=from; i<to-1; i++)
      str+=text[i];
    return str;
  }


  @Override
  public String toString() {
    return new String(textToProcess);
  }
  public String toString(int position) {
    return slice(textToProcess, position, textToProcess.length-1);
  }
  public String toString(int from, int to) {
    return slice(textToProcess, from, to);
  }
}
