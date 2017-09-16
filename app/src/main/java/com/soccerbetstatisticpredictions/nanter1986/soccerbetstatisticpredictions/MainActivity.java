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
    String urlForJsoup;
    String selectedUrl;
    private Document doc2;
    private Elements tableHomeTeams;
    private Elements scores;
    private ArrayList<String> allTeams=new ArrayList<>();
    private ArrayList<String> allScores=new ArrayList<>();
    private ArrayList<String> allValues=new ArrayList<>();
    private Elements matchCards;
    private ArrayList<Double> allValuesDouble=new ArrayList<>();
    private ArrayList<SoccerMatch> allReadyMatches=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlForJsoup=getTheExtras(savedInstanceState);
        Log.i("results",urlForJsoup);
        setupViews();
        new GetTheData().execute();
    }

    private String getTheExtras(Bundle savedInstanceState) {
        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("theLink");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("theLink");
        }
        return newString;
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
            String toShowInTextview="";
            for(SoccerMatch s:allReadyMatches){
                toShowInTextview+=s.printSelf();
            }
            resultsTextview.setText(toShowInTextview);

        }

        protected void grabNextMatchDayURL(){
            //String urlForJsoup = "https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/GB1";
            //String urlForJsoup = "https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/ES1";
            //String urlForJsoup = "https://www.transfermarkt.gr/serie-a/startseite/wettbewerb/IT1";
            //String urlForJsoup = "https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/FR1";
            //String urlForJsoup = "https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/L1";
            //String urlForJsoup = "https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/PO1";
            //String urlForJsoup = "https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/BE1";
            //String urlForJsoup = "https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/NL1";
            //String urlForJsoup = "https://www.transfermarkt.gr/jumplist/startseite/wettbewerb/GB2";
            try {
                doc = Jsoup.connect(urlForJsoup).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            links=doc.select("div.footer-links").select("a[href]");
            Log.i("results",links.toString());
            selectedUrl="https://www.transfermarkt.gr"+links.get(3).attr("href");
            try {
                doc2 = Jsoup.connect(selectedUrl).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            tableHomeTeams=doc2.select("div.box").select("tr.table-grosse-schrift").select("td.hide-for-small.spieltagsansicht-vereinsname").select("a");
            Log.i("results",tableHomeTeams.toString());
            for(Element e:tableHomeTeams){
                if(e.text().equals("")){

                }else{
                    allTeams.add(e.text());
                }

            }
            scores=doc2.select("div.box").select("tr.table-grosse-schrift").select("td.spieltagsansicht-ergebnis").select("span.ergebnis-box").select("a");
            Log.i("results","scores\n"+scores.toString());
            for(Element e:scores){
                allScores.add(e.text());
            }
            matchCards =doc2.select("div.large-8.columns").select("div.box").select("table");
            //Log.i("results",matchCards.toString());
            int cardsSize=matchCards.size();
            for(int i=1;i<cardsSize;i++){
                if(allScores.get(i-1).equals("-:-")){
                    allValues.add(matchCards.get(i).select("td").select("a").select("span.wert-bar").text());
                    allValues.add(matchCards.get(i).select("td").select("a").select("span.wert-bar-right").text());
                }else{
                    allValues.add("-1");
                    allValues.add("-1");
                }
            }
            Log.i("results","allvalues\n"+allValues.toString());
            for(String s:allValues){
                if(s.equals("")){

                }else{
                    Double thisValue=extractDoubleValue(s);
                    Log.i("results",thisValue.toString());
                    allValuesDouble.add(thisValue);
                }


            }
            Log.i("results","avd\n"+allValuesDouble.toString());
            constractMatches();
        }
    }

    private void constractMatches() {
        int length = allTeams.size();
        Log.i("results",length+"");
        for(int i=0;i<length;i=i+2){
            allReadyMatches.add(new SoccerMatch(allTeams.get(i),allTeams.get(i+1),allValuesDouble.get(i),allValuesDouble.get(i+1)));
        }
        for(SoccerMatch s:allReadyMatches){
            Log.i("results",s.printSelf());
        }
    }

    private Double extractDoubleValue(String s) {
        char[] arrayC=s.toCharArray();
        String theValueInString="";
        Double valueInDouble;
        if(s.equals(-1)){
            valueInDouble=-1.0;
        }else{
            for(char c:arrayC){
                if(c==' '){
                    break;
                }
                if(c==','){
                    theValueInString+='.';
                }else{
                    theValueInString+=c;
                }

            }
            valueInDouble=Double.parseDouble(theValueInString);
        }

        return valueInDouble;
    }
}
