/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

/**
 *
 * @author branp
 */
public class Resaltador {
    private int Pos = 0;
    private final String PalbraABuscar;
    private final char[] Carac;    
    private final JTextArea AreaDeTexto;
    private final Highlighter Resaltador;
    //Constructor
    public Resaltador(JTextArea AreaDeTexto, String PAB) {
        this.AreaDeTexto = AreaDeTexto;
        this.PalbraABuscar = PAB;
        Carac = PAB.toCharArray();
        Resaltador = AreaDeTexto.getHighlighter();
        Resaltador.removeAllHighlights();
    }

    public int BuscarTexto() throws IndexOutOfBoundsException {
        String Texto = AreaDeTexto.getText();
        char Caracter;
        int NC = 0;
        while (Pos < Texto.length()) {
            Caracter = Texto.charAt(Pos);
            if (Caracter == Carac[0]) {
                int contador = 0;
                for (int i = 0; i < PalbraABuscar.length(); i++) {
                    Caracter = Texto.charAt(Pos);
                    if (Caracter == Carac[i]) {
                        contador++;
                    }
                    Pos++;
                }
                if (contador == PalbraABuscar.length()) {
                    Colorear(Pos - PalbraABuscar.length(), Pos);
                    NC++;
                }
            } else {
                Pos++;
            }
        }
        return NC;
    }

    private void Colorear(int PosI, int PosF) {
        DefaultHighlighter.DefaultHighlightPainter highlightPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
        try {
            Resaltador.addHighlight(PosI, PosF, highlightPainter);
        } catch (BadLocationException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
