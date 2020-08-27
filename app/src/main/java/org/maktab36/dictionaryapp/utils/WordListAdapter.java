package org.maktab36.dictionaryapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.maktab36.dictionaryapp.R;
import org.maktab36.dictionaryapp.controller.fragment.WordDetailFragment;
import org.maktab36.dictionaryapp.model.Word;

import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordHolder> {
    private List<Word> mWords;
    private FragmentManager mFragmentManager;

    public void setWords(List<Word> words) {
        mWords = words;
    }

    public WordListAdapter(List<Word> wordList, FragmentManager fragmentManager) {
        mWords=wordList;
        mFragmentManager=fragmentManager;
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
        private TextView mTextViewWordText;
        private TextView mTextViewWordTranslate;
        private Word mWord;

        //TODO: add row to holder
        public WordHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewWordText=itemView.findViewById(R.id.text_view_word_text);
            mTextViewWordTranslate=itemView.findViewById(R.id.text_view_word_translate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.constraint_layout_fragment_container,
                                    WordDetailFragment.newInstance(mWord.getId()))
                            .commit();
                }
            });
        }

        public void bindWord(Word word){
            mWord=word;
            mTextViewWordText.setText(word.getEnglish());
            mTextViewWordTranslate.setText(word.getPersian());
        }
    }
}
