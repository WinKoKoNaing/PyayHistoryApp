package com.techhousestudio.pyayhistory.models;

public class Master {
    public static final int HEADER = 0;
    public static final int BODY = 1;
    public static final int FOOTER = 2;

    public int type;
    public Object object;

    public Master(int type, Object object) {
        this.type = type;
        this.object = object;
    }


}
