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
public class Boton extends Objetos_Graficos{
    public Boton(){}
    
    public Boton(String nombre){
        Hoja_Sprites map = new Hoja_Sprites();
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("Tienda", 0, 0, 75, 50, 1, true, 1);
        map.Añadir_accion("O100", 0, 149, 44, 169, 1, true, 1);
        map.Añadir_accion("Cuartel0", 44, 149, 88, 169, 1, true, 1);
        map.Añadir_accion("Almacen0", 44, 149, 88, 169, 1, true, 1);
        map.Añadir_accion("Centro0", 315, 149, 359, 169, 1, true, 1);
        map.Añadir_accion("Torre0", 269, 130,313, 150, 1, true, 1);
        map.Añadir_accion("Mercado0", 269, 130,313, 150, 1, true, 1);
        map.Añadir_accion("Guarnicion0", 133, 130,177, 150, 1, true, 1);
        
        //Crear la hoja de sprites
        animacion = new Animacion(map, nombre, Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.BOTONES).getImage());
    }
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }
    
}
