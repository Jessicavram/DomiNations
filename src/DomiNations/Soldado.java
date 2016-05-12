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
 * @author Carlos
 */
public class Soldado extends Objetos_Animados{
    /**Unidades de daño por cada accion*/
    int dano;
    /**tiempo entre accion*/
    int secuencia_ataque;
    /**Radio de accion del ataque unidades cuadradas*/
    int alcance;
    /**Hoja de acciones posibles para el cuartel*/
    Hoja_Sprites map = new Hoja_Sprites();  
    /**¨pote para guardar la matriz logica*/
    Matriz_Logica matriz;
    /**Poscion a alcanzar x*/
    int posx;
    /**Poscion a alcanzar y*/
    int posy;
    
    public Soldado(){              
        super(220,1, 1, 5, 0);              
        //Definir las acciones en la hoja de sprites
        map.Añadir_accion("Quieto", 0, 0, 24, 31, 1, true, 1);
        map.Añadir_accion("CaminarI", 0, 70, 24, 100, 6, true, 6);
        map.Añadir_accion("CaminarD", 0, 35, 24, 65, 6, true, 6);
        map.Añadir_accion("CaminarAb", 0, 103, 24, 133, 6, true, 6);
        map.Añadir_accion("CaminarAr", 0, 137, 24, 167, 6, true, 6);
        map.Añadir_accion("DispararAb", 0, 170, 24, 201, 2, true, 4);
        map.Añadir_accion("DispararAr", 50, 170, 74, 201, 2, true, 4);
        map.Añadir_accion("DispararI", 100, 170, 124, 201, 2, true, 4);
        map.Añadir_accion("DispararD", 150, 170, 174, 201, 2, true, 4);
        //Crear la hoja de sprites
        currentAction="Quieto";
        animacion = new Animacion(map, currentAction, Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.SOLDADO1).getImage());
        dano=25;
        alcance=3;
        secuencia_ataque=2;
        vx=posx=0;
        vy=posy=0;
        dirx=1;
        diry=1;
    }
    @Override
    public void Actualizar_Objeto_Grafico(double timePassed){
        if(colision==1){
            if ((posx==x)|| (currentAction.equals("CaminarD") && matriz.coordenaX_a_Columna((int) x, 115)+1<matriz.matriz_logica[0].length && matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)][matriz.coordenaX_a_Columna((int) x, 115)+1]=='1')) {
                vx=0;
                if(posy<y && matriz.coordenadaY_a_Fila((int)y, 70)-1 >0 &&matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)-1][matriz.coordenaX_a_Columna((int) x, 115)]=='0') {
                    currentAction="CaminarAr";
                    vy=2;
                    diry=-1;
                }
                else if(posx<x && matriz.coordenaX_a_Columna((int) x, 115)-1 >0 && matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)][matriz.coordenaX_a_Columna((int) x, 115)-1]=='0') {
                    currentAction="CaminarI";
                    vx=2;
                    dirx=-1;
                }else if (posy>y && matriz.coordenadaY_a_Fila((int)y, 70)+1<matriz.matriz_logica.length &&matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)+1][matriz.coordenaX_a_Columna((int) x, 115)]=='0') {
                    currentAction="CaminarAb";
                    vy=2;
                    diry=1;
                }
                animacion.Seleccionar_Accion(currentAction, true);

            }
            else if ((posx==x)|| (currentAction.equals("CaminarAr") && matriz.coordenadaY_a_Fila((int) x, 115)-1>0 && matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)-1][matriz.coordenaX_a_Columna((int) x, 115)]=='1')) {
                vx=0;
                if (posx>x && matriz.coordenaX_a_Columna((int) x, 115)+1<matriz.matriz_logica[0].length && matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)][matriz.coordenaX_a_Columna((int) x, 115)+1]=='0') {
                    currentAction="CaminarD";            
                    vx=2;
                    dirx=1;
                }
                else if(posx<x && matriz.coordenaX_a_Columna((int) x, 115)-1 >0 && matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)][matriz.coordenaX_a_Columna((int) x, 115)-1]=='0') {
                    currentAction="CaminarI";
                    vx=2;
                    dirx=-1;
                }else if (posy>y && matriz.coordenadaY_a_Fila((int)y, 70)+1<matriz.matriz_logica.length &&matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)+1][matriz.coordenaX_a_Columna((int) x, 115)]=='0') {
                    currentAction="CaminarAb";
                    vy=2;
                    diry=1;
                }
                animacion.Seleccionar_Accion(currentAction, true);

            }
        
        }
        
        Actualizar_PosicionX();
        Actualizar_PosicionY();
        super.Actualizar_Objeto_Grafico(timePassed);
    }
    public void mover(float x_llegar, float y_llegar, Matriz_Logica m){        
        char a,b,c,d;
        matriz = m;
        posx=(int)x_llegar;
        posy=(int)y_llegar;
        vx=vy=0;
        if (posx>x && matriz.coordenaX_a_Columna((int) x, 115)+1<matriz.matriz_logica[0].length && matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)][matriz.coordenaX_a_Columna((int) x, 115)+1]=='0') {
            currentAction="CaminarD";            
            vx=2;
            dirx=1;
        }else if(posy<y && matriz.coordenadaY_a_Fila((int)y, 70)-1 >0 &&matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)-1][matriz.coordenaX_a_Columna((int) x, 115)]=='0') {
            currentAction="CaminarAr";
            vy=2;
            diry=-1;
        }
        else if(posx<x && matriz.coordenaX_a_Columna((int) x, 115)-1 >0 && matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)][matriz.coordenaX_a_Columna((int) x, 115)-1]=='0') {
            currentAction="CaminarI";
            vx=2;
            dirx=-1;
        }else if (posy>y && matriz.coordenadaY_a_Fila((int)y, 70)+1<matriz.matriz_logica.length &&matriz.matriz_logica[matriz.coordenadaY_a_Fila((int)y, 70)+1][matriz.coordenaX_a_Columna((int) x, 115)]=='0') {
            currentAction="CaminarAb";
            vy=2;
            diry=1;
        }
        colision=1;
        //System.out.println(currentAction+" Vx:"+vx+" Vy:"+vy+" x:"+x+" y:"+y);
        animacion.Seleccionar_Accion(currentAction, true);
    }
    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }
}
