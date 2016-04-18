package com.studentsales.gwu.gwusales;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Baubek on 04/13/16.
 */
public class Items {

    String itemName;
    String itemDescp;
    Bitmap itemPhotoId;
    String itemId;

    List<ParseFile> parseFilesList;
    private Bitmap bmp;

    public Items() {
    }

    public Items(String itemName, String itemDescp, String itemId, Bitmap itemPhotoId) {
        this.itemName = itemName;
        this.itemDescp = itemDescp;
        this.itemPhotoId = itemPhotoId;
        this.itemId = itemId;
    }

    public static List<Items> items;

    public void initializeData() {
        items = new ArrayList<>();
        final ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Items");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            public ParseFile parseFile;
            public ParseObject parseObject;
            public String itemName = "", itemDescription = "", itemId = "";

            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        parseObject = list.get(i);
                        parseFile = parseObject.getParseFile("ItemPic");
                        itemName = parseObject.get("name").toString();
                        itemDescription = parseObject.get("description").toString();
                        itemId = parseObject.getObjectId();
                        try {
                            bmp = BitmapFactory.decodeByteArray(parseFile.getData(), 0, parseFile.getData().length);
                            items.add(new Items(itemName, itemDescription, itemId, bmp));
                        } catch (ParseException parseEx) {
                            parseEx.printStackTrace();
                        }
                        //Log.d("Parse File: ", String.valueOf(i));
                    }
                }
            }
        });
    }
}
