package com.example.ljosias.appcontroledepresencas.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ljosias on 16/06/2017.
 */

public class Tag {

    @SerializedName("TagId")
    @Expose
    public int tagId ;
    /// <summary>
    /// Código da Tag
    /// </summary>
    @SerializedName("Code")
    @Expose
    public String code ;
    /// <summary>
    /// Status da Tag
    /// </summary>
    @SerializedName("Status")
    @Expose
    public int status ;

}
