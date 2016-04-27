/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Mario;

import Cargador.Cargar_Imagenes;
import sprites.Animacion;
import sprites.Hoja_Sprites;

/**
 *
 * @author Usuario
 */
public class Planta extends Objetos_Animados{
     boolean activa=true;
    public Planta(){
        Hoja_Sprites map = new Hoja_Sprites();
      
        //Definir las acciones en la hoja de sprites 173
        map.Añadir_accion("Planta", 0, 23, 15, 44, 9, false, 4);

            //Crear la hoja de sprite
            currentAction="Planta";
            animacion = new Animacion(map, "Planta", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.PLANTA).getImage());
    }
    @Override
    public void Actualizar_Objeto_Grafico(double timePassed){
        //if(animacion.termino_animacion)activa=false;
        if(Motor_Juego.cont%250==0 && !activa){
            activa=true;
            animacion.Reiniciar_Accion();
        }
        if(animacion.termino_animacion && activa){activa=false;
        }
        else if(activa){super.Actualizar_Objeto_Grafico(timePassed);
        }
        
    }
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int Lado) {}
    @Override
    
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
        if( objeto_colision instanceof Mario ){
            Mario nuevo = (Mario) objeto_colision;
            if( nuevo.getX()-getX()<=-12 || nuevo.getX()-getX()>=12  ){
                activa=false;
            }else
                activa=true;
        }
        
    }
    
}
