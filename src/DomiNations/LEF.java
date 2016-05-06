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
    String descripcion, tiempo, posicion;
    
    public LEF()
    {
        
    }
    
    public String getLinea()
    {   
        return descripcion+"  |  "+tiempo;
    }
    
    public String getPos()
    {
        return posicion;
    }
    
}
