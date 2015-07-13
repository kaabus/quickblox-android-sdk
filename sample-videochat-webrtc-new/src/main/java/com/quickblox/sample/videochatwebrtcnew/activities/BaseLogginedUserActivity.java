package com.quickblox.sample.videochatwebrtcnew.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import com.quickblox.chat.QBChatService;
import com.quickblox.core.QBSettings;
import com.quickblox.sample.videochatwebrtcnew.R;
import com.quickblox.sample.videochatwebrtcnew.definitions.Consts;
import com.quickblox.sample.videochatwebrtcnew.holder.DataHolder;
import com.quickblox.sample.videochatwebrtcnew.services.IncomeCallListenerService;
import com.quickblox.users.model.QBUser;


/**
 * Created by tereha on 26.01.15.
 */
public class BaseLogginedUserActivity extends AppCompatActivity {

    private static final String VERSION_NUMBER = "0.9.4.18062015";
    private static final String APP_VERSION = "App version";
    static ActionBar mActionBar;
    private Chronometer timerABWithTimer;
    private boolean isTimerStarted = false;
    protected QBUser loginedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QBSettings.getInstance().fastConfigInit(Consts.APP_ID, Consts.AUTH_KEY, Consts.AUTH_SECRET);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        getSupportActionBar().setIcon(R.drawable.logo_qb);
        getSupportActionBar().setTitle(R.string.app_name);


    }

    protected void initActionBar() {

        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar_view, null);

        TextView numberOfListAB = (TextView) mCustomView.findViewById(R.id.numberOfListAB);
        numberOfListAB.setBackgroundResource(resourceSelector(
                DataHolder.getUserIndexByID(loginedUser.getId()) + 1));
        numberOfListAB.setText(String.valueOf(
                DataHolder.getUserIndexByID(loginedUser.getId()) + 1));

        numberOfListAB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(BaseLogginedUserActivity.this);
                dialog.setTitle(APP_VERSION);
                dialog.setMessage(VERSION_NUMBER);
                dialog.show();
                return true;
            }});

        TextView loginAsAB = (TextView) mCustomView.findViewById(R.id.loginAsAB);
        loginAsAB.setText(R.string.logged_in_as);


        TextView userNameAB = (TextView) mCustomView.findViewById(R.id.userNameAB);
        userNameAB.setText(DataHolder.getUserNameByID(loginedUser.getId()));

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);

    }

    public void initActionBarWithTimer() {
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);

        LayoutInflater mInflater = LayoutInflater.from(this);

        View mCustomView = mInflater.inflate(R.layout.actionbar_with_timer, null);

        timerABWithTimer = (Chronometer) mCustomView.findViewById(R.id.timerABWithTimer);

        TextView loginAsABWithTimer = (TextView) mCustomView.findViewById(R.id.loginAsABWithTimer);
        loginAsABWithTimer.setText(R.string.logged_in_as);

        TextView userNameAB = (TextView) mCustomView.findViewById(R.id.userNameABWithTimer);
        userNameAB.setText(DataHolder.getUserNameByID(loginedUser.getId()));

        mActionBar.setCustomView(mCustomView);
        mActionBar.setDisplayShowCustomEnabled(true);
    }

    public void startTimer() {
        if (!isTimerStarted) {
            timerABWithTimer.setBase(SystemClock.elapsedRealtime());
            timerABWithTimer.start();
            isTimerStarted = true;
        }
    }

    public void stopTimer(){
        if (timerABWithTimer != null){
            timerABWithTimer.stop();
            isTimerStarted = false;
        }
    }

    public void startIncomeCallListenerService(String login, String password){
        Intent intent = new Intent(this, IncomeCallListenerService.class);
        intent.putExtra(Consts.USER_LOGIN, login);
        intent.putExtra(Consts.USER_PASSWORD, password);
        startService(intent);
    }

    public void stopIncomeCallListenerService(){
        stopService(new Intent(this, IncomeCallListenerService.class));
    }

    public static int resourceSelector(int number) {
        int resStr = -1;
        switch (number) {
            case 0:
                resStr = R.drawable.shape_oval_spring_bud;
                break;
            case 1:
                resStr = R.drawable.shape_oval_orange;
                break;
            case 2:
                resStr = R.drawable.shape_oval_water_bondi_beach;
                break;
            case 3:
                resStr = R.drawable.shape_oval_blue_green;
                break;
            case 4:
                resStr = R.drawable.shape_oval_lime;
                break;
            case 5:
                resStr = R.drawable.shape_oval_mauveine;
                break;
            case 6:
                resStr = R.drawable.shape_oval_gentianaceae_blue;
                break;
            case 7:
                resStr = R.drawable.shape_oval_blue;
                break;
            case 8:
                resStr = R.drawable.shape_oval_blue_krayola;
                break;
            case 9:
                resStr = R.drawable.shape_oval_coral;
                break;
            default:
                resStr = resourceSelector(number % 10);
        }
        return resStr;
    }

    public static int selectBackgrounForOpponent(int number) {
        int resStr = -1;
        switch (number) {
            case 0:
                resStr = R.drawable.rectangle_rounded_spring_bud;
                break;
            case 1:
                resStr = R.drawable.rectangle_rounded_orange;
                break;
            case 2:
                resStr = R.drawable.rectangle_rounded_water_bondi_beach;
                break;
            case 3:
                resStr = R.drawable.rectangle_rounded_blue_green;
                break;
            case 4:
                resStr = R.drawable.rectangle_rounded_lime;
                break;
            case 5:
                resStr = R.drawable.rectangle_rounded_mauveine;
                break;
            case 6:
                resStr = R.drawable.rectangle_rounded_gentianaceae_blue;
                break;
            case 7:
                resStr = R.drawable.rectangle_rounded_blue;
                break;
            case 8:
                resStr = R.drawable.rectangle_rounded_blue_krayola;
                break;
            case 9:
                resStr = R.drawable.rectangle_rounded_coral;
                break;
            default:
                resStr = selectBackgrounForOpponent(number % 10);
        }
        return resStr;
    }
}




