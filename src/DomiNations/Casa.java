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
 * @author Tyson
 */
public class Casa extends Objetos_Animados {
    
    /*capacidad total de almacenamiento de comida en la casa*/
    int capacidad_almacenamiento_comida;
    /*capacidad actual de comida dentro de la casa*/
    int capacidad_actual_comida;
    /*capacidad total de almacenamiento de oro en la casa*/
    int capacidad_almacenamiento_oro;
    /*capacidad actual de oro dentro de la casa*/
    int capacidad_actual_oro;
    
    /*Hoja de acciones posibles para el mercado*/
    Hoja_Sprites map= new Hoja_Sprites();
   public Casa(){
   
       super(370,2,2,10,0);
+       map.Añadir_accion("ConstruirC",0,0,50,70, 10, true, 10);
       animacion = new Animacion(map, "ConstruirC", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CASA).getImage());

       capacidad_almacenamiento_comida=200;
       capacidad_actual_comida=capacidad_almacenamiento_comida;
       capacidad_almacenamiento_oro=200;
       capacidad_actual_oro=capacidad_almacenamiento_oro;
   } 

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
