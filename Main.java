import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Listino listino = new Listino();
        while (true) {
            String next = s.nextLine();
            if (next.isEmpty())
                break;
            String[] splittata = next.split("\\|");
            listino.aggiungi(new Prodotto(splittata[0], Integer.parseInt(splittata[1])));
        }
        
        Registratore registratore = new Registratore(listino);

        while (s.hasNextLine()) {
            String[] splittata = s.nextLine().split(" ");
            switch (splittata[0]) {
                case "A":
                    registratore.aggiungi(listino.getProdotto(splittata[1]));
                    break;
                case "S":
                    registratore.rimuovi(listino.getProdotto(splittata[1]));
                    break;
                case "E":
                    registratore.emetti();
                    break;
                case "R":
                    System.out.println(registratore.registro());
                    break;
                default:
                    throw new InputMismatchException();
            }
        }
    }
}