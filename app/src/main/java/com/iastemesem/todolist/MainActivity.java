package com.iastemesem.todolist;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    Button add;
    RecyclerView noteRV;
    LinearLayoutManager layoutManager;
    NoteAdapter adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.add_note).setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        noteRV = (RecyclerView) findViewById(R.id.note_RV);
        layoutManager = new LinearLayoutManager(this);
        adapter = new NoteAdapter();
        noteRV.setLayoutManager(layoutManager);
        noteRV.setAdapter(adapter);


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
        }else if (requestCode == 2){
            if (resultCode == RESULT_OK){
                String titolo = data.getStringExtra(AddActivity.TITLE);
                String oggetto = data.getStringExtra(AddActivity.OBJECT);
                int posizione = data.getIntExtra(NoteAdapter.POSIZIONE,0);
                adapter.updateNote(titolo, oggetto, posizione);
            }if(resultCode == RESULT_CANCELED){
                int posizione = data.getIntExtra(NoteAdapter.POSIZIONE,0);
                adapter.deleteNote(posizione);
            }
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_note) {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivityForResult(intent, 1);
        } else if (v.getId() == R.id.item_note_activity){
//            TextView title = (TextView)item.findViewById(R.id.item_title_tv);
//            TextView object = (TextView)item.findViewById(R.id.item_object_tv);
//            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
//            intent.putExtra(AddActivity.TITLE, title.getText().toString() );
//            intent.putExtra(AddActivity.OBJECT, object.getText().toString());
//            startActivityForResult(intent,2);
        }


    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
