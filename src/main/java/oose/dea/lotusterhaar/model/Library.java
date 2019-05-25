package oose.dea.lotusterhaar.model;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();

    private int length;

    public Library() {super();}

    public Library(ArrayList<Playlist> playlists) {
        super();
        this.playlists = playlists;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setLength(int length) {
        this.length = length;
    }

}
