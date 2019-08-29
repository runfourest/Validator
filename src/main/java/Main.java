import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import models.Node;
import models.Objects;
import models.UnifiedNode;

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
        Map<String, UnifiedNode> unl = new HashMap<String, UnifiedNode>();
        Map<String, Node> nodes = readNodes(nodeFilePath);

        for(Node node: nodes.values())   {
            switch (node.getObjectType()) {
                case "Procedure":
                    //System.out.println("Adding new proceure object for" + node.toString());
                    UnifiedNode un = new UnifiedNode();
                    un.setObjectSourceId(node.getObjectId());
                    un.setObjectName(node.getObjectName());
                    Node parent = nodes.get(node.getParentId());
                    if (parent.getObjectType().equals("Schema"))
                        un.setObjectSchema(parent.getObjectName());
                    else if (parent.getObjectType().equals("PLSQL Package")) {
                        un.setObjectPackage(parent.getObjectName());
                        Node schema = nodes.get(parent.getParentId());
                        if (schema.getObjectType().equals("Schema"))
                            un.setObjectSchema(schema.getObjectName());
                    }
                    un.setObjectSource("node.csv");
                    unl.put(un.getObjectSourceId(), un);
                    System.out.println(node.toString());
                    System.out.println("New procedure object added:" + un.toString());
                    break;
                case "PLSQL Block":
                   // System.out.println("Marking PLSQL Block " + node.toString() + " with HASBODY");
                    Node myParent = nodes.get(node.getParentId());
                    if (myParent == null)
                        continue;
                    // tag unified object representing this one as having a body
                    un = unl.get(myParent.getObjectId());
                    if(un != null) {
                        un.setHasBody(true);
                        System.out.println("Procedure Object tagged:" + un.toString());
                    }
                    break;
                default:
                    continue;
            }
        }


         //objects.forEach(System.out::println);









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

public static Map<String, Node> readNodes (final String nodeFilePath) throws Exception{
    try(
            Reader reader1 = Files.newBufferedReader(Paths.get(nodeFilePath))
    ){
        CsvToBean<Node> csvToBeanObject = new CsvToBeanBuilder<Node>(reader1)
                .withType(Node.class)
                .withIgnoreLeadingWhiteSpace(false)
                .build();

        List<Node> nodes = csvToBeanObject.parse();

        HashMap<String, Node> nodeMap = new HashMap<String, Node>();
        for (Node node: nodes) {
            nodeMap.put(node.getObjectId(), node);
        }

        return nodeMap;

    }

}

}































