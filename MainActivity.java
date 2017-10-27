package com.example.java_oglen.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

EditText adi,soyadi,mail,telefon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adi=(EditText)findViewById(R.id.editText);
       soyadi=(EditText)findViewById(R.id.editText2);
        mail=(EditText)findViewById(R.id.editText3);
      telefon=(EditText)findViewById(R.id.editText4);

        //yaz();
        //dataOku();
    }
    public void yaz(Kullanicilar kl){
        //key value  ilişkisi
        ContentValues data=new ContentValues();
        SQLiteDatabase yaz=new DB(this).getWritableDatabase();
     data.put(kisiler.adi.toString(),kl.getAdi());
       data.put(kisiler.soyadi.toString(),kl.getSoyadi());
        data.put(kisiler.mail.toString(),kl.getMail());
      data.put(kisiler.telefon.toString(),kl.getTelefon());
        yaz.insert("kisiler",null,data);
        long sonuc=yaz.insert("kisiler",null,data);
        if(sonuc>0){
            Toast.makeText(this,"Yazma işlemi başaralı",Toast.LENGTH_SHORT).show();
            dataOku();

        }
        yaz.close();
    }
    //data okuma
    public void dataOku()
    {
        SQLiteDatabase oku=new DB(this).getReadableDatabase();
        Cursor cr=oku.query("kisiler",null,null,null,null,null,null);
        while(cr.moveToNext()){

            String adi=cr.getString(1);
            Log.d("Adı",adi);
        }

    }

    public void btnKayitYap(View view)
    {
        String a=adi.getText().toString();
        String s=soyadi.getText().toString();
        String m=mail.getText().toString();
        String t=telefon.getText().toString();
        //dogrulama işlemi
        if(a.equals("")){
            Toast.makeText(this,"LÜTFEN ADINIZI GIRINIZ",Toast.LENGTH_SHORT).show();
            adi.requestFocus();//adına odaklan
            adi.setBackgroundColor(0xFF00FF00);

        }
        else if(s.equals(""))
        {
        Toast.makeText(this,"LÜTFEN SOYADINIZI GİRİNİZ",Toast.LENGTH_SHORT).show();
            soyadi.requestFocus();
    }
    else{
            //artık datalar gönderilebilir
         Kullanicilar kl=new Kullanicilar();
            kl.setAdi(a);
            kl.setSoyadi(s);
            kl.setMail(m);
            kl.setTelefon(t);
yaz(kl);

        }


    }
}
