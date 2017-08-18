package com.example.ljosias.appcontroledepresencas.log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ljosias on 19/06/2017.
 */

public class Log {
    @SerializedName("Message")
    @Expose
    public  String message;
    @SerializedName("Status")
    @Expose
    public String status;
    @SerializedName("Type")
    @Expose
    public String type;

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status; 
    }

    public String gettype() {
        return type;
    }

    public void settype(String type) {
        this.type = type;
    }
}
