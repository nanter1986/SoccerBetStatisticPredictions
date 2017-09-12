package com.soccerbetstatisticpredictions.nanter1986.soccerbetstatisticpredictions;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    TextView resultsTextview;
    private Document doc;
    private Elements links;
    String selectedUrl;
    private Document doc2;
    private Elements tableHomeTeams;
    private Elements scores;
    private ArrayList<String> allTeams=new ArrayList<>();
    private ArrayList<String> allScores=new ArrayList<>();
    private ArrayList<String> allValues=new ArrayList<>();
    private Elements matchCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        new GetTheData().execute();
    }

    private void setupViews() {
        resultsTextview=findViewById(R.id.resultsTextview);
    }

    public class GetTheData extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            grabNextMatchDayURL();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Log.i("results",links.get(3).attr("href"));
            Log.i("results",allTeams.toString());
            Log.i("results",allScores.toString());
            Log.i("results",matchCards.size()+"");
            Log.i("results",allValues.toString());

        }

        protected void grabNextMatchDayURL(){
            //String urlForJsoup = "https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/GB1";
            String urlForJsoup = "https://www.transfermarkt.gr/serie-a/startseite/wettbewerb/IT1";
            try {
                doc = Jsoup.connect(urlForJsoup).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            links=doc.select("div.footer-links").select("a[href]");
            selectedUrl="https://www.transfermarkt.gr"+links.get(3).attr("href");
            try {
                doc2 = Jsoup.connect(selectedUrl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tableHomeTeams=doc2.select("div.box").select("tr.table-grosse-schrift").select("td.hide-for-small.spieltagsansicht-vereinsname").select("a");
            for(Element e:tableHomeTeams){
                allTeams.add(e.text());
            }
            scores=doc2.select("div.box").select("tr.table-grosse-schrift").select("td.spieltagsansicht-ergebnis").select("a.ergebnis-link");
            for(Element e:scores){
                allScores.add(e.text());
            }
            matchCards =doc2.select("div.large-8.columns").select("div.box").select("table");
            int cardsSize=matchCards.size();
            for(int i=1;i<cardsSize-1;i++){
                if(allScores.get(i-1).equals("-:-")){
                    allValues.add(matchCards.get(i).select("td.rechts-no-padding").select("a").select("span.wert-bar").text());
                    allValues.add(matchCards.get(i).select("td.rechts-no-padding").select("a").select("span.wert-bar-right").text());
                }else{
                    allValues.add("-1");
                    allValues.add("-1");
                }
            }
        }
    }
}
