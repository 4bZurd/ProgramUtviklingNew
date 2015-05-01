/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.Box.*;

/**
 *
 * @author Marthejansonskogen
 */
public class StatistikkPanel extends JPanel implements ActionListener
{
    private final String[] soket = {"", "Alle kunder som har en gitt type forsikring",
                               "Antall skademeldinger innen en gitt tidsperiode", "Antall"
                              + " tegnede forsikringer innen en gitt tidsperiode"};
    private final JComboBox<String> sokevelger;
    private final String[] forsikringer = {"", "Bilforsikring", "Båtforsikring",
                            "Hus- og innboforsikring", "Fritidsboligforsikring",
                            "Reiseforsikring", "Alle forsikringstyper"};
    private final JComboBox<String> forsikringsvelgeren;
    private final String[] utgifter = {"", "Total utbetaling av erstatninger i løpet"
                                        + " av et år",
                                "Total utbetaling av erstatninger for en gitt"
                                 + " forsikringstype i løpet av et år",
                                "Utbetaling til en gitt forsikringskunde i løpet"
                                + " av kundeforholdet"};
    private final JComboBox<String> utgiftsvelger;
    private final String[] inntekter = {"", "Total forsikringspremieinntekter i løpet"
                                        + " av et år", 
                                    "Total forsikringspremieinntekter for en"
                                    + " gitt forsikringstype i løpet av et år",
                                    "Forsikringspremieinntekter på en gitt "
                                + "forsikringskunde i løpet av kundeforholdet"};
    private final JComboBox<String> inntektsvelger;
    
    //Statistikk
    private final String[] statistikk = {"", "Øking/minking av antall skademeldinger"
                                        + " innenfor en gitt tidsperiode", 
                                        "Øking/minking av skademeldinger av en "
                                   + "bestemt type innenfor en gitt tidsperiode", 
                              "Øking/minking av de totale erstatningskostnadene", 
                                "Øking/minking av erstatningskostnadene for en "
                                + "gitt skadetype innenfor en gitt tidsperiode", 
                                   "Type forsikringer rangert etter antall"};
    private final JComboBox<String> statistikkvelger;
    private final JTextField stDatoDag;
    private final JTextField stDatoMnd;
    private final JTextField stDatoAr;
    private final JTextField slDatoDag;
    private final JTextField slDatoMnd;
    private final JTextField slDatoAr;
    
 public StatistikkPanel()
 {
     sokevelger = new JComboBox<>(soket);
        utgiftsvelger = new JComboBox<>(utgifter);
        inntektsvelger = new JComboBox<>(inntekter);
        forsikringsvelgeren = new JComboBox<>(forsikringer);
        statistikkvelger = new JComboBox<>(statistikk);
        stDatoDag = new JTextField(2);
        stDatoMnd = new JTextField(2);
        stDatoAr = new JTextField(4);
        slDatoDag = new JTextField(2);
        slDatoMnd = new JTextField(2);
        slDatoAr = new JTextField(4);
        
        JPanel avansertSokPanel1 = new JPanel();
        JPanel avansertSokPanel2 = new JPanel();
        JPanel avansertSokPanel3 = new JPanel();
        JPanel avansertSokPanel4 = new JPanel();
        avansertSokPanel3.setLayout(new BoxLayout(avansertSokPanel3, BoxLayout.PAGE_AXIS));
        avansertSokPanel1.setLayout(new GridLayout(11,1,2,2));
        avansertSokPanel2.setLayout(new GridLayout(6,3,2,2));
        avansertSokPanel1.add(new JLabel("Søk etter:"));
        avansertSokPanel1.add(sokevelger);
        avansertSokPanel1.add(new JLabel());
        avansertSokPanel1.add(new JLabel("Skriv ut utgifter:"));
        avansertSokPanel1.add(utgiftsvelger);
        avansertSokPanel1.add(new JLabel());
        avansertSokPanel1.add(new JLabel("Skriv ut inntekter:"));
        avansertSokPanel1.add(inntektsvelger);
        avansertSokPanel1.add(new JLabel());
        avansertSokPanel1.add(new JLabel("Skriv ut statistikk om:"));
        avansertSokPanel1.add(statistikkvelger);
        
        avansertSokPanel4.add(new JLabel("Velg forsikringstype: "));
        avansertSokPanel4.add(forsikringsvelgeren);
        avansertSokPanel2.add(new JLabel("Fra og med: "));
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel("Dag: (dd)"));
        avansertSokPanel2.add(new JLabel("Måned: (mm)"));
        avansertSokPanel2.add(new JLabel("År: (åååå)"));
        avansertSokPanel2.add(stDatoDag);
        avansertSokPanel2.add(stDatoMnd);
        avansertSokPanel2.add(stDatoAr);
        avansertSokPanel2.add(new JLabel("Til og med: "));
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel());
        avansertSokPanel2.add(new JLabel("Dag: (dd)"));
        avansertSokPanel2.add(new JLabel("Måned: (mm)"));
        avansertSokPanel2.add(new JLabel("År: (åååå)"));
        avansertSokPanel2.add(slDatoDag);
        avansertSokPanel2.add(slDatoMnd);
        avansertSokPanel2.add(slDatoAr);
        avansertSokPanel3.add(avansertSokPanel4);
        avansertSokPanel3.add(avansertSokPanel2);
        add(avansertSokPanel1);
        add(Box.createRigidArea(new Dimension(100,1)));
        add(avansertSokPanel3);
        
        sokevelger.addItemListener(new ItemListener()
        {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
        if (sokevelger.getSelectedIndex() != 0)
        {
            utgiftsvelger.setEnabled(false);
            inntektsvelger.setEnabled(false);
            statistikkvelger.setEnabled(false);
        }
        else if (sokevelger.getSelectedIndex() == 0)
        {
            utgiftsvelger.setEnabled(true);
            inntektsvelger.setEnabled(true);
            statistikkvelger.setEnabled(true);
        }
        }});
        utgiftsvelger.addItemListener(new ItemListener()
        {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
        if (utgiftsvelger.getSelectedIndex() != 0)
        {
            sokevelger.setEnabled(false);
            inntektsvelger.setEnabled(false);
            statistikkvelger.setEnabled(false);
        }
        else if (utgiftsvelger.getSelectedIndex() == 0)
            {
            sokevelger.setEnabled(true);
            inntektsvelger.setEnabled(true);
            statistikkvelger.setEnabled(true);
        }
        }});
        
        inntektsvelger.addItemListener(new ItemListener()
        {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
        if (inntektsvelger.getSelectedIndex() != 0)
        {
            sokevelger.setEnabled(false);
            utgiftsvelger.setEnabled(false);
            statistikkvelger.setEnabled(false);
        }
        else if (inntektsvelger.getSelectedIndex() == 0)
                {
                    sokevelger.setEnabled(true);
            utgiftsvelger.setEnabled(true);
            statistikkvelger.setEnabled(true);
                }
        }});
        
        statistikkvelger.addItemListener(new ItemListener()
        {
        @Override
        public void itemStateChanged(ItemEvent e)
        {
        if (statistikkvelger.getSelectedIndex() != 0)
        {
            sokevelger.setEnabled(false);
            utgiftsvelger.setEnabled(false);
            inntektsvelger.setEnabled(false);
        }
        else if (statistikkvelger.getSelectedIndex() == 0)
                {
                    sokevelger.setEnabled(true);
                    utgiftsvelger.setEnabled(true);
                    inntektsvelger.setEnabled(true);
                }
        }});
        
        
 }
 
        
        @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}
