package com.puneet.tunein_navigation.model.topnavmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categories implements Serializable {

    private Head head;

    private List<Body> bodyList = new ArrayList<>();

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public List<Body> getBodyList() {
        return bodyList;
    }

    public void setBodyList(List<Body> bodyList) {
        this.bodyList = bodyList;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "head=" + head +
                ", bodyList=" + bodyList +
                '}';
    }
}
