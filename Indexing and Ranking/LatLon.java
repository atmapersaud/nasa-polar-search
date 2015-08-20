package com.bericotech.clavin;

public class LatLon implements Comparable<LatLon> {
    private double lat;
    private double lon;

    public LatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;        
    }

    public int compareTo(LatLon other) {
        if (other.lat - this.lat == 0) {
            return (int) (other.lat - this.lat);             
        } else {
            return (int) (other.lon - this.lon);
        }
    }

    public String toString() {
        return (int)this.lat + "," + (int)this.lon;
    }
}
