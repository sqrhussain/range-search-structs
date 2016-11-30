/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twodimstruct;

/**
 *
 * @author hussain
 */
public abstract class TDKStruct<T> implements TDKInterface<T>{
    
    protected int x0, y0, X, Y;
    
    protected boolean isOut(Point p) {
        return (p.x() < x0 || p.x() >= x0 + X || p.y() < y0 || p.y() >= y0 + Y);
    }

    protected boolean isIn(Point p) {
        return !isOut(p);
    }
}
