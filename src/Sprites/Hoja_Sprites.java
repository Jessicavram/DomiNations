package sprites;
import java.util.HashMap;
/**Clase que nos ayuda a manejor los sprite_accion, a travez de la creacion de una tabla hash, aunque tambien se podria manejar con un vector de sprite accion 
 * la tabla hash nos ayuda a manejar mejor la informacion ya que a travez de ella podemos relacionar el nombre de la accion con la accion como tal
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0**/
public class Hoja_Sprites {
    /**Tabla hash, objeto que nos permitira relacionar los aprite con nombres posteriormente asignados, no se guardaron todos 
     * los sprite_accion en un vector ya que es mas comodo recorda el nombre de una accion que una posicion detro de un vector*/
    protected HashMap<String, Sprite> tabla_hash = new HashMap<String, Sprite>();

    /**Metodo que nos permite agregar acciones a la tabla hash, recibe el string a relacionar y todos los parametros necesarios para crear un objeto de tipo Sprite_Accion
     * @param relacion String que va a ser la llave en la tabla hash, es decir, el nombre de la accion
     * @param x1 Parametro para crear el sprite accion
     * @param y1 Parametro para crear el sprite accion
     * @param x2 Parametro para crear el sprite accion
     * @param y2 Parametro para crear el sprite accion
     * @param cant Parametro para crear el sprite accion
     * @param rep Parametro para crear el sprite accion
     * @param FPS Parametro para crear el sprite accion */
    public void Añadir_accion(String nombre, int x1, int y1, int x2, int y2, int cant_imagenes, boolean repite, int FPS) {
        tabla_hash.put(nombre, new Sprite(x1, y1, x2, y2, cant_imagenes, repite, FPS));
    }
    
    /**Metodo que nos da nos devuelve una accion dada la llave en la tabla hash
     * @param llave String que es la relacion dentro de la tabla hash*/
    public Sprite Obtener_accion(String llave) {
        return tabla_hash.get(llave);
    }
}