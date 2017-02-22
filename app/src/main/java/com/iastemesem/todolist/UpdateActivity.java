package com.iastemesem.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Gianfranco on 22/02/2017.
 */

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    EditText title, object;
    Toolbar toolbar;
    Intent i;
    int posizione;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        i = getIntent();

        title = (EditText)findViewById(R.id.update_title_ET);
        object = (EditText) findViewById(R.id.update_body_ET);

        title.setText(i.getStringExtra(AddActivity.TITLE));
        object.setText(i.getStringExtra(AddActivity.OBJECT));
         posizione = i.getIntExtra(NoteAdapter.POSIZIONE,0);

        toolbar = (Toolbar)findViewById(R.id.update_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_update, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete) {
            deleteNote();
            return true;
        }

        if (id == R.id.action_update) {
            updateNote();
            return true;
        }
        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateNote() {
        Intent intent = new Intent();
        intent.putExtra(AddActivity.TITLE, title.getText().toString());
        intent.putExtra(AddActivity.OBJECT, object.getText().toString());
        intent.putExtra(NoteAdapter.POSIZIONE,posizione);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void deleteNote() {
        Intent intent = new Intent();
        intent.putExtra(NoteAdapter.POSIZIONE,posizione);
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    public void onClick(View v) {

    }
}
