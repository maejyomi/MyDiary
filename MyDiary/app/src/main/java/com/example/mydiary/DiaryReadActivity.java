package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import static com.example.mydiary.DiaryWriteActivity.value_array;

public class DiaryReadActivity extends AppCompatActivity {
    public static int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_read);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("values");

        TextView name_output = (TextView)findViewById(R.id.name_output);
        name_output.setText(value_array[position][0]);

        TextView explanation_output = (TextView)findViewById(R.id.explanation_output);
        explanation_output.setText(value_array[position][1]);

        ImageView image_output = (ImageView)findViewById(R.id.image_output);
        Bitmap bitmap = StringToBitmap(value_array[position][2]);
        image_output.setImageBitmap(bitmap);

        Button map_button = (Button)findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent map_intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(map_intent);
            }
        });
    }

    public Bitmap StringToBitmap(String encodedString){
        try{
            byte[] encodedByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodedByte,0,encodedByte.length);
            return bitmap;
        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }
}