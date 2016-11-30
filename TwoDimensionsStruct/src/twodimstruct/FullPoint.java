package twodimstruct;


import twodimstruct.Point;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HPPro
 */
public class FullPoint<T> extends Point{
    public final static FullPoint NONE = new FullPoint(Integer.MAX_VALUE, Integer.MAX_VALUE,new Object());
    protected T data;
    public FullPoint(int x,int y, T data){
        super(x,y);
        this.data = data;
    }
    
    @Override
    public boolean equals(Object o){
        if(!(o instanceof FullPoint)) {
            return false;
        }
        FullPoint other = (FullPoint) o ;
        return other.x() == X && other.y() == Y && data.equals(other.getData());
    }

    public T getData() {
        return data;
    }
    
    public String tosString(){
        return "("+X+","+Y+")";
    }
}
