package hackingismakingisengineering.com.languagepronunciationstudy.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by helloworld on 17/2/17.
 */

public class WordScorer {

    List<Character> characterArrayList;


    int difficultyScore;


    public int getDifficultyScore(String ipaWord) {


        Character[] charObjectArray = toCharacterObjectArray(ipaWord);
        int[] scores = new int[charObjectArray.length];
        int score = 0;

        for (int i = 0; FrenchPhoneme.values().length < i; i++) {
            for (int j = 0; scores.length < j; j++) {

                if (FrenchPhoneme.values()[i].getSymbol().equals(charObjectArray[j])) {

                    scores[j] = FrenchPhoneme.values()[i].getDifficulty();
                    score =+ scores[j];
                }
            }
        }
        return score;

    }

    public Character[] toCharacterObjectArray(String s) {

        if(s == null){
            return null;
        }

        Character[] chararray = new Character[s.length()];

        for(int i=0; i<s.length(); i++){
            chararray[i] = new Character(s.charAt(i));

        }

        return chararray;

    }

    public List<Character> toListCharacterArray(String s){

        List<Character> charcterList = Arrays.asList(toCharacterObjectArray(s));

        return charcterList;
    }

    public String characterListToString(List<Character> characterList){

        String str ="";

        for(Character c: characterList){
            str += c.toString();
        }

        return str;
    }

    public List<Character>  reportMissingPhonemes(String target, String resultsConcatenated){

        List<Character> missingPhonemes = new ArrayList<>();

        List<Character> targetPhonemes = toListCharacterArray(target);
        List<Character> resultPhonemes = toListCharacterArray(resultsConcatenated);

        for(Character c: targetPhonemes){
            if(!resultPhonemes.contains(c)){
                missingPhonemes.add(c);
            }
        }
        return missingPhonemes;

    }

    public List<Character>  reportCommonPhonemes(String target, String resultsConcatenated){

        List<Character> commonPhonemes = new ArrayList<>();

        List<Character> targetPhonemes = toListCharacterArray(target);
        List<Character> resultPhonemes = toListCharacterArray(resultsConcatenated);

        for(Character c: targetPhonemes){
            if(resultPhonemes.contains(c)){
                commonPhonemes.add(c);
            }
        }
        return commonPhonemes;

    }
}
