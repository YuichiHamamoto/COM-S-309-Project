package com.example.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class todoAdapter extends RecyclerView.Adapter<todoAdapter.todoViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public todoAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }
    public class todoViewHolder extends  RecyclerView.ViewHolder{

        public TextView nameText;
        public TextView countText;

        public todoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textNameItem);
            countText = itemView.findViewById(R.id.textAmountItem);

        }
    }

    @Override
    public todoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.todoitem,parent,false);
        return new todoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(todoViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;

        }

        String name = mCursor.getString(mCursor.getColumnIndex(todoContract.todoEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(todoContract.todoEntry.COLUMN_AMOUNT));

        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if (mCursor!=null){
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}
