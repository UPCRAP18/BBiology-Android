package com.biomaster.Conectores.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class GetPracticasRequest extends StringRequest {
    private static final String BASE_URL = "https://bbiology-service.herokuapp.com";
    private Map<String,String> params;

    public GetPracticasRequest(Response.Listener<String> listener){
        super(Method.GET, BASE_URL.concat("/practicas/"), listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
