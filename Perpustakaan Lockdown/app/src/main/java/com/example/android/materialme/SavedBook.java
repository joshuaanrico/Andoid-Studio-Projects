package com.example.android.materialme;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.materialme.adapters.SavedBookAdapter;

public class SavedBook extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    private SavedBookAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_book);

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SavedBookAdapter(this, getAllItems());
        recyclerView.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void removeItem(long id){
        mDatabase.delete(SavedBookContract.SavedBookEntry.TABLE_NAME,
                SavedBookContract.SavedBookEntry.COL_1 + "=" + id, null);
        mAdapter.swapCursor(getAllItems());
    }

    private Cursor getAllItems(){
        return mDatabase.query(
                SavedBookContract.SavedBookEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                SavedBookContract.SavedBookEntry.COL_4 + " DESC"
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId() == R.id.about){
            startActivity(new Intent(this, AboutApp.class));
        }else if (item.getItemId() == R.id.saved_book){
            startActivity(new Intent(this, SavedBook.class));
        }else if (item.getItemId() == R.id.home){
            startActivity(new Intent(this, MainActivity.class));
        }
        return true;
    }
}
