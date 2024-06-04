package juego;

import entidad1.Entidad;

public class CollisionChecker {

    PanelJuego pj;

    public CollisionChecker(PanelJuego pj) {
        this.pj = pj;
    }

    public void checkTile(Entidad entity) {

        int entityLeftWorldX = entity.mundoX + entity.solidArea.x;
        int entityRightWorldX = entity.mundoX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.mundoY + entity.solidArea.y;
        int entityBottomWorldY = entity.mundoY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / pj.tamañopantalla;
        int entityRightCol = entityRightWorldX / pj.tamañopantalla;
        int entityTopRow = entityTopWorldY / pj.tamañopantalla;
        int entityBottomRow = entityBottomWorldY / pj.tamañopantalla;

        int tileNum1, tileNum2;

        switch (entity.direccion) {
            case "arriba":
                entityTopRow = (entityTopWorldY - entity.velocidad) / pj.tamañopantalla;
                tileNum1 = pj.tileD.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = pj.tileD.mapTileNum[entityRightCol][entityTopRow];
                if (pj.tileD.tile[tileNum1].colision == true || pj.tileD.tile[tileNum2].colision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "abajo":
                entityBottomRow = (entityBottomWorldY + entity.velocidad) / pj.tamañopantalla;
                tileNum1 = pj.tileD.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = pj.tileD.mapTileNum[entityRightCol][entityBottomRow];
                if (pj.tileD.tile[tileNum1].colision == true || pj.tileD.tile[tileNum2].colision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "derecha":
                entityRightCol = (entityRightWorldX + entity.velocidad) / pj.tamañopantalla;
                tileNum1 = pj.tileD.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = pj.tileD.mapTileNum[entityRightCol][entityBottomRow];
                if (pj.tileD.tile[tileNum1].colision == true || pj.tileD.tile[tileNum2].colision == true) {
                    entity.collisionOn = true;
                }
                break;
            case "izquierda":
                entityLeftCol = (entityLeftWorldX - entity.velocidad) / pj.tamañopantalla;
                tileNum1 = pj.tileD.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = pj.tileD.mapTileNum[entityLeftCol][entityBottomRow];
                if (pj.tileD.tile[tileNum1].colision == true || pj.tileD.tile[tileNum2].colision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public int checkObject(Entidad entity, boolean player) {
        int index = 999;

        for (int i = 0; i < pj.obj.length; i++) {
            if (pj.obj[i] != null) {
                // obtenemos el solidArea position del objeto
                entity.solidArea.x = entity.mundoX + entity.solidArea.x;
                entity.solidArea.y = entity.mundoY + entity.solidArea.y;
                // obtener solid area del objeto 
                pj.obj[i].solidArea.x = pj.obj[i].mundoX + pj.obj[i].solidArea.x;
                pj.obj[i].solidArea.y = pj.obj[i].mundoY + pj.obj[i].solidArea.y;

                switch (entity.direccion) {
                    case "arriba":
                        entity.solidArea.y -= entity.velocidad;
                        if(entity.solidArea.intersects(pj.obj[i].solidArea)){
                            if(pj.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "abajo":
                        entity.solidArea.y += entity.velocidad;
                        if(entity.solidArea.intersects(pj.obj[i].solidArea)){
                            if(pj.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "derecha":
                        entity.solidArea.x += entity.velocidad;
                        if(entity.solidArea.intersects(pj.obj[i].solidArea)){
                            if(pj.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "izquierda":
                        entity.solidArea.x -= entity.velocidad;
                        if(entity.solidArea.intersects(pj.obj[i].solidArea)){
                            if(pj.obj[i].collision == true){
                                entity.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                pj.obj[i].solidArea.x = pj.obj[i].solidAreaDefaultX;
                pj.obj[i].solidArea.y = pj.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }
}
