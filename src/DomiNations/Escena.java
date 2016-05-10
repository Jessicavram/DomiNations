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
    /**Arreglo para objetos de fondo*/
    ArrayList<Objetos_Graficos> vec_objetos_fondo;
    /**Vrctor para almacenar los enemigos*/
    ArrayList<Objetos_Graficos> vec_item_estaticos;
    /**Vector para guardar los cuadros que interactuan con mario*/
    ArrayList<Objetos_Graficos> vec_item_con_movimiento;
    /**Vector para guardar los botones*/
    ArrayList<Objetos_Graficos> vec_botones;
    /** guarda el elemento seleccionado para construir en la aldea*/
    String elemento="";
    /* Registra el item a contruir**/
    Objetos_Graficos item;
     boolean edoElemento=false;
     
    Aldea aldea; 
    
    /**Estado de la escena*/
    boolean Ventana_tienda=false;
    boolean Ventana_cuartel=false;
    boolean agregar_elemento=false;
    int posicion_Cuartel=-1;
    /**Lista de requerimientos para crear o mejor item*/
    Lista_de_Requerimientos Requerimiento;
    
//MAtriz logica de la aldea para concoer por donde pueden caminar los aldeanos
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
        //Crear la lista de requerimientos
        Requerimiento = new Lista_de_Requerimientos();
        matriz_logica=new Matriz_Logica();
    }
    
    public void AgregarElementosAldea(float x,float y){
        
        int t_construc=0; 
        if(elemento.compareTo("Centro0")==0){
            Centro cen = new Centro();
            Requerimientos r = Requerimiento.buscar_requerimiento("Centro",0);
           if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
           {
                cen.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(cen.Obtener_Alto()-(cen.alto*25)));
                vec_item_estaticos.add(cen);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                aldea.total_comida+=cen.capacidad_comida;
                e.setComidaTotal(aldea.total_comida);
                aldea.total_oro+=cen.capcidad_oro;
                e.setOroTotal(aldea.total_oro);
                 
                 t_construc=(Motor_Juego.cont/50)+cen.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Centro",t_construc,e.pos));
                e.panel.repaint();
           }
        }else if(elemento.compareTo("Cuartel0")==0){
           Cuartel cua= new Cuartel();
           Requerimientos r = Requerimiento.buscar_requerimiento("Cuartel",0);
           if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
           {
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
                e.insertar_LEF(new LEF("Cuartel",t_construc,e.pos));
                e.panel.repaint();
           }
        }else if(elemento.compareTo("Almacen0")==0){
                //Insertando almacen
           Almacen alm= new Almacen();
           Requerimientos r = Requerimiento.buscar_requerimiento("Almacen",0);
           if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                alm.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(alm.Obtener_Alto()-(alm.alto*25)));
                vec_item_estaticos.add(alm);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                aldea.total_comida+=alm.capacidad_almacenamiento_comida;
                e.setComidaTotal(aldea.total_comida);
                
                 t_construc=(Motor_Juego.cont/50)+alm.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Almacen",t_construc,e.pos));
                e.panel.repaint();
            }
       
        }else if(elemento.compareTo("Torre0")==0){
             //Insertando torre
            Torre tor= new Torre();
            Requerimientos r = Requerimiento.buscar_requerimiento("Torre",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
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
                e.insertar_LEF(new LEF("Torre",t_construc,e.pos));
                e.panel.repaint();
            }
            
       
        }else if(elemento.compareTo("Mercado0")==0){
            Mercado mer=new Mercado();
            Requerimientos r = Requerimiento.buscar_requerimiento("Mercado",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                mer.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(mer.Obtener_Alto()-(mer.alto*25)));
                vec_item_estaticos.add(mer);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                aldea.total_oro+=mer.capacidad_almacenamiento_oro;
                e.setOroTotal(aldea.total_oro);
                
                t_construc=(Motor_Juego.cont/50)+mer.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Mercado",t_construc,e.pos));
              
                 e.panel.repaint();   
                
            }
       
        }else if(elemento.compareTo("Guarnicion0")==0){
            //Insertando guarnicion
            Guarnicion guar=new Guarnicion();
            Requerimientos r = Requerimiento.buscar_requerimiento("Guarnicion",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
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
                e.insertar_LEF(new LEF("Guarnicion",t_construc,e.pos));
                
               // System.out.println(e.listaLEF.get(e.pos-1).descripcion+" "+e.listaLEF.get(e.pos-1).tiempo+" "+e.listaLEF.get(e.pos-1).posicion);
                //System.out.println(e.listaLEF.get(e.pos-1).getLinea());
                e.panel.repaint();
            }
        
        }else if (elemento.compareTo("Casa")==0) {
            Casa casa = new Casa();
            Requerimientos r = Requerimiento.buscar_requerimiento("Casa",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                casa.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(casa.Obtener_Alto()-(casa.alto*25)));
                vec_item_estaticos.add(casa);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);
                aldea.total_comida+=casa.capacidad_almacenamiento_comida;
                e.setComidaTotal(aldea.total_comida);
                aldea.total_oro+=casa.capacidad_almacenamiento_oro;
                e.setOroTotal(aldea.total_oro);
                t_construc=(Motor_Juego.cont/50)+casa.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);
                e.insertar_LEF(new LEF("Casa",t_construc,e.pos));
                e.panel.repaint();
            }
            
        }else if (elemento.compareTo("Granja0")==0) {
            Granja granja = new Granja();
            Requerimientos r = Requerimiento.buscar_requerimiento("Granja",0);
            if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                granja.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(granja.Obtener_Alto()-(granja.alto*25)));
                vec_item_estaticos.add(granja);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                aldea.oro_Actual-=r.costo_oro;
                e.setOro(aldea.oro_Actual);
                aldea.comida_Actual-=r.costo_comida;
                e.setComida(aldea.comida_Actual);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanosDisponibles(aldea.nro_aldeanos_disponibles);  
                aldea.total_comida+=granja.capacidad_comida;
                e.setComidaTotal(aldea.total_comida);
                t_construc=(Motor_Juego.cont/50)+granja.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);
                e.insertar_LEF(new LEF("Granja",t_construc,e.pos));
                e.panel.repaint();
            }
        }
        
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
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Centro0");
        else
            boton = new Boton("NO-Centro0");
        boton.Seleccionar_Localizacion(16, 550);
        vec_botones.add(boton);
        
        //Precio Torre
        Torre tor=new Torre();
        r = Requerimiento.buscar_requerimiento("Torre",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Torre0");
        else
            boton = new Boton("NO-Torre0");
        boton.Seleccionar_Localizacion(96, 550);
        vec_botones.add(boton);
        
//Precio Mercado
        Mercado mer=new Mercado();
        r = Requerimiento.buscar_requerimiento("Mercado",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Mercado0");
        else
            boton = new Boton("NO-Mercado0");
        boton.Seleccionar_Localizacion(173, 550);
        
        vec_botones.add(boton);
        
        //Precio Guarnicion
        Guarnicion guar=new Guarnicion();
        r = Requerimiento.buscar_requerimiento("Guarnicion",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
               boton = new Boton("Guarnicion0");
        else
                boton = new Boton("NO-Guarnicion0");
        boton.Seleccionar_Localizacion(252, 550);
        vec_botones.add(boton);
        
        //Precio Almacen        
        Almacen alm= new Almacen();
        r = Requerimiento.buscar_requerimiento("Almacen",0);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Almacen0");
        else 
            boton = new Boton("NO-Almacen0");
        boton.Seleccionar_Localizacion(332, 550);
        vec_botones.add(boton);
        
        //Precio Cuartel
        Cuartel cua=new Cuartel();
        r = Requerimiento.buscar_requerimiento("Cuartel",cua.nivel);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Cuartel0");
        else
            boton = new Boton("NO-Cuartel0");
        boton.Seleccionar_Localizacion(415, 550);
        vec_botones.add(boton);
        
        //Precio Casa
        Casa casa=new Casa();
        r = Requerimiento.buscar_requerimiento("Casa",cua.nivel);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Casa");
        else
            boton = new Boton("NO-Casa");
        boton.Seleccionar_Localizacion(495, 550);
        vec_botones.add(boton);
        
        //Precio Granja
       Granja granja =new Granja();
        r = Requerimiento.buscar_requerimiento("Granja0",cua.nivel);
        if(aldea.comida_Actual>=r.costo_comida && aldea.oro_Actual>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
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
                        }else if(cuartel.nro_soldado2_cola>0){
                            cuartel.nro_soldado2_cola--;
                            cuartel.nro_soldado2++;
                        }
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
        g.drawString( ""+cuartel.soldados_en_cola,395,499);
        g.drawString( ""+cuartel.nro_soldado1_cola,300,553);
        g.drawString( ""+cuartel.nro_soldado2_cola,364,553);
        if(cuartel.tiempo_entrenamiento<10)
            g.drawString( ""+cuartel.tiempo_entrenamiento,465,527);    
        else
            g.drawString( ""+cuartel.tiempo_entrenamiento,461,527);   
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
        //Pintar los item que no se mueven en la aldea
        for(int i=0;i<vec_item_estaticos.size();i++)
            vec_item_estaticos.get(i).Dibujar(g); 
        //pintan los item que tienen movimiento por la aldea 
        for(int i=0;i<vec_item_con_movimiento.size();i++){            
            vec_item_con_movimiento.get(i).Dibujar(g);
        }
        //pintan los item que tienen movimiento por la aldea 
        if(!Ventana_tienda)vec_botones.get(0).Dibujar(g);
        
        for(int i=1;i<vec_botones.size() && (Ventana_tienda || Ventana_cuartel);i++){ 
                vec_botones.get(i).Dibujar(g);
        }
        //Pintar el personaje
        g.setColor(Color.BLACK);
        g.fillRect(0,0,80,15);
        g.setColor(Color.RED);
        g.drawString( "Tiempo: "+(Motor_Juego.cont/50) , 0, 10);
        
        if(Ventana_cuartel){
            actualizar_cuartel_entrenar(vec_item_estaticos.get(posicion_Cuartel), g);
        }
    }
    /**Metodo que actializa la escena y donde se realizan acciones logicas*/
    public void update(double timePassed){                       
        //actualizar los item que no se mueven en la aldea
        for(int i=0;i<vec_item_estaticos.size();i++){
            /*Eliminar lo que se requiera eliminar del vector 
            if(vec_item_estaticos.get(i).getY()>=310 || vec_item_estaticos.get(i).getX()<=-300 || vec_item_estaticos.get(i).borrar){
                //eliminar los enemigos que se caigan
                Motor_Fisico.getInstance().borrar_animado(vec_item_estaticos.get(i) );
                vec_item_estaticos.remove(i);
            }else*/
                vec_item_estaticos.get(i).Actualizar_Objeto_Grafico(timePassed);
        }
        //actualizar los item que se mueven en la aldea
        for(int i=0;i<vec_item_con_movimiento.size();i++){
            /*Eliminar lo que se requiera eliminar del vector
            if(vec_item_con_movimiento.get(i).getY()>=310 || vec_item_con_movimiento.get(i).getX()<=-16 || vec_item_con_movimiento.get(i).borrar){
                Motor_Fisico.getInstance().borrar_animado(vec_item_con_movimiento.get(i) );
                vec_item_con_movimiento.remove(i);
            }else*/
                vec_item_con_movimiento.get(i).Actualizar_Objeto_Grafico(timePassed);
        }
      //Consultar en la LEF los eventos futuros 
       e.Consultar_LEF(Motor_Juego.cont/50); 
       if (Motor_Juego.cont%50==0){
           actualizar_tiempos_cuartel();
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
        
        //Insertando soldado
        Soldado sol = new Soldado();
        sol.Seleccionar_Localizacion(250, 300);
        vec_item_con_movimiento.add(sol);
        
//Insertando soldado
        Soldado2 a= new Soldado2();
        a.Seleccionar_Localizacion(300, 300);
        vec_item_con_movimiento.add(a); 
        
        //Insertando Varios Arboles 
        Arbol arb[]=new Arbol[5];
        for(int i=0;i<5;i++){
           
         arb[i]=new Arbol();
         arb[i].Seleccionar_Localizacion(110+i*67, 90);
         vec_item_estaticos.add(arb[i]); 
        }
        
        //insertando Mina
        
        Mina min =new Mina();
        
        min.Seleccionar_Localizacion(140, 20);
        vec_item_estaticos.add(min); 
       
        Aldeano alde= new Aldeano();
        alde.currentAction="Icaminar";
        alde.Seleccionar_Localizacion(400,430);
        alde.animacion.Seleccionar_Accion(alde.currentAction, true);
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
       
       e = new Estadisticas(aldea.oro_Actual,aldea.comida_Actual,aldea.nro_aldeanos_disponibles,aldea.total_oro,aldea.total_comida,aldea.nro_aldeanos);    
    }
    
    @Override
    public void mouseClicked(MouseEvent evento) {
        //Motor_Fisico.getInstance().mouse(e.getX(), e.getY());
        float pos_x = evento.getX();
        float pos_y = evento.getY();
        Objetos_Graficos dinamico;
        Boton b=new Boton();      
        
        for(int i=0;i<vec_item_estaticos.size() && !Ventana_tienda;i++){
            dinamico = vec_item_estaticos.get(i); 
            if((pos_x > dinamico.x && pos_x < dinamico.x + dinamico.Obtener_Ancho()) && (pos_y > dinamico.y && pos_y < dinamico.y + dinamico.Obtener_Alto())){
                if(dinamico instanceof Cuartel){
                    crear_cuartel_entrenar((Cuartel)dinamico);
                    Ventana_cuartel=true;
                    posicion_Cuartel=i;
                }else if(dinamico instanceof Torre){
                    System.out.println("Selecciono Torre");
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
                }
            }
        }
        
        if(agregar_elemento==true){            
            AgregarElementosAldea(evento.getX(),evento.getY());
            agregar_elemento=false;        
        }
        
        for(int i=2;i<vec_botones.size() && (Ventana_tienda || Ventana_cuartel);i++){
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
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
      //  System.out.println("El mouse esta sobre la pantalla en la posicion X= "+e.getX());
      //Este evento solo detecta cuando el mouse entra a la escena    
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
        //Agregando elementos a la aldea
        if(pos_x>692 && pos_y>542 && !Ventana_tienda){
            Ventana_tienda=true;
            crear_tienda();
        }
        if(Ventana_tienda && pos_y<465){
            borrar_botones();
            Ventana_tienda=false;
        }
    }
    
    

}
