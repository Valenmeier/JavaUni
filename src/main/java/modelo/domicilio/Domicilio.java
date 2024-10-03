
package modelo.domicilio;

/**
 *
 * @author Valentin Meier
 */
public class Domicilio {
    private Ciudad ciudad;
    private String calle;
    private int nro;

    public Domicilio(Ciudad ciudad, String calle, int nro) {
        this.ciudad = ciudad;
        this.calle = calle;
        this.nro = nro;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }
    
    
}
