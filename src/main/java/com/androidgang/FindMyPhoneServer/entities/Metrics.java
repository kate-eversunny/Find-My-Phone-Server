package com.androidgang.FindMyPhoneServer.entities;

import lombok.Data;

@Data
public class Metrics {

    private long longitude;
    private long latitude;
    private long time;
    private String cellId;
    private String lac;
    private String rsrp;
    private String rsrq;
    private String sinr;
    private String imsi;
}
