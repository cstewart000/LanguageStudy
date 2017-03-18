package hackingismakingisengineering.com.languagepronunciationstudy.model;

import java.util.List;

/**
 * Created by Cam on 10/03/2017.
 */

public class Session {

    private List<Word> words;
    private int scorePercentage;
    private Feedback feedback;

    private int sessionLength;

    private int progress;


    public Session(int sessionLength) {
        this.sessionLength = sessionLength;
    }

    public Session(List<Word> words) {
        this.words = words;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public int getScorePercentage() {
        return scorePercentage;
    }

    public void setScorePercentage(int scorePercentage) {
        this.scorePercentage = scorePercentage;
    }

    public Feedback getFeedback() {
        return feedback;
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }

    public int getSessionLength() {
        return sessionLength;
    }

    public void setSessionLength(int sessionLength) {
        this.sessionLength = sessionLength;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
