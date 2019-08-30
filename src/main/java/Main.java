import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import models.NodeCsv;
import models.Objects;
import models.UnifiedNode;
import models.UnifiedNodeCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    /*
    private static final String nodeFilePath = "src/main/resources/Fidelity/node.csv";
    private static final String objectFilePath = "src/main/resources/Fidelity/objects.csv";
    /* */

    private static final String nodeFilePath = "src/main/resources/Test/node.csv";
    private static final String objectFilePath = "src/main/resources/Test/objects.csv";
    /* */
    private static final String outputFilePath = "src/main/resources/output.csv";
    private static final Logger LOGGER = LogManager.getLogger(Main.class);


    public static void main(String[] args) throws Exception {


        LOGGER.error("Starting the process");
        LOGGER.error("reading node.csv");
        Map<String, NodeCsv> nodes = readNodes(nodeFilePath);

        LOGGER.error("processing node.csv");
        UnifiedNodeCollection unl = new UnifiedNodeCollection();


        // Step #1
        // convert node.csv to unified objects
        for (NodeCsv node : nodes.values()) {
            switch (node.getNodeType()) {
                case "Procedure":
                    // for procedures, create a brand new unified object
                    //System.out.println("Adding new proceure object for" + node.toString());
                    UnifiedNode un = new UnifiedNode();
                    un.setObjectSourceId(node.getNodeId());
                    un.setObjectName(node.getNodeName());
                    un.setObjectType(node.getNodeType());
                    un.setObjectSource("node.csv");
                    NodeCsv parent = nodes.get(node.getParentNodeId());
                    if (parent.getNodeType().equals("Schema"))
                        // parent object is directly schema
                        un.setObjectSchema(parent.getNodeName());
                    else if (parent.getNodeType().equals("PLSQL Package")) {
                        // parent object is Package, then Packe's parent object is schema
                        un.setObjectPackage(parent.getNodeName());
                        NodeCsv schema = nodes.get(parent.getParentNodeId());
                        if (schema.getNodeType().equals("Schema"))
                            un.setObjectSchema(schema.getNodeName());
                    }
                    // store the object into collection
                    unl.put(un);
                    LOGGER.debug(node.toString());
                    LOGGER.debug("New procedure object added:" + un.toString());
                    break;
                case "PLSQL Block":
                    // for PL/SQL block, i.e. procedure body, mark it as having body, i.e. exportable into EDC
                    // LOGGER.debug("Marking PLSQL Block " + node.toString() + " with HASBODY");
                    // find parent object (procedure) by node.csv Id
                    LOGGER.debug("Found PLSQL Block trying to tag parent" + node.toString());
                    NodeCsv myParent = nodes.get(node.getParentNodeId());
                    if (myParent == null) {
                        LOGGER.debug("No parent found" + node.toString());
                        continue;
                    }

                    // tag unified object representing this one as having a body
                    un = unl.getByNodeId(myParent.getNodeId());
                    if (un != null) {
                        un.setHasBody(true);
                        LOGGER.debug("Procedure Object tagged:" + un.toString());
                    } else
                        LOGGER.debug("Unified object not found for " + myParent.getNodeId());

                    break;
                default:
                    // ignore other object types
                    continue;
            }
        }


        // step #2
        LOGGER.error("reading objects.csv");
        List<Objects> objects = readObjects(objectFilePath);

        LOGGER.error("processing objects.csv");
        // go through Objects.csv and match all Procedures to existing Unified object to mark it as present in Objects.csv
        for (Objects object : objects) {
            // skip all objects that are not procedures
            if (!object.getClassId().equals("com.getmanta.edc.Procedure"))
                continue;

            /* generated full path as Schema/Package/ProcedureName; expected identity structures are:
             Identity = "DWH/Packages/IMPORT_CRM/Procedures/IMPORT"
             Identity = "DWH/Procedures/IMPORT"
             */
            String identity = object.getIdentityId();
            String[] idParts = identity.split("/");
            String fullPath = null;
            String objectName = null;
            String objectSchema = null;
            String objectPackage = null;

            if (idParts.length == 5) {
                objectName = idParts[4];
                objectPackage = idParts[2];
                objectSchema = idParts[0];
                fullPath = objectSchema + "/" + objectPackage + "/" + objectName;
            } else if (idParts.length == 3) {
                objectName = idParts[2];
                objectSchema = idParts[0];
                fullPath = objectSchema + "/" + objectName;
            } else
                fullPath = "unexpected structure " + identity;

            // mark Unified object as resent in Objects.csv
            UnifiedNode un = unl.getByFullPath(fullPath);
            if (un != null) {
                LOGGER.debug("Unified object  found tagging fullname" + fullPath);
                un.setInObjectsCsv(true);
            } else {
                //if Unified object not found, then create it.  This should never happen as all entries from objects.csv should always be in node.csv.
                LOGGER.debug("Unified object not found for fullname" + fullPath);
                un = new UnifiedNode();
                un.setObjectSourceId(fullPath);
                un.setObjectName(objectName);
                un.setObjectPackage(objectPackage);
                un.setObjectSchema(objectSchema);
                un.setObjectType(object.getClassId());
                un.setObjectSource("objects.csv");
                unl.put(un);
            }

        }

        LOGGER.error("writing output.csv");
        csvWriterAll(unl,outputFilePath);

        LOGGER.error("Completed");

        /**/

    }

    //Read Csv to Java Pojo
    public static List<Objects> readObjects(final String objectFilePath) throws Exception {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(objectFilePath))
        ) {
            CsvToBean<Objects> csvToBeanObject = new CsvToBeanBuilder<Objects>(reader)
                    .withType(Objects.class)
                    .withIgnoreLeadingWhiteSpace(false)
                    .build();

            List<Objects> objects = csvToBeanObject.parse();

            return objects;

        }

    }
 // Read Csv to Java Pojo
    public static Map<String, NodeCsv> readNodes(final String nodeFilePath) throws Exception {
        try (
                Reader reader1 = Files.newBufferedReader(Paths.get(nodeFilePath))
        ) {
            CsvToBean<NodeCsv> csvToBeanObject = new CsvToBeanBuilder<NodeCsv>(reader1)
                    .withType(NodeCsv.class)
                    .withIgnoreLeadingWhiteSpace(false)
                    .build();

            List<NodeCsv> nodes = csvToBeanObject.parse();

            HashMap<String, NodeCsv> nodeMap = new LinkedHashMap<String, NodeCsv>();
            for (NodeCsv node : nodes) {
                nodeMap.put(node.getNodeId(), node);
            }

            return nodeMap;

        }

    }


    public static void csvWriterAll(UnifiedNodeCollection unl, final String outputFilePath) throws Exception{
        Writer writer = new FileWriter(outputFilePath);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder<>(writer).build();

        beanToCsv.write(unl.values());
        writer.close();
        }


    }
