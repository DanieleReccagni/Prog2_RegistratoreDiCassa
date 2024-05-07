import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Iterator;

// Classe concreta immutabile che rappresenta uno scontrino chiuso, ossia una semplice collezione di prodotti
public class ScontrinoChiuso extends AbsScontrino {

    // RI: scontrinoChiuso non deve essere nullo
    //     scontrinoChiuso non deve essere vuoto
    //     scontrinoChiude non deve contenere elementi nulli
    // AF: scontrinoChiuso rappresenta una collezione di prodotti acquistati che non può più essere modificata

    // ATTRIBUTI
    // Scontrino chiuso
    private final List<Riga> scontrinoChiuso = new ArrayList<>();

    // COSTRUTTORI
    // Post-condizioni: inizializza scontrinoChiuso chiudendo scontrinoModificabile
    //                  solleva un'eccezione di tipo NullPointerException se scontrinoModificabile è nullo
    //                  solleva un'eccezione di tipo NullPointerException se scontrinoModificabile contiene elementi nulli
    //                  solleva un'eccezione di tipo IllegalArgumentException se scontrinoModificabile è vuoto
    public ScontrinoChiuso(List<Riga> scontrinoModificabile) {
        Objects.requireNonNull(scontrinoModificabile);
        if (scontrinoModificabile.isEmpty()) throw new IllegalArgumentException();
        for (Riga r : scontrinoModificabile) {
            Objects.requireNonNull(r);
            scontrinoChiuso.add(r);
        }
    }

    // METODI
    @Override
    public Iterator<Riga> righe() {
        return Collections.unmodifiableList(scontrinoChiuso).iterator();
    }

}