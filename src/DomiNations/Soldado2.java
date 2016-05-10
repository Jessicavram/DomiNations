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
public class Soldado2 extends Objetos_Animados{
    /**Unidades de daño por cada accion*/
    int dano;
    /**tiempo entre accion*/
    int secuencia_ataque;
    /**Radio de accion del ataque unidades cuadradas*/
    int alcance;
    /**Hoja de acciones posibles para el cuartel*/
    Hoja_Sprites map = new Hoja_Sprites();  
    
    public Soldado2(){              
        super(220, 1, 1, 5, 0);              
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("Quieto", 0, 0, 24, 31, 1, true, 1);
        map.Añadir_accion("CaminarI", 0, 70, 24, 100, 6, true, 6);
        map.Añadir_accion("CaminarD", 0, 35, 24, 65, 6, true, 6);
        map.Añadir_accion("CaminarAb", 0, 103, 24, 133, 6, true, 6);
        map.Añadir_accion("CaminarAr", 0, 137, 24, 167, 6, true, 6);
        map.Añadir_accion("DispararAb", 0, 170, 24, 201, 2, true, 4);
        map.Añadir_accion("DispararAr", 50, 170, 74, 201, 2, true, 4);
        map.Añadir_accion("DispararI", 100, 170, 124, 201, 2, true, 4);
        map.Añadir_accion("DispararD", 150, 170, 174, 201, 2, true, 4);
        //Crear la hoja de sprites
        animacion = new Animacion(map, "CaminarD", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.SOLDADO2).getImage());
        dano=25;
        alcance=3;
        secuencia_ataque=2;
    }
    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }
}

