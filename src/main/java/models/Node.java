package models;

public class Node {

    private String objectid;
    private String parentid;
    private String objectname;
    private String someotherobjectid;



    //crappy constructor
    public Node(String objectid, String parentid, String objectname, String someotherobjectid){

        this.objectid = objectid;
        this.parentid = parentid;
        this.objectname = objectname;
        this.someotherobjectid = someotherobjectid;

    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getObjectname() {
        return objectname;
    }

    public void setObjectname(String objectname) {
        this.objectname = objectname;
    }

    public String getSomeotherobjectid() {
        return someotherobjectid;
    }

    public void setSomeotherobjectid(String someotherobjectid) {
        this.someotherobjectid = someotherobjectid;
    }


    @Override
    public String toString() {
        return "Node{" +
                "objectid='" + objectid + '\'' +
                ", parentid='" + parentid + '\'' +
                ", objectname='" + objectname + '\'' +
                ", someotherobjectid='" + someotherobjectid + '\'' +
                '}';
    }





//getters and settersS
}
