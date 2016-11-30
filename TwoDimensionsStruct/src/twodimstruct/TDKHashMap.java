/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twodimstruct;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author hussain
 */
public class TDKHashMap<T> extends TDKStruct<T> {
    
    HashMap<Point,T> map;

    public TDKHashMap(int x0, int y0, int X, int Y) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.Y = Y;
        map = new HashMap<>();
    }
    
    
    @Override
    public boolean insert(FullPoint<T> p) {
        if(isOut(p)){
            return false;
        }
        map.put(new Point(p.x(), p.y()), p.getData());
        return true;
    }

    @Override
    public FullPoint<T> find(int x, int y) {
        Point k = new Point(x, y);
        if(map.containsKey(k)){
            return new FullPoint<>(x,y,map.get(k));
        } else {
            return FullPoint.NONE;
        }
    }

    @Override
    public Set<FullPoint<T>> rangeSearch(Range R) {
        HashSet<FullPoint<T>> ret = new HashSet<>();
        Set<Point> keys = map.keySet();
        for(Point k : keys){
            ret.add(new FullPoint<>(k.x(),k.y(),map.get(k)));
        }
        return ret;
    }
    
}
