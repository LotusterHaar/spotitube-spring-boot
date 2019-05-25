package oose.dea.lotusterhaar.model;

public class LocalStorage {
    private static String token;

    public LocalStorage(String token) {
        this.token = token;
    }

    public static String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
