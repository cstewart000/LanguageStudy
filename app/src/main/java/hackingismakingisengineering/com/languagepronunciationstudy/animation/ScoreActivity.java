package hackingismakingisengineering.com.languagepronunciationstudy.animation;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import hackingismakingisengineering.com.languagepronunciationstudy.R;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

public class ScoreActivity extends AppCompatActivity {

    private PieChartView chart;
    private PieChartData data;

    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasCenterCircle = false;
    private boolean hasCenterText1 = false;
    private boolean hasCenterText2 = false;
    private boolean isExploded = false;
    private boolean hasLabelForSelected = false;

    private Button upButton;
    private Button downButton;

    private List<SliceValue> values = new ArrayList<SliceValue>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        chart = (PieChartView) findViewById(R.id.chart);
        //chart.setOnValueTouchListener(new ValueTouchListener());

        generateData();

        upButton = (Button) findViewById(R.id.score_up_button);
        downButton = (Button) findViewById(R.id.score_down_button);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int right = (int)values.get(0).getValue();
                right++;

                values.get(0).setValue(right);
                updateDataDisply();
            }
        });


        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int right = (int)values.get(0).getValue();
                right--;

                values.get(0).setValue(right);
                updateDataDisply();

            }
        });
    }

    private void updateDataDisply() {
        data = new PieChartData(values);
        chart.setPieChartData(data);
    }

    private void generateData() {

        SliceValue sliceValue1 = new SliceValue(20, ChartUtils.pickColor());
        SliceValue sliceValue2 = new SliceValue(20, ChartUtils.pickColor());


        values.add(sliceValue1);
        values.add(sliceValue2);

        data = new PieChartData(values);
        /*

        data.setHasLabels(hasLabels);
        data.setHasLabelsOnlyForSelected(hasLabelForSelected);
        data.setHasLabelsOutside(hasLabelsOutside);
        data.setHasCenterCircle(hasCenterCircle);

        */


        chart.setPieChartData(data);
    }
}
