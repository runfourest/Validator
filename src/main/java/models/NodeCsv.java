package models;

import com.opencsv.bean.CsvBindByPosition;

public class NodeCsv {
    @CsvBindByPosition(position = 0)
    private String nodeId;
    @CsvBindByPosition(position = 1)
    private String parentNodeId;
    @CsvBindByPosition(position = 2)
    private String nodeName;
    @CsvBindByPosition(position = 3)
    private String nodeType;




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
