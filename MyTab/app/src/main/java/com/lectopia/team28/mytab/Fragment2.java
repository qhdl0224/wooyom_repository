package com.lectopia.team28.mytab;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.lectopia.team28.mytab.Adapter.MyBookAdapter;
import com.lectopia.team28.mytab.Model.Book;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    static final String dbName ="mydb3";
    static final String tableName = "mybook";

    private static final String TAG = "DATA BASE";
    private ListView listView = null;
    private ArrayList<Book> data = null;

    boolean isDbCreated = false;
    boolean isTableCreated = false;

    SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

        createDatabase(dbName);
        createTable(tableName);
        data = selectRecord();
        MyBookAdapter adapter = new MyBookAdapter(rootView.getContext(),data);
        listView = (ListView)rootView.findViewById(R.id.myListView);
        listView.setAdapter(adapter);


        return rootView;
    }

    private void createDatabase(String dbName){
        try{
            db = getActivity().openOrCreateDatabase(dbName,android.content.Context.MODE_PRIVATE,null);
            isDbCreated = true;
            Log.d(TAG,"data base is created");


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createTable(String tableName){
        if(isDbCreated){
            try{
                db.execSQL("CREATE TABLE IF NOT EXISTS "+tableName+"("
                        +"_id integer PRIMARY KEY AUTOINCREMENT, "
                        +"title text,"
                        +"author text,"
                        +"content text);");
                isTableCreated = true;
            }catch (Exception e){
              //  Toast.makeText(getApplicationContext(),"테이블 생성 오류 , 로그를 확인해주세요.",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }else{
            //Toast.makeText(getApplicationContext(),"DB를 생성해주세요.",Toast.LENGTH_SHORT).show();
        }

    }

    private ArrayList<Book> selectRecord(){
        String sql = "SELECT title,author,content FROM "+tableName;
        String[] args= new String[1];
        args=null;

        ArrayList<Book> data = null;
        Cursor c = db.rawQuery(sql,args);
        int count = c.getCount();
        if(count>0){
            data = new ArrayList<>();
            while(c.moveToNext()){
                Book book = new Book();
                book.setBookTitle(c.getString(0));
                book.setAuthor(c.getString(1));
                book.setContent(c.getString(2));
                data.add(book);
                //sb.append(c.getString(0)+"/"+c.getInt(1)+"/"+c.getString(2)+"\n");
            }
            return data;
        }else{
            return data;
        }
    }
}
