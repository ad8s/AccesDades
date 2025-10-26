package Clase.Ex;

import java.io.*;
import java.util.Scanner;

public class ex1 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        String file = "textEx.txt";

        File f = new File(file);
        String input = "";
        int b = 0;
        boolean inici = true;
        try {
            FileOutputStream fos = new FileOutputStream(f);
            FileInputStream fis = new FileInputStream(f);
            do {
                System.out.println("Introdueix una frase o un palabra (quit per exit): ");
                input = in.nextLine();
                if (!input.equals("quit")) {
                    if (!inici) {
                        fos.write(("\n").getBytes());
                    }
                    inici = false;

                    fos.write((input).getBytes());
                }
            } while (!input.equals("quit"));

            fos.close();
            fis.close();
            System.out.println();
            System.out.println("Arxiu: ");
            while (b != -1) {
                b = fis.read();
                if (b != -1) {
                    System.out.print((char) b);
                }
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
