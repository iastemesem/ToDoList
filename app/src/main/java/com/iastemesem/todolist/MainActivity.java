package com.iastemesem.todolist;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    RecyclerView noteRV;
    RecyclerView.LayoutManager layoutManager;
    NoteAdapter adapter;
    Toolbar toolbar;
    Databasehandler dbHandler ;
    RelativeLayout mRelativeLayout;
    boolean onSpecial = false;
    DemoView demoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.main_layout);

        noteRV = (RecyclerView) findViewById(R.id.note_RV);
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        adapter = new NoteAdapter();
        noteRV.setLayoutManager(layoutManager);
        noteRV.setAdapter(adapter);

        dbHandler = new Databasehandler(this);
        ArrayList <Note> notes = dbHandler.getAllNotes();
        adapter.setDataSet(notes);

        findViewById(R.id.add_note).setOnClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            if (resultCode == RESULT_OK) {
                String titolo = data.getStringExtra(AddActivity.TITLE);
                String oggetto = data.getStringExtra(AddActivity.OBJECT);
                short speciale = (short) data.getShortExtra(AddActivity.SPECIAL, (short)0);
                Note note = new Note (titolo, oggetto, (short) speciale);
                dbHandler.addNote(note);
                adapter.setDataSet(dbHandler.getAllNotes());
                toolbar.getMenu().findItem(R.id.action_heart).setIcon(R.drawable.ic_bookmark_border_black_48dp);
                onSpecial = false;
            }
        }else if (requestCode == 2){
            if (resultCode == RESULT_OK){
                String titolo = data.getStringExtra(AddActivity.TITLE);
                String oggetto = data.getStringExtra(AddActivity.OBJECT);
                short speciale = data.getShortExtra(NoteAdapter.SPECIALE,(short)0);
                int posizione = data.getIntExtra(NoteAdapter.POSIZIONE,0);
                adapter.updateNote(titolo, oggetto, posizione,speciale);
                dbHandler.updateNote(adapter.getNote(posizione));
                adapter.setDataSet(dbHandler.getAllNotes());
                toolbar.getMenu().findItem(R.id.action_heart).setIcon(R.drawable.ic_bookmark_border_black_48dp);
                onSpecial = false;
            }if(resultCode == RESULT_CANCELED){
                int posizione = data.getIntExtra(NoteAdapter.POSIZIONE,0);
                dbHandler.deletNote(adapter.getNote(posizione));
                adapter.setDataSet(dbHandler.getAllNotes());
                toolbar.getMenu().findItem(R.id.action_heart).setIcon(R.drawable.ic_bookmark_border_black_48dp);
                onSpecial = false;
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete_all) {
            showDeleteAllDialog();
            return true;
        }
        if (id == R.id.action_draw){
            demoview = new DemoView(this);
            setContentView(demoview);
        }
        if (id == R.id.action_heart){
            if (onSpecial == false){
                item.setIcon(R.drawable.ic_bookmark_black_48dp);
                adapter.setDataSet(dbHandler.getSpecialNote());
                onSpecial = true;
            }else {
                item.setIcon(R.drawable.ic_bookmark_border_black_48dp);
                adapter.setDataSet(dbHandler.getAllNotes());
                onSpecial = false;
            }

     }

        return super.onOptionsItemSelected(item);
    }

    public void showDeleteAllDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setTitle(R.string.deleteAll);
        dialogBuilder.setMessage(R.string.deleteAsk);

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == DialogInterface.BUTTON_POSITIVE) {
                    dbHandler.deleteAllNote();
                    adapter.setDataSet(dbHandler.getAllNotes());
                }

            }
        };
        dialogBuilder.setPositiveButton(R.string.done,dialogClickListener);

        dialogBuilder.setNegativeButton(R.string.cancel,dialogClickListener);
        AlertDialog b = dialogBuilder.create();
        b.show();


    }



    private class DemoView extends View {
        public DemoView(Context context){
            super(context);
        }

        @Override protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            // custom drawing code here
            // remember: y increases from top to bottom
            // x increases from left to right
            int x = 0;
            int y = 0;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);

            canvas.save();
            canvas.translate(100, 200);

            // make the entire canvas white
            canvas.drawColor(Color.WHITE);

            // draw some text using STROKE style
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(1);
            paint.setColor(Color.MAGENTA);
            paint.setTextSize(100);
            canvas.drawText("Style.STROKE", 0, 0, paint);

            canvas.translate(0, 200);

            // draw some text using FILL style
            paint.setStyle(Paint.Style.FILL);
            //turn antialiasing on
            paint.setAntiAlias(true);
            //paint.setTextSize(30);
            canvas.drawText("Style.FILL", 0, 0, paint);

            canvas.translate(0, 200);

            // draw some rotated text
            // get text width and height
            // set desired drawing location
            x = 75;
            y = 185;
            paint.setColor(Color.GRAY);
            //paint.setTextSize(25);
            String str2rotate = "Rotated!";

            // draw bounding rect before rotating text
            Rect rect = new Rect();
            paint.getTextBounds(str2rotate, 0, str2rotate.length(), rect);
            canvas.translate(x, y);
            paint.setStyle(Paint.Style.FILL);
            // draw unrotated text
            canvas.drawText("!Rotated", 0, 0, paint);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(rect, paint);
            // undo the translate
            canvas.translate(-x, -y);

            // rotate the canvas on center of the text to draw
            canvas.rotate(-45, x + rect.exactCenterX(),
                    y + rect.exactCenterY());
            // draw the rotated text
            paint.setStyle(Paint.Style.FILL);
            canvas.drawText(str2rotate, x, y, paint);

            //undo the translation and rotation
            canvas.restore();
        }
    }
}

