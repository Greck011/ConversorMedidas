package Logica;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 *
 * @author greivinin
 */
public class Divisa {

    private final double tasaDeCambioCrc;

    public Divisa() {
        // Inicializa la tasa de cambio al cargar la clase.
        this.tasaDeCambioCrc = obtenerTasaDeCambio("CRC"); // ₡524.0719 = $1. Varia según el momoento
    }

    // Método para actualizar la tasa de cambio
    private double obtenerTasaDeCambio(String codigoDivisa) {
        String apiKey = "0136070ab03a5252f22345ee"; // API Key
        String url = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/USD"; // URL de la API USD

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonResponse = new JSONObject(response.body());
            return jsonResponse.getJSONObject("conversion_rates").getDouble(codigoDivisa);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 510; // Valor por defecto en caso de error
        }
    }

    //Metodo devuelve el valor en dolares
    public double ColonADolar(double dolares) {
        dolares = (dolares / tasaDeCambioCrc);
        return dolares;
    }

    // Método para convertir de dólares a colones
    public double DolarAColon(double colones) {
        colones = (colones * tasaDeCambioCrc);
        return colones;
    }
}
