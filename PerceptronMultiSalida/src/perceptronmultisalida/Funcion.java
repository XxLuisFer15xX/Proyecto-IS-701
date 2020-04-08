/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perceptronmultisalida;

/**
 *
 * 
 */
public class Funcion {
    // Funcion Activacion
    // <====={Funcion para y_inj}=====>
    public static void y_inj(
            Perceptron p,
            TablaEntrenamiento t,
            int nFilas,
            int nEntradas,
            int nSalidas,
            int fila,
            int salida
    ){
        int net = 0;
        if (fila == 0){
            net = p.getWb()[nFilas-1][salida] * t.getTablaBias()[fila][salida];
        }else{
            net = p.getWb()[fila-1][salida] * t.getTablaBias()[fila][salida];
        }
        for ( int k = 0 ; k < nEntradas ; k++ ){
            if (fila == 0){
                net += ( p.getW()[nFilas-1][k][salida] * t.getTablaEntradas()[fila][k] );
            }else{
                net += ( p.getW()[fila-1][k][salida] * t.getTablaEntradas()[fila][k] );
            }
        }
        p.setY_inj(net, fila, salida);
    }
    
    // <====={Funcion para yj}=====>
    public static void yj(
            Perceptron p,
            int nFilas,
            int nSalidas,
            int fila,
            int salida,
            int teta
    ){
        
        int net = p.getY_inj()[fila][salida];
        int out = 0;
        if (net > teta){
            out = 1;
        } else if (   (-1)*teta <= net   &&   net <= teta   ){
            out = 0;
        }else if ( net < (-1)*teta ){
            out = -1;
        }
        p.setYj(out, fila, salida);
    }
    
    public static void Ejecutar(
        Perceptron p,
        TablaEntrenamiento t,
        int teta
    ){
        // Prueba de los pesos
        int Y_inj = 0;
        int Yj = 0;
        System.out.println("Y_inj = (bj) + Sumatoria(xi*wij)");
        for ( int j = 0 ; j < p.getnY() ; j++ ){
            // Encabezado
            System.out.println();
            System.out.println("Y_in" + (j+1) + " = (b" + j+1 + ") + Sumatoria(xi*wi" + j+1 + ")\t\t\t\tt"+ j+1);    
            for ( int i = 0 ; i < p.getnFilas() ; i++ ){
                Y_inj = t.getTablaBias()[i][j] * p.getWb()[i][j];
                System.out.print("Y_in" + (j+1) + " = "
                + "(" + t.getTablaBias()[i][j] + ")"
                + "(" + p.getWb()[i][j] + ")"
                + " + [") ;

                Y_inj += t.getTablaEntradas()[i][0] * p.getW()[i][0][j];
                System.out.print(" (" + t.getTablaEntradas()[i][0] + ")"
                + "(" + p.getW()[i][0][j] + ")");
                for ( int k = 1 ; k < p.getnX() ; k++ ){
                    Y_inj += t.getTablaEntradas()[i][k] * p.getW()[i][k][j];
                    System.out.print(" + (" + t.getTablaEntradas()[i][k] + ")"
                    + "(" + p.getW()[i][k][j] + ")");
                }
                System.out.print(" ] = " + Y_inj);
                
                System.out.print( "\t\t " + "t" + (j+1) + " = ");
                if (Y_inj > teta){
                    Yj = 1;
                } else if (   (-1)*teta <= Y_inj   &&   Y_inj <= teta   ){
                    Yj = 0;
                }else if ( Y_inj < (-1)*teta ){
                    Yj = -1;
                }
                System.out.println(Yj);
            }
            System.out.println();
        }
    }
    
    
    public static String EjecutarAprendisaje(
        Perceptron p,
        TablaEntrenamiento t,
        int[] entradas
    ){
        return "";
    }
}
