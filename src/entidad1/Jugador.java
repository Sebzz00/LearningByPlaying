package entidad1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import juego.EntradaTeclado;
import juego.PanelJuego;

public class Jugador extends Entidad {

    PanelJuego pj;
    EntradaTeclado et;
    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Jugador(PanelJuego pj, EntradaTeclado et) {
        this.et = et;
        this.pj = pj;
        screenX = pj.pantallaalto / 2 - (pj.tamañopantalla / 2);
        screenY = pj.pantallaancho / 2 - (pj.tamañopantalla / 2);

        solidArea = new Rectangle(6, 12, 25, 25);
        solidAreaDefaultX = 6;
        solidAreaDefaultY = 12;

        setDefaultValues();
        getJugadorImagen();
        direccion = "izquierda";
    }

    public void setDefaultValues() {
        mundoX = pj.tamañopantalla * 23;
        mundoY = pj.tamañopantalla * 21;
        velocidad = 4;
    }

    public void getJugadorImagen() {
        try {
            izquierda1 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteleft1.png"));
            izquierda2 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteleft2.png"));
            izquierda3 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteleft3.png"));
            izquierda4 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteleft4.png"));
            ///derecha 
            derecha1 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteright1.png"));
            derecha2 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteright2.png"));
            derecha3 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteright3.png"));
            derecha4 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteright4.png"));
            //Arriba
            arriba1 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteup1.png"));
            arriba2 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteup2.png"));
            arriba3 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteup3.png"));
            arriba4 = ImageIO.read(getClass().getResourceAsStream("/jugador/spriteup4.png"));
            ///abajo
            abajo1 = ImageIO.read(getClass().getResourceAsStream("/jugador/spritedown1.png"));
            abajo2 = ImageIO.read(getClass().getResourceAsStream("/jugador/spritedown2.png"));
            abajo3 = ImageIO.read(getClass().getResourceAsStream("/jugador/spritedown3.png"));
            abajo4 = ImageIO.read(getClass().getResourceAsStream("/jugador/spritedown4.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizar() {

        if (et.abajoPresionado == true || et.arribaPresionado == true || et.derechaPresionado == true || et.izquierdaPresionado == true) {
            if (et.arribaPresionado == true) {
                direccion = "arriba";
            } else {
                if (et.abajoPresionado == true) {
                    direccion = "abajo";
                } else {
                    if (et.izquierdaPresionado == true) {
                        direccion = "izquierda";
                    } else {
                        if (et.derechaPresionado == true) {
                            direccion = "derecha";
                        }
                    }
                }
            }

            //Verificando el estado de la colision
            collisionOn = false;
            pj.cChecker.checkTile(this);
            //Verificando colision con objeto
            int indexobj = pj.cChecker.checkObject(this, true);
            pickUpObj(indexobj);

            //Caso falso, el jugador se mueve
            if (collisionOn == false) {
                switch (direccion) {
                    case "arriba":
                        mundoY -= velocidad;
                        break;
                    case "abajo":
                        mundoY += velocidad;
                        break;
                    case "derecha":
                        mundoX += velocidad;
                        break;
                    case "izquierda":
                        mundoX -= velocidad;
                        break;
                }
            }

            spritecontador++;

            if (spritecontador > 12) {
                spriteNum++;
                if (spriteNum > 4) {
                    spriteNum = 1;
                }
                spritecontador = 0;
            }
        }

    }

    public void pickUpObj(int i) {
        if (i != 999) {
            String Objectname = pj.obj[i].name;
            switch (Objectname) {
                case "Bateria":
                    pj.playSE(2);
                    hasKey++;
                    pj.obj[i] = null;
                    System.out.println("bateria obtenida"+"\n"+"Baterias: "+ hasKey);
                    break;
                case "Puerta":
                    if (hasKey > 0) {
                        pj.playSE(3);
                        hasKey--;
                        pj.obj[i] = null;
                        System.out.println("Baterias: "+ hasKey);
                    }else{
                        System.out.println("Llaves insuficientes");
                    }
                    break;
            }
        }
    }

    public void dibujar(Graphics2D g2) {
        //g2.setColor(Color.blue);
        //g2.fillRect(x, y, pj.tamañopantalla, pj.tamañopantalla);

        BufferedImage imagen = null;

        switch (direccion) {
            case "izquierda":
                if (spriteNum == 1) {
                    imagen = izquierda1;
                }
                if (spriteNum == 2) {
                    imagen = izquierda2;
                }
                if (spriteNum == 3) {
                    imagen = izquierda3;
                }
                if (spriteNum == 4) {
                    imagen = izquierda4;
                }
                break;
            case "derecha":
                if (spriteNum == 1) {
                    imagen = derecha1;
                }
                if (spriteNum == 2) {
                    imagen = derecha2;
                }
                if (spriteNum == 3) {
                    imagen = derecha3;
                }
                if (spriteNum == 4) {
                    imagen = derecha4;
                }
                break;
            case "arriba":
                if (spriteNum == 1) {
                    imagen = arriba1;
                }
                if (spriteNum == 2) {
                    imagen = arriba2;
                }
                if (spriteNum == 3) {
                    imagen = arriba3;
                }
                if (spriteNum == 4) {
                    imagen = arriba4;
                }
                break;
            case "abajo":
                if (spriteNum == 1) {
                    imagen = abajo1;
                }
                if (spriteNum == 2) {
                    imagen = abajo2;
                }
                if (spriteNum == 3) {
                    imagen = abajo3;
                }
                if (spriteNum == 4) {
                    imagen = abajo4;
                }

                break;
        }
        g2.drawImage(imagen, screenX, screenY, pj.tamañopantalla, pj.tamañopantalla, null);
    }
}
