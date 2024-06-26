# flower kiosk

**팀 프로젝트의 백엔드 Repository 입니다.**

[프론트 Repositroy](https://github.com/acornkiosk/flower_front)<br/>
[키오스크 메인 Repository](https://github.com/acornkiosk/flower_kiosk)
## 프로젝트 특징

* React, Spring Boot을 기반으로 꽃을 판매하는 키오스크 서비스
    
* 프론트엔드와 백엔드를 분리하여 프로젝트 개발
    * 각 파트의 별도 Repository를 생성 후 작업
    * 프론트 : React 프레임워크 이용
    * 백엔드 : Spring Boot를 이용

* 회원가입은 X

* 로그인 처리는 Spring Boot Security와 Jwt Token방식으로 처리

* 초기 더미데이터는 DBeaver를 통한 DB에 직접 삽입
  
* RestApi 방식으로 CRUD 구현
    * 키오스크 정보 추가, 조회, 수정, 삭제, 키오스크 전원 변경   
    * 메뉴 정보 추가, 조회, 수정, 삭제
    * 사용자 정보 추가, 조회, 수정, 삭제
    * 주문 정보 추가, 조회, 수정, 삭제
    
## 개요

* 명칭 : flower_kiosk

* 개발 인원 : 7명

* 개발 기간 : 2024.02.18 ~ 2024.03.29

* 주요 기능 
	* 키오스크 관리 : 추가, 조회, 삭제, 위치 변경, 전원 변경
	* 메뉴 관리 : 추가, 조회, 수정, 삭제
	* 사용자 관리 : 추가, 조회, 수정, 삭제 
	* 주문 관리 : 추가, 조회, 수정 , 삭제
	* 로그인 관리 : 로그인 role 설정, Jwt를 이용한 로그인 상태 유지
	
* 개발 환경 : Springboot 3.2.2, java 17, Oracle

* 형상 관리 툴 : git

* 협업 툴 : Notion  

## 테이블 설계
![flowerDB](https://github.com/acornkiosk/Flower_back/assets/94777814/7ba9f291-2f64-43c0-a15d-a89b6cedfb1b)


## API 설계
![image](https://github.com/acornkiosk/Flower_back/assets/94777814/9009d22c-debf-45c1-9502-144bbc5a843e)
![image](https://github.com/acornkiosk/Flower_back/assets/94777814/e7564ea9-07cb-4164-b72a-0dce3870981f)
![image](https://github.com/acornkiosk/Flower_back/assets/94777814/0734253a-81c6-4287-8f1c-b72decbc88fb)
![image](https://github.com/acornkiosk/Flower_back/assets/94777814/6cc545d5-9c34-42bd-801a-a726668b17e3)
![image](https://github.com/acornkiosk/Flower_back/assets/94777814/25801c98-c4d8-4412-ac83-8e2f92960691)
![image](https://github.com/acornkiosk/Flower_back/assets/94777814/6cf3f077-8d32-4299-ac7f-a5696261be1b)
![image](https://github.com/acornkiosk/Flower_back/assets/94777814/81dad43d-1cbe-48e9-817a-631494b2b34e)

## 로그인 프로세스
![image](https://github.com/acornkiosk/Flower_back/assets/94777814/769b7e44-8d16-45f4-808d-7af70177fd63)
![image](https://github.com/acornkiosk/Flower_back/assets/94777814/957b97ec-fc02-4e73-a76b-cb2fe456cd52)


## 개발 역할분담 (백엔드)

| 이름       | 진행 목록                                                    |
| ------------ | ------------------------------------------------------------- |
| 김동주         | 주문관리 키오스크 관리 테이블 설계, 주문관리 테이블관리 API설계, 초기 프로젝트 셋팅, 웹소켓 |  |                          


| 이름       | 진행 목록                                                    |
| ------------ | ------------------------------------------------------------- |
| 김대원         | CRUD API와 예외처리, 테이블 관계설정, 화면설계도 보충, DB설계, 이미지 관리 API  |  |                         
| 이승우         | 요구사항 기능분석, 메뉴관리 흐름도 및 화면설계, DB설계, 웹소켓 |     


| 이름       | 진행 목록                                                    |
| ------------ | ------------------------------------------------------------- |
| 이준호         | API 설계, 초기 프로젝트 셋팅 |  |                       
| 오영찬         | 사용자관리 테이블 설계, 초기 프로젝트 셋팅 |   |                                                            


| 이름       | 진행 목록                                                    |
| ------------ | ------------------------------------------------------------- |
| 정도경         | security, CustomUserDetailService |  |                         
| 이안철         | JwtToken, Exception, 로그인 관리 api |                                                                

## 개발 타임라인(백엔드, 프론트 공통)

| 일자       | 진행 목록                                                    |
| ---------- | ------------------------------------------------------------ |
| 2024.02.17 | [ 프로젝트 화면계획서 V0.1 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) <br />프론트/백엔드 Repository 생성 |
| 2024.02.19 | [ 프로젝트 화면계획서 V0.3 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) |
| 2024.02.20 | [ 프로젝트 화면계획서 V0.5 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) <br/> 키오스크 관리 DB, API 추가|
| 2024.02.21 | [ 프로젝트 화면계획서 V0.6 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) <br/> 주문 관리 DB, API 추가<br/> 프론트 Repository 생성|
| 2024.02.23 | [ 프로젝트 화면계획서 V0.7 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) <br/> API 명세서 작성|
| 2024.02.24 | 키오스크 관리 기능 완료|
| 2024.02.27 | 주문 관리 기능 완료|
| 2024.02.28 | 사이드바 기능 완료|
| 2024.03.03 | 키오스크 메인 화면 레포 이전|
| 2024.03.04 | 웹소켓 기능 완료|
| 2024.03.06 | 키오스크, 메뉴, 사용자 페이징 처리 API 기능 완료|
| 2024.03.13 | [ 프로젝트 화면계획서 V0.8 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) |
| 2024.03.14 | [ 프로젝트 화면계획서 V0.9 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) |
