/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

import Cargador.Cargar_Imagenes;
import sprites.Animacion;
import sprites.Hoja_Sprites;

/**
 *
 * @author Carlos
 */
public class Cuartel extends Objetos_Animados{
    /**Capacidad de guerreros dentro del cuartel*/
    int capacidad_ejercito;
    /**Numero actual de guerreros dentro del cuartel*/
    int capacidad_actual;
    /**Numero de soldados en cola para ser entrenados*/
    int soldados_en_cola;
    /**Hoja de acciones posibles para el cuartel*/
    Hoja_Sprites map = new Hoja_Sprites();  
    
    public Cuartel(){              
        super(1000, 3, 3, 10, 4);              
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("Construir", 0, 0, 143, 117, 10, false, 1);
        map.Añadir_accion("Listo", 1296, 0, 1439, 117, 1, false, 0);
        //Crear la hoja de sprites
        animacion = new Animacion(map, "Construir", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CUARTEL).getImage());
  
        capacidad_ejercito=5;
        capacidad_actual=soldados_en_cola=0;
    }
    
    public void actualizar_nivel_2(){
        vida_max +=100;
        capacidad_ejercito+=5;
        nivel++;
    }
    
    public void actualizar_nivel_3(){
        vida_max +=100;
        capacidad_ejercito+=5;
        nivel++;
    }
    
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
       
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    
    }
}
    