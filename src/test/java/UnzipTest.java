import util.Unzipper;

public class UnzipTest {
    public static void main(String[] args){
        String zipFilePath = "";
        String destDirectory= "";
        Unzipper unzipper = new Unzipper();
        try{
            unzipper.unZip(zipFilePath,destDirectory);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
