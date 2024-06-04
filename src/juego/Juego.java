package juego;

import javax.swing.JFrame;

public class Juego {

    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Viajando En El Tiempo I");

        PanelJuego panelJuego = new PanelJuego();
        window.add(panelJuego);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        panelJuego.setUpJuego();
        panelJuego.startGameThread();

    }

}
