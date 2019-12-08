package pl.mamicam.tile_game.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {
    public static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            String line;
            while ((line = br.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        return builder.toString();
    }

    public static int praseInt(String number) {
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException exc) {
            exc.printStackTrace();
            return 0;
        }
    }
}
