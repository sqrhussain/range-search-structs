package twodimstruct;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HPPro
 */
public class RangeTester {
    static FullPoint<String> [] all = new FullPoint[234567];
    static Set [] allData = new Set[234567];
    static int [] qx1 = new int[234567];
    static int [] qy1 = new int[234567];
    static int [] qx2 = new int[234567];
    static int [] qy2 = new int[234567];
    static int qn,n;
    static double insertionTime,searchTime;
    static void read() throws FileNotFoundException{
        Scanner sc = new Scanner(new File("rangetest.in"));
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
        int x1,x2,y1,y2;
        while(true){
            x1 = sc.nextInt();
            y1 = sc.nextInt();
            x2 = sc.nextInt();
            y2 = sc.nextInt();
            if(x1<0 || y1<0 || x2<0 || y2<0) {
                break;
            }
            qx1 [i] = x1;
            qy1 [i] = y1;
            qx2 [i] = x2;
            qy2 [i] = y2;
            i++;
            
        }
        qn = i;
    }
    
    static void write() throws FileNotFoundException{
        
        PrintStream pw = new PrintStream("TreeMaprangetest.out");
        for(int i =0 ; i<qn;i++){
            pw.println(allData[i].size());
        }
    }
    
    static void report() throws FileNotFoundException{
        PrintStream rep = new PrintStream("TreeMaprangereport.out");
        rep.printf("Total insertion time: %f milliseconds for %d insertions.%n", insertionTime/1000000, n);
        rep.printf("Average insertion time: %f milliseconds.%n", insertionTime/n/1000000);
        
        rep.printf("Total searching time: %f milliseconds for %d queries.%n", searchTime/1000000, qn);
        rep.printf("Average searching time: %f milliseconds.%n", searchTime/1000000/qn);
        
    }
   
    public static void main(String []args) throws FileNotFoundException{
        TDKStruct<String> container = new TDKTreeMap<>(10, 10, 200000, 200000);
        
        
        read();
        insertionTime = System.nanoTime();
        for(int i=0;i<n;i++){
           container.insert(all[i]);
           //map.put(new Pair(all[i].x(), all[i].y()),all[i].getData());
        }
        insertionTime -= System.nanoTime();
        insertionTime = -insertionTime;
        
        searchTime = System.nanoTime();
        for(int i=0;i<qn;i++){
            allData[i] = container.rangeSearch(new Range(qx1[i],qy1[i],qx2[i],qy2[i]));
        }
        searchTime -= System.nanoTime();
        searchTime = - searchTime;
        write();
        
        report();
    }
}
