package com.lectopia.team28.myimage;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int i=5;//currentPosition

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int start = R.drawable.dd1;
        final int last = R.drawable.dd9;

        Button prevBtn = (Button)findViewById(R.id.prevBtn);
        Button nextBtn = (Button)findViewById(R.id.nextBtn);
        final ImageView imageView = (ImageView)findViewById(R.id.imageView);



        final List<Integer> list = new ArrayList<Integer>();

        for(int i=start;i<=last;i++){
            list.add(i);
        }

        imageView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if(action == MotionEvent.ACTION_DOWN){
                    if((imageView.getX()+imageView.getWidth()/2 < motionEvent.getX())
                            &&(motionEvent.getY() > imageView.getY())
                            && (motionEvent.getY() < imageView.getY()+imageView.getHeight()) ){ //next
                        if(i == list.size()-1){
                            i=list.size()-1;
                            //imageView.setImageResource(list.get(list.size()-1));
                        }else{

                            i++;
                        }
                        imageView.setImageResource(list.get(i));
                    }else if((imageView.getX()+imageView.getWidth()/2 > motionEvent.getX())
                            &&(motionEvent.getY() > imageView.getY())
                            && (motionEvent.getY() < imageView.getY()+imageView.getHeight())){ //prev
                        if(i == 0){
                            i=0;
                            //imageView.setImageResource(list.get(0));
                        }else{

                            i--;
                        }
                        imageView.setImageResource(list.get(i));
                    }
                }

                return true;
            }
        });

      /*  imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(imageView.getWidth()/2 < motionEvent.getX()){ //next

                    if(i == list.size()-1){
                        i=list.size()-1;
                        //imageView.setImageResource(list.get(list.size()-1));
                    }else{

                        i++;
                    }
                    imageView.setImageResource(list.get(i));
                }else if(imageView.getWidth()/2 > imageView.getX()){ //prev
                    if(i == 0){
                        i=0;
                        //imageView.setImageResource(list.get(0));
                    }else{

                        i--;
                    }
                    imageView.setImageResource(list.get(i));

                }
            }
        });*/


       /* prevBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(i == 0){
                    i=0;
                    //imageView.setImageResource(list.get(0));
                }else{

                    i--;
                }
                imageView.setImageResource(list.get(i));
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                if(i == list.size()-1){
                    i=list.size()-1;
                    //imageView.setImageResource(list.get(list.size()-1));
                }else{

                    i++;
                }
                imageView.setImageResource(list.get(i));
            }
        });*/

    }
}
