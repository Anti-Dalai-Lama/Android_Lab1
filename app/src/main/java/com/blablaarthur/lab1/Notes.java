package com.blablaarthur.lab1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Артур on 04.10.2016.
 */

public class Notes extends AppCompatActivity {

    int noteToDeleteEditId = 0;
    NoteAdapter notesAdapter = null;

    android.widget.SearchView search_view;
    MenuItem search_menu_item;
    private static final int CREATE_NOTE = 101;
    private static final int EDIT_NOTE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        Log.d("A_R_T", "OnCreate");
        List<Note> notes = new ArrayList<>(1);
        notes.add(new Note(1, "Note 1", "Note 1 description", 1, Calendar.getInstance(), ""));
        notes.add(new Note(2, "Note 2", "Note 2 description", 2, Calendar.getInstance(), ""));
        notes.add(new Note(3, "Note 3", "Note 3 description", 0, Calendar.getInstance(), ""));
        Calendar c = Calendar.getInstance();
        c.set(2016, 9, 5, 12, 3);
        notes.add(new Note(4, "Note 4", "Note 4 description", 2, c, ""));
        notes.add(new Note(5, "Note 5", "Note 5 description", 1, Calendar.getInstance(), ""));


        notesAdapter = new NoteAdapter(this, notes);
        ListView notesListView = (ListView) findViewById(R.id.notesListView);
        notesListView.setAdapter(notesAdapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note current = notesAdapter.getItem(position);
                Intent myIntent = new Intent(Notes.this,
                        CreateNote.class);
                myIntent.setAction("android.intent.myaction.WATCH");
                myIntent.putExtra("Position", position);
                myIntent.putExtra("Title", current.Title);
                myIntent.putExtra("Description", current.Description);
                myIntent.putExtra("Importance", current.Importance);
                myIntent.putExtra("Date", CalendarConverter.getDate(current.DateTime));
                myIntent.putExtra("Time", CalendarConverter.getTime(current.DateTime));
                myIntent.putExtra("Image", current.Image);
                startActivity(myIntent);
            }
        });

        registerForContextMenu(notesListView);

        ActionBar bar = getSupportActionBar();
        //bar.setLogo(R.drawable.ic_action_name);
        bar.setHomeButtonEnabled(true);
        bar.setDisplayUseLogoEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        Log.d("A_R_T", info.position + "");
        noteToDeleteEditId = notesAdapter.getItem(info.position).Id;
        Log.d("A_R_T", noteToDeleteEditId + "");

        menu.add(0,0,0,"Edit");
        menu.add(0,1,1,"Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Note current = notesAdapter.getItemById(noteToDeleteEditId);
        switch (item.getItemId()){
            case 0:

                Intent myIntent = new Intent(Notes.this,
                        CreateNote.class);
                myIntent.setAction("android.intent.myaction.EDIT");
                myIntent.putExtra("Id", current.Id);
                myIntent.putExtra("Title", current.Title);
                myIntent.putExtra("Description", current.Description);
                myIntent.putExtra("Importance", current.Importance);
                myIntent.putExtra("Date", CalendarConverter.getDate(current.DateTime));
                myIntent.putExtra("Time", CalendarConverter.getTime(current.DateTime));
                myIntent.putExtra("Image", current.Image);
                search_view.setQuery("", false);
                search_view.setIconified(true);
                startActivityForResult(myIntent, EDIT_NOTE);
                break;
            case 1:
                Log.d("A_R_T", "DELETE");
                notesAdapter.removeById(current.Id);
                notesAdapter.notifyDataSetChanged();
                search_view.setQuery("", false);
                search_view.setIconified(true);
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflator = getMenuInflater();
        menuInflator.inflate(R.menu.notes_menu, menu);
        search_menu_item = menu.findItem(R.id.searchView);
        search_view = (android.widget.SearchView) search_menu_item.getActionView();
        search_view.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                notesAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_note_icon:
                Intent myIntent = new Intent(Notes.this,
                        CreateNote.class);
                myIntent.setAction("android.intent.myaction.CREATE");
                startActivityForResult(myIntent, CREATE_NOTE);
            case R.id.filter_notes:
                AlertDialog.Builder adb = new AlertDialog.Builder(this);
                adb.setTitle("Importance");
                adb.setSingleChoiceItems(new CharSequence[]{"High", "Medium" ,"Low" ,"None"}, -1, selectImpFilter);
                adb.setPositiveButton("Filter", selectImpFilter);
                adb.setNegativeButton("Cancel", selectImpFilter);
                adb.create();
                adb.show();
        }
        return super.onOptionsItemSelected(item);
    }

    DialogInterface.OnClickListener selectImpFilter = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            ListView lv = ((AlertDialog) dialog).getListView();
            if(which == Dialog.BUTTON_POSITIVE){
                List<Note> filter = new ArrayList<>();
                switch (lv.getCheckedItemPosition()){
                    case 0:
                        for(Note n : notesAdapter.original){
                            if(n.Importance == 2)
                                filter.add(n);
                        }
                        notesAdapter.filtered = filter;
                        break;
                    case 1:
                        for(Note n : notesAdapter.original){
                            if(n.Importance == 1)
                                filter.add(n);
                        }
                        notesAdapter.filtered = filter;
                        break;
                    case 2:
                        for(Note n : notesAdapter.original){
                            if(n.Importance == 0)
                                filter.add(n);
                        }
                        notesAdapter.filtered = filter;
                        break;
                    case 3:
                        notesAdapter.filtered = new ArrayList<Note>(notesAdapter.original);
                        break;
                }
                notesAdapter.notifyDataSetChanged();
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Calendar c = Calendar.getInstance();
            String[] date = data.getStringExtra("Date").split("\\.");
            String[] time = data.getStringExtra("Time").split(":");
            Log.d("A_R_T", date[0]);
            c.set(Integer.parseInt(date[2]), Integer.parseInt(date[1])-1, Integer.parseInt(date[0]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));
            Note note_new = new Note(data.getIntExtra("Id", 0), data.getStringExtra("Title"), data.getStringExtra("Description"), data.getIntExtra("Importance", 0), c, data.getStringExtra("Image"));

            if(requestCode == CREATE_NOTE && resultCode == RESULT_OK){
                notesAdapter.add(note_new);
                notesAdapter.notifyDataSetChanged();
            }
            else if(requestCode == EDIT_NOTE && resultCode == RESULT_OK){
                int id = data.getIntExtra("Id", 0);
                notesAdapter.changeById(id, note_new);
                notesAdapter.notifyDataSetChanged();
            }
        }
    }
}
