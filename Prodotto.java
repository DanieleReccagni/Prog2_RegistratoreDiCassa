import java.util.Objects;

// Record immutabile che rappresenta un generico Prodotto, caratterizzato dal suo nome e dal suo prezzoUnitario
public record Prodotto(String nome, int prezzoUnitario) {

    // RI: nome deve essere non nullo
    //     nome deve avere una lunghezza >= 1 e <= 10
    //     prezzoUnitario deve essere >= 1 e <= 999
    // AF: nome rappresenta il nome del prodotto
    //     prezzoUnitario rappresenta il prezzo dell'acquisto di un'unità di prodotto

    // COSTRUTTORI
    // Post-condizioni: inizializza il prodotto con nome e prezzoUnitario
    //                  solleva un'eccezione di tipo NullPointerException se nome è nullo
    //                  solleva un'eccezione di tipo IllegalArgumentException se la lunghezza di nome è < 1 o > 10
    //                  solleva un'eccezione di tipo IllegalArgumentException se prezzoUnitario < 1 o prezzoUnitario > 999
    public Prodotto(String nome, int prezzoUnitario) {
        Objects.requireNonNull(nome);
        if (nome.length() < 1 || nome.length() > 10)
            throw new IllegalArgumentException();
        if (prezzoUnitario < 1 || prezzoUnitario > 999)
            throw new IllegalArgumentException();
        this.nome = nome;
        this.prezzoUnitario = prezzoUnitario;
    }

    // METODI
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(nome);
        for (int i=nome.length() + String.valueOf(prezzoUnitario).length(); i<15; i++)
            s.append(" ");
        s.append(prezzoUnitario);
        return s.toString();
    }

}