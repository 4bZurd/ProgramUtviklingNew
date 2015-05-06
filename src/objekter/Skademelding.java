/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class Skademelding
{
    
    private final Forsikring forsikring;
    private Date dato;
    private Date opprettetdato;
    private final int skadenummer;
    private static int nestenr = 20000;
    private String skadetype;
    //private String skademeldingsskjema;
    private String beskrivelse;
    //private FIL bilde;
    Vitne vitne;
    private int takseringsbelop;
    private int erstatningsbelop;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    
    public Skademelding( Forsikring forsikring, Date dato, String skadetype, String beskrivelse, int takseringsbelop, int erstatingsbelop )
    {
        this.forsikring = forsikring;
        this.dato = dato;
        this.skadetype = skadetype;
        this.beskrivelse = beskrivelse;
        this.takseringsbelop = takseringsbelop;
        this.erstatningsbelop = erstatingsbelop;
        skadenummer = nestenr++;
        opprettetdato = new Date();
    }
    
    public Forsikring getForsikring()
    {
        return forsikring;
    }
    
    public Date getOpprettetDato()
    {
        return opprettetdato;
    }
    
    public Date getSkadeDato()
    {
        return dato;
    }
    
    public int getErstatningsbelop()
    {
        return erstatningsbelop;
    }
    
    public int getTakseringsbelop()
    {
        return takseringsbelop;
    }
    
    public int getSkadenummer()
    {
        return skadenummer;
    }
    
    public void setBeskrivelse( String input )
    {
        this.beskrivelse = input;
    }

    @Override
    public String toString()
    {
        String ut = "\nOpprettet dato: " + sdf.format(opprettetdato) + "\nSkadenummer: " + skadenummer
                + "\nDato for skaden: " + dato + "\nSkadetype: " + skadetype +  
                "\nBeskrivelse av skaden: " + beskrivelse + "\nTakseringsbeløp: "
                + takseringsbelop + "\nErstatningsbeløp: " + erstatningsbelop;
        return ut;
    }
    
    
}
