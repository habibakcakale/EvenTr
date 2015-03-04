package com.special.core;

import com.special.MainActivity;
import com.special.R;
import com.special.menu.ResideMenu;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.special.utils.Utilities;

public class HomeFragmentActivity extends Fragment {

    private View parentView;
    private ResideMenu resideMenu;
    private Utilities utilities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_home, container, false);
        utilities = new Utilities();
        setUpViews();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        return parentView;
    }

    private void setUpViews() {
        final MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

        parentView.findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        
    }

}
