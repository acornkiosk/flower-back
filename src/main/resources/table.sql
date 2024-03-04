-- 키오스크 테이블 생성
CREATE TABLE kiosk
(
	id NUMBER PRIMARY KEY, --키오스크 id
	location VARCHAR2(100) NOT NULL,--키오스크 위치
	power VARCHAR2(5) CHECK (power IN ('on', 'off')) NOT NULL--키오스크 전원
);

-- 키오스크 id 시퀀스 생성
CREATE SEQUENCE seq_kiosk_id;

-- 주문 테이블 생성 
CREATE TABLE CART
(
	order_id NUMBER NOT NULL, --전체 주문 id
	id NUMBER PRIMARY KEY, -- 단일 주문 id 
	kiosk_id NUMBER NOT NULL, -- 키오스크 id
	menu_name VARCHAR2(100) NOT NULL, --메뉴 이름
	menu_price NUMBER NOT NULL, --메뉴 가격
	menu_count NUMBER NOT NULL, -- 메뉴 갯수
	OPTIONS VARCHAR2(500), -- 메뉴 옵션
	is_completed VARCHAR2(50) CHECK (is_completed IN ('true', 'false')) NOT NULL, --주문 완료 
	regdate DATE -- 주문등록 시간
);

-- 단일 주문 id 시퀀스 생성
CREATE SEQUENCE seq_order_id;

-- 주문 테이블 id 전용 시퀀스 생성
CREATE SEQUENCE seq_cart_id;


--외래키 추가(kiosk(부모)의 pk를 일반 필드로(FK)로 사용)
ALTER TABLE CART 
ADD FOREIGN KEY (kiosk_id) REFERENCES kiosk(id);


-- 메뉴 id 시퀀스 생성
CREATE SEQUENCE seq_menu_id;

--메뉴 테이블 생성
CREATE TABLE menu (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(50) NOT NULL,
    price NUMBER NOT NULL,
    img_name VARCHAR2(200),
    summary VARCHAR2(50) NOT NULL,
    description VARCHAR2(200) NOT NULL,
    is_sold VARCHAR2(50) CHECK (is_sold IN ('true', 'false')) NOT NULL,
    category_id NUMBER NOT NULL
);


--외래키 추가(COMMON(부모)의 pk를 일반 필드로(FK)로 사용)
ALTER TABLE menu
ADD FOREIGN KEY (category_id) REFERENCES COMMON(code_id);

drop table user_manage

--사용자 테이블 생성
CREATE TABLE USER_MANAGE (
	ID VARCHAR2(100) PRIMARY KEY,
	PASSWORD VARCHAR2(100) NOT NULL,
	USERNAME VARCHAR2(100) NOT NULL,
	RANK NUMBER NOT NULL,
	ROLE VARCHAR2(100) NOT NULL,
	REGDATE DATE NOT NULL
);

--외래키 추가(COMMON(부모)의 pk를 일반 필드로(FK) 사용)
ALTER TABLE USER_MANAGE 
ADD FOREIGN KEY (rank) REFERENCES COMMON(code_id);

--공통 테이블 생성
CREATE TABLE COMMON
(
	code_id NUMBER	PRIMARY KEY,
	p_code_id NUMBER ,
	code_name VARCHAR2(50) NOT NULL,
	code_value VARCHAR2(50),
	code_img VARCHAR2(2000) 
);


