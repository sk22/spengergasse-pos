import java.time.LocalDate;

/**
 * @author Samuel Kaiser
 * @since 1/9/2017
 */
public class Sculpture extends Artwork {
  public static final int COSTS_PER_KILOGRAM = 2;
  
  private Material material;
  private int volume;
  private int weight;

  /**
   * Constructs a Sculpture
   *
   * @param title  Artwork's title, null if unnamed
   * @param artist Artist's name, null if unknown
   * @param date   Date of Creation
   */
  public Sculpture(
    String title,
    String artist,
    LocalDate date,
    Material material,
    int volume,
    int weight
  ) {
    super(title, artist, date);
    setMaterial(material);
    setVolume(volume);
    setWeight(weight);
  }

  public Material getMaterial() {
    return material;
  }

  public void setMaterial(Material material) {
    this.material = material;
  }

  public int getVolume() {
    return volume;
  }

  public void setVolume(int volume) {
    if (volume < 0) {
      throw new IllegalArgumentException("Volume must not be less than 0");
    }
    this.volume = volume;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  @Override
  public float transportCosts() {
    final float costs = COSTS_PER_KILOGRAM * weight;
    return material.fragile ? costs * 1.05f : costs;
  }

  @Override
  String getAttributesRepresentation() {
    return String.format("material: %s, volume: %d, weight: %d",
      material, volume, weight);
  }

  public enum Material {
    STONE(false), WOOD(false), PLASTER(true), GLASS(true);

    public final boolean fragile;

    private Material(boolean fragile) {
      this.fragile = fragile;
    }
  }
}
