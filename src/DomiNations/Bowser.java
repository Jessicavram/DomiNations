
package DomiNations;

import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import sprites.Animacion;
import sprites.Hoja_Sprites;

/**Clase realizar las operaciones logicas de bowser
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Bowser extends Objetos_Animados{
    /**Estado de bowser */
    boolean muerto=false;
    /**Constructor donde se añaden las acciones del objeto y se da una por defecto*/
    public Bowser(){
        Hoja_Sprites map = new Hoja_Sprites();       
        //Definir las acciones en la hoja de sprites
        
        map.Añadir_accion("brincando", 0, 0, 32, 30, 1, false,0);
        map.Añadir_accion("muerto", 96, 0, 133, 30, 1, false, 0);
        animacion = new Animacion(map, "brincando", Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.BOWSER).getImage());
        currentAction="brincando";
    }
    @Override
    /**SObreescribir el metodo colisiones animadas para realizar las acciones logicas de colisiones de bowser
     * @paran objeto_colision recibe el objeto con el cual esta colisionando bowser
     * @paran lado indica el lado por el cual ocurrio la colision
     */
    public void OnCollide_animada(Objetos_Animados objeto_colision, int lado) {
        if(lado==Motor_Fisico.COL_BOTTOM && !muerto){
            Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.ESP_ENEMIGO, false, false);
            setY( getY()+4 );
            currentAction="muerto";
            animacion.Seleccionar_Accion(currentAction, true);
            Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.NO_BOWSER, false, false);
            muerto=true;
        }
    }

    @Override
    /**SObreescribir el metodo colisiones con estaticos para realizar las acciones logicas de colisiones de bowser
     * @paran objeto_colision recibe el objeto con el cual esta colisionando bowser
     * @paran lado indica el lado por el cual ocurrio la colision
     */
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
        if((Motor_Juego.cont%250==0) && !muerto){
        setVy(-12);
        }
    }
    
    
}
