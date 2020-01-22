package com.aditas.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList <String> data;
    private ArrayAdapter <String> dataAdapter;
    private ListView lvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvData = findViewById(R.id.lv_list);
        data = new ArrayList<String>();
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        lvData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Yakin?")
                        .setMessage("Beneran mo ngapus nh?")
                        .setPositiveButton("Njeh", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                data.remove(which_item);
                                dataAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Ora",null).show();
                return true;
            }
        });
        lvData.setAdapter(dataAdapter);
        /*data.add("First");
        data.add("Second");*/

        FloatingActionButton fab = findViewById(R.id.fab_add);
        fab.setOnClickListener((new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addTask();
            }
        }));

    }

    public void addTask(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add task");
        builder.setMessage("What do you want?");
        final EditText inputField = new EditText(this);
        builder.setView(inputField);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.add(inputField.getText().toString());
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }
}