/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

/**
 *
 * @author Jessica
 */
public class LEF 
{
    String descripcion;
    int tiempo, posicion;
    int evento_ocurrio=0;
    
    public LEF()
    {
        
    }

    public LEF(String descripcion,int tiempo, int posicion) {
        this.descripcion = descripcion;
        this.tiempo = tiempo;
        this.posicion = posicion;
    }
     
    public String getLinea()
    {   
        return "\n "+tiempo+"      | "+descripcion;
    }
    
    public int getPos()
    {
        return posicion;
    }
    
}
