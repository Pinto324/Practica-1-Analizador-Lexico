/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.util.*;
import javax.swing.JTextArea;

/**
 *
 * @author branp
 */
public class Automata {
    private String Texto;
    private JTextArea AreaDeTexto;
    private int Pos = 0;
    private final ArrayList<Tokens> tokens = new ArrayList<>();
    private Set<String> TokenSR = new HashSet<>();
    private final int[][] Transiciones = new int[8][6];
    char[] Agrupacion = {'(', ')', '[', ']', '{', '}'}; 
    char[] Operadores = {'+', '-', '*', '/', '%'}; 
    char[] Puntuacion = {'.', ',', ';', ':'};
    private int EstadoActual = 0;
    private int NFilaEstado = 1;
    private int NColumnaEstado = 0;
    private int NFilaTransicion = 1;
    private int NColumnaTransicion = 0;
    private boolean reiniciar = false;    
    //transiciones
    {
        Transiciones[0][0] = 1; Transiciones[0][1] = 2; Transiciones[0][2] =-1; Transiciones[0][3] = 5; Transiciones[0][4] = 6; Transiciones[0][5] = 7;                               
        Transiciones[1][0] = 1; Transiciones[1][1] = 1; Transiciones[1][2] =-1; Transiciones[1][3] =-1; Transiciones[1][4] =-1; Transiciones[1][5] =-1; 
        Transiciones[2][0] =-1; Transiciones[2][1] = 2; Transiciones[2][2] = 3; Transiciones[2][3] =-1; Transiciones[2][4] =-1; Transiciones[2][5] = -1;                              
        Transiciones[3][0] =-1; Transiciones[3][1] = 4; Transiciones[3][2] =-1; Transiciones[3][3] =-1; Transiciones[3][4] =-1; Transiciones[3][5] = -1;                                
        Transiciones[4][0] =-1; Transiciones[4][1] = 4; Transiciones[4][2] =-1; Transiciones[4][3] =-1; Transiciones[4][4] =-1; Transiciones[4][5] = -1;                               
        Transiciones[5][0] =-1; Transiciones[5][1] =-1; Transiciones[5][2] =-1; Transiciones[5][3] =-1; Transiciones[5][4] =-1; Transiciones[5][5] =-1;                                
        Transiciones[6][0] =-1; Transiciones[6][1] =-1; Transiciones[6][2] =-1; Transiciones[6][3] =-1; Transiciones[6][4] =-1; Transiciones[6][5] =-1;                                
        Transiciones[7][0] =-1; Transiciones[7][1] =-1; Transiciones[7][2] =-1; Transiciones[7][3] =-1; Transiciones[7][4] =-1; Transiciones[7][5] =-1;
    }
    //constructor
    public Automata(JTextArea AreaDeTexto) {
        this.AreaDeTexto = AreaDeTexto;
        this.Texto = AreaDeTexto.getText();
    }
    // gets y sets
     private void getTokenSR() {
        for (Tokens t : tokens) {
            if (!t.getTipoToken().equals(TokensValidos.ERROR)) {
                TokenSR.add(t.getLexema());
            }
        }
    }
    public String getSiSingularOPlural(int contador) {
        String palabra = " veces.";
        if (contador == 1) {
            palabra = " vez.";
        }
        return palabra;
    }
     
    //metodos:
    
    private boolean Pertenece(char[] Gcarac, char carac) {
        boolean pertenece = false;
        for (char c : Gcarac) {
            if (c == carac) {
                pertenece = true;
                break;
            }
        }
        return pertenece;
    }
    
    private int obtenerEstadoSiguiente(char caracTransicion ,int estadoActual) {
        int EstadoSiguiente = -1;
        try {
            if (estadoActual >= 0 && estadoActual <= 7) {  //S0-S7 
                EstadoSiguiente = Transiciones[estadoActual][TipoCaracter(caracTransicion)];
            }
        } catch (IndexOutOfBoundsException ex) {
        }
        return EstadoSiguiente;
    }
    
    public void Analizar(JTextArea AreaDeTexto) {
        AreaDeTexto.setText("");
        while (Pos < Texto.length()) {
            ObtenerToken(AreaDeTexto);
        }
    }
  
    public void mostrarErrores(JTextArea AreaErrores) {
        AreaErrores.setText("");
        for (Tokens e : tokens) {
            if (e.getTipoToken().equals(TokensValidos.ERROR)) {
                AreaErrores.append("Error en: " + e.getLexema() +  " columna: " + e.getNumColumna() + " en la fila " + e.getNumFila() + "\n");
            }
        }
    }
    
    public boolean TieneErrores(){
        for (Tokens e : tokens) {
            if (e.getTipoToken().equals(TokensValidos.ERROR)) {
                return true;
            }
        }
        return false;
    }
    
    public void imprimirContadorTokens(JTextArea AreaDeRepes) {
        AreaDeRepes.setText("");
        getTokenSR();
        for (String s : TokenSR) {
            int contador = 0;
            for (int i = 0; i < tokens.size(); i++) {
                if (s.equals(tokens.get(i).getLexema())) {
                    contador++;
                }
            }
            AreaDeRepes.append(s + " se repitió " + contador + getSiSingularOPlural(contador) + "\n");
        }
    }
   
    public void MostrarTokens(JTextArea AreaDeTokens) {
        AreaDeTokens.setText("");
        for (Tokens t : tokens) {
            AreaDeTokens.append("El Patron encontrado fue: '"+t.getLexema() + "' es | " + t.getTipoToken().toString() + " | se encuentra en Fila: " + t.getNumFila() + "\n");
        }
    }

    private TokensValidos Clasificacion() {
        TokensValidos Token;
        switch (EstadoActual) {
            case 1:
                Token = TokensValidos.ID;
                break;
            case 2:
                Token = TokensValidos.ENTERO;
                break;
            case 4:
                Token = TokensValidos.DECIMAL;
                break;
            case 5:
                Token = TokensValidos.AGRUPACION;
                break;
            case 6:
                Token = TokensValidos.OPERADOR;
                break;
            case 7:
                Token = TokensValidos.PUNTUACION;
                break;
            default:
                Token = TokensValidos.ERROR;
        }
        return Token;
    }
    private int TipoCaracter(char carac) {
        int tipo = -1;
        if (Character.isLetter(carac)) {
            tipo = 0;
        } else if (Character.isDigit(carac)) {
            tipo = 1;
        } else if (EstadoActual == 2 && carac == '.') {
            tipo = 2;
        } else if (Pertenece(Agrupacion, carac)) {
            tipo = 3;
        } else if (Pertenece(Operadores, carac)) {
            tipo = 4;
        } else if (Pertenece(Puntuacion, carac)) {
            tipo = 5;
        }
        return tipo;
    }
     private void ObtenerToken(JTextArea AreaDeTokens) {
        boolean Seguir = true;
        EstadoActual = 0;
        String token = "";
        char carac = ' ';
        while (Seguir && Pos < Texto.length()) {
            if (reiniciar) {
                NFilaTransicion++;
                NColumnaTransicion = 0;
            }
            carac = Texto.charAt(Pos);
            if (Character.isWhitespace(carac)) {
                Seguir = false;
            } else {
                this.reiniciar = false;
                int auxEstadoActual = EstadoActual;
                EstadoActual = obtenerEstadoSiguiente(carac,EstadoActual);
                token += carac;
                AreaDeTokens.append("Estado inicial: " + auxEstadoActual + " se movio al estado: " + EstadoActual + " leyendo: '" + carac + "'" + "\n");
            }
            if (EstadoActual == -1) {
                Seguir = false;
            }
            NColumnaTransicion++;
            NColumnaEstado++;
            Pos++;
            if (carac == '\n') {
                reiniciar = true;
                NFilaEstado++;
                NColumnaEstado = 0;
            }
        }
        if (!token.isBlank() && !Clasificacion().equals(TokensValidos.ERROR)) {
            Tokens nuevoToken = new Tokens(Clasificacion(), token.replace(" ", ""), this.NFilaTransicion, this.NColumnaTransicion - 1);
            tokens.add(nuevoToken);
            AreaDeTokens.append("========================================\n");
        } else if (!token.isBlank() && Clasificacion().equals(TokensValidos.ERROR)) {
            Tokens Error = new Tokens(Clasificacion(), token.replace(" ", ""), NFilaEstado, NColumnaEstado);
            tokens.add(Error);
            AreaDeTokens.append("========================================\n");
        }
    }
}
