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
public class Mercado extends Objetos_Animados {
    
    /*capacidad total de almacenamiento de oro en el mercado*/
    int capacidad_almacenamiento_oro;
    /*capacidad actual de oro dentro del mercado*/
    int capacidad_actual_oro;
    /*Hoja de acciones posibles para el mercado*/
    Hoja_Sprites map= new Hoja_Sprites();
   public Mercado(){
   
       super(800,0,500,10,4);
       map.Añadir_accion("ConstuirM",0,0,97,132, 10, true, 2);
       animacion = new Animacion(map, "ConstruirM", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.MERCADO).getImage());
       currentAction="ConstuirM";
       capacidad_almacenamiento_oro=2500;
       capacidad_actual_oro=capacidad_almacenamiento_oro;
   } 
   public void actualizar_nivel_2(){
       vida_max+=600;
       costo_comida+=1500;
       capacidad_almacenamiento_oro+=7500;
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
