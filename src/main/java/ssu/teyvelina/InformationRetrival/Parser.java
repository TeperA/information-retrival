package ssu.teyvelina.InformationRetrival;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Parser {
    public static String findDescription(String inputString, String name){
        //bcontent
        //System.out.println(inputString);
        String val = "";
        int pos = inputString.indexOf(name);
        pos += name.length();
        while(inputString.charAt(pos) != '\n' && inputString.charAt(pos) != '<'){
            val += inputString.charAt(pos);
            pos++;
        }
        return val;
    }

    public  static String findUrl(String inputString, String name){
        String val = "";
        int pos = inputString.indexOf(name);
        pos += name.length();
        while(inputString.charAt(pos) != '\"'){
            val += inputString.charAt(pos);
            pos++;
        }
        return "https://www.regard.ru" + val;
    }

    public static String findId(String inputString, String name){
        String val = "";
        int pos = inputString.indexOf(name);
        pos += name.length();
        while(inputString.charAt(pos) != '\n'){
            val += inputString.charAt(pos);
            pos++;
        }
        return val;
    }

    //name, price
    public static String findValue(String inputString, String name){
        String val = "";
        int pos = inputString.indexOf(name);
        pos += name.length();
        while(inputString.charAt(pos) != '<'){
            val += inputString.charAt(pos);
            pos++;
        }
        return val;
    }
    public static String findBrand(Elements goodElements){
        String brandName = "";
        for (int k = 0; k < goodElements.size(); k++){
            Element goodElement = goodElements.get(k);
            String elem = goodElement.toString();
            if (elem.contains("Производитель</td>")) {
                int brandIdx = elem.indexOf("Производитель</td>");// + </td>
                //</td>\n<td>
                brandIdx += "Производитель".length() + 11;
                while (elem.charAt(brandIdx) != '\n' && elem.charAt(brandIdx) != '<'){ // + <
                    brandName += elem.charAt(brandIdx);
                    brandIdx++;
                }
                //System.out.println(brandName);
            }
        }
        return brandName;
    }
    public static String findType(Elements goodElements){
        String type = "";
        for (int k = 0; k < goodElements.size(); k++){
            Element goodElement = goodElements.get(k);
            String elem = goodElement.toString();
            if (elem.contains("<td>Тип <")) {
                int idx1 = elem.indexOf("Тип");
                //</td>\n<td>
                int typeIdx = elem.indexOf("<td>", idx1);
                typeIdx += "<td>".length();
                while (elem.charAt(typeIdx) != '<'){ //<
                    type += elem.charAt(typeIdx);
                    typeIdx++;
                }
                //System.out.println(brandName);
            }
        }
        return type;
    }

    // клавиатуры
    public static String findTypeDev(Elements goodElements){
        String type = "";
        for (int k = 0; k < goodElements.size(); k++){
            Element goodElement = goodElements.get(k);
            String elem = goodElement.toString();
            if (elem.contains("<td>Тип устройства <")) {
                int idx1 = elem.indexOf("Тип устройства");
                //</td>\n<td>
                int typeIdx = elem.indexOf("<td>", idx1);
                typeIdx += "<td>".length();
                while (elem.charAt(typeIdx) != '<'){ //<
                    type += elem.charAt(typeIdx);
                    typeIdx++;
                }
                //System.out.println(brandName);
            }
        }
        return type;
    }
}