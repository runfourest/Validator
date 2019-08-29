package models;

import com.opencsv.bean.CsvBindByPosition;

public class UnifiedNode {
    @CsvBindByPosition(position = 0)
    private String objectSourceId;
    @CsvBindByPosition(position = 1)
    private String parentId;
    @CsvBindByPosition(position = 2)
    private String objectName;
    @CsvBindByPosition(position = 3)
    private String  objectType;
    @CsvBindByPosition(position = 4)
    private String  objectSource;
    @CsvBindByPosition(position = 5)
    private String  objectSchema;
    @CsvBindByPosition(position = 5)
    private String  objectPackage;

    public boolean isHasBody() {
        return hasBody;
    }

    public void setHasBody(boolean hasBody) {
        this.hasBody = hasBody;
    }

    private boolean hasBody;


    public String getObjectSourceId() {
        return objectSourceId;
    }

    public void setObjectSourceId(String objectSourceId) {
        this.objectSourceId = objectSourceId;
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

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(String objectSource) {
        this.objectSource = objectSource;
    }

    public String getObjectSchema() {
        return objectSchema;
    }

    public void setObjectSchema(String objectSchema) {
        this.objectSchema = objectSchema;
    }

    public String getObjectPackage() {
        return objectPackage;
    }

    public void setObjectPackage(String objectPackage) {
        this.objectPackage = objectPackage;
    }




    @Override
    public String toString() {
        return "UnifiedNode{" +
                "objectId='" + objectSourceId + '\'' +
                ", parentId='" + parentId + '\'' +
                ", objectName='" + objectName + '\'' +
                ", objectType='" + objectType + '\'' +
                ", objectSource='" + objectSource + '\'' +
                ", objectSchema='" + objectSchema + '\'' +
                ", objectPackage='" + objectPackage + '\'' +
                ", hasBody='" + hasBody + '\'' +
                '}';
    }


}
