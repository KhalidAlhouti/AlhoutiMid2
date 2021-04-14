package com.example.alhoutimid2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {



    AtomicInteger timer=new AtomicInteger(6);
    TextView timerText;
    final Timer Mytimer=new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timerText=findViewById(R.id.timer);
        TimerTask task =new Task();
        Mytimer.schedule(task,600);
    }

    class Task extends TimerTask{

        @Override
        public void run() {
            if (timer.get()!= 1){
                timer.decrementAndGet();
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        timerText.setText(timer.get()+" seconds");
                    }
                });
                Mytimer.schedule(new Task(),600);
            }else{
                finish();
                startActivity(new Intent(MainActivity.this,DatabaseAccess2.class));
            }
        }
    };
}