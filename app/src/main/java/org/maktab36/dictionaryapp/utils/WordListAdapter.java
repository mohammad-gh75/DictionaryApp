package org.maktab36.dictionaryapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab36.dictionaryapp.R;
import org.maktab36.dictionaryapp.model.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordHolder> {
    private List<Word> mWords;

    public void setWords(List<Word> words) {
        mWords = words;
    }

    public WordListAdapter(List<Word> wordList) {
        mWords=wordList;
    }

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.list_row_word,parent,false);
        return new WordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordHolder holder, int position) {
        Word word=mWords.get(position);
        holder.bindWord(word);
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }


    public class WordHolder extends RecyclerView.ViewHolder {


        public WordHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindWord(Word word){

        }
    }
}
