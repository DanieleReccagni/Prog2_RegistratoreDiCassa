import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Iterator;
import java.util.List;

// Classe concreta mutabile che rappresenta un listino prezzi,
// ossia una collezione di Prodotti senza duplicati
public class Listino implements Iterable<Prodotto> {
    
    // RI: listino non deve essere nullo
    //     listino non deve contenere chiavi String nulle
    //     listino non deve contenere valori Prodotto nulli
    //     ogni entry di listino deve avere la chiave String corrispondente al nome del Prodotto
    // AF: listino rappresenta un listino prezzi
    //     esso è tenuto in ordine di inserimento dei prodotti

    // ATTRIBUTI
    // Listino prezzi
    private final Map<String, Prodotto> listino = new LinkedHashMap<>();

    // COSTRUTTORI
    // Post-condizioni: crea un listino vuoto
    public Listino() {}

    // Post-condizioni: inizializza il listino con *listino*
    //                  solleva un'eccezione di tipo NullPointerException se listino è nullo
    //                  solleva un'eccezione di tipo IllegalArgumentException se *listino* contiene dei prodotti duplicati
    public Listino(List<Prodotto> listino) {
        Objects.requireNonNull(listino);
        for (Prodotto p : listino) {
            Objects.requireNonNull(p);
            if (contiene(p.nome())) throw new IllegalArgumentException();
            this.listino.put(p.nome(), p);
        }
    }

    // METODI
    // Post-condizioni: restituisce true se il listino contiene già un prodotto con nome uguale a *nome*
    //                              false altrimenti
    public boolean contiene(String nome) {
        if (listino.containsKey(nome)) return true;
        return false;
    }

    // Post-condizioni: aggiunge prodotto al listino
    //                  solleva un'eccezione di tipo NullPointerException se prodotto è nullo
    //                  solleva un'eccezione di tipo IllegalArgumentException se listino contiene
    //                      già un prodotto con lo stesso nome di *prodotto*
    // Effetti collaterali: modifica il listino aggiungendovi il prodotto
    public void aggiungi(Prodotto prodotto) {
        Objects.requireNonNull(prodotto);
        if (contiene(prodotto.nome())) throw new IllegalArgumentException();
        listino.put(prodotto.nome(), prodotto);
    }

    // Post-condizioni: restituisce il prodotto con nome *nome* presente nel listino
    //                  solleva un'eccezione di tipo NullPointerException se nome è null
    //                  solleva un'eccezione di tipo NoSuchElementException se il listino non contiene
    //                      un prodotto con nome *nome*
    public Prodotto getProdotto(String nome) {
        Objects.requireNonNull(nome);
        if (!contiene(nome)) throw new NoSuchElementException();
        return listino.get(nome);
    }

    // Post-condizioni: restituisce un iteratore che permette di enumerare i prodotti del listino in ordine di inserimento
    @Override
    public Iterator<Prodotto> iterator() {
        return Collections.unmodifiableCollection(listino.values()).iterator();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Prodotto p : this) {
            s.append(p);
            s.append("\n");
        }    
        return s.toString();
    }

}