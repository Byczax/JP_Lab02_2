package lab02.knapsacksatisfactionsolution;

import java.util.ArrayList;

import lab02.datastorage.MusicList;
import lab02.datastorage.UserData;

public class SatisfactionCounter {

    public static void satisfactionCounterForEachSong(ArrayList<MusicList> musicList, ArrayList<UserData> userData) {
        String genre;

        for (MusicList musicList1 : musicList) {
            int sumPreferencePositive = 0;
            int sumPreferenceNegative = 0;
            genre = musicList1.getGenre();
            for (UserData userData1 : userData) {
                int actualPreference = 0;
                for (var preferences : userData1.getPreferences()
                ) {
                    if (preferences.getGenre().equals(genre)) {
                        actualPreference = preferences.getValue();
                        break;
                    }
                }
                sumPreferencePositive += (actualPreference - userData1.getMinPreference());
                sumPreferenceNegative += (userData1.getMaxPreference() - actualPreference);
            }
            musicList1.setPositiveSatisfaction(sumPreferencePositive);
            musicList1.setNegativeSatisfaction(sumPreferenceNegative);
        }
    }

    public static float earnedSatisfaction(ArrayList<MusicList> musicList, boolean positive) {
        float sumSatisfaction = 0;
        if (positive) {
            for (MusicList musicList1 : musicList) {
                sumSatisfaction += musicList1.getPositiveSatisfaction() * musicList1.getPlayCountForPositive() / 100.0;
            }
        } else {
            for (MusicList musicList1 : musicList) {
                sumSatisfaction += musicList1.getNegativeSatisfaction() * musicList1.getPlayCountForNegative() / 100.0;
            }
        }
        return sumSatisfaction;
    }
}
