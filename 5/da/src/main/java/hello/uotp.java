package hello;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Random;

@Entity // This tells Hibernate to make a table out of this class
public class uotp {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    public String phone;
    public String otp;
    public String otpgene(){
        String numbers = "0123456789";
        Random rndm_method = new Random();
        String x = "";
        for (int i = 0; i < 4; i++) {
            x=x.concat(numbers.charAt(rndm_method.nextInt(numbers.length()))+"") ;
        }
        return x;
    }
}
