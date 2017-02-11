package hackingismakingisengineering.com.languagepronunciationstudy.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * Created by helloworld on 11/2/17.
 */

public class ApplicationSettings {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;


    public static final String NATIVE_LANGUAGE_KEY = "NATIVE_LANGUAGE_KEY";
    public static final String TARGET_LANGUAGE_KEY = "TARGET_LANGUAGE_KEY";

    private static String nativeLanguage;
    private static String targetLanguage;


    public static void initaliseApplicationSettings(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        editor = sharedPreferences.edit();
    }

    public static String getNativeLanguage() {
        return sharedPreferences.getString(NATIVE_LANGUAGE_KEY, SupportedLanguages.ENGLISH.getStringLanguage());
    }

    public static void setNativeLanguage(String language) {
        nativeLanguage = language;
        editor.putString(NATIVE_LANGUAGE_KEY, language);
    }

    public static String getTargetLanguage() {
        return sharedPreferences.getString(TARGET_LANGUAGE_KEY, SupportedLanguages.FRENCH.getStringLanguage());
    }

    public static void setTargetLanguage(String language) {
        targetLanguage = language;
        editor.putString(NATIVE_LANGUAGE_KEY, language);
    }
}
