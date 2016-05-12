/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

import java.util.Random;

/**
 *
 * @author Carlos
 */
public class Batalla {
    /**Guardara los valores de la aldea para saber el numero de edificios y soldados que posee*/
    Aldea aldea;
    int total_soldados_generados;
    int oro_robado;
    int comida_robada;
    int tiempo_batalla;
    String nivel_destruccion;
    
    public Batalla(){}
    /**Metodo para simular una batalla, crea un ejercito enemigo con caracteristicas similares a las de la aldea
     * @param ald Aldea a ser atacada, contiene la especificacion de numeros de edificios y numero de unidades de ataque
     */
   
    public Batalla(Aldea ald){        
        //Guarda la sumatoria de los edificios de defensa y los soldados que posee la aldea a ataca
        int unidades_de_defensa=0;
        int total_soldados_aldea=0;
        int unidades_destruidas=0;
        int soldados_aniquilados=0;
        int sumatoria_edificios=0;

        //Recibe la aldea a la que se va atacar
        aldea= ald;
        total_soldados_aldea= aldea.soldados_tipo_1 + aldea.soldados_tipo_2;
        //Generar el tiempo de batalla
        tiempo_batalla= (int)aleatorio(30,150);

        //Sumatoria de unidades de batalla
        unidades_de_defensa = aldea.torres_creadas + (aldea.guarnicion_construidas*4*((int)(tiempo_batalla/20)))+total_soldados_aldea;
           
        //Sumatoria de edificios
        sumatoria_edificios = aldea.torres_creadas + aldea.guarnicion_construidas + aldea.almacenes_construidos + aldea.cuarteles_construidas + aldea.granjas_construidas + aldea.mercados_construidas;
        //Generar el ejercito atacante
        total_soldados_generados = (int)(aleatorio(0,1)*unidades_de_defensa);

        //Condicional para generar destruccion
        if(total_soldados_generados/total_soldados_aldea >= 5){
            nivel_destruccion = "Destrucción Total";
            unidades_destruidas = sumatoria_edificios;
            soldados_aniquilados = total_soldados_aldea;
            oro_robado = (int)(aldea.oro_Actual/2);
            comida_robada = (int)(aldea.comida_Actual/2);

        }else if (4 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea <5) {
            nivel_destruccion = "Destrucción Media";
            unidades_destruidas = (int)(sumatoria_edificios * aleatorio(.6,.9));
            soldados_aniquilados = total_soldados_aldea;
            oro_robado = (int)(aldea.oro_Actual/3);
            comida_robada = (int)(aldea.comida_Actual/3);

        }else if (2 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea <4) {
            nivel_destruccion = "Destrucción Baja";
            unidades_destruidas = (int)(sumatoria_edificios * aleatorio(.5,.8));
            soldados_aniquilados = total_soldados_aldea;
            oro_robado = (int)(aldea.oro_Actual/4);
            comida_robada = (int)(aldea.comida_Actual/4);

        }else if (1.5 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea < 2) {
            nivel_destruccion = "Victoria con grandes perdidas";
            unidades_destruidas = (int)(sumatoria_edificios * aleatorio(.2,.4));
            soldados_aniquilados = total_soldados_aldea;
            oro_robado = (int)(aldea.oro_Actual/5);
            comida_robada = (int)(aldea.comida_Actual/5);

        }else if (0.9 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea< 1.5) {
            nivel_destruccion = "Victoria forzada";
            unidades_destruidas = (int)(sumatoria_edificios * aleatorio(.05,.4));
            soldados_aniquilados = (int)(total_soldados_aldea * aleatorio(.8,1));
            oro_robado = (int)(aldea.oro_Actual/6);
            comida_robada = (int)(aldea.comida_Actual/6);

        }else if (0.4 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea<0.9){
            nivel_destruccion = "Victoria con pequeños daños";
            unidades_destruidas = (int)(sumatoria_edificios * aleatorio(.4,.7));
            soldados_aniquilados = (int)(total_soldados_aldea * aleatorio(.6,1));
            oro_robado = (int)(aldea.oro_Actual/7);
            comida_robada = (int)(aldea.comida_Actual/7);
        }else{
            nivel_destruccion = "Victoria aplastante";
            unidades_destruidas = (int)(sumatoria_edificios * aleatorio(.0,.1));
            soldados_aniquilados = (int)(total_soldados_aldea * aleatorio(.2,.6));
            oro_robado = 0;
            comida_robada = 0;
        }
    }
    public float aleatorio(double desde, double hasta){
        Random ran = new Random();
        float n = 100000;
        int d = (int)(desde * n);
        int h = (int)(hasta * n);
        float r = ((ran.nextInt(h-d+1)+d)/n);
        return r; 
    }
    public void descontar_edificios_destruidos(int cantidad){
        Random ran = new Random();
        if (cantidad >= aldea.torres_creadas) {
            cantidad-=aldea.torres_creadas;
            aldea.torres_creadas=0;
        }else{
            aldea.torres_creadas=cantidad;
            cantidad = 0;
        }
            
        if(cantidad>0){
            for (int i = 0; i < cantidad; i++) {
               switch(ran.nextInt(5)+1){
                    case 1:
                        //elimino almacen
                        if(aldea.almacenes_construidos>0){
                            aldea.almacenes_construidos--;
                            cantidad--;
                        }else{
                            i--;
                        }
                    break;
                    case 2:
                        //elimino cuartel
                        if(aldea.cuarteles_construidas>0){
                            aldea.cuarteles_construidas--;
                            cantidad--;
                        }else{
                            i--;
                        }
                    break;
                    case 3:
                        //elimina granja
                        if(aldea.granjas_construidas>0){
                            aldea.granjas_construidas--;
                            cantidad--;
                        }else{
                            i--;
                        }
                    break;                        
                    case 4:
                        //elimina guarnicion
                        if(aldea.guarnicion_construidas>0){
                            aldea.guarnicion_construidas--;
                            cantidad--;
                        }else{
                            i--;
                        }
                    break;
                    case 5:
                        //elimina Mercado
                        if(aldea.mercados_construidas>0){
                            aldea.guarnicion_construidas--;
                            cantidad--;
                        }else
                            i--;
                    break;                               
               } 
            }
        }
    }
    public void descontar_soldados_aniquilados(int cantidad){
        aldea.soldados_tipo_2=(int)(cantidad*.7);
        aldea.soldados_tipo_2=cantidad-aldea.soldados_tipo_1;
    }
}   
