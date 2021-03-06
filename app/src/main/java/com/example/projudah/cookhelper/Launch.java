package com.example.projudah.cookhelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.projudah.cookhelper.Trans;


public class Launch extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);
        AlphaAnimation start = new AlphaAnimation(0.0f,1.0f);
        start.setDuration(500);
        start.setStartOffset(500);
        start.setFillAfter(true);
        TextView st = (TextView) findViewById(R.id.st);
        st.startAnimation(start);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void home(View v){
        RelativeLayout bg = (RelativeLayout) findViewById(R.id.fbg);
        Trans.out(bg, this , Home.class);
    }
}
