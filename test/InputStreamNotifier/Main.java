
import java.io.IOException;
import java.io.InputStream;


public class Main {

	public static void main(String[] args) throws IOException {
            System.in.read();
            p(System.in.available());
            System.in.mark(5);
            
            
            
	}
        
        public static void p(String str){
            System.out.println(str);
        }
        public static void p(int str){
            System.out.println(str+"");
        }

}

abstract class InformativeInputStream extends InputStream{
    public InformativeInputStream(){
        super();
    }
    
    
    @Override
    public void reset(){
        System.out.println("Thing added");
    }
}
