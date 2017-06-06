package com.example.ljosias.appcontroledepresencas.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ljosias on 02/06/2017.
 */

public class Company {

    @SerializedName("Id")
    @Expose
    public String id ;
    @SerializedName("Name")
    @Expose
    public String name ;
    @SerializedName("BranchId")
    @Expose
    public String branchId ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
}
