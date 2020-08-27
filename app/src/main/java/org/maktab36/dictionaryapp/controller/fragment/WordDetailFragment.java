package org.maktab36.dictionaryapp.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.maktab36.dictionaryapp.R;
import org.maktab36.dictionaryapp.model.Word;
import org.maktab36.dictionaryapp.repository.WordRepository;

import java.util.UUID;

public class WordDetailFragment extends Fragment {
    private static final String ARG_WORD_ID = "wordId";
    private Word mCurrentWord;
    private TextView mTextViewWordText;
    private TextView mTextViewWordTranslate;
    private TextView mTextViewWordArabic;
    private TextView mTextViewWordFrench;
    private WordRepository mRepository;

    public WordDetailFragment() {
        // Required empty public constructor
    }


    public static WordDetailFragment newInstance(UUID wordId) {
        WordDetailFragment fragment = new WordDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_WORD_ID,wordId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepository=WordRepository.getInstance(getActivity());
        UUID wordId= (UUID) getArguments().getSerializable(ARG_WORD_ID);
        mCurrentWord= mRepository.get(wordId);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_word_detail, container, false);
        findViews(view);
        setUI();
        return view;
    }

    private void findViews(View view) {
        mTextViewWordText =view.findViewById(R.id.text_view_word_text);
        mTextViewWordTranslate =view.findViewById(R.id.text_view_word_translate);
        mTextViewWordArabic =view.findViewById(R.id.text_view_word_arabic_translate);
        mTextViewWordFrench =view.findViewById(R.id.text_view_word_french_translate);
    }

    private void setUI(){
        mTextViewWordText.setText(mCurrentWord.getEnglish());
        mTextViewWordTranslate.setText(mCurrentWord.getPersian());
        mTextViewWordArabic.setText(mCurrentWord.getArabic());
        mTextViewWordFrench.setText(mCurrentWord.getFrench());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.word_detail_menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_back:
                goToWordListFragment();
                return true;
            case R.id.menu_item_edit_word:
                //TODO: implement edit word method.
                return true;
            case R.id.menu_item_delete_word:
                deleteWord();
                goToWordListFragment();
                return true;
            case R.id.menu_item_share_word:
                shareWord();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareWord() {
        Intent sendIntent= ShareCompat
                .IntentBuilder
                .from(getActivity())
                .setText(getShareText())
                .setSubject(getString(R.string.word_share_subject))
                .setType("text/plain")
                .getIntent();

        Intent shareIntent = Intent.createChooser(sendIntent, null);

        if (shareIntent.resolveActivity(getActivity().getPackageManager()) != null)
            startActivity(shareIntent);
    }

    private void deleteWord() {
        mRepository.delete(mCurrentWord);
        for (Fragment fragment:getActivity().getSupportFragmentManager().getFragments()) {
            if(fragment instanceof WordListFragment){
                ((WordListFragment) fragment).UpdateUI();
            }
        }
    }

    private void goToWordListFragment() {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_layout_fragment_container, WordListFragment.newInstance())
                .commit();
    }

    private String getShareText(){
        StringBuilder temp=new StringBuilder();
        temp.append("English: ").append(mCurrentWord.getEnglish());
        temp.append("Persian: ").append(mCurrentWord.getPersian());
        temp.append("Arabic: ").append(mCurrentWord.getArabic());
        temp.append("French: ").append(mCurrentWord.getFrench());
        return temp.toString();
    }

}