package at.spengergasse.kai17521.id3;

import at.spengergasse.kai17521.id3.fields.Genre;

import java.util.Arrays;

/**
 * @author Samuel Kaiser
 * @since 1/21/2017
 */
public enum Id3Version {
  V1(Id3V1FieldMeta.values());

  private Id3FieldMeta[] fieldMeta;

  Id3Version(Id3FieldMeta[] fieldMeta) {
    this.fieldMeta = fieldMeta;
  }

  public Id3FieldMeta[] getFieldMeta() {
    return fieldMeta;
  }

  /**
   * @author Samuel Kaiser
   * @since 1/18/2017
   */
  public enum Id3V1FieldMeta implements Id3FieldMeta {
    HEADER(range(fromString(), 0, 3)),
    TITLE(range(fromString(), 3, 33)),
    ARTIST(range(fromString(), 33, 63)),
    ALBUM(range(fromString(), 63, 93)),
    YEAR(range(fromString(), 93, 97)),
    /** length is 30 if zero bit != 0, else 28 */
    COMMENT(b -> range(fromString(), 97, b[125] == 0 ? 125 : 127).parse(b)),
    TRACK(b -> b[125] == 0 ? range(v -> (int) v[0], 126, 127).parse(b) : -1),
    GENRE(b -> b[127] <= 0 ? null : Genre.values()[b[127] - 1]);

    public final Id3FieldParser parser;

    static Id3FieldParser range(Id3FieldParser fn, int from, int to) {
      return b -> fn.parse(Arrays.copyOfRange(b, from, to));
    }

    static Id3FieldParser fromString() {
      return bytes -> new String(bytes).replaceAll("\\x00", "");
    }

    Id3V1FieldMeta(Id3FieldParser parser) {
      this.parser = parser;
    }

    @Override
    public Id3FieldParser getParser() {
      return parser;
    }
  }
}
