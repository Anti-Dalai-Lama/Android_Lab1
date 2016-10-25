package com.blablaarthur.lab1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Артур on 24.10.2016.
 */

class NoteAdapter extends ArrayAdapter<Note> {
    public NoteAdapter(Context context, List<Note> notes) {
        super(context, R.layout.note_list_element, notes);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater notesInflater = LayoutInflater.from(getContext());
        View myView = notesInflater.inflate(R.layout.note_list_element, parent, false);

        Note item = getItem(position);
        TextView title = (TextView) myView.findViewById(R.id.titleTextView);
        TextView datetime = (TextView) myView.findViewById(R.id.dateTextView);
        ImageView image = (ImageView) myView.findViewById(R.id.noteImage);
        ImageView imp = (ImageView) myView.findViewById(R.id.impImage);

        title.setText(item.Title);
        datetime.setText(item.DateTime.get(Calendar.DAY_OF_MONTH) + "." + item.DateTime.get(Calendar.MONTH)+ "."
                + item.DateTime.get(Calendar.YEAR) + " " + item.DateTime.get(Calendar.HOUR) + ":" + item.DateTime.get(Calendar.MINUTE));

        switch (item.Importance){
            case 0:
                imp.setImageResource(R.drawable.green_dot);
                break;
            case 1:
                imp.setImageResource(R.drawable.orange_dot);
                break;
            case 2:
                imp.setImageResource(R.drawable.red_dot);
        }

        image.setImageResource(R.drawable.image);
        return myView;

    }
}
