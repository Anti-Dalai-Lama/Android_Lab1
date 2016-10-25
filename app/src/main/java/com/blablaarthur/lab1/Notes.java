package com.blablaarthur.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import java.util.Date;
import java.util.List;

/**
 * Created by Артур on 04.10.2016.
 */

public class Notes extends AppCompatActivity {

    List<Note> notes = new ArrayList<>(1);
    int noteToDeleteEdit;
    NoteAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);
        Log.d("A_R_T", "OnCreate");
        notes.add(new Note("Note 1", "Note 1 description", 2, Calendar.getInstance(), ""));
        notes.add(new Note("Note 2", "Note 2 description", 3, Calendar.getInstance(), ""));
        notes.add(new Note("Note 3", "Note 3 description", 1, Calendar.getInstance(), ""));
        Calendar c = Calendar.getInstance();
        c.set(2016, 10, 29, 12, 23);
        notes.add(new Note("Note 4", "Note 4 description", 3, c, ""));
        notes.add(new Note("Note 5", "Note 5 description", 2, Calendar.getInstance(), ""));


        notesAdapter = new NoteAdapter(this, notes);
        ListView notesListView = (ListView) findViewById(R.id.notesListView);
        notesListView.setAdapter(notesAdapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Notes.this, "simple press", Toast.LENGTH_LONG).show();
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
        noteToDeleteEdit = info.position;
        //Toast.makeText(Notes.this, info.position, Toast.LENGTH_LONG).show();

        menu.add(0,0,0,"Edit");
        menu.add(0,1,1,"Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Note current = notes.get(noteToDeleteEdit);
        switch (item.getItemId()){
            case 0:
                Intent myIntent = new Intent(Notes.this,
                        CreateNote.class);
                myIntent.setAction("android.intent.myaction.EDIT");
                myIntent.putExtra("Title", current.Title);
                myIntent.putExtra("Description", current.Description);
                myIntent.putExtra("Importance", current.Importance);
                myIntent.putExtra("Date", current.DateTime.get(Calendar.DAY_OF_MONTH)+"."+ current.DateTime.get(Calendar.MONTH) +"."+ current.DateTime.get(Calendar.YEAR));
                myIntent.putExtra("Time", current.DateTime.get(Calendar.HOUR)+":"+ current.DateTime.get(Calendar.MINUTE));
                myIntent.putExtra("Image", current.Image);
                startActivity(myIntent);
                break;
            case 1:
                Log.d("A_R_T", "DELETE");
                notesAdapter.remove(current);
                notesAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflator = getMenuInflater();
        menuInflator.inflate(R.menu.notes_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_note_icon:
                Intent myIntent = new Intent(Notes.this,
                        CreateNote.class);
                myIntent.setAction("android.intent.myaction.CREATE");
                startActivity(myIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
