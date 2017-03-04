package hackingismakingisengineering.com.languagepronunciationstudy.database;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import hackingismakingisengineering.com.languagepronunciationstudy.R;
import hackingismakingisengineering.com.languagepronunciationstudy.model.Word;

/**
 * Created by helloworld on 11/2/17.
 */



public class DictionaryReader {


    private static long record = 0;
    private static final String TAG = DictionaryReader.class.getSimpleName();
    public static ArrayList<Word> dictionary= new ArrayList<>();

    private Context context;

    static WordsDatabaseHelper wordsDatabaseHelper;

    static Dao<Word, Long> wordDao;


    // write your code here
    public void initialise(Context con){
    //public static void main(String[] args) {

        context = con;





        wordsDatabaseHelper = OpenHelperManager.getHelper(context,WordsDatabaseHelper.class);


        try {
            wordDao = wordsDatabaseHelper.getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        readinDictionary(R.raw.french_dictionary_utf_16);
        readinDictionary(R.raw.fpd);

        Collections.sort(dictionary, Word.WordComparator);

        for(int i=0; i< dictionary.size()-1; i++){


            if(dictionary.get(i).compareTo(dictionary.get(i+1))==0){
                Word word2 = dictionary.remove(i+1);
                Word word1 = dictionary.remove(i);


                try {
                    word1 = Word.mergeObjects(word1,word2);
                    dictionary.add(i,word1);
                    //dictionary.add(word1);
                    //dictionary.remove(word2);
                    //dictionary.remove(word1);
                    //dictionary.add(word1);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

            }

        }




        Log.v(TAG, dictionary.get(123).toString());


        loadToDatabase();


    }

    private void loadToDatabase() {
        try {
        for(int i=0; i< dictionary.size()-1; i++) {


                wordDao.create(dictionary.get(i));
            Log.v(TAG, "record" + record++ + dictionary.get(i).toString());

         }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readinDictionary(int resourceId) {

        //Read in file,

        //final Resources resources = mainActivity.getResources();
        //
        //InputStream inputStream = resources.openRawResource(R.raw.fpd);

        InputStream inputStream;
        inputStream = context.getResources().openRawResource(resourceId);
        //inputStream = Resources.getSystem().openRawResource(resourceId);

        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-16")));

        switch (resourceId) {
            case R.raw.french_dictionary_utf_16:
                br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-16")));
                break;
            case R.raw.fpd:
                br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
                break;
        }
        //BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        try {
            //StringBuilder sb = new StringBuilder();
            //String line = br.readLine();
            String line = br.readLine();

            while (line!= null) {


                switch (resourceId) {
                    case R.raw.french_dictionary_utf_16:
                        parseLineCompactus(line);
                        break;
                    case R.raw.fpd:
                        parseLinePronunciation(line);
                        break;
                }

                line = br.readLine();
            }
            //String everything = sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void parseLineCompactus(String line) {

        // Trap null line(
        if(line ==null){
            return;
        }

        // Check if comment line
        if (line.charAt(0) != '#') {
            String[] parts = line.split("\t", 5);
            Word word = new Word();
            word.setWordText(parts[1]);
            //word.setAttributes(parts[3]);
            word.setFrequency(Float.parseFloat(parts[4]));

            dictionary.add(word);

            Log.v(TAG, "record" + record++ + word.toString());
        }

    }

    public static void parseLinePronunciation(String line) {

        // Trap null line(
        if(line ==null){
            return;
        }
        // Check if comment line
        if (line.charAt(0) != '#') {
            String[] parts = line.split("_",2);
            Word word = new Word();
            word.setWordText(parts[0]);
            word.setWordIPA(parts[1]);

            dictionary.add(word);
            Log.v(TAG, "record" + record++ +word.toString());

        }

    }
}