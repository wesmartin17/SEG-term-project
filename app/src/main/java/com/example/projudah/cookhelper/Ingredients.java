package com.example.projudah.cookhelper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Ingredients extends ActionBarActivity {
    IngredientList ing;
    PopupWindow window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients);
        ing = new IngredientList();
        start();
    }

    public void start(){
        ArrayList<String> ingslist = new ArrayList<>();
        try {
            ingslist = ing.readRecipe(this);
        } catch (IOException e){}
        ArrayAdapter my = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ingslist);
        ListView list = (ListView) findViewById(R.id.ings);
        list.setAdapter(my);
        final EditText name = new EditText(this);
        window = new PopupWindow(this);
        window.setOutsideTouchable(false);
        window.setHeight(500);
        window.setWidth(500);
        window.setFocusable(true);
        final String[] curname = {""};

        TextView Ok = new TextView(this), cancel = new TextView(this);
        Ok.setClickable(true);
        cancel.setClickable(true);
        final Activity thishome = this;


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        Ok.setText("Ok");
        Ok.setTextSize(18);
        cancel.setText("Cancel");
        cancel.setTextSize(18);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams params2 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout popup = new LinearLayout(this);
        popup.setOrientation(LinearLayout.VERTICAL);
        popup.addView(name, params2);
        popup.addView(Ok,params);
        popup.addView(cancel,params);
        popup.setBackgroundColor(getResources().getColor(R.color.themecolor));
        window.setContentView(popup);


        ImageView add = (ImageView) findViewById(R.id.adding);
        add.setClickable(true);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curname[0]="";
                window.showAtLocation(findViewById(R.id.relativeLayout), 0, 500, 500);

            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                window.showAtLocation(findViewById(R.id.relativeLayout), 0,
                        500, 500);
                String ingname = ((TextView) view).getText().toString();
                name.setText(ingname);
                curname[0] = ingname;
            }
        });
        final Activity thiss =this;
        Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(curname[0].equals(""))) {
                    ing.delete(curname[0]);
                    curname[0] = "";
                }
                if (!(name.getText().toString().equals(""))) {
                    ing.store(name.getText().toString());
                    try {
                        ing.writeRecipe(thiss);
                    } catch (IOException e) {}
                    window.dismiss();
                }else {
                    Toast.makeText(thishome, "Blank name field", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}