package hackingismakingisengineering.com.languagepronunciationstudy.animation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import hackingismakingisengineering.com.languagepronunciationstudy.R;
import hackingismakingisengineering.com.languagepronunciationstudy.transformers.DownTransformer;
import hackingismakingisengineering.com.languagepronunciationstudy.transformers.UpTransformer;

public class RightWrongSortingActivity extends AppCompatActivity {

    private Button wrongButton;
    private Button rightButton;

    //private TextView exampleTextView;

    private NonSwipableViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_right_wrong_sorting_base);

        mViewPager = (NonSwipableViewPager) findViewById(R.id.viewPager);
        mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));



        //exampleTextView = (TextView) findViewById(R.id.animation_right_wrong_textView);
        wrongButton = (Button) findViewById(R.id.animation_sorting_wrong_button);
        rightButton = (Button) findViewById(R.id.animation_sorting_right_button);

        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setPageTransformer(false, new UpTransformer());
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            }
        });

        wrongButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //animateWrong();
                mViewPager.setPageTransformer(false, new DownTransformer());
                mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        int pagei = position + 1;
                    }

                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
            }
        });




    }

    private void animateRight() {


    }

    private void animateWrong() {


    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(final FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(final int position) {
            return PageFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            //return Content.values().length;
            return 50;
        }
    }
}