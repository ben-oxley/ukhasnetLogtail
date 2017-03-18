package com.benoxley.logtail;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

import java.time.LocalDateTime;

/**
 * Created by ben on 18/03/2017.
 */
//{"i":30590464,"ni":3,"nn":"XF0","t":"2017-03-18T13:03:19.553Z","p":"3yP98048T7.9R-78[XF0]","s":"Pending","r":0}
public class Packet {

    private final long i;
    private final long ni;
    private final String nn;
    private final String t;
    private final String p;
    private final String s;
    private final long r;

    @JsonCreator
    public Packet(
            @JsonProperty("id") long i,
             @JsonProperty("ni") long ni,
             @JsonProperty("nn") String nn,
             @JsonProperty("t") String t,
             @JsonProperty("p") String p,
             @JsonProperty("s") String s,
             @JsonProperty("r") long r
    ){
        this.i = i;
        this.ni = ni;
        this.nn = nn;
        this.t = t;
        this.p = p;
        this.s = s;
        this.r = r;
    }


    public long getI() {
        return i;
    }

    public long getNi() {
        return ni;
    }

    public String getNn() {
        return nn;
    }

    public String getT() {
        return t;
    }

    public String getP() {
        return p;
    }

    public String getS() {
        return s;
    }

    public long getR() {
        return r;
    }

    public LocalDateTime getTime(){
        return LocalDateTime.parse(getT());
    }
}
