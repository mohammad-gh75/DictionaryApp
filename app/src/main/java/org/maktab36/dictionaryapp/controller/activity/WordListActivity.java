package org.maktab36.dictionaryapp.controller.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import org.maktab36.dictionaryapp.controller.fragment.WordListFragment;

public class WordListActivity extends SingleFragmentActivity {
    private static final String EXTRA_LANGUAGE="org.maktab36.dictionaryapp.language";

    public static Intent newIntent(Context context,String language) {
        Intent intent = new Intent(context, WordListActivity.class);
        intent.putExtra(EXTRA_LANGUAGE,language);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        String language=getIntent().getStringExtra(EXTRA_LANGUAGE);
        return WordListFragment.newInstance(language);
    }
}