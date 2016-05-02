/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

import java.util.logging.Logger;

public class Aldea {
    /**Total de oro en toda la aldena**/
    int total_oro;
    /**Oro actual en toda la aldena**/
    int oro_Actual;
    /**Total Comida en toda la aldena**/
    int total_comida;
    /**Comida actual en toda la aldena*/
    int comida_Actual;
    /**Numero total de aldeanos*/
    int nro_aldeanos;
    /**Numero de aldeanos disponibles*/
    int nro_aldeanos_disponibles;

    public Aldea() {
        total_oro=total_comida=oro_Actual=comida_Actual=nro_aldeanos_disponibles=nro_aldeanos=0;
    }
    
}
