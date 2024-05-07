import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;

// Classe concreta mutabile che rappresenta un registratore di cassa,
// caratterizzato da un listino e in grado di emettere scontrini
public class Registratore {

    // RI: listino non deve essere nullo
    //     listino non deve essere vuoto
    //     elenco non deve essere nullo
    //     elenco non deve contenere elementi nulli
    //     elenco non deve contenere elementi vuoti
    //     scontrino non deve essere nullo
    // AF: listino rappresenta il listino prezzi del registratore di cassa this
    //     elenco rappresenta un elenco di scontrini chiusi che contengono prodotti elencati nel listino di this
    //     scontrino rappresenta lo scontrino aperto alle modifiche in un certo momento di this

    // ATTRIBUTI
    // Listino del registratore di cassa
    private final Listino listino;
    // Elenco degli scontrini chiusi dal registratore di cassa
    private final List<ScontrinoChiuso> elenco = new ArrayList<>();
    // Scontrino in elaborazione in un certo momento del registratore di cassa
    private ScontrinoInElaborazione scontrino = new ScontrinoInElaborazione();

    // COSTRUTTORI
    // Post-condizioni: inizializza this con un listino, un elenco vuoto e uno scontrino in elaborazione vuoto
    public Registratore(Listino listino) {
        this.listino = listino;
    }

    // METODI
    // Post-condizioni: chiude lo scontrino in elaborazione e lo emette,
    //                  aggiungendolo alla lista degli scontrini emessi e predisponendo un nuovo scontrino aperto
    // Effetti collaterali: re-inizializza this.scontrino con un nuovo scontrino aperto vuoto
    public ScontrinoChiuso emetti() {
        ScontrinoChiuso chiuso = scontrino.chiudi();
        elenco.add(chiuso);
        scontrino = new ScontrinoInElaborazione();
        return chiuso;
    }

    // Post-condizioni: se lo scontrino in elaborazione è vuoto, restituisce una stringa contenenre la lista degli scontrini emessi
    //                  altrimenti restituisce una stringa vuota
    public String registro() {
        if (scontrino.isEmpty()) {
            StringBuilder s = new StringBuilder();
            int i = 1;
            for (ScontrinoChiuso scontrino : elenco) {
                s.append("Scontrino n. ");
                s.append(i);
                s.append("\n----------------\n");
                s.append(scontrino);
                s.append("\n\n");
                i++;
            }
            return s.substring(0, s.length()-1).toString();
        }
        return "";
    }

    // Post-condizioni: aggiunge prodotto allo scontrino in elaborazione
    public void aggiungi(Prodotto prodotto) {
        scontrino.aggiungi(prodotto);
    }

    // Post-condizioni: rimuove prodotto dallo scontrino in elaborazione
    //                  solleva un'eccezione di tipo NoSuchElementException se prodotto non è presente nello scontrino aperto
    public void rimuovi(Prodotto prodotto) {
        if (!scontrino.contiene(prodotto)) throw new NoSuchElementException();
        scontrino.storna(prodotto);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(listino);
        s.append("\n");
        s.append(registro());
        s.append("\n");
        s.append(scontrino);
        return s.toString();
    }
}