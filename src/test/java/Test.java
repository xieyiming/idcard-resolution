import com.github.xieyiming.idcard.IdcardInfoExtractor;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Test {


    @org.junit.Test
    public void testIdcardBy18bit(){
        String idcard = "341125199001011995";
        IdcardInfoExtractor extractor = new IdcardInfoExtractor(idcard);
        System.out.println(extractor.toString());
    }
}
