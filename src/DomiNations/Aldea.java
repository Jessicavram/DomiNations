/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

import java.util.logging.Logger;

public class Aldea {
    /**Edad actual de la aldea*/
    String edad;
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
    /**Bandera de si esta construido el centro o aun no se ha construido Centro*/
    boolean centro;
    /**Numero de torres que se le permite tener*/
    int torres_permitidas;
    /**Numero de torres construidas en la aldea*/
    int torres_creadas;
    /**Numero de Almacenes que se le permiten tener*/
    int almacenes_permitidos;
    /**Numero de Almacenes construidas en la aldea*/
    int almacenes_construidos;
    /**Numero de Casas que se le permite construir*/
    int casas_permitidas;
    /**Numero de casa construidas*/
    int casas_construidas;
    /**Numero de cuarteles que se le permite construir*/
    int cuarteles_permitidas;
    /**Numero de cuarteles construidas*/
    int cuarteles_construidas;
    /**Numero de mercados que se le permite construir*/
    int mercados_permitidas;
    /**Numero de mercados construidas*/
    int mercados_construidas;
    /**Numero de guarnicion que se le permite construir*/
    int guarnicion_permitidas;
    /**Numero de guarnicion construidas*/
    int guarnicion_construidas;
    /**Numero de granjas que se le permite construir*/
    int granjas_permitidas;
    /**Numero de granjas construidas*/
    int granjas_construidas;
    /**Numero de soldados1*/
    int soldados_tipo_1;
    /**Numero de cuarteles construidas*/
    int soldados_tipo_2;

    public Aldea() {
        edad="";
        total_oro =0;
        oro_Actual =0;
        total_comida =0;
        comida_Actual =0;
        nro_aldeanos =7;
        nro_aldeanos_disponibles =5;
        centro =false;
        torres_permitidas =0;
        torres_creadas =0;
        almacenes_permitidos =0;
        almacenes_construidos =0;
        casas_permitidas =0;
        casas_construidas =0;
        cuarteles_permitidas =0;
        cuarteles_construidas =0;
        mercados_permitidas =0;
        mercados_construidas =0;
        guarnicion_permitidas =0;
        guarnicion_construidas =0;
        granjas_permitidas =0;
        granjas_construidas =0;
        soldados_tipo_1 =0;
        soldados_tipo_2 =0;
    }
    
}
