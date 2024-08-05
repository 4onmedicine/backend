<div align="center">
  <h1> 💊 의약품 간편 검색 서비스, 4온 약 어때? 💊 </h1>
  <img width="75%" alt="Title image" src="https://github.com/user-attachments/assets/ee7378e3-97a4-4bfc-8652-ca4e9b04c1ac">
</div>
<p align="center">
  <strong>
    의약품에 대한 신뢰성 있는 정보를 제공하는 서비스<br>
    <a href="https://4onmedicine.kro.kr">https://4onmedicine.kro.kr</a><br>
    멋쟁이사자처럼 12기 해커톤
  </strong>
</p>
<br>

## 🔍 Introduction
### 기획 배경 및 서비스 특징
<img width="50%" alt="기획 배경 및 서비스 특징" src="https://github.com/user-attachments/assets/e3518789-0869-4b1b-b1b2-28eadacc104a">


## 🖥️ Demo
### 의약품 검색
<img width="850" alt="의약품 검색" src="https://github.com/user-attachments/assets/1e357fe8-a39c-4641-9f78-0902aaf98705">

### 처방전 이미지 검색
<img width="850" alt="처방전 이미지 검색" src="https://github.com/user-attachments/assets/e5a8c774-1512-41bc-a770-7e6a3f7a415c">

### GPT와 대화
<img width="850" alt="GPT와 대화" src="https://github.com/user-attachments/assets/33c2e52b-92cb-48ee-975c-18189637298d">



## 📜 Architecture
<img width="863" alt="architecture" src="https://github.com/user-attachments/assets/dab985ba-68f0-4ee2-bb69-57b5e5b57ac5">

## 💡 Tech Stack
|Frontend|                                                         Backend                                                          |                                                                                                                                                                                          Deployment                                                                                                                                                                                           |
|:------:|:------------------------------------------------------------------------------------------------------------------------:|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|<img src="https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB"/></a>| <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=flat-square&logo=Spring Boot&logoColor=white"/></a><br><img src="https://img.shields.io/badge/flask-%23000.svg?style=for-the-badge&logo=flask&logoColor=white"> | <img src="https://img.shields.io/badge/GoogleCloud-%234285F4.svg?style=for-the-badge&logo=google-cloud&logoColor=white"/></a><br><img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=Amazon Web Services&logoColor=white"/></a><br><img src="https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white"><br><img src="https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white"/></a>

## 📂 Directory Structure

### Backend
```bash
.
├── Dockerfile
├── Dockerfile-nginx
├── build
├── build.gradle
├── gradle
├── gradlew
├── gradlew.bat
├── nginx
│   └── conf.d
│       └── nginx.conf
├── settings.gradle
└── src
    ├── main
    │   ├── java
    │   │   └── com
    │   │       └── medicine
    │   │           └── backend
    │   │               ├── BackendApplication.java
    │   │               └── medicine
    │   │                   ├── config
    │   │                   │   ├── CorsMvcConfig.java
    │   │                   │   └── SwaggerConfig.java
    │   │                   ├── controller
    │   │                   │   ├── FlaskController.java
    │   │                   │   └── MedicineController.java
    │   │                   └── dto
    │   │                       ├── ChatRequest.java
    │   │                       ├── ImgMedicineDetail.java
    │   │                       ├── ImgPayload.java
    │   │                       ├── MedicineDetail.java
    │   │                       └── MedicineInfo.java
    │   └── resources
    │       └── application.properties
    └── test
        └── java
            └── com
                └── medicine
                    └── backend
                        └── BackendApplicationTests.java
```
<br>

## ⚠️ Commit Convention

```
{태그}: {클래스 이름} {커밋 메시지}
```

- 💡 예시: `[Feat] 회원 가입 기능 구현`
- 커밋 내용은 명사로 끝나며 마침표를 사용하지 않는다.

### 태그

- 이모지는 선택에 따라 활용한다.

| 태그       | 설명                      |
|:---------|:------------------------|
| Feat     | 새로운 기능 구현               |
| Fix      | 버그, 오류 수정                   |
| Docs     | README와 같은 문서 수정        |
| Test     | 테스트 코드 추가 및 업데이트        |
| Refactor | 코드 리팩토링                 |
| Comment  | 주석 추가(코드 변경 X) 혹은 오타 수정 |
| Merge    | 다른 브랜치를 merge 할 때 사용                   |
| Add   | Feat 이외의 부수적인 코드 추가, 라이브러리 추가, 새로운 파일 생성 시        |
| Rename   | 파일 이름 변경        |
| Move   | 프로젝트 내 파일이나 코드의 이동        |


## 🦁 Team
|                                                           [한태동](https://github.com/HANTAEDONG)                                                           |                           [한지우](https://github.com/huzan2)                           |                           [최지훈](https://github.com/cjh-19)                           |                         [이정현](https://github.com/LEEJH1029)                          |                                                      [장원준](https://github.com/jangwonjun)                                                      |
|:----------------------------------------------------------------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------:|:------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------:|
| <img width = "300" src ="https://avatars.githubusercontent.com/u/132195232?s=96&v=4"> | <img width = "300" src ="https://avatars.githubusercontent.com/u/95648841?s=96&v=4"> | <img width = "300" src ="https://avatars.githubusercontent.com/u/66457014?s=96&v=4"> | <img width = "300" src ="https://avatars.githubusercontent.com/u/67615226?s=96&v=4"> | <img width = "300" src ="https://avatars.githubusercontent.com/u/41234293?s=96&v=4"> |
|                                                                Frontend Developer                                                                 |                                  Frontend Developer                                  |                                  Backend Developer                                   |                                  Backend Developer                                   |                                                               Backend Developer                                                                |
