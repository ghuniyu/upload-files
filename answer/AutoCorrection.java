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

    public static void main(String[] tot) {
        Scanner in = new Scanner(System.in);
        String key;
        System.out.print("Insert Key : ");
        if ((key = in.next().toLowerCase()).length() != 10) {
            System.out.println("Your Key Must be 10");
            return;
        }
        keyCh = key.toCharArray();
        System.out.printf("Key : %s [NOT CASE-SENSITIVE]\n\n", key);
        checker(getFiles());
    }

    private static File[] getFiles() {
        URL location = AutoCorrection.class.getProtectionDomain().getCodeSource().getLocation();

        File folder = new File(location.toString().replace("\\", "/").replace("file:/", ""));
        return folder.listFiles();
    }

    private static void checker(File[] listOfFiles) {
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile() && listOfFile.getName().contains(".txt")) {
                try {
                    String line;
                    int score = 0;
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(listOfFile));
                    while ((line = bufferedReader.readLine()) != null) {
                        line = line.replace(".", "").replace(" ", "");
                        char ans = line.toLowerCase().toCharArray()[line.length() - 1];
                        int i = Integer.parseInt(line.substring(0, line.length() - 1));
                        if (keyCh[i - 1] == ans)
                            score += 10;
                    }
                    System.out.printf("NIM : %s \nScore : %d\n\n", listOfFile.getName().replace(".txt", ""), score);
                } catch (IOException p) {
                    p.printStackTrace();
                }
            }
        }
    }
}
