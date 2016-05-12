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
    //Aldea aldea_batalla;
    int total_soldados_generados;
    int oro_robado;
    int comida_robada;
    int tiempo_batalla;
    String nivel_destruccion;
    int soldado_1;
    int soldado_2;
    int nro_torres;
    int nro_guarniciones;
    int nro_almacenes;
    int nro_cuarteles;
    int nro_granjas;
    int nro_mercados;
    int comida_Aldea;
    int oro_Aldea;
    
    
    public Batalla(){
        
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
        
        //igualar valores
        soldado_1 = ald.soldados_tipo_1;
        soldado_2 = ald.soldados_tipo_2;
        nro_torres= ald.torres_creadas;
        nro_guarniciones= ald.guarnicion_construidas;
        nro_almacenes = ald.almacenes_construidos;
        nro_cuarteles = ald.cuarteles_construidas;
        nro_granjas = ald.granjas_construidas;
        nro_mercados = ald.mercados_construidas;
        comida_Aldea = ald.comida_Actual;
        oro_Aldea = ald.oro_Actual;
        //Evento nulo --> que no ocurra batalla por falta de unidades
        nivel_destruccion= "No ocurrio la batalla por falta de unidades";
        //Recibe la aldea_batalla a la que se va atacar
        total_soldados_aldea= soldado_1 + soldado_2;
        //Generar el tiempo de batalla
        tiempo_batalla= (int)ald.aleatorio(30,150);

        //Sumatoria de unidades de batalla
        unidades_de_defensa = nro_torres+ (nro_guarniciones*4*((int)(tiempo_batalla/20)))+total_soldados_aldea;
           
        //Sumatoria de edificios
        sumatoria_edificios = nro_torres+ nro_guarniciones+ nro_almacenes + nro_cuarteles + nro_granjas + nro_mercados;
        //Generar el ejercito atacante
        total_soldados_generados = (int)(ald.aleatorio(0.1,1)*unidades_de_defensa);
        
        //Condicional para generar destruccion
        if(unidades_de_defensa==0 && total_soldados_aldea==0){
        }else{
            if(total_soldados_aldea>0){
                if (total_soldados_generados/total_soldados_aldea >= 5){
                    nivel_destruccion = "Destrucción Total";
                    unidades_destruidas = sumatoria_edificios;
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(oro_Aldea/2);
                    comida_robada = (int)(comida_Aldea/2);

                }else if (4 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea <5) {
                    nivel_destruccion = "Destrucción Media";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.6,.9));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(oro_Aldea/3);
                    comida_robada = (int)(comida_Aldea/3);

                }else if (2 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea <4) {
                    nivel_destruccion = "Destrucción Baja";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.5,.8));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(oro_Aldea/4);
                    comida_robada = (int)(comida_Aldea/4);

                }else if (1.5 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea < 2) {
                    nivel_destruccion = "Victoria con grandes perdidas";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.2,.4));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(oro_Aldea/5);
                    comida_robada = (int)(comida_Aldea/5);

                }else if (0.9 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea< 1.5) {
                    nivel_destruccion = "Victoria forzada";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.05,.4));
                    soldados_aniquilados = (int)(total_soldados_aldea * ald.aleatorio(.8,1));
                    oro_robado = (int)(oro_Aldea/6);
                    comida_robada = (int)(comida_Aldea/6);

                }else if (0.4 <= total_soldados_generados/total_soldados_aldea && total_soldados_generados/total_soldados_aldea<0.9){
                    nivel_destruccion = "Victoria con pequeños daños";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.4,.7));
                    soldados_aniquilados = (int)(total_soldados_aldea * ald.aleatorio(.6,1));
                    oro_robado = (int)(oro_Aldea/7);
                    comida_robada = (int)(comida_Aldea/7);
                }else{
                    nivel_destruccion = "Victoria aplastante";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.0,.1));
                    soldados_aniquilados = (int)(total_soldados_aldea * ald.aleatorio(.2,.6));
                    oro_robado = 0;
                    comida_robada = 0;
                }
            }else{
                if (total_soldados_generados - unidades_de_defensa> 10){
                    nivel_destruccion = "Destrucción Total";
                    unidades_destruidas = sumatoria_edificios;
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(oro_Aldea/2);
                    comida_robada = (int)(comida_Aldea/2);

                }else if(total_soldados_generados - unidades_de_defensa>5 && total_soldados_generados - unidades_de_defensa<= 10  ){
                    nivel_destruccion = "Destrucción Media";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.6,.9));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(oro_Aldea/3);
                    comida_robada = (int)(comida_Aldea/3);

                }else if(total_soldados_generados - unidades_de_defensa <= 5 &&  total_soldados_generados - unidades_de_defensa> 1){
                    nivel_destruccion = "Victoria con grandes perdidas";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.2,.4));
                    soldados_aniquilados = total_soldados_aldea;
                    oro_robado = (int)(oro_Aldea/5);
                    comida_robada = (int)(comida_Aldea/5);
                
                }else if(total_soldados_generados - unidades_de_defensa <= 1&& total_soldados_generados - unidades_de_defensa>= -5){
                    nivel_destruccion = "Victoria con pequeños daños";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.4,.7));
                    soldados_aniquilados = (int)(total_soldados_aldea * ald.aleatorio(.6,1));
                    oro_robado = (int)(oro_Aldea/7);
                    comida_robada = (int)(comida_Aldea/7);

                }else{
                    nivel_destruccion = "Victoria aplastante";
                    unidades_destruidas = (int)(sumatoria_edificios * ald.aleatorio(.01,.1));
                    soldados_aniquilados = (int)(total_soldados_aldea * ald.aleatorio(.2,.6));
                    oro_robado = 0;
                    comida_robada = 0;
                }

            }
            descontar_soldados_aniquilados(soldados_aniquilados);
            descontar_edificios_destruidos(unidades_destruidas);
            System.out.println("SM: "+soldados_aniquilados+" ED: "+unidades_destruidas+" S1:"+soldado_1+" S2:"+soldado_2+" linea 141 Batalla");
            
        }
    }
    
    public void descontar_edificios_destruidos(int cantidad){
        Random ran = new Random();
        if (cantidad >= nro_torres) {
            cantidad-=nro_torres;
            nro_torres=0;
        }else{
            nro_torres=cantidad;
            cantidad = 0;
        }
            
        if(cantidad>0){
            for (int i = 0; i <= cantidad; i++) {
               switch(ran.nextInt(5)+1){
                    case 1:
                        //elimino almacen
                        if(nro_almacenes>0){
                            nro_almacenes--;
                            cantidad--;
                            System.out.println("almacen-linea166-Batalla");
                        }else{
                            i--;
                        }
                    break;
                    case 2:
                        //elimino cuartel
                        if(nro_cuarteles>0){
                            nro_cuarteles--;
                            cantidad--;
                            System.out.println("cuartl - Linea 176 - Batalla");
                        }else{
                            i--;
                        }
                    break;
                    case 3:
                        //elimina granja
                        if(nro_granjas>0){
                            nro_granjas--;
                            cantidad--;
                            System.out.println("granja -linea 186 - Batalla");
                        }else{
                            i--;
                        }
                    break;                        
                    case 4:
                        //elimina guarnicion
                        if(nro_guarniciones>0){
                            nro_guarniciones--;
                            cantidad--;
                            System.out.println("guarnicion - Linea 196 - Batalla");
                        }else{
                            i--;
                        }
                    break;
                    case 5:
                        //elimina Mercado
                        if(nro_mercados>0){
                            nro_guarniciones--;
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
                        if(soldado_1>0){
                            soldado_1--;
                            cantidad--;
                            System.out.println("soldado1-linea232-Batalla");
                        }else{
                            i--;
                        }
                    break;
                    case 2:
                        //elimino soldao2
                        if(soldado_2>0){
                            soldado_2--;
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
    public void mostrar(){
        System.out.println("++++++    Batalla   +++++++++");
        System.out.println("+ To: "+nro_torres);
        System.out.println("+ Al: "+nro_almacenes);
        System.out.println("+ Cu: "+nro_cuarteles);
        System.out.println("+ Gr: "+nro_granjas);
        System.out.println("+ Gu: "+nro_guarniciones);
        System.out.println("+ Me: "+nro_mercados);
    }
}   
