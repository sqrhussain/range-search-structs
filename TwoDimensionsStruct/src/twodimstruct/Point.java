package twodimstruct;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HPPro
 */
public class Point implements Comparable<Point>{
    public final static Point NONE = new Point (Integer.MAX_VALUE, Integer.MAX_VALUE);
    protected int X,Y;

    public Point(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }
    
    public int x(){return X;}
    
    public int y(){return Y;}
    
    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(!(o instanceof Point)){
            return false;
        }
        Point that = (Point) o;
        return X == that.x() && Y == that.y();
    }
    
    @Override
    public int hashCode(){
        return X + Y * 1000000007;
    }
    
    

    public void setX(int i) {
        X=i;
    }
    
    public void setY(int i) {
        Y=i;
    }
    
    public boolean fallsWithin(Range R){
        return X>=R.left() && X<R.right()
                && Y>=R.bottom() && Y<R.top();
    }

    @Override
    public int compareTo(Point p) {
        if(this.equals(p)) return 0;
        if(this.x() < p.x() || (this.x() == p.x() && this.y() < p.y())){
            return 1;
        } 
        return -1;
    }
}
