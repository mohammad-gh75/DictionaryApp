package org.maktab36.dictionaryapp.controller.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import org.maktab36.dictionaryapp.R;
import org.maktab36.dictionaryapp.controller.fragment.WordListFragment;

public class WordsActivity extends AppCompatActivity {

    public static Intent newIntent(Context context,String language) {
        Intent intent = new Intent(context, WordsActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_words);

        FragmentManager fragmentManager=getSupportFragmentManager();

        Fragment fragment=fragmentManager.findFragmentById(R.id.constraint_layout_fragment_container);

        if(fragment==null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.constraint_layout_fragment_container, WordListFragment.newInstance())
                    .commit();
        }
    }
}