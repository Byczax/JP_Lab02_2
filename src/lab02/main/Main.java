package lab02.main;

import lab02.datareader.ReadData;
import lab02.datastorage.MusicList;
import lab02.datastorage.UserData;
import lab02.knapsacksatisfactionsolution.SatisfactionCounter;
import lab02.knapsacksatisfactionsolution.SolutionGenerator;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<MusicList> musicList = ReadData.readMusic("musicList.txt");
        ArrayList<UserData> userData = ReadData.readUsers("attendantsList.txt");
        SatisfactionCounter.satisfactionCounterForEachSong(musicList, userData);

        long concertFullLength = ReadData.readDataFromUser("Enter the value in seconds - how long the concert should last?");
        int musicLengthSum = SolutionGenerator.musicSumLength(musicList);
        long concertLengthLeft = concertFullLength % musicLengthSum;
        long concertAdditionalLoop = concertFullLength / musicLengthSum;
        try {
            SolutionGenerator.knapsackPositive((int) concertLengthLeft, musicList);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        SolutionGenerator.printingListPositive(musicList, (int) concertAdditionalLoop);
        System.out.println("\n" + SatisfactionCounter.earnedSatisfaction(musicList, true));
        try {
            SolutionGenerator.knapsackNegative((int) concertLengthLeft, musicList);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        SolutionGenerator.printingListNegative(musicList, (int) concertAdditionalLoop);
        System.out.println("\n" + SatisfactionCounter.earnedSatisfaction(musicList, false));
    }
}
