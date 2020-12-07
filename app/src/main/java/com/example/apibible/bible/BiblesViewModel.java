package com.example.apibible.bible;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.apibible.bible.models.Bible;
import com.example.apibible.book.Book;

import java.util.List;

public class BiblesViewModel extends AndroidViewModel {
    private BibleRepository bibleRepository;
    private MutableLiveData<List<Bible>> allBibles;

    public BiblesViewModel(@NonNull Application application) {
        super(application);
        bibleRepository = new BibleRepository(application);
        allBibles = bibleRepository.getAllBibles();
    }

    public MutableLiveData<List<Bible>> getAllBibles(){

        return allBibles;
    }

    public MutableLiveData<Integer> countAllBooks(String bibleId){

        return bibleRepository.getBookCount(bibleId);
    }
}