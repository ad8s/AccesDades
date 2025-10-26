package practicar.practica3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "files/usertext.txt";
        String userInput = "";
        try (FileOutputStream fos = new FileOutputStream(file)) {
            while (!userInput.equalsIgnoreCase("quit")) {
                System.out.print("Introduce un string (escribe 'quit' para salir): ");
                userInput = sc.nextLine();
                if (!userInput.equalsIgnoreCase("quit")) {
                    fos.write((userInput + System.lineSeparator()).getBytes());
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo " + e.getMessage());
        } finally {
            sc.close();
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            int b;
            StringBuilder sb = new StringBuilder();
            while ((b = fis.read()) != -1) {
                sb.append((char) b);
            }
            System.out.println("Contenido del archivo: ");
            System.out.println(sb.toString());
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo " + e.getMessage());
        }
    }
}
