package DomiNations;
import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import sprites.*;

public class Goomba extends Objetos_Animados {
    public Goomba(){
        Hoja_Sprites map = new Hoja_Sprites();
      
        //Definir las acciones en la hoja de sprites 173
        map.Añadir_accion("Caminar", 0, 153, 18, 173, 3, true, 7);
        //Crear la hoja de sprites
        animacion = new Animacion(map, "Caminar", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.ENEMIGOS).getImage());
        dirx = -1;
        setVx(2);
    }
    
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int Lado) {
        if( Lado == Motor_Fisico.COL_LEFT || Lado == Motor_Fisico.COL_RIGHT){
            changeDirx();
            currentAction = "Caminar";
            animacion.Seleccionar_Accion(currentAction, true);
        }
    }
    
    /***/
    @Override
    public void Actualizar_Objeto_Grafico(double timePassed){
        Actualizar_PosicionX();
        setVx(1);
        super.Actualizar_Objeto_Grafico(timePassed);
    }

    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
        //if( lado == Motor_Fisico.COL_LEFT || lado == Motor_Fisico.COL_RIGHT)
        changeDirx();
        if( objeto_colision instanceof Mario && lado==Motor_Fisico.COL_BOTTOM){
            Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.ESP_ENEMIGO, false, false);
            this.Borrar();
  
        }
        
        if( objeto_colision instanceof Tortuga ){
            Tortuga nuevo = (Tortuga) objeto_colision;
            if( nuevo.muerto && nuevo.golpe){
            Borrar();
            changeDirx();
            }
        }
        if( objeto_colision instanceof Planta ){
            Planta auxiliar = (Planta) objeto_colision;
                 if(!auxiliar.activa){
                 changeDirx();
                 }
        }
    }

}
