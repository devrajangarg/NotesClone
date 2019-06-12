package com.example.notesclone;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesclone.activities.UpdateNoteActivity;
import com.example.notesclone.pojo.NotePojo;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    ArrayList<NotePojo> notesList;
    Context context;

    public NotesAdapter(Context context, ArrayList<NotePojo> notesList) {
        this.notesList = notesList;
        this.context = context;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        holder.noteTitle.setText(notesList.get(position).getTitle());
        holder.noteData.setText(notesList.get(position).getNote());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(), UpdateNoteActivity.class);
                intent.putExtra("id", notesList.get(position).get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteData;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.tv_note_title);
            noteData = itemView.findViewById(R.id.tv_note_data);
        }
    }
}
