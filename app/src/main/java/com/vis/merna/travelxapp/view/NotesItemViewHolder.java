package com.vis.merna.travelxapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vis.merna.travelxapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class NotesItemViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.note_text_view)
    TextView noteTextView;
    @BindView(R.id.delete_note_button)
    ImageButton deleteNoteButton;

    public NotesItemViewHolder(View v) {
        super(v);
        ButterKnife.bind(this, v);
    }
}
