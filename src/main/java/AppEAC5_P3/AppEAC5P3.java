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
import java.text.ParseException;
import java.util.Random;

public class AppEAC5P3 {

    static final String MISSATGE_TRII_OPCIO = "\nTrii una opció";

    static final String MISSATGE_CODI_CIUTAT = "\nSobre quina ciutat vol introduir les dades.";
    static final String MISSATGE_CODI_ANY = "\nSobre quin any vol introduir les dades.";

    static final String MISSATGE_MAN_O_AUT = "\nCom vols ingressar les dades, MANUALMENT(1) o AUTOMATICAMENT(2)?";
    static final String INGRES_AUTOMATIC = "\n\n--------------INGRÉS AUTOMÀTIC DE DADES CLIMÀTIQUES--------------";
    static final String INGRES_MANUAL = "\n\n--------------INGRÉS MANUAL DE DADES CLIMÀTIQUES--------------";
    static final String MISSATGE_TEMP = "\nIntrodueix la temperatura mitja de l'any.";
    static final String MISSATGE_PLUJA = "\nIntrodueix la pluja acumulada de l'any.";
    static final String MISSATGE_INGRES_OK = "Dades climàtiques correctament introduides";
    static final String MISSATGE_INGRES_KO = "\nDades climàtiques no vàlides";
    static final String MISSATGE_NOTROBAT = "\nNo existeixen dades climàtiques de la ciutat o any seleccionats";
    static final String MISSATGE_MITJANA = "\nLa mitjana de l'any ";
    static final String MISSATGE_MAXPLUJA = "\nEl valor de pluja màxim ha estat a la ciutat de ";

    static final String MISSATGE_ERROR_COLISIO = "Error - Dades de ciutat i any ja existent:";
    static final String MISSATGE_ERROR_NOESPAI = "Error - Espai insuficient:";
    static final String MISSATGE_NO_DADES = "Error - No hi ha dades";

    static final String MISSATGE_COMIAT = "Fins aviat!";

    public static void main(String[] args) throws ParseException {
        AppEAC5P3 prg = new AppEAC5P3();
        prg.inici();
    }

    private void inici() {
        DadesClima dades_Clima = new DadesClima();
        int opcio;

        //Inicialitzem l'array de ciutats amb el metode propi de DadesClima
        dades_Clima.iniciarArrayCiutats();

        String[] menuPrincipal = {"GESTIÓ DADES CLIMÀTIQUES",
            "1. Introduir dades ciutat",
            "2. Modificar dades ciutat",
            "3. Llistar dades climàtiques per ciutat.",
            "4. Mostra mitja temperatura per any",
            "5. Mostra ciutat-any màxima pluja acumulada",
            "6. Sortir"
        };

        //Arranquem el menu de selecció
        do {
            UtilsES.mostrarMenu(menuPrincipal);
            opcio = UtilsES.demanarEnter(MISSATGE_TRII_OPCIO, UtilsES.MISSATGE_ERROR_LECTURA);
            switch (opcio) {
                case 1:
                    escollirComIngressarDades(dades_Clima.dadesCiutats, dades_Clima.dadesClima);
                    break;
                case 2:
                    opcioModifDadesCiutat(dades_Clima.dadesCiutats, dades_Clima.dadesClima);
                    break;
                case 3:
                    opcioLlistarDadesCiutat(dades_Clima.dadesCiutats, dades_Clima.dadesClima);
                    break;
                case 4:
                    opcioMitjaTemp(dades_Clima.dadesCiutats, dades_Clima.dadesClima);
                    break;
                case 5:
                    opcioMaximaPluja(dades_Clima.dadesCiutats, dades_Clima.dadesClima);
                    break;
                case 6:
                    System.out.println(MISSATGE_COMIAT);
                    break;
                default:
                    System.out.println(UtilsES.MISSATGE_ERROR_LECTURA);
                    break;
            }
        } while (opcio != 6);
    }

    public boolean registraDadesClima(String[][] dCiutats, float[][] dClima, String ciutat, String any, float temp, float pluja) {
        if (ClimaUtils.existeixCiutatAny(dCiutats, ciutat, any) >= dCiutats.length) {
            int posicio = ClimaUtils.buscaPrimerEspai(dCiutats);
            if (posicio < dCiutats.length) {
                dCiutats[posicio][ClimaUtils.ID_CIUTAT] = ciutat;
                dCiutats[posicio][ClimaUtils.ID_ANY] = any;
                dClima[posicio][ClimaUtils.ID_TEMP] = temp;
                dClima[posicio][ClimaUtils.ID_PLUJA] = pluja;
                return true;
            } else {
                UtilsES.mostrarMissatge(MISSATGE_ERROR_NOESPAI);
                return false;
            }
        } else {
            UtilsES.mostrarMissatge(MISSATGE_ERROR_COLISIO + ciutat + " " + any);
            return false;
        }
    }

    //METODE per ESCOLLIR COM INGRESSAR dades als arrays i procedir al REGISTRE DE DADES
    public void escollirComIngressarDades(String[][] dadesCiutats, float[][] dadesClima) {
        int inputUser;
        boolean finalitzat = false;

        if (dadesCiutats[DadesClima.LIMIT_ARRAY][ClimaUtils.ID_CIUTAT].equalsIgnoreCase(DadesClima.EMPTY_ROW)) {
            while (!finalitzat) {

                //User escogeix com fer ingres de dades
                inputUser = UtilsES.demanarEnter(MISSATGE_MAN_O_AUT, UtilsES.MISSATGE_ERROR_LECTURA);
                switch (inputUser) {
                    case 1:
                        ingressarDadesMan(dadesCiutats, dadesClima);
                        finalitzat = true;
                        break;
                    case 2:
                        ingressarDadesAut(dadesCiutats, dadesClima);
                        finalitzat = true;
                        break;
                    default:
                        System.out.println(UtilsES.MISSATGE_ERROR_LECTURA);
                        break;
                }
            }
        } else {
            System.out.println(MISSATGE_ERROR_NOESPAI + " " + dadesCiutats.length + " espais ocupats!");
        }
    }

    //Mètode PER INGRESSAR MANUALMENT
    public void ingressarDadesMan(String[][] dadesCiutat, float[][] dadesClima) {
        String cCiutat = UtilsES.demanaCiutat(DadesClima.ciutats, MISSATGE_CODI_CIUTAT);
        String cAny = UtilsES.demanaAnyRang(MISSATGE_CODI_ANY, DadesClima.MIN_ANY, DadesClima.MAX_ANY);
        float temp = UtilsES.demanarFloat(MISSATGE_TEMP, UtilsES.MISSATGE_ERROR_LECTURA);
        float pluja = UtilsES.demanarFloat(MISSATGE_PLUJA, UtilsES.MISSATGE_ERROR_LECTURA);

        if (registraDadesClima(dadesCiutat, dadesClima, cCiutat, cAny, temp, pluja)) {
            UtilsES.mostrarMissatge(MISSATGE_INGRES_OK + " per " + cCiutat + " - " + cAny);
        } else {
            UtilsES.mostrarMissatge(MISSATGE_INGRES_KO);
        }
    }

    //Mètode PER INGRESSAR AUTOMATICAMENT
    public void ingressarDadesAut(String[][] dadesCiutats, float[][] dadesClima) {
        int iCiu, iAny;
        float tempRnd, plujaRnd;
        boolean res;

        System.out.println(INGRES_AUTOMATIC);

        // Simulem l'entrada dades de cinc ciutats
        for (iCiu = 0; iCiu < DadesClima.ciutats.length; iCiu++) {
            for (iAny = 0; iAny < DadesClima.anys.length; iAny++) {
                tempRnd = generaFloatRnd(10.0f, 30.0f);
                plujaRnd = generaFloatRnd(100.0f, 500.0f);
                res = registraDadesClima(dadesCiutats, dadesClima, DadesClima.ciutats[iCiu][UtilsES.CODI_CIUTAT], DadesClima.anys[iAny], tempRnd, plujaRnd);

                if (res) {
                    System.out.println(MISSATGE_INGRES_OK + " per [" + DadesClima.ciutats[iCiu][UtilsES.CODI_CIUTAT]
                            + " - " + DadesClima.anys[iAny] + "] || [TEMP: "
                            + String.format("%.02f", tempRnd) + " / PLUJA: " + String.format("%.02f", plujaRnd) + "]");
                }
            }
        }
    }

    public float generaFloatRnd(float min, float max) {
        Random rand = new Random();
        return min + (max - min) * rand.nextFloat();
    }

    //Mètode per modificar dades
    public void opcioModifDadesCiutat(String[][] dadesCiutat, float[][] dadesClima) {
        int opcDades;

        String[] menuDades = {"MODIFICAR DADES CIUTAT",
            "1. Modificar dades temperatura",
            "2. Modificar dades pluja",
            "3. Sortir"
        };

        String cCiutat = UtilsES.demanaCodiOpcions(MISSATGE_CODI_CIUTAT, DadesClima.ciutats);
        String cAny = UtilsES.demanaAnyRang(MISSATGE_CODI_ANY, DadesClima.MIN_ANY, DadesClima.MAX_ANY);
        int posDades = ClimaUtils.existeixCiutatAny(dadesCiutat, cCiutat, cAny);
        if (posDades < dadesCiutat.length) {
            do {
                UtilsES.mostrarMenu(menuDades);
                opcDades = UtilsES.demanarEnter(MISSATGE_TRII_OPCIO, UtilsES.MISSATGE_ERROR_LECTURA);
                switch (opcDades) {
                    case 1:
                        modificarTemp(dadesClima, posDades);
                        System.out.print("\t-A la ciutat amb codi: " + cCiutat
                                + "\n\t-A l'any: " + cAny);
                        break;
                    case 2:
                        modificarPluja(dadesClima, posDades);
                        System.out.print("\t-A la ciutat amb codi: " + cCiutat
                                + "\n\t-A l'any: " + cAny);
                        break;
                    case 3:
                        System.out.println("\nTornem al MENU principal");
                        break;
                    default:
                        System.out.println(UtilsES.MISSATGE_ERROR_LECTURA);
                        break;
                }
            } while (opcDades != 3);

        } else {
            UtilsES.mostrarMissatge(MISSATGE_NOTROBAT);
        }
    }

    public void modificarTemp(float[][] dadesClima, int posCiutat) {
        float temp = UtilsES.demanarFloat(MISSATGE_TEMP, UtilsES.MISSATGE_ERROR_LECTURA);
        dadesClima[posCiutat][ClimaUtils.ID_TEMP] = temp;
        System.out.println("\n" + MISSATGE_INGRES_OK + ":\n\n\t-Temperatura modificada a: " + temp);
    }

    public void modificarPluja(float[][] dadesClima, int posCiutat) {
        float pluja = UtilsES.demanarFloat(MISSATGE_PLUJA, UtilsES.MISSATGE_ERROR_LECTURA);
        dadesClima[posCiutat][ClimaUtils.ID_PLUJA] = pluja;
        System.out.println("\n" + MISSATGE_INGRES_OK + ":\n\n\t-Pluja modificada a: " + pluja);
    }

    //Mètode per llistar les dades de la ciutat escollida
    public void opcioLlistarDadesCiutat(String[][] dadesCiutat, float[][] dadesClima) {
        String cCiutat = UtilsES.demanaCodiOpcions(MISSATGE_CODI_CIUTAT, DadesClima.ciutats);
        ClimaUtils.ordenarPerAnys(dadesCiutat, dadesClima, cCiutat);
        ClimaUtils.mostrarDadesCiutat(dadesCiutat, dadesClima, cCiutat);
    }

    //Mètode per trobar la mitjana de temperatura
    public void opcioMitjaTemp(String[][] dCiutats, float[][] dClima) {
        String any = UtilsES.demanaAnyRang(MISSATGE_CODI_ANY, DadesClima.MIN_ANY, DadesClima.MAX_ANY);
        UtilsES.mostrarMissatge(MISSATGE_MITJANA + any + " va ser "
                + String.format("%.2f", ClimaUtils.calculaMitjaTempAny(dCiutats, dClima, any)));
    }

    //Mètode per trobar maxima pluja
    public void opcioMaximaPluja(String[][] dCiutats, float[][] dClima) {
        if (!dCiutats[0][ClimaUtils.ID_CIUTAT].isEmpty()) {
            int posicioMaxPluja = ClimaUtils.posicioMaximaPluja(dCiutats, dClima);
            UtilsES.mostrarMissatge(MISSATGE_MAXPLUJA + dCiutats[posicioMaxPluja][ClimaUtils.ID_CIUTAT] + " l'any "
                    + dCiutats[posicioMaxPluja][ClimaUtils.ID_ANY] + " amb un valor de "
                    + String.format("%.2f", dClima[posicioMaxPluja][ClimaUtils.ID_PLUJA]));
        } else {
            System.out.println(MISSATGE_NO_DADES);
        }
    }

}
