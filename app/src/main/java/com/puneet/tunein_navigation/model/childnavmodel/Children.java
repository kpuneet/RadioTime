package com.puneet.tunein_navigation.model.childnavmodel;

import java.io.Serializable;

public class Children implements Serializable {

    private String element;

    private String type;

    private String text;

    private String URL;

    private String guide_id;

    private String subtext;

    private String item;

    private String image;

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getGuide_id() {
        return guide_id;
    }

    public void setGuide_id(String guide_id) {
        this.guide_id = guide_id;
    }

    public String getSubtext() {
        return subtext;
    }

    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Children{" +
                "element='" + element + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", URL='" + URL + '\'' +
                ", guide_id='" + guide_id + '\'' +
                ", subtext='" + subtext + '\'' +
                ", item='" + item + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
