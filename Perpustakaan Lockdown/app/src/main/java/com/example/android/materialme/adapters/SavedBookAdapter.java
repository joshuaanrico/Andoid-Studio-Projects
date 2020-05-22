package com.example.android.materialme.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.materialme.R;
import com.example.android.materialme.SavedBookContract;

public class SavedBookAdapter extends RecyclerView.Adapter<SavedBookAdapter.SavedBookViewHolder> {
    private Context mContext;
    private Cursor mCursor;

    public SavedBookAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;
    }

    public class SavedBookViewHolder extends RecyclerView.ViewHolder{
        public TextView judulText;
        public TextView penulisText;

        public SavedBookViewHolder(View itemView) {
            super(itemView);
            judulText = itemView.findViewById(R.id.textView_judul);
            penulisText = itemView.findViewById(R.id.textView_penulis);
        }
    }

    @NonNull
    @Override
    public SavedBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.saved_book_item, parent, false);
        return new SavedBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SavedBookViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }
        String judul = mCursor.getString(mCursor.getColumnIndex(SavedBookContract.SavedBookEntry.COL_2));
        String penulis = mCursor.getString(mCursor.getColumnIndex(SavedBookContract.SavedBookEntry.COL_3));
        long id = mCursor.getLong(mCursor.getColumnIndex(SavedBookContract.SavedBookEntry.COL_1));

        holder.judulText.setText(judul);
        holder.penulisText.setText(penulis);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor){
        if (mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;

        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}
