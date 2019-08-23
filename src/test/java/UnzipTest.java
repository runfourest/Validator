import util.Unzipper;

public class UnzipTest {
    public static void main(String[] args){
        String zipFilePath = "C:\\Users\\afournier\\IdeaProjects\\valfilechecker\\src\\main\\resources\\oracle.zip";
        String destDirectory= "C:\\Users\\afournier\\IdeaProjects\\valfilechecker\\src\\main\\resources\\destination";
        Unzipper unzipper = new Unzipper();
        try{
            unzipper.unZip(zipFilePath,destDirectory);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}


