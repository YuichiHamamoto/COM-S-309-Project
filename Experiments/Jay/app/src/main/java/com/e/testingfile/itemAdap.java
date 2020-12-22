package com.e.testingfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class itemAdap extends BaseAdapter {
    String[] obj;
    LayoutInflater mInf;
    @Override
    public int getCount() {
        return obj.length;
    }
    public itemAdap(Context con, String[] i) {
        obj = i;
        mInf =(LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public Object getItem(int i) {
        return obj[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInf.inflate(R.layout.mylistviewdetail, null);
        TextView nameTextView = (TextView) v.findViewById(R.id.nameTextView);
        String name = obj[i];
        nameTextView.setText(name);
        return v;
    }
}
