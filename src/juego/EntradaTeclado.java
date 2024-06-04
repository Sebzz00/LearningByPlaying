
package juego;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class EntradaTeclado implements KeyListener {

    public boolean arribaPresionado, abajoPresionado, derechaPresionado, izquierdaPresionado;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            arribaPresionado = true;
        }

        if (code == KeyEvent.VK_S) {
            abajoPresionado = true;
        }

        if (code == KeyEvent.VK_A) {
            izquierdaPresionado = true;
        }

        if (code == KeyEvent.VK_D) {
            derechaPresionado = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            arribaPresionado = false;
        }

        if (code == KeyEvent.VK_S) {
            abajoPresionado = false;
        }

        if (code == KeyEvent.VK_A) {
            izquierdaPresionado = false;
        }

        if (code == KeyEvent.VK_D) {
            derechaPresionado = false;
        }

    }

}
