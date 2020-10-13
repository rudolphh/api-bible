package com.example.apibible;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.apibible.bible.Bible;

import java.util.List;

public class BibleViewModel extends AndroidViewModel {

    private BibleRepository bibleRepository;
    private MutableLiveData<List<Bible>> allBibles;

    public BibleViewModel(@NonNull Application application) {
        super(application);
        bibleRepository = new BibleRepository(application);
        allBibles = bibleRepository.getAllBibles();
    }

    public MutableLiveData<List<Bible>> getAllBibles(){

        return allBibles;
    }
}
