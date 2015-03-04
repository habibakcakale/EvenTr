package com.special.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.special.R;

/**
 * Created by CrimsonKing on 21.2.2015.
 */
public class Utilities {

    Dialog dialog;

    public void showLoadingPanel(Activity activity) {

        dialog = new Dialog(activity, android.R.style.Theme_Translucent);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.layout_dialog);

        final ImageView myImage = (ImageView) dialog.findViewById(R.id.loader);
        myImage.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.rotate) );

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));

        dialog.show();
    }

    public void hideLoadingPanel(Activity activity){

        dialog.hide();
    }

}
