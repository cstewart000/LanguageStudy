package hackingismakingisengineering.com.languagepronunciationstudy.Model;

import java.io.Serializable;

/**
 * Created by helloworld on 11/10/2015.
 */
public class Word implements Serializable {


    //member variables
    private long mId;
    private String mWord;
    private boolean mPron_bool;
    private boolean mVocab_bool;
    private boolean mDistinc_bool;
    private boolean mGender_bool;
    private boolean mConj_bool;
    private float mLast_score;
    private int mTries;
    private float mAve_score;



    // GET METHODS
    public long getId() {
        return mId;
    }
    public boolean getPron_bool(){return mPron_bool;}
    public boolean getVocab_bool(){return mVocab_bool;}
    public boolean getDistinc_bool(){return mDistinc_bool;}
    public boolean getGender_bool(){return mGender_bool;}
    public boolean getConj_bool(){return mConj_bool;}

    public String getWord() {return mWord;}

    public float getlast_score(){return this.mLast_score;}
    public float getTries(){return this.mTries;}
    public float getAveScore(){return this.mAve_score;}

    //SET METHODS
    public void setId(long id) {this.mId = id;}
    public void setWord(String word) {this.mWord = word;}
    public void setPron_bool(boolean pronBool){this.mPron_bool = pronBool;}
    public void setVocab_bool(boolean vocabBool){this.mVocab_bool = vocabBool;}
    public void setDistinc_bool(boolean distincBool){this.mDistinc_bool = distincBool;}
    public void setGender_bool(boolean genderBool){this.mGender_bool = genderBool;}
    public void setConj_bool(boolean conjBool){this.mConj_bool = conjBool;}

    // Has been saved is used to see if the record has been given a SQL number
    public boolean hasBeenSaved(){
        return (getId()!=-1);
    }

    //MODIFICATION METHODS
    public void changeLastScore(float score) {this.mLast_score = score;}
    public void incrementTries() {this.mTries++;}
    public void reviseAverage() {
        float newAveScore = (this.mAve_score * (this.mTries - 1) + this.mLast_score) / this.mTries;
        this.mAve_score = newAveScore;
    }

    // Null constructor
    public Word(){

    }



    // Full Constructor
    public Word( long mId, String mWord,boolean mPron_bool, boolean mVocab_bool, boolean mDistinc_bool, boolean mGender_bool, boolean mConj_bool, float mLast_score, int mTries, float mAve_score) {
        this.mAve_score = mAve_score;
        this.mConj_bool = mConj_bool;
        this.mDistinc_bool = mDistinc_bool;
        this.mGender_bool = mGender_bool;
        this.mId = mId;
        this.mLast_score = mLast_score;
        this.mPron_bool = mPron_bool;
        this.mTries = mTries;
        this.mVocab_bool = mVocab_bool;
        this.mWord = mWord;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {

        String toStringReturn = ("ID: "+ Long.toString(mId) +"\n"
                + "word: " + mWord + "\n"
                + "Pronunciation: " + Boolean.toString(mPron_bool) + "\n"
                + "Vocabulary: " + Boolean.toString(mVocab_bool) + "\n"
                + "Gender: " + Boolean.toString(mGender_bool) + "\n"
                + "Distinction: " + Boolean.toString(mDistinc_bool) + "\n"
                + "Conjugation: " + Boolean.toString(mConj_bool) + "\n"
                + "Score: " + Float.toString(mLast_score) + "\n"
                + "Tries: " + Integer.toString(mTries) + "\n"
                + "Average Score: " + Float.toString(mAve_score) + "\n"

        );

        return toStringReturn;
    }
}


