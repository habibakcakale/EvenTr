package com.special;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;

import android.widget.TextView;

import com.special.core.HomeFragmentActivity;
import com.special.core.InTheatersListFragmentActivity;
import com.special.menu.ResideMenu;
import com.special.menu.ResideMenuItem;
import com.special.utils.Utilities;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ResideMenu resideMenu;
    private ResideMenuItem[] menuItems;

    private Utilities utilities;
    private TextView actionBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        utilities = new Utilities();
        actionBar = (TextView)findViewById(R.id.actionBar);
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); /// Giving all permissions to threads.
        StrictMode.setThreadPolicy(policy);

        setUpMenu();
    }

    private void setUpMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.menu_background);
        resideMenu.attachToActivity(this);
        resideMenu.setShadowVisible(true);
        resideMenu.setHeaderView(findViewById(R.id.actionbar));
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);

        menuItems = new ResideMenuItem[12];

        menuItems[0] = new ResideMenuItem(this, R.drawable.ic_home, "Giriş");
        menuItems[1] = new ResideMenuItem(this, R.drawable.reside_menu_movie, "Sinema");
        menuItems[2] = new ResideMenuItem(this, R.drawable.reside_menu_theatre, "Sahne Sanatları");
        menuItems[3] = new ResideMenuItem(this, R.drawable.reside_menu_conference, "Konferans");
        menuItems[4] = new ResideMenuItem(this, R.drawable.reside_menu_kongre, "Seminer");
        menuItems[5] = new ResideMenuItem(this, R.drawable.reside_menu_concert, "Konser");
        menuItems[6] = new ResideMenuItem(this, R.drawable.reside_menu_competition, "Yarışma");
        menuItems[7] = new ResideMenuItem(this, R.drawable.reside_menu_fair, "Fuar");
        menuItems[8] = new ResideMenuItem(this, R.drawable.reside_menu_kongre, "Kongre");
        menuItems[9] = new ResideMenuItem(this, R.drawable.reside_menu_exhibition, "Sergi");
        menuItems[10] = new ResideMenuItem(this, R.drawable.reside_menu_conference, "Eğitim");

        menuItems[0].setOnClickListener(this);
        menuItems[1].setOnClickListener(this);
        menuItems[2].setOnClickListener(this);
        menuItems[3].setOnClickListener(this);
        menuItems[4].setOnClickListener(this);
        menuItems[5].setOnClickListener(this);
        menuItems[6].setOnClickListener(this);
        menuItems[7].setOnClickListener(this);
        menuItems[8].setOnClickListener(this);
        menuItems[9].setOnClickListener(this);
        menuItems[10].setOnClickListener(this);

        resideMenu.addMenuItem(menuItems[0]);
        resideMenu.addMenuItem(menuItems[1]);
        resideMenu.addMenuItem(menuItems[2]);
        resideMenu.addMenuItem(menuItems[3]);
        resideMenu.addMenuItem(menuItems[4]);
        resideMenu.addMenuItem(menuItems[5]);
        resideMenu.addMenuItem(menuItems[6]);
        resideMenu.addMenuItem(menuItems[7]);
        resideMenu.addMenuItem(menuItems[8]);
        resideMenu.addMenuItem(menuItems[9]);
        resideMenu.addMenuItem(menuItems[10]);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu();
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == menuItems[0]) {
            changeFragment(new HomeFragmentActivity());
        } else if (view == menuItems[1]) {
            changeFragment(new InTheatersListFragmentActivity());
            actionBar.setText("Sinemalar");
        } else if (view == menuItems[2]) {

        } else if (view == menuItems[3]) {
        }

        resideMenu.closeMenu();
    }

    //Example of menuListener
    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
        }

        @Override
        public void closeMenu() {
        }
    };

    private void changeFragment(Fragment targetFragment) {
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    //return the residemenu to fragments
    public ResideMenu getResideMenu() {
        return resideMenu;
    }

    @Override
    public void onBackPressed() {
        if (resideMenu.isOpened()) {
            resideMenu.closeMenu();
        } else {
            resideMenu.openMenu();
        }
    }
}
