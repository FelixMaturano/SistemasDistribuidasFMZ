/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemabecas;
import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Nota implements Serializable{
    private String materia;
    private int Calificacion;

    public Nota(String materia, int Calificacion) {
        this.materia = materia;
        this.Calificacion = Calificacion;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int Calificacion) {
        this.Calificacion = Calificacion;
    }
    
    
    
}
