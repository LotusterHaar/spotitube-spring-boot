package oose.dea.lotusterhaar.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean owner;

    private ArrayList<Track> tracks;
    private int length;

    public Playlist() {super(); }

    public Playlist(int id, String name, boolean owner, ArrayList<Track> tracks) {
        super();
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
