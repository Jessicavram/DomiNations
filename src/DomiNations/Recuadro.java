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
public class Recuadro extends Objetos_Graficos{
    
    public  Recuadro(int ancho, int alto, boolean disponible){
      
        Hoja_Sprites map = new Hoja_Sprites();
        //Definir las acciones en la hoja de sprites
        if(disponible)
            map.Añadir_accion("Cuadro", 0, 0, ancho*25, alto*25, 1, true, 1);
        else
            map.Añadir_accion("Cuadro", 0, 201, ancho*25, alto*25+201, 1, true, 1);
        animacion = new Animacion(map, "Cuadro", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.RECUADRO).getImage());
        
    }
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }
    
}
