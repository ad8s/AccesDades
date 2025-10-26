package practicar.practica1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String file = "files/numeros.txt";
        String userInput = "";
        try (FileOutputStream fos = new FileOutputStream(file)) {
            while (!userInput.equalsIgnoreCase("stop")) {
                System.out.print("Introdueix un número (ex: 1 o 10, escriu 'stop' per sortir): ");
                userInput = input.nextLine();
                if (!userInput.equalsIgnoreCase("stop")) {
                    fos.write((userInput + System.lineSeparator()).getBytes());
                }
            }
        } catch (IOException e) {
            System.out.println("Error escribin en l'arxiu: " + e.getMessage());
            return;
        } finally {
            input.close();
        }
        try (Scanner fileScanner = new Scanner(new File(file))) {
            int suma = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int count = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.trim().isEmpty())
                    continue;

                int numero = Integer.parseInt(line.trim());
                suma += numero;
                if (numero > max)
                    max = numero;
                if (numero < min)
                    min = numero;
                count++;
            }
            if (count > 0) {
                double promedio = (double) suma / count;
                System.out.println("Promedio: " + promedio);
                System.out.println("Máximo: " + max);
                System.out.println("Mínimo: " + min);
            } else {
                System.out.println("No hay números en el archivo.");
            }
        } catch (IOException e) {
            System.out.println("Error escribin en l'arxiu: " + e.getMessage());

        }
    }
}
