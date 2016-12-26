package hackingismakingisengineering.com.languagepronunciationstudy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hackingismakingisengineering.com.languagepronunciationstudy.TTSandSTT.TextAndSpeech;

public class MainActivity extends AppCompatActivity {

    public static Context baseContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button_play);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextAndSpeech.speak("Example only");
            }
        });


    }
}
