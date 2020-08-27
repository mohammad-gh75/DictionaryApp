package org.maktab36.dictionaryapp.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;

import org.maktab36.dictionaryapp.R;
import org.maktab36.dictionaryapp.model.Word;
import org.maktab36.dictionaryapp.repository.WordRepository;

public class AddWordDialogFragment extends DialogFragment {
    private EditText mEditTextWordEnglish;
    private EditText mEditTextWordPersian;
    private EditText mEditTextWordArabic;
    private EditText mEditTextWordFrench;
    private Button mButtonAdd;
    private Button mButtonCancel;

    public AddWordDialogFragment() {
        // Required empty public constructor
    }

    public static AddWordDialogFragment newInstance() {
        AddWordDialogFragment fragment = new AddWordDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_word_dialog, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    private void findViews(View view) {
        mEditTextWordEnglish=view.findViewById(R.id.edit_text_word_english);
        mEditTextWordPersian=view.findViewById(R.id.edit_text_word_persian);
        mEditTextWordArabic=view.findViewById(R.id.edit_text_word_arabic);
        mEditTextWordFrench=view.findViewById(R.id.edit_text_word_french);
        mButtonAdd=view.findViewById(R.id.button_add);
        mButtonCancel=view.findViewById(R.id.button_cancel);
    }
    private void setListeners(){
        mButtonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word=getUI();
                WordRepository.getInstance(getActivity()).insert(word);
                WordListFragment wordListFragment= (WordListFragment) getTargetFragment();
                wordListFragment.UpdateUI();
                dismiss();
            }
        });

        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private Word getUI(){
        String english=mEditTextWordEnglish.getText().toString();
        String persian=mEditTextWordPersian.getText().toString();
        String arabic=mEditTextWordArabic.getText().toString();
        String french=mEditTextWordFrench.getText().toString();
        return new Word(english,persian,arabic,french);
    }
}