package com.example.nishanth.multitthreading;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView result,evennum;
    ProgressBar pb;
    Button start,btnCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button)findViewById(R.id.start);
        btnCount = (Button)findViewById(R.id.btnCount);
        result = (TextView)findViewById(R.id.result);
        evennum = (TextView)findViewById(R.id.evennum);
        pb = (ProgressBar)findViewById(R.id.progressBar);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable runnabl2 = new Runnable() {
                    @Override
                    public void run() {
                        android.os.Process.setThreadPriority(1);
                        for(int i=0;i<=20;i=i+2){
                            final int value =i;
                            sleepSometime();
                            evennum.post(new Runnable() {
                                @Override
                                public void run() {
                                    evennum.setText(value + " ");
                                }
                            });

                        }
                    }
                };
                new Thread(runnabl2).start();
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Runnable runnable = new Runnable() {
                     @Override
                     public void run() {
                         android.os.Process.setThreadPriority(2);
                         for(int i=0;i<100;i=i+5){
                             final int myInt =i;
                             doSomething();
                             pb.post(new Runnable() {
                                 @Override
                                 public void run() {
                                     result.setText("Updating");
                                     pb.setProgress(myInt);
                                 }
                             });
                         }
                     }
                 };
                 new Thread(runnable).start();
            }
        });

    }
    void sleepSometime() {
        try {
                Thread.sleep(2000);
        }catch(Exception e){

        }
    }
    void doSomething(){
        try {
            Thread.sleep(1000);
        }catch(Exception e){

        }
    }

}
