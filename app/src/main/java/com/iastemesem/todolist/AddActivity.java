package com.iastemesem.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class AddActivity extends Activity implements View.OnClickListener {
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
        add = (Button) findViewById(R.id.add_btnAdd);
        cancel = (Button) findViewById(R.id.add_cancelbtn);

        dateStart.setText(Note.TODAY_DATE);
        dateEnd.setText(Note.DEADLNE_DATE);

        add.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btnAdd){
            if (!title.getText().toString().equals("") && !object.getText().toString().equals("")){
                Intent intent = new Intent();
                intent.putExtra(TITLE, title.getText().toString());
                intent.putExtra(OBJECT,object.getText().toString());
                intent.putExtra("result",1);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }
}
