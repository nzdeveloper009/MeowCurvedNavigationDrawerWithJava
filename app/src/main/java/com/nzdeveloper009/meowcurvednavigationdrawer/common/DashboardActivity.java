package com.nzdeveloper009.meowcurvednavigationdrawer.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.nzdeveloper009.meowcurvednavigationdrawer.R;
import com.nzdeveloper009.meowcurvednavigationdrawer.fragment.AboutFragment;
import com.nzdeveloper009.meowcurvednavigationdrawer.fragment.HistoryFragment;
import com.nzdeveloper009.meowcurvednavigationdrawer.fragment.ProfileFragment;
import com.nzdeveloper009.meowcurvednavigationdrawer.fragment.TradeFragment;

public class DashboardActivity extends AppCompatActivity {

    MeowBottomNavigation navigation;
    private final static int ABOUT_ID = 1;
    private final static int TRADE_ID = 2;
    private final static int HISTORY_ID = 3;
    private final static int PROFILE_ID = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        if(Build.VERSION.SDK_INT>=21)
        {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        navigation = findViewById(R.id.bottom_navigation);
        navigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_about));
        navigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_trade_2));
        navigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_history_2));
        navigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_profile));

        getSupportFragmentManager().beginTransaction().replace(R.id.root_container,new AboutFragment()).commit();

        navigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //These listener are required here
            }
        });

        navigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //These listener are required here
            }
        });

        navigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragmentSelected = null;
                switch (item.getId())
                {
                    case ABOUT_ID:
                        fragmentSelected = new AboutFragment();
                        break;
                    case TRADE_ID:
                        fragmentSelected = new TradeFragment();
                        break;
                    case HISTORY_ID:
                        fragmentSelected = new HistoryFragment();
                        break;
                    case PROFILE_ID:
                        fragmentSelected = new ProfileFragment();
                        break;
                }
                assert fragmentSelected != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.root_container,fragmentSelected).commit();
            }
        });

    }
}