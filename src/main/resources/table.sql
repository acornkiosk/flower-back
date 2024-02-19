-- 키오스크 테이블 생성
CREATE TABLE kiosk
(
	id NUMBER PRIMARY KEY, --키오스크 id
	location VARCHAR2(100) NOT NULL,--키오스크 위치
	power VARCHAR2(5) CHECK (power IN ('on', 'off'))--키오스크 전원
);

-- 키오스크 id 시퀀스 생성
CREATE SEQUENCE seq_kiosk_id;