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
 * @author Carlos Rangel 
 **/
public class Aldeano extends Objetos_Animados{
    /**Variable para bloquea movimientos*/
    public  boolean muteInput;

    double cont;
    /**estado actual del aldeano (caminar, construir)*/
    public int estado=0;
    public int posicion=-1;
    public int cordxi=0;
    public int cordyi=0;
    /*si el estado es 1 el aldeano se encuentra construyendo*/
    
    /**Constructor donde se añaden las acciones del objeto y se da una por defecto*/
    public Aldeano(){        
        Hoja_Sprites map = new Hoja_Sprites();       
        //Definir las acciones en la hoja de sprites

        //Inactivo         
        map.Añadir_accion("Iquieto",   0,  0, 25,  25, 1, true, 1);
        map.Añadir_accion("Dquieto",   0, 25, 25,  50, 1, true, 1);
        map.Añadir_accion("Arquieto",  0, 50, 25,  75, 1, true, 1);
        map.Añadir_accion("Abquieto",  0, 75, 25, 100, 1, true, 1);

        //Caminar         
        map.Añadir_accion("Icaminar", 0,   0,   24,  25, 4, true, 8);
        map.Añadir_accion("Dcaminar", 0,  25,   24,  50, 4, true, 8);
        map.Añadir_accion("Arcaminar",25,  50,   50,  75, 2, true, 6);
        map.Añadir_accion("Abcaminar",25,  75,   49, 100, 2, true, 6);

        //Caminar         
        map.Añadir_accion("Iconstruir", 100,   0,    124,  25, 3, true, 6);
        map.Añadir_accion("Dconstruir", 100,  25,    124,  49, 3, true, 6);
        map.Añadir_accion("Arconstruir",75,  50,   100,  74, 2, true, 4);
        map.Añadir_accion("Abconstruir",75,  75,    99, 100, 4, true, 8);
        
        //Crear la hoja de sprites
        animacion = new Animacion(map, "Abquieto", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.ALDEANO).getImage());
        dirx = 1;
        muteInput = false;
        cont = 0;
    }
    @Override
    public void Actualizar_Objeto_Grafico(double timePassed){
        
       /* vx=1;
        //vy=1;
        dirx=1;
        //diry=-1;
        Actualizar_PosicionX();
        Actualizar_PosicionY();
        */
        super.Actualizar_Objeto_Grafico(timePassed);
     
    }
    
    
    public void Actualizar_Aldeano(double timePassed,Matriz_Logica m, int x_inicial,int y_inicial){
        
       /* vx=1;
        dirx=1;
        Actualizar_PosicionX();
        Actualizar_PosicionY();
        Verificar_camino(m,x_inicial,y_inicial);
        */
    }
    public void Aparecer_Aldeano( Matriz_Logica m, int x_inicial, int y_inicial,int fila,int columna){
            
            x=m.Columna_a_CoordenadaX(columna, x_inicial);
            y=m.Fila_a_CoordenadaY(fila, y_inicial);
            //x=200;
            //y=200;
    }
   public static boolean resuelve(Matriz_Logica m,int x,int y,int xdest,int ydest){
       m.matriz_logica[x][y]='.';
       /*
       if(x==0||y==1||x==m.matriz_logica[0].length-1||y==m.matriz_logica.length-1){
           System.out.println("Encontrada la solución: ");
           m.imprimir();
           return true;
       }*/
       if(x==0||y==0){
           System.out.println("Encontrada la solución: ");
           m.imprimir();
           return true;
       }
       //Arriba
       if(m.matriz_logica[x-1][y]=='0'){
           boolean tmp=resuelve(m,x-1,y,xdest,ydest);
           if (tmp==true) return true;
       }
       //Abajo
       if(m.matriz_logica[x+1][y]=='0'){
           boolean tmp=resuelve(m,x+1,y,xdest,ydest);
           if(tmp==true) return true;
       }
       //Izquierda
       if(m.matriz_logica[x][y-1]=='0'){
           boolean tmp=resuelve(m,x,y-1,xdest,ydest);
           if(tmp==true) return true;
       }
       //Derecha
       if(m.matriz_logica[x][y+1]=='0'){
           boolean tmp=resuelve(m,x,y+1,xdest,ydest);
           if(tmp==true) return true;
       }
       //El camino no tiene solucion
       m.matriz_logica[x][y]='0';
       return false;
   }
    
    /*
    @Override
   
    public void Actualizar_Objeto_Grafico(double timePassed){
        if(!muteInput){
            if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.DERECHA)  && !Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.IZQUIERDA) ) {
                    if (!currentAction.equals("Dcaminar")) {
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVx(2);
                        dirx = 1;
                    }
            }

            if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.IZQUIERDA) && !Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.DERECHA)  ) {
                    if (!currentAction.equals("Icaminar")) {
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVx(2);
                        dirx = -1;
                    }

            }

            if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.ARRIBA) ) {
                    if (!currentAction.equals("Arcaminar")) {                        
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVy(2);
                        diry = -1;
                        cont = Motor_Juego.cont;                        
                    }
            }

            if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.ABAJO)) {

                    if (!currentAction.substring(3, currentAction.length()).equals("Abcaminar")) {
                        animacion.Seleccionar_Accion(currentAction, true);
                        setVy(2);
                        diry = 1;                        
                    }

            }
        }    
        if (!Informacion_de_Teclado.getInstance().isAnyKeyActived()) {
                currentAction = (diry == 1 ? "D" : "I") + "quieto";                
                animacion.Seleccionar_Accion(currentAction, false);
                setVx(0);
                setVy(0);

        }else{
             if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.DERECHA)  ) {                  
                  currentAction = "Dcaminar";
                  animacion.Seleccionar_Accion(currentAction, true);
                  setVx(2);
                  dirx = 1;                  
            }else if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.IZQUIERDA)) {                
              currentAction = "Icaminar";                       
              animacion.Seleccionar_Accion(currentAction, true);
              setVx(2);
              dirx = -1;      
            }else if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.ARRIBA)  ) {                  
              currentAction = "Arcaminar";
              animacion.Seleccionar_Accion(currentAction, true);
              setVy(0);
              dirx = 1;                  
            }else if (Informacion_de_Teclado.getInstance().isActive(Informacion_de_Teclado.ABAJO)) {                
              currentAction = "Abcaminar";                       
              animacion.Seleccionar_Accion(currentAction, true);
              setVx(2);
              dirx = -1;
        }
        super.Actualizar_Objeto_Grafico(timePassed);
    } 
  }
*/
    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
    }

}
