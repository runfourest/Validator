package models;

import com.opencsv.bean.CsvBindByPosition;

public class Node {
    @CsvBindByPosition(position = 0)
    private String objectId;
    @CsvBindByPosition(position = 1)
    private String parentId;
    @CsvBindByPosition(position = 2)
    private String objectName;
    @CsvBindByPosition(position = 3)
    private String  someOtherObjectId;




    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getSomeOtherObjectId() {
        return someOtherObjectId;
    }

    public void setSomeOtherObjectId(String someOtherObjectId) {
        this.someOtherObjectId = someOtherObjectId;
    }

    @Override
    public String toString() {
        return "Node{" +
                "objectId='" + objectId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", objectName='" + objectName + '\'' +
                ", someOtherObjectId='" + someOtherObjectId + '\'' +
                '}';
    }
}
