package lab02.datastorage;

public class MusicList {
    private final int index;
    private final String genre;
    private final int length;
    private int positiveSatisfaction;
    private int negativeSatisfaction;
    private int playCountForPositive;
    private int playCountForNegative;

    public MusicList(int index, String genre, int length) {
        this.index = index;
        this.genre = genre;
        this.length = length;
        playCountForPositive = 0;
        playCountForNegative = 0;
    }

    public int getIndex() {
        return index;
    }

    public String getGenre() {
        return genre;
    }

    public int getLength() {
        return length;
    }

    public int getPositiveSatisfaction() {
        return positiveSatisfaction;
    }

    public void setPositiveSatisfaction(int positiveSatisfaction) {
        this.positiveSatisfaction = positiveSatisfaction;
    }

    public int getNegativeSatisfaction() {
        return negativeSatisfaction;
    }

    public void setNegativeSatisfaction(int negativeSatisfaction) {
        this.negativeSatisfaction = negativeSatisfaction;
    }

    public int getPlayCountForPositive() {
        return playCountForPositive;
    }

    public void setPlayCountForPositive(int playCountForPositive) {
        this.playCountForPositive = playCountForPositive;
    }

    public int getPlayCountForNegative() {
        return playCountForNegative;
    }

    public void setPlayCountForNegative(int playCountForNegative) {
        this.playCountForNegative = playCountForNegative;
    }
}
