package com.example.ljosias.appcontroledepresencas.utils;

import com.example.ljosias.appcontroledepresencas.client.RetrofitClient;
import com.example.ljosias.appcontroledepresencas.services.IUsuarioService;

/**
 * Created by ljosias on 02/06/2017.
 */

public class Utils {

    private Utils() {}

    public static IUsuarioService getUsuarioService( ) {

        return RetrofitClient.getClient().create(IUsuarioService.class);
    }


}
