package com.nutellabread.snacktogo;

import java.io.*;
import java.net.*;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SelectCafeteria extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_cafeteria);
        ArrayAdapter <String> adapter =
        		  new ArrayAdapter <String> (this, android.R.layout.simple_list_item_1);
        adapter.add("Centrales");
        adapter.add("Jubileo");
        adapter.add("Borrego");
        adapter.add("Carreta");
        adapter.add("Comedor de Estudiantes");
        setListAdapter(adapter);
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	Intent k = new Intent(SelectCafeteria.this, Login.class);
    	startActivity(k);
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_select_cafeteria, menu);
        return true;
    }
}
