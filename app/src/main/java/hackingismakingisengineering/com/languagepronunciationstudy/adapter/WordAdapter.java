package hackingismakingisengineering.com.languagepronunciationstudy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hackingismakingisengineering.com.languagepronunciationstudy.model.Word;

/**
 * Created by helloworld on 5/2/17.
 */

public class WordAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<Word> wordArrayList;
    private ArrayAdapter<Word> wordArrayAdapter;

    public WordAdapter(Context context, ArrayList<Word> wordArrayList) {
        this.context = context;

        this.wordArrayList = wordArrayList;
    }

    @Override
    public int getCount() {
        return wordArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return wordArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {



        ViewHolder holder ;

        /* TODO: FIND R!!!!

        // if the convert view is null, there is no old view to use and we have to inflate a new one.
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.word_item, null);
            holder = new ViewHolder();
            holder.word_textView = (TextView) convertView.findViewById(R.id.wi_word_textView);
            holder.pron_textView = (TextView) convertView.findViewById(R.id.wi_pron_textView);
            holder.translation_textView= (TextView) convertView.findViewById(R.id.wi_translation_textView);
            holder.score_textView = (TextView) convertView.findViewById(R.id.wi_score_textView);
            holder.tries_textView = (TextView) convertView.findViewById(R.id.wi_tries_textView);
            holder.class_textView = (TextView) convertView.findViewById(R.id.wi_class_textView);

            convertView.setTag(holder);
        }else{

            // If the view is being recycled, this code is exectuted
            holder = (ViewHolder)convertView.getTag();

        }


        // Set the data
        Word word = wordArrayList.get(position);

        holder.word_textView.setText(word.getWord());

        //TODO - ADD Pronunciation and translation fields to Word class
        holder.pron_textView.setText(word.getWord());
        holder.translation_textView.setText(word.getVocab_bool()+"");
        holder.score_textView.setText(word.getAveScore()+"");
        holder.tries_textView.setText(word.getTries()+"");
        holder.class_textView.setText(getWordClassDisplay(word));
        */
        return convertView;
    }

    private String getWordClassDisplay(Word word) {
        String displayAttr ="";
        String b = "-/";

        /*
        displayAttr += (word.getPron_bool()) ? "p/" : b;
        displayAttr += (word.getVocab_bool()) ? "v/" : b;
        displayAttr += (word.getGender_bool()) ? "g/" : b;
        displayAttr += (word.getDistinc_bool()) ? "d/" : b;
        displayAttr += (word.getConj_bool()) ? "c/": b;
        */
        return  displayAttr;
    }

    private static class ViewHolder{

        TextView word_textView;
        TextView pron_textView;
        TextView translation_textView;
        TextView score_textView;
        TextView tries_textView;
        TextView class_textView;


    }
}