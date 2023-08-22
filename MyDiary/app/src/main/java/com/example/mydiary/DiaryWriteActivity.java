package com.example.mydiary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class DiaryWriteActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    public static String[][] value_array = new String [50][5];
    public static int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_write);

        Button img_button = (Button)findViewById(R.id.image_button);
        img_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }
    public String image;

    public void addData(View view){
        ContentValues addValues = new ContentValues();

        addValues.put(MyContentProvider.NAME,((EditText)findViewById(R.id.name_text)).getText().toString());
        addValues.put(MyContentProvider.EXPLANATION,((EditText)findViewById(R.id.explanation_text)).getText().toString());
        addValues.put(MyContentProvider.IMAGE,image.toString());
        addValues.put(MyContentProvider.LATITUDE,((EditText)findViewById(R.id.latitude_text)).getText().toString());
        addValues.put(MyContentProvider.LONGITUDE,((EditText)findViewById(R.id.longitude_text)).getText().toString());

        getContentResolver().insert(MyContentProvider.CONTENT_URI,addValues);
        Toast.makeText(getBaseContext(), "추가되었습니다!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                try{
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    image=BitmapToString(bitmap);
                    ImageView imageView = (ImageView)findViewById(R.id.imageView);
                    imageView.setImageBitmap(bitmap);

                    inputStream.close();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        String string_image = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string_image;
    }
}