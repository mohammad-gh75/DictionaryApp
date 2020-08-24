package org.maktab36.dictionaryapp.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.maktab36.dictionaryapp.R;
import org.maktab36.dictionaryapp.model.Word;
import org.maktab36.dictionaryapp.utils.WordListAdapter;

import java.util.ArrayList;
import java.util.List;

public class WordListFragment extends Fragment {
    private static final String ARG_LANGUAGE="language";
    private String mLanguage;
    private List<Word> mWordList;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    public WordListFragment() {
        // Required empty public constructor
    }

    public static WordListFragment newInstance(String language) {
        WordListFragment fragment = new WordListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LANGUAGE,language);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLanguage=getArguments().getString(ARG_LANGUAGE,"english");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_word_list, container, false);
        findViews(view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        return view;
    }


    private void findViews(View view) {
        mRecyclerView=view.findViewById(R.id.recycler_view_words);
    }

    private void UpdateUI() {
        mWordList=new ArrayList<>();
        if(mAdapter==null){
            mAdapter=new WordListAdapter(mWordList);
            mRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.setWords(mWordList);
            mAdapter.notifyDataSetChanged();
        }
    }
}