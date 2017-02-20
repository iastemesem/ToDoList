package com.iastemesem.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TITLE = "title";
    public static final String OBJECT = "oggetto";
    Button add, cancel;
    EditText title, object;
    TextView dateStart, dateEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        dateEnd = (TextView)findViewById(R.id.add_dateend);
        dateStart = (TextView) findViewById(R.id.add_todayDate);
        title = (EditText) findViewById(R.id.add_title_ET);
        object = (EditText) findViewById(R.id.add_object_ET);

        dateStart.setText(Note.TODAY_DATE);
        dateEnd.setText(Note.DEADLNE_DATE);


        Toolbar toolbar = (Toolbar) findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);

        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_confirm) {
            confirmNote();
            return true;
        }
        if(id == android.R.id.home){
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void confirmNote() {
        if (!title.getText().toString().equals("") && !object.getText().toString().equals("")){
            Intent intent = new Intent();
            intent.putExtra(TITLE, title.getText().toString());
            intent.putExtra(OBJECT,object.getText().toString());
            intent.putExtra("result",1);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }


    @Override
    public void onClick(View v) {

    }
}
