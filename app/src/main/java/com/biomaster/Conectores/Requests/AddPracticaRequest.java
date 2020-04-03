package com.biomaster.Conectores.Requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.biomaster.Models.Practica;

import java.util.HashMap;
import java.util.Map;

public class AddPracticaRequest extends StringRequest {
    private static final String BASE_URL = "https://bbiology-service.herokuapp.com";
    private Map<String,String> params;
    public AddPracticaRequest(Practica practica, Response.Listener<String> listener) {
        super(Method.POST, BASE_URL.concat("/app/add.php") , listener, null);

        params = new HashMap<>();

        params.put("ID_Prof", practica.getID_Prof());
        params.put("Asist_Aux", String.valueOf(practica.isAsist_Aux()));
        params.put("Asist_Chico_Serv", String.valueOf(practica.isAsist_Chico_Serv()));
        params.put("Practica_Realizar", String.valueOf(practica.getPractica_Realizar()));
        params.put("Hubo_Practica", String.valueOf(practica.isHubo_Practica()));
        params.put("Muestras_Fija", String.valueOf(practica.isMuestras_Fija()));
        params.put("Bata", String.valueOf(practica.isBata()));
        params.put("Materiales_Completos", String.valueOf(practica.isMateriales_Completos()));
        params.put("Puntualidad_Alumno", String.valueOf(practica.isPuntualidad_Alumno()));
        params.put("Puntualidad_Profesor", String.valueOf(practica.isPuntualidad_Profesor()));
        params.put("Manual", String.valueOf(practica.isManual()));
        params.put("Fecha", practica.getFecha());
        params.put("Hora_Inicio", practica.getHora_Inicio());
        params.put("Hora_Fin", practica.getHora_Fin());

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
