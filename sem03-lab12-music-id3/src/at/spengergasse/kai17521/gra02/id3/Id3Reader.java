package at.spengergasse.kai17521.gra02.id3;

import java.io.*;

/**
 * @author Samuel Kaiser
 * @since 1/16/2017
 */
public abstract class Id3Reader {
  InputStream input;
  File file;
  byte[] bytes;

  /**
   * Creates a new Id3Reader that parses the file's ID3 tag to an {@link Id3Tag}
   * object
   * @param file File containing the ID3 tag
   * @throws IOException File's bytes could not be read properly
   * @throws InvalidId3TagException ID3v1 tag didn't have the correct length of
   *                                128 bytes
   */
  public Id3Reader(File file) throws IOException, InvalidId3TagException {
    this.file = file;
    input = new FileInputStream(file);
  }


  /**
   * Reads the ID3 tag's bytes
   * @throws IOException File's bytes could not be read properly
   * @throws InvalidId3TagException ID3v1 tag didn't have the correct length of
   *                                128 bytes
   */
  public abstract byte[] read() throws IOException, InvalidId3TagException;

  /**
   * Reads a file's ID3v1 tag bytes
   * @author Samuel Kaiser
   * @since 1/18/2017
   */
  public static class V1 extends Id3Reader {
    public V1(File file) throws IOException, InvalidId3TagException {
      super(file);
    }

    @Override
    public byte[] read() throws IOException, InvalidId3TagException {
      long lengthToSkip = file.length() - 128;
      if (input.skip(lengthToSkip) != lengthToSkip) {
        throw new InvalidId3TagException("File too short for ID3v1 tag");
      }
      bytes = new byte[128];
      if (input.read(bytes) < 128) {
        throw new InvalidId3TagException("ID3v1 tag didn't have 128 bytes");
      }
      return bytes;
    }
  }
}
