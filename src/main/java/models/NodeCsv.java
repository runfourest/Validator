package models;

import com.opencsv.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * class that represents objects from node.csv
 */

public class NodeCsv {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "nodeId")
    private String nodeId;
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "parentNodeId")
    private String parentNodeId;
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "nodeName")
    private String nodeName;
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "nodeType")
    private String nodeType;

    private static final Logger LOGGER = LogManager.getLogger(NodeCsv.class);

    /**
     * Read the file into a list of objects
     * @param objectFilePath including the filename
     * @return File represented as a Map of objects with objectId as a key
     * @throws Exception
     */
    public static LinkedHashMap<String, NodeCsv> readFromFile(final String nodeFilePath) throws Exception {
        try (
                Reader reader1 = Files.newBufferedReader(Paths.get(nodeFilePath))
        ) {
            CsvToBean<NodeCsv> csvToBeanObject = new CsvToBeanBuilder<NodeCsv>(reader1)
                    .withType(NodeCsv.class)
                    .withIgnoreLeadingWhiteSpace(false)
                    .withFilter(new NodeTypeFilter(new ColumnPositionMappingStrategy<NodeCsv>()))
                    .build();

            List<NodeCsv> nodes = csvToBeanObject.parse();

            LinkedHashMap<String, NodeCsv> nodeMap = new LinkedHashMap<String, NodeCsv>();
            for (NodeCsv node : nodes) {
                nodeMap.put(node.getNodeId(), node);
            }

            return nodeMap;

        }

    }

    /**
     * Filter to exclude any Columns and ColumnFlow from the read.
     */
    static class NodeTypeFilter implements CsvToBeanFilter {

        private final MappingStrategy strategy;


        private NodeTypeFilter(MappingStrategy strategy) {
            this.strategy = strategy;
        }

        public boolean allowLine(String[] line) {
            String value = line[3];
            boolean result = !value.matches("Column|ColumnFlow");
            return result;
        }

    }


    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getParentNodeId() {
        return parentNodeId;
    }

    public void setParentNodeId(String parentNodeId) {
        this.parentNodeId = parentNodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    @Override
    public String toString() {
        return "Node{" +
                "objectId='" + nodeId + '\'' +
                ", parentId='" + parentNodeId + '\'' +
                ", objectName='" + nodeName + '\'' +
                ", objectType='" + nodeType + '\'' +
                '}';
    }
}
