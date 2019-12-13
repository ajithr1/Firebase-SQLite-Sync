package com.example.va;

import android.provider.BaseColumns;

public class ShoppingContract {

    private ShoppingContract(){}

    public static final class ShoppingEntry implements BaseColumns{
        public static final String TABLE_NAME = "shopping_list";
        public static final String COLUMN_NAME = "name";

    }
}
