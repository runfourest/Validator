package models;

import java.util.Collection;
import java.util.HashMap;

public class UnifiedNodeCollection {

    private HashMap<String, UnifiedNode> nodeCsvMap;
    private HashMap<String, UnifiedNode> objectsCsvMap;

    public UnifiedNodeCollection() {
        nodeCsvMap = new HashMap<String, UnifiedNode>();
        objectsCsvMap = new HashMap<String, UnifiedNode>();
    }

    public void put(UnifiedNode un) {
        nodeCsvMap.put(un.getObjectSourceId(), un);
        objectsCsvMap.put(un.getFullPath(), un);
    }

    public UnifiedNode getByNodeId(String nodeId) {
        return nodeCsvMap.get(nodeId);
    }

    public UnifiedNode getByFullPath(String fullPath) {
        return objectsCsvMap.get(fullPath);
    }

    public Collection<UnifiedNode> values() {
        return nodeCsvMap.values();
    }



}
