package org.maktab36.dictionaryapp.controller.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;

import org.maktab36.dictionaryapp.controller.fragment.FirstPageFragment;

public class FirstPageActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context){
        Intent intent=new Intent(context,FirstPageActivity.class);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return FirstPageFragment.newInstance();
    }
}