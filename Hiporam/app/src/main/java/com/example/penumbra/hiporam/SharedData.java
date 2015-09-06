package com.example.penumbra.hiporam;

import com.example.penumbra.hiporam.model.PairPhotoItem;

import java.util.ArrayList;

/**
 * Created by penumbra on 06.09.2015.
 */
public class SharedData
{
    private static SharedData instance = null;

    protected SharedData() {}

    public ArrayList<PairPhotoItem> listItems = new ArrayList<PairPhotoItem>();

    public static SharedData getInstance() {
        if(instance == null) {
            instance = new SharedData();
        }
        return instance;
    }
}
