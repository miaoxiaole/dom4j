import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.io.Writer;

/**
 * @author wuyue
 * @data 2016/5/16
 */
public class TestXStream {
    public static void main(String[] args){
        Person person = new Person();

        person.setName("wuyue");
        person.setInfo("student");


        //强person转化成xml
//        XStream xStream = new XStream();
        //设置别名：将class名称设置别名
        xStream.alias("person",Person.class);
        String xml=xStream.toXML(person);



        System.out.print(xml);

    }

    //xml转义
    private static XStream xStream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out){
                //对所有xml节点都进行CDATA标记
                boolean cdata = true;


                public void startNode(String name, Class clazz){
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text){
                    if (cdata){
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                    else
                        writer.write(text);
                }

            };
        }
    });
}
