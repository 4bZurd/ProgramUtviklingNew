/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import objekter.*;
import register.*;

/**
 *
 * @author Odd, Thomas, Marthe
 */
public class ReiseforsikringPanel extends JPanel implements ActionListener, ForsikringsPanel
{
    private final AnsattVindu vindu;
    private HovedRegister register;
    private Reiseforsikring forsikring;
    private KundePanel kundePanel;
    
    private final JTextField reiseBelop;
    private final JTextField reiseTilbud;
    private final JTextField antbarn;
    private final JLabel antbarnLabel;
    private final JRadioButton forsorgerJa;
    private final JRadioButton forsorgerNei;
    private final JButton reiseGiTilbud;
    private final JButton beregnPris;
    private final JButton vilkarKnapp;
    private final String[] sone = {"", "Norden", "Europa", "Verden"};
    private final JComboBox<String> sonevelger;
    private final String[] egenandel = {"", "2000", "4000", "8000", "12000", "16000", "20000", "30000"};
    private final JComboBox<String> egenandelsvelger;
    private final String[] dekning = {"", "Reise", "Reise-Pluss"};
    private final JComboBox<String> dekningvelger;
    private final Kunde kunde;
    
    private double foreslåttPris; 
    private String vilkår;
    private int antBarn;
    private int belop;
    private boolean forsorger_b;
    private int sone_n;
    private String sonevalget;
    private int egenandelvalget;
    private String dekningvalget;
    private JButton rediger = new JButton("Rediger forsikring");
    private JButton lagreNyInfo = new JButton("Lagre forsikring");
    private JButton deaktiver = new JButton("Si opp forsikring");
    private JPanel knappePanel = new JPanel();
    private JLabel tilbudLabel;
    
    public ReiseforsikringPanel(Kunde k, AnsattVindu v)
    {
        vindu = v;
        register = vindu.getRegister();
        kunde = k;
        //forsikringsPanel implements ForsikringsPanel();
        reiseBelop = new JTextField( 7 );
        reiseTilbud = new JTextField( 7 );
        antbarn = new JTextField(2);
        tilbudLabel = new JLabel("Foreslått tilbud: ");
        antbarnLabel = new JLabel("Forsørger antall barn: ");
        antbarn.setEnabled(false);
        antbarnLabel.setEnabled(false);
        reiseGiTilbud = new JButton("Tegn forsikring");
        reiseGiTilbud.setVisible(false);
        beregnPris = new JButton("Beregn pris");
        vilkarKnapp = new JButton("Se vilkår");
        sonevelger = new JComboBox<>(sone);
        egenandelsvelger = new JComboBox<>(egenandel);
        dekningvelger = new JComboBox<>(dekning);
        forsorgerJa = new JRadioButton("Ja");
        forsorgerJa.setMnemonic(KeyEvent.VK_J);
        forsorgerNei = new JRadioButton("Nei");
        forsorgerNei.setMnemonic(KeyEvent.VK_N);
        ButtonGroup forsorger = new ButtonGroup();
        forsorger.add(forsorgerJa);
        forsorger.add(forsorgerNei);  
        
        JPanel tegnReisePanel1 = new JPanel();
        JPanel forsorgerP = new JPanel();
        tegnReisePanel1.setLayout(new GridLayout(6,4,1,5));
        forsorgerP.add(forsorgerJa);
        forsorgerP.add(forsorgerNei);
        tegnReisePanel1.add(new JLabel("Er kunde forsørger? "));
        tegnReisePanel1.add(forsorgerP);
        tegnReisePanel1.add(antbarnLabel);
        tegnReisePanel1.add(antbarn);
        tegnReisePanel1.add(new JLabel("Forsikringssone: "));
        tegnReisePanel1.add(sonevelger);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(vilkarKnapp);
        tegnReisePanel1.add(new JLabel("Egenandel: "));
        tegnReisePanel1.add(egenandelsvelger);
        tegnReisePanel1.add(new JLabel("Velg dekning: "));
        tegnReisePanel1.add(dekningvelger);
        tegnReisePanel1.add(new JLabel("Forsikringsbeløp: "));
        tegnReisePanel1.add(reiseBelop);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(beregnPris);
        tegnReisePanel1.add(tilbudLabel);
        tegnReisePanel1.add(reiseTilbud);
        tegnReisePanel1.add(new JLabel());
        tegnReisePanel1.add(reiseGiTilbud);
        add(tegnReisePanel1);
        
        VilkårLytter vilkårLytter = new VilkårLytter();
        reiseGiTilbud.addActionListener(this);
        beregnPris.addActionListener(this);
        vilkarKnapp.addActionListener(this);
        rediger.addActionListener(this);
        lagreNyInfo.addActionListener(this);
        deaktiver.addActionListener(this);
        dekningvelger.addItemListener(vilkårLytter);
        
        forsorgerJa.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
                if (e.getStateChange() == ItemEvent.SELECTED)
            {
            antbarn.setEnabled(true);
            antbarnLabel.setEnabled(true);
        }
        else if (e.getStateChange() == ItemEvent.DESELECTED)
        {
            antbarn.setEnabled(false);
            antbarnLabel.setEnabled(false);
        } 
        }});
    }
    
    // ikke fjern, ikke ferdig
    public void visForsikring( Forsikring f)
    {
        this.forsikring = (Reiseforsikring)f;
        sonevelger.setSelectedItem(forsikring.getSone());
        reiseBelop.setText(String.valueOf(forsikring.getBelopet()));
        egenandelsvelger.setSelectedItem(String.valueOf(forsikring.getEgenandel()));
        dekningvelger.setSelectedItem(forsikring.getVilkar());
        
        if (forsikring.isForsorger())
        {
            forsorgerJa.setSelected(true);
            antbarn.setText(String.valueOf(forsikring.getAntBarn()));
        }
        else
            forsorgerNei.setSelected(true);
        
        if (forsikring.erAktiv())
        {
            knappePanel.setLayout(new BoxLayout(knappePanel, BoxLayout.PAGE_AXIS));
            knappePanel.add(rediger);
            knappePanel.add(deaktiver);
            add(knappePanel);
        }
        
        tilbudLabel.setText("Årlig premie: ");
        disableFelter( this, reiseGiTilbud, beregnPris );
    }
    
    public boolean hentInfo()
    {
        sone_n = sonevelger.getSelectedIndex();
        int egenandel_n = egenandelsvelger.getSelectedIndex();
        int dekning_n = dekningvelger.getSelectedIndex();
                
                
        if(egenandel_n == 0 || sone_n == 0 || dekning_n == 0 || (!forsorgerJa.isSelected() && !forsorgerNei.isSelected()) )
        {
            String ut = "Det mangler informasjon om:\n";
            if (sone_n == 0)
            {
                ut += "Sone\n";
            }
            if (egenandel_n == 0)
            {
                ut += "Egenandel\n";
            }
            if (dekning_n == 0)
            {
                ut += "Dekning\n";
            }
            if (!forsorgerJa.isSelected() && !forsorgerNei.isSelected())
            {
                ut += "Forsørgervalg\n";
            }
            ut += "\nVennligst fyll ut denne informasjonen og prøv igjen.";
                          JOptionPane.showMessageDialog(null, ut, "Feilmelding",
                                                JOptionPane.ERROR_MESSAGE);
            return false;
            }
            else
            {
                try
                {
                    if (forsorgerJa.isSelected() && !forsorgerNei.isSelected())
                    {
                        forsorger_b = true;
                        antBarn = Integer.parseInt(antbarn.getText());
                    }
                    else if (!forsorgerJa.isSelected() && forsorgerNei.isSelected())
                    {
                        forsorger_b = false;
                        antBarn = 0;
                    }
                    
                    egenandelvalget = Integer.parseInt(egenandelsvelger.getItemAt(egenandel_n));
                    sonevalget = sonevelger.getItemAt(sone_n);
                    dekningvalget = dekningvelger.getItemAt(dekning_n);
                    belop = Integer.parseInt( reiseBelop.getText() );
                    return true;
                }
                catch( NumberFormatException e )
                {
                    vindu.visFeilmelding("Feilmelding", "Feil format i et av tekstfeltene. ");
                    return false;
                }
            }
    }
    
    public void beregnPris()
    {
        if (hentInfo())
        {
            double foreslåttPris = ForsikringsKalulator.beregnReiseforsikring(egenandelvalget, dekningvalget, forsorger_b , antBarn, sonevalget, belop );
            reiseTilbud.setVisible(true);
            reiseTilbud.setText(String.valueOf(foreslåttPris));
            reiseTilbud.setToolTipText("Kan redigeres");
            reiseGiTilbud.setVisible(true);
        }
    }
    
    public void tegnNy()
    {
        if (hentInfo())
        {
            if( vindu.getRegister().getKundeliste().erKunde(kunde) == false )
            {
                vindu.getAnsatt().leggTilKundenøkel(kunde.getPersonnummer());
                register.nyKunde(kunde);
            }
            
            Reiseforsikring nyForsikring = new Reiseforsikring(kunde, egenandelvalget, dekningvalget, forsorger_b, antBarn, sonevalget, belop);
            nyForsikring.setArligPremie(foreslåttPris);
            register.nyForsikring(nyForsikring);
            
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
            
            vindu.visInformasjon("Beskjed", "Du har nå tegnet en ny forsikring på " + nyForsikring.getKunde().getFornavn() + " " + nyForsikring.getKunde().getEtternavn());
        }
    }
    
    public void oppdaterForsikring()
    {
        if (hentInfo())
        {
            forsikring.setForsorger(forsorger_b);
            forsikring.setAntBarn(antBarn);
            forsikring.setSone(sonevalget);
            forsikring.setEgenandel(egenandelvalget);
            forsikring.setBelopet(belop);
            forsikring.setVilkar(dekningvalget);
            forsikring.setArligPremie(foreslåttPris);
              
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
        }  
    }
    
    public void rediger()
    {
        enableFelter( this, beregnPris );
        knappePanel.add(lagreNyInfo);
        tilbudLabel.setText("Foreslått tilbud: ");
        beregnPris.setText("Beregn ny pris");   
        revalidate();
        repaint(); 
    } 
    
    public void deaktiverForsikring()
    {
        int svar = JOptionPane.showConfirmDialog(null, "Er du sikker på at du vil deaktivere denne forsikringen?", "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()), JOptionPane.YES_NO_OPTION);
        if (svar == JOptionPane.YES_OPTION)
        {
            knappePanel.remove(rediger);
            knappePanel.remove(lagreNyInfo);
            this.remove(beregnPris);
            knappePanel.remove(deaktiver);
            forsikring.setAktiver(false);
            JOptionPane.showMessageDialog(null, "Forsikring " + String.valueOf(forsikring.getForsikringsnummer()) + " er ikke lenger aktiv.", "Bekreftelse", JOptionPane.PLAIN_MESSAGE);
              
            if(kundePanel != null)
                kundePanel.oppdaterVindu();
              
            repaint();
            revalidate();
        }
    }
    
    public void visVilkår()
    {
        if( forsikring == null )
            visForsikringensVilkår("Ny Fritidsboligforsikring " + kunde.getFornavn() + " " + kunde.getEtternavn() , vilkår);
        else
            visForsikringensVilkår("Vilkår" + forsikring.getForsikringsnummer(), forsikring.getVilkar());
    }
    
    public void leggTilKundePanel( KundePanel panel )
    {
        kundePanel = panel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == reiseGiTilbud)
        {
            tegnNy();
        }
        else if (e.getSource() == beregnPris)
        {
            beregnPris();
        }
        else if (e.getSource() == vilkarKnapp)
        {
            visVilkår();
        }
        else if (e.getSource() == rediger)
        {
            rediger();
        }
        else if (e.getSource() == lagreNyInfo)
        {
            oppdaterForsikring();
        }
        else if (e.getSource() == deaktiver)
        {
           deaktiverForsikring();
        }
    }
    
    private class VilkårLytter implements ItemListener, ForsikringsPanel
    {
        @Override
        public void itemStateChanged(ItemEvent e) 
        {
            if( dekningvelger.getSelectedIndex() != 0)
                vilkår = this.velgVilkår( "Reise"+ dekningvelger.getItemAt(dekningvelger.getSelectedIndex()) );
        }
    }
    
}
