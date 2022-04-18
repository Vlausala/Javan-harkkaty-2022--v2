package com.example.java_harkkatyo;

public class Lake {

    private String lakename, kuntaname;
    private Double avgDepth, shoreLength, area;

    public Lake(String lakename, String kuntaname, Double avgDepth, Double shoreLength, Double area) {
        this.lakename = lakename;
        this.kuntaname = kuntaname;
        this.avgDepth = avgDepth;
        this.shoreLength = shoreLength;
        this.area = area;
    }

    public Lake(){
        this("","",0.0,0.0,0.0);
    }

    public String getLakename() {
        return lakename;
    }

    public String getKuntaname() {
        return kuntaname;
    }

    public String getAvgDepth() {
        return ""+avgDepth;
    }

    public String getShoreLength() {
        return ""+shoreLength;
    }

    public String  getArea() {
        return ""+area;
    }

    public void setLakename(String lakename) {
        this.lakename = lakename;
    }

    public void setKuntaname(String kuntaname) {
        this.kuntaname = kuntaname;
    }

    public void setAvgDepth(Double avgDepth) {
        this.avgDepth = avgDepth;
    }

    public void setShoreLength(Double shoreLength) {
        this.shoreLength = shoreLength;
    }

    public void setArea(Double area) {
        this.area = area;
    }
}
