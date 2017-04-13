package com.framgia.forder.screen.mainpagetemp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.framgia.forder.R;
import com.framgia.forder.screen.mainpagetemp.mainpage.MainPageContainerFragment;

/**
 * Created by Duong on 4/13/2017.
 */

public class MainPageTempActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_temp);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_content, MainPageContainerFragment.newInstance())
                .commit();
        getSupportActionBar().setElevation(0);
    }
}
