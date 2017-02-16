package hackingismakingisengineering.com.languagepronunciationstudy;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import hackingismakingisengineering.com.languagepronunciationstudy.api.GoogleTranslateApi;
import hackingismakingisengineering.com.languagepronunciationstudy.database.DictionaryReader;
import hackingismakingisengineering.com.languagepronunciationstudy.database.WordsDatabaseHelper;
import hackingismakingisengineering.com.languagepronunciationstudy.model.Word;
import hackingismakingisengineering.com.languagepronunciationstudy.settings.ApplicationSettings;
import hackingismakingisengineering.com.languagepronunciationstudy.settings.SupportedLanguages;
import hackingismakingisengineering.com.languagepronunciationstudy.ttsandstt.Speech;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    // Constants
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int TTS_CHECK_CODE = 202;
    private static final int REQ_CODE_SPEECH_INPUT = 201;


    // Applicaiton state
    private boolean initialised =false;

    // UI components
    private TextView translationTextView;
    private EditText editTextWordEntry;
    private TextView ipaTextView;


    private Button speakText;
    private Button recordText;
    private Button nextWord;

    // Speech helper class
    private Speech speech;

    // Google translate client
    private OkHttpClient okHttpGoogleTranslateClient;

    // Database and query
    private WordsDatabaseHelper wordsDatabaseHelper;
    private Dao<Word, Long> wordDao;
    private Where<Word, Long> results;
    private Iterator iterator;

    // Current Word model
    private Word currentWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise the application settings oject - use, native language, target language
        ApplicationSettings.initaliseApplicationSettings(getApplicationContext());

        // Initialise UI views
        translationTextView = (TextView)findViewById(R.id.textView_translation);
        editTextWordEntry = (EditText) findViewById(R.id.editText_word);
        ipaTextView = (TextView) findViewById(R.id.textView_ipa);

        speakText = (Button) findViewById(R.id.button_play);
        recordText = (Button) findViewById(R.id.button_record);
        nextWord = (Button) findViewById(R.id.next_button);

        // Initialise the Text to speech object
        // TODO: Refactor so that the check is independent of the initialisation
        checkTTSandInit();

        // Initialise client for translation calls.
        okHttpGoogleTranslateClient = new OkHttpClient();


        ApplicationSettings.setFirstRun(false);
        // Code is only required to setup database in the first time
        if(ApplicationSettings.isFirstRun()){

            runDictionaryReader();
            ApplicationSettings.setFirstRun(false);
        }

        // Initialise the database items
        try {
            initialiseDatabaseHelperDao();
            filterDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        // UI - Listeners
        speakText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                speech.allow(true);
                speech.speak(editTextWordEntry.getText().toString());
            }
        });

        recordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                promptSpeechInput();;
            }
        });

        // Edit text fetches a translation when word entered.
        editTextWordEntry.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            //Log.i(TAG, "edit text actioned");
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                                // the user is done typing.
                                Log.d(TAG, "Completed editing edit text");
                                translateText();

                                return true; // consume.
                        }
                        return false; // pass on to other listeners.
                    }
                });

        nextWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadNextWord();
            }
        });
    }

    private void loadNextWord() {

        currentWord = (Word) iterator.next();
        translateText();
        updateUI();

    }

    private void updateUI() {

        editTextWordEntry.setText(currentWord.getWordText());
        ipaTextView.setText(currentWord.getWordIPA());

    }

    //TODO: Refactor so part of the GoogleTranslateApi class.
    private String parseCallbackResponse(String jsonData){

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");

            JSONArray jsonObjectDataTranslations = jsonObjectData.getJSONArray("translations");

            String jsonObjectDataTranslationsTranslatedText = jsonObjectDataTranslations
                    .getJSONObject(0)
                    .getString("translatedText");

            Log.d(TAG, jsonObjectDataTranslationsTranslatedText);

            return jsonObjectDataTranslationsTranslatedText;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void translateText() {
        //String untranslatedWord = String.valueOf(editTextWordEntry.getText());



        Request request = new Request.Builder()
                .url(GoogleTranslateApi.COMPLETE+currentWord.getWordText())
                .build();

        Call call = okHttpGoogleTranslateClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if(response.isSuccessful()){
                        Log.v(TAG, "Response successful");

                        String jsonData = response.body().string();
                        Log.v(TAG, jsonData);
                        currentWord.setWordTranslation(parseCallbackResponse(jsonData));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                translationTextView.setText(currentWord.getWordTranslation());
                                updateUI();
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Log.d(TAG, "Main UI code running");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speech.destroy();
    }

    //http://www.androidhive.info/2014/07/android-speech-to-text-tutorial/
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        // get the language from application settings
        List<SupportedLanguages> languages = Arrays.asList(SupportedLanguages.values());

        Locale locale = Locale.FRENCH;

        for(SupportedLanguages languge: languages){
            if(ApplicationSettings.getTargetLanguage().equals(languge.getStringLanguage())){
                locale = languge.getLocale();
            }
        }

        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, locale.toLanguageTag());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Speak now");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Speech not supported", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Toast.makeText(getApplicationContext(), result.get(0), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case TTS_CHECK_CODE: {
                if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {

                    speech = new Speech(this);
                } else {
                    Intent install = new Intent();
                    install.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                    startActivity(install);
                }
            }
        }
    }


    private void initialiseDatabaseHelperDao() throws SQLException{

        wordsDatabaseHelper = OpenHelperManager.getHelper(this,WordsDatabaseHelper.class);

        wordDao = wordsDatabaseHelper.getDao();
    }

    private void filterDataBase() throws SQLException {
        results = wordDao.queryBuilder().where().isNotNull("wordIPA");
        iterator = results.iterator();
    }

    private void logNextWord() {
        Word word = (Word) iterator.next();
        String output = word.toString();
        Log.d(TAG, output);
    }

    private void checkTTSandInit(){
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(check, TTS_CHECK_CODE);
    }

    public void runDictionaryReader() {

        DictionaryReader dr = new DictionaryReader();
        dr.initialise(getApplicationContext());
    }

}
