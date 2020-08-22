package Proyecto1;

import java.text.SimpleDateFormat;

public class Proyecto1 {

    public static void main(String[] args) {

        Boot boot = new Boot();

        java.util.Date fecha = new java.util.Date();
        System.out.println("Fecha " + new SimpleDateFormat("dd/MM/yyyy").format(fecha) + " hora " + new SimpleDateFormat("hh:mm").format(fecha));
        System.out.println("Circuito de Pruebas \n");

    }

}
