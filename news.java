package com.example.midterm3;

public class news {

    int id;
    String url;
    String desc;
    String heading;
    String ref;

    public news(int id, String url, String desc, String heading,String ref) {
        this.id = id;
        this.url = url;
        this.desc = desc;
        this.heading = heading;
        this.ref=ref;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDesc() {
        return desc;
    }

    public String getHeading() {
        return heading;
    }

    public String getRef() {
        return ref;
    }
}
