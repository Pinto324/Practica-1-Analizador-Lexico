/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import InterfazGrafica.IG1;

/**
 *
 * @author branp
 */
public class main {
    public static IG1 MenuInicial = new IG1();
    public static void main(String[] args){
        MenuInicial.setVisible(true);
        MenuInicial.setLocationRelativeTo(null);
        MenuInicial.setTitle("Menu Principal");
    }  
}
