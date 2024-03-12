package rest_accreditation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class ConsultaVisitantes {

    public static void main(String[] args) {
        consultarVisitantes();
    }

    public static void consultarVisitantes() {
        try {
            // Establecer la URL de la solicitud
            URL url = new URL("http://localhost:8080/webServExpertos/visitantes/getDatosVisitantes");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurar la solicitud HTTP
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer <token>");

            // Construir el cuerpo de la solicitud
            String cuerpo = "{\"doc\": \"documento\", \"nombres\": \"nombres\", \"apellidos\": \"apellidos\", \"ubicacion\": \"ubicacion\"}";

            // Enviar el cuerpo de la solicitud
            connection.setDoOutput(true);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = cuerpo.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer la respuesta del servidor
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                String response = br.lines().collect(Collectors.joining(System.lineSeparator()));
                System.out.println(response);
            }

            // Cerrar la conexión
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}