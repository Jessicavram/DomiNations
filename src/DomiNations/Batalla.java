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
    /**Guardara los valores de la aldea_batalla para saber el numero de edificios y soldados que posee*/
    Aldea aldea_batalla;
    int total_soldados_generados;
    int oro_robado;
    int comida_robada;
    int tiempo_batalla;
    String nivel_destruccion;
    
    
    
    public Batalla(){
        aldea_batalla = new Aldea();
    }
    /**Metodo para simular una batalla, crea un ejercito enemigo con caracteristicas similares a las de la aldea
     * @param ald Aldea a ser atacada, contiene la especificacion de numeros de edificios y numero de unidades de ataque
     */
   
    /**
     * Metodo para simular una batalla, crea un ejercito enemigo con caracteristicas similares a las de la aldea_batalla
     * @param ald Aldea a ser atacada, contiene la especificacion de numeros de edificios y numero de unidades de ataque
     */
    public void generar(Aldea ald){        
        //Guarda la sumatoria de los edificios de defensa y los soldados que posee la aldea_batalla a ataca
        int unidades_de_defensa=0;
        int total_soldados_aldea=0;
        int unidades_destruidas=0;
        int soldados_aniquilados=0;
        int sumatoria_edificios=0;
        //Evento nulo --> que no ocurra batalla por falta de unidades
        nivel_destruccion= "No ocurrio la batalla por falta de unidades";
        //Recibe la aldea_batalla a la que se va atacar
        aldea_batalla= ald;
        total_soldados_aldea= aldea_batalla.soldados_tipo_1 + aldea_batalla.soldados_tipo_2;
        //Generar el tiempo de batalla
        tiempo_batalla= (int)aldea_batalla.aleatorio(30,150);

        //Sumatoria de unidades de batalla
        unidades_de_defensa = aldea_batalla.torres_creadas + (aldea_batalla.guarnicion_construidas*4*((int)(tiempo_batalla/20)))+total_soldados_aldea;
           
        //Sumatoria de edificios
        sumatoria_edificios = aldea_batalla.torres_creadas + aldea_batalla.guarnicion_construidas + aldea_batalla.almacenes_construidos + aldea_batalla.cuarteles_construidas + aldea_batalla.granjas_construidas + aldea_batalla.mercados_construidas;
        //Generar el ejercito atacante
        total_soldados_generados = (int)(aldea_batalla.aleatorio(0.1,1)*unidades_de_defensa);
        
        //Condicional para generar destruccion
        if(unidades_de_defensa==0 && total_soldados_aldea==0){
        }else{
            if(total_soldados_aldea>0){
                if (total_soldados_generados/total_soldados_aldea >= 5){
                    nivel_destruccion = "Destrucción Total";
                    unidades_destruidas = sumatoria_edificios;
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(aldea_batalla.oro_Actual/2);
                    comida_robada = (int)(aldea_batalla.comida_Actual/2);

                }else if (4 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea <5) {
                    nivel_destruccion = "Destrucción Media";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.6,.9));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(aldea_batalla.oro_Actual/3);
                    comida_robada = (int)(aldea_batalla.comida_Actual/3);

                }else if (2 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea <4) {
                    nivel_destruccion = "Destrucción Baja";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.5,.8));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(aldea_batalla.oro_Actual/4);
                    comida_robada = (int)(aldea_batalla.comida_Actual/4);

                }else if (1.5 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea < 2) {
                    nivel_destruccion = "Victoria con grandes perdidas";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.2,.4));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(aldea_batalla.oro_Actual/5);
                    comida_robada = (int)(aldea_batalla.comida_Actual/5);

                }else if (0.9 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea< 1.5) {
                    nivel_destruccion = "Victoria forzada";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.05,.4));
                    soldados_aniquilados = (int)(total_soldados_aldea * aldea_batalla.aleatorio(.8,1));
                    oro_robado = (int)(aldea_batalla.oro_Actual/6);
                    comida_robada = (int)(aldea_batalla.comida_Actual/6);

                }else if (0.4 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea<0.9){
                    nivel_destruccion = "Victoria con pequeños daños";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.4,.7));
                    soldados_aniquilados = (int)(total_soldados_aldea * aldea_batalla.aleatorio(.6,1));
                    oro_robado = (int)(aldea_batalla.oro_Actual/7);
                    comida_robada = (int)(aldea_batalla.comida_Actual/7);
                }else{
                    nivel_destruccion = "Victoria aplastante";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.0,.1));
                    soldados_aniquilados = (int)(total_soldados_aldea * aldea_batalla.aleatorio(.2,.6));
                    oro_robado = 0;
                    comida_robada = 0;
                }
            }else{
                if (total_soldados_generados - unidades_de_defensa> 10){
                    nivel_destruccion = "Destrucción Total";
                    unidades_destruidas = sumatoria_edificios;
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(aldea_batalla.oro_Actual/2);
                    comida_robada = (int)(aldea_batalla.comida_Actual/2);

                }else if(total_soldados_generados - unidades_de_defensa>5 && total_soldados_generados - unidades_de_defensa<= 10  ){
                    nivel_destruccion = "Destrucción Media";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.6,.9));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(aldea_batalla.oro_Actual/3);
                    comida_robada = (int)(aldea_batalla.comida_Actual/3);

                }else if(total_soldados_generados - unidades_de_defensa <= 5 &&  total_soldados_generados - unidades_de_defensa> 1){
                    nivel_destruccion = "Victoria con grandes perdidas";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.2,.4));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(aldea_batalla.oro_Actual/5);
                    comida_robada = (int)(aldea_batalla.comida_Actual/5);
                
                }else if(total_soldados_generados - unidades_de_defensa <= 1&& total_soldados_generados - unidades_de_defensa>= -5){
                    nivel_destruccion = "Victoria con pequeños daños";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.4,.7));
                    soldados_aniquilados = (int)(total_soldados_aldea * aldea_batalla.aleatorio(.6,1));
                    oro_robado = (int)(aldea_batalla.oro_Actual/7);
                    comida_robada = (int)(aldea_batalla.comida_Actual/7);

                }else{
                    nivel_destruccion = "Victoria aplastante";
                    unidades_destruidas = (int)(sumatoria_edificios * aldea_batalla.aleatorio(.01,.1));
                    soldados_aniquilados = (int)(total_soldados_aldea * aldea_batalla.aleatorio(.2,.6));
                    oro_robado = 0;
                    comida_robada = 0;
                }

            }
            descontar_soldados_aniquilados(soldados_aniquilados);
            descontar_edificios_destruidos(unidades_destruidas);
            System.out.println("SM: "+soldados_aniquilados+" ED: "+unidades_destruidas+" S1:"+aldea_batalla.soldados_tipo_1+" S2:"+aldea_batalla.soldados_tipo_2+" linea 141 Batalla");
            
        }
    }
    
    public void descontar_edificios_destruidos(int cantidad){
        Random ran = new Random();
        if (cantidad >= aldea_batalla.torres_creadas) {
            cantidad-=aldea_batalla.torres_creadas;
            aldea_batalla.torres_creadas=0;
        }else{
            aldea_batalla.torres_creadas=cantidad;
            cantidad = 0;
        }
            
        if(cantidad>0){
            for (int i = 0; i <= cantidad; i++) {
               switch(ran.nextInt(5)+1){
                    case 1:
                        //elimino almacen
                        if(aldea_batalla.almacenes_construidos>0){
                            aldea_batalla.almacenes_construidos--;
                            cantidad--;
                            System.out.println("almacen-linea166-Batalla");
                        }else{
                            i--;
                        }
                    break;
                    case 2:
                        //elimino cuartel
                        if(aldea_batalla.cuarteles_construidas>0){
                            aldea_batalla.cuarteles_construidas--;
                            cantidad--;
                            System.out.println("cuartl - Linea 176 - Batalla");
                        }else{
                            i--;
                        }
                    break;
                    case 3:
                        //elimina granja
                        if(aldea_batalla.granjas_construidas>0){
                            aldea_batalla.granjas_construidas--;
                            cantidad--;
                            System.out.println("granja -linea 186 - Batalla");
                        }else{
                            i--;
                        }
                    break;                        
                    case 4:
                        //elimina guarnicion
                        if(aldea_batalla.guarnicion_construidas>0){
                            aldea_batalla.guarnicion_construidas--;
                            cantidad--;
                            System.out.println("guarnicion - Linea 196 - Batalla");
                        }else{
                            i--;
                        }
                    break;
                    case 5:
                        //elimina Mercado
                        if(aldea_batalla.mercados_construidas>0){
                            aldea_batalla.guarnicion_construidas--;
                            cantidad--;
                            System.out.println("Mercado - Linea 206 - Batalla");
                        }else
                            i--;
                    break;                               
               } 
            }
        }
    }
    public void descontar_soldados_aniquilados(int cantidad){
        Random ran = new Random();
        if(cantidad>0){
            for (int i = 0; i <= cantidad; i++) {
               switch(ran.nextInt(2)+1){
                    case 1:
                        //elimino soldado1
                        if(aldea_batalla.soldados_tipo_1>0){
                            aldea_batalla.soldados_tipo_1--;
                            cantidad--;
                            System.out.println("soldado1-linea232-Batalla");
                        }else{
                            i--;
                        }
                    break;
                    case 2:
                        //elimino soldao2
                        if(aldea_batalla.soldados_tipo_2>0){
                            aldea_batalla.soldados_tipo_2--;
                            cantidad--;
                            System.out.println("soldado2 - Linea 241 - Batalla");
                        }else{
                            i--;
                        }
                    break;                           
               } 
            }
        }
    }
}   
