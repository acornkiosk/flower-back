# flower kiosk

**팀 프로젝트의 백엔드 Repository 입니다.**

[프론트 Repositroy](https://github.com/acornkiosk/flower_front)
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
![flowerDB](https://github.com/acornkiosk/flower_back/assets/94777814/325cb73e-2d07-403f-bf19-ccf7ecc0e662)


## API 설계

## 로그인 프로세스
![image](https://github.com/acornkiosk/flower_back/assets/94777814/274685fe-30ef-4878-9007-9bfea86ccdac)
![image](https://github.com/acornkiosk/flower_back/assets/94777814/56872714-d87b-4eb9-a54f-cf07016160d7)

## 개발 역할분담 (백엔드)

| 이름       | 진행 목록                                                    |
| ------------ | ------------------------------------------------------------- |
| 김동주         | 주문관리 키오스크 관리 테이블 설계, 주문관리 테이블관리 API설계, 초기 프로젝트 셋팅 |  |                          


| 이름       | 진행 목록                                                    |
| ------------ | ------------------------------------------------------------- |
| 김대원         | 기본 CRUD API와 예외처리, 테이블 관계설정, 화면설계도 보충, DB설계  |  |                         
| 이승우         | 요구사항 기능분석, 메뉴관리 흐름도 및 화면설계, DB설계 |     


| 이름       | 진행 목록                                                    |
| ------------ | ------------------------------------------------------------- |
| 이준호         | API 설계, 초기 프로젝트 셋팅 |  |                       
| 오영찬         | 사용자관리 테이블 설계, 초기 프로젝트 셋팅 |   |                                                            


| 이름       | 진행 목록                                                    |
| ------------ | ------------------------------------------------------------- |
| 정도경         | security, CustomUserDetailService |  |                         
| 이안철         | JwtToken, Exception |                                                                

## 개발 타임라인(백엔드, 프론트 공통)

| 일자       | 진행 목록                                                    |
| ---------- | ------------------------------------------------------------ |
| 2024.02.17 | [ 프로젝트 화면계획서 V0.1 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) <br />프론트/백엔드 Repository 생성 |
| 2024.02.19 | [ 프로젝트 화면계획서 V0.3 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) |
| 2024.02.20 | [ 프로젝트 화면계획서 V0.5 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) <br/> 키오스크 관리 DB, API 추가|
| 2024.02.21 | [ 프로젝트 화면계획서 V0.6 작성](https://drive.google.com/drive/folders/19cVOkx5jpWMl9KqFia3Dd_BrflqpRaVl) <br/> 주문 관리 DB, API 추가<br/> 프론트 Repository 생성|
