package com.example.apibible;

import android.app.Application;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.apibible.bible.Bible;
import com.example.apibible.network.ApiBibleRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BibleRepository {

    private MutableLiveData<List<Bible>> allBibles = new MutableLiveData<>();
    private ApiBibleRequest apiBibleRequest;

    BibleRepository(Application application){

        apiBibleRequest = new ApiBibleRequest(application.getApplicationContext());

    }

    public MutableLiveData<List<Bible>> getAllBibles() {

        apiBibleRequest.getAllBibles(response -> {
            try {

                Gson gson = new Gson();

                // Getting JSON Array node
                JSONArray bibles = response.getJSONArray("data");
                List<Bible> bibleList = new ArrayList<>(bibles.length());

                // looping through All Bibles
                for (int i = 0; i < bibles.length(); i++) {

                    JSONObject b = bibles.getJSONObject(i);
                    Bible bible = gson.fromJson(b.toString(), Bible.class);
                    Log.i(BibleRepository.class.getSimpleName(), bible.getName());
                    bibleList.add(bible);
                }
                allBibles.setValue(bibleList);

            } catch (final JSONException e){
                e.printStackTrace();
            }
        });

        return allBibles;
    }
}
