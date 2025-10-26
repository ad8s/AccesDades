package practicar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.IOException;

public record ex5() {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "clave.txt";
        System.out.print("Entrada: ");
        String userInput = sc.nextLine();
        try (FileOutputStream fos = new FileOutputStream(file)) {
            char[] line = userInput.toCharArray();
            int conver = 0;
            int resultat = 0;
            System.out.print("Archivo: ");
            for (int i = 0; i < line.length; i++) {
                conver = (int) line[i];
                resultat = conver + 1;
                System.out.print((char) resultat);
                fos.write((char) resultat);
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error llegitn l'arxiu: " + e.getMessage());
        } finally {
            sc.close();
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            int b;
            System.out.print("Salida: ");
            while ((b = fis.read()) != -1) {
                char des = (char) (b - 1);
                System.out.print(des);
            }
            System.out.println();
        } catch (IOException e) {
            System.err.println("Error llegitn l'arxiu: " + e.getMessage());

        }
    }
}
