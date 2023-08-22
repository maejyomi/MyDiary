package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static com.example.mydiary.DiaryListActivity.columns;
import static com.example.mydiary.DiaryWriteActivity.index;
import static com.example.mydiary.DiaryWriteActivity.value_array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent write_intent = new Intent(getApplicationContext(), DiaryWriteActivity.class);
                startActivity(write_intent);
            }
        });

        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {

            String name, explanation, image, latitude, longitude;

            @Override
            public void onClick(View view) {
                Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,columns,null,null,null,null);

                if(cursor != null){
                    while(cursor.moveToNext()){
                        int id=cursor.getInt(0);
                        name = cursor.getString(1);
                        explanation = cursor.getString(2);
                        image = cursor.getString(3);
                        latitude = cursor.getString(4);
                        longitude = cursor.getString(5);
                        value_array[index][0] = name;
                        value_array[index][1] = explanation;
                        value_array[index][2] = image;
                        value_array[index][3] = latitude;
                        value_array[index][4] = longitude;
                        index++;
                    }
                    cursor.close();
                }
                Intent diary_list_intent = new Intent(getApplicationContext(),DiaryListActivity.class);
                startActivity(diary_list_intent);
            }
        });
    }
}