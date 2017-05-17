package net.a6te.lazycoder.stopwatch;

import android.databinding.DataBindingUtil;
import android.icu.text.DateFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.a6te.lazycoder.stopwatch.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActivityMainBinding binder;
    private Thread mThread;
    private Handler handler;

    private long milli=0,second=0,mint=0;
    private long startTime=0,time=0,beforeStopTime=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = DataBindingUtil.setContentView(this,R.layout.activity_main);
        binder.startBtn.setOnClickListener(this);
        binder.stopBtn.setOnClickListener(this);
        binder.resetBtn.setOnClickListener(this);

        handler = new Handler();

    }

    public class myRunnable implements Runnable{
        @Override
        public void run() {
            while (1==1){

                time = System.currentTimeMillis() - startTime+beforeStopTime;
                beforeStopTime = time;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        statusUpdate();
                    }
                });


            }
        }
    }

    private void statusUpdate() {

        milli = time%1000;
        milli = time%1000;
        milli = time%1000;
        milli = time%1000;
        Log.d("Test", "statusUpdate: /1000 "+time/1000);
        Log.d("Test", "statusUpdate: %1000 "+time%1000+"\n");

        Log.d("Test", "statusUpdate: second /1000 "+time/1000/60);
        Log.d("Test", "statusUpdate: second %1000 "+time%1000/60);
        //binder.showWatchTv.setText(formate.format(time));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.startBtn:
                startTime = System.currentTimeMillis();
                mThread = new Thread(new myRunnable());
                mThread.start();
                break;
            case R.id.stopBtn:
                startTime = System.currentTimeMillis();
                break;
            case R.id.resetBtn:
                time = 0;
                beforeStopTime = 0;
                startTime = 0;

                break;

        }
    }


}
