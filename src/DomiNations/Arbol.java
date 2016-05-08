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
 * @author leydy
 */
public class Arbol extends Objetos_Animados{

    int fruta_max; /*hace referencia a la maxima cantidad de comida que puede proporcinar un arbol */
    int fruta_actual; /*si el adeano este recogiendo la fruta se muestra lo que va quedando*/
    int estado_arbol; /* un arbol puede o no tenr fruta, creo yo que cada tanto haremos que le aparezca fruta
                  0: sin fruta
                  1: con fruta
                  2: cortado eliminamos de la escena no se si eso lo vamos a permitir */
    Hoja_Sprites map= new Hoja_Sprites();

    public Arbol() {
        fruta_max=50; //puede ser mas o menos de acuerdo a nuestro juego
        map.Añadir_accion("recursoArbol",0,0,65,100,3, true, 1); //Arbol inicial sin fruta 
        map.Añadir_accion("recursoArbolConFruta",65,0,129,100,1,true, 1); //empieza a salir la fruta
        map.Añadir_accion("recursoArbolCon+Fruta",129,0,194,100,1, true, 1); //lista la fruta para ser recolectada
        animacion = new Animacion(map, "recursoArbol", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.ARBOL).getImage());
        
    }
    
  
    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
