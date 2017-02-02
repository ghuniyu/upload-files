import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by iamnubs on 29/01/2017.
 */


public class AutoCorrection {
    private static char[] keyCh;
    private static int divider;

    public static void main(String[] tot) {
        Scanner in = new Scanner(System.in);
        String key;
        System.out.print("Insert Jumlah Soal : ");
        divider = in.nextByte();
        System.out.printf("Nilai Persoal : %f\n\n", (100.0 / divider));
        System.out.print("Insert Key : ");
        if ((key = in.next().toLowerCase()).length() != divider) {
            System.out.printf("Your Key Must be exact %d", divider);
            return;
        }
        keyCh = key.toCharArray();
        System.out.printf("Key : %s [NOT CASE-SENSITIVE]\n", key);
        checker(getFiles());
    }

    private static File[] getFiles() {
        URL location = AutoCorrection.class.getProtectionDomain().getCodeSource().getLocation();

        File folder = new File(String.format("/%s",location.toString().replace("\\", "/").replace("file:/", "")));
        System.out.println(location.toString().replace("\\", "/").replace("file:/", ""));
        return folder.listFiles();
    }

    private static void checker(File[] listOfFiles) {
        try {
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile() && listOfFile.getName().contains(".txt")) {
                    try {
                        String line;
                        double score = 0;
                        BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfFile));
                        while ((line = bufferedReader.readLine()) != null) {
                            if (line.length() != 0) {
                                line = line.replace(".", "").replace(" ", "").replace("-", "");
                                try {
                                    char ans = line.toLowerCase().toCharArray()[line.length() - 1];
                                    int i = Integer.parseInt(line.substring(0, line.length() - 1));
                                    try {
                                        if (keyCh[i - 1] == ans) {
                                            score++;
                                        }
                                    } catch (ArrayIndexOutOfBoundsException a) {
                                        System.out.println("Skipped");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Skipped");
                                }
                            } else
                                System.out.println("Skipped");
                        }
                        System.out.printf("NIM : %s \nScore : %f\n\n", listOfFile.getName().replace(".txt", ""), (score/divider)*100);
                    } catch (IOException p) {
                        p.printStackTrace();
                    }
                }
            }
        } catch (NullPointerException e) {
            System.err.println("There's no File TXT in this directory");
        }
    }
}
