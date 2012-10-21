package com.nutellabread.snacktogo;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ConfirmarPedido extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);
        Bundle extras = getIntent().getExtras();
        final Articulo[] pedido = (new Gson()).fromJson(extras.getString("pedido"), Articulo[].class);
        TableLayout tbl = (TableLayout)findViewById(R.id.tableLayout1);
        double sum=0;
        for(Articulo lista:pedido){
        	TableRow newRow = new TableRow(this);
        	TextView newText = new TextView(this);
        	newText.setText(lista.nombre);
        	newRow.addView(newText);
        	newText = new TextView(this);
        	newText.setGravity(Gravity.RIGHT);
        	newText.setText(lista.cantidad+"");
        	newRow.addView(newText);
        	newText = new TextView(this);
        	newText.setGravity(0x05);
        	newText.setText((NumberFormat.getCurrencyInstance(Locale.US)).format(lista.precio));
        	newRow.addView(newText);
        	tbl.addView(newRow);
        	sum+=lista.cantidad*lista.precio;
        }
        TableRow newRow = new TableRow(this);
        TextView newText = new TextView(this);
    	newRow.addView(newText);
    	newText = new TextView(this);
        newText.setGravity(Gravity.RIGHT);
    	newText.setText("Total");
    	newRow.addView(newText);
    	newText = new TextView(this);
    	newText.setGravity(Gravity.RIGHT);
    	newText.setText((NumberFormat.getCurrencyInstance(Locale.US)).format(sum));
    	newRow.addView(newText);
    	tbl.addView(newRow);
    	ConfirmarPedido este=this;
        ((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		LineaPedido[] lineas=new LineaPedido[pedido.length];
        		for(int i=0; i<pedido.length; i++){
        			lineas[i]=new LineaPedido();
        			lineas[i].articulo=pedido[i].id;
        			lineas[i].cantidad=pedido[i].cantidad;
        			lineas[i].precio=pedido[i].precio;
        		}
        		Pedido envio=new Pedido();
        		envio.lineas=lineas;
        		envio.usuario="juvs@me.com";
        		DatePicker datep=(DatePicker)findViewById(R.id.datePicker1);
        		TimePicker timep=(TimePicker)findViewById(R.id.timePicker1);
        		Calendar cal=Calendar.getInstance();
        		cal.set(datep.getYear(), datep.getMonth(), datep.getDayOfMonth(), timep.getCurrentHour(), timep.getCurrentMinute());
        		envio.entrega=cal.getTime();
        		Gson gson=new GsonBuilder().setDateFormat(DateFormat.SHORT, DateFormat.SHORT).create();
        		String json = gson.toJson(envio);
        		String response="Ocurrió un error al procesar tu pedido.";
        		try {
        			Socket socket = new Socket("10.20.98.87",6666);
        			PrintWriter alServidor = new PrintWriter(socket.getOutputStream(),true);
        			DataInputStream delServidor = new DataInputStream(socket.getInputStream());
        			alServidor.println("pedido");
        			alServidor.println(json);
        			response=delServidor.readLine();
        			delServidor.close();
        			alServidor.close();
        			socket.close();
        			Toast toast = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT);
        			toast.show();
        		} catch (Exception e) {
        			e.printStackTrace();
        		}
            	}
        	  });
        Intent k = new Intent(ConfirmarPedido.this, SelectPedido.class);
        startActivity(k);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_confirmar_pedido, menu);
        return true;
    }
}
