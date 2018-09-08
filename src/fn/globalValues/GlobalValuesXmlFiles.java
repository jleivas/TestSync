/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fn.globalValues;

import fn.GV;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 *
 * @author jlleivas
 */
public class GlobalValuesXmlFiles {
    public static void crearRegistroLocal(){
         
         Document document = null;
         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
         try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            document = implementation.createDocument(null, "local", null);
            /**************************USER******************************/
            //Creación de elementos
            //creamos el elemento principal usr
            Element user = document.createElement("usr"); 
            //creamos un nuevo elemento. usr contiene name
            Element name= document.createElement("name");
            Text vName = document.createTextNode(GV.username()); 
            /**************************LICENCE******************************/
            Element lic = document.createElement("lic"); 
            //creamos un nuevo elemento. lic contiene st y date
            Element st= document.createElement("st");
            Element code= document.createElement("code");
            Element date= document.createElement("date");
            //Ingresamos la info. 
            Text vSt = document.createTextNode("0");
            if(GV.diasRestatntes(GV.expDate())<0){
                GV.setLicence(false);
            }
            if(GV.licence()){
                vSt = document.createTextNode("1");
            }
            Text vCode = document.createTextNode(GV.licenceCode());
            Text vDate = document.createTextNode(GV.expDate()); 
            /**************************NETWORK******************************/
            Element network = document.createElement("network"); 
            //creamos un nuevo elemento. lic contiene st y date
            Element equipo= document.createElement("equipo");
            Element uri= document.createElement("uri");
            Element port= document.createElement("port");
            //Ingresamos la info. 
            Text vEquipo = document.createTextNode(GV.equipo());
            Text vUri = document.createTextNode(GV.uri());
            Text vPort = document.createTextNode(GV.port());
            
             /**************************REGISTRY******************************/
            Element registry = document.createElement("registry"); 
            //creamos un nuevo elemento. lic contiene st y date
            Element office= document.createElement("office");
            Element company= document.createElement("company");
            Element inventary= document.createElement("inventary");
            Element lastUpdate = document.createElement("last_update_bd");
            Element companyDesc = document.createElement("company_description");
            Element companyRut = document.createElement("company_rut");
            Element companyGiro = document.createElement("company_giro");
            Element msgFile = document.createElement("message_file");
            //Ingresamos la info. 
            Text vOffice = document.createTextNode(GV.getNombreOficina());
            Text vCompany = document.createTextNode(GV.companyName());
            Text vInventary = document.createTextNode(GV.inventarioName());
            Text vLastUpdate = document.createTextNode(GV.dateToString(GV.getLastUpdate(),"dd-mm-yyyy"));
            /**************USER*******************************************/
            //Asignamos la versión de nuestro XML
            document.setXmlVersion("1.0"); 
            //Añadimos al usr al documento
            document.getDocumentElement().appendChild(user); 
            user.appendChild(name); 
            //Añadimos valor
            name.appendChild(vName); 
            /**************LICENCE*******************************************/
            document.getDocumentElement().appendChild(lic);
            //Añadimos el elemento hijo a la raíz
            lic.appendChild(st);
            st.appendChild(vSt);
            lic.appendChild(code);
            code.appendChild(vCode);
            lic.appendChild(date); 
            
            date.appendChild(vDate);
            /**************NETWORK*******************************************/
            document.getDocumentElement().appendChild(network);
            //Añadimos el elemento hijo a la raíz
            network.appendChild(equipo);
            equipo.appendChild(vEquipo);
            //Añadimos valor
            network.appendChild(uri); 
            uri.appendChild(vUri);
            
            network.appendChild(port); 
            port.appendChild(vPort);
            
            /**************REGISTRY*******************************************/
            document.getDocumentElement().appendChild(registry);
            //Añadimos el elemento hijo a la raíz
            registry.appendChild(office);
            office.appendChild(vOffice);
            //Añadimos valor
            registry.appendChild(company); 
            company.appendChild(vCompany);
            
            registry.appendChild(inventary); 
            inventary.appendChild(vInventary);
            
            registry.appendChild(lastUpdate); 
            lastUpdate.appendChild(vLastUpdate);
            
            guardaConFormato(document,GV.directoryFilesPath()+"local.xml");
            
         }catch(Exception e){
             System.err.println("Class RegistroGlobal: Error");
         }
    }
    
    private static void guardaConFormato(Document document, String URI){
    try {
        TransformerFactory transFact = TransformerFactory.newInstance();

        //Formateamos el fichero. Añadimos sangrado y la cabecera de XML
        transFact.setAttribute("indent-number", new Integer(3));
        Transformer trans = transFact.newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT, "yes");
        trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

        //Hacemos la transformación
        StringWriter sw = new StringWriter();
        StreamResult sr = new StreamResult(sw);
        DOMSource domSource = new DOMSource(document);
        trans.transform(domSource, sr);

        //Mostrar información a guardar por consola (opcional)
        //Result console= new StreamResult(System.out);
        //trans.transform(domSource, console);
        try {
            //Creamos fichero para escribir en modo texto
            PrintWriter writer = new PrintWriter(new FileWriter(URI));

            //Escribimos todo el árbol en el fichero
            writer.println(sw.toString());

            //Cerramos el fichero
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } catch(Exception ex) {
        ex.printStackTrace();
    }
    }
    
    public static void cargarRegistroLocal(){
        try{
            File archivo = new File(GV.directoryFilesPath()+"local.xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document documento = db.parse(archivo);
            
            /***********USER************************************************/
            documento.getDocumentElement().normalize();
            NodeList filas = documento.getElementsByTagName("usr");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    GV.username(element.getElementsByTagName("name").item(0).getTextContent());
                }
            }
            
            /***********LICENCE************************************************/
            boolean st = false;
            documento.getDocumentElement().normalize();
            filas = documento.getElementsByTagName("lic");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    st = (Integer.parseInt(element.getElementsByTagName("st").item(0).getTextContent()) == 1) ? true:false;
                    GV.licence(st);
                    GV.setLicenceCode(element.getElementsByTagName("code").item(0).getTextContent());
                    GV.expDate(element.getElementsByTagName("date").item(0).getTextContent());
                }
            }
            
            /***********NETWORK************************************************/
            documento.getDocumentElement().normalize();
            filas = documento.getElementsByTagName("network");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    GV.setCurrentEquipo(element.getElementsByTagName("equipo").item(0).getTextContent());
                    GV.setUri(element.getElementsByTagName("uri").item(0).getTextContent());
                    GV.setPort(element.getElementsByTagName("port").item(0).getTextContent());
                }
            }
            
            /***********REGISTRY************************************************/
            Date fecha = null;
            documento.getDocumentElement().normalize();
            filas = documento.getElementsByTagName("registry");
            for (int temp = 0; temp < filas.getLength(); temp++) {
                Node nodo = filas.item(temp);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    GV.setOficina(element.getElementsByTagName("office").item(0).getTextContent());
                    GV.setCompanyNameFromXml(element.getElementsByTagName("company").item(0).getTextContent());
                    GV.setInventarioLocalFromXml(element.getElementsByTagName("inventary").item(0).getTextContent());
                    GV.setLastUpdateFromXml(GV.strToDate(element.getElementsByTagName("last_update_bd").item(0).getTextContent()));
                    GV.setCompanyDescriptionFromXml(element.getElementsByTagName("company_description").item(0).getTextContent());
                    GV.setCompanyRutFromXml(element.getElementsByTagName("company_rut").item(0).getTextContent());
                    GV.setCompanyGiroFromXml(element.getElementsByTagName("company_giro").item(0).getTextContent());
                    GV.setMessageFileFromXml(element.getElementsByTagName("message_file").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            System.out.println("Class RegistroGlobal: Error al cargar xml local");
            return;
        }
    }
}
