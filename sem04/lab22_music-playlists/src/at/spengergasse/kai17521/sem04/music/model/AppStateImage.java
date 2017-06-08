package at.spengergasse.kai17521.sem04.music.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Samuel Kaiser
 * @since 5/12/2017
 */
public class AppStateImage implements Serializable {
  public final List<PlaylistImage> playlists;
  public final List<File> library;

  public AppStateImage(AppState state) {
    this.playlists = new ArrayList<>(
      state.getPlaylists().stream().map(PlaylistImage::new).collect(toList())
    );
    this.library = new ArrayList<>(
      state.getLibrary().stream().map(Song::getFile).collect(toList())
    );
  }

  public class PlaylistImage implements Serializable {
    public final List<File> songs;
    public final String name;

    private PlaylistImage(Playlist playlist) {
      this.songs = new ArrayList<>(
        playlist.getSongs().stream().map(Song::getFile).collect(toList())
      );
      this.name = playlist.getName();
    }
  }
}
