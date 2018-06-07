package com.puneet.tunein_navigation.model.childnavmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Body implements Serializable {

    String element;

    String text;

    String key;

    List<Children> childrenList = new ArrayList<>();


    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Children> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Children> childrenList) {
        this.childrenList = childrenList;
    }

    @Override
    public String toString() {
        return "Body{" +
                "element='" + element + '\'' +
                ", text='" + text + '\'' +
                ", key='" + key + '\'' +
                ", childrenList=" + childrenList +
                '}';
    }
}
