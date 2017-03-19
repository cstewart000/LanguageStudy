package hackingismakingisengineering.com.languagepronunciationstudy.ttsandstt;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.util.ArraySet;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

import hackingismakingisengineering.com.languagepronunciationstudy.MainActivity;
import hackingismakingisengineering.com.languagepronunciationstudy.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by helloworld on 11/2/17.
 *
 *
 * Code followed from code.tutsplus.com
 */






public class Speech implements TextToSpeech.OnInitListener {


    private static final String TAG = Speech.class.getSimpleName();
    private TextToSpeech textToSpeech;

    private boolean ready = false;
    private boolean allowed = false;
    private Context context;

    private ArrayList<Voice> availableVoices = new ArrayList<>();
    private ArrayList<Locale> availableLocales = new ArrayList<>();;

    public Speech(Context context){
        this.context = context;
        textToSpeech = new TextToSpeech(context, this);
    }

    public void allow(boolean allowed){
        this.allowed = allowed;
    }

    public boolean isAllowed() {
        return allowed;
    }



    @Override
    public void onInit(int status) {


        for(Voice voice: textToSpeech.getVoices()){

            //Log.v(TAG, voice.toString());
            //Log.v(TAG,voice.getFeatures().toString());

            if(!voice.getFeatures().contains("notInstalled")){

                if(!voice.isNetworkConnectionRequired()) {
                    availableVoices.add(voice);
                    availableLocales.add(voice.getLocale());
                }
            }
        }

        for(Voice voice: availableVoices) {

            Log.v(TAG, voice.toString());
            Log.v(TAG, voice.getFeatures().toString());
        }
            if (status == TextToSpeech.SUCCESS) {
            //textToSpeech.setVoice(voice)
            setLanguage(Locale.FRENCH);
            ready = true;
        } else {

            ready = false;
        }


    }

    public void speak(String text){
        if(ready && allowed){

            textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null,null);
        }

    }

    public void pause(long duration){

        textToSpeech.playSilentUtterance(duration,TextToSpeech.QUEUE_ADD, null);
    }

    public void destroy(){

        textToSpeech.shutdown();
    }

    public void setLanguage(Locale locale){
        textToSpeech.setLanguage(locale);

    }

    public void toggleLocale(){

        Random rand = new Random();

        int localeIndex = rand.nextInt(availableLocales.size());

        Locale locale = availableLocales.get(localeIndex);
        textToSpeech.setLanguage(locale);

        Toast.makeText(this.context, "new locale; " + locale.toString(), Toast.LENGTH_SHORT).show();

    }

    public void changeVoice(){

        Random rand = new Random();

        int voiceIndex = rand.nextInt(availableVoices.size());

        Voice voice = availableVoices.get(voiceIndex);
        textToSpeech.setVoice(voice);

        Toast.makeText(this.context, "new voice; " + voice.toString(), Toast.LENGTH_SHORT).show();


    }


}
