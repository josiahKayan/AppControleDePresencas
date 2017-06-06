package com.example.ljosias.appcontroledepresencas.services;

import com.example.ljosias.appcontroledepresencas.model.Company;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ljosias on 02/06/2017.
 */

public interface ICompanyService {

    @GET("companies/{Id}")
    //@FormUrlEncoded
    Call<Company> GetCompanyById(  @Path("Id") String Id );

    @GET("companies/companies/companies/companies/companies/companies")
    //@FormUrlEncoded
    List<Call<Company>> GetCompanies(  );

}
