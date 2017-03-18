package hackingismakingisengineering.com.languagepronunciationstudy.animation;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import hackingismakingisengineering.com.languagepronunciationstudy.R;

public class WrongRightWordAnimationActivity extends AppCompatActivity {

    private Button wrongButton;
    private Button rightButton;

    private TextView exampleTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_right);


        exampleTextView = (TextView)findViewById(R.id.animation_right_wrong_textView);
        wrongButton = (Button) findViewById(R.id.animation_right_wrong_button_wrong);
        rightButton = (Button)findViewById(R.id.animation_right_wrong_button_right);

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateRight();
            }
        });

        wrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateWrong();
            }
        });

    }

    private void animateRight() {
        Animation rightAnimation = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.right_animation);

        ObjectAnimator colorAnim = ObjectAnimator.ofInt(exampleTextView, "textColor",
                getResources().getColor(R.color.text_colour), getResources().getColor(R.color.text_colour_right));
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.start();

        exampleTextView.startAnimation(rightAnimation);

    }

    private void animateWrong() {

        Animation wrongAnimation = AnimationUtils.loadAnimation(this.getApplicationContext(), R.anim.wrong_animation);

//        ObjectAnimator colorAnim = ObjectAnimator.ofInt(exampleTextView, "textColor",
//                R.color.text_colour, R.color.text_colour_wrong);
//        colorAnim.setEvaluator(new ArgbEvaluator());
//        colorAnim.start();

        ObjectAnimator colorAnim = ObjectAnimator.ofInt(exampleTextView, "textColor",
                getResources().getColor(R.color.text_colour), getResources().getColor(R.color.text_colour_wrong));
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.start();
        //exampleTextView.setTransi
        exampleTextView.startAnimation(wrongAnimation);
    }
}
