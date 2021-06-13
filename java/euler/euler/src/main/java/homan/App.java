package homan;

import java.util.ArrayList;

public class App {
    public static long nextCollatz(long n) {
        return n % 2 == 0 ? (n / 2) : (3 * n + 1);
    }

    public static ArrayList<Long> collatzSequence(ArrayList<Long> seq) {
        long lastElement = seq.get(seq.size() - 1);
        if (lastElement == 1) {
            return seq;
        } else {
            seq.add(nextCollatz(lastElement));
            return collatzSequence(seq);
        }
    }

    public static ArrayList<Long> collatzSequenceNonRecursive(long n) {
        ArrayList<Long> arrayList = new ArrayList<>();
        arrayList.add(0, n);

        while(arrayList.get(arrayList.size() - 1) != 1) {
            try {
                arrayList.add(nextCollatz(arrayList.get(arrayList.size() - 1)));
            } catch(OutOfMemoryError outOfMemoryError) {
                System.out.println("Can't allocate for n = " + n);
                System.exit(-1);
            }
        }

        return arrayList;
    }

    public static long getCollatzSeqLength(long n) {
        long original = n;
        long length = 1;

        while(n != 1) {
            n = nextCollatz(n);
            length++;
            if(length % 10000 == 0) {
                System.out.println(length + " Still cracking on " + original);
            }
        }
        return length;
    }

    public static ArrayList<Long> getSeq(long n) {
        ArrayList<Long> arrayList = new ArrayList<>();
        arrayList.add(0, n);
        return collatzSequence(arrayList);
    }

    public static ArrayList<Long> longerList(ArrayList<Long> a, ArrayList<Long> b) {
        return a.size() > b.size() ? a : b;
    }

    // final static long MAX = 100;
    final static long MAX = 1_000_000;

    public static void main(String[] args) {
        //ArrayList<Long> longest = getSeq(1);
        long longestLength = 1;
        long longestValue = 1;
        for (long i = 2; i < MAX; i++) {
            if (i % 10000 == 0) {
                System.out.println(i + ": Size = " +longestLength + " > " + longestValue);
            }
            long currLength = getCollatzSeqLength(i);
            if(currLength > longestLength) {
                System.out.println("New longest! " + currLength + " > " + i);
                longestLength = currLength;
                longestValue = i;
            }
        }

        System.out.println("Longest value = " + longestValue + " (length = " + longestLength + ")");
    }
}
