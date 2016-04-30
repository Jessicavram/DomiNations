package DomiNations;
import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import sprites.*;
/**Objeto animado
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Tortuga extends Objetos_Animados {
    boolean muerto;
    boolean golpe;
    /***/
    public Tortuga(){
        Hoja_Sprites animaciones = new Hoja_Sprites();       
        animaciones.Añadir_accion("DCaminar", 32, 3, 47, 30, 2, true, 10);
        animaciones.Añadir_accion("ICaminar", 0, 3, 15, 30, 2, true, 10);
        animaciones.Añadir_accion("Muerto", 64, 10, 79, 30, 1, false, 0);
        animaciones.Añadir_accion("Muerto_1", 80, 10, 95, 30, 3, true, 15);
        animacion = new Animacion(animaciones, "ICaminar", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.ENEMIGOS).getImage());
        dirx = -1;
        setVx(1);
    }
    /***/
    public Tortuga(boolean azul){
        Hoja_Sprites animaciones = new Hoja_Sprites();       
        animaciones.Añadir_accion("DCaminar", 32, 32, 47, 59, 2, true, 10);
        animaciones.Añadir_accion("ICaminar", 0, 32, 15, 59, 2, true, 10);
        animaciones.Añadir_accion("Muerto", 64, 44, 79, 59, 1, false, 0);
        animaciones.Añadir_accion("Muerto_1", 80, 44, 95, 59, 3, true, 15);
        animacion = new Animacion(animaciones, "ICaminar", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.ENEMIGOS).getImage());
        dirx = -1;
        setVx(1);
    }
    
    /***/
    @Override
    public void OnCollide(Objetos_Graficos Objeto_Colision, int Lado) {
        if( Lado == Motor_Fisico.COL_LEFT || Lado == Motor_Fisico.COL_RIGHT){
            changeDirx();
            if(!muerto){
            currentAction = (dirx==1?"D":"I")+"Caminar";
            animacion.Seleccionar_Accion(currentAction, true);
            }else{
                currentAction = "Muerto_1";
                animacion.Seleccionar_Accion(currentAction, true);
            }
        }
    }
    
    /***/
    @Override
    public void Actualizar_Objeto_Grafico(double timePassed){
        Actualizar_PosicionX();
        super.Actualizar_Objeto_Grafico(timePassed);
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
       changeDirx();
       if( objeto_colision instanceof Tortuga && ((Tortuga)objeto_colision).getVx()>=4 )
        Borrar();
       if( objeto_colision instanceof Goomba &&( Motor_Fisico.COL_LEFT== lado  || Motor_Fisico.COL_RIGHT == lado ) && muerto && golpe ){
           changeDirx();
       }
        if(!muerto){
       
       currentAction = (dirx==1?"D":"I")+"Caminar";
       animacion.Seleccionar_Accion(currentAction, true);
       }
       if( objeto_colision instanceof Mario && muerto){
            golpe=true;
            currentAction = "Muerto_1";
            animacion.Seleccionar_Accion(currentAction, true);
            setVx(6);
            if( (objeto_colision.getX()-getX())>=0 )
                dirx = -1;
            else
                dirx = 1;
        }
        if( objeto_colision instanceof Mario  && lado==Motor_Fisico.COL_BOTTOM && !golpe){
//            Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.ESP_ENEMIGO, false, false);
            currentAction = "Muerto";
            animacion.Seleccionar_Accion(currentAction, true);
            setVx(0);
            muerto = true;
        } 
     }          

    @Override
    public void Modificar(int dir,int x,int y){
        this.vx=1;
    }

}
