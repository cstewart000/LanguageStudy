package hackingismakingisengineering.com.languagepronunciationstudy.database;

/**
 * Created by helloworld on 13/2/17.
 */
public class SingleSven {
    private static SingleSven ourInstance = new SingleSven();

    public static SingleSven getInstance() {
        return ourInstance;
    }

    private SingleSven() {
    }
}
