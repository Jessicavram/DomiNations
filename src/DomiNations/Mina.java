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
public class Mina extends Objetos_Animados{

    int estado;
    int oro_maximo;
    int oro_actual;
    
    Hoja_Sprites map= new Hoja_Sprites();
    public Mina(){
         oro_maximo=100; //puede ser mas o menos de acuerdo a nuestro juego
        map.Añadir_accion("MinaVacia",0,0,59,100,2, true, 1); //mina vacia 
        map.Añadir_accion("MinaConOro",59,0,118,100,1,true, 1); //Minallena
        
        
        animacion = new Animacion(map, "MinaVacia", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.MINA).getImage());
    }
    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
