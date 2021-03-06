package com.puneet.tunein_navigation.model.childnavmodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChildCategories implements Serializable {

    private Head head;

    private List<Body> body = new ArrayList<>();

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public List<Body> getBody() {
        return body;
    }

    public void setBody(List<Body> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "ChildrenCategories{" +
                "head=" + head +
                ", body=" + body +
                '}';
    }
}
