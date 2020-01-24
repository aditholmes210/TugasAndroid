package com.aditas.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final static String PREFERENCES = "MAIN PREF";
    private final static String TODO_LIST = "TODO";

    private ArrayList <String> data;
    private ArrayAdapter <String> dataAdapter;
    private ListView lvData;
    private EditText inputList;
    private SharedPreferences pref;
    private Set <String> setList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = getSharedPreferences(PREFERENCES, MODE_PRIVATE);

        lvData = findViewById(R.id.lv_list);
        data = new ArrayList<String>();
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        lvData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("Remove")
                        .setMessage("You sure want to delete this?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                data.remove(which_item);
                                dataAdapter.notifyDataSetChanged();
                                savePreference();
                                regAndSort();
                            }
                        })
                        .setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showEdit(which_item);
                            }
                        })
                        .setNeutralButton("Cancel", null).show();
//                savePreference();

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
                savePreference();
            }
        }));

    }

    public void addTask(){
        @SuppressLint("InflateParams")
        final View view = this.getLayoutInflater().inflate(R.layout.dialog_add, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add task");
        builder.setMessage("What do you want?");
        final EditText inputField = new EditText(this);
        builder.setView(inputField);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                data.add(inputField.getText().toString());
//                savePreference();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.create().show();
    }

    private void savePreference(){
        setList = new HashSet<>(data);
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor = pref.edit();
        editor.putStringSet(TODO_LIST, setList);
        editor.apply();
    }

    private void regAndSort(){
        pref = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.apply();
        for(int i = 0;i < data.size(); i++){
            String key = String.valueOf(i);
            editor.putString(key, data.get(i));
        }
    }

    private void showEdit(final int pos){
        View view = View.inflate(this, R.layout.dialog_add, null);
        inputList = view.findViewById(R.id.input_list);
        inputList.setText(dataAdapter.getItem(pos));
        AlertDialog.Builder dial = new AlertDialog.Builder(this);
        dial.setTitle("Want to change the data?");
        dial.setView(view);
        dial.setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               editItem(pos, inputList.getText().toString());
            }
        });
        dial.setNegativeButton("Cancel", null);
        dial.create().show();
    }

    private void editItem(int pos, String newItem){
        data.set(pos, newItem);
        regAndSort();
        dataAdapter.notifyDataSetChanged();
    }
}