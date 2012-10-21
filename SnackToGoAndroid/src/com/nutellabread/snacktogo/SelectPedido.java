package com.nutellabread.snacktogo;

import java.io.*;
import java.net.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

public class SelectPedido extends ListActivity {
    static final String KEY_ARTICULO = "articulo"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_NOMBRE = "nombre";
    static final String KEY_DESCRIPCION = "descripcion";
    static final String KEY_PRECIO = "precio";
    static final String KEY_CANTIDAD = "cantidad";
    static final String KEY_TOTAL = "total";
    static final String KEY_THUMB_URL = "thumb_url";
    private Articulo[] articulos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pedido);
        Socket socket = null;
		PrintWriter alServidor;
		DataInputStream delServidor;
		String json="";
		try {
			socket = new Socket("10.20.98.87",6666);
			alServidor = new PrintWriter(socket.getOutputStream(),true);
			delServidor = new DataInputStream(socket.getInputStream());
			alServidor.println("articulos");
			json=delServidor.readLine();
			delServidor.close();
			alServidor.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		articulos = (new Gson()).fromJson(json, Articulo[].class);
		updatePrecios();
		final SelectPedido este=this;
        ((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		LinkedList<Articulo> pedido=new LinkedList();
        		for(Articulo articulo : articulos){
        			if(articulo.cantidad>0){
        				Articulo temp=new Articulo();
        				temp.id=articulo.id;
        				temp.nombre=articulo.nombre;
        	        	temp.cantidad=articulo.cantidad;
        	        	temp.precio=articulo.precio;
        	        	pedido.add(temp);
        			}
        		}
        		Intent k = new Intent(SelectPedido.this, ConfirmarPedido.class);
        		k.putExtra("pedido", (new Gson()).toJson(pedido));
        		startActivity(k);
            	}
        	  });
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, final long id){
    	AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Cantidad");
        alert.setMessage("Selecciona la cantidad que deseas.");

        // Set an EditText view to get user input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        alert.setView(input);
        input.setText(articulos[(int) id].cantidad+"");
        alert.setPositiveButton("Cambiar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    	articulos[(int) id].cantidad = Integer.parseInt(input.getText().toString());
                    	updatePrecios();
                    }
                });

        alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // do nothing
            }
        });
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_select_pedido, menu);
        return true;
    }
    
    private void updatePrecios(){
        ArrayList<HashMap<String, String>> listaArticulos = new ArrayList<HashMap<String, String>>();
        float sum=0;
        for(Articulo articulo : articulos){
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put(KEY_NOMBRE,  articulo.nombre);
        	map.put(KEY_DESCRIPCION,  articulo.descripcion);
        	map.put(KEY_PRECIO,  (NumberFormat.getCurrencyInstance(Locale.US)).format(articulo.precio));
        	map.put(KEY_CANTIDAD,  "x"+articulo.cantidad);
        	map.put(KEY_TOTAL,  (NumberFormat.getCurrencyInstance(Locale.US)).format(articulo.precio*articulo.cantidad));
        	map.put(KEY_THUMB_URL,  "http://10.20.98.87:8887/"+articulo.id+".jpg");
        	listaArticulos.add(map);
        	sum+=articulo.precio*articulo.cantidad;
        }
        ((TextView)findViewById(R.id.textView1)).setText("Total: "+(NumberFormat.getCurrencyInstance(Locale.US)).format(sum));
        setListAdapter(new ListArticuloAdapter(this, listaArticulos));
    }
}
