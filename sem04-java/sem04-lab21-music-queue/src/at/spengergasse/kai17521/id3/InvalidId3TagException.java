package at.spengergasse.kai17521.id3;

/**
 * @author Samuel Kaiser
 * @since 1/16/2017
 */
public class InvalidId3TagException extends Exception {
  InvalidId3TagException(String message) {
    super(message);
  }
}
