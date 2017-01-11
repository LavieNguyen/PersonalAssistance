import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);

        if (sc.nextLine().equals("fitness")) {
            Fitness f = new Fitness();
            f.log();

            LinkedList lst = f.getList();
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(((Entry) lst.get(i)).getDate());
            }
        }

        sc.close();


    }
}
