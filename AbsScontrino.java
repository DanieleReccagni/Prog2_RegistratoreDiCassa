import java.util.Iterator;

// Classe astratta immutabile che rappresenta parzialmente uno Scontrino implementandone il metodo toString
public abstract class AbsScontrino implements Scontrino {

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Iterator<Riga> it = righe();
        while (it.hasNext()) {
            s.append(it.next());
            s.append("\n");
        }
        s.append("----------------\n");
        s.append("Totale:");
        for (int i=String.valueOf(totale()).length(); i<8; i++)
            s.append(" ");
        s.append(totale());
        return s.toString();
    }

    @Override
    public abstract Iterator<Riga> righe();

}