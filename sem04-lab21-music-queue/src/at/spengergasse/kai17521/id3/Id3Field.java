package at.spengergasse.kai17521.id3;

import java.io.Serializable;

/**
 * @author Samuel Kaiser
 * @since 1/16/2017
 */
public class Id3Field implements Comparable<Id3Field>, Serializable {
  private Id3FieldMeta fieldMeta;
  private Object value;

  public Id3Field(Id3FieldMeta fieldMeta, Object value) {
    this.fieldMeta = fieldMeta;
    this.value = value;
  }

  public Id3Field(Id3FieldMeta fieldMeta, byte[] bytes) {
    this.fieldMeta = fieldMeta;
    try {
      this.value = fieldMeta.getParser().parse(bytes);
    } catch (ArrayIndexOutOfBoundsException e) {
      this.value = null;
    }
  }

  public Id3FieldMeta getFieldMeta() {
    return fieldMeta;
  }

  public Object getValue() {
    return value;
  }

  @Override
  public String toString() {
    return getFieldMeta() + ": " + getValue();
  }

  @Override
  public int compareTo(Id3Field o) {
    return fieldMeta.ordinal() - o.fieldMeta.ordinal();
  }
}
