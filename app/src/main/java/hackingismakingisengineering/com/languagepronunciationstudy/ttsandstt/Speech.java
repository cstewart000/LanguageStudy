package hackingismakingisengineering.com.languagepronunciationstudy.ttsandstt;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;

import hackingismakingisengineering.com.languagepronunciationstudy.R;

import static android.app.Activity.RESULT_OK;

/**
 * Created by helloworld on 11/2/17.
 *
 *
 * Code followed from code.tutsplus.com
 */






public class Speech implements TextToSpeech.OnInitListener {


    private TextToSpeech textToSpeech;

    private boolean ready = false;
    private boolean allowed = false;

    public Speech(Context context){
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

        if (status == TextToSpeech.SUCCESS) {
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


}
