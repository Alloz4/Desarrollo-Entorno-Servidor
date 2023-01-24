package tema13;

import java.util.LinkedList;
import java.util.List;

public class LibroDeFirmas {

    private List<Usuario> firmantes = new LinkedList<Usuario>();

    public List<Usuario> getFirmantes() {
        return firmantes;
    }

    public void setFirmantes(List<Usuario> firmantes) {
        this.firmantes = firmantes;
    }

    public void anhadirFirmante(Usuario firmante) {
        this.firmantes.add(firmante);
    }
}