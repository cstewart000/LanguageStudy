package hackingismakingisengineering.com.languagepronunciationstudy.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by helloworld on 17/2/17.
 *
 * Helper class for scoring :-)
 */

public class WordScorer {


    public static int getDifficultyScore(String ipaWord) {


        Character[] charObjectArray = toCharacterObjectArray(ipaWord);
        int[] scores = new int[charObjectArray.length];
        int score = 0;

        for (int j = 0; j < scores.length; j++) {
            for (int i = 0; i < FrenchPhoneme.values().length; i++) {


                if (FrenchPhoneme.values()[i].getSymbol().equals(charObjectArray[j].toString())) {

                    scores[j] = FrenchPhoneme.values()[i].getDifficulty();
                    score += scores[j];
                }
            }
        }
        return score;

    }

    public static Character[] toCharacterObjectArray(String s) {

        if(s == null){
            return null;
        }

        Character[] chararray = new Character[s.length()];

        for(int i=0; i<s.length(); i++){
            chararray[i] = new Character(s.charAt(i));

        }

        return chararray;

    }

    public static List<Character> toListCharacterArray(String s){

        List<Character> charcterList = Arrays.asList(toCharacterObjectArray(s));

        return charcterList;
    }

    public static String characterListToString(List<Character> characterList){

        String str ="";

        for(Character c: characterList){
            str += c.toString();
        }

        return str;
    }

    public static List<Character>  reportMissingPhonemes(String target, String resultsConcatenated){

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

    public static List<Character>  reportCommonPhonemes(String target, String resultsConcatenated){

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
