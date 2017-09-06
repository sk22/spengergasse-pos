package at.spengergasse.kai17521.id3;

/**
 * Instructs how to parse a specific field from the ID3 tag's bytes
 * @author Samuel Kaiser
 * @since 1/16/2017
 * @see Id3FieldParser#parse(byte[])
 */
@FunctionalInterface
public interface Id3FieldParser {
  /**
   * Parses a specific field based on the ID3 tag's bytes
   * @param bytes ID3 tag's bytes
   * @return Parsed field
   */
  Object parse(byte[] bytes);
}
