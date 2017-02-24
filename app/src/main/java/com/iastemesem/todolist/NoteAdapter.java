package com.iastemesem.todolist;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Gianfranco on 20/02/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {

    public static final String POSIZIONE = "posizione" ;
    public static final String SPECIALE = "speciale" ;
    ArrayList <Note> dataSet = new ArrayList<>();
    View item;

    public void setDataSet (ArrayList<Note> note) {
        dataSet = note;
        notifyDataSetChanged();
    }

    public void addNote (Note note) {
        dataSet.add(note);
        notifyDataSetChanged();
    }

    public void updateNote(String titolo, String oggetto, int posizione, short speciale) {
        for (Note n:dataSet){
            if (n.getId()==posizione){
                n.setTitle(titolo);
                n.setBody(oggetto);
                n.setIsSpecial(speciale);
            }
        }
    }

//    public void deleteNote(int posizione) {
//        dataSet.remove(posizione);
//        notifyItemRemoved(posizione);
//    }


    public Note getNote(int posizione) {
        for (Note n : dataSet){
            if (n.getId() == posizione){
                return n;
            }
        }
        return new Note();
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
        Log.d("ADAPTER", String.valueOf(note.getId())+"--->"+String.valueOf(note.getIsSpecial()));
        if (note.getIsSpecial() == 1) {
            holder.img.setVisibility(View.VISIBLE);
        }
        else holder.img.setVisibility(View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }




    public class NoteVH extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView title, object, status;
        View item;
        ImageButton img;
        public NoteVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title_tv);
            object = (TextView) itemView.findViewById(R.id.item_object_tv);
            item = itemView.findViewById(R.id.item_note_activity);
            item.setOnLongClickListener(this);
            img = (ImageButton) itemView.findViewById(R.id.item_btn_special);

        }

        @Override
        public void onClick(View v) {

        }

        @Override
        public boolean onLongClick(View v) {
            if (v.getId() == R.id.item_note_activity){
                Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                intent.putExtra(AddActivity.TITLE, title.getText().toString() );
                intent.putExtra(AddActivity.OBJECT, object.getText().toString());
                intent.putExtra(POSIZIONE, dataSet.get(getAdapterPosition()).getId());
                intent.putExtra(SPECIALE, dataSet.get(getAdapterPosition()).getIsSpecial());
                AppCompatActivity info = (AppCompatActivity) itemView.getContext();
                info.startActivityForResult(intent, 2);
            }
            return false;
        }
    }
}
