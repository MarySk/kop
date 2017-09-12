package com.example.mary.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mary on 21.03.2017.
 */

public class Activity4 extends Activity {

    EditText editText6;
    ArrayAdapter<String> adapter;
    ListView listView;
    public DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_9);
        DB = new DBHelper(this, Contract.Shop.TABLE_NAME, null, 1);
        editText6 = (EditText) findViewById(R.id.editText6);
        listView = (ListView)findViewById(R.id.listView);
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getShop(Contract.Shop._ID + "> 0"));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
        editText6.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // TODO Auto-generated method stub
                if (event.getAction() == KeyEvent.ACTION_DOWN)
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        DB.insert_s(editText6.getText().toString());
                        Method();
                        editText6.setText("");
                        return true;
                    }
                return false;
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                final String strText = textView.getText().toString(); // текст нажатого элемента
                AlertDialog.Builder adb=new AlertDialog.Builder(Activity4.this);
                adb.setTitle("Удалить элемент?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DB.delete_s(strText);
                        Method();
                    }});
                adb.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                TextView textView = (TextView) itemClicked;
                final String strText = textView.getText().toString(); // текст нажатого элемента
                AlertDialog.Builder adb=new AlertDialog.Builder(Activity4.this);
                adb.setTitle("Показать на карте?");
                adb.setNegativeButton("Отмена     ", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Activity4.this, MapsActivity.class);
                        intent.putExtra("shop", strText);
                        startActivity(intent);
                    }});
                adb.show();
            }
        });
    }
    public void Method(){
        try {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    DB.getShop(Contract.Shop._ID + "> 0"));
            listView.setAdapter(adapter);
        }
        catch (Exception ex) {
            Toast t = Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG);
            t.show();
        }
    }
}


