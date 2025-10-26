package practicar.practica3;

import java.util.Scanner;
import java.io.File;

public class ex1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String file = "files/contador.txt";
        int count = 0;
        try {
            File f = new File(file);
            Scanner input = new Scanner(f);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'a') {
                        count++;
                    }
                }
            }
            input.close();
            System.out.println("Hay un total de " + count + " a's");
        } catch (Exception e) {

        } finally {
            sc.close();
        }
    }
}
