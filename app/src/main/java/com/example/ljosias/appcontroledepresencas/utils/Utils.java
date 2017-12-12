package com.example.ljosias.appcontroledepresencas.utils;

import com.example.ljosias.appcontroledepresencas.client.RetrofitClient;
import com.example.ljosias.appcontroledepresencas.services.IAlunoService;
import com.example.ljosias.appcontroledepresencas.services.ICursoService;
import com.example.ljosias.appcontroledepresencas.services.IPresencaService;
import com.example.ljosias.appcontroledepresencas.services.IProfessorService;
import com.example.ljosias.appcontroledepresencas.services.ITagService;
import com.example.ljosias.appcontroledepresencas.services.ITurmaService;
import com.example.ljosias.appcontroledepresencas.services.IUsuarioService;

/**
 * Created by ljosias on 02/06/2017.
 */

public class Utils {

    private Utils() {}

    public static IUsuarioService getUsuarioService( ) {

        return RetrofitClient.getClient().create(IUsuarioService.class);
    }

    public static ITagService getTagService( ) {

        return RetrofitClient.getClient().create(ITagService.class);
    }

    public static ICursoService getCursoService( ) {

        return RetrofitClient.getClient().create(ICursoService.class);
    }

    public static IProfessorService getProfessorService( ) {

        return RetrofitClient.getClient().create(IProfessorService.class);
    }

    public static IAlunoService getAlunoService( ) {

        return RetrofitClient.getClient().create(IAlunoService.class);
    }

    public static ITurmaService getTurmaService( ) {

        return RetrofitClient.getClient().create(ITurmaService.class);
    }

    public static IPresencaService getPresencaService( ) {

        return RetrofitClient.getClient().create(IPresencaService.class);
    }
}
