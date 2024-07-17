package com.medicine.backend.medicine.controller;

import com.medicine.backend.medicine.dto.MedicineDetail;
import com.medicine.backend.medicine.dto.MedicineInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MedicineController {
    @GetMapping("/home")
    public List<MedicineInfo> getMedicineInfo(
            @RequestParam(name = "search", required = false) String name,
            @RequestParam(name = "efcy", required = false) String efcy
    ) {
        List<MedicineInfo> result = new ArrayList<>();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=46v4f%2FfTVSebPS3vIHjKErfM%2FNWA68Qlu%2FvYdzqhkNx%2B9aSeOLJ%2FFITCYliTwlqWIb%2F3%2BFIY27BrDYUQMuPdrA%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("entpName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("itemSeq", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));

            if (name != null && !name.isEmpty()) {
                urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8"));
            } else {
                urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            }

            if (efcy != null && !efcy.isEmpty()) {
                urlBuilder.append("&" + URLEncoder.encode("efcyQesitm", "UTF-8") + "=" + URLEncoder.encode(efcy, "UTF-8"));
            } else {
                urlBuilder.append("&" + URLEncoder.encode("efcyQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            }

            urlBuilder.append("&" + URLEncoder.encode("useMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("atpnWarnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("atpnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("intrcQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("seQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("depositMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("openDe", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("updateDe", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new java.io.ByteArrayInputStream(sb.toString().getBytes("UTF-8")));

            NodeList itemNameList = doc.getElementsByTagName("itemName");
            NodeList efcyQesitmList = doc.getElementsByTagName("efcyQesitm");
            NodeList useMethodQesitmList = doc.getElementsByTagName("useMethodQesitm");
            NodeList intrcQesitmList = doc.getElementsByTagName("intrcQesitm");
            NodeList itemSeqList = doc.getElementsByTagName("itemSeq");

            for (int i = 0; i < itemNameList.getLength(); i++) {
                MedicineInfo dto = new MedicineInfo();
                dto.setItemName(itemNameList.item(i).getTextContent());
                dto.setEfcyQesitm(efcyQesitmList.item(i).getTextContent());
                dto.setUseMethodQesitm(useMethodQesitmList.item(i).getTextContent());
                dto.setIntrcQesitm(intrcQesitmList.item(i).getTextContent());
                dto.setItemSeq(itemSeqList.item(i).getTextContent());
                result.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/medicine/{itemSeq}")
    public MedicineDetail getMedicineDetail(@PathVariable int itemSeq) {
        MedicineDetail result = null;
        try {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList");
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=46v4f%2FfTVSebPS3vIHjKErfM%2FNWA68Qlu%2FvYdzqhkNx%2B9aSeOLJ%2FFITCYliTwlqWIb%2F3%2BFIY27BrDYUQMuPdrA%3D%3D");
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("entpName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("itemSeq", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(itemSeq), "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("efcyQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("useMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("atpnWarnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("atpnQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("intrcQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("seQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("depositMethodQesitm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("openDe", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("updateDe", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();


            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new java.io.ByteArrayInputStream(sb.toString().getBytes("UTF-8")));


            NodeList itemSeqList = doc.getElementsByTagName("itemSeq");
            NodeList itemNameList = doc.getElementsByTagName("itemName");
            NodeList atpnQesitmList = doc.getElementsByTagName("atpnQesitm");
            NodeList efcyQesitmList = doc.getElementsByTagName("efcyQesitm");
            NodeList useMethodQesitmList = doc.getElementsByTagName("useMethodQesitm");
            NodeList intrcQesitmList = doc.getElementsByTagName("intrcQesitm");
            NodeList seQesitmList = doc.getElementsByTagName("seQesitm");
            NodeList depositMethodQesitmList = doc.getElementsByTagName("depositMethodQesitm");
            NodeList itemImageList = doc.getElementsByTagName("itemImage");


            if (itemNameList.getLength() >0) {
                result = new MedicineDetail();
                result.setItemSeq(Integer.parseInt(itemSeqList.item(0).getTextContent()));
                result.setItemName(itemNameList.item(0).getTextContent());
                result.setAtpnQesitm(atpnQesitmList.item(0).getTextContent());
                result.setEfcyQesitm(efcyQesitmList.item(0).getTextContent());
                result.setUseMethodQesitm(useMethodQesitmList.item(0).getTextContent());
                result.setIntrcQesitm(intrcQesitmList.item(0).getTextContent());
                result.setSeQesitm(seQesitmList.item(0).getTextContent());
                result.setDepositMethodQesitm(depositMethodQesitmList.item(0).getTextContent());
                result.setItemImage(itemImageList.item(0).getTextContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
