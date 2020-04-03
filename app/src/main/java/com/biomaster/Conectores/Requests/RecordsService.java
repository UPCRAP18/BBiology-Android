package com.biomaster.Conectores.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RecordsService extends StringRequest {
    private static final String BASE_URL = "https://bbiology-service.herokuapp.com";
    private Map<String,String> params;

    public RecordsService(String id, Response.Listener<String> listener){
        super(Method.POST, BASE_URL.concat("/app/index.php"), listener, null);
        params = new HashMap<>();
        params.put("id", String.valueOf(id));
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

