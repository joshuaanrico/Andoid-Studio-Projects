package com.example.android.materialme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class AboutApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);
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
