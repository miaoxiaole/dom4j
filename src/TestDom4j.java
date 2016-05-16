import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * @author wuyue
 * @data 2016/5/13
 */
public class TestDom4j {

    /**
     * 主方法
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

         parseXmlFile();
    }

    private static void parseXmlFile() throws Exception {
        HashMap<String,String>map = new HashMap<String, String>();
        //通过IO获得document
        SAXReader reader = new SAXReader();
        Document doc = reader.read(new File("D:\\workSpace\\dom4j\\src\\apple.xml"));
        //得到xml的根节点
        Element root = doc.getRootElement();

        recursiveParseXML(root,map);

        System.out.print(map);
       /* //得到根节点的子节点列表
        List<Element> elementsList = root.elements();

        for (Element e: elementsList){
            System.out.println(e.getName()+"=>"+e.getStringValue());

        }*/

    }

    private static void recursiveParseXML(Element root,HashMap<String,String> map){
        //得到根节点的子节点列表
        List<Element> elementsList = root.elements();

        //判断有没有子元素列表
        if (elementsList.size()==0){
            System.out.print(root.getName()+"=>"+root.getStringValue());
            map.put(root.getName(),root.getTextTrim());
        }
        else{
            //遍历
            for (Element e: elementsList){
                recursiveParseXML(e,map);
            }
        }
    }
}
