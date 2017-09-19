package at.spengergasse.kai17521.sem03.lab10;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Samuel Kaiser
 * @since 12/12/2016
 */
public class Submission {
  private File[] files;
  private LocalDate date;

  public Submission(LocalDate date, File... files) {
    if (files == null || Arrays.stream(files).anyMatch(f -> f == null)) {
      throw new IllegalArgumentException("Files must not be null");
    }
    if (files.length < 1) {
      throw new IllegalArgumentException("At least one file must be passed");
    }
    Optional<File> nonexistent = Arrays.stream(files)
      .filter(f -> !f.exists())
      .findAny();
    if (nonexistent.isPresent()) {
      throw new IllegalArgumentException("All files must exist. " +
        "\"" + nonexistent.get().getName() + "\"" + " didn't. ");
    }
    if (date == null) {
      throw new IllegalArgumentException("Date must not be null");
    }
    this.files = files;
    this.date = date;
  }

  public File[] getFiles() {
    return files;
  }

  public LocalDate getDate() {
    return date;
  }
}
