package Objetos;

import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Bateria extends SuperObject{

    public OBJ_Bateria() {
        name = "Bateria";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/items/BateriaDelTiempo.png"));
        }catch(IOException e ){
            e.printStackTrace();
        }
    }
}
