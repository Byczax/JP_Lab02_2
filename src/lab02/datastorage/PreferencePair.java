package lab02.datastorage;

public class PreferencePair {
    private final String genre;
    private final int value;

    public PreferencePair(String genre, int value) {
        this.genre = genre;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getGenre() {
        return genre;
    }
}
