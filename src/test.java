import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * @author wuyue
 * @data 2016/5/13
 */
public class test {

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

         parseXmlFile();
    }

    private static void parseXmlFile() throws Exception {
        //通过IO获得document
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("D:\\workSpace\\dom4j\\src\\apple.xml"));
        //得到xml的根节点
        Element root = doc.getRootElement();
        //得到根节点的子节点列表
        List<Element> elementsList = root.elements();

        for (Element e: elementsList){
            System.out.println(e.getName()+"=>"+e.getStringValue());

        }

    }
}
