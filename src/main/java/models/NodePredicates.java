package models;

import java.util.Map;
import java.util.function.Predicate;

public class NodePredicates {

    public static Predicate<Node> isOracleObject(){
        return p -> p.getObjectType().equals("foo");
    }

    //public static Map<String,Node> filterNode (Map<String,Node> nodes, Predicate<Node>)

}


}