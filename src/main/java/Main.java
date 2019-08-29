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
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final String nodeFilePath = "C:\\Users\\afournier\\IdeaProjects\\valfilechecker\\src\\main\\resources\\node.csv";
    private static final String objectFilePath = "C:\\Users\\afournier\\IdeaProjects\\valfilechecker\\src\\main\\resources\\objects.csv";

    public static void main(String[] args) throws Exception {
        List<Objects> objects = readObjects(objectFilePath);
        List<Node> nodes = readNodes(nodeFilePath);






        }


public static List<Objects> readObjects (final String objectFilePath) throws Exception{
        try(
                Reader reader = Files.newBufferedReader(Paths.get(objectFilePath))
                ){
            CsvToBean<Objects> csvToBeanObject = new CsvToBeanBuilder<Objects>(reader)
                    .withType(Objects.class)
                    .withIgnoreLeadingWhiteSpace(false)
                    .build();

            List<Objects> objects = csvToBeanObject.parse();
            return objects;

        }

}

public static List<Node> readNodes (final String nodeFilePath) throws Exception{
    try(
            Reader reader1 = Files.newBufferedReader(Paths.get(nodeFilePath))
    ){
        CsvToBean<Node> csvToBeanObject = new CsvToBeanBuilder<Node>(reader1)
                .withType(Node.class)
                .withIgnoreLeadingWhiteSpace(false)
                .build();

        List<Node> nodes = csvToBeanObject.parse();


        return nodes;

    }

}

}































