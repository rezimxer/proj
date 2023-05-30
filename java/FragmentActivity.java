package com.apps.frontendsiswa.fitursiswa.rekomendasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.apps.frontendsiswa.MenuActivity;
import com.apps.frontendsiswa.R;

import com.apps.frontendsiswa.fitursiswa.rekomendasi.notif.NotifActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;


public class FragmentActivity extends AppCompatActivity {
    private String KEY_NAMELATITUDE = "AMBILLATITUDE";
    private String KEY_NAMELONGITUDE = "AMBILLONGITUDE";
    public static String ambilLatitude, ambilLongitude;

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        Bundle extras = getIntent().getExtras();
        ambilLatitude = extras.getString(KEY_NAMELATITUDE);
        ambilLongitude = extras.getString(KEY_NAMELONGITUDE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FragmentActivity.this, MenuActivity.class));
                finish();
            }
        });





        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
//        progressBar.setVisibility(View.GONE);


    }


 

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    //Membuat pilihan menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.action_notif)
        {
            startActivity(new Intent(FragmentActivity.this, NotifActivity.class));
        }

        return true;
    }



    @Override
    public void onBackPressed() {
                            Intent intent = new Intent(FragmentActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
    }



}
