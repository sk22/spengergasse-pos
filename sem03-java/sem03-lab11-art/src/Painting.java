import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Samuel Kaiser
 * @since 1/9/2017
 */
public class Painting extends Artwork {
  private static final int PRICE_PER_SQUARE_METER = 30;
  private static final int ADDITIONAL_MINIMUM_METERS = 3;
  private static final int ADDITIONAL_COSTS = 20;
  private Dimensions dimensions;
  private Technique technique;

  /**
   * Constructs a Painting
   *
   * @param title      Artwork's title, null if unnamed
   * @param artist     Artist's name, null if unknown
   * @param date       Date of Creation
   * @param dimensions Defines the painting's dimensions
   * @param technique  Technique the painting was created with, null if unknown
   */
  public Painting(
    String title,
    String artist,
    LocalDate date,
    Dimensions dimensions,
    Technique technique
  ) {
    super(title, artist, date);
    setDimensions(dimensions);
    setTechnique(technique);
  }

  public Dimensions getDimensions() {
    return dimensions;
  }

  public void setDimensions(Dimensions dimensions) {
    if (dimensions == null) {
      throw new IllegalArgumentException("Painting must have dimensions");
    }
    this.dimensions = dimensions;
  }

  public Technique getTechnique() {
    return technique;
  }

  public void setTechnique(Technique technique) {
    this.technique = technique;
  }

  @Override
  public float transportCosts() {
    // 100 cm = 1 m
    Dimensions meters = dimensions.divide(100);
    final float squareMeters = meters.width * meters.height;
    final float costs = PRICE_PER_SQUARE_METER * squareMeters;
    final boolean add = meters.width > ADDITIONAL_MINIMUM_METERS
      || meters.height > ADDITIONAL_MINIMUM_METERS;
    return costs + (add ? ADDITIONAL_COSTS : 0);
  }

  @Override
  String getAttributesRepresentation() {
    return String.format("dimensions: %s, technique: %s",
      dimensions, technique);
  }

  public static final class Dimensions implements Serializable {
    public final float width;
    public final float height;

    public Dimensions(float width, float height) {
      if (!(width > 0 && height > 0)) {
        throw new IllegalArgumentException("Width and height must be greater " +
          "than 0");
      }
      this.width = width;
      this.height = height;
    }

    public Dimensions divide(int dividend) {
      return new Dimensions(width / dividend, height / dividend);
    }

    @Override
    public String toString() {
      return String.format("%sx%s", width, height);
    }
  }

  public enum Technique {
    OIL, ACRYL;
  }
}
