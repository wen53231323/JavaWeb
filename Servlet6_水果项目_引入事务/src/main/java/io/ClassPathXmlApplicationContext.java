package io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ClassPathXmlApplicationContext implements BeanFactory {

    private Map<String, Object> beanMap = new HashMap<>();

    // -----------------------------------步骤二：解析xml配置文件，并放在map集合中-------------------------------------
    public ClassPathXmlApplicationContext() {
        try {

            // 读取配置文件
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
            // 创建DocumentBuilderFactory对象
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            // 创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            // 创建Document对象
            Document document = documentBuilder.parse(inputStream);
            // 根据Document对象，获取xml文档中所有的bean节点列表
            NodeList beanNodeList = document.getElementsByTagName("bean");

            // --------------------------------遍历获取的xml文档中所有的bean节点并放在Map集合---------------------------
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                // 取到其中一个
                Node beanNode = beanNodeList.item(i);
                // 如果是元素节点则执行
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    // 将元素节点强转为Element
                    Element beanElement = (Element) beanNode;
                    // 获取xml配置文件中的id
                    String beanId = beanElement.getAttribute("id");
                    // 获取xml配置文件中的class（类的全类名）
                    String className = beanElement.getAttribute("class");
                    // 根据 类的全类名，获取 类的实例
                    Class beanClass = Class.forName(className);
                    // 加载的类的实例对象
                    Object beanObj = beanClass.newInstance();
                    // 将 id 和 对应类的实例对象 保存到map集合
                    beanMap.put(beanId, beanObj);
                }
            }

            // --------------------------组装bean之间的依赖关系-----------------------
            // 遍历获取的xml文档中所有的bean节点列表
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                // 如果是元素节点则执行
                if (beanNode.getNodeType() == Node.ELEMENT_NODE) {
                    // 将元素节点强转为Element
                    Element beanElement = (Element) beanNode;
                    // 获取xml配置文件中的id
                    String beanId = beanElement.getAttribute("id");
                    // 获取节点中的子节点
                    NodeList beanChildNodeList = beanElement.getChildNodes();

                    // 遍历
                    for (int j = 0; j < beanChildNodeList.getLength(); j++) {
                        // 取到其中一个节点
                        Node beanChildNode = beanChildNodeList.item(j);
                        // 如果是元素节点，且名字是property，则执行
                        if (beanChildNode.getNodeType() == Node.ELEMENT_NODE && "property".equals(beanChildNode.getNodeName())) {
                            // 将元素节点强转为Element
                            Element propertyElement = (Element) beanChildNode;
                            // 获取子节点property内属性为name的值
                            String propertyName = propertyElement.getAttribute("name");
                            // 获取子节点property内属性为ref的值
                            String propertyRef = propertyElement.getAttribute("ref");
                            // 从map集合中找到propertyRef对应的实例
                            Object refObj = beanMap.get(propertyRef);
                            // 从map集合中找到propertyRef对应的实例
                            Object beanObj = beanMap.get(beanId);
                            // 将refObj设置到当前bean对应的实例的property属性上去
                            Class beanClazz = beanObj.getClass();
                            // getDeclaredField(String name)：根据属性名name获取指定的属性
                            Field propertyField = beanClazz.getDeclaredField(propertyName);
                            propertyField.setAccessible(true);
                            propertyField.set(beanObj, refObj);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Object getBean(String id) {
        return beanMap.get(id);
    }
}
