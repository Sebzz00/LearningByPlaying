package juego;

import Objetos.SuperObject;
import entidad1.Jugador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import tile.TileDirector;

public class PanelJuego extends JPanel implements Runnable {

    ///COnfiguracion de la pantalla 
    final int tamañooriginal = 16; ///16x16
    final int escala = 3;
    public int tamañopantalla = escala * tamañooriginal; //48x48

    public int tamañomaximocol = 16;
    public int tamañomaximorow = 12;

    public int pantallaalto = tamañopantalla * tamañomaximocol;
    public int pantallaancho = tamañopantalla * tamañomaximorow;
    /////////////////////////

    EntradaTeclado et = new EntradaTeclado();
    TileDirector tileD = new TileDirector(this);
    Thread juegoThread;
    Sound sound = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetsSetter aSetter = new AssetsSetter(this);
    
    ///Entidades y Objetos
    public Jugador jugador = new Jugador(this, et);
    public SuperObject obj[] = new SuperObject[10];

    /// Configuracion del mundo 
    ///si se quiere aumentar el tamaño del mapa del archivo .txt
    public final int maximoMundoCol = 50;
    public final int maximoMundoRow = 50;
    public final int mundoAncho = tamañopantalla * maximoMundoCol;
    public final int mundoAlto = tamañopantalla * maximoMundoRow;
    int fps = 60;
    public final int statetitulo =0;
    
    ///Dejar posicion predeterminada al personaje 
    int jugadorX = 100;
    int jugadorY = 100;
    int velocidadjugador = 4;

    public PanelJuego() {
        this.setPreferredSize(new Dimension(pantallaalto, pantallaancho));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(et);
        this.setFocusable(true);
    }

    public void setUpJuego() {
        aSetter.SetObjects();
        playMusic(0);
    }

    public void startGameThread() {
        juegoThread = new Thread(this);
        juegoThread.start();
    }

    @Override
    public void run() {

        double intervaloDibujo = 1000000000 / fps;  ////0.01666 segundos 
        double siguientetiempodibujo = System.nanoTime() + intervaloDibujo;
        while (juegoThread != null) {
            actualizar();
            repaint();

            try {
                double tiempoRestante = siguientetiempodibujo - System.nanoTime();
                tiempoRestante = tiempoRestante / 1000000;
                if (tiempoRestante < 0) {
                    tiempoRestante = 0;
                }

                Thread.sleep((long) tiempoRestante);
                siguientetiempodibujo += intervaloDibujo;
            } catch (InterruptedException ex) {
                
            }
        }
    }
    public void actualizar() {

        jugador.actualizar();

    }
    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
     
        //Pantalla Menu
        
        //Tiles
        tileD.dibujar(g2);
        
        //Objeto 
        for (int i = 0; i < obj.length; i++) {
            if(obj[i] != null){
                obj[i].draw(this, g2);
            }
        }
        
        
        //Jugador
        jugador.dibujar(g2);
        g2.dispose();

    }
    
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    
    public void stopMusic(){
        sound.stop();
    }
    
    public void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
}
