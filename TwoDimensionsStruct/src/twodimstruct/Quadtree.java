package twodimstruct;

import java.util.HashSet;
import java.util.Set;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HPPro
 */
public class Quadtree <T>  extends TDKStruct<T>{
    
    protected boolean isleaf;
    protected FullPoint<T> p;
    protected Quadtree<T>[] child = new Quadtree[4];

    protected void initalize(int x0, int y0, int X, int Y) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
        this.Y = Y;
        isleaf = true;
        p = FullPoint.NONE;
    }
    
    /**
     * 
     * @param p (x0,y0) left-bottom corner
     * @param P (X ,Y) dimensions
     */
    public Quadtree(Point p, Point P) {
        initalize(p.x(), p.y(), P.x(), P.y());
    }

    public Quadtree(int x0, int y0, int X, int Y) {
        initalize(x0, y0, X, Y);
    }

    public T getData() {
        return p.getData();
    }

    protected Range getRange() {
        return new Range(x0,y0,x0+X,y0+Y);
    }
    
    protected int select(Point p) {
        int ret = 0;
        if (p.x() >= x0 + X / 2) {
            ret |= 1;
        }
        if (p.y() >= y0 + Y / 2) {
            ret |= 2;
        }
        return ret;
    }



    protected Point calcCorner(int idx) {
        return new Point(x0 + (idx & 1) * (X / 2), y0 + ((idx >> 1) & 1) * (Y / 2));
    }

    protected Point calcDim(int idx) {
        Point ret = new Point(X / 2, Y / 2);
        if ((idx & 1) == 1) {
            ret.setX(X - X / 2);
        }
        if ((idx & 2) == 2) {
            ret.setY(Y - Y / 2);
        }
        return ret;
    }

    @Override
    public boolean insert(FullPoint<T> p) {
        // needs some optimization
        if (isOut(p)) { // out, do nothing
            return false;
        }
        int idx;
        if (isleaf && this.p != FullPoint.NONE) {
            if (p.equals(this.p)) {
                return true; // already in
            }
            idx = select(this.p);
            if (child[idx] == null) {
                child[idx] = new Quadtree(calcCorner(idx), calcDim(idx));
            }
            child[idx].insert(this.p);
            this.p = null;
        } else if (isleaf) {
            this.p = p;
            return true;
        }
        idx = select(p);
        if (child[idx] == null) {
            child[idx] = new Quadtree(calcCorner(idx), calcDim(idx));
        }
        child[idx].insert(p);
        isleaf = false;
        return true;
    }

    @Override
    public FullPoint<T> find(int x, int y) {
        Point P = new Point(x, y);
        int idx = select(P);
        if (isOut(P)) {
            return FullPoint.NONE;
        }
        if (isleaf && p.x() == P.x() && p.y() == P.y()) {
            //logger.info(one more step, )
            return p;
        }
        if (!isleaf && child[idx] != null) {
            return child[idx].find(x, y);
        }
        return FullPoint.NONE;
    }

    @Override
    public HashSet<FullPoint<T>> rangeSearch(Range R) {
        HashSet<FullPoint<T>> ret = new HashSet<FullPoint<T>>();
        if(this.getRange().intersects(R)){
            if(isleaf && p != FullPoint.NONE && p.fallsWithin(R)){
                //System.out.print("("+p.x()+","+p.y()+")");
                ret.add(p);
            } else {
                for(Quadtree c : child){
                    if(c!=null) {
                        ret.addAll(c.rangeSearch(R));
                    }
                }
            }
        }
        return ret;
    }

}
