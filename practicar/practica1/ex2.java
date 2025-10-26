package practicar.practica1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ex2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String fileName = "usertext.txt";
        int numLines = 0;
        int numWords = 0;
        int numChars = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                numLines++;
                numChars += line.length();

                String[] words = line.trim().split("\\s+");
                if (!line.trim().isEmpty()) {
                    numWords += words.length;
                }
            }
            System.out.println("Estadístiques del fitxer '" + fileName + "':");
            System.out.println(" - Línies: " + numLines);
            System.out.println(" - Paraules: " + numWords);
            System.out.println(" - Caràcters: " + numChars);
        } catch (FileNotFoundException e) {
            System.out.println("No s'ha pogut trobar el arxiu " + fileName + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error llegint l'arxiu " + e.getMessage());
        } finally {
            input.close();
        }
    }

}
