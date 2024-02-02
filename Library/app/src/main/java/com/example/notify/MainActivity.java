package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText title,id,author,year;
    Button load,upload;
    RecyclerView recyclerView;
    ArrayList<books> book = new ArrayList<>();
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title=findViewById(R.id.bookName);
        id=findViewById(R.id.id);
        author=findViewById(R.id.Author);
        year=findViewById(R.id.year);
        upload=findViewById(R.id.Upload);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eTitle = title.getText().toString();
                String eId = id.getText().toString();
                String eAuthor=author.getText().toString();
                String eYear = year.getText().toString();

                if(eTitle.isEmpty() || eId.isEmpty() || eAuthor.isEmpty() || eYear.isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please Enter All Fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    // class to add values in the database
                    ContentValues values = new ContentValues();

                    // fetching text from user
                    values.put(MyContentProvider.id,eId);
                    values.put(MyContentProvider.title,eTitle);
                    values.put(MyContentProvider.Author,eAuthor);
                    values.put(MyContentProvider.year,eYear);

                    // inserting into database through content URI
                    getContentResolver().insert(MyContentProvider.CONTENT_URI, values);

                    // displaying a toast message
                    Toast.makeText(getApplicationContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
                }
                recyclerView = findViewById(R.id.recycleView);
                // creating a cursor object of the
                // content URI
                Cursor cursor = getContentResolver().query(MyContentProvider.CONTENT_URI,
                        null, null, null, null);

                // iteration of the cursor
                // to print whole table

                if(cursor.moveToFirst()) {
                    String i,t,a,y;
                    while (!cursor.isAfterLast()) {
                        i ="ID : "+ cursor.getString(cursor.getColumnIndex(MyContentProvider.id));
                        t = cursor.getString(cursor.getColumnIndex(MyContentProvider.title));
                        a = "Author : "+cursor.getString(cursor.getColumnIndex(MyContentProvider.Author));
                        y = "Published Year : "+cursor.getString(cursor.getColumnIndex(MyContentProvider.year));
                        book.add(new books(i,t,a,y,R.drawable.books));
                        cursor.moveToNext();
                    }
                    Books_RecyclerAdapter adapter = new Books_RecyclerAdapter(getApplicationContext(),book);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyItemInserted(book.size()-1);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }
            }
        });

            }

    }