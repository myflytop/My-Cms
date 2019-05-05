
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.cms.model.po.CmsTheme;

public class Mytest {

	public static void main(String[] args) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper(); 
 /*     JsonNode node=  mapper.readTree(new File("C:\\Users\\Administrator\\Desktop\\json.json"));
        System.out.println(node.toString());*/
        CmsTheme theme=mapper.readValue(new File("C:\\Users\\Administrator\\Desktop\\json.json"), CmsTheme.class);
	    System.out.println(theme.getCreateBy());
	}


}
