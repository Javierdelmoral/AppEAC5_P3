/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AppEAC5_P3;

/**
 *
 * @author Javier
 */
public class ClimaUtils {

    static final short ID_CIUTAT = 0;
    static final short ID_ANY = 1;
    static final short ID_TEMP = 0;
    static final short ID_PLUJA = 1;

    public static int posicioMaximaPluja(String[][] dCiutat, float[][] dClima) {
        float plujaMax = 0;
        int posMax = -1;
        for (int i = 0; i < dClima.length; i++) {
            if (!dCiutat[i][ID_CIUTAT].equals("")) {
                if (dClima[i][ID_PLUJA] > plujaMax) {
                    plujaMax = dClima[i][ID_PLUJA];
                    posMax = i;
                }
            }
        }
        return posMax;
    }

    /*Mètode que calcularà la mitja de temperatura per un any donat.*/
    public static float calculaMitjaTempAny(String[][] dCiutats, float[][] dClima, String any) {
        float sumaTemp = 0.0f;
        int numCiutats = 0;
        for (int i = 0; i < dCiutats.length; i++) {
            if (dCiutats[i][ID_ANY].equalsIgnoreCase(any)) {
                sumaTemp = sumaTemp + dClima[i][ID_TEMP];
                numCiutats = numCiutats + 1;
            }
        }
        if (numCiutats > 0) {
            return sumaTemp / numCiutats;
        } else {
            return 0.0f;
        }
    }

    public static int existeixCiutatAny(String[][] dCiutats, String ciutat, String any) {
        int posIndex = 0;
        boolean trobat = false;

        do {
            trobat = dCiutats[posIndex][ID_CIUTAT].equalsIgnoreCase(ciutat) && dCiutats[posIndex][ID_ANY].equalsIgnoreCase(any);
            if (!trobat) {
                posIndex++;
            }
        } while (!trobat && posIndex < dCiutats.length);

        return posIndex;
    }

    //Mètode per ordenar anys de ciutat escollida
    public static void ordenarPerAnys(String[][] dCiutats, float[][] dClima, String ciutat) {
        String[][] dadesCiutatsIntercanvi = new String[1][];
        float[][] dadesClimaIntercanvi = new float[1][];

        for (int j = 0; j < dCiutats.length; j++) {
            //Situem la 1a posició de la ciutat escollida
            if (dCiutats[j][ID_CIUTAT].equalsIgnoreCase(ciutat)) {
                for (int k = j; k < dCiutats.length; k++) {
                    //Situem la seguent posició de la mateixa ciutat 
                    if (dCiutats[k][ID_CIUTAT].equalsIgnoreCase(ciutat)) {
                        //Comencem la ordenació dels anys amb BubbleSort
                        if (Integer.parseInt(dCiutats[j][ID_ANY]) > Integer.parseInt(dCiutats[k][ID_ANY])) {
                            // Canviant dades de ciutat i any de posició
                            dadesCiutatsIntercanvi[0] = dCiutats[j].clone();
                            dCiutats[j] = dCiutats[k].clone();
                            dCiutats[k] = dadesCiutatsIntercanvi[0].clone();
                            // Canviant dades de clima de posició
                            dadesClimaIntercanvi[0] = dClima[j].clone();
                            dClima[j] = dClima[k].clone();
                            dClima[k] = dadesClimaIntercanvi[0].clone();
                        }
                    }
                }
            }
        }
    }

    public static void mostrarDadesCiutat(String[][] dCiutats, float[][] dClima, String ciutat) {

        System.out.println("-----------------------------------------------------------------");
        System.out.println("----------------------DADES CLIMATIQUES--------------------------");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("CIUTAT" + "      ANY" + " \tTEMPERATURA" + "  \tPLUJA");
        System.out.println("-----------------------------------------------------------------");

        for (int i = 0; i < dClima.length; i++) {
            if (dCiutats[i][ID_CIUTAT].equals(ciutat)) {
                System.out.print(dCiutats[i][ID_CIUTAT]);
                System.out.print("         ");
                System.out.print(dCiutats[i][ID_ANY]);
                System.out.print("         ");
                System.out.print(String.format("%.02f", dClima[i][ID_TEMP]));
                System.out.print("          ");
                System.out.println(String.format("%.02f", dClima[i][ID_PLUJA]));
            }
        }
    }

    //Mètode per buscar primer espai buit a l'array
    public static int buscaPrimerEspai(String[][] dCiutats) {
        int index = 0;
        boolean trobat = false;
        while (index < dCiutats.length && !trobat) {
            if (dCiutats[index][ID_CIUTAT].equals("")) {
                trobat = true;
            } else {
                index++;
            }
        }

        return index;
    }
}
