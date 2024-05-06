package servicios_api;

import objetos.tipoDeCambio;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class conexion_api {
    public tipoDeCambio newConexionAPi(String tipoDeMonedas) throws IOException, InterruptedException {

        //Cremos una instacia de cliente
        HttpClient client = HttpClient.newHttpClient();

        //Nuestra apikey
        String api = "https://v6.exchangerate-api.com/v6/663e4f366436f00880ea55fa/latest/";

        //Nuestra solicitud para la api
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api + tipoDeMonedas))
                .build();

        //Respuesta de la api
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        //Realizamos la conversion de la respuesta de la api a tipoDeCambio
        return new Gson().fromJson(response.body(), tipoDeCambio.class);
    }
}
