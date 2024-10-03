package modelo.persona;

import java.time.LocalDate;

import modelo.domicilio.Domicilio;
import repositorio.RepositorioQuiniela;

/**
 * @author Valentin Meier
 */
public class Cliente extends Persona {

    private String codigo;

    public Cliente(String nombre, String apellido, String email, int dni, Domicilio domicilio) {
        super(nombre, apellido, email, dni, domicilio);
        this.codigo = generarCodigo();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String generarCodigo() {
        LocalDate currentDate
                = LocalDate.now();
        return "C-" + currentDate.getDayOfMonth() + "-" + currentDate.getMonth() + "-" + currentDate.getYear() + "-" + contarClientes();
    }

    private int contarClientes() {
        int contar = 0;
        for (int i = 0; i < RepositorioQuiniela.clientes.length; i++) {
            if (RepositorioQuiniela.clientes[i] instanceof Cliente) contar++;
        }
        return contar;
    }

}
