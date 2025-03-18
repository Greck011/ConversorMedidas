package Logica;

/**
 *
 * @author greivin
 */
public class Medida {
    public double MilimetrosACM(double unidadMM) {
        unidadMM = unidadMM / 10;

        return unidadMM;
    }

    public double MilimetrosAPG(double unidadMM) {
        unidadMM = unidadMM / 25.4;
        return unidadMM;
    }

    public double MilimetrosAMTR(double unidadMM) {
        unidadMM = unidadMM / 1000;

        return unidadMM;
    }

    public double CentimetrosAMM(double unidadCM) {
        unidadCM = unidadCM * 10;

        return unidadCM;
    }

    public double CentimetrosAPG(double unidadCM) {
        unidadCM = unidadCM / 2.54;
        return unidadCM;
    }

    public double CentimetrosAMTR(double unidadCM) {
        unidadCM = unidadCM / 100;
        return unidadCM;
    }

    public double PulgadaAMM(double unidadPG) {
        unidadPG = unidadPG * 25.4;
        return unidadPG;
    }

    public double PulgadaACM(double unidadPG) {
        unidadPG = unidadPG * 2.54;

        return unidadPG;
    }

    public double PulgadaAMTR(double unidadPG) {
        unidadPG = unidadPG * 0.0254;
        return unidadPG;
    }

    public double MetrosAMM(double unidadMTR) {
        unidadMTR = unidadMTR * 1000;
        return unidadMTR;
    }

    public double MetrosACM(double unidadMTR) {
        unidadMTR = unidadMTR * 100;
        return unidadMTR;
    }

    public double MetrosAPG(double unidadMTR) {
        unidadMTR = unidadMTR * 39.37;
        return unidadMTR;
    }    
}
