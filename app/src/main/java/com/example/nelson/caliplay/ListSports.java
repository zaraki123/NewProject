package com.example.nelson.caliplay;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Zaraki on 19/11/2015.
 */

public class ListSports extends ListActivity {

    private TextView selection;
    private static final String[] items = {"None", "Arti marziali", "Atletica", "Basket",
            "Box", "Calcio", "Canottaggio", "Ciclismo", "Danza", "Ginnastica", "Hockey",
            "Judo", "Karate", "Nuoto", "Pallavolo", "Sci",
            "Tennis", "Triathlon"};

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.list_sports);
        setListAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items));
        selection = (TextView) findViewById(R.id.selection);
    }

    @Override
    public void onListItemClick(ListView parent, View v, int position, long id) {
        selection.setText(items[position]);
        String sport = (String) getListAdapter().getItem(position);
        Bundle extras = getIntent().getExtras();
        String sportType = extras.getString("sportType");
        Intent returnSport = new Intent();
        returnSport.putExtra("selectedSport", sport);
        returnSport.putExtra("sportType", sportType);
        setResult(Activity.RESULT_OK, returnSport);
        finish();

    }
}




