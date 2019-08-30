package models;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ObjectCsv {
    @CsvBindByPosition(position = 0)
    private String classId;

    @CsvBindByPosition(position = 1)
    private String identityId;

    @CsvBindByPosition(position = 2)
    private String coreName;

    @CsvBindByPosition(position = 3)
    private String coreDescription;

    @CsvBindByPosition(position = 4)
    private String getMantaExpression;


    //Read Csv to Java Pojo
    public static List<ObjectCsv> readFromFile(final String objectFilePath) throws Exception {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(objectFilePath))
        ) {
            CsvToBean<ObjectCsv> csvToBeanObject = new CsvToBeanBuilder<ObjectCsv>(reader)
                    .withType(ObjectCsv.class)
                    .withIgnoreLeadingWhiteSpace(false)
                    .build();

            List<ObjectCsv> objects = csvToBeanObject.parse();

            return objects;

        }

    }



    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public String getCoreName() {
        return coreName;
    }

    public void setCoreName(String coreName) {
        this.coreName = coreName;
    }

    public String getCoreDescription() {
        return coreDescription;
    }

    public void setCoreDescription(String coreDescription) {
        this.coreDescription = coreDescription;
    }

    public String getGetMantaExpression() {
        return getMantaExpression;
    }

    public void setGetMantaExpression(String getMantaExpression) {
        this.getMantaExpression = getMantaExpression;
    }


    @Override
    public String toString() {
        return "Objects{" +
                "classId='" + classId + '\'' +
                ", identityId='" + identityId + '\'' +
                ", coreName='" + coreName + '\'' +
                ", coreDescription='" + coreDescription + '\'' +
                ", getMantaExpression='" + getMantaExpression + '\'' +
                '}';
    }


}
