import models.NodeCsv;
import models.ObjectCsv;
import models.UnifiedNodeCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    //*
    private static final String nodeFilePath = "c:/Users/julrych/Downloads/HD-1230/export/csv_export/node.csv";
    private static final String objectFilePath = "c:/Users/julrych/Downloads/HD-1230/export/iedc_export/objects.csv";
    /* */
    /*
    private static final String nodeFilePath = "src/main/resources/Test/node.csv";
    private static final String objectFilePath = "src/main/resources/Test/objects.csv";
    /* */
    private static final String outputFilePath = "src/main/resources/output.csv";


    public static void main(String[] args) throws Exception {

        LOGGER.info("Starting the process");
        LOGGER.info("reading node.csv");
        LinkedHashMap<String, NodeCsv> nodes = NodeCsv.readFromFile(nodeFilePath);

        LOGGER.info("initializing collection");
        UnifiedNodeCollection unl = new UnifiedNodeCollection();

        LOGGER.info("processing node.csv");
        unl.applyNodeCsv(nodes);

        LOGGER.info("reading objects.csv");
        List<ObjectCsv> objects = ObjectCsv.readFromFile(objectFilePath);

        LOGGER.info("processing objects.csv");
        // go through Objects.csv and match all Procedures to existing Unified object to mark it as present in Objects.csv
        for (ObjectCsv object : objects) {
            unl.applyObjectCsv(object);
        }

        LOGGER.info("writing output.csv");
        unl.writeToFile(outputFilePath);

        LOGGER.info("Completed");

    }

}
