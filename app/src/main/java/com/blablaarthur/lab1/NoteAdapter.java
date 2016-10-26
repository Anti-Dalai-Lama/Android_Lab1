package com.blablaarthur.lab1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Артур on 24.10.2016.
 */

class NoteAdapter extends ArrayAdapter<Note> {

    List<Note> original;
    List<Note> filtered;
    ItemFilter mFilter = new ItemFilter();

    public NoteAdapter(Context context, List<Note> notes) {
        super(context, R.layout.note_list_element, notes);
        original = notes;
        filtered = notes;
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



        datetime.setText( CalendarConverter.getDateTime(item.DateTime));

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

    public int getId(){
        int maxId = 0;
        for(Note note: original){
            if(note.Id > maxId)
                maxId = note.Id;
        }
        return maxId+1;
    }

    public Note getItemById(int id){
        for(Note note: original)
            if (note.Id == id) {
                return note;
            }
        return null;
    }

    @Override
    public void add(Note object) {
        filtered.add(object);
    }

    @Nullable
    @Override
    public Note getItem(int position) {
        return filtered.get(position);
    }

    public Filter getFilter() {
        return mFilter;
    }

    @Override
    public int getCount() {
        return filtered.size();
    }

//    public void removeAt(int pos){
//        filtered.remove(pos);
//    }

    public void removeById(int id){
        int pos = -1;
        for(int i = 0; i < filtered.size(); i++){
            if(filtered.get(i).Id == id)
                pos = i;
        }
        if(pos >= 0)
            filtered.remove(pos);
        pos = -1;
        for(int i = 0; i < original.size(); i++){
            if(original.get(i).Id == id)
                pos = i;
        }
        if(pos >= 0)
            original.remove(pos);
    }

    public void changeById(int id, Note note_new){
        int pos = -1;
        for(int i = 0; i < filtered.size(); i++){
            if(filtered.get(i).Id == id)
                pos = i;
        }
        if(pos >= 0)
            filtered.set(pos, note_new);
        pos = -1;
        for(int i = 0; i < original.size(); i++){
            if(original.get(i).Id == id)
                pos = i;
        }
        if(pos >= 0)
            original.set(pos, note_new);
    }

    private class ItemFilter extends Filter {
        @Override
        protected Filter.FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            Filter.FilterResults results = new Filter.FilterResults();

            final List<Note> list = original;

            int count = list.size();
            final List<Note> nlist = new ArrayList<Note>(count);

            Note filterableNote ;

            for (int i = 0; i < count; i++) {
                filterableNote = list.get(i);
                if (filterableNote.Description.toLowerCase().contains(filterString) ||
                        filterableNote.Title.toLowerCase().contains(filterString)) {
                    nlist.add(filterableNote);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered = (List<Note>) results.values;
            notifyDataSetChanged();
        }

    }
}
