package org.maktab36.dictionaryapp.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.maktab36.dictionaryapp.R;
import org.maktab36.dictionaryapp.model.Word;
import org.maktab36.dictionaryapp.repository.WordRepository;
import org.maktab36.dictionaryapp.utils.WordListAdapter;

import java.util.List;

public class WordListFragment extends Fragment {
//    private static final String ARG_LANGUAGE = "language";
    public static final int REQUEST_CODE_ADD_WORD = 0;
    public static final String TAG_WORD = "word";
//    private String mLanguage;
    private List<Word> mWordList;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private FloatingActionButton mFABAdd;

    public WordListFragment() {
        // Required empty public constructor
    }

    public static WordListFragment newInstance(/*String language*/) {
        WordListFragment fragment = new WordListFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_LANGUAGE,language);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mLanguage=getArguments().getString(ARG_LANGUAGE,"english");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_word_list, container, false);
        findViews(view);
        setListeners();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        return view;
    }


    private void findViews(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view_words);
        mFABAdd = view.findViewById(R.id.floating_action_button_add_word);
    }

    private void setListeners() {
        mFABAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddWordDialogFragment addWordDialogFragment = AddWordDialogFragment.newInstance();
                addWordDialogFragment.setTargetFragment(WordListFragment.this, REQUEST_CODE_ADD_WORD);
                addWordDialogFragment.show(getFragmentManager(), TAG_WORD);
            }
        });
    }

    public void UpdateUI() {
        mWordList = WordRepository.getInstance(getActivity()).getWords();
        if (mAdapter == null) {
            mAdapter = new WordListAdapter(mWordList,getActivity().getSupportFragmentManager());
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setWords(mWordList);
            mAdapter.notifyDataSetChanged();
        }
    }
}