/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_inteligenciai;

/**
 *
 * @author user
 */

 //CLASE DE INTELIGENCIA ARTIFICIAL PARA EL RATON


import java.math.*;

public class Inteligencia {
    //Algoritmo menor costo
    //Variables locales para establecer el peligro y la ocupacion.
    int peligro2[][];//para establecer las coordenadas de peligro
    int recorrido[][];//para evitar que se sobrepongan imagenes
    
    //posicion del queso y del raton locales
    int pratonl[][];
    int pquesol[][];
    int pr[][];//variable que almacena temporalmente la direccion de raton
    int pra[][];//variable que simula el movimiento del raton en su recorrido
    int GB; //variable gran bandera para ver si se encontro el queso
    int cA2;
    double proporcion;
    
    Inteligencia(){
    //Matriz que registra si una posicion se encuentra ocupada 
        //para evitar que las imagenes se sobrepongan
       //establecer cuadros de peligro              
        peligro2=new int[8][8];
        pquesol=new int[1][2];
        pratonl=new int[1][2];
        recorrido=new int[8][8];
        pr=new int[1][2];
        pra=new int[1][2];
        GB=0;
        cA2=0;
        proporcion=0.0;
        recorridoCero();//establecer los cuadros de recorrido
     //   imprimeValores();
    }
    
    void busqueda(int x,int y){
    }
    
    //Funcion que dice hacia que direccion se debe de mover el raton
    int aDonde(int x,int y,int x1,int y1){
       
    int m;
      //revision si la posicion del queso es la misma que la del raton          
       if(llegue(x,y,x1,y1)==1){
       System.out.println("llegue al queso");
      // retrazo.stop();
       //GB=10;    
       return 5;
       }else{
       if(x1==x){//movimiento horizontal en la misma fila       
    //System.out.println("");
       m=moverseY(y,y1);//si 0 a la izquierda 1 a la derecha 
       
       if(m==0){
       System.out.println("el raton se tiene que mover hacia la Izquierda");
       return 4;
       }else{
       System.out.println("el raton se tiene que mover hacia la Derecha");
       return 2;
       }
    }else{
    if(x1<x){//movimiento hacia arriba
    System.out.println("el raton se tiene que mover hacia Arriba");
    return 1;
    }else{//movimiento hacia abajo
    System.out.println("el raton se tiene que mover hacia Abajo");
    return 3;
    }
    }//fin arriba y abajo
       } //fin if movimiento horizontal        
   }//fin aDonde
    //funcion de apoyo para aDonde() 
    int moverseY(int a,int b){    
        if(b<a){
            return 0;
            }else{
            return 1;
        }//fin else2
    //fin else
    }//fin de la funcion moverY
    
    //Funcion que dice si se ha llegado al queso
    int llegue(int a,int b,int c,int d){
    if(a==c && b==d){
    return 1;
    }else{return 0;}
    }
    
    
    
    //calcula la diferencia entre las X
    int calculaX(){
    int n;
    if(pratonl[0][0]==pquesol[0][0]){//evalua si esta en la misma fila
        n=0;
    }else{
         n=pquesol[0][0]-pratonl[0][0];  
    }
    return n;
    }//fin calculaX
    
    //Calcula la diferencia entre las Y
    int calculaY(){
    int n;
    if(pratonl[0][1]==pquesol[0][1]){//evalua si esta en la misma fila
        n=0;
    }else{
         n=pquesol[0][1]-pratonl[0][1];  
    }
    return n;
    }//fin calculaY
    
    //Imprime las coordenadas de raton y queso
    void imprimeValores(){
    System.out.println("coordenada del raton:("+pratonl[0][0]+","+pratonl[0][1]+")");
    System.out.println("coordenada del queso:("+pquesol[0][0]+","+pquesol[0][1]+")");
    imprimeCalculos();//imprime la diferencia entre las X y las Y de las coordenadas
    movimiento();
    }
    
    void movimiento(){
    }
    
    //imprime la diferencia entre las X y las Y de las coordenadas
    void imprimeCalculos(){
    int a,b;
    a=calculaX();
    b=calculaY();
    System.out.println("Valor de X="+a);
    System.out.println("Valor de Y="+b);
    if(a==0){
    proporcion=0;
    }else{
    proporcion=(b/a);
    System.out.println("Valor de proporcion es: "+proporcion);
    }
    }
    
    //Establece las casillas de recorrido en 0 o vacias
    void recorridoCero(){
      
        for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				{
					//marcar con cero todas las posiciones
					recorrido[i][j]=0;
				}
    }
    
        
    void peligroTrampa2(int a,int b){
    peligro2[a][b]=1;
    }

    void peligroGato2(int c,int d){
        //c=filas
        //d=columnas
        
        switch(c){
            case 0://prinera fila
                switch(d){
                    case 0://priner columna
                        //p1
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c][d+1]=1;
                        peligro2[c+1][d]=1;
                        peligro2[c+1][d+1]=1;
                        break;
                    case 7://segunda columna
                        //p2
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c][d-1]=1;
                        peligro2[c+1][d-1]=1;
                        peligro2[c+1][d]=1;
                        break;
                    default://intermedias
                        //p5
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c][d-1]=1;
                        peligro2[c][d+1]=1;
                        peligro2[c+1][d-1]=1;
                        peligro2[c+1][d]=1;
                        peligro2[c+1][d+1]=1;
                        break;
                }//fin switch d
                break;
            case 7://ultima fila
                switch(d){
                    case 0://primer columna
                        //p4
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d]=1;
                        peligro2[c-1][d+1]=1;
                        peligro2[c][d+1]=1;
                        break;
                    case 7://ultima columna
                        //p3
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d-1]=1;
                        peligro2[c-1][d]=1;
                        peligro2[c][d-1]=1;
                        break;
                    default://intermedias
                        //p7
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d-1]=1;
                        peligro2[c-1][d]=1;
                        peligro2[c-1][d+1]=1;
                        peligro2[c][d-1]=1;
                        peligro2[c][d+1]=1;
                        break;
                }//fin switch d
                break;
            default:
                
                switch(d){
                    //p8
                    case 0:
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d]=1;
                        peligro2[c-1][d+1]=1;
                        peligro2[c][d+1]=1;
                        peligro2[c+1][d]=1;
                        peligro2[c+1][d+1]=1;
                        break;
                        
                    case 7:
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d-1]=1;
                        peligro2[c-1][d]=1;
                        peligro2[c][d-1]=1;
                        peligro2[c+1][d-1]=1;
                        peligro2[c+1][d]=1;
                        break;
                        
                    default:
                        peligro2[c][d]=1;//posicion del gato
                        peligro2[c-1][d-1]=1;
                        peligro2[c-1][d]=1;
                        peligro2[c-1][d+1]=1;
                        peligro2[c][d-1]=1;
                        peligro2[c][d+1]=1;
                        peligro2[c+1][d-1]=1;
                        peligro2[c+1][d]=1;
                        peligro2[c+1][d+1]=1;
                        break;
                }
                
                break;
        }//fin switch c
    }
    
			

    
    
}//Fin Clase Inteligencia.
