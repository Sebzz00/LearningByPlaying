package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import juego.PanelJuego;

public final class TileDirector {

    PanelJuego pj;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileDirector(PanelJuego pj) {
        this.pj = pj;

        tile = new Tile[10];
        mapTileNum = new int[pj.maximoMundoCol][pj.maximoMundoRow];
        getTileImagen();
        cargarmapa("/mapas/mapa02.txt");
    }

    public void getTileImagen() {
        try {
            /////guardar las imagenes en un vector, cada indice del vector significa una imagen diferente que sera agregada al mapa 
            tile[0] = new Tile();
            tile[0].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].colision = true;

            tile[2] = new Tile();
            tile[2].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[2].colision = true;
            
            tile[3] = new Tile();
            tile[3].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].colision = true;
            
            tile[5] = new Tile();
            tile[5].imagen = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarmapa(String rutaArchivo) {

        try {
            InputStream is = getClass().getResourceAsStream(rutaArchivo);
            BufferedReader bf = new BufferedReader(new InputStreamReader(is));
            int ancho = 0;
            int alto = 0;
            while (ancho < pj.maximoMundoCol && alto < pj.maximoMundoRow) {
                String linea = bf.readLine();

                while (ancho < pj.maximoMundoCol) {
                    String numeros[] = linea.split(" ");
                    int num = Integer.parseInt(numeros[ancho]);
                    mapTileNum[ancho][alto] = num;
                    ancho++;
                }
                if (ancho == pj.maximoMundoCol) {
                    ancho = 0;
                    alto++;
                }
            }
            bf.close();

        } catch (Exception e) {
        }

    }

    public void dibujar(Graphics2D g2) {

        int mundocol = 0;
        int mundorow = 0;

        while (mundocol < pj.maximoMundoCol && mundorow < pj.maximoMundoRow) {
            int tilenumero = mapTileNum[mundocol][mundorow];
            int mundoX = mundocol * pj.tamañopantalla;
            int mundoY = mundorow * pj.tamañopantalla;
            int screenX = mundoX - pj.jugador.mundoX + pj.jugador.screenX;
            int screenY = mundoY - pj.jugador.mundoY + pj.jugador.screenY;

            if (mundoX + pj.tamañopantalla > pj.jugador.mundoX - pj.jugador.screenX
                    && mundoX - pj.tamañopantalla < pj.jugador.mundoX + pj.jugador.screenX
                    && mundoY + pj.tamañopantalla > pj.jugador.mundoY - pj.jugador.screenY
                    && mundoY - pj.tamañopantalla < pj.jugador.mundoY + pj.jugador.screenY) {
                g2.drawImage(tile[tilenumero].imagen, screenX, screenY, pj.tamañopantalla, pj.tamañopantalla, null);

            }
            mundocol++;
            if (mundocol == pj.maximoMundoCol) {
                mundocol = 0;
                mundorow++;

            }
        }

    }

}
