package com.codepath.simpletodo;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static android.os.FileUtils.*;


public class MainActivity extends AppCompatActivity {

    ArrayList<String> items;
    ArrayAdapter<String> itemsAdapter;
    ListView lvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readItems();
        itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        lvItems = (ListView) findViewById(R.id.IvItems);
        lvItems.setAdapter(itemsAdapter);

        // mock data
        //items.add("First item");
        //items.add("Second item");

        setupListViewListener();
    }

    private void readItems() {
    }

    private void writeItems() {
    }

    public void onAddItem(View v) {
        EditText editText2 = (EditText) findViewById(R.id.editText2);
        String itemText = editText2.getText().toString();
        itemsAdapter.add(itemText);
        editText2.setText("");
        writeItems();
        Toast.makeText(getApplicationContext(), "Item add to list", Toast.LENGTH_SHORT).show();
    }

    private void setupListViewListener() {
        Log.i("MainActivity", "Setting up lister view ");
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("MainActivity", "Item removed from list: " + position);
                itemsAdapter.remove(String.valueOf(position));
                itemsAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }

            private void writeItems() {
            }
        });
    }

    private File getDataFile() {
        return new file(getFilesDir(), "todo.txt");
    }

    private class file extends File {
        public file(File filesDir, String s) {
            super("");


            class MainActivity2 extends Activity {


                private void readItems() {
                    try {
                        items = new ArrayList<String>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
                    } catch (IOException e) {
                        Log.e("MainActivity1", "Error reading file", e);
                        items = new ArrayList<String>();

                    }
                }

                private void writeItems() {
                    try {
                        FileUtils.writeLines(getDataFile(), items);
                    } catch (IOException e) {
                        Log.e("MainActivity", "Error writing file", e);
                    }
                }
            }
        }
    }
}
