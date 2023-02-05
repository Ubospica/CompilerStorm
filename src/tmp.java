
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;


public class tmp {
	public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        ListIterator<Integer> iter = list.listIterator();
        while(iter.hasNext()){
            var nex = iter.next();
            System.out.println(nex);
            if(nex == 1){
                iter.add(5);
                iter.previous();
            }
        }
	}
}
