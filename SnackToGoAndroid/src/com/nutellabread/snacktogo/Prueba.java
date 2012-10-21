package com.nutellabread.snacktogo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Prueba extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_prueba, menu);
        return true;
    }
}
