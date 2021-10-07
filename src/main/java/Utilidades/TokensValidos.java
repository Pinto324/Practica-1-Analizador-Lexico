/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

/**
 *
 * @author branp
 */
public enum TokensValidos {
    ID("Identificador"),
    ENTERO("Numero entero"),
    DECIMAL("Numero decimal"),
    PUNTUACION("Signo de puntuacion"),
    OPERADOR("Operador"),
    AGRUPACION("Signo de agrupacion"),
    ERROR("Error");

    private final String Contexto;

    private TokensValidos(String descripcion) {
        this.Contexto = descripcion;
    }

    @Override
    public String toString() {
        return this.Contexto;
    }
}
