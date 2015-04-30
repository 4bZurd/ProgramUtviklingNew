/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import objekter.Kunde;
import register.HovedRegister;

/**
 *
 * @author Odd
 */
public class AnsattVindu extends JFrame
{
    private final Container mainContainer;
    private final JPanel hovedPanel;
    private final JPanel hovedPanelBunn;
    private final JTabbedPane fanekort;
    private final MenyPanel menyPanel;
    private final JPanel tabellContainer;
    private final JPanel bunnContainer;
    private final JPanel søkePanel;
    
    private final JTextField søkefelt;
    private final JTextField søkefeltFornavn;
    private final JTextField søkefeltEtternavn;
    private final JButton søkekanpp;
    
    private HovedRegister register;
    
    public AnsattVindu()
    {
        super("Forsikring Vindu");
        setSize(1600,900);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        register = new HovedRegister();
        Lytter lytter = new Lytter();
        mainContainer = getContentPane();
        fanekort =  new JTabbedPane();
        hovedPanelBunn =  new JPanel();
        hovedPanel = new JPanel();
        menyPanel = new MenyPanel(this);
        tabellContainer = new JPanel();
        bunnContainer = new JPanel();
        søkePanel = new JPanel();
        
        søkefelt = new JTextField(15);
        søkefeltFornavn = new JTextField(25);
        søkefeltEtternavn = new JTextField(25);
        søkekanpp = new JButton("Søk");
        søkekanpp.addActionListener(lytter);
        
        mainContainer.setLayout( new GridBagLayout() );
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainContainer.add( menyPanel, gbc);
        
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 100;
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainContainer.add( hovedPanel, gbc);  
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainContainer.add( hovedPanelBunn, gbc);
        
        hovedPanel.setLayout( new GridBagLayout() );
        gbc.gridx = 0;
        gbc.gridy = 0;
        hovedPanel.add(fanekort, gbc);
        
        hovedPanelBunn.setLayout( new BorderLayout());
        
        
        visTabellPanel(register.getKundeliste().alleKunder());
    }
    
    public void lukkFanekort()
    {
        
    }
    
    public void leggTilNyFane( JPanel panel )
    {
        JPanel wrapper = new JPanel();
        wrapper.add( panel );
        fanekort.add(wrapper,"Ny Kunde");
        revalidate();
        repaint();
    }
    
    public void visTabellPanel( List<Kunde> list )
    {
        TabellModell tabellModell = new TabellModell(list);
        JTable tabell = new JTable(tabellModell);
        tabell.addMouseListener(new MouseAdapter() {
         @Override
        public void mouseReleased(MouseEvent e) 
        {
            int r = tabell.rowAtPoint(e.getPoint());
            if (r >= 0 && r < tabell.getRowCount()) 
            {
                tabell.setRowSelectionInterval(r, r);
            }
            else 
            {
                tabell.clearSelection();
            }
            
            int rowindex = tabell.getSelectedRow();
            if (rowindex < 0)
                return;
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable ) 
            {
                JPopupMenu popup = new JPopupMenu();
                popup.add( new JMenuItem("Vis Informasjon"));
                popup.add( new JMenuItem("Ny Forsikring"));
                popup.add( new JMenuItem("Ny Skademelding"));
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    });
        tabell.setRowHeight(20);
        bunnContainer.setLayout( new BorderLayout() );
        søkePanel.setLayout( new FlowLayout() );
        søkePanel.add( new JLabel("ID: "));
        søkePanel.add(søkefelt);
        søkePanel.add(new JLabel("Fornavn: "));
        søkePanel.add( søkefeltFornavn );
        søkePanel.add(new JLabel("Etternavn: "));
        søkePanel.add( søkefeltEtternavn );
        søkePanel.add( søkekanpp);
        bunnContainer.add(søkePanel,BorderLayout.NORTH);
        tabellContainer.setLayout( new BorderLayout());
        tabellContainer.add(tabell.getTableHeader(), BorderLayout.NORTH);
        tabellContainer.add(tabell, BorderLayout.CENTER);
        bunnContainer.add( new JScrollPane(tabellContainer));
        hovedPanelBunn.add( bunnContainer, BorderLayout.CENTER);
    }
    
    private class Lytter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if( e.getSource() != søkekanpp )
            {
                String søkeord = søkefelt.getText();
                String fornavn = søkefeltFornavn.getText();
                String etternavn = søkefeltEtternavn.getText();
                List<Kunde> testliste = register.finnKundeMedNavn(fornavn,etternavn);
                visTabellPanel(testliste);
                revalidate();
                repaint();
            }
        }
        
    }
}
