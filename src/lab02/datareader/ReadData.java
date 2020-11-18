package lab02.datareader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import lab02.datastorage.MusicList;
import lab02.datastorage.UserData;
import lab02.datastorage.PreferencePair;

public class ReadData {

    public static ArrayList<MusicList> readMusic(String fileName) {
        ArrayList<MusicList> musicListTemp = new ArrayList<>();
        try (Scanner data = new Scanner(new File(fileName))) {
            data.nextLine();
            while (data.hasNext()) {
                String row = data.next();
                String[] strData = row.split(";");
                int index = Integer.parseInt(strData[0]);
                String genre = strData[1];
                int time = Integer.parseInt(strData[2]);
                musicListTemp.add(new MusicList(index, genre, time));
            }
            return musicListTemp;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found.");
        }
        return musicListTemp;
    }

    public static ArrayList<UserData> readUsers(String fileName) {
        ArrayList<UserData> userDataList = new ArrayList<>();
        String genre;
        int preference;
        try (Scanner data = new Scanner(new File(fileName))) {
            data.nextLine();
            while (data.hasNext()) {
                String row = data.next();
                String[] strData = row.split("[;,|]");

                List<PreferencePair> preferencePairList = new LinkedList<>();
                for (int i = 1; i < strData.length; i += 2) {
                    genre = strData[i];
                    preference = Integer.parseInt(strData[i + 1]);
                    PreferencePair preferenceSingle = new PreferencePair(genre, preference);
                    preferencePairList.add(preferenceSingle);
                }
                UserData userTemp = new UserData(preferencePairList);
                userDataList.add(userTemp);
            }
            return userDataList;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found.");
        }
        return userDataList;
    }

    public static long readDataFromUser(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        long userValue = 0;
        boolean error;
        do {
            error = false;
            try {
                userValue = Long.parseLong(scanner.nextLine());
            } catch (Exception e) {
                error = true;
                System.out.println("Wrong value, enter your value again");
            }
        } while (error);
        return userValue;
    }
}
