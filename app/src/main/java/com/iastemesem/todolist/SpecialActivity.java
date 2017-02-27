package com.iastemesem.todolist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Gianfranco on 27/02/2017.
 */

public class SpecialActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView noteRV;
    RecyclerView.LayoutManager layoutManager;
    NoteAdapter adapter;
    Toolbar toolbar;
    Databasehandler dbHandler ;
    RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_special);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.special_layout);

        noteRV = (RecyclerView) findViewById(R.id.note_RV);
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        adapter = new NoteAdapter();
        noteRV.setLayoutManager(layoutManager);
        noteRV.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

    }
}
