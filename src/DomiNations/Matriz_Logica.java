/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomiNations;

/**
 *
 * @author Tyson
 */
public class Matriz_Logica {
     char [][] matriz_logica={  {'1','0','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'1','0','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'1','0','0','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0','0','0'},
                                {'1','0','1','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0','0','0'},
                                {'1','0','0','0','0','0','0','0','0','0','1','0','0','0','0','0','0','0','0','0','0','0'},
                                {'1','0','1','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
                                {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'},
};

    
    public Matriz_Logica(){
       
                 }
    public void imprimir(){
        for (int x=0; x < matriz_logica.length; x++) {
            for (int y=0; y < matriz_logica[x].length; y++) {
                System.out.print (matriz_logica[x][y]+" ");
            }
            System.out.println(" ");
        }
    }
        /**Verifica la disponibilidad de espacios vacios para construir en el mapa
     *@param fila pos x de esquina superior izquierda 
     *@param columna pos y de esquina superior izquierda
     *@param ancho ancho
     *@param alto alto */
    public int verificar_disponibilidad(int fila,int columna,int ancho,int alto){
        int ocupado=0;
        //validacion para que los edificios no se salgan del alto
        if(fila+alto>18){
              ocupado=1;
           //   System.out.println("Edificio se sale de los bordes de alto");
          }
        //validacion para que los edificios no se salgan del ancho
        if(columna+ancho>22){
            ocupado=1;
            //System.out.println("Edificio se sale de los bordes de ancho ");
        }
        if(ocupado==0){
            for (int x=fila; x < fila+alto; x++) {
                for (int y=columna; y < columna+ancho; y++) {                 
                    if(matriz_logica[x][y]=='1'||matriz_logica[x][y]=='3'){
                     //hay un obstaculo posicion ocupada para el edificio
                        ocupado=1;
                    }   
                }
            }
        }
        return ocupado;
      }
     /**Coloca un edificio en el mapa logico 
     *@param fila pos x de esquina inferior izquierda 
     *@param columna pos y de esquina inferior izquierda
     *@param ancho ancho
     *@param alto alto */
    
    public void colocar_edificio(int fila,int columna,int ancho,int alto){
        for (int x=fila; x < fila+alto; x++) 
            for (int y=columna; y < columna+ancho; y++)
                matriz_logica[x][y]='1';
    }
    public void colocar_Recurso(int fila,int columna,int ancho,int alto){
        for (int x=fila; x < fila+alto; x++) 
            for (int y=columna; y < columna+ancho; y++)
                matriz_logica[x][y]='3';
    }
    
    /**Transforma un coordena yx de punto, a una posicion de fila en la matriz logica 
     *@param pos_x coordenada en xx del punto 
     *@param x_inicio valor a descontar hasta donde comienza la matiz logica en la imagen */
    public int coordenaX_a_Columna(int pos_x, int x_inicio){
        //System.out.println("C:"+(int)(pos_x - x_inicio)/25);    
        return (pos_x - x_inicio)/25;
    }
    /**Transforma un coordena yx de punto, a una posicion de fila en la matriz logica 
     *@param pos_y coordenada en y del punto 
     *@param y_inicio valor a descontar hasta donde comienza la matiz logica en la imagen */
    public int coordenadaY_a_Fila(int pos_y, int y_inicio){
        //System.out.println("F:"+(int)(pos_y - y_inicio)/25);
            return (pos_y - y_inicio)/25;
    }
}
