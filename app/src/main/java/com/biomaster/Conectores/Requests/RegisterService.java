package com.biomaster.Conectores.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.biomaster.Models.User;

import java.util.HashMap;
import java.util.Map;

public class RegisterService extends StringRequest {
    private static final String BASE_URL = "https://bbiology-service.herokuapp.com";
    private Map<String,String> params;

    public RegisterService(User usr, Response.Listener<String> listener){
        super(Method.POST, BASE_URL.concat("/usuarios/register.php"), listener, null);

        params = new HashMap<>();
        params.put("ID", usr.getID());
        params.put("Nombre", usr.getNombre());
        params.put("Apellido", usr.getApellido());
        params.put("Iniciales", usr.getIniciales());
        params.put("Correo", usr.getCorreo());
        params.put("Turno", usr.getTurno());
        params.put("Password", usr.getPassword());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
