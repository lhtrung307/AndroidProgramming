package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHat {
    @SerializedName("idBaiHat")
    @Expose
    private String idBaiHat;
    @SerializedName("tenbaihat")
    @Expose
    private String tenbaihat;
    @SerializedName("hinhbaihat")
    @Expose
    private String hinhbaihat;
    @SerializedName("casi")
    @Expose
    private String casi;
    @SerializedName("linkbaihat")
    @Expose
    private String linkbaihat;
    @SerializedName("luotthich")
    @Expose
    private String luotthich;

    public String getIdbaihat() {
        return idBaiHat;
    }

    public void setIdbaihat(String idbauhat) {
        this.idBaiHat = idbauhat;
    }

    public String getTenbaihat() {
        return tenbaihat;
    }

    public void setTenbaihat(String tenbaihat) {
        this.tenbaihat = tenbaihat;
    }

    public String getHinhbaihat() {
        return hinhbaihat;
    }

    public void setHinhbaihat(String hinhbaihat) {
        this.hinhbaihat = hinhbaihat;
    }

    public String getCasi() {
        return casi;
    }

    public void setCasi(String casi) {
        this.casi = casi;
    }

    public String getLinkbaihat() {
        return linkbaihat;
    }

    public void setLinkbaihat(String linkbaihat) {
        this.linkbaihat = linkbaihat;
    }

    public String getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(String luotthich) {
        this.luotthich = luotthich;
    }
}
