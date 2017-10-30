package com.example.java_oglen.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Kullanicilar> al = new ArrayList<>();
    ListView lv;
    LayoutInflater li;
    BaseAdapter ba;
    EditText adi, soyadi, mail, telefon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adi = (EditText) findViewById(R.id.editText);
        soyadi = (EditText) findViewById(R.id.editText2);
        mail = (EditText) findViewById(R.id.editText3);
        telefon = (EditText) findViewById(R.id.editText4);
        li = LayoutInflater.from(MainActivity.this);
       lv = (ListView) findViewById(R.id.lv);
        Button ekle = (Button) findViewById(R.id.btn1);
        Button sil = (Button) findViewById(R.id.btn2);

        //yaz();
        dataOku();
    }

    public void yaz(Kullanicilar kl) {
        //key value  ilişkisi

        SQLiteDatabase yaz = new DB(this).getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put(kisiler.adi.toString(), kl.getAdi());
        data.put(kisiler.soyadi.toString(), kl.getSoyadi());
        data.put(kisiler.mail.toString(), kl.getMail());
        data.put(kisiler.telefon.toString(), kl.getTelefon());
       // yaz.insert("kisiler", null, data);
        long sonuc = yaz.insert("kisiler", null, data);
        if (sonuc > 0) {
            ba.notifyDataSetChanged();
        }
        yaz.close();
        dataOku();
    }

    //data okuma
    public void dataOku() {
        SQLiteDatabase oku = new DB(this).getReadableDatabase();
        Cursor cr = oku.query("kisiler", null, null, null, null, null, null);
        while (cr.moveToNext()) {
            Kullanicilar k = new Kullanicilar();
            k.setAdi(cr.getString(1));
            k.setSoyadi(cr.getString(2));
            k.setMail(cr.getString(3));
            k.setTelefon(cr.getString(4));

            al.add(k);
        }

        ba = new BaseAdapter() {
            @Override
            public int getCount() {
                return al.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (convertView == null)
                    convertView = li.inflate(R.layout.item, null);

                TextView tvAd = (TextView) convertView.findViewById(R.id.tv_ad);
                TextView tvTel = (TextView) convertView.findViewById(R.id.tv_tel);
                Kullanicilar k = al.get(al.size() - 1 - position);
                tvAd.setText(k.getAdi() + " " + k.getSoyadi());
                tvTel.setText(k.getTelefon() + " " + k.getMail());




                return convertView;
            }
        };

        lv.setAdapter(ba);
    }

    public void btnKayitYap(View view) {
        String a = adi.getText().toString();
        String s = soyadi.getText().toString();
        String m = mail.getText().toString();
        String t = telefon.getText().toString();
        //dogrulama işlemi
        if (a.equals("")) {
            Toast.makeText(this, "LÜTFEN ADINIZI GIRINIZ", Toast.LENGTH_SHORT).show();
            adi.requestFocus();//adına odaklan
            adi.setBackgroundColor(0xFF00FF00);

        } else if (s.equals("")) {
            Toast.makeText(this, "LÜTFEN SOYADINIZI GİRİNİZ", Toast.LENGTH_SHORT).show();
            soyadi.requestFocus();
        } else {
            //artık datalar gönderilebilir
            Kullanicilar kl = new Kullanicilar();
            kl.setAdi(a);
            kl.setSoyadi(s);
            kl.setMail(m);
            kl.setTelefon(t);
            yaz(kl);


            adi.setText("");
            soyadi.setText("");
            mail.setText("");
            telefon.setText("");
        }


    }
}
