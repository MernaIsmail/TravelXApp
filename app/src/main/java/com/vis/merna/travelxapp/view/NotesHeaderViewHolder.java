package com.vis.merna.travelxapp.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vis.merna.travelxapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

class NotesHeaderViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.note_edit_text)
    EditText noteEditText;
    @BindView(R.id.add_note_button)
    Button addNoteButton;

    public NotesHeaderViewHolder(View v) {
        super(v);
        ButterKnife.bind(this,v);
    }
}
