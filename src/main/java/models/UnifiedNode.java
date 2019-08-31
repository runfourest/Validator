package models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

public class UnifiedNode {
    @CsvBindByPosition(position = 0)
    @CsvBindByName(column = "objectSourceId")
    private String objectSourceId;
    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "objectName")
    private String objectName;
    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "objectType")
    private String objectType;
    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "objectSource")
    private String objectSource;
    @CsvBindByPosition(position = 4)
    @CsvBindByName(column = "objectSchema")
    private String objectSchema;
    @CsvBindByPosition(position = 5)
    @CsvBindByName(column = "objectPackage")
    private String objectPackage;
    @CsvBindByPosition(position = 6)
    @CsvBindByName(column = "inObjectsCsv")
    private boolean inObjectsCsv;
    @CsvBindByPosition(position = 7)
    @CsvBindByName(column = "hasBody")
    private boolean hasBody;

    public UnifiedNode(String objectSourceId, String objectName, String objectType, String objectSource, String objectSchema, String objectPackage) {
        this.objectSourceId = objectSourceId;
        this.objectName = objectName;
        this.objectType = objectType;
        this.objectSource = objectSource;
        this.objectSchema = objectSchema;
        this.objectPackage = objectPackage;
    }

    /**
     * get Full path to an object in form /schema/[package]/object_name
     * @return full path to the object
     */
    public String getFullPath() {
        return getObjectSchema() + "/" + getObjectPackage() + "/" + getObjectName();
    }

    public String getObjectSourceId() {
        return objectSourceId;
    }

    public void setObjectSourceId(String objectSourceId) {
        this.objectSourceId = objectSourceId;
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

    public boolean isInObjectsCsv() {
        return inObjectsCsv;
    }

    public void setInObjectsCsv(boolean inObjectsCsv) {
        this.inObjectsCsv = inObjectsCsv;
    }

    public boolean isHasBody() {
        return hasBody;
    }

    public void setHasBody(boolean hasBody) {
        this.hasBody = hasBody;
    }

    @Override
    public String toString() {
        return "UnifiedNode{" +
                "objectSourceId='" + objectSourceId + '\'' +
                ", objectName='" + objectName + '\'' +
                ", objectType='" + objectType + '\'' +
                ", objectSource='" + objectSource + '\'' +
                ", objectSchema='" + objectSchema + '\'' +
                ", objectPackage='" + objectPackage + '\'' +
                ", InObjectsCsv=" + inObjectsCsv +
                ", hasBody=" + hasBody +
                '}';
    }
}
