package practicar.practica2;

import java.io.*;

public class ex1 {
    public static void main(String[] args) {
        String inputFile = "files/vocales.txt";
        String outputFile = "resultado.txt";

        int a = 0, e = 0, i = 0, o = 0, u = 0;

        try (FileInputStream fis = new FileInputStream(inputFile)) {
            int b;
            while ((b = fis.read()) != -1) {
                char c = Character.toLowerCase((char) b);
                switch (c) {
                    case 'a':
                        a++;
                        break;
                    case 'e':
                        e++;
                        break;
                    case 'i':
                        i++;
                        break;
                    case 'o':
                        o++;
                        break;
                    case 'u':
                        u++;
                        break;
                }
            }
        } catch (IOException ex) {
            System.out.println("Error leyendo archivo: " + ex.getMessage());
            return;
        }

        // Guardar resultado
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            String resultado = String.format("a: %d\ne: %d\ni: %d\no: %d\nu: %d\n", a, e, i, o, u);
            fos.write(resultado.getBytes());
            System.out.println("Resultado guardado en " + outputFile);
        } catch (IOException ex) {
            System.out.println("Error escribiendo archivo: " + ex.getMessage());
        }
    }
}
