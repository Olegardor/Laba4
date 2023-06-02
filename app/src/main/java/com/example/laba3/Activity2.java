package com.example.laba3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;

public class Activity2 extends AppCompatActivity {//за инициализацию активности23
    ContactItem contact;//класс контакта ,где нах вся инфа о конткте
    @Override
    protected void onCreate(Bundle savedInstanceState) {//метод)один изх методов) создания активити
        super.onCreate(savedInstanceState);//вызывает метод из родительсокго класса
        setContentView(R.layout.activity_2);//дастает xml из активиьти
        Bundle arguments = getIntent().getExtras();//получаем интетнт и достаем набор данных
        contact = (ContactItem) arguments.getParcelable("contact");//достаем из наших данных необходимый класс данных
        restoreState();//обновленние состояния активити
        TextView prevText = findViewById(R.id.prevText);//текстовое поле в каком мы показываем данные из предыдущей актвити
        prevText.setText(contact.toString());//выводим в это поле данные
        ImageView prevAva = findViewById(R.id.prevAva);//поле с картинокй в какое мы выводим картинку из предыдущей активити
        prevAva.setImageURI((Uri.parse(contact.getAva())));//вывод картинки в это поле

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {//запускается когда сворачиваем прогу ,для сохранения данных при различных действиях

        outState.putParcelable("contact", contact);//записываем класс,для обновления активити
        super.onSaveInstanceState(outState);//вызывает метод из родительсокго класса
    }
    private void restoreState(){//обновленние состояния активити
        if(contact.getPhone()!=null)((EditText)findViewById(R.id.editTextPhone)).setText(contact.getPhone());//выносим данные с класса в поле ввода
        if(contact.getAddress()!=null)((EditText)findViewById(R.id.editTextAdress)).setText(contact.getAddress());//
        if(contact.getVkLink()!=null)((EditText)findViewById(R.id.editTextVK)).setText(contact.getVkLink());//
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {//обновляем активити после сохранения onSaveInstanceState
        super.onRestoreInstanceState(savedInstanceState);//вызывает метод из родительсокго класса
        contact = (ContactItem) savedInstanceState.getParcelable("contact");//достаем класс из бандла Bundle outState
        restoreState();//
    }
    public void clickNext1(View view) {//вызываем когда нажимаем кнопку некст
        Intent intent = new Intent(this, Activity3.class);//создаетмя интетнт для открытие след активити
        contact.setPhone(((EditText)findViewById(R.id.editTextPhone)).getText().toString());//запись в класс данных из активити
        contact.setAddress(((EditText)findViewById(R.id.editTextAdress)).getText().toString());//
        contact.setVkLink(((EditText)findViewById(R.id.editTextVK)).getText().toString());//
       intent.putExtra("contact",contact);//внесе класс в интетнт
        startActivity(intent);//запукс след окна с переданными данными
    }

    public void clickNext2(View view) {//вызываем когда нажимаем кнопку некст
        Intent intent = new Intent(this, MainActivity.class);//создаетмя интетнт для открытие след активити
        ContactItem contact = new ContactItem();
        contact.setName(((EditText) findViewById(R.id.editTextName)).getText().toString());
        contact.setSurname(((EditText) findViewById(R.id.editTextSurname)).getText().toString());
        contact.setMail(((EditText) findViewById(R.id.editTextMail)).getText().toString());
        contact.setIsMale(((RadioButton) findViewById(R.id.radioButtonMale)).isChecked());
        intent.putExtra("contact", contact);
        startActivity(intent);

    }

}