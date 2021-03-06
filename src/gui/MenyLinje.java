/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

/**
 *
 * @author Odd
 */
public class MenyLinje extends MenuBar
{
    private final MenyLytter lytter = new MenyLytter();
    private final Menu filMeny = new Menu("Fil");
    private final Menu vinduMeny = new Menu("Vindu");
    private final Menu adminMeny = new Menu("Admin");
    private final MenuItem loggUtKnapp = new MenuItem("Logg Ut");
    private final MenuItem avsluttKnapp = new MenuItem("Avslutt");
    private final AnsattVindu ansattVindu;
    
    
    public MenyLinje(AnsattVindu vindu )
    {
        ansattVindu = vindu;
        filMeny.add(loggUtKnapp);
        filMeny.add(avsluttKnapp);
        this.add(filMeny);
        this.add(vinduMeny);
        
        loggUtKnapp.addActionListener(lytter);
        avsluttKnapp.addActionListener(lytter);
    }
    
    public void avslutt()
    {
        ansattVindu.dispatchEvent(new WindowEvent( ansattVindu,WindowEvent.WINDOW_CLOSING));
    }
    
    private class MenyLytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() == loggUtKnapp)
            {
                ansattVindu.setAnsatt(null);
                ansattVindu.setVisible(false);
                ansattVindu.visLogin();
            }
            else if( e.getSource() == avsluttKnapp )
            {
                avslutt();
            }
        }
        
    }
}
