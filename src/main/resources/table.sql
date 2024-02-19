-- 키오스크 테이블 생성
CREATE TABLE kiosk
(
	id NUMBER PRIMARY KEY, --키오스크 id
	location VARCHAR2(100) NOT NULL,--키오스크 위치
	power VARCHAR2(5) CHECK (power IN ('on', 'off'))--키오스크 전원
);

-- 키오스크 id 시퀀스 생성
CREATE SEQUENCE seq_kiosk_id;

--주문 테이블 생성
CREATE TABLE cart
(
	id NUMBER NOT NULL, --주문 id
	kiosk_id NUMBER NOT NULL, -- 키오스크 id
	menu_name VARCHAR2(100) NOT NULL, --메뉴 이름
	menu_price NUMBER NOT NULL, --메뉴 가격
	menu_count NUMBER NOT NULL, -- 메뉴 갯수
	OPTIONS VARCHAR2(500) -- 메뉴 옵션
);

-- 주문 테이블 id 전용 테이블 생성
CREATE TABLE cart_id
(
	id NUMBER NOT NULL
);


--외래키 추가(kiosk(부모)의 pk를 일반 필드로(FK)로 사용)
ALTER TABLE CART 
ADD FOREIGN KEY (kiosk_id) REFERENCES kiosk(id);
