package vn.viettuts.qlsv.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import vn.viettuts.qlsv.entity.Product;

public class ProductXML {
    private static final String PRODUCT_FILE = "products";
    
    /**
     * Lưu danh sách product vào file product.xml
     * 
     * @param products
     */
    public void writeProductToXML(List<Product> products) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            
            // tạo phần tử gốc products
            Element rootElement = doc.createElement("products");
            doc.appendChild(rootElement);

            for (Product product : products) {
                Element productElement = doc.createElement("product");
                rootElement.appendChild(productElement);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(String.valueOf(product.getId())));
                productElement.appendChild(id);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(product.getName()));
                productElement.appendChild(name);

                Element price = doc.createElement("price");
                price.appendChild(doc.createTextNode(String.valueOf(product.getPrice())));
                productElement.appendChild(price);

                Element quantity = doc.createElement("quantity");
                quantity.appendChild(doc.createTextNode(product.getQuantity()));
                productElement.appendChild(quantity);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(PRODUCT_FILE));
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Đọc danh sách product từ file product.xml
     * 
     * @return list product
     */
    public List<Product> readProductFromXML() {
        List<Product> products = new ArrayList<>();
        
        try {
            File inputFile = new File(PRODUCT_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("product");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    Product product = new Product(1, "CAFE", 10000);
                    
                    product.setId(Integer.parseInt(eElement.getElementsByTagName("id")
                            .item(0).getTextContent()));
                    product.setName(eElement.getElementsByTagName("name")
                            .item(0).getTextContent());
                    product.setPrice(Float.parseFloat(eElement.getElementsByTagName("price")
                            .item(0).getTextContent()));
                    product.setQuantity(eElement.getElementsByTagName("quantity")
                            .item(0).getTextContent());
                    
                    products.add(product);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
}