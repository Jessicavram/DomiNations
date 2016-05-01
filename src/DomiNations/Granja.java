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
public class Granja extends Objetos_Animados{
    /**Capacidad de almacenamiento del recurso comida*/
    int capacidad_comida;
    /**Cantidad de comida producida*/
    int comida;
    /**Tiempo entre produccion de comida*/
    int tiempo_por_produccion;
    
    public Granja(){
        super(800,500,0,5,2);
        Hoja_Sprites map = new Hoja_Sprites();
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("Construir1", 0, 0, 74, 59, 10, true, 2);
        map.Añadir_accion("Listo1", 1710, 0, 1900, 210, 1, false, 0);
        map.Añadir_accion("Construir2", 0, 210, 189, 420, 10, false, 2);
        map.Añadir_accion("Listo2", 1710, 210, 1900, 420, 1, false, 0);
        
        //Crear la hoja de sprites
        animacion = new Animacion(map, "Construir1", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.GRANJA).getImage());
        
        capacidad_comida=1000;
        comida=100;
        tiempo_por_produccion=6;
    }
    

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    
    }
    
}
