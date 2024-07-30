package com.medicine.backend.medicine.controller;

import com.medicine.backend.medicine.dto.ChatRequest;
import com.medicine.backend.medicine.dto.ImgMedicineDetail;
import com.medicine.backend.medicine.dto.ImgPayload;
import com.medicine.backend.medicine.dto.MedicineDetail;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class FlaskController {
    @PostMapping("/send-image")
    @ResponseBody
    public String sendImageToFlask(@RequestParam("image") MultipartFile image) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
//        String serverUrl = "http://175.123.252.36:8686/upload_flask";
        String serverUrl = "http://4onprescription.kro.kr/upload_flask";

        // 이미지를 바이트 배열로 변환
        byte[] imageBytes = image.getBytes();

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 파일과 기타 파라미터를 포함하는 MultiValueMap 생성
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        ByteArrayResource resource = new ByteArrayResource(imageBytes) {
            @Override
            public String getFilename() {
                return image.getOriginalFilename();
            }
        };
        body.add("file", resource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 이미지 전송
        ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.POST, requestEntity, String.class);

//        List<Integer> responseList = Arrays.asList(response.getBody().split(","))
//                .stream()
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());

        /* 삭제 필요 */
        /* 공공 데이터 약 낱알 api 호출 */
        // 리스트로 저장
//        List<ImgMedicineDetail> resultList = new ArrayList<>();
//        for (Integer item : responseList) {
//            ImgMedicineDetail result = new ImgMedicineDetail();
//            try {
//                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01"); /*URL*/
//                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=서비스키"); /*Service Key*/
//                urlBuilder.append("&" + URLEncoder.encode("edi_code", "UTF-8") + "=" + URLEncoder.encode(item.toString(), "UTF-8")); /*품목일련번호*/
//                urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*응답데이터 형식(xml/json) default : xml*/
//
//                URL url = new URL(urlBuilder.toString());
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                conn.setRequestProperty("Content-type", "application/json");
//                System.out.println("Response code: " + conn.getResponseCode());
//                BufferedReader rd;
//                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                } else {
//                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//                }
//                StringBuilder sb = new StringBuilder();
//                String line;
//                while ((line = rd.readLine()) != null) {
//                    sb.append(line);
//                }
//                rd.close();
//                conn.disconnect();
//
//                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder builder = factory.newDocumentBuilder();
//                Document doc = builder.parse(new java.io.ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
//
//                NodeList itemNameList = doc.getElementsByTagName("ITEM_NAME");
//
//                if (itemNameList.getLength() > 0) {
//                    result.setItemName(itemNameList.item(0).getTextContent());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            resultList.add(result);
//
//        }
//
//        /* resultList 돌면서 약 정보 추출*/
//        List<MedicineDetail> resultMedicineList = new ArrayList<>();
//        for (ImgMedicineDetail imgMedicineDetail : resultList) {
//            MedicineDetail medicineDetail = null;
//            try {
//                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList");
//                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=46v4f%2FfTVSebPS3vIHjKErfM%2FNWA68Qlu%2FvYdzqhkNx%2B9aSeOLJ%2FFITCYliTwlqWIb%2F3%2BFIY27BrDYUQMuPdrA%3D%3D");
//                urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(imgMedicineDetail.getItemName(), "UTF-8"));
//                urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
//
//                URL url = new URL(urlBuilder.toString());
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                conn.setRequestProperty("Content-type", "application/json");
//                System.out.println("Response code: " + conn.getResponseCode());
//                BufferedReader rd;
//                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                } else {
//                    rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//                }
//                StringBuilder sb = new StringBuilder();
//                String line;
//                while ((line = rd.readLine()) != null) {
//                    sb.append(line);
//                }
//                rd.close();
//                conn.disconnect();
//
//
//                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder builder = factory.newDocumentBuilder();
//                Document doc = builder.parse(new java.io.ByteArrayInputStream(sb.toString().getBytes("UTF-8")));
//
//
//                NodeList itemSeqList = doc.getElementsByTagName("itemSeq");
//                NodeList itemNameList = doc.getElementsByTagName("itemName");
//                NodeList atpnQesitmList = doc.getElementsByTagName("atpnQesitm");
//                NodeList efcyQesitmList = doc.getElementsByTagName("efcyQesitm");
//                NodeList useMethodQesitmList = doc.getElementsByTagName("useMethodQesitm");
//                NodeList intrcQesitmList = doc.getElementsByTagName("intrcQesitm");
//                NodeList seQesitmList = doc.getElementsByTagName("seQesitm");
//                NodeList depositMethodQesitmList = doc.getElementsByTagName("depositMethodQesitm");
//                NodeList itemImageList = doc.getElementsByTagName("itemImage");
//
//
//                if (itemNameList.getLength() > 0) {
//                    medicineDetail = new MedicineDetail();
//                    medicineDetail.setItemSeq(Integer.parseInt(itemSeqList.item(0).getTextContent()));
//                    medicineDetail.setItemName(itemNameList.item(0).getTextContent());
//                    medicineDetail.setAtpnQesitm(atpnQesitmList.item(0).getTextContent());
//                    medicineDetail.setEfcyQesitm(efcyQesitmList.item(0).getTextContent());
//                    medicineDetail.setUseMethodQesitm(useMethodQesitmList.item(0).getTextContent());
//                    medicineDetail.setIntrcQesitm(intrcQesitmList.item(0).getTextContent());
//                    medicineDetail.setSeQesitm(seQesitmList.item(0).getTextContent());
//                    medicineDetail.setDepositMethodQesitm(depositMethodQesitmList.item(0).getTextContent());
//                    medicineDetail.setItemImage(itemImageList.item(0).getTextContent());
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            resultMedicineList.add(medicineDetail);
//        }

        // 최종 약 정보 리스트 반환
        return response.getBody();
    }

    @PostMapping("/receive-data")
    public List<MedicineDetail> receiveData(@RequestBody ImgPayload payload) {
/*        try {
            // DTO에서 데이터 추출
            List<String> receivedData = payload.getData();

            // 받은 데이터 처리 (여기서는 단순히 출력만 함)
            System.out.println("Received data from Flask: " + receivedData);

            // JSON 형식의 응답 생성
            Map<String, String> response = new HashMap<>();
            response.put("message", "Data received successfully!");

            // 명시적으로 응답 상태 코드와 JSON 메시지 설정
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            // 오류 발생 시
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }*/


        // 약 정보 추출

        /* 공공 데이터 약 낱알 api 호출 */
        // 리스트로 저장
        List<ImgMedicineDetail> resultList = new ArrayList<>();
        for (String item : payload.getData()) {
            ImgMedicineDetail result = new ImgMedicineDetail();
            try {
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/MdcinGrnIdntfcInfoService01/getMdcinGrnIdntfcInfoList01"); /*URL*/
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=서비스키"); /*Service Key*/
                urlBuilder.append("&" + URLEncoder.encode("edi_code", "UTF-8") + "=" + URLEncoder.encode(item, "UTF-8")); /*품목일련번호*/
                urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*응답데이터 형식(xml/json) default : xml*/

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

                NodeList itemNameList = doc.getElementsByTagName("ITEM_NAME");

                if (itemNameList.getLength() > 0) {
                    result.setItemName(itemNameList.item(0).getTextContent());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultList.add(result);

        }

        /* resultList 돌면서 약 정보 추출*/
        List<MedicineDetail> resultMedicineList = new ArrayList<>();
        for (ImgMedicineDetail imgMedicineDetail : resultList) {
            MedicineDetail medicineDetail = null;
            try {
                StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList");
                urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=46v4f%2FfTVSebPS3vIHjKErfM%2FNWA68Qlu%2FvYdzqhkNx%2B9aSeOLJ%2FFITCYliTwlqWIb%2F3%2BFIY27BrDYUQMuPdrA%3D%3D");
                urlBuilder.append("&" + URLEncoder.encode("itemName", "UTF-8") + "=" + URLEncoder.encode(imgMedicineDetail.getItemName(), "UTF-8"));
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


                if (itemNameList.getLength() > 0) {
                    medicineDetail = new MedicineDetail();
                    medicineDetail.setItemSeq(Integer.parseInt(itemSeqList.item(0).getTextContent()));
                    medicineDetail.setItemName(itemNameList.item(0).getTextContent());
                    medicineDetail.setAtpnQesitm(atpnQesitmList.item(0).getTextContent());
                    medicineDetail.setEfcyQesitm(efcyQesitmList.item(0).getTextContent());
                    medicineDetail.setUseMethodQesitm(useMethodQesitmList.item(0).getTextContent());
                    medicineDetail.setIntrcQesitm(intrcQesitmList.item(0).getTextContent());
                    medicineDetail.setSeQesitm(seQesitmList.item(0).getTextContent());
                    medicineDetail.setDepositMethodQesitm(depositMethodQesitmList.item(0).getTextContent());
                    medicineDetail.setItemImage(itemImageList.item(0).getTextContent());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultMedicineList.add(medicineDetail);
        }

        // 최종 약 정보 리스트 반환
        return resultMedicineList;
    }

    @PostMapping(value = "/chat", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String medicineChat(@RequestBody ChatRequest chatRequest) {
        RestTemplate restTemplate = new RestTemplate();
//        String serverUrl = "http://175.123.252.36:8686/chat";
        String serverUrl = "http://4onprescription.kro.kr/chat";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("message", chatRequest.getMessage());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);

        return response.getBody();
    }
}
