package at.spengergasse.kai17521.id3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Samuel Kaiser
 * @since 1/16/2017
 */
public class Id3Tag extends TreeSet<Id3Field> {
  public Id3Tag() {}
  public Id3Tag(List<Id3Field> fields) {
    addAll(fields);
  }

  public static Id3Tag parse(Id3Version version, byte[] bytes) {
    return new Id3Tag(Arrays.stream(version.getFieldMeta())
      .map(m -> new Id3Field(m, bytes))
      .collect(Collectors.toList()));
  }

  public Id3Field get(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must not be null");
    }
    return stream()
      .filter(f -> f.getFieldMeta().name().equalsIgnoreCase(name))
      .findAny().orElse(null);
  }

  public Id3Field get(Id3FieldMeta layout) {
    return stream().filter(f -> f.getFieldMeta() == layout)
      .findAny().orElse(null);
  }

  @Override
  public String toString() {
    return String.join(", ",
      stream()
        .filter(f -> f.getValue() != null && !f.getValue().toString().isEmpty())
        .map(Id3Field::toString).collect(Collectors.toList()));
  }
}
