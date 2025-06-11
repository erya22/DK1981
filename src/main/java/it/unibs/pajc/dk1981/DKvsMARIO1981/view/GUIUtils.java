package it.unibs.pajc.dk1981.DKvsMARIO1981.view;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GUIUtils {
    public static void applyIcon(JFrame frame) {
        try {
            Image icon = Toolkit.getDefaultToolkit().getImage(GUIUtils.class.getResource("/PLAYER/a1.png"));
            frame.setIconImage(icon);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}