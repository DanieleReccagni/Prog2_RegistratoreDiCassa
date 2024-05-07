Registratore di Cassa
=====================

Descrizione
-----------

Scopo della prova è progettare e implementare una gerarchia di oggetti utili a rappresentare il comportamento di un _registratore di cassa_.

Si richiede di decidere quali interfacce e classi (concrete o astratte) implementare. Per ciascuna di esse, è necessario fornire una **descrizione** attraverso commenti nel codice (preferibilmente in formato Javadoc); questa documentazione dovrà includere le scelte relative alla **rappresentazione** dello stato (con particolare riferimento all'_invariante di rappresentazione_ e alla _funzione di astrazione_) e ai **metodi** (con particolare riferimento a _pre/post-condizioni_, _eccezioni_ ed eventuali _effetti collaterali_). Si sottolinea che _il risultato della prova sarà valutato sia in base a questa documentazione che al codice sorgente_.

### Dettagli pratici

La soluzione deve essere realizzata in una serie di file `.java` che dovranno essere salvati nella _stessa cartella_ in cui si trova il testo d'esame. È importante utilizzare solo il _default package_, evitando di creare sottocartelle o di introdurre dichiarazioni `package` nei file sorgenti. **Se queste indicazioni non vengono seguite correttamente, il materiale non sarà raccolto dal sistema di consegna e il lavoro non potrà essere valutato**.

Si prega di prestare attenzione agli _errori di compilazione_: l'intero contenuto dei file che il compilatore rifiuta di compilare _non verrà esaminato_. Nel caso in cui si verifichino errori di compilazione che non si riescono a correggere, è possibile considerare la possibilità di commentare le porzioni di codice che li causano tenendo tuttavia presente che il codice così commentato non sarà valutato (ma verrà almeno esaminato il resto del codice del file).

Le entità
---------

### Prodotti e listino

Una **prodotto** è caratterizzato da un _nome_ (di lunghezza compresa tra 1 e 10 caratteri, estremi compresi) e da un _prezzo unitario_ dato per semplicità da un intero (compreso tra 1 e 999 estremi compresi). Per semplicità, si assuma che il prezzo di un prodotto non cambi nel tempo.

Un **listino** è un elenco di prodotti che hanno tutti nomi distinti; deve essere possibile popolare un listino aggiungendo un prodotto alla volta così come reperire le informazioni riguardanti un prodotto a partire dal suo nome; è bene che si possa iterare sui prodotti del listino. Per semplicità, si assuma che, oltre all'aggiunga dei prodotti, il listino non subisca altre modifiche.

### Scontrini e righe

La **riga** di uno scontrino è una coppia costituita da un _prodotto_ e da una _quantità_ (un numero intero maggiore di 0); per ciascuna riga è definito il _prezzo totale_ (dato dalla moltiplicazione tra il prezzo unitario del prodotto e la quantità).

Un **scontrino** è un elenco di _righe_ tali per cui non ne esistono due consecutive con lo stesso prodotto (che può però comparire più volte nello scontrino, a patto che ciascuna coppia di occorrenze dello stesso prodotto sia separata da una o più righe con prodotti diversi). Ad esempio, gli scontrini

    Pane         10
    Acqua         5
    Pane         10
    ----------------
    Totale:      25
    

e

    Pane       ( 10)
       x   2     20
    Acqua         5
    ----------------
    Totale:      25
    

sono corretti, mentre non lo è lo scontrino

    Scontrino n. 6
    ----------------
    Acqua         5
    Pane         10
    Pane         10
    ----------------
    Totale:      25
    

Deve essere possibile iterare sulle righe dello scontrino, così come conoscere il _totale_ dello scontrino, ossia la somma dei prezzi totali di tutte le righe che lo compongono.

Gli scontrini sono di due tipi: "in elaborazione", o "emessi". A uno scontrino del primo tipo è possibile aggiungere dei prodotti oppure stornarli (ossia eliminarli, si osservi che stornare un prodotto non presente nello scontrino non produce alcun effetto o eccezione); uno scontrino "in elaborazione" può essere _chiuso_ e diventare "emesso".

Si osservi che, dato che gli scontrini sono mediamente brevi, piuttosto che preoccuparsi di questioni di efficienza, va data la massima priorità alla correttezza del codice.

#### Suggerimento

Se si decide di rappresentare uno scontrino "in elaborazione" come una lista di righe potrebbe essere piuttosto complicato implementare correttamente le operazioni di aggiunta e storno (evitando che creino righe consecutive riferite allo stesso prodotto); potrebbe risultare più semplice usare piuttosto una lista di prodotti (con ripetizioni, il che renderebbe banale implementare aggiunte e storni), lasciando poi all'iteratore il compito di produrre le righe "compattando" in una sola riga le occorrenze consecutive di uno stesso prodotto.

### Registratore di cassa

Un **registratore di cassa** memorizza un _listino_ ed è in grado di _emettere_ scontrini che contengano prodotti elencati nel sui listino. Il registratore conserva l'elenco di scontrini emessi sul quale deve essere possibile iterare; inoltre il registratore deve produrre un _registro_ (ossia una stringa contenente la concatenazione di tutti gli scontrini emessi),il registro può essere ottenuto solo se lo scontrino aperto non contiene prodotti.

Nel corso del suo funzionamento, il registratore ha sempre uno e un solo scontrino "in elaborazione" nel quale è in grado di _aggiungere_ o _stornare_ prodotti dato il loro nome (sollevando eventualmente un'eccezione nel caso in cui il prodotto non sia presente nel listino). Al termine dell'elaborazione dello scontrino in corso, il registratore lo _emette_ (ossia chiude lo scontrino "in elaborazione" ottenendone uno "emesso" che aggiunge alla lista di quelli emessi e predispone un nuovo scontrino "in elaborazione").

### La classe di test

La classe di test deve istanziare un listino una volta letta dal flusso di ingresso standard una sequenza di prodotti (uno per linea, con nome e prezzo unitario separati dal carattere `'|'`) seguiti da una linea vuota; utilizzando tale listino deve quindi istanziare un registratore di cassa e, proseguendo nella lettura del flusso di ingresso, deve interpretare i seguenti comandi:

*   `A` seguito dal nome di un prodotto, lo _aggiunge_ allo scontrino "in elaborazione";
*   `S` seguito dal nome di un prodotto, lo _storna_ (se presente) dallo scontrino "in elaborazione";
*   `E` _emette_ lo scontrino "in elaborazione";
*   `R` ottiene il _registro_ e lo emette nel flusso d'uscita.

l'esecuzione termina al termine del flusso d'ingresso.

Ad esempio, eseguendo la classe di test e avendo nel flusso d'ingresso:

    Pane|10
    Acqua|5
    
    A Acqua
    A Acqua
    A Pane
    A Acqua
    E
    A Acqua
    A Acqua
    A Pane
    A Acqua
    S Acqua
    E
    A Acqua
    A Acqua
    A Pane
    A Acqua
    S Acqua
    S Acqua
    E
    A Acqua
    A Acqua
    A Pane
    A Acqua
    S Acqua
    S Acqua
    S Acqua
    E
    A Pane
    A Acqua
    A Pane
    S Acqua
    E
    A Pane
    A Acqua
    A Pane
    E
    R
    

il programma emette nel flusso d'uscita

    Scontrino n. 1
    ----------------
    Acqua      (  5)
       x   2     10
    Pane         10
    Acqua         5
    ----------------
    Totale:      25
    
    Scontrino n. 2
    ----------------
    Acqua      (  5)
       x   2     10
    Pane         10
    ----------------
    Totale:      20
    
    Scontrino n. 3
    ----------------
    Acqua         5
    Pane         10
    ----------------
    Totale:      15
    
    Scontrino n. 4
    ----------------
    Pane         10
    ----------------
    Totale:      10
    
    Scontrino n. 5
    ----------------
    Pane       ( 10)
       x   2     20
    ----------------
    Totale:      20
    
    Scontrino n. 6
    ----------------
    Pane         10
    Acqua         5
    Pane         10
    ----------------
    Totale:      25
    

Si osservi il modo in cui sono indicate le righe degli scontrini: se la quantità di un prodotto aggiunto consecutivamente è 1, nel registro c'è una sola riga relativa ad esso (recante nome e prezzo unitario); viceversa, se la quantità è maggiore di 1 vengono prodotte due linee nel registro: la prima contiene il prodotto (con il prezzo unitario tra parentesi), mentre la seconda la quantità di volte per cui quel prodotto è stato aggiunto consecutivamente (a meno degli storni) seguita dal prezzo totale relativo.

Inoltre, si osservi la differenza negli ultimi due scontrini: la presenza dell'acqua nell'ultimo fa si che vengano mostrate tre righe, ma lo storno nel precedente fa si che le due righe del "Pane" vengano compattate in una sola.