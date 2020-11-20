package lab02.knapsacksatisfactionsolution;

import java.util.ArrayList;

import lab02.datastorage.MusicList;

public class SolutionGenerator {

    public static void knapsackPositive(int concertLength, ArrayList<MusicList> musicList) {
        int[] songLengths = musicList.stream().mapToInt(MusicList::getLength).toArray();
        int howManySongs = songLengths.length;

        var lowestPositivePopularityOption = musicList.stream().mapToInt(MusicList::getPositiveSatisfaction).min();
        if (lowestPositivePopularityOption.isEmpty())
            throw new RuntimeException("Unexpected empty lowestPositivePopularityOption");
        int lowestPositivePopularity = lowestPositivePopularityOption.getAsInt();

        int[] songPopularity;
        if (lowestPositivePopularity < 0) {
            songPopularity = musicList.stream().mapToInt(s -> s.getPositiveSatisfaction() - lowestPositivePopularity).toArray();
        } else {
            songPopularity = musicList.stream().mapToInt(MusicList::getPositiveSatisfaction).toArray();
        }
        int[][] weight = new int[howManySongs + 1][concertLength + 1];
        int[] index = musicList.stream().mapToInt(MusicList::getIndex).toArray();
        for (int i = 0; i <= howManySongs; i++) {
            for (int j = 0; j <= concertLength; j++) {
                if (i == 0 || j == 0) {
                    weight[i][j] = 0;
                } else if (songLengths[i - 1] <= j) {
                    weight[i][j] = Math.max(songPopularity[i - 1] + weight[i - 1][j - songLengths[i - 1]], weight[i - 1][j]);
                } else {
                    weight[i][j] = weight[i - 1][j];
                }
            }
        }
        long popularityResult = weight[howManySongs][concertLength];
        int concertLengthTemp = concertLength;
        for (int i = howManySongs; i > 0 && popularityResult > 0; i--) {
            if (popularityResult != weight[i - 1][concertLengthTemp]) {
                musicList.get(index[i - 1] - 1).setPlayCountForPositive(musicList.get(index[i - 1] - 1).getPlayCountForPositive() + 1);
                popularityResult = popularityResult - songPopularity[i - 1];
                concertLengthTemp = concertLengthTemp - songLengths[i - 1];
            }
        }
    }

    public static void knapsackNegative(int concertLength, ArrayList<MusicList> musicList) {
        int[] songLengths = musicList.stream().mapToInt(MusicList::getLength).toArray();
        int howManySongs = songLengths.length;

        var highestNegativePopularityOption = musicList.stream().mapToInt(MusicList::getNegativeSatisfaction).max();
        if (highestNegativePopularityOption.isEmpty())
            throw new RuntimeException("Unexpected empty lowestPositivePopularityOption");
        var highestNegativePopularity = highestNegativePopularityOption.getAsInt();

        int[] songPopularity;
        songPopularity = musicList.stream().mapToInt(s -> ((-s.getNegativeSatisfaction()) + highestNegativePopularity)).toArray();

        int[][] weight = new int[howManySongs + 1][concertLength + 1];
        int[] index = musicList.stream().mapToInt(MusicList::getIndex).toArray();
        for (int i = 0; i <= howManySongs; i++) {
            for (int j = 0; j <= concertLength; j++) {
                if (i == 0 || j == 0) {
                    weight[i][j] = 0;
                } else if (songLengths[i - 1] <= j) {
                    weight[i][j] = Math.max(songPopularity[i - 1] + weight[i - 1][j - songLengths[i - 1]], weight[i - 1][j]);
                } else {
                    weight[i-1][j] = weight[i][j];
                }
            }
        }
        long popularityResult = weight[howManySongs][concertLength];
        int concertLengthTemp = concertLength;
        for (int i = howManySongs; i > 0 && popularityResult > 0; i--) {
            if (popularityResult != weight[i - 1][concertLengthTemp]) {
                musicList.get(index[i - 1] - 1).setPlayCountForNegative(musicList.get(index[i - 1] - 1).getPlayCountForNegative() + 1);
                popularityResult = popularityResult - songPopularity[i - 1];
                concertLengthTemp = concertLengthTemp - songLengths[i - 1];
            }
        }
    }


    public static void printingListPositive(ArrayList<MusicList> musicList, int concertAdditionalLoop) {
        System.out.println("\nSongs that have been played (index from list) to get maximal positive satisfaction:");
        for (MusicList listOfAllMusic : musicList) {
            if (listOfAllMusic.getPlayCountForPositive() + concertAdditionalLoop > 0)
                System.out.print(listOfAllMusic.getIndex() + "(x" + (listOfAllMusic.getPlayCountForPositive() + concertAdditionalLoop) + ") ");
        }
    }

    public static void printingListNegative(ArrayList<MusicList> musicList, int concertAdditionalLoop) {
        System.out.println("\nSongs that have been played (index from list) to get minimal negative satisfaction:");
        for (MusicList listOfAllMusic : musicList) {
            if (listOfAllMusic.getPlayCountForNegative() + concertAdditionalLoop > 0)
                System.out.print(listOfAllMusic.getIndex() + "(x" + (listOfAllMusic.getPlayCountForNegative() + concertAdditionalLoop) + ") ");
        }
    }

    public static int musicSumLength(ArrayList<MusicList> musicList) {
        int lengthSum = 0;
        for (MusicList listOfAllMusic : musicList) {
            lengthSum += listOfAllMusic.getLength();
        }
        return lengthSum;
    }

}
