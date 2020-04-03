package com.biomaster.Conectores.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.biomaster.Models.User;

import java.util.HashMap;
import java.util.Map;

public class LoginService extends StringRequest {
    private static final String BASE_URL = "https://bbiology-service.herokuapp.com";
    private Map<String,String> params;

    public LoginService(User usr, Response.Listener<String> listener){
        super(Method.POST, BASE_URL.concat("/usuarios/login.php"), listener, null);

        params = new HashMap<>();
        params.put("user", usr.getID());
        params.put("password", usr.getPassword());
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
