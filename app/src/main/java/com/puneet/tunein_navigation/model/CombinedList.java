package com.puneet.tunein_navigation.model;

import java.io.Serializable;

public class CombinedList implements Serializable {

    public String text;
    public String subText;
    public int viewType;
    public String imageUrl;
    public String subCatUrl;

    public CombinedList(String text, String subText, String imageUrl, int viewType) {
        this.text = text;
        this.subText = subText;
        this.viewType = viewType;
        this.imageUrl = imageUrl;
    }

    public CombinedList(String text, String url, int viewType) {
        this.text = text;
        this.viewType = viewType;
        this.subCatUrl = url;
    }

    public String getText() {
        return text;
    }

    public String getSubText() {
        return subText;
    }

    public int getViewType() {
        return viewType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSubCatUrl() {
        return subCatUrl;
    }

}
