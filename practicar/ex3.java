package practicar;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ex3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String fileName = "usertext.txt";
        String userInput = "";
        try (FileOutputStream fos = new FileOutputStream(fileName, true)) {
            while (!userInput.equalsIgnoreCase("fin")) {
                System.out.println("Introdueix un string (fin per sortir/terminar): ");
                userInput = input.nextLine();
                if (!userInput.equalsIgnoreCase("fin")) {
                    fos.write((userInput + System.lineSeparator()).getBytes());
                }
            }
            System.out.println("Contingut afegit! ");
        } catch (IOException e) {
            System.err.println("Error llegint l'arxiu " + e.getMessage());
        } finally {
            input.close();
        }
    }
}
