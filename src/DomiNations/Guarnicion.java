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
public class Guarnicion extends Objetos_Animados{
    /**Cantidad de asteros por oleada*/
    int asteros_por_oleada;
    /**Cantidad de arqueros por oleada */
    int arqueros_por_oleada;
    /**Numero actual de guerreros dentro del cuartel*/
    int Tiempo_entre_oleada;
    /**Hoja de acciones posibles para el cuartel*/
    Hoja_Sprites map = new Hoja_Sprites();  
    
    public Guarnicion(){              
        super(1000, 3, 4, 10, 4);              
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("ConstruirG", 0, 0, 50, 65, 10, true, 10);
        map.Añadir_accion("ListoG", 458, 0, 510, 65, 10, true, 10);
                //Crear la hoja de sprites
        animacion = new Animacion(map, "ConstruirG", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.GUARNICION).getImage());
        //currentAction="Construir_Torre";
        asteros_por_oleada=3;
        arqueros_por_oleada=0;
        Tiempo_entre_oleada=20;
    }
    
    public void actualizar_nivel_2(){
        vida_max +=400;
   
        asteros_por_oleada-=1;
        arqueros_por_oleada=4;
        nivel++;
    }
    
   
    
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
       
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    
    }
}
    
