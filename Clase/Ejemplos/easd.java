package Clase.Ejemplos;

import java.io.*;

public class easd {

    public static void main(String args[]) {
        String file = "text.txt";

        File f = new File(file);
        try {
            FileOutputStream fos = new FileOutputStream(f);

            fos.write("HOLA ESTIMATS ALUMNES".getBytes());
            fos.write("\n".getBytes());
            String t = "Som a classe";
            for (char c : t.toCharArray()) {
                fos.write(c);
            }
            fos.close();
            FileInputStream fis = new FileInputStream(f);
            int b = 0;
            // byte[] b = new byte[100];
            while (b != -1) {
                b = fis.read();
                if (b != -1)
                    System.out.print((char) b);
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
