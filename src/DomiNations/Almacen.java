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
public class Almacen extends Objetos_Animados {
    
    /*capacidad total de almacenamiento de oro en el mercado*/
    int capacidad_almacenamiento_comida;
    /*capacidad actual de oro dentro del mercado*/
    int capacidad_actual_comida;
    /*Hoja de acciones posibles para el mercado*/
    Hoja_Sprites map= new Hoja_Sprites();
   public Almacen(){
   
       super(800,3,3,10,4);
+       map.Añadir_accion("ConstruirA",0,20,74,129, 10, true, 5);
       animacion = new Animacion(map, "ConstruirA", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.ALMACEN).getImage());

       capacidad_almacenamiento_comida=2500;
       capacidad_actual_comida=capacidad_almacenamiento_comida;
   } 
   public void actualizar_nivel_2(){
       vida_max+=600;
    
       capacidad_almacenamiento_comida+=7500;
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
