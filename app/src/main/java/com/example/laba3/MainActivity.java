package com.example.laba3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private ImageView imageAva;
    private final int Pick_image = 1;

    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageAva = findViewById(R.id.imageAva);
    }
    // Обработчик кнопки
    public void clickNext(View view) {
        Intent intent = new Intent(this, Activity2.class);
        ContactItem contact = new ContactItem();
        // Заполнение полей объекта ContactItem
        contact.setName(((EditText) findViewById(R.id.editTextName)).getText().toString());
        contact.setSurname(((EditText) findViewById(R.id.editTextSurname)).getText().toString());
        contact.setMail(((EditText) findViewById(R.id.editTextMail)).getText().toString());
        contact.setIsMale(((RadioButton) findViewById(R.id.radioButtonMale)).isChecked());
        if(imageUri!=null)contact.setAva(imageUri.toString());
        intent.putExtra("contact", contact);
        startActivity(intent);
    }
    // Обработчик кнопки "Выбрать изображение"
    public void selectImage(View view) {
        //Вызываем стандартную галерею для выбора изображения с помощью Intent.ACTION_PICK:
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        //Тип получаемых объектов - image:
        photoPickerIntent.setType("image/*");
        //Запускаем переход с ожиданием обратного результата в виде информации об изображении:
        startActivityForResult(photoPickerIntent, Pick_image);
    }

    // Обработчик результата выбора изображения в галерее
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch (requestCode) {
            case Pick_image:
                if (resultCode == RESULT_OK) {
                    imageUri = imageReturnedIntent.getData();
                    imageAva.setImageURI(imageUri);

                }
        }
    }
}