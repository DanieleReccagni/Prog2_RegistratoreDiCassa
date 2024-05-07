import java.util.Objects;

// Record immutabile che rappresenta una riga di uno scontrino, caratterizzata da un prodotto e da una quantità
public record Riga(Prodotto prodotto, int quantita) {

    // RI: prodotto deve essere non nullo
    //     quantita deve essere > 0
    // AF: Riga rappresentante *prodotto* in quantità *quantita*

    // Post-condizioni: inizializza la riga con prodotto e quantita
    //                  solleva un'eccezione di tipo NullPointerException se prodotto è nullo
    //                  solleva un'eccezione di tipo IllegalArgumentException se quantita <= 0
    public Riga(Prodotto prodotto, int quantita) {
        Objects.requireNonNull(prodotto);
        if (quantita <= 0) throw new IllegalArgumentException();
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    // Post-condizioni: restituisce il prezzoTotale della riga
    public int prezzoTotale() {
        return prodotto.prezzoUnitario() * quantita;
    }

    @Override
    public String toString() {
        if (quantita == 1)
            return prodotto.toString();
        else {
            StringBuilder s = new StringBuilder();
            s.append(prodotto.nome());
            for (int i = prodotto.nome().length(); i<11; i++)
                s.append(" ");
            s.append("(");
            for (int i=String.valueOf(prodotto.prezzoUnitario()).length(); i<3; i++)
                s.append(" ");
            s.append(prodotto.prezzoUnitario());
            s.append(")\n");
            s.append("   x   ");
            s.append(quantita);
            s.append("    ");
            for (int i=String.valueOf(prezzoTotale()).length(); i<3; i++)
                s.append(" ");
            s.append(prezzoTotale());
            return s.toString();
        }
    }

}