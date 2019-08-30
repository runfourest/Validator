package models;

import com.opencsv.bean.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

public class NodeCsv {
    @CsvBindByPosition(position = 0)
    private String nodeId;
    @CsvBindByPosition(position = 1)
    private String parentNodeId;
    @CsvBindByPosition(position = 2)
    private String nodeName;
    @CsvBindByPosition(position = 3)
    private String nodeType;

    private static final Logger LOGGER = LogManager.getLogger(NodeCsv.class);

    // Read Csv to Java Pojo
    public static LinkedHashMap<String, NodeCsv> readFromFile(final String nodeFilePath) throws Exception {
        try (
                Reader reader1 = Files.newBufferedReader(Paths.get(nodeFilePath))
        ) {
            CsvToBean<NodeCsv> csvToBeanObject = new CsvToBeanBuilder<NodeCsv>(reader1)
                    .withType(NodeCsv.class)
                    .withIgnoreLeadingWhiteSpace(false)
                    .build();

            List<NodeCsv> nodes = csvToBeanObject.parse();

            LinkedHashMap<String, NodeCsv> nodeMap = new LinkedHashMap<String, NodeCsv>();
            for (NodeCsv node : nodes) {
                nodeMap.put(node.getNodeId(), node);
            }

            return nodeMap;

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
