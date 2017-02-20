package com.iastemesem.todolist;

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

    ArrayList <Note> dataSet = new ArrayList<>();

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
        holder.dataCreazione.setText(note.getDataCreazione());
        holder.dataScadenza.setText(note.getDataScadenza());
        holder.object.setText(note.getBody());
        holder.status.setText("Running");
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class NoteVH extends RecyclerView.ViewHolder {
        TextView title, object, dataCreazione, dataScadenza, status;

        public NoteVH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title_tv);
            object = (TextView) itemView.findViewById(R.id.item_object_tv);
            dataCreazione = (TextView) itemView.findViewById(R.id.item_creationDate_tv);
            dataScadenza = (TextView) itemView.findViewById(R.id.item_dataScadenza_tv);
            status = (TextView) itemView.findViewById(R.id.item_status_tv);

        }

    }
}
