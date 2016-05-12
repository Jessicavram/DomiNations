package DomiNations;
import Cargador.Cargar_Imagenes;
import Cargador.Cargar_Sonidos;
import DomiNations.Lista_de_Requerimientos.Requerimientos;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sprites.Rectangulo;
/**Clase para cargar todas las imagenes necesarias para el juego desde el paquete cargador.Imagenes sin necesidad de cargar desde el disco duro del equipo
 * @author Carlos Rangel y Daniel Nieto
 * @version Super Mario Bros 1.0
 **/
public class Escena extends JPanel implements MouseListener,MouseMotionListener{
    /**Personajo Mario*/
    Mario mario;
    /**Escena actual*/
    int escena_actual=1;
    /**coordenada del pixel en x donde empieza el espacio de construccion*/
    int x_inicial=115;
    /**coordenada del pixel en y donde empieza el espacio de construccion*/
    int y_inicial=70;
    /**coordenada del pixel en x donde termina el espacio de construccion*/
    int x_final=665;
    /**coordenada del pixel en y donde termina el espacio de construccion*/
    int y_final=525;
    /**Termino la escena*/
    boolean ter_esc=false;
    /*Recursos*/
    ArrayList<Arbol>RArboles;
    ArrayList<Mina>RMina;
    
    /**Arreglo para objetos de fondo*/
    ArrayList<Objetos_Graficos> vec_objetos_fondo;
    /**Vrctor para almacenar los enemigos*/
    ArrayList<Objetos_Graficos> vec_item_estaticos;
    /**Vector para guardar los cuadros que interactuan con mario*/
    ArrayList<Objetos_Graficos> vec_item_con_movimiento;
    /**Vector para guardar los botones*/
    ArrayList<Objetos_Graficos> vec_botones;
    /**Vector para guardar los soldados*/
    ArrayList<Objetos_Graficos> vec_soldados;
    /** guarda el elemento seleccionado para construir en la aldea_batalla*/
    String elemento="";
    /* Registra el item a contruir**/
    Objetos_Graficos item;

    
    boolean edoElemento=false,boton_arbol=false,boton_oro=false;
    
    /** Tiempo de la proxima batalla*/
    int proxima_batalla;
    /**Batalla*/
    Batalla batalla;

    Aldea aldea; 
    /***/
    boolean ventana_batalla=false;
    
    /**Estado de la escena*/
    boolean Ventana_tienda=false;
    boolean Ventana_cuartel=false;
    boolean agregar_elemento=false;
    int posicion_Cuartel=-1,posicion_arbol=-1,posicion_mina=-1;
    /**Lista de requerimientos para crear o mejor item*/
    Lista_de_Requerimientos Requerimiento;
    
//MAtriz logica de la aldea_batalla para concoer por donde pueden caminar los aldeanos
    Matriz_Logica matriz_logica;
    
 
    public static Estadisticas e;
    
    public boolean isEstado() {
        return Ventana_tienda;
    }

    public void setEstado(boolean estado) {
        this.Ventana_tienda = estado;
    }
    /**Constructor*/
    public Escena(){
        super();
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(771,592));
        mario = new Mario();
        vec_objetos_fondo = new ArrayList<Objetos_Graficos>();
        vec_item_estaticos = new ArrayList<Objetos_Graficos>();
        vec_item_con_movimiento= new ArrayList<Objetos_Graficos>();        

        vec_botones= new ArrayList<Objetos_Graficos>();    
        RArboles=new ArrayList<Arbol>();
        RMina=new ArrayList<Mina>();
        vec_botones= new ArrayList<Objetos_Graficos>();
        vec_soldados= new ArrayList<Objetos_Graficos>();             

        //Crear la lista de requerimientos
        Requerimiento = new Lista_de_Requerimientos();
        matriz_logica=new Matriz_Logica();
    }
    
    public void AgregarElementosAldea(float x,float y){
        
        int t_construc=0;
        int pos = vec_item_estaticos.size();
        if(elemento.compareTo("Centro0")==0){
            Centro cen = new Centro();
            Requerimientos r = Requerimiento.buscar_requerimiento("Centro",0);
           if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
           {
               aldea.centro=true;       
               
               cen.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(cen.Obtener_Alto()-(cen.alto*25)));
                vec_item_estaticos.add(cen);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                 
                 t_construc=(Motor_Juego.cont/50)+cen.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Centro",t_construc,pos));
                e.panel.repaint();
           }
        }else if(elemento.compareTo("Cuartel0")==0){
           Cuartel cua= new Cuartel();
           Requerimientos r = Requerimiento.buscar_requerimiento("Cuartel",0);
           if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
           {
               aldea.cuarteles_construidas++; 
               
                cua.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(cua.Obtener_Alto()-(cua.alto*25)));
                vec_item_estaticos.add(cua);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                
                 t_construc=(Motor_Juego.cont/50)+cua.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Cuartel",t_construc,pos));
                e.panel.repaint();
           }
        }else if(elemento.compareTo("Almacen0")==0){
                //Insertando almacen
           Almacen alm= new Almacen();
           Requerimientos r = Requerimiento.buscar_requerimiento("Almacen",0);
           if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                aldea.almacenes_construidos++;
                
                alm.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(alm.Obtener_Alto()-(alm.alto*25)));
                vec_item_estaticos.add(alm);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                
                 t_construc=(Motor_Juego.cont/50)+alm.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Almacen",t_construc,pos));
                e.panel.repaint();                
            }
       
        }else if(elemento.compareTo("Torre0")==0){
             //Insertando torre
            Torre tor= new Torre();
            Requerimientos r = Requerimiento.buscar_requerimiento("Torre",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                aldea.torres_creadas++;
                
                tor.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(tor.Obtener_Alto()-(tor.alto*25)));
                vec_item_estaticos.add(tor);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                
                 t_construc=(Motor_Juego.cont/50)+tor.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Torre",t_construc,pos));
                e.panel.repaint();
            }
            
       
        }else if(elemento.compareTo("Mercado0")==0){
            Mercado mer=new Mercado();
            Requerimientos r = Requerimiento.buscar_requerimiento("Mercado",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                aldea.mercados_construidas++;
                
                mer.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(mer.Obtener_Alto()-(mer.alto*25)));
                vec_item_estaticos.add(mer);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                
                t_construc=(Motor_Juego.cont/50)+mer.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Mercado",t_construc,pos));
              
                 e.panel.repaint();   
                
            }
       
        }else if(elemento.compareTo("Guarnicion0")==0){
            //Insertando guarnicion
            Guarnicion guar=new Guarnicion();
            Requerimientos r = Requerimiento.buscar_requerimiento("Guarnicion",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                aldea.guarnicion_construidas++;
                
                guar.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(guar.Obtener_Alto()-(guar.alto*25)));
                vec_item_estaticos.add(guar);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
            
                t_construc=(Motor_Juego.cont/50)+guar.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);
                e.insertar_LEF(new LEF("Guarnicion",t_construc,pos));
                
               // System.out.println(e.listaLEF.get(e.pos-1).descripcion+" "+e.listaLEF.get(e.pos-1).tiempo+" "+e.listaLEF.get(e.pos-1).posicion);
                //System.out.println(e.listaLEF.get(e.pos-1).getLinea());
                e.panel.repaint();
            }
        
        }else if (elemento.compareTo("Casa")==0) {
            Casa casa = new Casa();
            Requerimientos r = Requerimiento.buscar_requerimiento("Casa",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                aldea.casas_construidas++;
                
                casa.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(casa.Obtener_Alto()-(casa.alto*25)));
                vec_item_estaticos.add(casa);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                
                t_construc=(Motor_Juego.cont/50)+casa.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);
                e.insertar_LEF(new LEF("Casa",t_construc,pos));
                e.panel.repaint();
            }
            
        }else if (elemento.compareTo("Granja0")==0) {
            Granja granja = new Granja();
            Requerimientos r = Requerimiento.buscar_requerimiento("Granja",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                aldea.granjas_construidas++;
                
                granja.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(granja.Obtener_Alto()-(granja.alto*25)));
                vec_item_estaticos.add(granja);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);  
                
                t_construc=(Motor_Juego.cont/50)+granja.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);
                e.insertar_LEF(new LEF("Granja",t_construc,pos));
                e.panel.repaint();
            }
        }
        
    }
    public void actualizar_valores_cuartel(Objetos_Graficos obj, String tipo){
        if(obj instanceof Cuartel)
        {
            
            Cuartel cuartel = (Cuartel)obj;
            if(tipo.equals("Soldado"))
                cuartel.nro_soldado1++;
            else if(tipo.equals("Artillero"))
                cuartel.nro_soldado2++;            
        }
    }
    public void actualizar_valores(Objetos_Graficos obj)
    {
        if(obj instanceof Centro)
        {
            Centro cen = (Centro)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Centro",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
            aldea.total_comida+=cen.capacidad_comida;
            e.setComidaTotal(aldea.total_comida);
            aldea.total_oro+=cen.capcidad_oro;
            e.setOroTotal(aldea.total_oro);
            cen.avanzar(aldea);
        }
        if(obj instanceof Almacen)
        {
            Almacen alm = (Almacen)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Almacen",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
            aldea.total_comida+=alm.capacidad_almacenamiento_comida;
            e.setComidaTotal(aldea.total_comida);
            
        }
        if(obj instanceof Mercado)
        {
            Mercado mer = (Mercado)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Mercado",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
            aldea.total_oro+=mer.capacidad_almacenamiento_oro;
            e.setOroTotal(aldea.total_oro);
        }
        if(obj instanceof Casa)
        {
            Casa casa = (Casa)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Casa",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
            aldea.total_comida+=casa.capacidad_almacenamiento_comida;
            e.setComidaTotal(aldea.total_comida);
            aldea.total_oro+=casa.capacidad_almacenamiento_oro;
            e.setOroTotal(aldea.total_oro);
        }
        if(obj instanceof Granja)
        {
            Granja granja = (Granja)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Granja",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);  
            aldea.total_comida+=granja.capacidad_comida;
            e.setComidaTotal(aldea.total_comida);
        }
        if (obj instanceof Arbol)
        {
            Arbol arbol = (Arbol)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Arbol",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);  
            aldea.comida_Actual+=arbol.fruta_max;
            e.setComida_Actual(aldea.comida_Actual);
        }
        if (obj instanceof Mina)
        {
            Mina mina = (Mina)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Mina",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);  
            aldea.oro_Actual+=mina.oro_maximo;
            e.setOro_Actual(aldea.oro_Actual);
        }
       
        if(obj instanceof Torre)
        {
            Torre torre = (Torre)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Torre",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);  
            e.setComidaTotal(aldea.total_comida);
        }
        else if(obj instanceof Cuartel)
        {
            Cuartel cuartel = (Cuartel)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Cuartel",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);  
            e.setComidaTotal(aldea.total_comida);
        }else if(obj instanceof Guarnicion)
        {
            Guarnicion guar = (Guarnicion)obj;
            Requerimientos r = Requerimiento.buscar_requerimiento("Guarnicion",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);  
            e.setComidaTotal(aldea.total_comida);
        }

        
        e.panel.repaint();
    }
    
    public void crear_tienda(){        
        borrar_botones();
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.BOTONES).getImage(), new Rectangulo(0, 0, 771,126) );
        obj.Seleccionar_Localizacion(0,466);
        vec_botones.add(obj);
        //Precio Centro
        Requerimientos r;
        Boton boton;
        Centro Centro=new Centro();
        r = Requerimiento.buscar_requerimiento("Centro",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos && !aldea.centro)
            boton = new Boton("Centro0");
        else
            boton = new Boton("NO-Centro0");
        boton.Seleccionar_Localizacion(16, 550);
        vec_botones.add(boton);
        
        //Precio Torre
        Torre tor=new Torre();
        r = Requerimiento.buscar_requerimiento("Torre",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos && aldea.torres_creadas<aldea.torres_permitidas)
            boton = new Boton("Torre0");
        else
            boton = new Boton("NO-Torre0");
        boton.Seleccionar_Localizacion(96, 550);
        vec_botones.add(boton);
        
//Precio Mercado
        Mercado mer=new Mercado();
        r = Requerimiento.buscar_requerimiento("Mercado",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos && aldea.mercados_construidas<aldea.mercados_permitidas)
            boton = new Boton("Mercado0");
        else
            boton = new Boton("NO-Mercado0");
        boton.Seleccionar_Localizacion(173, 550);
        
        vec_botones.add(boton);
        
        //Precio Guarnicion
        Guarnicion guar=new Guarnicion();
        r = Requerimiento.buscar_requerimiento("Guarnicion",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos && aldea.guarnicion_construidas<aldea.guarnicion_permitidas)
               boton = new Boton("Guarnicion0");
        else
                boton = new Boton("NO-Guarnicion0");
        boton.Seleccionar_Localizacion(252, 550);
        vec_botones.add(boton);
        
        //Precio Almacen        
        Almacen alm= new Almacen();
        r = Requerimiento.buscar_requerimiento("Almacen",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos && aldea.almacenes_construidos<aldea.almacenes_permitidos)
            boton = new Boton("Almacen0");
        else 
            boton = new Boton("NO-Almacen0");
        boton.Seleccionar_Localizacion(332, 550);
        vec_botones.add(boton);
        
        //Precio Cuartel
        Cuartel cua=new Cuartel();
        r = Requerimiento.buscar_requerimiento("Cuartel",cua.nivel);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos && aldea.cuarteles_construidas<aldea.cuarteles_permitidas)
            boton = new Boton("Cuartel0");
        else
            boton = new Boton("NO-Cuartel0");
        boton.Seleccionar_Localizacion(415, 550);
        vec_botones.add(boton);
        
        //Precio Casa
        Casa casa=new Casa();
        r = Requerimiento.buscar_requerimiento("Casa",cua.nivel);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos && aldea.casas_construidas<aldea.casas_permitidas)
            boton = new Boton("Casa");
        else
            boton = new Boton("NO-Casa");
        boton.Seleccionar_Localizacion(495, 550);
        vec_botones.add(boton);
        
        //Precio Granja
       Granja granja =new Granja();
        r = Requerimiento.buscar_requerimiento("Granja0",cua.nivel);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos && aldea.granjas_construidas<aldea.granjas_permitidas)
            boton = new Boton("Granja0");
        else
            boton = new Boton("NO-Granja0");
        boton.Seleccionar_Localizacion(575, 550);
        vec_botones.add(boton);
    }
    public void borrar_botones(){
        vec_botones.clear();
        Boton boton = new Boton("Tienda");
        boton.Seleccionar_Localizacion(692, 542);
        vec_botones.add(boton);        
    }
    
    /**Revisa el vector de estaticos si hay algun cuartel verifica si tiene soldades en adiestramiento de ser asi disminuye su tiempo en una unidad*/
    public void actualizar_tiempos_cuartel(){
        Cuartel cuartel;
        Objetos_Graficos estatico;
        for (int i = 0; i < vec_item_estaticos.size(); i++) {
            estatico = vec_item_estaticos.get(i);
            if(estatico instanceof Cuartel){
                cuartel = (Cuartel)estatico;
                if (cuartel.tiempo_entrenamiento>0) {
                    if((cuartel.tiempo_entrenamiento-(cuartel.soldados_en_cola*5)-1)==0){
                        if(cuartel.soldados_en_cola>0)
                            cuartel.soldados_en_cola--;
                        cuartel.capacidad_actual++;
                        if(cuartel.nro_soldado1_cola>0){
                            cuartel.nro_soldado1_cola--;
                            cuartel.nro_soldado1++;
                            aldea.soldados_tipo_1++;
                        }else if(cuartel.nro_soldado2_cola>0){
                            cuartel.nro_soldado2_cola--;
                            cuartel.nro_soldado2++;
                            aldea.soldados_tipo_2++;
                            
                        }
                        System.out.println("CUARTEL: S1:"+cuartel.nro_soldado1+" S2:"+cuartel.nro_soldado2+"Linea 522 esce");
                    }
                    cuartel.tiempo_entrenamiento--;
                    vec_item_estaticos.set(i, cuartel);                   
                }                
            }
        }
    }
    public void crear_cuartel_entrenar(Cuartel cuartel){
        borrar_botones();  
        Requerimientos r;
        Boton boton;
        
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.ENTRENAR).getImage(), new Rectangulo(0, 0, 771,126) );
        obj.Seleccionar_Localizacion(0,466);
        vec_botones.add(obj);
        
        boton = new Boton("X-Cuartel");
        boton.Seleccionar_Localizacion(720, 480);
        vec_botones.add(boton);
        
        
        //Precio Soldado
        Soldado sol1=new Soldado();
        r = Requerimiento.buscar_requerimiento("Soldado1",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && ((cuartel.capacidad_actual+cuartel.soldados_en_cola+1)<cuartel.capacidad_ejercito))
            boton = new Boton("Soldado1");
        else
            boton = new Boton("NO-Soldado1");
        boton.Seleccionar_Localizacion(91, 546);
        vec_botones.add(boton);
        
        //Precio Soldado
        Soldado2 sol2=new Soldado2();
        r = Requerimiento.buscar_requerimiento("Soldado2",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && ((cuartel.capacidad_actual+cuartel.soldados_en_cola+1)<cuartel.capacidad_ejercito))
            boton = new Boton("Soldado2");
        else
            boton = new Boton("NO-Soldado2");
        boton.Seleccionar_Localizacion(153, 546);
        vec_botones.add(boton);
    }
    public void actualizar_cuartel_entrenar(Objetos_Graficos aux, Graphics g){        
        Cuartel cuartel= (Cuartel)(aux);
        g.setColor(Color.black);
        g.drawString( ""+cuartel.soldados_en_cola,395,499);
        g.drawString( ""+cuartel.nro_soldado1_cola,300,553);
        g.drawString( ""+cuartel.nro_soldado2_cola,364,553);
        if(cuartel.tiempo_entrenamiento<10)
            g.drawString( ""+cuartel.tiempo_entrenamiento,465,527);    
        else
            g.drawString( ""+cuartel.tiempo_entrenamiento,461,527);   
    }
    public void actualizar_estadistica_batalla(Graphics g){ 
        g.drawString( ""+(aldea.almacenes_construidos-batalla.nro_almacenes),155,140);
        g.drawString( ""+(aldea.guarnicion_construidas-batalla.nro_guarniciones),300,140);
        g.drawString( ""+(aldea.cuarteles_construidas-batalla.nro_cuarteles),155,252);
        g.drawString( ""+(aldea.mercados_construidas-batalla.nro_mercados),300,252);
        g.drawString( ""+(aldea.granjas_construidas-batalla.nro_granjas),155,366);
        g.drawString( ""+(aldea.torres_creadas-batalla.nro_torres),300,366);
        g.drawString( ""+(aldea.soldados_tipo_1-batalla.soldado_1),155,476);
        g.drawString( ""+(aldea.soldados_tipo_2-batalla.soldado_2),300,476);        
        g.drawString( ""+(batalla.oro_robado),500,275);
        g.drawString( ""+(batalla.comida_robada),660,275);        
        g.drawString( ""+(batalla.tiempo_batalla),585,430);
        /*
        aldea.oro_Actual-=batalla.oro_robado;
        aldea.comida_Actual-=batalla.comida_robada;
        */
    }
    public Objetos_Graficos Tipo_Item(String nombre){
        String clase= nombre.substring(0, nombre.length()-1);
        if(clase.compareTo("Almacen")==0)
            return new Almacen();
        else if (clase.compareTo("Cas")==0) {
            return new Casa();
        }else if (clase.compareTo("Centro")==0) {
            return new Centro();
        }
        else if(clase.compareTo("Cuartel")==0){
              return new Cuartel();           
        }else if(clase.compareTo("Granja")==0)
            return new Granja();
        else if (clase.compareTo("Guarnicion")==0) {
            return new Guarnicion();
        }else if (clase.compareTo("Mercado")==0) {
            return new Mercado();            
        }else if (clase.compareTo("Torre")==0){
            return new Torre();
        }
        return null;
    }
    
    
    @Override
    public void paint(Graphics g){
        //Pintar el fondo de la aplicación

       
        g.setColor( Color.BLACK );
        g.fillRect(0, 0, getWidth(), getHeight());
        
        //Pintar el fondo
         vec_objetos_fondo.get(0).Dibujar(g);
        for(int i=0; i<vec_objetos_fondo.size() && Ventana_tienda;i++){
            vec_objetos_fondo.get(i).Dibujar(g);
        }
        if(ventana_batalla)vec_objetos_fondo.get(vec_objetos_fondo.size()-1).Dibujar(g);
        //Pintar los item que no se mueven en la aldea_batalla
        for(int i=0;i<vec_item_estaticos.size() && !ventana_batalla;i++)
            vec_item_estaticos.get(i).Dibujar(g); 
        //pintan los item que tienen movimiento por la aldea_batalla 
        for(int i=0;i<vec_item_con_movimiento.size() && !ventana_batalla;i++){            
            vec_item_con_movimiento.get(i).Dibujar(g);
        }
        //pintar boton tienda 
        if(!Ventana_tienda && !ventana_batalla)vec_botones.get(0).Dibujar(g);
        
        for(int i=1;i<vec_botones.size() && (Ventana_tienda || Ventana_cuartel || boton_oro || boton_arbol) && !ventana_batalla;i++){ 
                vec_botones.get(i).Dibujar(g);
        }
        
        for(int i=0;i<vec_soldados.size() && !ventana_batalla;i++){            
            vec_soldados.get(i).Dibujar(g);
        }
        //Pintar el personaje
        g.setColor(Color.BLACK);
        g.fillRect(0,0,80,15);
        g.setColor(Color.RED);
        g.drawString( "Tiempo: "+(Motor_Juego.cont/50) , 0, 10);
        
        if(proxima_batalla>0){
            g.setColor(Color.black);
            g.fillRect(550,0,780,30);
            g.setColor(Color.white);
            g.drawString( "Tiempo Proxim Batalla: "+proxima_batalla , 600, 15);
        }
        if(Ventana_cuartel){
            actualizar_cuartel_entrenar(vec_item_estaticos.get(posicion_Cuartel), g);
        }
        if(ventana_batalla)actualizar_estadistica_batalla(g);
    }
    /**Metodo que actializa la escena y donde se realizan acciones logicas*/
    public void update(double timePassed){                       
        //actualizar los item que no se mueven en la aldea_batalla
        for(int i=0;i<vec_item_estaticos.size();i++){
            /*Eliminar lo que se requiera eliminar del vector 
            if(vec_item_estaticos.get(i).getY()>=310 || vec_item_estaticos.get(i).getX()<=-300 || vec_item_estaticos.get(i).borrar){
                //eliminar los enemigos que se caigan
                Motor_Fisico.getInstance().borrar_animado(vec_item_estaticos.get(i) );
                vec_item_estaticos.remove(i);
            }else*/
                vec_item_estaticos.get(i).Actualizar_Objeto_Grafico(timePassed);
        }
        //actualizar los item que se mueven en la aldea_batalla
        for(int i=0;i<vec_item_con_movimiento.size();i++){
            /*Eliminar lo que se requiera eliminar del vector
            if(vec_item_con_movimiento.get(i).getY()>=310 || vec_item_con_movimiento.get(i).getX()<=-16 || vec_item_con_movimiento.get(i).borrar){
                Motor_Fisico.getInstance().borrar_animado(vec_item_con_movimiento.get(i) );
                vec_item_con_movimiento.remove(i);
            }else*/
                vec_item_con_movimiento.get(i).Actualizar_Objeto_Grafico(timePassed);
        }
        for(int i=0;i<vec_soldados.size();i++){
            /*Eliminar lo que se requiera eliminar del vector
            if(vec_item_con_movimiento.get(i).getY()>=310 || vec_item_con_movimiento.get(i).getX()<=-16 || vec_item_con_movimiento.get(i).borrar){
                Motor_Fisico.getInstance().borrar_animado(vec_item_con_movimiento.get(i) );
                vec_item_con_movimiento.remove(i);
            }else*/
                vec_soldados.get(i).Actualizar_Objeto_Grafico(timePassed);
                
              //averiguando en que posicion de la matriz logica se encuentra el ALDEANO
                
                if(vec_item_con_movimiento.get(i) instanceof Aldeano){
                    Aldeano aldeano;
                    aldeano=(Aldeano)vec_item_con_movimiento.get(i);
                    aldeano.Actualizar_Aldeano(timePassed, matriz_logica, x_inicial, y_inicial);
                    vec_item_con_movimiento.set(i, aldeano);
                 
                } 
                
                //System.out.println("Posicion en x "+vec_item_con_movimiento.get(i).x);
        }
      //Consultar en la LEF los eventos futuros 
       int pos_obj=e.Consultar_LEF(Motor_Juego.cont/50); 
       if (Motor_Juego.cont%50==0){
           actualizar_tiempos_cuartel();
           proxima_batalla--;
       }
       //Es tiempo de una batalla
       if(pos_obj==-5){
           System.out.println("A-S1:"+aldea.soldados_tipo_1+" A-S2:"+aldea.soldados_tipo_2+"linea 669");
           batalla = new Batalla();
           batalla.generar(aldea);
           batalla.mostrar();
           aldea.mostrar("Aldea");
           System.out.println("Creadas: "+batalla.total_soldados_generados+" "+batalla.nivel_destruccion+" linea 668");
           System.out.println("A-S1:"+aldea.soldados_tipo_1+" A-S2:"+aldea.soldados_tipo_2+"linea 669");
           mostrar_estadistica_batalla();
       }
       if(pos_obj>=0 && pos_obj<vec_item_estaticos.size())
       {
           actualizar_valores(vec_item_estaticos.get(pos_obj));
       }
      
    }
    
    public void moveScreen(float dx){
        for(int i=0;i<vec_objetos_fondo.size();i++){
            vec_objetos_fondo.get(i).Mover(dx, 0);
        }
        for(int i=0;i<vec_item_estaticos.size();i++)
            vec_item_estaticos.get(i).Mover(dx, 0);
        for(int i=0;i<vec_item_con_movimiento.size();i++)
            vec_item_con_movimiento.get(i).Mover(dx, 0);
    }
      
    public void Crear_Aldea(){       
        vec_objetos_fondo.clear();
        vec_item_estaticos.clear();
        vec_item_con_movimiento.clear();
        Motor_Fisico.getInstance().clearPhysicsEngine();
        mario.Modificar(1, 0, 266);
        mario.Seleccionar_Localizacion(0,250);
        Motor_Fisico.getInstance().addDynamicObject(mario);   

        //aldea
        
        
        aldea = new Aldea();
        aldea.oro_Actual=2000;
        aldea.total_oro=3000;
        aldea.comida_Actual=3000;
        aldea.total_comida=4000;
        aldea.nro_aldeanos=20;
        aldea.nro_aldeanos_disponibles=20;
        //NIVEL 1
        
        //Imagen de Fondo
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.FONDO_ALDEA).getImage(), new Rectangulo(0, 0, 767,592) );
        obj.Seleccionar_Localizacion(0,0);
        vec_objetos_fondo.add(obj);    
        
        //Agregar boton tienda
        Boton boton = new Boton("Tienda");
        boton.Seleccionar_Localizacion(692, 542);
        vec_botones.add(boton);        
        
        
       
        AgregarRecursosAldea();
        /*
        Aldeano alde= new Aldeano();
        alde.currentAction="Icaminar";
        alde.Seleccionar_Localizacion(400,430);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        matriz_logica.imprimir();
        alde.resuelve(matriz_logica,10,10,0,1);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Dcaminar";
        alde.Seleccionar_Localizacion(400,460);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Abcaminar";
        alde.Seleccionar_Localizacion(400,490);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Arcaminar";
        alde.Seleccionar_Localizacion(400,400);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Iquieto";
        alde.Seleccionar_Localizacion(430,400);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Dquieto";
        alde.Seleccionar_Localizacion(430,430);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Arquieto";
        alde.Seleccionar_Localizacion(430,460);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Abquieto";
        alde.Seleccionar_Localizacion(430,490);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Iconstruir";
        alde.Seleccionar_Localizacion(460,430);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Dconstruir";
        alde.Seleccionar_Localizacion(460,460);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Abconstruir";
        alde.Seleccionar_Localizacion(460,490);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);

        
        alde= new Aldeano();
        alde.currentAction="Arconstruir";
        alde.Seleccionar_Localizacion(460,400);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
        vec_item_con_movimiento.add(alde);
       */
       e = new Estadisticas(aldea.oro_Actual,aldea.comida_Actual,aldea.nro_aldeanos_disponibles,aldea.total_oro,aldea.total_comida,aldea.nro_aldeanos);    
       tiempo_proxima_batalla();
    }
    
    public void AgregarRecursosAldea(){
      
         Arbol a1=new Arbol();
         a1.Seleccionar_Localizacion(110, 70);
         vec_item_estaticos.add(a1); 
         RArboles.add(a1);
               
         Arbol a2=new Arbol();
         a2.Seleccionar_Localizacion(175, 70);
         vec_item_estaticos.add(a2); 
         RArboles.add(a2);
         
         Arbol a3=new Arbol();
         a3.Seleccionar_Localizacion(600, 60);
         vec_item_estaticos.add(a3); 
         RArboles.add(a3);
         
         
         Arbol a4=new Arbol();
         a4.Seleccionar_Localizacion(600, 430);
         vec_item_estaticos.add(a4);
         RArboles.add(a4);
         
         Arbol a5=new Arbol();
         a5.Seleccionar_Localizacion(110, 400);
         vec_item_estaticos.add(a5);
         RArboles.add(a5);
        
         Mina min1 =new Mina();
         min1.Seleccionar_Localizacion(260, 70);
         vec_item_estaticos.add(min1);
         RMina.add(min1);
         
          Mina min2 =new Mina();
         min2.Seleccionar_Localizacion(470, 70);
         vec_item_estaticos.add(min2);
         RMina.add(min2);
         
         Mina min3 =new Mina();
         min3.Seleccionar_Localizacion(200, 460);
         vec_item_estaticos.add(min3); 
          RMina.add(min3);
          
          
          /*Insertando Arboles en la matrizLogica recurso es 3 en la matriz Logica*/
           for(int i=0;i<RArboles.size();i++){
               
        //     matriz_logica.colocar_Recurso(matriz_logica.coordenadaY_a_Fila((int)RArboles.get(i).getY(), y_inicial),matriz_logica.coordenaX_a_Columna((int)RArboles.get(i).getX(), x_inicial),2,2);  
           }
            /*Insertando Mina en la matrizLogica recurso es 3 en la matriz Logica*/
           for(int i=0;i<RArboles.size();i++){
          //   matriz_logica.colocar_Recurso(matriz_logica.coordenadaY_a_Fila((int)RMina.get(i).getY(), y_inicial),matriz_logica.coordenaX_a_Columna((int)RMina.get(i).getX(), x_inicial),2,2);  
           }
         
    }
    
    
    public void RecolectarRecursos(Objetos_Graficos objg){
     
        if(objg instanceof Mina){
           Mina m=(Mina)objg;
           if(m.botonre_activo!=true)
           vec_item_estaticos.add(m.HabilitarBotonRecoleccion(objg));
          
        }
        
        if(objg instanceof Arbol){
           Arbol a=(Arbol)objg;
           if(a.botonre_activo!=true)
           vec_item_estaticos.add(a.HabilitarBotonRecoleccion(objg));
           
        }
        
        if(objg instanceof Torre){
            Torre t=(Torre)objg;
            if(t.botonActt_activo!=true)
              vec_item_estaticos.add(t.HabilitarBotonActualizacion(objg));  
            
        }
       
    }
    /*No sirve Aqui*/
    public void ActualizarRecursosLEF(int tiempo,Objetos_Graficos obj){
        int poslista;
        System.out.println("AQUI Aumento Recurso");
        //if(Motor_Juego.cont/50==tiempo){
        if(obj instanceof Mina){//es una mina 
            poslista=e.Consultar_LEF(tiempo);
            Mina m=(Mina)obj;
           Requerimientos r = Requerimiento.buscar_requerimiento("Mina",0);
            aldea.nro_aldeanos_disponibles+=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
            aldea.total_oro+=m.oro_maximo;
            e.setOroTotal(aldea.total_oro);
        }else if(obj instanceof Arbol){//es arbol
            poslista=e.Consultar_LEF(tiempo);
        
        }
     //}
    
    }
    
    
    public Mina BuscarMina(){
         
        for(int i=0;i<RMina.size();i++){
             
             if(RMina.get(i).botonre_activo==true){
                 return RMina.get(i);
             }              
          }
        
        return null;
    }
    

    public Arbol BuscarArbol(){
         
        for(int i=0;i<RArboles.size();i++){
             
             if(RArboles.get(i).botonre_activo==true){
                 return RArboles.get(i);
             }              
          }
        
        return null;
    }
    
    @Override
    public void mouseClicked(MouseEvent evento) {
        //Motor_Fisico.getInstance().mouse(e.getX(), e.getY());
        float pos_x = evento.getX();
        float pos_y = evento.getY();
        Objetos_Graficos dinamico;
        Boton b=new Boton(); 
        Mina m=new Mina();
        Arbol a=new Arbol();
        Soldado soldado1;
        if(!ventana_batalla){
            for(int i=0;i<vec_item_estaticos.size() && !Ventana_tienda;i++){
                dinamico = vec_item_estaticos.get(i); 
                if((pos_x > dinamico.x && pos_x < dinamico.x + dinamico.Obtener_Ancho()) && (pos_y > dinamico.y && pos_y < dinamico.y + dinamico.Obtener_Alto())){

                    if(dinamico instanceof Cuartel){
                        crear_cuartel_entrenar((Cuartel)dinamico);
                        Ventana_cuartel=true;
                        posicion_Cuartel=i;
                    }else if(dinamico instanceof Torre){
                        System.out.println("Selecciono Torre");
                        RecolectarRecursos(dinamico);
                    }else if(dinamico instanceof Guarnicion){
                        System.out.println("Selecciono Guarnicion");
                    }else if(dinamico instanceof Almacen){
                        System.out.println("Selecciono Almacen");
                    }else if(dinamico instanceof Casa){
                        System.out.println("Selecciono Casa");
                    }else if(dinamico instanceof Centro){
                        System.out.println("Selecciono Centro");
                    }else if(dinamico instanceof Mercado){
                        System.out.println("Selecciono Mercado");
                    }else if(dinamico instanceof Granja){
                        System.out.println("Selecciono Granaja");
                    }else if(dinamico instanceof Mina && !boton_oro){
                        System.out.println("Selecciono mina");
                        //RecolectarRecursos(dinamico);
                        posicionar_boton_recolectar(dinamico);
                        posicion_mina=i;
                    }else if(dinamico instanceof Arbol && !boton_arbol){
                        //RecolectarRecursos(dinamico);
                        System.out.println("Selecciono arbol");
                        posicionar_boton_recolectar(dinamico);
                        posicion_arbol=i;
                    }/*else if(dinamico instanceof Boton){            
                        b=(Boton)dinamico;
                        if(b.Nombre.compareTo("MINA")==0){                      
                            System.out.println("Selecciono Boton Mina");
                            m=BuscarMina();                    
                            Requerimientos r = Requerimiento.buscar_requerimiento("Mina",0);
                            if(aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos){
                                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                                int t_obtencionRecursoM=(Motor_Juego.cont/50)+m.tiempo;
                                e.insertar_LEF(new LEF("mas ORO", t_obtencionRecursoM,e.pos));
                                Motor_Fisico.getInstance().borrar_animado(BuscarMina().recolectaM);
                                vec_item_estaticos.remove(BuscarMina().recolectaM);
                                m.botonre_activo=false;
                                m.bloquear=true;

                                //ActualizarRecursosLEF(t_obtencionRecursoM,(Objetos_Graficos)(Objetos_Animados)m);
                                e.panel.repaint();                        
                            }

                       }else if(b.Nombre.compareTo("ARBOL")==0){
                            System.out.println("Selecciono Boton Arbol");                       
                            a=BuscarArbol();                          
                            Requerimientos r = Requerimiento.buscar_requerimiento("Arbol",0);
                            if(aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos){
                                System.out.println("entro seleccion");
                                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                                int t_obtencionRecursoA=(Motor_Juego.cont/50)+a.tiempo;
                                e.insertar_LEF(new LEF("mas COMIDA", t_obtencionRecursoA,e.pos));
                                Motor_Fisico.getInstance().borrar_animado(BuscarArbol().recolectarA);
                                vec_item_estaticos.remove(BuscarArbol().recolectarA);
                                a.botonre_activo=false;                              
                             //     ActualizarRecursosLEF(t_obtencionRecursoA,(Objetos_Graficos)(Objetos_Animados)a);

                            }   
                        }    
                    }*/
                }
            }

            if(agregar_elemento==true){      
                for(int i=0;i<vec_item_estaticos.size();i++){
                    dinamico=vec_item_estaticos.get(i);
                    if(dinamico instanceof Recuadro){
                        vec_item_estaticos.remove(i);
                    } 
                } 
                Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.INICIAR_CONSTRUCCION, false, false);//
                AgregarElementosAldea(evento.getX(),evento.getY());
                agregar_elemento=false;        
            }
            if(edoElemento==true){ 
                if(boton_arbol){
                    agregar_recurso_lef(vec_item_estaticos.get(posicion_arbol));
                    edoElemento=false;
                }else if(boton_oro){
                    agregar_recurso_lef(vec_item_estaticos.get(posicion_mina));
                    edoElemento=false;
                }
            }
            int qw;
            if(Ventana_tienda || Ventana_cuartel)qw=2;
            else qw=1;
            for(int i=qw;i<vec_botones.size() && (Ventana_tienda || Ventana_cuartel || boton_arbol || boton_oro);i++){
                dinamico = vec_botones.get(i);
                b=(Boton)vec_botones.get(i);
                if((pos_x > dinamico.x && pos_x < dinamico.x + dinamico.Obtener_Ancho()) && (pos_y > dinamico.y && pos_y < dinamico.y + dinamico.Obtener_Alto()))
                {    if(b.Nombre.equals("X-Cuartel"))
                    {   Ventana_cuartel=false;
                        borrar_botones();
                    }else if(!b.Nombre.substring(0,2).equals("NO") && b.Nombre.length()>7 && b.Nombre.substring(0,7).equals("Soldado")){
                        Cuartel aux = (Cuartel)vec_item_estaticos.get(posicion_Cuartel);    
                        Requerimientos r;                    
                            if (b.Nombre.equals("Soldado1")) {
                                aux.nro_soldado1_cola++;                            
                                Soldado sol1 = new Soldado();
                                aux.tiempo_entrenamiento+=sol1.tiempo;
                                r = Requerimiento.buscar_requerimiento("Soldado1",0);
                                aldea.comida_Actual-=r.costo_comida;
                                e.setComida(aldea.comida_Actual);
                                e.insertar_LEF(new LEF("Soldado",((Motor_Juego.cont/50)+aux.tiempo_entrenamiento),e.pos));
                                e.panel.repaint();
                            }else{
                                aux.nro_soldado2_cola++;
                                Soldado2 sol2 = new Soldado2();
                                aux.tiempo_entrenamiento+=sol2.tiempo;
                                r = Requerimiento.buscar_requerimiento("Soldado2",0);
                                aldea.comida_Actual-=r.costo_comida;
                                e.setComida(aldea.comida_Actual);
                                e.insertar_LEF(new LEF("Artillero",((Motor_Juego.cont/50)+aux.tiempo_entrenamiento),posicion_Cuartel));
                                e.panel.repaint(); 
                            }
                            aux.soldados_en_cola= (aux.nro_soldado1_cola+aux.nro_soldado2_cola==0 ? 0 : (aux.nro_soldado1_cola+aux.nro_soldado2_cola-1));
                            vec_item_estaticos.set(posicion_Cuartel, aux);
                            borrar_botones();
                            crear_cuartel_entrenar(aux);
                    }else if(b.Nombre.equals("ARBOL") ){
                        edoElemento=true;
                            
                    }else if(b.Nombre.equals("MINA") ){
                         edoElemento=true;
                    }
                    else if(!b.Nombre.substring(0,2).equals("NO")){
                        elemento=b.Nombre;  
                        item = Tipo_Item(elemento);
                        System.out.println(b.Nombre);
                        agregar_elemento=true;
                        Ventana_tienda=false; 
                    }
                }
            }
        }else{
            if(evento.getX()>=675 && evento.getX()<=702 && (evento.getY()>=30 && evento.getY()<=63)){
                ventana_batalla=false;
                vec_objetos_fondo.remove(vec_objetos_fondo.size()-1);
                tiempo_proxima_batalla();
            }
        }
    }
        
     
                    
      /*                 for(int i=2;i<vec_botones.size() && (Ventana_tienda || Ventana_cuartel);i++){
                dinamico = vec_botones.get(i);
                b=(Boton)vec_botones.get(i);
                if((pos_x > dinamico.x && pos_x < dinamico.x + dinamico.Obtener_Ancho()) && (pos_y > dinamico.y && pos_y < dinamico.y + dinamico.Obtener_Alto()))
                {    if(b.Nombre.equals("X-Cuartel"))
                    {   Ventana_cuartel=false;
                        borrar_botones();
                    }else if(!b.Nombre.substring(0,2).equals("NO") && b.Nombre.length()>7 && b.Nombre.substring(0,7).equals("Soldado")){
                        Cuartel aux = (Cuartel)vec_item_estaticos.get(posicion_Cuartel);    
                        Requerimientos r;                    
                            if (b.Nombre.equals("Soldado1")) {
                                aux.nro_soldado1_cola++;                            
                                Soldado sol1 = new Soldado();
                                aux.tiempo_entrenamiento+=sol1.tiempo;
                                r = Requerimiento.buscar_requerimiento("Soldado1",0);
                                aldea.comida_Actual-=r.costo_comida;
                                aldea.soldados_tipo_1++;
                                e.setComida(aldea.comida_Actual);
                                e.insertar_LEF(new LEF("Soldado",((Motor_Juego.cont/50)+aux.tiempo_entrenamiento),e.pos));
                                e.panel.repaint();
                            }else{
                                aux.nro_soldado2_cola++;
                                Soldado2 sol2 = new Soldado2();
                                aux.tiempo_entrenamiento+=sol2.tiempo;
                                r = Requerimiento.buscar_requerimiento("Soldado2",0);
                                aldea.comida_Actual-=r.costo_comida;
                                aldea.soldados_tipo_2++;
                                e.setComida(aldea.comida_Actual);
                                e.insertar_LEF(new LEF("Artillero",((Motor_Juego.cont/50)+aux.tiempo_entrenamiento),e.pos));
                                e.panel.repaint();
                            }
                            aux.soldados_en_cola= (aux.nro_soldado1_cola+aux.nro_soldado2_cola==0 ? 0 : (aux.nro_soldado1_cola+aux.nro_soldado2_cola-1));
                            vec_item_estaticos.set(posicion_Cuartel, aux);
                            borrar_botones();
                            crear_cuartel_entrenar(aux);
                    }
                    else if(!b.Nombre.substring(0,2).equals("NO")){
                        elemento=b.Nombre;  
                        item = Tipo_Item(elemento);
                        System.out.println(b.Nombre);
                        agregar_elemento=true;
                        Ventana_tienda=false; 
                    }
                }
            }
        }else{
            if(evento.getX()>=675 && evento.getX()<=702 && (evento.getY()>=30 && evento.getY()<=63)){
                ventana_batalla=false;
                vec_objetos_fondo.remove(vec_objetos_fondo.size()-1);
            }
        }
    }
        }
    
    */
    
    
    @Override
    public void mousePressed(MouseEvent e) {
        
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
        
        
    }

  
    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        
        float pos_x = me.getX();
        float pos_y = me.getY();
        Objetos_Graficos obj;
       
        Recuadro cuadro;
        for(int i=0;i<vec_item_estaticos.size();i++){
            obj=vec_item_estaticos.get(i);
            if(obj instanceof Recuadro){
                vec_item_estaticos.remove(i);
            } 
         }
        if((me.getX()>x_inicial && me.getX()<x_final) && (me.getY()>y_inicial && me.getY()<y_final) && agregar_elemento){
            if(matriz_logica.verificar_disponibilidad(matriz_logica.coordenadaY_a_Fila(me.getY(), y_inicial),matriz_logica.coordenaX_a_Columna(me.getX(), x_inicial),item.ancho,item.alto)==0)
                cuadro =new Recuadro(item.ancho,item.alto,true);
            else
                cuadro =new Recuadro(item.ancho,item.alto,false);
            cuadro.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna(me.getX(), x_inicial)*25, y_inicial+matriz_logica.coordenadaY_a_Fila(me.getY(), y_inicial)*25);
            vec_item_estaticos.add(cuadro);
        }
        //Agregando elementos a la aldea_batalla
        if(pos_x>692 && pos_y>542 && !Ventana_tienda && !ventana_batalla){
            Ventana_tienda=true;
            crear_tienda();
        }
        if(Ventana_tienda && pos_y<465){
            borrar_botones();
            Ventana_tienda=false;
        }
        
    }
       
    public void tiempo_proxima_batalla(){
        //proxima_batalla= (int)(aldea.aleatorio(60, 150));
        proxima_batalla=(int) ((Motor_Juego.cont/50)+aldea.aleatorio(60, 150));
        e.insertar_LEF(new LEF("Batalla",(int)((Motor_Juego.cont/50)+proxima_batalla) ,-5));
        proxima_batalla++;
        //e.insertar_LEF(new LEF("Batalla",proxima_batalla ,-5));
    }
    public void mostrar_estadistica_batalla(){
        //Imagen de Fondo
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.BATALLA).getImage(), new Rectangulo(0, 0, 767,592) );
        obj.Seleccionar_Localizacion(0,0);
        vec_objetos_fondo.add(obj); 
        ventana_batalla=true;
        Ventana_cuartel=false;
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    public void posicionar_boton_recolectar(Objetos_Graficos x){
        borrar_botones();  
        Requerimientos r;
        Boton boton;
        if(x instanceof Mina){
            boton =new Boton(true,"MINA");
            boton_oro=true;
            edoElemento=false;
        }else{
            boton =new Boton(true,"ARBOL");            
            boton_arbol=true;
            edoElemento=false;
        }
        boton.Seleccionar_Localizacion((int)(x.getX()),(int)(x.getY()));
        vec_botones.add(boton);        
    }
    public void agregar_recurso_lef(Objetos_Graficos a){
        Requerimientos r = Requerimiento.buscar_requerimiento("Arbol",0);
        if(a instanceof Arbol && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos){
            aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
            e.insertar_LEF(new LEF("+Comida",((Motor_Juego.cont/50)+r.tiempo),posicion_arbol));
            e.panel.repaint(); 
            boton_arbol=false;
            
        }else if(a instanceof Mina && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos){
            aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
            e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
            e.insertar_LEF(new LEF("+Oro",((Motor_Juego.cont/50)+r.tiempo),posicion_mina));
            e.panel.repaint(); 
            boton_oro=false;
        }
        
    }
}


