/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import objekter.Kunde;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class KundePanel extends JPanel implements ActionListener
{
    private final JPanel kundeInfo_1;
    private final JPanel kundeInfo_2;
    private final JPanel knappeWrapper;
    private final JTextField regFornavn;
    private final JTextField regEtternavn;
    private final JTextField regPersnr;
    private final JTextField regTlfnr;
    private final JTextField regAdresse;
    private final JTextField regEpost;
    private final JTextArea regInfo;
    private final JButton regKunde;
    private final JButton kontaktKunde;
    private boolean nyKunde;
    private final AnsattVindu vindu;
    
    public KundePanel( AnsattVindu vindu)
    {
        this.vindu = vindu;
        kundeInfo_1 = new JPanel();
        kundeInfo_2 = new JPanel();
        knappeWrapper = new JPanel();
        
        regFornavn = new JTextField( 15 );
        regEtternavn = new JTextField( 15 );
        regPersnr = new JTextField( 11 );
        regTlfnr = new JTextField( 8 );
        regAdresse = new JTextField( 15 );
        regEpost = new JTextField(20);
        regInfo = new JTextArea(200,100);
        regKunde = new JButton("Registrer kunde" );
        kontaktKunde = new JButton("Kontakt");
        regKunde.addActionListener(this);
        kundeInfo_1.setLayout(new GridLayout(6,2,5,10));
        kundeInfo_1.add(new JLabel("Fornavn: "));
        kundeInfo_1.add(regFornavn);
        kundeInfo_1.add(new JLabel("Etternavn: "));
        kundeInfo_1.add(regEtternavn);
        kundeInfo_1.add(new JLabel("Personnummer: "));
        kundeInfo_1.add(regPersnr);
        kundeInfo_1.add(new JLabel("Telefonnummer: "));
        kundeInfo_1.add(regTlfnr);
        kundeInfo_1.add(new JLabel("Fakturaadresse: "));
        kundeInfo_1.add(regAdresse);
        kundeInfo_1.add( new JLabel("Epost: "));
        kundeInfo_1.add(regEpost);
        
        setLayout( new BorderLayout() );
        knappeWrapper.setLayout( new FlowLayout() );
        knappeWrapper.add(regKunde);
        add(kundeInfo_1, BorderLayout.CENTER );
        add(knappeWrapper, BorderLayout.SOUTH );
    }
    
    public KundePanel( AnsattVindu vindu, Kunde kunde )
    {
        this.vindu = vindu;
        kundeInfo_1 = new JPanel();
        kundeInfo_2 = new JPanel();
        knappeWrapper = new JPanel();
        
        regFornavn = new JTextField( 15 );
        regEtternavn = new JTextField( 15 );
        regPersnr = new JTextField( 11 );
        regTlfnr = new JTextField( 8 );
        regAdresse = new JTextField( 15 );
        regEpost = new JTextField(20);
        regInfo = new JTextArea(200,100);
        regKunde = new JButton("Registrer kunde" );
        kontaktKunde = new JButton("Kontakt");
        kontaktKunde.addActionListener(this);
        regKunde.addActionListener(this);
        kundeInfo_1.setLayout(new GridLayout(6,2,5,10));
        kundeInfo_1.add(new JLabel("Fornavn: "));
        kundeInfo_1.add(regFornavn);
        kundeInfo_1.add(new JLabel("Etternavn: "));
        kundeInfo_1.add(regEtternavn);
        kundeInfo_1.add(new JLabel("Personnummer: "));
        kundeInfo_1.add(regPersnr);
        kundeInfo_1.add(new JLabel("Telefonnummer: "));
        kundeInfo_1.add(regTlfnr);
        kundeInfo_1.add(new JLabel("Fakturaadresse: "));
        kundeInfo_1.add(regAdresse);
        kundeInfo_1.add( new JLabel("Epost: "));
        kundeInfo_1.add(regEpost);
        
        kundeInfo_2.setLayout( new GridLayout(6,2,5,10) );
        kundeInfo_2.add( new JLabel("Aktive forsikringer: "));
        kundeInfo_2.add( new JTextField(3));
        kundeInfo_2.add( new JLabel("Antall skademeldinger"));
        kundeInfo_2.add( new JTextField(3));
        kundeInfo_2.add( new JLabel("Kundenummer: "));
        kundeInfo_2.add( new JTextField(15));
        kundeInfo_2.add( new JLabel("Tidligere forhold"));
        kundeInfo_2.add( new JTextField(3));
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JLabel());
        kundeInfo_2.add( new JLabel());
        
        regFornavn.setText(kunde.getFornavn());
        regEtternavn.setText(kunde.getEtternavn());
        regPersnr.setText(kunde.getPersonnummer());
        regTlfnr.setText(kunde.getTlfnr());
        regAdresse.setText(kunde.getAdresse());
        regEpost.setText(kunde.getEpost());
        
        JPanel infobox = new JPanel();
        infobox.setLayout( new BoxLayout(infobox,BoxLayout.X_AXIS) );
        infobox.add( kundeInfo_1 );
        infobox.add(  Box.createRigidArea(new Dimension(14,20)) );
        infobox.add( kundeInfo_2 );
        
        knappeWrapper.setLayout( new FlowLayout() );
        JButton visForsikringer = new JButton("Vis Forsikringer");
        JButton visSkademeldinger = new JButton("Vis Skademeldinger");
        JButton nyForsikring = new JButton("Ny forsikring");
        JButton nySkademelding = new JButton("Ny Skademelding");
        knappeWrapper.add(visForsikringer);
        knappeWrapper.add(visSkademeldinger);
        knappeWrapper.add(nyForsikring);
        knappeWrapper.add(nySkademelding);
        knappeWrapper.add(kontaktKunde);
        
        
        setLayout( new BorderLayout()  );
        add( infobox, BorderLayout.CENTER );
        add(knappeWrapper, BorderLayout.SOUTH);
    }
    
    public Kunde nyKunde()
    {
        try
        { 
           String fornavn = regFornavn.getText();
           String etternavn = regEtternavn.getText();
            String adresse = regAdresse.getText();
            String telefonnummer = regTlfnr.getText();
            String epost = regEpost.getText();
            String personnummer = regPersnr.getText();
            //int fødselsår = Integer.parseInt(epost);
            //int fødselsmåned = Integer.parseInt(epost);
            //int fødselsdato = Integer.parseInt(epost);

            Date fødelsdato = new Date();
            Kunde kunde = new Kunde( fornavn, etternavn, adresse, telefonnummer,
            fødelsdato, epost, personnummer );
            return kunde;
        }
        catch( NumberFormatException e)
        {
            
        }
        return null;
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if( e.getSource() ==  kontaktKunde)
        {
            
        }
        else if( e.getSource()== regKunde)
        {
            Kunde nyKunde1 = nyKunde();
            vindu.leggTilNyFane( new ForsikringsPanel(), "Forsikringsvelger" );
        }
    }
}
