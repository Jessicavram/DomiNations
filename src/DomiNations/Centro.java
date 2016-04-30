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
 * Contiene las utilidades para la creacion y manejo del cento de la ciudad
 * @author Carlos
 */
public class Centro extends Objetos_Animados{
    /**Capacidad de almacenar oro dentro del centro de la ciudad*/
    int capcidad_oro;
    /**Capacidad de almacenar comida dentro del centro de la ciudad*/
    int capacidad_comida;
    
    public Centro(){
        super(1000,0,0,5,4);
        Hoja_Sprites map = new Hoja_Sprites();
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("Construir1", 0, 0, 189, 210, 10, false, 2);
        map.Añadir_accion("Listo1", 1710, 0, 1900, 210, 1, false, 0);
        map.Añadir_accion("Construir2", 0, 210, 189, 420, 10, false, 2);
        map.Añadir_accion("Listo2", 1710, 210, 1900, 420, 1, false, 0);
        
        //Crear la hoja de sprites
        animacion = new Animacion(map, "Construir1", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CENTRO).getImage());
        
    }
    

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    
    }
    
}