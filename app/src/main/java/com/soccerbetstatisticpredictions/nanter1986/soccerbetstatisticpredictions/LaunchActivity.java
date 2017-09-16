package com.soccerbetstatisticpredictions.nanter1986.soccerbetstatisticpredictions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class LaunchActivity extends Activity {

    ArrayList<Button>allButtons=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        viewsReference();
        addFunctionalityToButtons();
    }

    private void addFunctionalityToButtons() {
        allButtons.get(0).setText("England 1");
        allButtons.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLeague("https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/GB1");
            }
        });
        allButtons.get(1).setText("Spain 1");
        allButtons.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLeague("https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/ES1");
            }
        });
        allButtons.get(2).setText("Italy 1");
        allButtons.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLeague("https://www.transfermarkt.gr/serie-a/startseite/wettbewerb/IT1");
            }
        });
        allButtons.get(3).setText("France 1");
        allButtons.get(3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLeague("https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/FR1");
            }
        });
        allButtons.get(4).setText("Germany 1");
        allButtons.get(4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLeague("https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/L1");
            }
        });
        allButtons.get(5).setText("Portugal 1");
        allButtons.get(5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLeague("https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/PO1");
            }
        });
        allButtons.get(6).setText("Belgium 1");
        allButtons.get(6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLeague("https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/BE1");
            }
        });
        allButtons.get(7).setText("Netherlands 1");
        allButtons.get(7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLeague("https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/NL1");
            }
        });
        allButtons.get(8).setText("England 2");
        allButtons.get(8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLeague("https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/GB2");
            }
        });
    }

    private void goToLeague(String theLink){
        Intent i=new Intent(this,MainActivity.class);
        i.putExtra("theLink",theLink);
        startActivity(i);
        finish();
    }

    private void viewsReference() {
        allButtons.add((Button) findViewById(R.id.button1));
        allButtons.add((Button) findViewById(R.id.button2));
        allButtons.add((Button) findViewById(R.id.button3));
        allButtons.add((Button) findViewById(R.id.button4));
        allButtons.add((Button) findViewById(R.id.button5));
        allButtons.add((Button) findViewById(R.id.button6));
        allButtons.add((Button) findViewById(R.id.button7));
        allButtons.add((Button) findViewById(R.id.button8));
        allButtons.add((Button) findViewById(R.id.button9));
        allButtons.add((Button) findViewById(R.id.button10));
        allButtons.add((Button) findViewById(R.id.button11));
        allButtons.add((Button) findViewById(R.id.button12));
        allButtons.add((Button) findViewById(R.id.button13));
        allButtons.add((Button) findViewById(R.id.button14));
        allButtons.add((Button) findViewById(R.id.button15));
        allButtons.add((Button) findViewById(R.id.button16));
        allButtons.add((Button) findViewById(R.id.button17));
        allButtons.add((Button) findViewById(R.id.button18));
        allButtons.add((Button) findViewById(R.id.button19));
        allButtons.add((Button) findViewById(R.id.button20));
    }


}
