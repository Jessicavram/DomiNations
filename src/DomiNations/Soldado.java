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
public class Soldado extends Objetos_Animados{
    /**Unidades de daño por cada accion*/
    int dano;
    /**tiempo entre accion*/
    int secuencia_ataque;
    /**Radio de accion del ataque unidades cuadradas*/
    int alcance;
    /**Hoja de acciones posibles para el cuartel*/
    Hoja_Sprites map = new Hoja_Sprites();  
    
    public Soldado(){              
        super(220,1, 1, 5, 0);              
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("CaminarI", 25, 0, 49, 34, 2, true, 6);
        map.Añadir_accion("DispararI", 0, 0, 24, 34, 1, true, 4);
        map.Añadir_accion("CaminarD", 125, 0, 149, 34, 2, true, 6);
        map.Añadir_accion("DispararD", 100, 0, 124, 34, 1, true, 1);
        //Crear la hoja de sprites
        animacion = new Animacion(map, "CaminarD", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.SOLDADOS).getImage());
        dano=25;
        alcance=3;
        secuencia_ataque=2;
        vx=1;
        vy=1;
        dirx=1;
        diry=-1;
    }
    
    @Override
    public void Actualizar_Objeto_Grafico(double timePassed){
        Actualizar_PosicionX();
        Actualizar_PosicionY();
        super.Actualizar_Objeto_Grafico(timePassed);
    }
    
    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }
}
