package com.vis.merna.travelxapp.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vis.merna.travelxapp.R;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    Context context;
    ArrayList<String> notes;

    public NotesRecyclerAdapter(Context context, ArrayList<String> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_header_item, parent, false);
            return new NotesHeaderViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_list_item, parent, false);
            return new NotesItemViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof NotesHeaderViewHolder) {
            final NotesHeaderViewHolder header = (NotesHeaderViewHolder) holder;
            final EditText noteEditText = header.noteEditText;

            if (notes.size() < 4) header.addNoteButton.setEnabled(true);

            header.addNoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (notes.size() < 4) {
                        notes.add(noteEditText.getText().toString());
                        notifyDataSetChanged();
                        noteEditText.setText("");
                    }
                    if (notes.size() == 4) header.addNoteButton.setEnabled(false);
                }
            });

        } else if (holder instanceof NotesItemViewHolder) {
            final NotesItemViewHolder item = (NotesItemViewHolder) holder;
            final int pos = position;
            String currentItem = getItem(position - 1);
            item.noteTextView.setText(currentItem);
            item.deleteNoteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notes.remove(pos - 1);
                    notifyDataSetChanged();

                }
            });
        }

    }

    private String getItem(int position) {
        return notes.get(position);
    }

    @Override
    public int getItemCount() {
        return notes.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}
