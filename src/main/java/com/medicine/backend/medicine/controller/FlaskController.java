package com.medicine.backend.medicine.controller;

import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FlaskController {
    @PostMapping("/send-image")
    @ResponseBody
    public String sendImageToFlask(@RequestParam("image") MultipartFile image) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String serverUrl = "http://localhost:8686/upload_flask";

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

        // Flask 서버로부터 받은 응답 반환
        return response.getBody();
    }
}
