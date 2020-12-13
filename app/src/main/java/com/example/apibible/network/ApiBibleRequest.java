package com.example.apibible.network;

import android.content.Context;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ApiBibleRequest {

    public interface VolleyCallback {
        void onSuccessResponse(JSONObject result);
    }

    private final String url_base = "https://api.scripture.api.bible";
    private final String api_key = "9bea9ab8db7fd98f1f0ebb9cd98b8001";

    private final Context context;

    public ApiBibleRequest(Context context){
        this.context = context;
    }


    private void getResponse(String url, final VolleyCallback callback) {

        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url, null,
                callback::onSuccessResponse,
                e -> {
                    e.printStackTrace();
                    Toast.makeText(context, e + "error", Toast.LENGTH_LONG).show();
                }) {
            // set headers
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("api-key", api_key);
                return params;
            }
        };
        AppRequestQueue.getInstance(context).addToRequestQueue(jsonReq);
    }

    ///////// Bibles (Fetch Bibles and metadata)

    //v1/bibles
    public void getAllBibles(VolleyCallback callback){
        String url = url_base + "/v1/bibles";
        getResponse(url, callback);
    }

    //v1/bibles/{bibleId}
    public void getBible(String bibleId, VolleyCallback callback){
        String url = url_base + "v1/bibles/" + bibleId;
        getResponse(url, callback);
    }

    //v1/audio-bibles
    public void getAllAudioBibles(VolleyCallback callback){
        String url = url_base + "/v1/audio-bibles";
        getResponse(url, callback);
    }

    //v1/audio-bibles/{audioBibleId}
    public void getAudioBible(String audioBibleId, VolleyCallback callback){
        String url = url_base + "v1/audio-bibles/" + audioBibleId;
        getResponse(url, callback);
    }


    ///////// Books (Fetch Books for a Bible)


    //v1/bibles/{bibleId}/books

    public void getAllBibleBooks(String bibleId, VolleyCallback callback){
        getAllBibleBooks(bibleId, false, callback);
    }

    public void getAllBibleBooks(String bibleId, boolean includeChapters, VolleyCallback callback){
        String url = url_base + "/v1/bibles/" + bibleId + "/books";
        url += includeChapters ? "?include-chapters=true" : "";
        getResponse(url, callback);
    }

    //v1/bibles/{bibleId}/books/{bookId}
    public void getBibleBook(String bibleId, String bookId, VolleyCallback callback){
        String url = url_base + "/v1/bibles/" + bibleId + "/books/" + bookId;
        getResponse(url, callback);
    }

    //v1​/audio-bibles​/{audioBibleId}​/books
    public void getAllAudioBibleBooks(String audioBibleId, VolleyCallback callback){
        String url = url_base + "/v1/audio-bibles/" + audioBibleId + "/books";
        getResponse(url, callback);
    }

    //v1​/audio-bibles​/{audioBibleId}​/books​/{bookId}
    public void getAudioBibleBook(String audioBibleId, String bookId, VolleyCallback callback){
        String url = url_base + "/v1/audio-bibles/" + audioBibleId + "/books/" + bookId;
        getResponse(url, callback);
    }


    ///////// Chapters (Fetch Chapters for a Bible)


    //v1​/bibles​/{bibleId}​/books​/{bookId}​/chapters
    public void getBibleBookChapters(String bibleId, String bookId, VolleyCallback callback){
        String url = url_base + "v1/bibles/" + bibleId + "/books/" + bookId + "/chapters";
        getResponse(url, callback);
    }

    //v1​/bibles​/{bibleId}​/chapters​/{chapterId}
    public void getBibleChapter(String bibleId, String chapterId, VolleyCallback callback){
        String url = url_base + "v1/bibles/" + bibleId + "/chapters/" + chapterId;
        getResponse(url, callback);
    }

    //v1​/audio-bibles​/{audioBibleId}​/books​/{bookId}​/chapters
    public void getAudioBibleBookChapters(String audioBibleId, String bookId, VolleyCallback callback){
        String url = url_base + "v1/audio-bibles/" + audioBibleId + "/books/" + bookId + "/chapters";
        getResponse(url, callback);
    }

    //v1​/audio-bibles​/{audioBibleId}​/chapters​/{chapterId}
    public void getAudioBibleChapter(String audioBibleId, String chapterId, VolleyCallback callback){
        String url = url_base + "v1/audio-bibles/" + audioBibleId + "/chapters/" + chapterId;
        getResponse(url, callback);
    }


    ///////// Sections (Fetch Sections for a Bible (Experimental) )


    //v1​/bibles​/{bibleId}​/books​/{bookId}​/sections
    public void getBibleBookSections(String bibleId, String bookId, VolleyCallback callback){
        String url = url_base + "v1/bibles/" + bibleId + "/books/" + bookId + "/sections";
        getResponse(url, callback);
    }

    //v1​/bibles​/{bibleId}​/chapters​/{chapterId}​/sections
    public void getBibleChapterSections(String bibleId, String chapterId, VolleyCallback callback){
        String url = url_base + "v1/bibles/" + bibleId + "/chapters/" + chapterId + "/sections";
        getResponse(url, callback);
    }

    //v1​/bibles​/{bibleId}​/sections​/{sectionId}
    public void getBibleSection(String bibleId, String sectionId, VolleyCallback callback){
        String url = url_base + "v1/bibles/" + bibleId + "/sections/" + sectionId;
        getResponse(url, callback);
    }


    ///////// Passages (Fetch a Passage for a Bible)


    //v1​/bibles​/{bibleId}​/passages​/{passageId}
    public void getBiblePassage(String bibleId, String passageId, VolleyCallback callback){
        String url = url_base + "v1/bibles/" + bibleId + "/passages/" + passageId;
        getResponse(url, callback);
    }


    ///////// Verses (Fetch Verses for a Bible)


    //v1​/bibles​/{bibleId}​/chapters​/{chapterId}​/verses
    public void getBibleChapterVerses(String bibleId, String chapterId, VolleyCallback callback){
        String url = url_base + "v1/bibles/" + bibleId + "/chapters/" + chapterId + "/sections";
        getResponse(url, callback);
    }

    //v1​/bibles​/{bibleId}​/verses​/{verseId}
    public void getBibleVerse(String bibleId, String passageId, VolleyCallback callback){
        String url = url_base + "v1/bibles/" + bibleId + "/passages/" + passageId;
        getResponse(url, callback);
    }

    ///////// Search (Search by keyword or reference)


    //v1​/bibles​/{bibleId}​/search


}// end ApiBibleRequest
