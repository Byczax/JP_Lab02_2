package lab02.datastorage;

import java.util.List;

public class UserData {
    private final List<PreferencePair> preferences;
    private final int maxPreference;
    private final int minPreference;

    public UserData(List<PreferencePair> preferences) {
        this.preferences = preferences;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (var preference : preferences) {
            if (preference.getValue() > max)
                max = preference.getValue();
            if (preference.getValue() < min)
                min = preference.getValue();
        }
        maxPreference = max;
        minPreference = min;
    }

    public List<PreferencePair> getPreferences() {
        return preferences;
    }

    public int getMaxPreference() {
        return maxPreference;
    }

    public int getMinPreference() {
        return minPreference;
    }

}
