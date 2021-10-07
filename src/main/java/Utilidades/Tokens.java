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
public class Tokens {
    private final TokensValidos tipoToken;
    private final String lexema;
    private final int NFila;
    private final int NColumna;

    public Tokens(TokensValidos tipoToken, String lexema, int NFila, int NColumna) {
        this.tipoToken = tipoToken;
        this.lexema = lexema;
        this.NFila = NFila;
        this.NColumna = NColumna;
    }

    public TokensValidos getTipoToken() {
        return tipoToken;
    }

    public String getLexema() {
        return lexema;
    }

    public int getNumFila() {
        return NFila;
    }

    public int getNumColumna() {
        return NColumna;
    }
}
