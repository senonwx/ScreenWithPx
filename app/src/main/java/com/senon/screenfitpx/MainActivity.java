package com.senon.screenfitpx;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)this.findViewById(R.id.listv);

        for (int i = 0; i < 20; i++) {
            arrayList.add("i am "+(i+1));
        }

        listView.setAdapter(new ListViewAdapter(arrayList,this));
    }

    class ListViewAdapter extends BaseAdapter{

        private Context context;
        private ArrayList<String> arrayList;

        public ListViewAdapter(ArrayList<String> arrayList, Context context) {
            this.arrayList = arrayList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder holder = null;
            if(convertView == null){
                convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
                holder = new MyViewHolder();
                holder.textView = (TextView) convertView.findViewById(R.id.item_tv);
                convertView.setTag(holder);
            }else {
                holder = (MyViewHolder) convertView.getTag();
            }
            holder.textView.setText(arrayList.get(position));
            return convertView;
        }

        class MyViewHolder{
            TextView textView;
        }
    }
}
