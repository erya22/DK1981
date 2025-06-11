package it.unibs.pajc.dk1981.DKvsMARIO1981.menu;

import javax.swing.*;
import java.awt.*;

public class GameResultDialog extends JDialog {
    private Font retroFont;
    private Window player1Window;
    private Window player2Window;
    
    // costruttore per dialog singleplayer
    public GameResultDialog(Window owner, int playerScore) {
        super(owner, "Game Over", ModalityType.APPLICATION_MODAL);
        initSinglePlayerUI(playerScore);
    }

    // costruttore per dialog multiplayer
    public GameResultDialog(Window p1, Window p2, int p1Score, int p2Score, String winner) {
        super(p1, "Game Over", ModalityType.APPLICATION_MODAL);
        this.player1Window = p1;
        this.player2Window = p2;
        initUI(p1Score, p2Score, winner);
    }

    private void initSinglePlayerUI(int score) {
        try {
            retroFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/FONTS/PressStart2P-Regular.ttf"))
                    .deriveFont(Font.PLAIN, 16f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(retroFont);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento del font retro");
            retroFont = new Font("Monospaced", Font.BOLD, 16); // fallback
            e.printStackTrace();
        }

        setSize(400, 250);
        setLocationRelativeTo(getOwner());

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(retroFont);
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        centerPanel.setBackground(Color.BLACK);

        Window owner = getOwner();
        Font smallerFont = retroFont.deriveFont(10f);

        JLabel scoreLabel = new JLabel("YOUR SCORE: " + score, SwingConstants.CENTER);
        scoreLabel.setForeground(Color.YELLOW);
        scoreLabel.setFont(smallerFont);
        centerPanel.add(scoreLabel);

        JLabel thanksLabel = new JLabel("THANKS FOR PLAYING!", SwingConstants.CENTER);
        thanksLabel.setForeground(Color.GREEN);
        thanksLabel.setFont(smallerFont);
        centerPanel.add(thanksLabel);

        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.BLACK);

        JLabel replayLabel = new JLabel("DO YOU WANT TO REPLAY?", SwingConstants.CENTER);
        replayLabel.setForeground(Color.WHITE);
        replayLabel.setFont(retroFont);
        bottomPanel.add(replayLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setBackground(Color.BLACK);

        JButton yesButton = new JButton("YES");
        yesButton.setFont(retroFont.deriveFont(10f));
        yesButton.setBackground(Color.DARK_GRAY);
        yesButton.setForeground(Color.WHITE);
        yesButton.setFocusPainted(false);
        yesButton.addActionListener(e -> {
            dispose();
            if (owner != null) {
                owner.dispose(); // Chiude la finestra principale del gioco
            }
            new Thread(() -> GameLauncher.main(new String[]{})).start(); // rilancia il gioco
        });

        JButton noButton = new JButton("NO");
        noButton.setFont(retroFont.deriveFont(10f));
        noButton.setBackground(Color.DARK_GRAY);
        noButton.setForeground(Color.WHITE);
        noButton.setFocusPainted(false);
        noButton.addActionListener(e -> {
            dispose(); // chiudi dialog
            if (owner != null) {
                owner.dispose(); // chiudi la finestra principale
            }
            System.exit(0); // termina il programma
        });


        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);

        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setResizable(false);
    }

    
    private void initUI(int p1Score, int p2Score, String winner) {
        // Carica font retro
        try {
            retroFont = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("/FONTS/PressStart2P-Regular.ttf"))
                    .deriveFont(Font.PLAIN, 16f);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(retroFont);
        } catch (Exception e) {
            System.err.println("Errore nel caricamento del font retro");
            retroFont = new Font("Monospaced", Font.BOLD, 16); // fallback
            e.printStackTrace();
        }

        setSize(400, 300);
        setLocationRelativeTo(getOwner());

        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Titolo centrale
        JLabel titleLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        titleLabel.setForeground(Color.RED);
        titleLabel.setFont(retroFont);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Pannello con punteggi
        JPanel scoresPanel = new JPanel(new GridLayout(3, 1, 5, 2));
        scoresPanel.setBackground(Color.BLACK);

        Window owner = getOwner();
        Font smallerFont = retroFont.deriveFont(10f); // Font ridotto per gli score

        JLabel p1Label = new JLabel("Player 1 score: " + p1Score, SwingConstants.CENTER);
        p1Label.setForeground(Color.YELLOW);
        p1Label.setFont(smallerFont);
        scoresPanel.add(p1Label);

        JLabel p2Label = new JLabel("Player 2 score: " + p2Score, SwingConstants.CENTER);
        p2Label.setForeground(Color.YELLOW);
        p2Label.setFont(smallerFont);
        scoresPanel.add(p2Label);

        // Gestione pareggio o vincitore
        String resultText = (p1Score == p2Score) ? "DRAW" : winner + " wins!";
        JLabel winnerLabel = new JLabel(resultText, SwingConstants.CENTER);
        winnerLabel.setForeground(Color.GREEN);
        winnerLabel.setFont(retroFont);
        scoresPanel.add(winnerLabel);

        panel.add(scoresPanel, BorderLayout.CENTER);

        // Pannello inferiore: Replay domanda + bottoni
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.BLACK);

        JLabel replayLabel = new JLabel("DO YOU WANT TO REPLAY?", SwingConstants.CENTER);
        replayLabel.setForeground(Color.WHITE);
        replayLabel.setFont(retroFont);
        bottomPanel.add(replayLabel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonsPanel.setBackground(Color.BLACK);

        JButton yesButton = new JButton("YES");
        yesButton.setFont(retroFont.deriveFont(10f));
        yesButton.setBackground(Color.DARK_GRAY);
        yesButton.setForeground(Color.WHITE);
        yesButton.setFocusPainted(false);
        yesButton.addActionListener(e -> {
            dispose(); // Chiude il dialog
            if (player1Window != null) player1Window.dispose();
            if (player2Window != null) player2Window.dispose();
            new Thread(() -> GameLauncher.main(new String[]{})).start();
        });

        JButton noButton = new JButton("NO");
        noButton.setFont(retroFont.deriveFont(10f));
        noButton.setBackground(Color.DARK_GRAY);
        noButton.setForeground(Color.WHITE);
        noButton.setFocusPainted(false);
        noButton.addActionListener(e -> {
            dispose(); // chiudi dialog
            if (owner != null) {
                owner.dispose(); // chiudi la finestra principale
            }
            System.exit(0); // termina il programma
        });


        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);

        bottomPanel.add(buttonsPanel, BorderLayout.SOUTH);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(panel);
        setResizable(false);
    }
}