/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package proyecto_inteligenciai;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
//import javax.swing.JTextArea;

/**
 *
 * @author 
 */
public class Tablero extends javax.swing.JInternalFrame {

    /**
     * Creates new form Tablero
     */
    
    //Atributos
	JButton botones [][];//arreglo para los botones con imagen
         //Posicion de las piezas
        int pgato1[][];
        int pgato2[][];
        int pqueso[][];
        int ptrampa[][];
        int praton[][];
        
        int peligro[][];//para establecer las coordenadas de peligro
        int ocupado[][];//para evitar que se sobrepongan imagenes
     
    //Objeto de la clase movimiento para control
        Movimiento mueve=new Movimiento();
    
    public Tablero() {
        //donde se colocan todas las graficas e imagenes
        botones=new JButton [8][8];
        
        //Matriz que registra si una posicion se encuentra ocupada 
        //para evitar que las imagenes se sobrepongan
	ocupado=new int[8][8];
        
        //Establece las casillas de ocupado en 0 o vacias
        for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				{
					//marcar con cero todas las posiciones
					ocupado[i][j]=0;
				}
        
        
        //establecer cuadros de peligro              
                 peligro=new int[8][8];
                 /*lo que se busca realizar con la matriz peligro es 
                 * marcar los campos que son peligrosos para el raton
                 *  y de esta manera realizar la evaluacion de cada
                 * movimiento al cuadro que se quiere desplazar y si 
                 * es igual a 1, entonces existe peligro y no se movera 
                 * a ese cuadro.
                 */
                      
        //Panel de los botones
                
		JPanel panelMedio=new JPanel(new GridLayout(8,8));
		//	Crear y colocar botones
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				{
					//Crear boton
					botones [i][j]=new JButton();
                                        botones[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nada.gif")));
					//	Colocar en el panel
					panelMedio.add(botones[i][j]);
				}
                
                //panelBotones.add(panelMedio,"center");
		
                this.add(panelMedio,"Center");	
                //this.add(imprime,"RIGHT");
               // setTitle("El Raton Inteligente");	
	        setResizable(false);
	        setSize(400,400);//tamaño de la ventana y de los botones
		setVisible(true);
    //Colocar las piezas      
                dibujaG1();
                dibujaG2();
                dibujaT();
                dibujaQ();
                dibujaR();
                
                
                
                
                
        initComponents();
    }
    
    void dibujaG1(){
        int a,b;
        a=aleatorio();
        b=aleatorio();
        pgato1=new int[1][2];
        pgato1[0][0]=a;
        pgato1[0][1]=b;
        peligroGato(a,b);
        ocupado[a][b]=1;
        botones[a][b].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gato.gif")));
}
    
    void dibujaG2(){
    int a,b;
//colocar gato2
                int bandera=0;
                while(bandera==0){
                    System.out.println("Colocando gato2");
                    a=aleatorio();
                    b=aleatorio();
                if(disponible(a,b)==1){//si es 0 no esta disponible
                   bandera=1;
                   pgato2=new int[1][2];
                   pgato2[0][0]=a;
                   pgato2[0][1]=b;
                   botones[a][b].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/gato.gif")));
                   peligroGato(a,b);
                   ocupado[a][b]=1;
                   }else{
                System.out.println("posicion no disponible");
                }
                }
}
    
    void dibujaT(){
        int a,b,bandera;
    //colocar trampa
                bandera =0;
                while(bandera==0){
                    System.out.println("Colocando trampa");
                    a=aleatorio();
                    b=aleatorio();
                if(disponible(a,b)==1){//si es 0 no esta disponible
                   bandera=1;
                   ocupado[a][b]=1;
                   ptrampa=new int[1][2];
                   ptrampa[0][0]=a;
                   ptrampa[0][1]=b;
                   botones[a][b].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/trampa.gif")));
                   peligroTrampa(a,b);
                }else{
                System.out.println("posicion trampa no disponible");
                }
                }
    }
    
    void dibujaQ(){
    int a,b,bandera;
                //colocar ques
                a=0;
                b=0;
                bandera=0;
                 while(bandera==0){
                     System.out.println("Colocando queso");
                    a=aleatorio();
                    b=aleatorio();
                if(disponible(a,b)==1 && peligro[a][b]==0){//si es 0 no esta disponible y  si peligro =0 no hbay peligro
                   bandera=1;
                   ocupado[a][b]=1;
                   pqueso=new int[1][2];
                   pqueso[0][0]=a;
                   pqueso[0][1]=b;
                   botones[a][b].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/queso.gif")));
                   }else{
                System.out.println("posicion queso no disponible");
                }//fin else
                }//fin while
    }//fin dibujaQ
    
    void dibujaR(){
     int a,b,bandera;
                //colocar raton
                a=0;
                b=0;
                bandera=0;
                 while(bandera==0){
                     System.out.println("Colocando raton");
                    a=aleatorio();
                    b=aleatorio();
                if(disponible(a,b)==1 && peligro[a][b]==0){//si es 0 no esta disponible y  si peligro =0 no hbay peligro
                   bandera=1;
                   ocupado[a][b]=1;
                   praton=new int[1][2];
                   praton[0][0]=a;
                   praton[0][1]=b;
                   botones[a][b].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/raton.gif")));
                   
                   }else{
                System.out.println("posicion raton no disponible");
                }
                }
    }
    
    
    
    //Generar numero aleatorio
    int aleatorio(){
        int x=0;
    	double x1=0;
    	x1=Math.random()*8;//tamaño maximo 8
        x=(int)x1;
    	System.out.println(x);
        return x;
    	}
    
    
    //para ver si la posicion esta disponible
    int disponible(int h,int v){
        if(peligro[h][v]==1){
        return 0;
        }else{
        return 1;}
    }
    
    //para establecer el peligro de trampa
     void peligroTrampa(int c,int d){
        //c=filas
        //d=columnas
    peligro[c][d]=1;//posicion trampa
    }
    //para establecer peligro de gato
    void peligroGato(int c,int d){
        //c=filas
        //d=columnas
        
        switch(c){
            case 0://prinera fila
                switch(d){
                    case 0://priner columna
                        //p1
                        peligro[c][d]=1;//posicion del gato
                        peligro[c][d+1]=1;
                        peligro[c+1][d]=1;
                        peligro[c+1][d+1]=1;
                        break;
                    case 7://segunda columna
                        //p2
                        peligro[c][d]=1;//posicion del gato
                        peligro[c][d-1]=1;
                        peligro[c+1][d-1]=1;
                        peligro[c+1][d]=1;
                        break;
                    default://intermedias
                        //p5
                        peligro[c][d]=1;//posicion del gato
                        peligro[c][d-1]=1;
                        peligro[c][d+1]=1;
                        peligro[c+1][d-1]=1;
                        peligro[c+1][d]=1;
                        peligro[c+1][d+1]=1;
                        break;
                }//fin switch d
                break;
            case 7://ultima fila
                switch(d){
                    case 0://primer columna
                        //p4
                        peligro[c][d]=1;//posicion del gato
                        peligro[c-1][d]=1;
                        peligro[c-1][d+1]=1;
                        peligro[c][d+1]=1;
                        break;
                    case 7://ultima columna
                        //p3
                        peligro[c][d]=1;//posicion del gato
                        peligro[c-1][d-1]=1;
                        peligro[c-1][d]=1;
                        peligro[c][d-1]=1;
                        break;
                    default://intermedias
                        //p7
                        peligro[c][d]=1;//posicion del gato
                        peligro[c-1][d-1]=1;
                        peligro[c-1][d]=1;
                        peligro[c-1][d+1]=1;
                        peligro[c][d-1]=1;
                        peligro[c][d+1]=1;
                        break;
                }//fin switch d
                break;
            default:
                
                switch(d){
                    //p8
                    case 0:
                        peligro[c][d]=1;//posicion del gato
                        peligro[c-1][d]=1;
                        peligro[c-1][d+1]=1;
                        peligro[c][d+1]=1;
                        peligro[c+1][d]=1;
                        peligro[c+1][d+1]=1;
                        break;
                        
                    case 7:
                        peligro[c][d]=1;//posicion del gato
                        peligro[c-1][d-1]=1;
                        peligro[c-1][d]=1;
                        peligro[c][d-1]=1;
                        peligro[c+1][d-1]=1;
                        peligro[c+1][d]=1;
                        break;
                        
                    default:
                        peligro[c][d]=1;//posicion del gato
                        peligro[c-1][d-1]=1;
                        peligro[c-1][d]=1;
                        peligro[c-1][d+1]=1;
                        peligro[c][d-1]=1;
                        peligro[c][d+1]=1;
                        peligro[c+1][d-1]=1;
                        peligro[c+1][d]=1;
                        peligro[c+1][d+1]=1;
                        break;
                }
                
                break;
        }//fin switch c

   /* peligro[c][d]=1;//posicion del gato
    peligro[c-1][d-1]=1;
    peligro[c-1][d]=1;
    peligro[c-1][d+1]=1;
    peligro[c][d-1]=1;
    peligro[c][d+1]=1;
    peligro[c+1][d-1]=1;
    peligro[c+1][d]=1;
    peligro[c+1][d+1]=1;*/
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tablero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tablero().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
