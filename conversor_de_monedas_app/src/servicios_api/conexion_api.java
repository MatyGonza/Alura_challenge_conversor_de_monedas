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

        HttpClient client = HttpClient.newHttpClient();

        String api = "https://v6.exchangerate-api.com/v6/663e4f366436f00880ea55fa/latest/";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(api + tipoDeMonedas))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), tipoDeCambio.class);
    }
}
