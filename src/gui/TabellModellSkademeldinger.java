/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import objekter.Skademelding;

/**
 *
 * @author Odd
 */

public class TabellModellSkademeldinger extends AbstractTableModel implements ActionListener
{
    private final String[] kolonnenavn = {"Skadenummer", "Tilhørende forsikring", "Skadedato", "Opprettet Dato", "Takst", "Utbetalt"};
    private final Object[][] innhold;
    private final List<Skademelding> skademeldinger;
    private final KundePanel panel;
    
    public TabellModellSkademeldinger( List<Skademelding> skademeldinger, KundePanel panel )
    {
        this.skademeldinger = skademeldinger;
        this.panel = panel;
        innhold = new Object[this.skademeldinger.size()][kolonnenavn.length];
        
        int teller = 0;
        for(Skademelding skademelding : skademeldinger)
        {
            innhold[teller][0] = skademelding.getSkadenummer();
            innhold[teller][1] = skademelding.getForsikring().getType();
            innhold[teller][2] = skademelding.getSkadeDato();
            innhold[teller][3] = skademelding.getOpprettetDato();
            innhold[teller][4] = skademelding.getTakseringsbelop();
            innhold[teller][5] = skademelding.getErstatningsbelop();
            teller++;
        }
    }

    @Override
    public String getColumnName( int i )
    {
        return kolonnenavn[i];
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) 
    {
        return innhold[rowIndex][columnIndex];
    }

    @Override
    public int getRowCount() 
    {
        return skademeldinger.size();
    }

    @Override
    public int getColumnCount() 
    {
        return kolonnenavn.length;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
}