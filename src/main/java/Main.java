import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

    }

        int number1 = 5;
        int number2 = 6;

        public static int total (int num1, int num2){
            int num3;
            num3 = num1 * num2;
            return num3;




    }


    /*Create Node.CSV File*/
        public static Map<String,String> getMapFromNodeCSV(final String filepath)throws IOException {

            Stream<String> lines = Files.lines(Paths.get(filepath));
            Map<String,String> resultMap = lines.map(line -> line.split(","))
                    .collect(Collectors.toMap(line -> line[0], line -> line[1]));
            lines.close();
            return resultMap;
        }


}

/*Create a method to read the objects csv ... WAY more complicated logic -.-*/



