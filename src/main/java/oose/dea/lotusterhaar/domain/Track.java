package oose.dea.lotusterhaar.domain;

public class Track {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private int playcount;
    private boolean offlineAvailable;

    public Track() {
    }

    public Track(int id, String title, String performer, int duration, int playcount, boolean offlineAvailable) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.playcount = playcount;
        this.offlineAvailable = offlineAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public boolean isOfflineAvailable() {
        return offlineAvailable;
    }

    public void setOfflineAvailable(boolean offlineAvailable) {
        this.offlineAvailable = offlineAvailable;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", performer='" + performer + '\'' +
                ", duration=" + duration +
                ", playcount=" + playcount +
                ", offlineAvailable=" + offlineAvailable +
                '}';
    }
}
