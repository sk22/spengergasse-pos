import java.util.StringJoiner;

/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-05-11
 */
public class Alphabet {
  public static void main(String... args) {
    StringJoiner joiner = new StringJoiner(" ");
    for(String s : args) joiner.add(s);
    spell(joiner.toString());
  }

  public static void spell(String str) {
    for(int i=0; i<str.length(); i++) spell(str.charAt(i));
  }
  public static void spell(char ch) {
    System.out.print(" "+ch);
    char up = Character.toUpperCase(ch);
    if(up>=65 && up<=90) System.out.println(" ... "+name(up));
    else System.out.println();
  }

  // 65, 90
  public static String name(char ch) {
    char up = Character.toUpperCase(ch);
    switch (up) {
      case 'A': return "Alpha";
      case 'B': return "Bravo";
      case 'C': return "Charlie";
      case 'D': return "Delta";
      case 'E': return "Echo";
      case 'F': return "Foxtrot";
      case 'G': return "Golf";
      case 'H': return "Hotel";
      case 'I': return "India";
      case 'J': return "Juliett";
      case 'K': return "Kilo";
      case 'L': return "Lima";
      case 'M': return "Mike";
      case 'N': return "November";
      case 'O': return "Oscar";
      case 'P': return "Papa";
      case 'Q': return "Quebec";
      case 'R': return "Romeo";
      case 'S': return "Sierra";
      case 'T': return "Tango";
      case 'U': return "Uniform";
      case 'V': return "Victor";
      case 'W': return "Whiskey";
      case 'X': return "X-Ray";
      case 'Y': return "Yankee";
      case 'Z': return "Zulu";
      default: return null;
    }
  }
}
