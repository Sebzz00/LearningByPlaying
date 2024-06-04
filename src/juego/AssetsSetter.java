package juego;

import Objetos.OBJ_Bateria;
import Objetos.OBJ_Puerta;

public class AssetsSetter {
    PanelJuego pj;

    public AssetsSetter(PanelJuego pj) {
        this.pj = pj;
    }
    
    public void SetObjects(){
        pj.obj[0] = new OBJ_Bateria() ;
        pj.obj[0].mundoX = 23 * pj.tamañopantalla;
        pj.obj[0].mundoY = 7 * pj.tamañopantalla;
        
        pj.obj[1] = new OBJ_Bateria() ;
        pj.obj[1].mundoX = 23 * pj.tamañopantalla;
        pj.obj[1].mundoY = 40 * pj.tamañopantalla;
        
        pj.obj[2] = new OBJ_Bateria() ;
        pj.obj[2].mundoX = 38 * pj.tamañopantalla;
        pj.obj[2].mundoY = 8 * pj.tamañopantalla;
        
        pj.obj[3] = new OBJ_Puerta() ;
        pj.obj[3].mundoX = 10 * pj.tamañopantalla;
        pj.obj[3].mundoY = 11 * pj.tamañopantalla;
        
        pj.obj[4] = new OBJ_Puerta() ;
        pj.obj[4].mundoX = 8 * pj.tamañopantalla;
        pj.obj[4].mundoY = 28 * pj.tamañopantalla;
        
        pj.obj[5] = new OBJ_Puerta() ;
        pj.obj[5].mundoX = 12 * pj.tamañopantalla;
        pj.obj[5].mundoY = 22 * pj.tamañopantalla;
        
        
    }
}
