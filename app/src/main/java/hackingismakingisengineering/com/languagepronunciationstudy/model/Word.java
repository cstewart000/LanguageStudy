package hackingismakingisengineering.com.languagepronunciationstudy.model;

import java.lang.reflect.Field;
import java.util.Comparator;

/**
 * Created by helloworld on 5/2/17.
 */

public class Word implements Comparable<Word>{
    
    private boolean pron_bool;
    private boolean vocab_bool;
    private boolean gender_bool;
    private boolean distinc_bool;
    private String wordText;
    private String wordIPA;
    private String wordTranslation;
    private double aveScore;
    private int tries;
    private boolean conj_bool;
    private float frequency;
    private String attributes;

    public String getAttributes() {
        return attributes;
    }

    public float getFrequency() {
        return frequency;
    }

    public boolean isConj_bool() {
        return conj_bool;
    }

    public boolean isDistinc_bool() {
        return distinc_bool;
    }

    public boolean isGender_bool() {
        return gender_bool;
    }

    public boolean isPron_bool() {
        return pron_bool;
    }

    public boolean isVocab_bool() {
        return vocab_bool;
    }

    public String getWordIPA() {
        return wordIPA;
    }

    public String getWordText() {
        return wordText;
    }

    public String getWordTranslation() {
        return wordTranslation;
    }

    public void setAveScore(double aveScore) {

        this.aveScore = aveScore;
    }

    public void setConj_bool(boolean conj_bool) {
        this.conj_bool = conj_bool;
    }

    public void setDistinc_bool(boolean distinc_bool) {
        this.distinc_bool = distinc_bool;
    }

    public void setGender_bool(boolean gender_bool) {
        this.gender_bool = gender_bool;
    }

    public void setPron_bool(boolean pron_bool) {
        this.pron_bool = pron_bool;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public void setVocab_bool(boolean vocab_bool) {
        this.vocab_bool = vocab_bool;
    }

    public void setWordIPA(String wordIPA) {
        this.wordIPA = wordIPA;
    }

    public void setWordText(String wordText) {
        this.wordText = wordText;
    }

    public void setWordTranslation(String wordTranslation) {
        this.wordTranslation = wordTranslation;
    }

    public boolean getPron_bool() {
        return pron_bool;
    }

    public boolean getVocab_bool() {
        return vocab_bool;
    }

    public boolean getGender_bool() {
        return gender_bool;
    }

    public boolean getDistinc_bool() {
        return distinc_bool;
    }

    public boolean getConj_bool() {
        return conj_bool;
    }

    public String getWord() {
        return wordText;
    }

    public double getAveScore() {
        return aveScore;
    }

    public int getTries() {
        return tries;
    }

    public void setFrequency(float frequency) {
        this.frequency = frequency;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
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

    @Override
    public int compareTo(Word word) {
        return this.getWord().compareTo(word.getWord());
    }

    public static Comparator<Word> WordComparator = new Comparator<Word>() {
        @Override
        public int compare(Word word, Word t1) {
            return word.getWord().compareTo(t1.getWord());
        }
    };



}
