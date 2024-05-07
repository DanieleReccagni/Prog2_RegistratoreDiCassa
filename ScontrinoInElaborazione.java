import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

// Classe concreta mutabile che rappresenta uno scontrino ancora in elaborazione.
// Dopo la chiusura, lo scontrino in elaborazione rimane comunque disponibile per altre aggiunte o eliminazioni
public class ScontrinoInElaborazione extends AbsScontrino {
    
    // RI: scontrinoModificabile non deve essere nullo
    //     scontrinoModificabile non deve contenere elementi nulli
    // AF: scontrinoModificabile rappresenta una collezione di prodotti acquistati ancora in elaborazione
    
    // ATTRIBUTI
    // Scontrino aperto
    private final List<Prodotto> scontrinoModificabile = new ArrayList<>();

    // COSTRUTTORI
    // Post-condizioni: inizializza uno scontrino vuoto
    public ScontrinoInElaborazione() {}

    // Post-condizioni: inizializza lo scontrino aggiungendovi prodotto
    //                  solleva un'eccezione di tipo NullPointerException se prodotto è nullo
    public ScontrinoInElaborazione(Prodotto prodotto) {
        Objects.requireNonNull(prodotto);
        scontrinoModificabile.add(prodotto);
    }

    // Post-condizioni: inizializza lo scontrino con *scontrino*
    //                  solleva un'eccezione di tipo NullPointerException se scontrinoModificabile è nullo
    //                  solleva un'eccezione di tipo NullPointerException se scontrinoModificabile contiene elementi nulli
    //                  solleva un'eccezione di tipo IllegalArgumentException se scontrinoModificabile è vuoto
    public ScontrinoInElaborazione(List<Prodotto> scontrinoModificabile) {
        Objects.requireNonNull(scontrinoModificabile);
        for (Prodotto p : scontrinoModificabile) {
            Objects.requireNonNull(p);
            this.scontrinoModificabile.add(p);
        }
    }

    // METODI
    // Post-condizioni: restituisce true se scontrinoModificabile è vuoto
    //                              false altrimenti
    public boolean isEmpty() {
        if (scontrinoModificabile.size() == 0)
            return true;
        else
            return false;
    }

    // Post-condizioni: restituisce true se this contiene prodotto
    //                              false altrimenti
    public boolean contiene(Prodotto prodotto) {
        if (scontrinoModificabile.contains(prodotto))
            return true;
        return false;
    }

    // Post-condizioni: aggiunge allo scontrino il prodotto *prodotto*
    //                  solleva un'eccezione di tipo NullPointerException se prodotto è nullo
    // Effetti collaterali: modifica lo scontrino aggiungendovi *prodotto*
    public void aggiungi(Prodotto prodotto) {
        Objects.requireNonNull(prodotto);
        scontrinoModificabile.add(prodotto);
    }

    // Post-condizioni: se possibile, rimuove dallo scontrino il prodotto *prodotto*
    //                  solleva un'eccezione di tipo NullPointerException se prodotto è nullo
    // Effetti collaterali: modifica lo scontrino rimuovendovi (se possibile) *prodotto*
    public void storna(Prodotto prodotto) {
        Objects.requireNonNull(prodotto);
        if (contiene(prodotto))
            for (int i = scontrinoModificabile.size()-1; i >= 0; i--)
                if (scontrinoModificabile.get(i).equals(prodotto)) {
                    scontrinoModificabile.remove(i);
                    break;
                }
    }

    // Post-condizioni: chiude lo scontrino in elaborazione this, creando uno scontrino chiuso a partire da this
    public ScontrinoChiuso chiudi() {
        List<Riga> passa = new ArrayList<>();
        Iterator<Riga> it = righe();
        while (it.hasNext())
            passa.add(it.next());
        return new ScontrinoChiuso(passa);
    }

    @Override
    public Iterator<Riga> righe() {
        return new Iterator<Riga>() {
            private int i = 0;
            private int conta;
            private Prodotto attuale;

            @Override
            public Riga next() {
                return new Riga(attuale, conta);
            }

            @Override
            public boolean hasNext() {
                if (i >= scontrinoModificabile.size())
                    return false;
                conta = 1;
                attuale = scontrinoModificabile.get(i);
                i++;
                while (i < scontrinoModificabile.size()) {
                    Prodotto questo = scontrinoModificabile.get(i);
                    if (questo.equals(attuale)) {
                        conta++;
                        i++;
                    } else
                        break;
                }
                return true;
            }
        };

    }

}