package com.codingelab.tutorial;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Result extends AppCompatActivity {
    // Definitions of all elements on the interface
// mydb defines a data base
    DBHelper mydb;
    EditText Search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final ListView resList = findViewById(R.id.listView);
        Search=(EditText) findViewById(R.id.Search);
        mydb = new DBHelper(this);
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.colmn_row,
                mydb.getData(),
                new String[]{"id", "name"},
                new int[]{R.id.FirstText, R.id.SecondText});

        resList.setAdapter(adapter);

        resList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // It decreases from one interface to another
                Intent activity = new Intent(Result.this, UpdataActivity.class);
                final TextView idText = view.findViewById(R.id.FirstText);
                final TextView nameText = view.findViewById(R.id.SecondText);

              // take data and converts it to text
                activity.putExtra("id",idText.getText().toString());
                activity.putExtra("name",nameText.getText().toString());

                //Implementation
                startActivity(activity);
            }
        });
        //Here is the right to delete
        resList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view,
                                           final int pos, long id) {
              // Definition
                AlertDialog.Builder alert = new AlertDialog.Builder(Result.this);
                final TextView idText = view.findViewById(R.id.FirstText);
                // text
                alert.setTitle("Delete!");
                // Message
                alert.setMessage("Do you really want delete ?");
                // chose
                alert.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                        // delete by id
                                mydb.deleteContact(Integer.parseInt(idText.getText().toString()));
                                // Determine on the first and second row
                                SimpleCursorAdapter adapter = new SimpleCursorAdapter(Result.this,
                                        R.layout.colmn_row,
                                        mydb.getData(),
                                        new String[]{"id", "name"},
                                        new int[]{R.id.FirstText, R.id.SecondText});

                                resList.setAdapter(adapter);

                            }

                        });
                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(Result.this, "No", Toast.LENGTH_SHORT).show();
                            }
                        });


                alert.show();

                return true;
            }
        });
        // search
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int agr3) {
                List<Contacts> cont = mydb.SearchWords(Search.getText().toString());
                SearchAdapter mydata = new SearchAdapter( Result.this, cont);
                resList.setAdapter(mydata);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

