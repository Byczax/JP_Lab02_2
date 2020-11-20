package lab02;

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
        int concertFullLength = ReadData.readDataFromUser("Enter the value in minutes - how long the concert should last? (at least 10 minutes)");
        concertFullLength *=60; // change to seconds
        int musicLengthSum = SolutionGenerator.musicSumLength(musicList);
        int concertLengthLeft = concertFullLength % musicLengthSum;
        int concertAdditionalLoop = concertFullLength / musicLengthSum;

        SatisfactionCounter.satisfactionCounterForEachSong(musicList, userData);
        // Positive satisfaction
        try {
            SolutionGenerator.knapsackPositive(concertLengthLeft, musicList);
            SolutionGenerator.printingListPositive(musicList, concertAdditionalLoop);
            System.out.println("\n" + SatisfactionCounter.earnedSatisfaction(musicList, true));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        // Negative satisfaction
        try {
            SolutionGenerator.knapsackNegative(concertLengthLeft, musicList);
            SolutionGenerator.printingListNegative(musicList, concertAdditionalLoop);
            System.out.println("\n" + SatisfactionCounter.earnedSatisfaction(musicList, false));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
//        for (MusicList myList : musicList){
//            System.out.println(myList.getIndex()+";"+myList.getPositiveSatisfaction()+";"+myList.getNegativeSatisfaction());
//        }
    }
}
