package Objetos;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import juego.PanelJuego;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int mundoX, mundoY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(PanelJuego pj, Graphics2D g2) {
        int screenX = mundoX - pj.jugador.mundoX + pj.jugador.screenX;
        int screenY = mundoY - pj.jugador.mundoY + pj.jugador.screenY;

        if (mundoX + pj.tamañopantalla > pj.jugador.mundoX - pj.jugador.screenX
                && mundoX - pj.tamañopantalla < pj.jugador.mundoX + pj.jugador.screenX
                && mundoY + pj.tamañopantalla > pj.jugador.mundoY - pj.jugador.screenY
                && mundoY - pj.tamañopantalla < pj.jugador.mundoY + pj.jugador.screenY) {
            g2.drawImage(image, screenX, screenY, pj.tamañopantalla, pj.tamañopantalla, null);

        }
    }
}
