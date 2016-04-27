
package Mario;

import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import sprites.Animacion;
import sprites.Hoja_Sprites;

/**Clase realizar las operaciones logicas de la bola de fuego
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class bola_fuego extends Objetos_Animados{
    /**Inicializa las acciones y asigna una accion por defecto*/
    public bola_fuego(){}
    public bola_fuego(int dirx){
        Hoja_Sprites map = new Hoja_Sprites();
      
        //Definir las acciones en la hoja de sprites 173
        map.Añadir_accion("bola", 0, 0, 10, 11, 4, true, 7);
        //Crear la hoja de sprites
        animacion = new Animacion(map, "bola", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.FUEGO).getImage());
        this.dirx =dirx;
        setVx(2);
    }
    @Override
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
        objeto_colision.Borrar();   
        Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MATO_BOLA, false, false);
        Borrar();
    }

    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
        if(lado==Motor_Fisico.COL_TOP)
        setVy(-3);
        setVx(2);
        if(lado==Motor_Fisico.COL_LEFT || lado==Motor_Fisico.COL_RIGHT )
            Borrar();
    }
    
    @Override
    public void Actualizar_Objeto_Grafico(double timePassed){
        Actualizar_PosicionX();
        Actualizar_PosicionY();
        super.Actualizar_Objeto_Grafico(timePassed);
    }
    
}
