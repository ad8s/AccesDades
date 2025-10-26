package Clase.Ejemplos;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex322 {
    public static void main(String[] args) {
        String fileName = "text.bin";
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix un codi: ");
        int code = sc.nextInt();
        boolean found = false;
        boolean finish = false;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            while (!found || !finish) {
                try {
                    int codeFound = dis.readInt();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 3; i++) {
                        sb.append(dis.readChar());
                    }
                    if (code == codeFound) {
                        System.out.println("S'ha trobar un code: " + sb);
                        found = true;
                    }
                } catch (EOFException e) {
                    finish = true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error llegin l'arxiu: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
