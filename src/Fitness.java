import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by Lavie Nguyen on 1/10/2017.
 */

public class Fitness {
    private LinkedList<Entry> list = new LinkedList<>();

    public Fitness() {}

    public void log() {         // exercisename rep-weight rep-weight...
        Scanner sc = new Scanner(System.in);
        Entry entry = new Entry();
        String input = sc.nextLine();

        while (!input.equals("exit")) {
            String[] data = input.split(" ");
            int[][] set = new int[data.length - 2][2];

            for (int i = 1; i < data.length; i++) {
                String[] tmp = data[i].split("-");
                set[i - 1][0] = Integer.parseInt(tmp[0]);
                set[i - 1][1] = Integer.parseInt(tmp[1]);
            }

            Exercise ex = new Exercise();
            ex.setName(data[0]);
            ex.setSet(set);

            entry.add(ex);
        }

        sc.close();
        list.add(entry);
    }

    public void save() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data.txt"));

            for (Entry e : list) {
                bw.write(".");
                bw.write(e.toString());
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            String str = "";
            Entry e;
            Exercise ex;

            while ((str = br.readLine()) != null) {
                if (str.equals(".")) e = new Entry(br.readLine());
                else {
                    ex = new Exercise();
                    ex.parse(str);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LinkedList<Entry> getList() {
        return list;
    }
}

class Entry {
    private LinkedList<Exercise> exercises = new LinkedList<>();
    private Date date;

    public Entry() {
        date = new Date();
    }

    public Entry(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void add(Exercise e) {
        exercises.add(e);
    }

    public LinkedList<Exercise> getExercises() {
        return exercises;
    }

    public Date getDate() {
        return date;
    }

    public String toString() {
        String result = "";

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        result += sdf.format(date) + "\n";

        for (Exercise e : exercises) {
            result += e.getName() + " ";

            int[][] tmp = e.getSet();
            for (int i = 0; i < tmp.length; i++) {
                result += tmp[i][0] + "-" + tmp[i][1];
            }

            result += "\n";
        }

        return result;
    }
}


class Exercise {
    private String name;
    private String[] muscles;
    private int[][] set;                // [rep][weight]

    public Exercise() {}

    public Exercise(String name) {
        this.name = name;
    }

    public Exercise(String name, String[] muscles) {
        this(name);
        this.muscles = muscles;
    }

    public Exercise(String name, String[] muscles, int[][] set) {
        this(name, muscles);
        this.set = set;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getMuscles() {
        return muscles;
    }

    public void setMuscles(String[] muscles) {
        this.muscles = muscles;
    }

    public int[][] getSet() {
        return set;
    }

    public void setSet(int[][] set) {
        this.set = set;
    }

    public void parse(String str) {
        String[] data = str.split(" ");
        name = data[0];
        set = new int[data.length - 1][2];

        for (int i = 1; i < data.length; i++) {
            String[]tmp = data[i].split("-");
            set[i - 1][0] = Integer.parseInt(tmp[0]);
            set[i - 1][1] = Integer.parseInt(tmp[1]);
        }
    }
}
