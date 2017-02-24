package com.iastemesem.todolist;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import static android.content.RestrictionsManager.RESULT_DENIED;

/**
 * Created by Gianfranco on 22/02/2017.
 */

public class UpdateActivity extends AppCompatActivity implements View.OnClickListener {
    EditText title, object;
    ImageButton img ;
    Toolbar toolbar;
    Intent i;
    short speciale = 0;
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
        speciale = i.getShortExtra(NoteAdapter.SPECIALE, (short)0);

        Log.d("UPDATE", String.valueOf(posizione));

        toolbar = (Toolbar)findViewById(R.id.update_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_update, menu);
//       MenuItem item =  menu.getItem(R.id.update_action_special);
        if (speciale == (short)1){
            toolbar.getMenu().findItem(R.id.update_action_special).setIcon(R.drawable.ic_bookmark_black_48dp);
        }else  toolbar.getMenu().findItem(R.id.update_action_special).setIcon(R.drawable.ic_bookmark_border_black_48dp);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.update_action_special){
            Log.d("MENU", String.valueOf(speciale));
           // item.setIcon(R.drawable.ic_bookmark_black_48dp);
            if (speciale == AddActivity.TRUE_SPECIAL){
                Log.d("aaaaaaaaaaaaaaaaaaa","rtgyhujikol");
                toolbar.getMenu().findItem(R.id.update_action_special).setIcon(R.drawable.ic_bookmark_border_black_48dp);
                //item.setIcon(R.drawable.ic_bookmark_black_48dp);
                speciale = AddActivity.FALSE_SPECIAL;
                return true;
            }
            if (speciale == AddActivity.FALSE_SPECIAL){
                toolbar.getMenu().findItem(R.id.update_action_special).setIcon(R.drawable.ic_bookmark_black_48dp);
               // item.setIcon(R.drawable.ic_bookmark_border_black_48dp);
                speciale = AddActivity.TRUE_SPECIAL;
                return true;
            }

        }

        if (id == R.id.action_delete) {
            deleteNote();
            return true;
        }

        if (id == R.id.action_update) {
            updateNote();
            return true;
        }

        if(id == android.R.id.home){
            Intent intent = new Intent();
            setResult(RESULT_DENIED, intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateNote() {
        Intent intent = new Intent();
        intent.putExtra(AddActivity.TITLE, title.getText().toString());
        intent.putExtra(AddActivity.OBJECT, object.getText().toString());
        intent.putExtra(NoteAdapter.POSIZIONE,posizione);
        intent.putExtra(NoteAdapter.SPECIALE, speciale);
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
