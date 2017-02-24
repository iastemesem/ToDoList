package com.iastemesem.todolist;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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
    public static final String SPECIAL = "speciale" ;
    public static final short TRUE_SPECIAL = 1;
    public static final short FALSE_SPECIAL = 0;
    private short special = 0;

    Button add, cancel;
    EditText title, object;
    TextView dateStart, dateEnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        title = (EditText) findViewById(R.id.add_title_ET);
        object = (EditText) findViewById(R.id.add_object_ET);




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

        if (id == R.id.action_special){
//            item.setIcon(R.drawable.ic_bookmark_black_48dp);
//            special = TRUE_SPECIAL;
            if (special == TRUE_SPECIAL ){
                item.setIcon(R.drawable.ic_bookmark_border_black_48dp);
                special = FALSE_SPECIAL;
            }else{
                item.setIcon(R.drawable.ic_bookmark_black_48dp);
                special = TRUE_SPECIAL;
            }
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
            intent.putExtra(SPECIAL, special);
            intent.putExtra("result",1);
            setResult(Activity.RESULT_OK, intent);
            special = FALSE_SPECIAL;
            finish();
        }
    }


    @Override
    public void onClick(View v) {

    }

}
