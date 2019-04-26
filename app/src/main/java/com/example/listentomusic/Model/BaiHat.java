package com.example.listentomusic.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaiHat {
    @SerializedName("Idbaihat")
    @Expose
    private String idbauhat;

    @SerializedName("Tenbaihat")
    @Expose
    private String tenbaihat;

    @SerializedName("Hinhbaihat")
    @Expose
    private String hinhbaihat;

    @SerializedName("Casi")
    @Expose
    private String casi;

    @SerializedName("Linkbaihat")
    @Expose
    private String linkbaihat;

    @SerializedName("Luotthich")
    @Expose
    private String luotthich;

    public String getIdbauhat() {
        return idbauhat;
    }

    public void setIdbauhat(String idbauhat) {
        this.idbauhat = idbauhat;
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
