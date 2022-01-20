package com.test.myfinance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.test.myfinance.DBHelper.DATABASE_NAME;
import static com.test.myfinance.DBHelper.KEY_NAME;
import static com.test.myfinance.DBHelper.TABLE_TRANSITIONS;

public class MainActivity extends AppCompatActivity {


    public TextView enterSum, enterName;
    private Button addButton, readButton, clearButton, btn_checkAll;

    DBHelper dbHelper;

    RecyclerView recyclerView;
    ArrayList<String> trans_id, trans_sum, trans_name;
    CustomAdapter customAdapter;


//    ArrayList<String> list = new ArrayList<>();
//    ArrayAdapter<String> adapter;
//    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        recyclerView = findViewById(R.id.recyclerView);

        enterSum = findViewById(R.id.enterSum);
        enterName = findViewById(R.id.enterName);
        addButton = findViewById(R.id.addButton);
        readButton = findViewById(R.id.readButton);
        btn_checkAll = findViewById(R.id.btn_checkAll);
        clearButton = findViewById(R.id.clearButton);
        addButton.setOnClickListener(this::onClick);
        readButton.setOnClickListener(this::onClick);
        clearButton.setOnClickListener(this::onClick);



        btn_checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//                startActivity(intent);
            }
        });






        dbHelper = new DBHelper(this);
        dbHelper = new DBHelper(MainActivity.this);
        trans_id = new ArrayList<>();
        trans_name = new ArrayList<>();
        trans_sum = new ArrayList<>();




        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, trans_id, trans_sum, trans_name);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        // listView = findViewById(R.id.listView);
        // String[] data = list.toArray(new String[list.size()]);
        //  Log.d("MyLog", "Log " + data.length);
        //  adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        //  listView.setAdapter(adapter);


    }

    void storeDataInArrays(){
        Cursor cursor = dbHelper.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data. ", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()){
                trans_id.add(cursor.getString(0));
                trans_sum.add(cursor.getString(1));
                trans_name.add(cursor.getString(2));
            }
        }
    }

    public void onClick(View v) {


        SQLiteDatabase wrDatabase = dbHelper.getWritableDatabase();
        SQLiteDatabase rDatabase = dbHelper.getReadableDatabase();
        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {
            case R.id.addButton:

                int sum = Integer.parseInt(enterSum.getText().toString());
                String name = enterName.getText().toString();





                contentValues.put(DBHelper.KEY_SUM, sum);
                contentValues.put(DBHelper.KEY_NAME, name);
                wrDatabase.insert(TABLE_TRANSITIONS, null, contentValues);
                Toast.makeText(MainActivity.this, "Метод сработал, строка добавлена!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.readButton:

                    Cursor cursor = rDatabase.query(TABLE_TRANSITIONS, null, null, null, null, null, null);


                    if (cursor.moveToNext()) {
                         int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                         int sumIndex = cursor.getColumnIndex(DBHelper.KEY_SUM);

                         String nameOfTransition = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME));
                         String sumOfTransition = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_SUM));



                         String content = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_SUM));

                         String nameIndex = String.valueOf(cursor.getColumnIndex(DBHelper.KEY_NAME));
                         String readbleData = cursor.getString(cursor.getColumnIndex(dbHelper.KEY_NAME.toString()));
                         String db = dbHelper.readAllData().toString();
                         Toast.makeText(MainActivity.this, "read form DB: " + nameOfTransition + " " + sumOfTransition + " ", Toast.LENGTH_SHORT).show();
                    }

//                    if (cursor.moveToFirst()) {
//                        int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
//                        String sumIndex = String.valueOf(cursor.getColumnIndex(DBHelper.KEY_SUM));
//                        String nameIndex = String.valueOf(cursor.getColumnIndex(DBHelper.KEY_NAME));
//                        do {

//                            // Log.d("MyLog", "ID = " + cursor.getInt(idIndex) + ", name - " + cursor.getString(Integer.parseInt(nameIndex)) + ", sum - " + cursor.getInt(Integer.parseInt(sumIndex)));
//                        } while (cursor.moveToNext());
//                    } else {
//                        Log.d("MyLog", "0, rows");
//                    }
//                    cursor.close();
//
//                    try {
//                        //  Toast.makeText(MainActivity.this, "Метод сработал" + cursor.getString(Integer.parseInt(name)), Toast.LENGTH_SHORT).show();
//                    }catch (NumberFormatException e) {
//                        System.out.println(e.getMessage());
//                    }
//
                    break;





            case R.id.clearButton:




                wrDatabase.delete(TABLE_TRANSITIONS, null, null);
                wrDatabase.execSQL("VACUUM");
                Toast.makeText(MainActivity.this, "Метод сработал, база данных удалена", Toast.LENGTH_SHORT).show();
                break;
        }
        dbHelper.close();
    }
}

