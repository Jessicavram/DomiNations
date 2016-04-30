/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;
import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import sprites.*;

public class Tomate extends Objetos_Animados {
    boolean muerto;
    boolean golpe;
    public Tomate(){
        Hoja_Sprites map = new Hoja_Sprites();
        
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("ICaminar", 0, 99, 15, 120, 2, true, 7);
        map.Añadir_accion("DCaminar", 32, 99, 47, 120, 2, true, 7);
        map.Añadir_accion("IMuerto", 78, 99, 93, 120, 1, true, 0);
        map.Añadir_accion("DMuerto", 63, 99, 78, 120, 1, true, 0);

            //Crear la hoja de sprites
            animacion = new Animacion(map, "ICaminar", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.ENEMIGOS).getImage());
        dirx = -1;
        setVx(1);
    }
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int Lado) {
        if( Lado == Motor_Fisico.COL_LEFT || Lado == Motor_Fisico.COL_RIGHT){
            changeDirx();
            if(!muerto){
            currentAction = (dirx==1?"D":"I")+"Caminar";
            animacion.Seleccionar_Accion(currentAction, true);
            }
        }
    }
    
    @Override
    public void Actualizar_Objeto_Grafico(double timePassed){
        Actualizar_PosicionX();
        super.Actualizar_Objeto_Grafico(timePassed);
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
        changeDirx();
        currentAction = (dirx==1?"D":"I")+"Caminar";
        animacion.Seleccionar_Accion(currentAction, true);
         if( objeto_colision instanceof Mario  && lado==Motor_Fisico.COL_BOTTOM ){
             Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.ESP_ENEMIGO, false, false);  
             Borrar();
        }        
    }

}
