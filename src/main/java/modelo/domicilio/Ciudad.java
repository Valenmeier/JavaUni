/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.domicilio;


/**
 * @author Valentin Meier
 */
public class Ciudad {
    private String provincia;
    private String ciudad;

    public Ciudad(String provincia, String ciudd) {
        this.provincia = provincia;
        this.ciudad = ciudd;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCiudd() {
        return ciudad;
    }

    public void setCiudd(String ciudd) {
        this.ciudad = ciudd;
    }


}
