package com.lectopia.team28.mymovie;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tvDirector, tvGenre, tvActors, tvRelease, tvPlot;
    Button btnSearch;
    ImageView poster;
    EditText edTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDirector = (TextView)findViewById(R.id.tvDirector);
        tvGenre = (TextView)findViewById(R.id.tvGenre);
        tvActors = (TextView)findViewById(R.id.tvActors);
        tvRelease = (TextView)findViewById(R.id.tvRelease);
        tvPlot = (TextView)findViewById(R.id.tvPlot);

        btnSearch = (Button)findViewById(R.id.btnSearch);
        poster = (ImageView)findViewById(R.id.poster);
        edTitle = (EditText)findViewById(R.id.edTitle);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!edTitle.getText().toString().equals("")){
                    //MySearchTask.execute();
                    MySearchTask mySearchTask = new MySearchTask();
                    mySearchTask.execute(edTitle.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(),"Title을 입력하세요",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    class MySearchTask extends AsyncTask<String,Void,JSONObject>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... values) {
            //Socket <-> serverSocket, InputStream, OutputStream
            try {

                String jsonStr = null;
               String url = "http://www.omdbapi.com/?apikey=76ebc127&t="+values[0];

               URL myUrl = new URL(url);
               HttpURLConnection conn = (HttpURLConnection)myUrl.openConnection();
               conn.setRequestMethod("GET");
               InputStream is = new BufferedInputStream(conn.getInputStream());
               BufferedReader reader = new BufferedReader(new InputStreamReader(is));
               StringBuilder sb = new StringBuilder();
               String line;

               while((line = reader.readLine())!=null){
                   sb.append(line).append('\n');
               }

                jsonStr = sb.toString();

               JSONObject jsonObject = new JSONObject(jsonStr);
               return jsonObject;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }
        @Override
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            if(result!=null){
                StringBuilder genres;
                try{
                    MyThread myThread = new MyThread(result);
                    myThread.start();

                    //poster.setImageResource(result.getString("Poster"));
                    tvRelease.setText(result.getString("Released"));
                    tvActors.setText(result.getString("Actors"));
                    tvDirector.setText(result.getString("Director"));
                    tvGenre.setText(result.getString("Genre"));
                    tvPlot.setText(result.getString("Plot"));
                }catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }


    }

    class MyThread extends Thread{
        private JSONObject jsonObject;
        private Bitmap bitmap;
        public MyThread(JSONObject result){
            jsonObject = result;
        }

        public void run(){
            try{
                URL url = new URL(jsonObject.getString("Poster"));
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                InputStream is = url.openStream();
                bitmap = BitmapFactory.decodeStream(is);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        poster.setImageBitmap(bitmap);
                    }
                });
            }catch(JSONException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
