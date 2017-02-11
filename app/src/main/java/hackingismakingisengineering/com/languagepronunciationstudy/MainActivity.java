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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import hackingismakingisengineering.com.languagepronunciationstudy.api.GoogleTranslateApi;
import hackingismakingisengineering.com.languagepronunciationstudy.database.DictionaryReader;
import hackingismakingisengineering.com.languagepronunciationstudy.settings.ApplicationSettings;
import hackingismakingisengineering.com.languagepronunciationstudy.settings.SupportedLanguages;
import hackingismakingisengineering.com.languagepronunciationstudy.ttsandstt.Speech;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int TTS_CHECK_CODE = 202;
    private TextView translationTextView;
    private EditText editTextWordEntry;

    private Button speakText;
    private Button recordText;

    private static final int REQ_CODE_SPEECH_INPUT = 201;

    private Speech speech;

    private OkHttpClient okHttpClient;


    private boolean initialised =false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        translationTextView = (TextView)findViewById(R.id.textView_translation);
        editTextWordEntry = (EditText) findViewById(R.id.editText_word);

        speakText = (Button) findViewById(R.id.button_play);
        recordText = (Button) findViewById(R.id.button_record);


        // Initialise the Text to speech object

        //checkTTS();

        ApplicationSettings.initaliseApplicationSettings(getApplicationContext());
        speech = new Speech(getApplicationContext());


        if(!initialised){
            DictionaryReader.initialise(getApplicationContext());
        }




        okHttpClient = new OkHttpClient();

        speakText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //speech.speak(editTextWordEntry.getText().toString());
                //tts.speak("test", TextToSpeech.QUEUE_ADD, null, null);
                speech.allow(true);
                //speech.speak("test");
                speech.speak(editTextWordEntry.getText().toString());
                //Toast.makeText(getApplicationContext(),"test",Toast.LENGTH_SHORT).show();
            }
        });

        recordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                promptSpeechInput();

            }
        });

        editTextWordEntry.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            //Log.i(TAG, "edit text actioned");
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {



                                // the user is done typing.
                                translateText();

                                return true; // consume.

                        }
                        return false; // pass on to other listeners.
                    }
                });

        }

    private void parseCallbackResponse(String jsonData){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonData);
            JSONObject jsonObjectData = jsonObject.getJSONObject("data");
            JSONArray jsonObjectDataTranslations = jsonObjectData.getJSONArray("translations");
            String jsonObjectDataTranslationsTranslatedText = jsonObjectDataTranslations.getJSONObject(0).getString("translatedText");

            Log.d(TAG, jsonObjectDataTranslationsTranslatedText);
            translationTextView.setText(jsonObjectDataTranslationsTranslatedText);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void translateText() {
        String untranslatedWord = String.valueOf(editTextWordEntry.getText());

        Request request = new Request.Builder()
                .url(GoogleTranslateApi.COMPLETE+untranslatedWord)
                .build();

        Call call = okHttpClient.newCall(request);

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


                        parseCallbackResponse(jsonData);

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
        //Locale locale = ApplicationSettings.getTargetLanguage();
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



    private void checkTTS(){
        Intent check = new Intent();
        check.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(check, TTS_CHECK_CODE);
    }

}
