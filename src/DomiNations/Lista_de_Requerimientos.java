/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Lista_de_Requerimientos {
    /**Arreglo para objetos de fondo*/
    ArrayList<Requerimientos> PreCondiciones;
    
    public Lista_de_Requerimientos(){
        PreCondiciones = new ArrayList<Requerimientos>();
              
        //Almacen
        Requerimientos aux_reRequerimientos = new Requerimientos("Almacen","Construir",0,200,10,3,0);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Almacen","Mejorar2",0,500,20,3,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Almacen","Mejorar3",0,1000,30,3,2);
        PreCondiciones.add(aux_reRequerimientos);
                
        //Casa
        aux_reRequerimientos = new Requerimientos("Casa","Construir",200,0,10,1,0);
        PreCondiciones.add(aux_reRequerimientos);
        
        //Centro
        aux_reRequerimientos = new Requerimientos("Centro","Construir",0,100,10,4,0);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Centro","Mejorar2",0,1000,20,4,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Centro","Mejorar3",0,2000,30,4,2);
        PreCondiciones.add(aux_reRequerimientos);
        
        //Cuartel
        aux_reRequerimientos = new Requerimientos("Cuartel","Construir",0,200,10,4,0);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Cuartel","Mejorar2",0,500,20,4,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Cuartel","Mejorar3",0,1000,30,4,2);
        PreCondiciones.add(aux_reRequerimientos);
        
        //Granja
        aux_reRequerimientos = new Requerimientos("Granja","Construir",0,200,10,2,0);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Granja","Mejorar2",0,600,20,2,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Granja","Mejorar3",0,800,30,2,2);
        PreCondiciones.add(aux_reRequerimientos);
        
        //Guarnición
        aux_reRequerimientos = new Requerimientos("Guarnicion","Construir",100,0,10,3,0);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Guarnicion","Mejorar2",500,0,20,3,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Guarnicion","Mejorar3",1000,0,30,3,2);
        PreCondiciones.add(aux_reRequerimientos);
        
        //Mercado
        aux_reRequerimientos = new Requerimientos("Mercado","Construir",200,0,10,3,0);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Mercado","Mejorar2",500,0,20,3,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Mercado","Mejorar3",1000,0,30,3,2);
        PreCondiciones.add(aux_reRequerimientos);
        
        //Torre
        aux_reRequerimientos = new Requerimientos("Torre","Construir",200,0,10,3,0);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Torre","Mejorar2",500,0,20,3,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Torre","Mejorar3",1000,0,30,3,2);
        PreCondiciones.add(aux_reRequerimientos);
        
        //Soldado1
        aux_reRequerimientos = new Requerimientos("Soldado1","Entrenar0",25,0,5,0,0);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Soldado1","Entrenar2",50,0,10,0,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Soldado1","Entrenar3",80,0,15,0,2);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Soldado1","Mejorar2",500,0,30,0,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Soldado1","Mejorar3",1000,0,30,0,2);
        PreCondiciones.add(aux_reRequerimientos);
        
        //Soldado1
        aux_reRequerimientos = new Requerimientos("Soldado2","Entrenar0",80,0,5,0,0);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Soldado2","Entrenar2",120,0,10,0,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Soldado2","Entrenar3",150,0,15,0,2);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Soldado2","Mejorar2",0,500,30,0,1);
        PreCondiciones.add(aux_reRequerimientos);
        aux_reRequerimientos = new Requerimientos("Soldado2","Mejorar3",0,1000,30,0,2);
        PreCondiciones.add(aux_reRequerimientos);
        
    }
    public Requerimientos buscar_requerimiento(String nom_item, int nivel){
        Requerimientos obj = null;
        for(int i=0;i<PreCondiciones.size();i++){
            obj = PreCondiciones.get(i);
            if(obj.Nombre_Requerimiento.equalsIgnoreCase(nom_item) && obj.nivel == nivel)
                return obj;
        }   
        return obj;
    }
    
    public class Requerimientos{
        /**Nombre del item al que aplica el requerimiento*/
        String Nombre_item;
        /**Nombre del requerimiento ej= construir, mejorar2, mejorar3,...*/
        String Nombre_Requerimiento;
        /**Costo en comida del item*/
        int costo_comida;
        /**Costo en oro del item*/
        int costo_oro;
        /*Tiempo que tarda en construir el item**/
        int tiempo;
        /**Numero de aldanos requeridos*/
        int nro_aldeanos_requeridos;
        /**Nivel requerido*/
        int nivel;
        
        public Requerimientos(){
            Nombre_item="";
            Nombre_Requerimiento="";
            costo_comida=costo_oro=tiempo=nro_aldeanos_requeridos=nivel;            
        }

        public Requerimientos(String Nombre_item, String Nombre_Requerimiento, int costo_comida, int costo_oro, int tiempo, int nro_aldeanos_requeridos, int nivel) {
            this.Nombre_item = Nombre_item;
            this.Nombre_Requerimiento = Nombre_Requerimiento;
            this.costo_comida = costo_comida;
            this.costo_oro = costo_oro;
            this.tiempo = tiempo;
            this.nro_aldeanos_requeridos = nro_aldeanos_requeridos;
            this.nivel = nivel;
        }
        public void mostrar_condiciones(){
            System.out.println("Item: "+Nombre_item+" Tipo: "+Nombre_Requerimiento+" Comida: "+costo_comida+" Oro: "+costo_oro+" Nivel Req:"+nivel);
        }
    }
}

