package Logica;

/**
 *
 * @author greivin
 */
public class CajaMedida {

    private String[] cajaMedidas = new String[5];
    private String[] cajaOtrasMedidas = new String[5];

    public String[] getCajaMedidas() {
        return cajaMedidas;
    }

    public void setCajaMedidas(String[] cajaMedidas) {
        this.cajaMedidas = cajaMedidas;
    }

    public String[] getCajaOtrasMedidas() {
        return cajaOtrasMedidas;
    }

    public void setCajaOtrasMedidas(String[] cajaOtrasMedidas) {
        this.cajaOtrasMedidas = cajaOtrasMedidas;
    }

    public void ArregloMedida() {
        cajaMedidas = new String[]{"MM", "CM", "PG", "MT"};
    }

    public void ArregloOtraMedida() {
        cajaOtrasMedidas = new String[]{"MM", "CM", "PG", "MT"};
    }
}
