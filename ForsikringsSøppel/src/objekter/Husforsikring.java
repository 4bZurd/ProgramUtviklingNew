/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objekter;

/**
 *
 * @author Marthejansonskogen
 */
public class Husforsikring extends Eiendomsforsikring
{
    public Husforsikring( Kunde k, String hadresse, int byggar, String bt, String mat, String stand, int kvm, int belopByg,
                             int belopInn)
    {
    super( k, hadresse, byggar, bt, mat, stand, kvm, belopByg, belopInn);
    }
    
    public void beregnPris( Kunde kunde)
    {
        
    }
    
    @Override
    public String toString()
    {
        String utskrift = super.toString();
        return utskrift;
    }
}//end of class
