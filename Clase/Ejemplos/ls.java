package Clase.Ejemplos;

import java.io.File;

public class ls {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Falta un parámetro (ruta).");
            return;
        }

        String ruta = args[0];
        File path = new File(ruta);

        if (!path.exists()) {
            System.out.println("La ruta no existe.");
            return;
        }

        if (!path.isDirectory()) {
            System.out.println("La ruta no es un directorio.");
            return;
        }

        File[] files = path.listFiles();
        if (files == null) {
            System.out.println("No se pudo acceder a la ruta.");
            return;
        }

        for (File file : files) {
            String resultat = "";

            if (file.isDirectory()) {
                resultat += "d";
            } else {
                resultat += "-";
            }

            resultat += file.canRead() ? "r" : "-";
            resultat += file.canWrite() ? "w" : "-";
            resultat += file.canExecute() ? "x" : "-";

            System.out.println(resultat + " " + file.getName() + " " + file.length() + " bytes");
        }
    }
}
