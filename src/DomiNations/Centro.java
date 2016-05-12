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
    
    int max_torres;
    
    int max_casas;
    
    int max_almacenes;
    
    int max_mercados;
    
    int max_cuarteles;
    
    int max_guarniciones;
    
    int max_granjas;
    
    public Centro(){
        super(1000,5,6,10,4);
        Hoja_Sprites map = new Hoja_Sprites();
        //Definir las acciones en la hoja de sprites
        //Son 10 imagenes pero coloco 9 porque como no repite el coloca la imagen 9+1
        map.Añadir_accion("Construir1", 0,0,124,150, 9, false, 1);
        map.Añadir_accion("Listo1", 1500, 0, 1624,150, 1, false, 0);
        
        //Crear la hoja de sprites
        animacion = new Animacion(map, "Construir1", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CENTRO).getImage());
        
        max_casas=4;
        max_almacenes=2;
        max_cuarteles=2;
        max_guarniciones=2;
        max_mercados=2;
        max_torres=2;
        max_granjas=2;
    }
    

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    
    }
    
}
