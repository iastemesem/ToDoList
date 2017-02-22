package com.iastemesem.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {

    public static final String POSIZIONE = "posizione" ;
    ArrayList <Note> dataSet = new ArrayList<>();
    View item;

    public void addNote (Note note) {
        dataSet.add(0, note);
        notifyItemInserted(0);
    }

    @Override
    public NoteVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_todo, parent, false);
        return new NoteVH(v);
    }

    @Override
    public void onBindViewHolder(NoteVH holder, int position) {
        Note note = dataSet.get(position);
        holder.title.setText(note.getTitle());
        holder.object.setText(note.getBody());
        holder.status.setText("Running");
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void updateNote(String titolo, String oggetto, int posizione) {
        Note note = dataSet.get(posizione);
        note.setTitle(titolo);
        note.setBody(oggetto);
        dataSet.set(posizione, note);
        notifyItemChanged(posizione);
    }

    public void deleteNote(int posizione) {
        dataSet.remove(posizione);
        notifyItemRemoved(posizione);
    }

    public class NoteVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, object, status;
        View item;
        public NoteVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title_tv);
            object = (TextView) itemView.findViewById(R.id.item_object_tv);
            status = (TextView) itemView.findViewById(R.id.item_status_tv);
            item = itemView.findViewById(R.id.item_note_activity);
            item.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.item_note_activity){
                Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                intent.putExtra(AddActivity.TITLE, title.getText().toString() );
                intent.putExtra(AddActivity.OBJECT, object.getText().toString());
                intent.putExtra(POSIZIONE, getAdapterPosition());
                AppCompatActivity info = (AppCompatActivity) itemView.getContext();
                info.startActivityForResult(intent, 2);
            }
        }
    }
}
