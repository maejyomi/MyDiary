package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

import static com.example.mydiary.DiaryWriteActivity.index;
import static com.example.mydiary.DiaryWriteActivity.value_array;

public class DiaryListActivity extends AppCompatActivity {
   public static String[] columns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<NameList> diary_att = new ArrayList<>();
        columns = new String[]{"_id","name","explanation","image","latitude","longitude"};
        Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,columns,null,null,null,null);

        if(cursor != null && cursor.moveToFirst()){
            for(int i = 0; i < index; i++){
                diary_att.add(new NameList(value_array[i][0]));
            }
        }

        MyAdapter myAdapter = new MyAdapter(diary_att);
        recyclerView.setAdapter(myAdapter);

    }
}