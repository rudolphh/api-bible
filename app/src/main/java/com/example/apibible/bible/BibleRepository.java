package com.example.apibible.bible;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.apibible.bible.models.Bible;
import com.example.apibible.book.Book;
import com.example.apibible.network.ApiBibleRequest;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BibleRepository {

    private final MutableLiveData<List<Bible>> allBibles = new MutableLiveData<>();
    private final ApiBibleRequest apiBibleRequest;

    public BibleRepository(Application application){

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

    public MutableLiveData<Integer> getBookCount(String bibleId){

        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

        apiBibleRequest.getAllBibleBooks(bibleId, response ->{
            try {
                JSONArray books = response.getJSONArray("data");
                mutableLiveData.setValue(books.length());
            } catch (JSONException e){
                e.printStackTrace();
            }

        });

        return mutableLiveData;
    }


    public MutableLiveData<List<Book>> getAllBibleBooks(String bibleId){

        MutableLiveData<List<Book>> mutableLiveData = new MutableLiveData<>();

        apiBibleRequest.getAllBibleBooks(bibleId, response -> {

            try{
                Gson gson = new Gson();

                // Getting JSON Array node
                JSONArray books = response.getJSONArray("data");
                List<Book> bookList = new ArrayList<>(books.length());

                // looping through All Bibles
                for (int i = 0; i < books.length(); i++) {

                    JSONObject b = books.getJSONObject(i);
                    Book book = gson.fromJson(b.toString(), Book.class);
                    Log.i(BibleRepository.class.getSimpleName(), book.getName());
                    bookList.add(book);
                }
                mutableLiveData.setValue(bookList);

            } catch (JSONException e){
                e.printStackTrace();
            }

        });

        return mutableLiveData;
    }
}
