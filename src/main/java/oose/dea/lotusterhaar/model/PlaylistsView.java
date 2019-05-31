package oose.dea.lotusterhaar.model;

import javax.persistence.*;

@Entity
public class PlaylistsView {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String owner;

    @Column(nullable = false)
    private String username;


    public PlaylistsView(String name, String owner, String username) {
        this.name = name;
        this.owner = owner;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    public String getUsername() {
        return username;
    }
}
