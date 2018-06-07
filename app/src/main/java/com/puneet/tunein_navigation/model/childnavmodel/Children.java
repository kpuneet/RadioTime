package com.puneet.tunein_navigation.model.childnavmodel;

import java.io.Serializable;

public class Children implements Serializable {

    private String element;

    private String type;

    private String text;

    private String URL;

    private String bitrate;

    private String reliability;

    private String guide_id;

    private String subtext;

    private String genre_id;

    private String formats;

    private String playing;

    private String item;

    private String image;

    private String now_playing_id;

    private String preset_id;


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

    public String getBitrate() {
        return bitrate;
    }

    public void setBitrate(String bitrate) {
        this.bitrate = bitrate;
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
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

    public String getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(String genre_id) {
        this.genre_id = genre_id;
    }

    public String getFormats() {
        return formats;
    }

    public void setFormats(String formats) {
        this.formats = formats;
    }

    public String getPlaying() {
        return playing;
    }

    public void setPlaying(String playing) {
        this.playing = playing;
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

    public String getNow_playing_id() {
        return now_playing_id;
    }

    public void setNow_playing_id(String now_playing_id) {
        this.now_playing_id = now_playing_id;
    }

    public String getPreset_id() {
        return preset_id;
    }

    public void setPreset_id(String preset_id) {
        this.preset_id = preset_id;
    }

    @Override
    public String toString() {
        return "Children{" +
                "element='" + element + '\'' +
                ", type='" + type + '\'' +
                ", text='" + text + '\'' +
                ", URL='" + URL + '\'' +
                ", bitrate='" + bitrate + '\'' +
                ", reliability='" + reliability + '\'' +
                ", guide_id='" + guide_id + '\'' +
                ", subtext='" + subtext + '\'' +
                ", genre_id='" + genre_id + '\'' +
                ", formats='" + formats + '\'' +
                ", playing='" + playing + '\'' +
                ", item='" + item + '\'' +
                ", image='" + image + '\'' +
                ", now_playing_id='" + now_playing_id + '\'' +
                ", preset_id='" + preset_id + '\'' +
                '}';
    }

}
