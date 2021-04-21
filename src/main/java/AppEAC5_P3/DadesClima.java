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
public class DadesClima {

    static final short MIN_ANY = 2021;
    static final short MAX_ANY = 2025;
    static final short NUM_CIUTATS = 5;
    static final short NUM_ANYS = 5;
    static final short LIMIT_ARRAY = (NUM_CIUTATS * NUM_ANYS) - 1;
    static final String EMPTY_ROW = "";

    //Matriu que emmagatzemar dades de ciutat i any
    public String[][] dadesCiutats = new String[NUM_CIUTATS * NUM_ANYS][2];

    //Matriu que emmagatzemar dades de temperatura i pluja acumulada
    public float[][] dadesClima = new float[NUM_CIUTATS * NUM_ANYS][2];

    //Matriu noms i codis de Ciutats
    public static final String[][] ciutats = {
        {"Barcelona", "BCN"},
        {"Lisboa", "LIS"},
        {"Londres", "LON"},
        {"Paris", "PAR"},
        {"Munich", "MUN"}
    };

    //Matriu del anys enregistrats
    public static String[] anys = {"2021", "2022", "2023", "2024", "2025"};
    
    public void iniciarArrayCiutats(){
        //Inicialitzem l'array de ciutats amb EMPTY_ROW
        for (int i = 0; i < DadesClima.NUM_CIUTATS * DadesClima.NUM_ANYS; i++) {
            dadesCiutats[i][0] = DadesClima.EMPTY_ROW;
            dadesCiutats[i][1] = DadesClima.EMPTY_ROW;
        }
    }
}
