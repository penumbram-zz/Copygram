package com.example.penumbra.hiporam.model;

/**
 * Created by penumbra on 04.09.2015.
 */
public class PairPhotoItem
{
    public PhotoItem firstPhotoItem;
    public PhotoItem secondPhotoItem;

    public PairPhotoItem(PhotoItem firstPhotoItem, PhotoItem secondPhotoItem)
    {
        this.firstPhotoItem = firstPhotoItem;
        this.secondPhotoItem = secondPhotoItem;
    }
}
