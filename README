# Donkey KONG 1981

**Autori:** Elisa Montanari(745975) & Arianna Andreatta(745436)
**Università:** Unibs
**Linguaggio:** Java
**Architettura:** MVC • Client-Server • GUI

---

## Descrizione del progetto

**Donkey KONG 1981** è un videogioco sviluppato in Java, ispirato al classico arcade Donkey Kong. Il gioco è stato progettato seguendo i principi dell’**architettura MVC (Model-View-Controller)**, 
utilizza una comunicazione **Client-Server** per la modalità multiplayer, ed è dotato di una **interfaccia grafica (GUI)** interattiva e intuitiva per l’utente.

Il progetto è un esercizio completo di programmazione strutturata e orientata agli oggetti, gestione di interfacce grafiche, e progettazione di sistemi distribuiti. 
Abbiamo posto particolare attenzione nel separare in modo netto le responsabilità tra i diversi componenti del sistema, garantendo facilità di manutenzione del codice.

---

## Tecnologie e architetture utilizzate

### **MVC (Model-View-Controller)**

L’architettura MVC è alla base del progetto:

* **Model**: rappresenta la logica di gioco, incluse le entità come i personaggi, gli oggetti e le collisioni.
* **View**: gestisce la visualizzazione su schermo tramite una GUI Java.
* **Controller**: coordina l’input dell’utente e il flusso di dati tra il modello e la vista.

### **Client-Server**

In modalità multiplayer, il gioco stabilisce una connessione tra un **server** centrale e più **client**. 
Il server gestisce lo stato condiviso del gioco e sincronizza le azioni tra i giocatori. La modalità single-player, invece, 
non avvia alcun server, garantendo flessibilità e performance per entrambe le modalità.

### **GUI Java**

La grafica è completamente realizzata in Java con componenti personalizzati. Un menu iniziale guida l’utente nella scelta tra modalità 
single-player o multiplayer, e mostra in tempo reale i punteggi. Dopo ogni partita, l’utente può decidere se rigiocare o uscire.

---

## Istruzioni per l'esecuzione

- **Eseguire la classe `GameLauncher.java`**. Questa classe rappresenta il punto di ingresso principale del gioco.

- All'avvio verrà mostrato un **menu di selezione**:

   * **Single Player**: avvia il gioco in modalità locale, senza server.
   * **Multiplayer**: avvia automaticamente un **server** e connette il primo client. Gli altri client potranno collegarsi in seguito.

- Una volta completata la partita:

   * Viene **calcolato lo score** del giocatore o della sessione.
   * Viene mostrato un **menu di fine partita** con la possibilità di **rigiocare** dall’inizio o **chiudere il gioco**.

---

##  Requisiti tecnici

* **JDK 17 o superiore**
* **Sistema operativo**: multipiattaforma (testato su Windows, macOS, Linux)
* Nessuna dipendenza esterna richiesta: il progetto è **100% Java puro**
