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
    
    int nivel=0;
    
    public Centro(){
        super(1000,5,6,10,4);
        Hoja_Sprites map = new Hoja_Sprites();
        //Definir las acciones en la hoja de sprites
        //Son 10 imagenes pero coloco 9 porque como no repite el coloca la imagen 9+1
        map.Añadir_accion("Construir1", 0,0,124,150, 9, false, 1);
        map.Añadir_accion("Listo1", 1500, 0, 1624,150, 1, false, 0);
        
        //Crear la hoja de sprites
        animacion = new Animacion(map, "Construir1", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.CENTRO).getImage());
    }
    
    public void avanzar(Aldea aldea)
    {
        switch(nivel)
        {
            case 0: edad_inicial(aldea); break;
            case 1: edad_de_piedra(aldea); break;
            case 2: edad_de_bronce(aldea); break;
            case 3: edad_de_hierro(aldea); break;
        }
        nivel++;
    }
    
    public void edad_inicial(Aldea aldea)
    {
        aldea.casas_permitidas=3;
        aldea.almacenes_permitidos=1;
        aldea.cuarteles_permitidas=1;
        aldea.granjas_permitidas=1;
        aldea.guarnicion_permitidas=1;
        aldea.mercados_permitidas=1;
        aldea.torres_permitidas=1;
    }
    
    public void edad_de_piedra(Aldea aldea)
    {
        aldea.casas_permitidas=4;
        aldea.almacenes_permitidos=2;
        aldea.cuarteles_permitidas=2;
        aldea.granjas_permitidas=2;
        aldea.guarnicion_permitidas=2;
        aldea.mercados_permitidas=2;
        aldea.torres_permitidas=2;       
    }
    
    public void edad_de_bronce(Aldea aldea)
    {
        aldea.casas_permitidas=5;
        aldea.almacenes_permitidos=3;
        aldea.cuarteles_permitidas=3;
        aldea.granjas_permitidas=3;
        aldea.guarnicion_permitidas=3;
        aldea.mercados_permitidas=3;
        aldea.torres_permitidas=3;        
    }
    
    public void edad_de_hierro(Aldea aldea)
    {
        aldea.casas_permitidas=6;
        aldea.almacenes_permitidos=4;
        aldea.cuarteles_permitidas=4;
        aldea.granjas_permitidas=4;
        aldea.guarnicion_permitidas=4;
        aldea.mercados_permitidas=4;
        aldea.torres_permitidas=4;        
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    
    }
    
}
