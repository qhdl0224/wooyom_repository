package com.lectopia.team28.mytab;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment1 extends Fragment {

    static final String dbName ="mydb3";
    static final String tableName = "mybook";

    private static final String TAG = "DATA BASE";
    boolean isDbCreated = false;
    boolean isTableCreated = false;
    EditText etTitle;
    EditText etAuthor;
    EditText etContent;
    Button btnSave;

    SQLiteDatabase db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        etTitle = (EditText)rootView.findViewById(R.id.etTitle);
        etAuthor = (EditText)rootView.findViewById(R.id.etAuthor);
        etContent = (EditText)rootView.findViewById(R.id.etContent);
        btnSave = (Button)rootView.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDatabase(dbName);
                createTable(tableName);
                insertRecordParam(etTitle.getText().toString(),etAuthor.getText().toString(),
                        etContent.getText().toString());
                Toast.makeText(rootView.getContext(),"저장되었습니다.",Toast.LENGTH_LONG).show();
            }
        });

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
               // Toast.makeText(Fragment1.,"테이블 생성 오류 , 로그를 확인해주세요.",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }else{
            //Toast.makeText(getApplicationContext(),"DB를 생성해주세요.",Toast.LENGTH_SHORT).show();
        }

    }

    private int insertRecordParam(String title,String author,String content){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("author",author);
        contentValues.put("content",content);
        return (int)db.insert(tableName, null,contentValues);
    }
}
