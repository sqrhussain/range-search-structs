/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package twodimstruct;

/**
 *
 * @author HPPro
 */
public class InfoRange extends Range{
    protected int minX,minY,maxX,maxY;
    protected boolean empty;
    
    public InfoRange(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        empty = true;
        minX = this.x1;
        minY = this.y1;
        maxX = this.x2;
        maxY = this.y2;
    }
    
    public InfoRange(int x1, int y1, int x2, int y2, Point p) {
        super(x1, y1, x2, y2);
        empty = true;
        minX = maxX = p.x();
        minY = maxY = p.y();
    }
    
    public InfoRange(Point a,Point b,Point z){
        super(a.x(),a.y(),b.x(),b.y());
        empty = true;
        minX = maxX = z.x();
        minY = maxY = z.y();
        
    }
    
    public void update(Point p){
        if(empty){
            minX = maxX = p.x();
            minY = maxY = p.y();
        } else {
            minX = Math.min(minX, p.x());
            minY = Math.min(minY, p.y());
            maxX = Math.max(maxX, p.x());
            maxY = Math.max(maxY, p.y());
        }
    }
    
    public boolean isEmpty(){
        return empty;
    }
    
}
