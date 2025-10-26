package Clase.Ex;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class ex23 {
    public static void main(String[] args) {
        String fileName = "files/codis_secrets.bin";
        Random random = new Random();

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {
            int codi = random.nextInt(3) + 1;

            for (int i = 0; i < 10; i++) {
                dos.writeInt(codi);

                for (int j = 0; j < 3; j++) {
                    char lletra = (char) ('a' + random.nextInt(26));
                    dos.writeChar(lletra);
                }

                codi += random.nextInt(3) + 1;
            }

            System.out.println("Fitxer generat correctament: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
