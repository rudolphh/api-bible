
package com.example.apibible.chapter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta {

    @SerializedName("fums")
    @Expose
    private String fums;
    @SerializedName("fumsId")
    @Expose
    private String fumsId;
    @SerializedName("fumsJsInclude")
    @Expose
    private String fumsJsInclude;
    @SerializedName("fumsJs")
    @Expose
    private String fumsJs;
    @SerializedName("fumsNoScript")
    @Expose
    private String fumsNoScript;

    public String getFums() {
        return fums;
    }

    public void setFums(String fums) {
        this.fums = fums;
    }

    public String getFumsId() {
        return fumsId;
    }

    public void setFumsId(String fumsId) {
        this.fumsId = fumsId;
    }

    public String getFumsJsInclude() {
        return fumsJsInclude;
    }

    public void setFumsJsInclude(String fumsJsInclude) {
        this.fumsJsInclude = fumsJsInclude;
    }

    public String getFumsJs() {
        return fumsJs;
    }

    public void setFumsJs(String fumsJs) {
        this.fumsJs = fumsJs;
    }

    public String getFumsNoScript() {
        return fumsNoScript;
    }

    public void setFumsNoScript(String fumsNoScript) {
        this.fumsNoScript = fumsNoScript;
    }

}
