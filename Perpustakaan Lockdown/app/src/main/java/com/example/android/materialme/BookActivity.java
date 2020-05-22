package com.example.android.materialme;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class BookActivity extends AppCompatActivity {
    //Declare Database
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        //Enable Database in BookActivity
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        //hide the default actionBar
        getSupportActionBar().hide();

        //Receive
        Bundle extras = getIntent().getExtras();
        String title =""
                , authors ="", description="" , categories ="", publishDate=""
                ,info ="", buy ="",preview ="" ,thumbnail ="";
        if(extras != null){
            title = extras.getString("book_title");
            authors = extras.getString("book_author");
            description = extras.getString("book_desc");
            categories = extras.getString("book_categories");
            publishDate = extras.getString("book_publish_date");
            info = extras.getString("book_info");
            buy = extras.getString("book_buy");
            preview = extras.getString("book_preview");
            thumbnail = extras.getString("book_thumbnail");
        }

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        final TextView tvTitle = findViewById(R.id.aa_book_name);
        final TextView tvAuthors = findViewById(R.id.aa_author);
        TextView tvDesc = findViewById(R.id.aa_description);
        TextView tvCatag = findViewById(R.id.aa_categorie);
        TextView tvPublishDate = findViewById(R.id.aa_publish_date);
        TextView tvInfo = findViewById(R.id.aa_info);
        TextView tvPreview = findViewById(R.id.aa_preview);

        ImageView ivThumbnail = findViewById(R.id.aa_thumbnail);

        tvTitle.setText(title);
        tvAuthors.setText(authors);
        tvDesc.setText(description);
        tvCatag.setText(categories);
        tvPublishDate.setText(publishDate);

        final String finalInfo = info;
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalInfo));
                startActivity(i);
            }
        });


        final String finalPreview = preview;
        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Insert Database
                String judul = tvTitle.getText().toString();
                String penulis = tvAuthors.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put(SavedBookContract.SavedBookEntry.COL_2, judul);
                cv.put(SavedBookContract.SavedBookEntry.COL_3, penulis);

                mDatabase.insert(SavedBookContract.SavedBookEntry.TABLE_NAME, null, cv);
                Toast.makeText(BookActivity.this, "Buku sudah disave kedalam Saved Book", Toast.LENGTH_SHORT).show();
            }
        });

        collapsingToolbarLayout.setTitle(title);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(thumbnail).apply(requestOptions).into(ivThumbnail);
    }
}
