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

    boolean estado;
    boolean botonre_activo; //El boton de recoleccion esta activo
    int oro_maximo;
    int oro_actual;
    Boton recolectaM;
    boolean bloquear;
    
    Hoja_Sprites map= new Hoja_Sprites();
    public Mina(){
        
        super(370,2,2,10,1);
        botonre_activo=false;
        oro_maximo=100;
        bloquear=false;
        map.Añadir_accion("MinaVacia",0,0,50,50,3,false,1); //mina vacia 
        map.Añadir_accion("MinaConOro",59,0,118,100,1,true, 1); //Minallena
       
        animacion = new Animacion(map, "MinaVacia", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.MINA).getImage());
    }
    
    public Boton HabilitarBotonRecoleccion(Objetos_Graficos objg){
        recolectaM=new Boton(true,"MINA");
        recolectaM.Seleccionar_Localizacion((int)(objg.getX()+20),(int)(objg.getY()+20));
        botonre_activo=true;
        
        return recolectaM;
    }
    
    
    
    
    
    public void ActualizarOro(){
    
    }
    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Boton getRecolectaM() {
        return recolectaM;
    }

    public void setRecolectaM(Boton recolectaM) {
        this.recolectaM = recolectaM;
    }
    
    
}
