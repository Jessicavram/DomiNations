/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mario;

import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import Mario.Objetos_Animados;
import Mario.Objetos_Graficos;
import sprites.Animacion;
import sprites.Hoja_Sprites;

/**
 *
 * @author Usuario
 */
public class Items extends Objetos_Animados{
    public Items(){}
    public Items(int num){        
        Hoja_Sprites map = new Hoja_Sprites();       
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("Bloque_ladrillo", 132, 84, 147, 99, 10, true, 16);
        map.Añadir_accion("Bloque_Monedas",132, 104, 147, 119, 14, true, 32);
        map.Añadir_accion("Bloque_Hongo",132, 125, 147, 140, 14, true, 32);
        map.Añadir_accion("Tubo",182, 158, 212, 201, 1, false, 1);
        map.Añadir_accion("Bloque_gris",149, 141, 164, 156, 16, true, 16);
        map.Añadir_accion("flor",359, 123, 375, 139, 1, false, 0);
        map.Añadir_accion("estrella",-1, 0, 23, 21, 32, true, 10);
        map.Añadir_accion("bloqueado",131, 142, 147, 158, 1, false, 0);
        map.Añadir_accion("Hongo", 376, 124, 391, 139, 1, false, 0);
        map.Añadir_accion("Moneda_azul", 197, 71, 212, 86, 4, true, 10);
        map.Añadir_accion("Bloque_azul", 197, 52, 212, 67, 16, true, 12);
        map.Añadir_accion("Bloque_Vida", 200, 206, 215, 231, 14, true, 32);
        
        if(num==0){
            animacion = new Animacion(map, "Bloque_ladrillo", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage());
            currentAction="Bloque_ladrillo";
        }
        if(num==1){
            animacion = new Animacion(map, "Bloque_Monedas", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage());
            currentAction="Bloque_Monedas";
        } 
        if(num==2){            
            animacion = new Animacion(map, "Bloque_Hongo", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage());
            currentAction="Bloque_Hongo";
        }
        if(num==3){
            animacion = new Animacion(map, "Tubo", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage());
            currentAction="Tubo";
        }
        if(num==4){            
            animacion = new Animacion(map, "Bloque_gris", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage());
            currentAction="Bloque_gris";
        }
        if(num==5){            
            animacion = new Animacion(map, "flor", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage());
            currentAction="flor";
        }
        if(num==6){            
            animacion = new Animacion(map, "Bloque_azul", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage());
            currentAction="Bloque_azul";
        }
        if(num==7){
            
            animacion = new Animacion(map, "Moneda_azul", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage());
            currentAction="Moneda_azul";
        }
        if(num==8){            
            animacion = new Animacion(map, "estrella", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.ESTRELLA).getImage());
            currentAction="estrella";
        }
        if(num==9){            
            animacion = new Animacion(map, "Bloque_Vida", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage());
            currentAction="Bloque_Vida";
        }
               
    }
    /**Contructor para crear objetos que se muevan
     * 
     * @param x posicion en x
     * @param y posicon en y
     * @param vx velocidad en x
     * @param num numero ue define la accion
     */
    public Items(int x,int y,int vx,int num){
        Hoja_Sprites map = new Hoja_Sprites();
        map.Añadir_accion("Bloque_ladrillo", 132, 84, 147, 99, 10, true, 16);
        map.Añadir_accion("Bloque_Monedas",132, 104, 147, 119, 14, true, 32);
        map.Añadir_accion("Bloque_Hongo",132, 125, 147, 140, 14, true, 32);
        map.Añadir_accion("Tubo",182, 158, 212, 201, 1, false, 1);
        map.Añadir_accion("Bloque_gris",149, 141, 164, 156, 16, true, 16);
        map.Añadir_accion("flor",359, 123, 375, 139, 1, false, 0);
        map.Añadir_accion("estrella",-1, 0, 23, 21, 32, true, 10);
        map.Añadir_accion("bloqueado",131, 142, 147, 158, 1, false, 0);
        map.Añadir_accion("Hongo", 376, 124, 391, 139, 1, false, 0);
        map.Añadir_accion("Moneda_azul", 197, 71, 212, 86, 4, true, 10);
        map.Añadir_accion("Bloque_azul", 197, 52, 212, 67, 16, true, 12);
        map.Añadir_accion("Bloque_Vida", 200, 206, 215, 231, 14, true, 32);
        this.dirx=1;
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=0;
        if(num==0){            
            animacion = new Animacion(map, "bloqueado", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage());
            currentAction="bloqueado";
        }
        if(num==5){            
            animacion = new Animacion(map, "Hongo", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage());
            currentAction="Hongo";
        }
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
        if(lado == Motor_Fisico.COL_LEFT || lado==Motor_Fisico.COL_RIGHT)
        changeDirx();
        if(objeto_colision instanceof Mario && lado==Motor_Fisico.COL_TOP && currentAction.equals("bloqueado")){
            Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.BLO_BLOQ, false, false);
        }
        if(objeto_colision instanceof Mario && lado==Motor_Fisico.COL_TOP && currentAction.equals("Bloque_Hongo")){
           Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.SALIR_HONGO, false, false);
           colision=1;
           currentAction="bloqueado"; 
           animacion.Seleccionar_Accion(currentAction, true);
        } 
       if(objeto_colision instanceof Mario && lado==Motor_Fisico.COL_TOP && (currentAction.equals("Bloque_Monedas") || currentAction.equals("Moneda_azul") )){
            Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MONEDA, false, false);
            colision=2;
            currentAction="bloqueado"; 
            animacion.Seleccionar_Accion(currentAction, true);
        } 
       if(objeto_colision instanceof Mario && lado==Motor_Fisico.COL_TOP && ( currentAction.equals("Bloque_ladrillo") || currentAction.equals( "Bloque_azul") ) ){
            System.out.println("ladrillo con mario");
        } 
       else if(objeto_colision instanceof Mario && currentAction.equals("estrella")){
             //esto es para cambiar en escena el estado d ela escena para cambiar de mundo.       
             colision=10;
             Cargar_Sonidos.obtener_instancia().detener_pista(Cargar_Sonidos.MUNDO_1);
             Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.FIN_PISTA, false , false);
             Borrar();       
         }
       
       
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
        changeDirx();
        if(objeto_colision instanceof  Mario && currentAction.equals("Hongo") ){
          Borrar();
            System.out.println("hongo-hongo");
       }
        if(objeto_colision instanceof  Mario && currentAction.equals("flor") ){
          Borrar();
            System.out.println("flor");
       }
    }
    @Override
    public void Actualizar_Objeto_Grafico(double timePassed){
        Actualizar_PosicionX();
        super.Actualizar_Objeto_Grafico(timePassed);
    }
    
}
