package Cargador;
import java.net.URL;
import javax.swing.ImageIcon;
/**Clase para cargar todas las imagenes necesarias para el juego desde el paquete cargador.Imagenes sin necesidad de cargar desde el disco duro del equipo
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Cargar_Imagenes {
    /**Instancia de la clase para solo poder crear un objeto de la clase o la llamdada referencia al unico objeto de la clase*/
    private static Cargar_Imagenes instancia=null;
    /**Vector de imagenes en donde cargaremos todas las imagenes del juego*/
    protected ImageIcon vec_imagenes[];
    /**Constante que nos indica el total de imagenes a cargar, que seria la suma de cada una de las constantes*/

    protected static final int TOTAL_IMAGENES=38;

    /**Constante para conocer el nombre de la imagen que contiene la animacion de los enemigos*/
    public static final int ENEMIGOS=0;
    /**Constante para conocer el nombre de la imagen los objetos inanimados*/  
    public static final int INANIMADOS=1;
    /**Constante para conocer el nombre de la imagen que contiene las acciones mario*/
    public static final int MARIO=2;
    /**Constante para conocer el nombre de la imagen que contiene la imagen del escenario 1*/
    public static final int MUNDO_1=3;
    /**Constante para conocer el nombre de la imagen que contiene la animacion de la planta carnivora*/
    public static final int PLANTA=4;
    /**Constante para conocer el nombre de la imagen que contiene todas las partes del castillo*/
    public static final int CASTILLO=5;
     /**Constante para conocer el nombre de la imagen del fondo del menu*/
    public static final int MARIO_INC=6;
    /**Constante para conocer el nombre de la imagen de nuevo juego*/
    public static final int NUEVO_JUEGO=7;
    /**Constante para conocer el nombre de la imagen de salir*/
    public static final int SALIR=8;
    /**Constante para conocer el nombre de la imagen que contiene la estrella*/
    public static final int ESTRELLA=9;
    /**Constante para conocer el nombre de la imagen que contiene la imagen del escenario 2*/
    public static final int MUNDO_2=10;
    /**Constante para conocer el nombre de la imagen que contiene los inanimados del nivel 2*/
    public static final int INANIMADOS_2=11;
    /**Constante para conocer el nombre de la imagen que contiene el titulo instrucciones*/
    public static final int INSTRUCCIONES=12;
    /**Constante para conocer el nombre de la imagen que contiene las instrucciones*/
    public static final int INSTRUCCIONES_STATIC=13;
    /**Constante para conocer el nombre de la imagen las animaciones de bowser*/
    public static final int BOWSER=14;
    /**Constante para identificar la imagen de la bola de fuego*/
    public static final int FUEGO=15;
    /**Constante para identificar la imagen del boton de los creditos*/
    public static final int CREDITOS=16;
    /**Constante para identificar la imagen los creditos*/
    public static final int CREDITOS_IMG=17;    
    /**Fondo de la aldea principal*/
    public static final int FONDO_ALDEA=18;
    /**Imagen del cuartel*/
    public static final int CUARTEL=19;
    /**Imagen del centro*/
    public static final int CENTRO=20;
    /**Imagen de fondo para la presentacion incial del juego*/
    public static final int FONDO_INICIAL=21;
    /**Imagen de todos los soldados*/
    public static final int SOLDADO1=22; 
    /**Imagen del mercado**/
    public static final int MERCADO=23;
    /**Imagen de la Torre **/
    public static final int TORRE=24;
    /**Imagen de la guarnicion**/
    public static final int GUARNICION=25;
    /**imagen para el almacen **/
    public static final int ALMACEN=26;
    /**imagen de la casa **/
    public static final int CASA=27;

    /**Imagen del mercado**/
    public static final int GRANJA=28;
    /**Imagen del mercado**/
    public static final int BOTONES=29;
    /**Imagen de recuadro para ubicar edificio**/
    public static final int RECUADRO=30;
    /**Imagen del abol recurso*/
    public static final int ARBOL=31;
    /**Imagen de recuadro para ubicar edificio**/
    public static final int MINA=32;
    /**Aldeano*/
     public static final int ALDEANO=33;
    /**Imagen con el fondo de cuartel*/
    public static final int ENTRENAR=34;
    /**Solda 2*/
    public static final int SOLDADO2=35;
    /**Solda 3*/
    public static final int SOLDADO3=36;
    /**Solda 4*/
    public static final int SOLDADO4=37;
    /**Constructor de la clase, privado para usar la instancia y solo poder crearse un objeto de la clase, es usado para cargar
    /* el vector de imagenes a travez de la dirreccion url de cada una de las imagenes con ayuda de las constantes */
    private Cargar_Imagenes() {
        URL direccion;
        String nombres_imagenes[] = new String[]{"Enemigos","Inanimados","Mario","Mundo_1","planta_carnivora","Castillo","Mario_inc","Nuevo_juego","Salir","estrella","Mundo_2","Inanimados_2","Instrucciones","instrucciones_static"

        ,"bowser","fuego","creditos","creditos_img","fondo_aldea","cuartel","centro","fondo1","soldado1",
        "mercado","torre","guarnicion","almacen","casa","granja","boton","recuadro","arboles","mina","aldeano",
        "Cuartel_Entrenar","soldado2","soldado3","soldado4"};
        vec_imagenes = new ImageIcon[TOTAL_IMAGENES];
        for(int i=0;i<TOTAL_IMAGENES;i++){
            try {
                direccion = this.getClass().getResource("Imagenes/"+nombres_imagenes[i]+".png");
                vec_imagenes[i] = new ImageIcon(direccion);    
            } catch (Exception error) {
                System.out.println("No se pudo cargar la imagen "+nombres_imagenes[i]+" error: "+error.getMessage());
            }    
        }
    }  

    /**Metodo para poder crear el objeto de la clase, solo permite crearla si la instancia no ha sido creada
     * @return Devuelve la instancia de la clase es en si es un objeto de la clase como tal */
    public static Cargar_Imagenes obtener_instancia() {
        if(instancia==null)
            instancia = new Cargar_Imagenes();
        return instancia;
    }
    
    /**Metodo para obtener o cargar una imagen dada una posicion en el vector
     * @param identificador Entero que sera la posicion del vector en la que se encuentra la imagen requerida
     * @return Devuelve el vector en la posicion especificada o en caso que el identificador sea menor a cero o mayor al total de imagenes devuelve null */
    public ImageIcon obtener_imagen(int identificador) {
        if(identificador<0 || identificador>=TOTAL_IMAGENES)
            return null;
        return vec_imagenes[identificador];
    }   
}
