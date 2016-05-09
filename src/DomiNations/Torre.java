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
public class Torre extends Objetos_Animados{
    /**Dañor por segundo de los ataques de la torre **/
    int Danio_por_segundo;
    /**alcance de los ataques de la torre*/
    int alcance;
    /**Hoja de acciones posibles para la torre */
    Hoja_Sprites map = new Hoja_Sprites();  
    
    public Torre(){              
        super(1000, 1, 1, 10, 3);              
        //Definir las acciones en la hoja de sprites
        //Son 10 imagenes pero coloco 9 porque como no repite el coloca la imagen 9+1
        map.Añadir_accion("ConstruirT", 0, 0, 24, 50, 9, false, 1);
                //Crear la hoja de sprites
        animacion = new Animacion(map, "ConstruirT", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.TORRE).getImage());
        //currentAction="Construir_Torre";
        alcance=7;
        Danio_por_segundo=2; //revisar si hay que actualizar estas variables por nivel
    }
    
    public void actualizar_nivel_2(){
        vida_max +=50;
  
        nivel++;
    }
    
    public void actualizar_nivel_3(){
        vida_max +=100;
        nivel++;
    }
    
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
       
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    
    }
}
    