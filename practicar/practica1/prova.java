package practicar.practica1;

public class prova {
    public static void main(String[] args) {
        char[] line = "hola".toCharArray();
        int conver = 0;
        for (int i = 0; i < line.length; i++) {
            conver = (int) line[i];
            int r = conver + 1;
            System.out.print((char) r);
        }
    }
}
