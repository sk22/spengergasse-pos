package at.spengergasse.kai17521.id3.fields;
/**
 * Provides genres for ID3 tags
 * @author Samuel Kaiser
 * @since 09/20/2016
 */
public enum Genre {
  BLUES("Blues"),
  CLASSIC_ROCK("Classic Rock"),
  COUNTRY("Country"),
  DANCE("Dance"),
  FUNK("Funk"),
  GRUNGE("Grunge"),
  HIP_HOP("Hip-Hop"),
  JAZZ("Jazz"),
  METAL("Metal"),
  NEW_AGE("New Age"),
  OLDIES("Oldies"),
  OTHER("Other"),
  POP("Pop"),
  RNB("R&B"),
  RAP("Rap"),
  REGGAE("Reggae"),
  ROCK("Rock"),
  TECHNO("Techno"),
  INDUSTRIAL("Industrial"),
  ALTERNATIVE("Alternative"),
  SKA("Ska"),
  DEATH_METAL("Death Metal"),
  PRANKS("Pranks"),
  SOUNDTRACK("Soundtrack"),
  EURO_TECHNO("Euro-Techno"),
  AMBIENT("Ambient"),
  TRIP_HOP("Trip-Hop"),
  VOCAL("Vocal"),
  JAZZ_FUNK("Jazz+Funk"),
  FUSION("Fusion"),
  TRANCE("Trance"),
  CLASSICAL("Classical"),
  INSTRUMENTAL("Instrumental"),
  ACID("Acid"),
  HOUSE("House"),
  GAME("Game"),
  SOUND_CLIP("Sound Clip"),
  GOSPEL("Gospel"),
  NOISE("Noise"),
  ALTERNROCK("AlternRock"),
  BASS("Bass"),
  SOUL("Soul"),
  PUNK("Punk"),
  SPACE("Space"),
  MEDITATIVE("Meditative"),
  INSTRUMENTAL_POP("Instrumental Pop"),
  INSTRUMENTAL_ROCK("Instrumental Rock"),
  ETHNIC("Ethnic"),
  GOTHIC("Gothic"),
  DARKWAVE("Darkwave"),
  TECHNO_INDUSTRIAL("Techno-Industrial"),
  ELECTRONIC("Electronic"),
  POP_FOLK("Pop-Folk"),
  EURODANCE("Eurodance"),
  DREAM("Dream"),
  SOUTHERN_ROCK("Southern Rock"),
  COMEDY("Comedy"),
  CULT("Cult"),
  GANGSTA("Gangsta"),
  TOP_40("Top 40"),
  CHRISTIAN_RAP("Christian Rap"),
  POP_FUNK("Pop/Funk"),
  JUNGLE("Jungle"),
  NATIVE_AMERICAN("Native American"),
  CABARET("Cabaret"),
  NEW_WAVE("New Wave"),
  PSYCHADELIC("Psychadelic"),
  RAVE("Rave"),
  SHOWTUNES("Showtunes"),
  TRAILER("Trailer"),
  LO_FI("Lo-Fi"),
  TRIBAL("Tribal"),
  ACID_PUNK("Acid Punk"),
  ACID_JAZZ("Acid Jazz"),
  POLKA("Polka"),
  RETRO("Retro"),
  MUSICAL("Musical"),
  ROCK_N_ROLL("Rock & Roll"),
  HARD_ROCK("Hard Rock");

  private final String name;

  Genre(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
