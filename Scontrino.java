import java.util.Iterator;

// Interfaccia che elenca dei comportamenti caratteristici di uno scontrino, ossia una collezione di prodotti
public interface Scontrino {
    
    // Post-condizioni: restituisce un iteratore che permette di enumerare gli elementi dello scontrino in ordine di inserimento
    //                  esso crea una lista di righe secondo la seguente modalità
    //                  - se nello scontrino sono presenti più prodotti dello stesso tipo consecutivi, allora li accorpa in un'unica riga
    //                  - altrimenti crea delle righe separate (anche se lo stesso prodotto non è presente consecutivamente nello scontrino)
    Iterator<Riga> righe();

    // Post-condizioni: restituisce il prezzo complessivo dello scontrino
    default int totale() {
        int somma = 0;
        Iterator<Riga> it = righe();
        while (it.hasNext())
            somma += it.next().prezzoTotale();
        return somma;
    }

}