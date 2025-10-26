package practicar;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "invertido.txt";
        System.out.print("Original: ");
        String input = sc.nextLine();
        String invert = new StringBuilder(input).reverse().toString();
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(invert.getBytes());
        } catch (IOException e) {
            System.err.println("Error llegint l'arxiu: " + e.getMessage());
        } finally {
            sc.close();
        }
        try (FileInputStream fis = new FileInputStream(fileName)) {
            int b;
            StringBuilder sb = new StringBuilder();
            while ((b = fis.read()) != -1) {
                sb.append((char) b);
            }
            System.out.println("Invertido: " + sb.toString());
        } catch (IOException e) {
            System.err.println("Error llegint l'arxiu: " + e.getMessage());
        }
    }
}
