package com.iastemesem.todolist;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    Button add;
    RecyclerView noteRV;
    LinearLayoutManager layoutManager;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.add_note).setOnClickListener(this);

        noteRV = (RecyclerView) findViewById(R.id.note_RV);
        layoutManager = new LinearLayoutManager(this);
        adapter = new NoteAdapter();
        noteRV.setLayoutManager(layoutManager);
        noteRV.setAdapter(adapter);

        findViewById(R.id.note_RV).setOnLongClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                Log.d("Main---->", "SONO IN ON RESULT");
                String titolo = data.getStringExtra(AddActivity.TITLE);
                String oggetto = data.getStringExtra(AddActivity.OBJECT);
                Note note = new Note (titolo, oggetto);
                adapter.addNote(note);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_note) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
