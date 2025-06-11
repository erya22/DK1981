package it.unibs.pajc.dk1981.DKvsMARIO1981.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import it.unibs.pajc.dk1981.DKvsMARIO1981.GameApp;
import it.unibs.pajc.dk1981.DKvsMARIO1981.net.DKServer;
import it.unibs.pajc.dk1981.DKvsMARIO1981.view.GUIUtils;

public class ModeSelectionMenu extends JFrame {
    private BufferedImage titleImage;
    private Font retroFont;
    private JButton singleButton;
    private JButton multiButton;

    public ModeSelectionMenu() {
        setTitle("Seleziona modalità");
        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.setUndecorated(true); // Rimuove i bordi
        device.setFullScreenWindow(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        GUIUtils.applyIcon(this);

        // Carica immagine del titolo
        try {
            titleImage = ImageIO.read(getClass().getResource("/MENU/Title.png"));
        } catch (IOException e) {
            System.err.println("Errore nel caricamento dell'immagine del titolo");
            e.printStackTrace();
        }

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

        // Pannello principale con custom painting e pulsanti in basso
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                // Sfondo nero
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, panelWidth, panelHeight);

                if (titleImage != null) {
                    int imgWidth = titleImage.getWidth();
                    int imgHeight = titleImage.getHeight();
                    float aspectRatio = (float) imgWidth / imgHeight;

                    int newImgWidth = panelWidth / 2;
                    int newImgHeight = (int) (newImgWidth / aspectRatio);

                    int x = (panelWidth - newImgWidth) / 2;

                    // Sposta immagine un po' sopra (30% dall'alto)
                    int y = (int)(panelHeight * 0.4) - newImgHeight / 2;

                    g.drawImage(titleImage, x, y, newImgWidth, newImgHeight, this);

                    // Scritta "SELECT MODE" al 75% dell'altezza del pannello
                    String selectText = "SELECT MODE";
                    g.setColor(new Color(200, 200, 0));
                    int fontSize = Math.max(12, panelHeight / 30);
                    Font font = retroFont.deriveFont((float) fontSize);
                    g.setFont(font);

                    FontMetrics fm = g.getFontMetrics();
                    int textWidth = fm.stringWidth(selectText);
                    int textX = (panelWidth - textWidth) / 2;

                    // Qui posiziona il testo circa al 75% altezza
                    int textY = (int)(panelHeight * 0.75);

                    g.drawString(selectText, textX, textY);
                }
            }
        };

        mainPanel.setLayout(new BorderLayout());
        setContentPane(mainPanel);

        // Pannello pulsanti in basso
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 30));
        buttonPanel.setOpaque(false);

        singleButton = createStyledButton("Singleplayer");
        multiButton = createStyledButton("Multiplayer");

        // Dimensione pulsanti più grandi e scritte leggermente più piccole
        singleButton.setPreferredSize(new Dimension(220, 55));
        multiButton.setPreferredSize(new Dimension(220, 55));
        singleButton.setFont(retroFont.deriveFont(14f));
        multiButton.setFont(retroFont.deriveFont(14f));

        buttonPanel.add(singleButton);
        buttonPanel.add(multiButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Azioni pulsanti
        singleButton.addActionListener(e -> {
            dispose();
            new GameMenu(() -> {
            	GameApp gameApp = new GameApp();
				gameApp.initializesinglePlayer();
            });
        });

        multiButton.addActionListener(e -> {
            dispose();
            new Thread(() -> new DKServer().start()).start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            SwingUtilities.invokeLater(() -> {
                new GameMenu(() -> {
                	GameApp gameApp = new GameApp();
                    gameApp.initializemultiPlayer("Player1");
                });
            });
        });

        setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.YELLOW);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);

        // Hover effect colore testo
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn.setForeground(new Color(255, 255, 150));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                btn.setForeground(Color.YELLOW);
            }
        });

        return btn;
    }
}