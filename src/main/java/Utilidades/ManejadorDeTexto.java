
package Utilidades;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ManejadorDeTexto {
    private static String Texto;
    private File ArchivoACargar;

    public ManejadorDeTexto() {
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String Texto) {
        this.Texto = Texto;
    }
    public void LlenarTexto(File ArchivoACargar) {
        this.ArchivoACargar = ArchivoACargar;
        LlenarTexto();
    }
    public void LlenarTexto(){
        FileReader fr = null;
        BufferedReader br = null;
        Texto = "";
        try{
            fr = new FileReader(ArchivoACargar, StandardCharsets.UTF_8);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {
                Texto += linea + "\n";
            }
        }catch( IOException e1){
        
        }
    }
    public boolean GuardarArchivo(File Archivo, String Texto){
        FileOutputStream salida;
        boolean guardado = false;
        try{
            salida = new FileOutputStream(Archivo);
            byte[] bytxt = Texto.getBytes();
            salida.write(bytxt);
            guardado = true;
        }catch(FileNotFoundException ex){
        } catch (IOException ex) {
        }
        return guardado;
    }
}
