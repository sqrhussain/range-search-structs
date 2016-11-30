/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twodimstruct;

/**
 *
 * @author HPPro
 */
public class Range {
    protected int x1,y1,x2,y2;

    public Range(int x1, int y1, int x2, int y2) {
        this.x1 = Math.min(x1,x2);
        this.y1 = Math.min(y1,y2);
        this.x2 = Math.max(x1,x2);
        this.y2 = Math.max(y1,y2);
    }

    public Range(Point A, Point B) {
        x1 = Math.min(A.x(),B.x());
        y1 = Math.min(A.y(),B.y());
        x2 = Math.max(A.x(),B.x());
        y2 = Math.max(A.y(),B.y());
    }


    public boolean intersects(Range R){
        return !(x2<R.left() || R.right()<x1 || y2<R.bottom() || R.top()<y1);
    }
    
    
    /**
     * 
     * @return bottom-left corner
     */
    public Point A(){ 
        return new Point(x1,y1);
    }
    
    /**
     * 
     * @return top-right corner
     */
    public Point B(){ 
        return new Point(x2,y2);
    }
    
    /**
     * 
     * @return bottom-right corner
     */
    public Point C(){ 
        return new Point(x2,y1);
    }
    
    /**
     * 
     * @return top-left corner
     */
    public Point D(){ 
        return new Point(x1,y2);
    }
    
    public int left()   {return x1;}
    
    public int right()  {return x2;}
    
    public int bottom() {return y1;}
    
    public int top()    {return y2;}
    /**
     * 
     * @return width of the range
     */
    public int X()      {return x2-x1;}
    
    /**
     * 
     * @return height of the range
     */
    public int Y()      {return y2-y1;}
    
    public boolean equals(Object o){
        Range r;
        if(o instanceof Range ){
            r = (Range) o;
            return r.A().equals(this.A()) && r.B().equals(this.B());
        }
        return false;
    }
}
