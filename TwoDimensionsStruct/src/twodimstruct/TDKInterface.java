package twodimstruct;

import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HPPro
 */
public interface TDKInterface<T> {
    // TDK = Two Dimensional Key
    
    public boolean insert(FullPoint<T> p);
    public FullPoint<T> find(int x, int y);
    public Set< FullPoint<T> > rangeSearch(Range R);
    
}
