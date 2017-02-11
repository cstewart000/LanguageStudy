package hackingismakingisengineering.com.languagepronunciationstudy.api;

/**
 * Created by helloworld on 5/2/17.
 */

public interface GoogleTranslateApi{

    public static final String API_KEY = "AIzaSyAtmPSWcaHRdDbDAn4LB0YCrIDnn84oVaw";
    public static final String CLIENT_ID = "524599286618-5lt545n0l82i1hp3t6j4d49vb4rkcg5f.apps.googleusercontent.com";
    public static final String BASE_URL = "https://translation.googleapis.com/language/translate/v2?";
    public static final String PARAMS = "&source=en&target=fr&q=";

    public static final String COMPLETE = BASE_URL+"key="+API_KEY + PARAMS;





}
