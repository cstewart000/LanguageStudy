package hackingismakingisengineering.com.languagepronunciationstudy.TTSandSTT;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;


import hackingismakingisengineering.com.languagepronunciationstudy.MainActivity;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by helloworld on 11/10/2015.
 */
public class TextAndSpeech {


    static Context context= MainActivity.baseContext;



    private static TextToSpeech ttobj = new TextToSpeech(context, new TextToSpeech.OnInitListener() {


        @Override
        public void onInit(int status) {
        }
    }
    );




    public static void speak(String word) {
        ttobj.speak(word,TextToSpeech.QUEUE_ADD,null);


    }

    /*
    // Create the Text to Speech Oject
    TTS1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if(status != TextToSpeech.ERROR) {
                TTS1.setLanguage(Locale.US);
            }
        }
    });



    // Speech Activity

    /**
     * Showing google speech input dialog
     * */




    /**
     * Receiving speech input
     * */

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    Toast.makeText(getApplicationContext(),
                            result.get(0),
                            Toast.LENGTH_SHORT).show();

                }
                break;
            }

        }
    }

    */


}
