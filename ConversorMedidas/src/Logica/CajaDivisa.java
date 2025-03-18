package Logica;

/**
 *
 * @author greivin
 */
public class CajaDivisa {

    private String[] cajaDivisa = new String[5];
    private String[] cajaOtrasDivisa = new String[5];

    public String[] getCajaDivisa() {
        return cajaDivisa;
    }

    public void setCajaDivisa(String[] cajaDivisa) {
        this.cajaDivisa = cajaDivisa;
    }

    public String[] getCajaOtrasDivisa() {
        return cajaOtrasDivisa;
    }

    public void setCajaOtrasDivisas(String[] cajaOtrasMedidas) {
        this.cajaOtrasDivisa = cajaOtrasMedidas;
    }

    public void ArregloDivisa() {
        cajaDivisa = new String[]{"USD", "CRC"};
    }

    public void ArregloOtraDivisa() {
        cajaOtrasDivisa = new String[]{"USD", "CRC"};
    }
}
