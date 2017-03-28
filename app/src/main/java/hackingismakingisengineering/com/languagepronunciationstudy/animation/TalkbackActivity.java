package hackingismakingisengineering.com.languagepronunciationstudy.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import hackingismakingisengineering.com.languagepronunciationstudy.R;
import hackingismakingisengineering.com.languagepronunciationstudy.ttsandstt.Speech;

public class TalkbackActivity extends AppCompatActivity {

    private Button nextEnglish;
    private Button nextFrench;
    private TextView textView;
    private String[] frenchStrings;
    private String[] englishStrings;

    private int frenchStringInd=0;
    private int englishStringInd=0;

    // Speech helper class
    private Speech speech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talkback);

        nextEnglish = (Button) findViewById(R.id.talkback_button_english);
        nextFrench = (Button) findViewById(R.id.talkback_button_french);
        textView = (TextView) findViewById(R.id.talkback_textView);


        frenchStrings = getResources().getStringArray(R.array.chat_array_french);
        englishStrings = getResources().getStringArray(R.array.chat_array_english);

        speech = new Speech(this);

        nextEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                speech.allow(true);
                speech.setLanguage(Locale.ENGLISH);
                speech.speak(englishStrings[englishStringInd]);
                textView.setText(englishStrings[englishStringInd]);
                englishStringInd++;

            }
        });

        nextFrench.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                speech.allow(true);
                speech.setLanguage(Locale.FRENCH);
                speech.speak(frenchStrings[frenchStringInd]);
                textView.setText(frenchStrings[frenchStringInd]);
                frenchStringInd++;

            }
        });
    }
}
