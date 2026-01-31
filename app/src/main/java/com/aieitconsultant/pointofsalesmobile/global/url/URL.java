package com.aieitconsultant.pointofsalesmobile.global.url;

public class URL {
    private String name;

    public URL() {
        this.name = "pijau.xyz";
//        this.name = "192.168.0.9";
    }

    // Get domain w/o http/https method
    public String getName() {
        return this.name;
    }

    // Get full domain with http/https method
    public String getFullAddress() {
        return "https://" + this.name;
//        return "http://" + this.name + "/wizard";
    }
}
