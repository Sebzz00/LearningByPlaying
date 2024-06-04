package entidad1;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entidad {

    public int mundoX, mundoY;
    public int velocidad;

    public BufferedImage arriba1, arriba2, arriba3, arriba4, izquierda1, izquierda2, izquierda3, izquierda4, abajo1, abajo2, abajo3, abajo4, derecha1, derecha2, derecha3, derecha4;
    public String direccion;

    public int spritecontador = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
