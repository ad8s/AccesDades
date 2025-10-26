package practicar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Introdueix el arxiu d'entrada: ");
        String fileInput = sc.nextLine();
        System.out.println("Introdueix l'arxiu de sortida: ");
        String fileOutput = sc.nextLine();

        try (
                FileInputStream fis = new FileInputStream(fileInput);
                FileOutputStream fos = new FileOutputStream(fileOutput)) {

            int b;
            StringBuilder sb = new StringBuilder();
            while ((b = fis.read()) != -1) {
                fos.write(b);
                sb.append((char) b);
            }
            System.out.println("Arxiu copiat correctament de '" + fileInput + "' a '" + fileOutput + "'");
            System.out.println("Contingut de l'arxiu: ");
            System.out.println(sb.toString());
        } catch (IOException e) {
            System.out.println("Error al copiar l'arxiu: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
