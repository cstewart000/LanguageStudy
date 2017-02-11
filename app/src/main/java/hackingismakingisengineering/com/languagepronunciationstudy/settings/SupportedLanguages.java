package hackingismakingisengineering.com.languagepronunciationstudy.settings;

import java.util.Locale;

/**
 * Created by helloworld on 11/2/17.
 */
public enum SupportedLanguages {

    FRENCH ("FRENCH", Locale.FRENCH),
    ENGLISH ("ENGLISH", Locale.ENGLISH);

    private final String stringLanguage;
    private final Locale locale;


    SupportedLanguages(String stringLanguage, Locale locale) {

        this.stringLanguage = stringLanguage;
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getStringLanguage() {
        return stringLanguage;
    }


}
