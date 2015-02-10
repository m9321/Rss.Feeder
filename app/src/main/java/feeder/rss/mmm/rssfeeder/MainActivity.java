package feeder.rss.mmm.rssfeeder;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String url="http://downloads.bbc.co.uk/podcasts/manchester/mancity/rss.xml";
        FeedGet feedGet = new FeedGet();
        feedGet.execute(url);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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



    private class FeedGet extends AsyncTask <String,String,ArrayList<RssFeed>>{


        @Override
        protected ArrayList<RssFeed> doInBackground(String... params) {

        String sUrl=params[0];
        InputStream is = null;

            try {
                URL url = new URL(sUrl);
                HttpURLConnection conn;
                conn = (HttpURLConnection)
                url.openConnection();
                conn.connect();
                //there should be other data upward but here is just atry

                is = conn.getInputStream();
                //Read Data
                final int bufferSize = 1024;
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                while(true) {
                    int count = is.read(buffer, 0, bufferSize);
                    if(count == -1) {
                        break;
                    }

                    os.write(buffer); }
                    os.close();

                String result = new String(os.toByteArray(), "UTF-8");
                Toast.makeText(MainActivity.this, result , Toast.LENGTH_LONG).show();

            } catch (java.io.IOException e) {
                e.printStackTrace();
            }


            return null;
        }
        }

}
