/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twodimstruct;

import java.util.*;

/**
 *
 * @author hussain
 */
public class TDKTreeMap<T> extends TDKStruct<T>{
    
    TreeMap<Point, T> map;
    public TDKTreeMap(int x0, int y0, int X, int Y) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.Y = Y;
        map = new TreeMap<>();
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
        /**
         * h is the map of points between R.x1,R.x2
         * filter h to get the desired set
         */
        
        SortedMap h = (R.A().compareTo(map.firstKey())>0)?map.tailMap(R.A()):map; // or something like that
        h = h.headMap(R.B()); // another thing similar
        
        Set<Point> keys = h.keySet();
        HashSet<FullPoint<T>> ret = new HashSet<>();
        for( Point k:keys){
            if(k.fallsWithin(R)){
                ret.add(new FullPoint<>(k.x(),k.y(),map.get(k)));
            }
        }
        return ret;
    }
    
}
