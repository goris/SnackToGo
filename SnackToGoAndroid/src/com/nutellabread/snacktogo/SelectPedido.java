package com.nutellabread.snacktogo;

import android.os.Bundle;
import android.app.ListActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SelectPedido extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pedido);
        ArrayAdapter<String> adapter = 
        		new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add("Chilaquiles");
        adapter.add("Hamburguesas");
        adapter.add("Papas");
        adapter.add("Huevos");
        setListAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_select_pedido, menu);
        return true;
    }
}
