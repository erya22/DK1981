package it.unibs.pajc.dk1981.DKvsMARIO1981.net;

import java.io.*;
import java.net.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DKServer {
	private static final Logger log = LoggerFactory.getLogger(DKServer.class);
    public static final int PORT = 5555;
    
    public void start() {
    	log.info("[SERVER] Avvio, in ascolto su porta {}", PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            // 1) Accetta Player1
            log.info("[SERVER] In attesa di Player1...");
            Socket client1 = serverSocket.accept();
            log.info("[SERVER] Player1 connesso da: {}", client1.getRemoteSocketAddress());
            BufferedReader in1   = new BufferedReader(new InputStreamReader(client1.getInputStream()));
            PrintWriter out1      = new PrintWriter(client1.getOutputStream(), true);
            String join1 = in1.readLine(); // es: "JOIN#Player1"
            log.info("[SERVER] ricevuto: {}", join1);

            // 2) Accetta Player2
            log.info("[SERVER] In attesa di Player2...");
            Socket client2 = serverSocket.accept();
            log.info("[SERVER] Player2 connesso da: {}", client2.getRemoteSocketAddress());
            BufferedReader in2   = new BufferedReader(new InputStreamReader(client2.getInputStream()));
            PrintWriter out2      = new PrintWriter(client2.getOutputStream(), true);
            String join2 = in2.readLine(); // es: "JOIN#Player2"
            log.info("[SERVER] ricevuto: {}", join2);

            // 3) Entrambi si sono registrati → mando START a tutti e due
            out1.println("START");
            out2.println("START");
            log.info("[SERVER] Inviato START a entrambi i giocatori.");

            // QUA IMPLEMENTA BENE QUESTIONE PUNTEGGI!!!
            // 4) Aspetta i due punteggi in due thread separati (ma usando variabili condivise)
            final int[] scores = new int[2]; // [0]=score1, [1]=score2
            Thread t1 = new Thread(() -> {
                try {
                    String line = in1.readLine();
                    // es: "SCORE#123"
                    if (line != null && line.startsWith("SCORE#")) {
                        scores[0] = Integer.parseInt(line.substring(6));
                        log.info("[SERVER] Ricevuto score1 = {}", scores[0]);
                    }
                } catch (IOException e) {
                	log.error("Player1: IOError", e);
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    String line = in2.readLine();
                    // es: "SCORE#150"
                    if (line != null && line.startsWith("SCORE#")) {
                        scores[1] = Integer.parseInt(line.substring(6));
                        log.info("[SERVER] Ricevuto score2 = {}", scores[1]);
                    }
                } catch (IOException e) {
                	log.error("Player2: IOError", e);
                }
            });

            t1.start();
            t2.start();
            // Attendo che entrambi finiscano di leggere il loro SCORE
            t1.join();
            t2.join();

            // 5) Calcolo vincitore
            String winner;
            if (scores[0] > scores[1])            winner = "Player1";
            else if (scores[1] > scores[0])       winner = "Player2";
            else                                  winner = "DRAW";

            log.info("[SERVER] Fine raccolta punteggi. Vincitore = {}", winner);

            // 6) Invio RESULT a entrambi
            out1.println("RESULT#" + winner);
            out2.println("RESULT#" + winner);

            // 7) Chiudo risorse
            client1.close();
            client2.close();
            log.info("[SERVER] Connessioni chiuse. Server terminato.");

        } catch (IOException | InterruptedException e) {
        	log.error("Server error", e);
        }
    }
}