package com.example.apibible;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

//    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
//    public static final ExecutorService requestExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private String TAG = MainActivity.class.getSimpleName();
    private ApiBibleRequest apiBibleRequest;

    private ListView lv;
    ArrayList<HashMap<String, String>> bibleList;

    ////////////////////// onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiBibleRequest = new ApiBibleRequest(getApplicationContext());
        setBibleList();

    }// end onCreate


    public void setBibleList() {

        bibleList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);


        // end onSuccessResponse
        apiBibleRequest.getAllBibles(response -> {
            try {
                // Getting JSON Array node
                JSONArray bibles = response.getJSONArray("data");

                // looping through All Contacts
                for (int i = 0; i < bibles.length(); i++) {
                    JSONObject c = bibles.getJSONObject(i);
                    String id = c.getString("id");
                    String name = c.getString("name");
                    String abbreviation = c.getString("abbreviation");

                    // tmp hash map for single bible
                    HashMap<String, String> bible = new HashMap<>();

                    // adding each child node to HashMap key => value
                    bible.put("id", id);
                    bible.put("name", name);
                    bible.put("abbreviation", abbreviation);

                    // adding contact to contact list
                    bibleList.add(bible);
                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());
                runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                        "Json parsing error: " + e.getMessage(),
                        Toast.LENGTH_LONG).show());

            }// end catch

            // set up the list adapter
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, bibleList,
                    R.layout.bible_list_item, new String[]{ "name","abbreviation"},
                    new int[]{R.id.name, R.id.abbreviation});
            lv.setAdapter(adapter);

        });

    }// end setBibleList()


}