package org.maktab36.dictionaryapp.controller.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.maktab36.dictionaryapp.R;
import org.maktab36.dictionaryapp.controller.activity.WordListActivity;


public class FirstPageFragment extends Fragment {
    private Button mButtonEnglish;
    private Button mButtonPersian;

    public FirstPageFragment() {
        // Required empty public constructor
    }
    public static FirstPageFragment newInstance() {
        FirstPageFragment fragment = new FirstPageFragment();
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
        View view=inflater.inflate(R.layout.fragment_first_page, container, false);
        findViews(view);
        setListeners();
        return view;
    }

    private void findViews(View view) {
        mButtonEnglish=view.findViewById(R.id.button_language_english);
        mButtonPersian=view.findViewById(R.id.button_language_persian);
    }

    private void setListeners() {
        mButtonEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startApp("english");
                getActivity().finish();
            }
        });

        mButtonPersian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startApp("persian");
                getActivity().finish();
            }
        });
    }

    private void startApp(String language){
        Intent intent= WordListActivity.newIntent(getActivity(),language);
        startActivity(intent);
    }
}