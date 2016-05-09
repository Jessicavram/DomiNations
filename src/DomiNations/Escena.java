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
    boolean agregar_elemento=false;
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
    public void pintar_menu(){
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
           if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
           {
                cen.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(cen.Obtener_Alto()-(cen.alto*25)));
                vec_item_estaticos.add(cen);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.total_oro-=r.costo_oro;
                e.setOro(aldea.total_oro);
                aldea.total_comida-=r.costo_comida;
                e.setComida(aldea.total_comida);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanos(aldea.nro_aldeanos_disponibles);
                 
                 t_construc=(Motor_Juego.cont/50)+cen.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Centro",t_construc,e.pos));
                e.panel.repaint();
           }
        }else if(elemento.compareTo("Cuartel0")==0){
           Cuartel cua= new Cuartel();
           Requerimientos r = Requerimiento.buscar_requerimiento("Cuartel",0);
           if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
           {
                cua.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(cua.Obtener_Alto()-(cua.alto*25)));
                vec_item_estaticos.add(cua);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.total_oro-=r.costo_oro;
                e.setOro(aldea.total_oro);
                aldea.total_comida-=r.costo_comida;
                e.setComida(aldea.total_comida);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanos(aldea.nro_aldeanos_disponibles);
                 
                 t_construc=(Motor_Juego.cont/50)+cua.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Cuartel",t_construc,e.pos));
                e.panel.repaint();
           }
        }else if(elemento.compareTo("Almacen0")==0){
                //Insertando almacen
           Almacen alm= new Almacen();
           Requerimientos r = Requerimiento.buscar_requerimiento("Almacen",0);
           if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                alm.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(alm.Obtener_Alto()-(alm.alto*25)));
                vec_item_estaticos.add(alm);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.total_oro-=r.costo_oro;
                e.setOro(aldea.total_oro);
                aldea.total_comida-=r.costo_comida;
                e.setComida(aldea.total_comida);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanos(aldea.nro_aldeanos_disponibles);
                
                 t_construc=(Motor_Juego.cont/50)+alm.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Almacen",t_construc,e.pos));
                e.panel.repaint();
            }
       
        }else if(elemento.compareTo("Torre0")==0){
             //Insertando torre
            Torre tor= new Torre();
            Requerimientos r = Requerimiento.buscar_requerimiento("Torre",0);
            if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                tor.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(tor.Obtener_Alto()-(tor.alto*25)));
                vec_item_estaticos.add(tor);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.total_oro-=r.costo_oro;
                e.setOro(aldea.total_oro);
                aldea.total_comida-=r.costo_comida;
                e.setComida(aldea.total_comida);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanos(aldea.nro_aldeanos_disponibles);
                
                 t_construc=(Motor_Juego.cont/50)+tor.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Torre",t_construc,e.pos));
                e.panel.repaint();
            }
            
       
        }else if(elemento.compareTo("Mercado0")==0){
            Mercado mer=new Mercado();
            Requerimientos r = Requerimiento.buscar_requerimiento("Mercado",0);
            if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                mer.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(mer.Obtener_Alto()-(mer.alto*25)));
                vec_item_estaticos.add(mer);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.total_oro-=r.costo_oro;
                e.setOro(aldea.total_oro);
                aldea.total_comida-=r.costo_comida;
                e.setComida(aldea.total_comida);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanos(aldea.nro_aldeanos_disponibles);
                
                t_construc=(Motor_Juego.cont/50)+mer.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);        
                e.insertar_LEF(new LEF("Mercado",t_construc,e.pos));
              
                 e.panel.repaint();   
                
            }
       
        }else if(elemento.compareTo("Guarnicion0")==0){
            //Insertando guarnicion
            Guarnicion guar=new Guarnicion();
            Requerimientos r = Requerimiento.buscar_requerimiento("Guarnicion",0);
            if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                guar.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(guar.Obtener_Alto()-(guar.alto*25)));
                vec_item_estaticos.add(guar);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                
                aldea.total_oro-=r.costo_oro;
                e.setOro(aldea.total_oro);
                aldea.total_comida-=r.costo_comida;
                e.setComida(aldea.total_comida);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanos(aldea.nro_aldeanos_disponibles);
                
                
            
                t_construc=(Motor_Juego.cont/50)+guar.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);
                e.insertar_LEF(new LEF("Guarnicion",t_construc,e.pos));
                
               // System.out.println(e.listaLEF.get(e.pos-1).descripcion+" "+e.listaLEF.get(e.pos-1).tiempo+" "+e.listaLEF.get(e.pos-1).posicion);
                //System.out.println(e.listaLEF.get(e.pos-1).getLinea());
                e.panel.repaint();
            }
        
        }else if (elemento.compareTo("Casa0")==0) {
            Casa casa = new Casa();
            Requerimientos r = Requerimiento.buscar_requerimiento("Casa",0);
            if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                casa.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(casa.Obtener_Alto()-(casa.alto*25)));
                vec_item_estaticos.add(casa);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                aldea.total_oro-=r.costo_oro;
                e.setOro(aldea.total_oro);
                aldea.total_comida-=r.costo_comida;
                e.setComida(aldea.total_comida);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanos(aldea.nro_aldeanos_disponibles);  
                t_construc=(Motor_Juego.cont/50)+casa.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);
                e.insertar_LEF(new LEF("Casa",t_construc,e.pos));
                e.panel.repaint();
            }
            
        }else if (elemento.compareTo("Granja0")==0) {
            Granja granja = new Granja();
            Requerimientos r = Requerimiento.buscar_requerimiento("Granja",0);
            if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            {
                granja.Seleccionar_Localizacion(x_inicial+matriz_logica.coordenaX_a_Columna((int)x, x_inicial)*25, (y_inicial+matriz_logica.coordenadaY_a_Fila((int)y, y_inicial)*25)-(granja.Obtener_Alto()-(granja.alto*25)));
                vec_item_estaticos.add(granja);
                matriz_logica.colocar_edificio(matriz_logica.coordenadaY_a_Fila((int)y, y_inicial),matriz_logica.coordenaX_a_Columna((int)x, x_inicial),item.ancho,item.alto);
                aldea.total_oro-=r.costo_oro;
                e.setOro(aldea.total_oro);
                aldea.total_comida-=r.costo_comida;
                e.setComida(aldea.total_comida);
                aldea.nro_aldeanos_disponibles-=r.nro_aldeanos_requeridos;
                e.setAldeanos(aldea.nro_aldeanos_disponibles);  
                t_construc=(Motor_Juego.cont/50)+granja.tiempo;
                System.out.println("Tiempo Actual "+Motor_Juego.cont/50+" Tiempo de construccion "+t_construc);
                e.insertar_LEF(new LEF("Granja",t_construc,e.pos));
                e.panel.repaint();
            }
        }
        
    }
    
    public void crear_tienda(){        
        borrar_tienda();    
        //Precio Centro
        Requerimientos r;
        Boton boton;
        Centro Centro=new Centro();
        r = Requerimiento.buscar_requerimiento("Centro",0);
        if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Centro0");
        else
            boton = new Boton("NO-Centro0");
        boton.Seleccionar_Localizacion(16, 550);
        vec_botones.add(boton);
        
        //Precio Torre
        Torre tor=new Torre();
        r = Requerimiento.buscar_requerimiento("Torre",0);
        if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Torre0");
        else
            boton = new Boton("NO-Torre0");
        boton.Seleccionar_Localizacion(96, 550);
        vec_botones.add(boton);
        
//Precio Mercado
        Mercado mer=new Mercado();
        r = Requerimiento.buscar_requerimiento("Mercado",0);
        if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Mercado0");
        else
            boton = new Boton("NO-Mercado0");
        boton.Seleccionar_Localizacion(173, 550);
        
        vec_botones.add(boton);
        
        //Precio Guarnicion
        Guarnicion guar=new Guarnicion();
        r = Requerimiento.buscar_requerimiento("Guarnicion",0);
        if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
               boton = new Boton("Guarnicion0");
        else
                boton = new Boton("NO-Guarnicion0");
        boton.Seleccionar_Localizacion(252, 550);
        vec_botones.add(boton);
        
        //Precio Almacen        
        Almacen alm= new Almacen();
        r = Requerimiento.buscar_requerimiento("Almacen",0);
        if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Almacen0");
        else 
            boton = new Boton("NO-Almacen0");
        boton.Seleccionar_Localizacion(332, 550);
        vec_botones.add(boton);
        
        //Precio Cuartel
        Cuartel cua=new Cuartel();
        r = Requerimiento.buscar_requerimiento("Cuartel",cua.nivel);
        if(aldea.total_comida>=r.costo_comida && aldea.total_oro>=r.costo_oro && aldea.nro_aldeanos_disponibles>=r.nro_aldeanos_requeridos)
            boton = new Boton("Cuartel0");
        else
            boton = new Boton("NO-Cuartel0");
        boton.Seleccionar_Localizacion(415, 550);
        vec_botones.add(boton);
    }
    public void borrar_tienda(){
        vec_botones.clear();
        Boton boton = new Boton("Tienda");
        boton.Seleccionar_Localizacion(692, 542);
        vec_botones.add(boton);
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.BOTONES).getImage(), new Rectangulo(0, 0, 771,126) );
        obj.Seleccionar_Localizacion(0,466);
        vec_botones.add(obj);
    }
    public Objetos_Graficos Tipo_Item(String nombre){
        String clase= nombre.substring(0, nombre.length()-1);
        if(clase.compareTo("Almacen")==0)
            return new Almacen();
        else if (clase.compareTo("Casa")==0) {
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
        
        for(int i=1;i<vec_botones.size() && Ventana_tienda;i++){ 
                vec_botones.get(i).Dibujar(g);
        }
        //Pintar el personaje
        g.setColor(Color.BLACK);
        g.fillRect(0,0,80,15);
        g.setColor(Color.RED);
        g.drawString( "Tiempo: "+(Motor_Juego.cont/50) , 0, 10);
        
    }
    /**Metodo que actializa la escena y donde se realizan acciones logicas*/
    public void update(double timePassed){
            if(mario.murio==0){
            mario.Actualizar_Objeto_Grafico(timePassed);
            mario.Actualizar_PosicionX();
            
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
        }
            //Consultar en la LEF los eventos futuros 
           e.Consultar_LEF(Motor_Juego.cont/50);
            
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
        aldea.total_oro=7000;
        aldea.total_comida=5000;
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
       
       e = new Estadisticas(aldea.total_oro,aldea.total_comida,aldea.nro_aldeanos);    
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //Motor_Fisico.getInstance().mouse(e.getX(), e.getY());
        float pos_x = e.getX();
        float pos_y = e.getY();
        Objetos_Graficos dinamico;
        Boton b=new Boton();      
        
        for(int i=0;i<vec_item_estaticos.size() && !Ventana_tienda;i++){
            dinamico = vec_item_estaticos.get(i); 
            if((pos_x > dinamico.x && pos_x < dinamico.x + dinamico.Obtener_Ancho()) && (pos_y > dinamico.y && pos_y < dinamico.y + dinamico.Obtener_Alto())){
                if(dinamico instanceof Cuartel){
                    System.out.println("Selecciono Cuartel");
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
            AgregarElementosAldea(e.getX(),e.getY());
            agregar_elemento=false;        
        }
        
        for(int i=2;i<vec_botones.size() && Ventana_tienda;i++){
            dinamico = vec_botones.get(i);
            b=(Boton)vec_botones.get(i);
            if(!b.Nombre.substring(0,2).equals("NO") && (pos_x > dinamico.x && pos_x < dinamico.x + dinamico.Obtener_Ancho()) && (pos_y > dinamico.y && pos_y < dinamico.y + dinamico.Obtener_Alto())){
                //if(dinamico instanceof Boton){
                elemento=b.Nombre;  
                item = Tipo_Item(elemento);
                System.out.println(b.Nombre);
                agregar_elemento=true;
                Ventana_tienda=false;                    
                //}
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
            borrar_tienda();
            Ventana_tienda=false;
        }
    }
    
    

}
