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
public class Boton extends Objetos_Animados{
    
    public String Nombre;
    public Boton(){}
    
    public Boton(String nombre){
        Nombre=nombre;
        Hoja_Sprites map= new Hoja_Sprites();
        //Definir las acciones en la hoja de sprites
        if(Nombre.compareTo("Tienda")==0){
            map.Añadir_accion(Nombre, 0, 0, 75, 50, 1, true, 1); 
        }
        if(Nombre.compareTo("O100")==0){
            map.Añadir_accion(Nombre, 0, 149, 44, 169, 1, true, 1);
        }
        if(Nombre.compareTo("Cuartel0")==0){
            map.Añadir_accion(Nombre, 44, 149, 88, 169, 1, true, 1);
        }
        if(Nombre.compareTo("Almacen0")==0){
            map.Añadir_accion(Nombre, 44, 149, 88, 169, 1, true, 1);
        }
        if(Nombre.compareTo("Centro0")==0){
            map.Añadir_accion(Nombre, 315, 149, 359, 169, 1, true, 1);
        }
        if(Nombre.compareTo("Torre0")==0){
            map.Añadir_accion(Nombre, 269, 130,313, 150, 1, true, 1);
        }
        if(Nombre.compareTo("Mercado0")==0){
            map.Añadir_accion(Nombre, 269, 130,313, 150, 1, true, 1);
        }
        if(Nombre.compareTo("Guarnicion0")==0){
            map.Añadir_accion(Nombre, 133, 130,177, 150, 1, true, 1);
        }
        if(Nombre.compareTo("NO-Cuartel0")==0){
            map.Añadir_accion(Nombre, 44, 279, 88, 299, 1, true, 1);
        }
        if(Nombre.compareTo("NO-Almacen0")==0){
            map.Añadir_accion(Nombre, 44, 279, 88, 299, 1, true, 1);
        }
        if(Nombre.compareTo("NO-Centro0")==0){
            map.Añadir_accion(Nombre, 315, 279, 359,299, 1, true, 1);
        }
        if(Nombre.compareTo("NO-Torre0")==0){
            map.Añadir_accion(Nombre, 269, 259,313, 279, 1, true, 1);
        }
        if(Nombre.compareTo("NO-Mercado0")==0){
            map.Añadir_accion(Nombre, 269, 259,313, 279, 1, true, 1);
        }
        if(Nombre.compareTo("NO-Guarnicion0")==0){
            map.Añadir_accion(Nombre, 133, 259,177, 279, 1, true, 1);
        }
              
        //Crear la hoja de sprites
        animacion = new Animacion(map, nombre, Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.BOTONES).getImage());
    }
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
