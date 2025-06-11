package it.unibs.pajc.dk1981.DKvsMARIO1981.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import it.unibs.pajc.dk1981.DKvsMARIO1981.audio.AudioManager;
import it.unibs.pajc.dk1981.DKvsMARIO1981.view.GUIUtils;

public class GameMenu extends JFrame {

    private BufferedImage titleImage;
    private Font retroFont;
    private boolean showText = true;
    private Runnable onStart;

    public GameMenu(Runnable onStart) {
        this.onStart = onStart;
        setTitle("DK vs Mario - Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        GUIUtils.applyIcon(this);
        
        AudioManager.pauseBackgroundMusic(); // ferma temporaneamente la musica di gioco
        AudioManager.playOneShotMusic("/MUSIC/howhigh.wav"); // suona una volta la musica del menu
        
        // Carica immagine del title
        try {
            titleImage = ImageIO.read(getClass().getResource("/MENU/Title.png"));
        } catch (IOException e) {
            System.err.println("Errore nel caricamento dell'immagine del personaggio");
            e.printStackTrace();
        }

        // Carica il font retro
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

        // Crea pannello personalizzato per il menu
        JPanel menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                if (titleImage != null) {
                    int imgWidth = titleImage.getWidth();
                    int imgHeight = titleImage.getHeight();
                    float aspectRatio = (float) imgWidth / imgHeight;

                    int newImgWidth = panelWidth / 2;
                    int newImgHeight = (int) (newImgWidth / aspectRatio);

                    int x = (panelWidth - newImgWidth) / 2;
                    int y = (panelHeight - newImgHeight) / 2;

                    g.drawImage(titleImage, x, y, newImgWidth, newImgHeight, this);

                    // Testo lampeggiante
                    if (showText) {
                        g.setColor(new Color(200, 200, 0));
                        int dynamicFontSize = Math.max(8, Math.min(18, getHeight() / 25));
                        Font dynamicFont = retroFont.deriveFont((float) dynamicFontSize);
                        g.setFont(dynamicFont);

                        String text = "CLICK ANYWHERE TO START";
                        int textWidth = g.getFontMetrics().stringWidth(text);
                        g.drawString(text, (panelWidth - textWidth) / 2, y + newImgHeight + 20);
                    }

                    // Scritta copyright in basso
                    g.setColor(new Color(200, 200, 0));
                    int copyrightFontSize = Math.max(8, getHeight() / 45);
                    Font copyrightFont = retroFont.deriveFont((float) copyrightFontSize);
                    g.setFont(copyrightFont);

                    String copyright = "© 2025 DK vs Mario – All rights reserved – Elisa & Arianna";
                    int textWidth = g.getFontMetrics().stringWidth(copyright);
                    int xText = (panelWidth - textWidth) / 2;
                    int yBottom = getHeight() - 20;

                    g.drawString(copyright, xText, yBottom);
                }
            }
        };

        menuPanel.setBackground(Color.BLACK);

        // Timer per lampeggiare il testo
        Timer blinkTimer = new Timer(500, e -> {
            showText = !showText;
            menuPanel.repaint();
        });
        blinkTimer.start();

        // Listener per click ovunque
        menuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Chiudi il menu
                
                if (onStart != null) {
                    onStart.run(); // Lancia il gioco passato da fuori (single o multi)
                }

                AudioManager.playBackgroundMusic("/MUSIC/bacmusic.wav");
            }
        });

        setContentPane(menuPanel);
        setVisible(true);
    }
}