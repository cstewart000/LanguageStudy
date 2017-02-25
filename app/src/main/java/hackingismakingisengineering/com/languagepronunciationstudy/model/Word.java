package hackingismakingisengineering.com.languagepronunciationstudy.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.lang.reflect.Field;
import java.util.Comparator;


/**
 * Created by helloworld on 5/2/17.
 */

@DatabaseTable(tableName = "dictionary")
public class Word implements Comparable<Word>{

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String wordText;

    @DatabaseField
    private String wordIPA;

    @DatabaseField
    private String wordTranslation;

    @DatabaseField
    private double aveScore;

    @DatabaseField
    private int tries;

    @DatabaseField
    private float frequency;
    private int lastScore;


    public Word(double aveScore, float frequency, int id, int tries, String wordIPA, String wordText, String wordTranslation) {
        this.aveScore = aveScore;
        this.frequency = frequency;
        this.id = id;
        this.tries = tries;
        this.wordIPA = wordIPA;
        this.wordText = wordText;
        this.wordTranslation = wordTranslation;
    }

    public Word(String wordText) {
        this.wordText = wordText;
    }

    public Word() {
    }

    @Override
    public String toString() {
        return "Word{" +
                "aveScore=" + aveScore +
                ", id=" + id +
                ", wordText='" + wordText + '\'' +
                ", wordIPA='" + wordIPA + '\'' +
                ", wordTranslation='" + wordTranslation + '\'' +
                ", tries=" + tries +
                ", frequency=" + frequency +
                '}';
    }



    @SuppressWarnings("unchecked")
    public static <T> T mergeObjects(T first, T second) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = first.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Object returnValue = clazz.newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value1 = field.get(first);
            Object value2 = field.get(second);
            Object value = (value1 != null) ? value1 : value2;
            field.set(returnValue, value);
        }
        return (T) returnValue;
    }


    public double getAveScore() {
        return aveScore;
    }

    public void setAveScore(double aveScore) {
        this.aveScore = aveScore;
    }

    public float getFrequency() {
        return frequency;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public static Comparator<Word> getWordComparator() {
        return WordComparator;
    }

    public static void setWordComparator(Comparator<Word> wordComparator) {
        WordComparator = wordComparator;
    }

    public String getWordIPA() {
        return wordIPA;
    }

    public void setWordIPA(String wordIPA) {
        this.wordIPA = wordIPA;
    }

    public String getWordText() {
        return wordText;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public void setWordTranslation(String wordTranslation) {
        this.wordTranslation = wordTranslation;
    }

    @Override
    public int compareTo(Word word) {
        return this.getWordText().compareTo(word.getWordText());
    }

    public static Comparator<Word> WordComparator = new Comparator<Word>() {
        @Override
        public int compare(Word word, Word t1) {
            return word.getWordText().compareTo(t1.getWordText());
        }
    };


    public void incrementTries() {
        this.tries++;
    }

    public void score(int score) {
        
        this.lastScore = score;

        double newCummulativeScore = aveScore * tries +score;
        incrementTries();

        aveScore = newCummulativeScore/tries;

    }
}
