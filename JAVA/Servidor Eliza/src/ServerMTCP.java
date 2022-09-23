import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * Servidor Multihilo TCP, hace eco de lo que recibe
 */

public class ServerMTCP {

    public static void main(String args[]) {

        // Establecemos el número de puerto a utilizar.
        int serverPort = 4444;

        // Creamos una instancia para esperar las peticiones de los clientes.
        ServerSocket listenSocket;

        // Socket para conexión.
        Socket clientSocket;

        // Usamos la clase conection.
        Connection c;

        try {

            // Creamos el objeto para esperar peticiones de los clientes.
            listenSocket = new ServerSocket(serverPort);

            // CICLO DEL SERVIDOR
            while (true) {

                // Dejamos invocado el servidor esperando haste que un cliente
                // se conecte. clientSocket = Socket nuevo para comunicaciones.
                clientSocket = listenSocket.accept();
                System.out.println(" NUEVO CLIENTE: " + clientSocket.getInetAddress() +
                        " PUERTO:" + clientSocket.getPort());
                // Se establece la conexión, y se vuelve a esperar un nuevo cliente.
                c = new Connection(clientSocket);

            }

            // Control de excepciones.
        } catch (IOException e) {
            System.out.println("Error en socket: " + e.getMessage());
        }
    }
}

/**
 *
 * @author Iván Durango
 *         Hilo de ejecución que procesa cada una de la peticiones
 */
class Connection extends Thread {

    // Instanciamos los flujos de datos de entrada y salida,y el socket para
    // conexión.
    Socket clientSocket;
    BufferedReader entrada = null;
    PrintWriter salida = null;

    // No hace nada
    public static String servidorEliza(String cadena) {

        HashMap<String, String> theliza = new HashMap<String, String>();

        // Asigno palabras clave a respuesta
        theliza.put("HOLA", "Hola, ¿qué tal?");
        theliza.put("ENCANTADO", "Encantado de conocerte yo también");
        theliza.put("ADIOS", "Adiós, espero volverte a ver pronto");
        theliza.put("HORA", "Los siento no llevo reloj");
        theliza.put("NOMBRE", "Mi nombre es Eliza");
        theliza.put("CACA", "Creo que tu lenguaje no es adecuado");
        String otro = "Lo siento, no te comprendo.";

        Scanner sc = new Scanner(System.in);

        for (Map.Entry<String, String> entrada : theliza.entrySet()) {
            // Si la linea contiene la entrada en al clave
            if (cadena.toUpperCase().contains(entrada.getKey())) {
                return entrada.getValue();
            }
        }

        sc.close();
        return otro;
    }

    // Constructor.
    public Connection(Socket aSocket) {

        // Asocia el Socket(this) con el del cliente que conecta.
        clientSocket = aSocket;

        try {

            // Creamos los flujos de entrada y salida de datos, y lo se los
            // asociamos al socket cliente.
            // Establece canal de entrada
            entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            // Establece canal de salida
            salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())), true);

        } catch (IOException ex) {
            System.out.println("Error en conexion: " + ex.getMessage());
        }

        // Lanzamos el método run.
        this.start();
    }

    public void run() {

        try {

            while (true) {
                // Recibe los datos mandados por el cliente.
                String msg = entrada.readLine();
                System.out.println("Cliente: " + msg);
                // Realiza la réplica de los datos
                salida.println(">>" + servidorEliza(msg));
                // Si el mensaje es de terminar
                if (servidorEliza(msg).equals(msg))
                    break;
            }

            // Control de excepciones.
        } catch (Exception ex) {
            System.err.println(" Fin / Error de conexión." + clientSocket.getInetAddress() + "\n");
        }
    }
}
