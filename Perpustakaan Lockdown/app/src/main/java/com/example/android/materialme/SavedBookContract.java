package com.example.android.materialme;

import android.provider.BaseColumns;

public class SavedBookContract {
    private SavedBookContract() {}

    public static final class SavedBookEntry implements BaseColumns{
        public static final String TABLE_NAME = "savedBook";
        public static final String COL_1 = "id";
        public static final String COL_2 = "judul_buku";
        public static final String COL_3 = "penulis_buku";
        public static final String COL_4 = "timestamp";
    }
}
