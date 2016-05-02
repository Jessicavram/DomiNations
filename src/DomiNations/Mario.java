/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import sprites.Hoja_Sprites;
import sprites.Animacion;
import sun.security.jca.GetInstance;
/**Clase realizar las operaciones logicas de Mario
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Mario extends Objetos_Animados{
    /**Variable para bloquea movimientos*/
    public  boolean muteInput;
    /**Variable para bloquea fuego*/
    public  boolean mutefuego=false;
    /**Estado en que mario es invencible*/
    boolean invencible=false;
    double cont;
    /**Numero de vidas de mario*/
    public int vidas=3;
    /**Estado de mario vivo o muerto*/
    public int murio=0;
    /**estado actual de mario(mp,mg,mf)*/
    public int estado=0;
    /**Constructor donde se añaden las acciones del objeto y se da una por defecto*/
    public Mario(){        
        Hoja_Sprites map = new Hoja_Sprites();       
        //Definir las acciones en la hoja de sprites
        //MARIO PEQUEÑO
        //Inactivo 1
        map.Añadir_accion("MpDquieto", 128, 2, 143, 21, 3, true, 1);
        map.Añadir_accion("MpIquieto", 16, 2, 31, 21, 3, true, 1);
        //Caminar
        map.Añadir_accion("MpDcaminar", 112, 2, 127, 21, 2, true, 10);
        map.Añadir_accion("MpIcaminar", 0, 2, 15, 21, 2, true, 10);
        //Saltar
        map.Añadir_accion("MpDsalto", 272, 0, 286, 22, 0, false, 0);
        map.Añadir_accion("MpIsalto", 224, 0, 238, 22, 0, false, 0);
        //Agacharse
        map.Añadir_accion("MpDagachar", 192, 8, 207, 21, 1, false, 0);
        map.Añadir_accion("MpIagachar", 80, 8, 95, 21, 1, false, 0);
        //MARIO GRANDE
       //Caminar
        map.Añadir_accion("MgDcaminar", 0, 76, 15, 107, 4, true, 10);
        map.Añadir_accion("MgIcaminar", 0, 45, 15, 76, 4, true, 10);
        //Agacharse
        map.Añadir_accion("MgDagachar", 64, 76,79, 107, 1, false, 0);
        map.Añadir_accion("MgIagachar", 64, 45, 79, 76, 1, false, 0);
        //Saltar
        map.Añadir_accion("MgDsalto", 80, 76,95,107, 0, false, 0);
        map.Añadir_accion("MgIsalto", 80, 45, 95, 76, 0, false, 0);
        //Inactivo 1
        map.Añadir_accion("MgDquieto", 96, 76, 111, 107, 11, true, 1);
        map.Añadir_accion("MgIquieto", 96, 45, 111, 76, 11, true, 1);
        //Correr
        map.Añadir_accion("RRun", 0, 145, 38, 181, 4, true, 15);
        map.Añadir_accion("LRun", 0, 910, 38, 945, 4, true, 15);
        //MARIO FLOR
       //Caminar
        map.Añadir_accion("MfDcaminar", 0, 138, 15, 169, 4, true, 10);
        map.Añadir_accion("MfIcaminar", 0, 107, 15, 138, 4, true, 10);
        //Agacharse
        map.Añadir_accion("MfDagachar", 64, 138,79, 169, 1, false, 0);
        map.Añadir_accion("MfIagachar", 64, 107, 79, 138, 1, false, 0);
        //Saltar
        map.Añadir_accion("MfDsalto", 80, 138,95,169, 0, false, 0);
        map.Añadir_accion("MfIsalto", 80, 107, 95, 138, 0, false, 0);
        //Inactivo 1
        map.Añadir_accion("MfDquieto", 96, 138, 111, 169, 11, true, 1);
        map.Añadir_accion("MfIquieto", 96, 107, 111, 138, 11, true, 1);
        //Correr
        map.Añadir_accion("RRun", 0, 145, 38, 181, 4, true, 15);
        map.Añadir_accion("LRun", 0, 910, 38, 945, 4, true, 15);
        
        //Crear la hoja de sprites
        animacion = new Animacion(map, "MpDquieto", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.MARIO).getImage());
        dirx = 1;
        muteInput = false;
        cont = 0;
    }
    
    @Override
    /**Actualiza el objeto segun la operacion logica realizada
     * @paran timePassed tiempo transcurrido
     */
    public void Actualizar_Objeto_Grafico(double timePassed){
        //if(invencible && Motor_Juego.cont%100==0)invencible=false;
        if(!muteInput && murio==0 && getVy()<=1){
            if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.DERECHA)  && !Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.IZQUIERDA) ) {
                    if (!currentAction.substring(2, currentAction.length()).equals("Dcaminar")) {
                        if(estado==0)
                        currentAction = "MpDcaminar";
                        else if(estado==1)
                        currentAction = "MgDcaminar";
                        else if(estado==2)
                        currentAction = "MfDcaminar";
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVx(2);
                        dirx = 1;
                    }
            }

            if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.IZQUIERDA) && !Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.DERECHA)  ) {
                    if (!currentAction.substring(2, currentAction.length()).equals("Icaminar")) {
                        if(estado==0)
                        currentAction = "MpIcaminar";
                        if(estado==1)
                        currentAction="MgIcaminar";
                        if(estado==2)
                        currentAction = "MfIcaminar";
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVx(2);
                        dirx = -1;
                    }

            }

            if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.ARRIBA) ) {
                    if (!currentAction.substring(3, currentAction.length()).equals("salto")) {
                        if(estado==0)
                        currentAction = "Mp"+(dirx == 1 ? "D" : "I") + "salto";
                        if(estado==1)
                          currentAction="Mg"+(dirx==1?"D":"I")+"salto";
                        if(estado==2)
                        currentAction = "Mf"+(dirx==1?"D":"I")+"salto";
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVy(-8);
                        muteInput = true;
                        cont = Motor_Juego.cont;                        
                        Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.SALTO, false, false);
                    }
            }

            if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.ABAJO)) {

                    if (!currentAction.substring(3, currentAction.length()).equals("agachar")) {
                        if(estado==0)
                        currentAction = "Mp"+(dirx == 1 ? "D" : "I") + "agachar";
                        if(estado==1)
                            currentAction="Mg"+(dirx==1?"D": "I")+"agachar";
                        if(estado==2)
                         currentAction="Mf"+(dirx==1?"D": "I")+"agachar";
                        
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVx(0);
                        
                    }

            }
             if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.FUEGO)) {

                    if (currentAction.substring(0,2).equals("Mf") && !mutefuego) {
                        Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.DISPARO, false, false);
                        colision=1; mutefuego=true;                       
                    }
             }else if(!Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.FUEGO)){
                    mutefuego=false;
                    }

        if (!Informacion_de_Teclado.getInstance().isAnyKeyActived()) {
                if(estado==0)
                currentAction = "Mp"+(dirx == 1 ? "D" : "I") + "quieto";
                if(estado==1)
                    currentAction="Mg"+(dirx==1? "D" : "I")+"quieto";
                if(estado==2)
                    currentAction="Mf"+(dirx==1? "D" : "I")+"quieto";
                animacion.Seleccionar_Accion(currentAction, false);
                setVx(0);

        }}else{
             if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.DERECHA)  ) {
                  // if (!currentAction.equals("RJump")) {
                        if(estado==0)
                        currentAction = "MpDsalto";
                        if(estado==1)
                        currentAction = "MgDsalto";
                        if(estado==2)
                        currentAction = "MfDsalto";
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVx(2);
                        dirx = 1;
                  //  }
            }

            if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.IZQUIERDA)) {
                //    if (!currentAction.equals("LWalk")) {
                        if(estado==0)
                        currentAction = "MpIsalto";
                        if(estado==1)
                        currentAction = "MgIsalto";
                        if(estado==2)
                        currentAction = "MfIsalto";
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVx(2);
                        dirx = -1;
                //    }

            }
        }
        super.Actualizar_Objeto_Grafico(timePassed);
    }

    @Override
    /**Colisiones animadas para realizar las acciones logicas de colisiones de mario con otros objeto animados
     * @paran objeto_colision recibe el objeto con el cual esta colisionando mario
     * @paran lado indica el lado por el cual ocurrio la colision
     */
    public void OnCollide(Objetos_Graficos objeto_colision, int side){
        if(currentAction.substring(3, currentAction.length()).equals("salto") && side == Motor_Fisico.COL_TOP){
            muteInput = false;
            if(estado==0)
            currentAction = "Mp"+(dirx == 1 ? "D" : "I") + "quieto";
            if(estado==1)
            currentAction ="Mg"+ (dirx == 1 ? "D" : "I") + "quieto";
            if(estado==2)
            currentAction ="Mf"+ (dirx == 1 ? "D" : "I") + "quieto";
            vx = 0;
        }
        else if(objeto_colision instanceof Items){
            Items aux= (Items) objeto_colision;
           if(aux.colision==1 || aux.colision==2){
            //objeto_colision.animacion.Seleccionar_Accion("bloqueado", true);
            objeto_colision.colision=0;
            }
                
        }
        
    }

    @Override
    /**Colisiones con estaticos para realizar las acciones logicas de colisiones de mario con objets estaticos
     * @paran objeto_colision recibe el objeto con el cual esta colisionando bowser
     * @paran lado indica el lado por el cual ocurrio la colision
     */
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
        if(objeto_colision instanceof Goomba &&( lado==Motor_Fisico.COL_BOTTOM || lado==Motor_Fisico.COL_LEFT || lado==Motor_Fisico.COL_RIGHT)){
            if(estado==0){
                Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MUERTE, false, false);
                vidas--;
                murio=1;
            }else{
              estado--;
              if(estado==1)
                  Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.NO_HONGO, false, false);
              currentAction = "Mp"+currentAction.substring(2 , currentAction.length());
              if(estado==2)
                  Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.NO_HONGO, false, false);
              currentAction = "Mf"+currentAction.substring(2 , currentAction.length());
              animacion.Seleccionar_Accion(currentAction, true);
              
            }
         }else if( objeto_colision instanceof Goomba && lado==Motor_Fisico.COL_TOP ){
             setVy(-5);                 
         } 
         else if( (objeto_colision instanceof Tortuga || objeto_colision instanceof Tomate) && lado==Motor_Fisico.COL_TOP ){
                setVy(-5);
         }
         else if( objeto_colision instanceof Tortuga && (lado==Motor_Fisico.COL_LEFT || lado==Motor_Fisico.COL_RIGHT || lado==Motor_Fisico.COL_BOTTOM)  ){
             Tortuga tortuga = (Tortuga)objeto_colision;          
             System.out.println(estado);                    
             if( !tortuga.muerto || tortuga.golpe && tortuga.getVx()>=2){
                        if(estado==0){
                        Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MUERTE, false, false);
                        vidas--;
                        murio=1;
                        }else{
                          if(estado==1){
                          Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.NO_HONGO, false, false);
                          currentAction = "Mp"+currentAction.substring(2 , currentAction.length());}
                          if(estado==2){
                          Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.NO_HONGO, false, false);
                          currentAction = "Mg"+currentAction.substring(2 , currentAction.length());
                          animacion.Seleccionar_Accion(currentAction, true);}
                          estado--;
                        }
                    }

         }else if(objeto_colision instanceof Tomate && (lado==Motor_Fisico.COL_LEFT || lado==Motor_Fisico.COL_RIGHT || lado==Motor_Fisico.COL_BOTTOM)){
                       System.out.println(estado);
                        if(estado==0){
                        Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MUERTE, false, false);
                        vidas--;
                        murio=1;
                        }else{                          
                          if(estado==1){
                          Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.NO_HONGO, false, false);
                          currentAction = "Mp"+currentAction.substring(2 , currentAction.length());}
                          if(estado==2){
                          currentAction = "Mf"+currentAction.substring(2 , currentAction.length());
                          animacion.Seleccionar_Accion(currentAction, true);}
                          estado--;
                        }
                    
                     }
         else if( objeto_colision.currentAction.equals("Hongo")){
                    estado=1;
                    currentAction = "Mg"+currentAction.substring(2 , currentAction.length());
                    Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.SI_HONGO, false, false);
                    } 
         else if( objeto_colision.currentAction.equals("flor")){
                    estado=2;
                    currentAction = "Mf"+currentAction.substring(2 , currentAction.length());
                    Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.FLOR, false, false);
                    } 
         else if( objeto_colision instanceof Planta){
                    Planta aux=(Planta) objeto_colision;
                    if(aux.activa){
                    if(estado==0){
                        Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MUERTE, false, false);
                        vidas--;
                        murio=1;
                        }else{
                          if(estado==1){
                          Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.NO_HONGO, false, false);
                          currentAction = "Mp"+currentAction.substring(2 , currentAction.length());}
                          if(estado==2){
                          currentAction = "Mf"+currentAction.substring(2 , currentAction.length());
                          animacion.Seleccionar_Accion(currentAction, true);}
                          estado--;
                        }
                    }
         }
                 
    }
    @Override
    /**Modifica las posiciones y la direccion de mario
//     * @param dirx direccion del objeto
     * @param x posicion en x
     * @param y posicion en y
     */
    public void Modificar(int dirx,int x,int y){
        currentAction="MpDquieto";
        animacion.Seleccionar_Accion(currentAction, false);
        this.dirx = dirx;
        this.x=x;
        this.y=y;
        this.vx=0;
        this.vy=0;
    }

}
