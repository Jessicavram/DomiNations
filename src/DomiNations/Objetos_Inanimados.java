package DomiNations;
import Cargador.Cargar_Imagenes;
import java.awt.Image;
import sprites.*;
/**Clase para cargar todas las imagenes necesarias para el juego desde el paquete cargador.Imagenes sin necesidad de cargar desde el disco duro del equipo
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Objetos_Inanimados extends Objetos_Graficos{
    /***/
    public Objetos_Inanimados(Image imagen, Rectangulo rect) {
        super();
        Hoja_Sprites map = new Hoja_Sprites();
        map.Añadir_accion("Inactivo", rect.izquierda, rect.superior, rect.derecha, rect.inferior, 1, false, 1);
        animacion = new Animacion(map,"Inactivo", imagen);
    }

    /***/
    @Override
    public void OnCollide(Objetos_Graficos objeto_colision, int lado) {
        if( objeto_colision == new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS_2).getImage(), new Rectangulo(194, 0, 275, 41)) )
            System.out.println("entro");
    }



}
