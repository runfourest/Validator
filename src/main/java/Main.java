import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import models.Node;
import models.Objects;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String nodeFilePath = "C:\\Users\\afournier\\IdeaProjects\\valfilechecker\\src\\main\\resources\\node.csv";
    private static final String objectFilePath = "C:\\Users\\afournier\\IdeaProjects\\valfilechecker\\src\\main\\resources\\objects.csv";

    public static void main(String[] args) throws IOException {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(nodeFilePath));
        ) {
            CsvToBean<Node> csvToBeanNode = new CsvToBeanBuilder<Node>(reader)
                    .withType(Node.class)
                    .withIgnoreLeadingWhiteSpace(false)
                    .build();


            //Iterator is not necessary to create.
            Iterator<Node> nodeIterator = csvToBeanNode.iterator();

            List<Node> nodes = csvToBeanNode.parse();
            nodes.forEach(System.out::println);
        }
         try(
                 Reader reader1 =  Files.newBufferedReader(Paths.get(objectFilePath));
         )
         {
             CsvToBean<Objects> csvToBeanObject = new CsvToBeanBuilder<Objects>(reader1)
                 .withType(Objects.class)
                 .withIgnoreLeadingWhiteSpace(false)
                 .build();

             List<Objects> objects = csvToBeanObject.parse();
             objects.forEach(System.out::println);








            }





        }



    }



























            /***
             * This is for the static method below
             */

/*

        Map<String, String> mapper = getMapFromNodeCSV(filepath);

        mapper.values().stream()
                .forEach(System.out::println);

    }

*/







    /*
    This is a static Java Method to read a csv and map it to a Map - I don't think this will work anymore
    because it's not tabular. bummer.


     */
    /*

        public static Map<String, String> getMapFromNodeCSV ( final String filepath) throws IOException {
            Stream<String> lines = Files.lines(Paths.get(filepath));
            Map<String, String> resultMap = lines.map(line -> line.split(","))
                    .collect(Collectors.toMap(line -> line[0], line -> line[2]));
            lines.close();
            return resultMap;
        }


        public static List<String[]> readCSVTESTER (Reader reader) throws Exception {
            CSVReader csvreader = new CSVReader(reader);
            List<String[]> list = new ArrayList<>();
            reader.close();
            csvreader.close();
            return list;
        }
*/






