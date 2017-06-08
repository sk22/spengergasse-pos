package at.spengergasse.kai17521.id3;

import java.util.Arrays;

/**
 * @author Samuel Kaiser
 * @since 1/21/2017
 */
public interface Id3FieldMeta {
  static Id3FieldParser range(Id3FieldParser fn, int from, int to) {
    return b -> fn.parse(Arrays.copyOfRange(b, from, to));
  }

  static Id3FieldParser fromString() {
    return bytes -> new String(bytes).trim();
  }

  Id3FieldParser getParser();
  String name();
  int ordinal();
  int hashCode();
}
