package com.lectopia.team28.mytab.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lectopia.team28.mytab.Model.Book;
import com.lectopia.team28.mytab.R;

import java.util.ArrayList;

public class MyBookAdapter extends BaseAdapter{
    private Context context = null;
    private ArrayList<Book> data= null;
    private LayoutInflater layoutInflater = null;

    public MyBookAdapter(Context context, ArrayList<Book> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder{
        ImageView imageView;
        TextView tvTitle;
        TextView tvAuthor;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View myItemLayout = view;
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();

        if(myItemLayout == null){
            myItemLayout = layoutInflater.inflate(R.layout.my_book_list,null);

            viewHolder.imageView = (ImageView)myItemLayout.findViewById(R.id.imageView2);
            viewHolder.tvTitle = (TextView)myItemLayout.findViewById(R.id.tvTitle);
            viewHolder.tvAuthor = (TextView)myItemLayout.findViewById(R.id.tvAuthor);
            myItemLayout.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)myItemLayout.getTag();
        }
        //viewHolder.imageView.setImageResource(data.get(i).getBookImage());
        viewHolder.tvTitle.setText(data.get(i).getBookTitle());
        viewHolder.tvAuthor.setText(data.get(i).getAuthor());

        return myItemLayout;
    }
}
