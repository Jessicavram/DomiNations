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
        Requerimientos aux_reRequerimientos = new Requerimientos("Cuartel","Construir",0,200,10,4,0);
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

