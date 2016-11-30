package twodimstruct;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HPPro
 */
public class Tester {
    static FullPoint [] all = new FullPoint[1234567];
    static FullPoint [] allData = new FullPoint[1234567];
    static int [] qx = new int[1234567];
    static int [] qy = new int[1234567];
    static int qn,n;
    static double insertionTime,searchTime;
    static void read() throws FileNotFoundException{
        Scanner sc = new Scanner(new File("../NormalTest files/input/03normaltest.in"));
        int i=0;
        int x,y;
        String c;
        
        while(true){
            x = sc.nextInt();
            y = sc.nextInt();
            if(x<0 || y<0) {
                break;
            }
            c = sc.next();
            all [i++] = new FullPoint(x,y,c);
        }
        n=i;
        i = 0;
        
        while(true){
            x = sc.nextInt();
            y = sc.nextInt();
            if(x<0 || y<0) {
                break;
            }
            qx [i] = x;
            qy [i] = y;
            i++;
            
        }
        qn = i;
    }
    
    static void write() throws FileNotFoundException{
        
        PrintStream pw = new PrintStream("../NormalTest files/output/03normaltest.out");
        for(int i =0 ; i<qn;i++){
            
            pw.println(allData[i]!=FullPoint.NONE ?
                allData[i].getData().toString() :
                "Not Found"  );
        }
        
    }
    
    static void report() throws FileNotFoundException{
        PrintStream rep = new PrintStream("../NormalTest files/reports/03normalreport.out");
        rep.printf("Total insertion time: %f milliseconds for %d insertions.%n", insertionTime/1000000, n);
        rep.printf("Average insertion time: %f milliseconds.%n", insertionTime/n/1000000);
        
        rep.printf("Total searching time: %f milliseconds for %d queries.%n", searchTime/1000000, qn);
        rep.printf("Average searching time: %f milliseconds.%n", searchTime/1000000/qn);
        
    }
    public static void main(String []args) throws FileNotFoundException{
        TDKStruct container = new Quadtree(0, 0, 200001, 200001);
        
        read();
        insertionTime = System.nanoTime();
        for(int i=0;i<n;i++){
            container.insert(all[i]);
        }
        insertionTime -= System.nanoTime();
        insertionTime = -insertionTime;
        
        searchTime = System.nanoTime();
        for(int i=0;i<qn;i++){
            allData[i] = container.find(qx[i], qy[i]);
        }
        searchTime -= System.nanoTime();
        searchTime = - searchTime;
        write();
        
        report();
    }
}
