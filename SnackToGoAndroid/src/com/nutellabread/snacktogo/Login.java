package com.nutellabread.snacktogo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Login extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((Button)findViewById(R.id.button1)).setOnClickListener(new View.OnClickListener() {
        	@Override
        	public void onClick(View v) {
        		Intent k = new Intent(Login.this, SelectPedido.class);
        		startActivity(k);
            	}
        	  });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
}
