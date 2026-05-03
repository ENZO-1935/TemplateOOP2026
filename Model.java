package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;
import java.sql.Time;

/**
 * Enumerazioni fondamentali per definire i tipi di dato personalizzati ed evitare errori.
 */
enum Stato {
    IN_ATTESA,
    APPROVATA,
    RIFIUTATA
}

enum TipoTirocinio {
    INTERNO,
    ESTERNO
}

/**
 * Classe astratta Utente: base per la gerarchia degli utenti.
 * Applica l'ereditarietà con modificatore protected (#) per i campi.
 */
abstract class Utente {
    protected int id;
    protected String nome;
    protected String cognome;
    protected String email;
    protected String username;
    protected String password;
}

/**
 * Classe Studente: estende Utente.
 * Gestisce le richieste di tirocinio e il caricamento della tesi.
 */
class Studente extends Utente {
    private String matricola;
    
    // Relazione: 1 Studente -> * Richieste
    private List<RichiestaTirocinio> richiesteEffettuate = new ArrayList<>();

    public void richiediTirocinio(ArgomentoTirocinio argomento) {
        // Logica per istanziare una RichiestaTirocinio e collegarla
    }

    public void caricaTesi(File file, SedutaLaurea seduta) {
        // Logica per salvare il file e creare l'oggetto Tesi
    }
}

/**
 * Classe Docente: estende Utente.
 * Gestisce la proposta di argomenti e la valutazione di richieste/tesi.
 */
class Docente extends Utente {
    // Relazione: 1 Docente -> * Argomenti Proposti
    private List<ArgomentoTirocinio> argomentiProposti = new ArrayList<>();

    public void aggiungiArgomento(ArgomentoTirocinio argomento) {
        this.argomentiProposti.add(argomento);
    }

    public void valutaRichiesta(RichiestaTirocinio richiesta, boolean approvata) {
        if(approvata) {
            richiesta.setStato(Stato.APPROVATA);
        } else {
            richiesta.setStato(Stato.RIFIUTATA);
        }
    }

    public void valutaTesi(Tesi tesi, boolean approvata) {
        // Logica per approvare o rifiutare la tesi
    }

    public List<RichiestaTirocinio> getTirociniInCorso() {
        // Logica per filtrare e restituire solo le richieste con Stato.APPROVATA
        return new ArrayList<>();
    }
}

/**
 * Classe Coordinatore: estende Docente.
 * Responsabile dell'organizzazione delle sedute e della formazione commissioni.
 */
class Coordinatore extends Docente {
    // Relazione: 1 Coordinatore -> * Sedute Organizzate
    private List<SedutaLaurea> seduteOrganizzate = new ArrayList<>();

    public void inserisciSeduta(SedutaLaurea seduta) {
        this.seduteOrganizzate.add(seduta);
    }

    public List<Docente> formaCommissione(SedutaLaurea seduta) {
        // Logica per estrarre tutti i relatori collegati alle tesi di questa seduta
        return new ArrayList<>();
    }
}

/**
 * Classe ArgomentoTirocinio: rappresenta il core dei tirocini proposti.
 */
class ArgomentoTirocinio {
    private int id;
    private String titolo;
    private TipoTirocinio tipo;
    private String referenteAziendale; // Può essere null se il tipo è INTERNO
}

/**
 * Classe RichiestaTirocinio: gestisce lo stato dell'associazione tra Studente e Argomento.
 */
class RichiestaTirocinio {
    private int id;
    private Stato stato = Stato.IN_ATTESA; // Valore di default
    
    // Relazione: * Richieste -> 1 Argomento
    private ArgomentoTirocinio argomento;
    
    // Relazione: 1 Richiesta -> 0..1 Tesi
    private Tesi tesi;

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}

/**
 * Classe Tesi: rappresenta l'elaborato finale caricato dallo studente.
 */
class Tesi {
    private int id;
    private String filePath;
    private Stato stato = Stato.IN_ATTESA;
    
    // Relazione: * Tesi -> 1 SedutaLaurea
    private SedutaLaurea seduta;
}

/**
 * Classe SedutaLaurea: organizza l'evento finale di presentazione.
 */
class SedutaLaurea {
    private int id;
    private Date data;
    private Time ora;
    private String luogo;
    
    // Associazione inversa: la seduta sa quali tesi ospita
    private List<Tesi> tesiPresentate = new ArrayList<>();
}
