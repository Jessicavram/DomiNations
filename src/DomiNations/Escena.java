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
     boolean edoElemento=false;
    
    /**Estado de la escena*/
    boolean Ventana_tienda=false;
    boolean agregar_elemento=false;
    /**Lista de requerimientos para crear o mejor item*/
    Lista_de_Requerimientos Requerimiento;
    
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
        mario.Dibujar(g);
        g.drawString( "Tiempo: "+(Motor_Juego.cont/50) , 0, 10);
        
    }
    /**Metodo que actializa la escena y donde se realizan acciones logicas*/
    public void update(double timePassed){
            if(mario.murio==0){
            mario.Actualizar_Objeto_Grafico(timePassed);
            mario.Actualizar_PosicionX();
            
            //actualizar los item que no se mueven en la aldea
            for(int i=0;i<vec_item_estaticos.size();i++){
                if(vec_item_estaticos.get(i).getY()>=310 || vec_item_estaticos.get(i).getX()<=-300 || vec_item_estaticos.get(i).borrar){
                    //eliminar los enemigos que se caigan
                    Motor_Fisico.getInstance().borrar_animado(vec_item_estaticos.get(i) );
                    vec_item_estaticos.remove(i);
                }else
                    vec_item_estaticos.get(i).Actualizar_Objeto_Grafico(timePassed);
            }
            //actualizar los item que se mueven en la aldea
            for(int i=0;i<vec_item_con_movimiento.size();i++){
                if(vec_item_con_movimiento.get(i).getY()>=310 || vec_item_con_movimiento.get(i).getX()<=-16 || vec_item_con_movimiento.get(i).borrar){
                    Motor_Fisico.getInstance().borrar_animado(vec_item_con_movimiento.get(i) );
                    vec_item_con_movimiento.remove(i);
                }else
                    vec_item_con_movimiento.get(i).Actualizar_Objeto_Grafico(timePassed);
            }
            //matar a mario por caerse
                if(mario.y>=310){
                    Cargar_Sonidos.obtener_instancia().Reproducir_pistas(Cargar_Sonidos.MUERTE, false, false);
                    mario.murio=1;  
                    mario.vidas--;
                }  
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
      
    public void crearNivel1(){       
        vec_objetos_fondo.clear();
        vec_item_estaticos.clear();
        vec_item_con_movimiento.clear();
        Motor_Fisico.getInstance().clearPhysicsEngine();
        mario.Modificar(1, 0, 266);
        mario.Seleccionar_Localizacion(0,250);
        Motor_Fisico.getInstance().addDynamicObject(mario);   

        //NIVEL 1
        
        //Imagen de Fondo
        Objetos_Inanimados obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.FONDO_ALDEA).getImage(), new Rectangulo(0, 0, 767,592) );
        obj.Seleccionar_Localizacion(0,0);
        vec_objetos_fondo.add(obj);
        
        obj = new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.BOTONES).getImage(), new Rectangulo(0, 0, 771,126) );
        obj.Seleccionar_Localizacion(0,466);
        vec_objetos_fondo.add(obj);
        
        //agregar botones de precios por edificio
        Boton boton = new Boton("Tienda");
        boton.Seleccionar_Localizacion(692, 542);
        vec_botones.add(boton);
        
        //Precio Centro
        boton = new Boton("Centro0");
        boton.Seleccionar_Localizacion(16, 550);
        vec_botones.add(boton);
        //Precio Torre
        boton = new Boton("Torre0");
        boton.Seleccionar_Localizacion(96, 550);
        vec_botones.add(boton);
        //Precio Mercado
        boton = new Boton("Mercado0");
        boton.Seleccionar_Localizacion(173, 550);
        
        vec_botones.add(boton);
        //Precio Guarnicion
        boton = new Boton("Guarnicion0");
        boton.Seleccionar_Localizacion(252, 550);
        vec_botones.add(boton);
        //Precio Almacen
        boton = new Boton("Almacen0");
        boton.Seleccionar_Localizacion(332, 550);
        vec_botones.add(boton);
        
        //Precio Cuartel
        boton = new Boton("Cuartel0");
        boton.Seleccionar_Localizacion(415, 550);
        vec_botones.add(boton);
        
        
        //Vidas Mario
        if(mario.vidas>0){
            obj = new  Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(357,105,357+(mario.vidas*19),121));
            obj.Seleccionar_Localizacion(200, 2);
            vec_objetos_fondo.add(obj);
            Motor_Fisico.getInstance().AnadirInanimado(obj);
        }
        //Insertando soldado
        Soldado sol = new Soldado();
        sol.Seleccionar_Localizacion(250, 300);
        vec_item_con_movimiento.add(sol);
        
//Insertando soldado
        Soldado2 a= new Soldado2();
        a.Seleccionar_Localizacion(300, 300);
        vec_item_con_movimiento.add(a);
        
        //insertando cuartel
        Requerimientos x= Requerimiento.buscar_requerimiento("Cuartel",0);
        x.mostrar_condiciones();
        Cuartel cuartel = new Cuartel();
        cuartel.Seleccionar_Localizacion(170, 80);
        vec_item_estaticos.add(cuartel);
        
        //Insertando mercado
        
        /*Mercado mer=new Mercado();
        mer.Seleccionar_Localizacion(210, 300);
        vec_item_estaticos.add(mer);        
       */ 
        
          //Insertando Casa
        Casa casa=new Casa();
        casa.Seleccionar_Localizacion(510,200);
        vec_item_estaticos.add(casa);
        
        //Insertando Granja 
        Granja granja = new  Granja();
        granja.Seleccionar_Localizacion(250, 30);
        vec_item_estaticos.add(granja);
        
        //Insertando Recuadro de posicion
        Recuadro cuadro =new Recuadro();
        cuadro.Seleccionar_Localizacion(50, 50);
        vec_item_estaticos.add(cuadro);
        /*Probando la matriz logica*/
        Matriz_Logica m=new Matriz_Logica();
        //m.imprimir();
        System.out.println(m.verificar_disponibilidad(16, 19, 3, 2));
        if(m.verificar_disponibilidad(16, 19,3, 2)==0){
            m.colocar_edificio(16, 19, 3, 2);
        }
        System.out.println(m.verificar_disponibilidad(17, 21, 3, 2));
        //m.imprimir();
        /**************************************/
       
        //PISO NIVEL 1  
        obj= new Objetos_Inanimados(Cargar_Imagenes.obtener_instancia().obtener_imagen(Cargar_Imagenes.INANIMADOS).getImage(), new Rectangulo(0,0 , 57, 31));
        obj.Seleccionar_Localizacion(0, 268);
        vec_objetos_fondo.add(obj);
        Motor_Fisico.getInstance().AnadirInanimado(obj); 
        
       //
       
       e = new Estadisticas(1500,1000,7);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //Motor_Fisico.getInstance().mouse(e.getX(), e.getY());
        float pos_x = e.getX();
        float pos_y = e.getY();
        Point punto=MouseInfo.getPointerInfo().getLocation();//este me da la posicion pero en toda la pantalla
        //System.out.println("Posicion del evento "+e.getX()+"Posicioin del puntero "+punto.x);
        // System.out.println("Posicion del evento "+e.getY()+"Posicioin del puntero "+punto.y);
        //System.out.println("X"+pos_x+" "+"Y"+pos_y);
        Objetos_Graficos dinamico;
        Boton b=new Boton();
       
        
        
        if(pos_x>692 && pos_y>542 && !Ventana_tienda){
            System.out.println("Tienda");
            Ventana_tienda=true;
            pintar_menu();
        }
        
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
        
        for(int i=1;i<vec_botones.size() && Ventana_tienda;i++){
            dinamico = vec_botones.get(i);
            b=(Boton)vec_botones.get(i);
            if((pos_x > dinamico.x && pos_x < dinamico.x + dinamico.Obtener_Ancho()) && (pos_y > dinamico.y && pos_y < dinamico.y + dinamico.Obtener_Alto())){
                if(dinamico instanceof Boton){
                    elemento=b.Nombre;
                    System.out.println(b.Nombre);
                    agregar_elemento=true;
                    Ventana_tienda=false;
                }
            }
        }
        //Agregando elementos a la aldea
        if(agregar_elemento==true){
            
         AgregarElementosAldea(e.getX(),e.getY());
        
        
        }
        
        
        
    }
    
    public void AgregarElementosAldea(float x,float y){
        
        
        if(elemento.compareTo("Tienda")==0){
       
        }
        if(elemento.compareTo("O100")==0){
       
        }
        if(elemento.compareTo("Cuartel0")==0){
       
        }
        if(elemento.compareTo("Almacen0")==0){
                //Insertando almacen
           Almacen alm= new Almacen();
           alm.Seleccionar_Localizacion((int)x,(int)y);
           vec_item_estaticos.add(alm);
       
        }
        if(elemento.compareTo("Torre0")==0){
             //Insertando torre
            Torre tor= new Torre();
            tor.Seleccionar_Localizacion((int)x,(int)y);
            vec_item_estaticos.add(tor);
       
        }
        if(elemento.compareTo("Mercado0")==0){
            Mercado mer=new Mercado();
            mer.Seleccionar_Localizacion((int)x,(int)y);
            vec_item_estaticos.add(mer);  
       
        }
        if(elemento.compareTo("Guarnicion0")==0){
            //Insertando guarnicion
            Guarnicion guar=new Guarnicion();
            guar.Seleccionar_Localizacion((int)x,(int)y);
            vec_item_estaticos.add(guar);
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseMoved(MouseEvent me) {
       // System.out.println("la posicion actual del mouse es X= "+me.getX()+" Y="+me.getY());
        //revisar el vector de objetos
         Objetos_Graficos obj;
        for(int i=0;i<vec_item_estaticos.size();i++){
           obj=vec_item_estaticos.get(i);
           if(obj instanceof Recuadro){
               vec_item_estaticos.remove(i);
           } 
        }
        Recuadro cuadro =new Recuadro();
        cuadro.Seleccionar_Localizacion(me.getX(), me.getY());
        vec_item_estaticos.add(cuadro);
    }
    
    

}
