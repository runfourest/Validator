package models;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.Writer;
import java.util.*;

/**
 * Orchestration class for consolidating operations on UnifiedNodes
 */
public class UnifiedNodeCollection {

    private HashMap<String, UnifiedNode> nodeCsvMap = new HashMap<>();
    private HashMap<String, UnifiedNode> objectsCsvMap = new HashMap<>();

    private static final Logger LOGGER = LogManager.getLogger(NodeCsv.class);

    /**
     * add new object to the collection
     * @param un object to be added.
     */
    public void put(UnifiedNode un) {
        nodeCsvMap.put(un.getObjectSourceId(), un);
        objectsCsvMap.put(un.getFullPath(), un);
    }

    /**
     * Write the collection contents into a CSV file
     * @param outputFilePath path to the file including the filename
     * @throws Exception
     */
    public void writeToFile(final String outputFilePath) throws Exception {
        Writer writer = new FileWriter(outputFilePath);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder<>(writer).build();

        beanToCsv.write(this.values());
        writer.close();
    }

    private UnifiedNode getByNodeId(String nodeId) {
        return nodeCsvMap.get(nodeId);
    }

    private UnifiedNode getByFullPath(String fullPath) {
        return objectsCsvMap.get(fullPath);
    }

    private ArrayList<UnifiedNode> values() {
        return new ArrayList<>(nodeCsvMap.values());
    }


    /* @TODO: handle nested procedures - additional level of hierarchy/*
    /**
     * Convert node.csv to unified objects and insert into this collection.
     * Should be run as the first step of the processing.
     * @param nodes Hashmap of the nodes with nodeId as a key.
     */
    public void applyNodeCsv(Map<String, NodeCsv> nodes) {
        for(NodeCsv node : nodes.values()) {
            switch (node.getNodeType()) {
                case "Procedure":
                    // for procedures, create a brand new unified object
                    //System.out.println("Adding new procedure object for" + node.toString());
                    NodeCsv parent = nodes.get(node.getParentNodeId());
                    String nodeSchema = "";
                    String nodePackage = "";

                    if (parent.getNodeType().equals("Schema"))
                        // parent object is directly schema
                        nodeSchema = parent.getNodeName();
                    else if (parent.getNodeType().equals("PLSQL Package")) {
                        // parent object is Package, then Packe's parent object is schema
                        nodePackage = parent.getNodeName();
                        NodeCsv schema = nodes.get(parent.getParentNodeId());
                        if (schema.getNodeType().equals("Schema"))
                            nodeSchema = schema.getNodeName();
                    }
                    // store the object into collection
                    UnifiedNode un = new UnifiedNode(
                            node.getNodeId(),
                            node.getNodeName(),
                            node.getNodeType(),
                            "node.csv",
                            nodeSchema,
                            nodePackage
                    );
                    this.put(un);
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
                    un = this.getByNodeId(myParent.getNodeId());
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
    }

    /* @TODO: handle nested procedures: procedure name= porcedure1.procedure2/*
    /**
     * Tag the object from the collection based on the object in the parameter based on the object's full path.
     * @param object object to be matched to an objects in the collection.
     */
    public void applyObjectCsv(ObjectCsv object) {
        // skip all objects that are not procedures
        if (!object.getClassId().equals("com.getmanta.edc.Procedure"))
            return;

            /* generated full path as Schema/Package/ProcedureName; expected identity structures are:
             Identity = "DWH/Packages/IMPORT_CRM/Procedures/IMPORT"
             Identity = "DWH/Procedures/IMPORT"
             */
        String identity = object.getIdentityId();
        String[] idParts = identity.split("/");
        String fullPath = null;
        String objectName = "";
        String objectSchema = "";
        String objectPackage = "";

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
        UnifiedNode un = this.getByFullPath(fullPath);

        // debugging start
        if(objectName=="ARM_DEL_FRZ_STATUS" || object.getCoreName() == "ARM_DEL_FRZ_STATUS")
            LOGGER.warn(object);
        // debugging end

        if (un != null) {
            LOGGER.debug("Unified object  found tagging fullname" + fullPath);

            un.setInObjectsCsv(true);
        } else {
            //if Unified object not found, then create it.  This should never happen as all entries from objects.csv should always be in node.csv.
            LOGGER.debug("Unified object not found for fullname" + fullPath);
            un = new UnifiedNode(
                    fullPath,
                    objectName,
                    object.getClassId(),
                    "objects.csv",
                    objectSchema,
                    objectPackage
            );
            this.put(un);
        }
    }
}
