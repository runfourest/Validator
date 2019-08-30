package models;

import java.util.function.Predicate;

public class NodePredicates {

    public static Predicate<NodeCsv> isOracleObject(){
        return p -> p.getNodeType().equals("foo");
    }

    //public static Map<String,Node> filterNode (Map<String,Node> nodes, Predicate<Node>)

}


