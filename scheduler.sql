# CREATE table SCHEDULER
CREATE TABLE IF NOT EXISTS SCHEDULER
(
    SCHEDULER_ID BIGINT AUTO_INCREMENT PRIMARY KEY comment 'SCHEDULER의 고유 식별자',
    WRITER VARCHAR(30) NOT NULL comment '작성자명',
    PASSWORD VARCHAR(20) NOT NULL comment '비밀번호',
    TODO VARCHAR(400) comment '할 일',
    CREATION_DATE TIMESTAMP comment '최초 작성일',
    MODIFICATION_DATE TIMESTAMP comment '수정일'
);

-- 위 코드는 아래 수정 과정 후, 완성된 코드
/*
# CREATE table SCHEDULER
CREATE TABLE IF NOT EXISTS SCHEDULER
(
    SCHEDULER_ID BIGINT AUTO_INCREMENT PRIMARY KEY comment 'SCHEDULER의 고유 식별자',
    WRITER VARCHAR(30) NOT NULL comment '작성자명',
    PASSWORD VARCHAR(20) NOT NULL comment '비밀번호',
    SCHEDULE VARCHAR(400) comment '할 일',
    CREATION_DATE TIMESTAMP comment '최초 작성일',
    MODIFICATION_DATE TIMESTAMP comment '수정일'
);

# SCHEDULE 컬럼명 변경
# '할 일'을 담은 내용과 일정을 나타내는 SCHEDULE이라는 이름이 맞지 않는 듯하여, 좀 더 적합한 TODO 로 변경
ALTER TABLE SCHEDULER CHANGE SCHEDULE TODO VARCHAR(400);

# '할 일' comment를 추가하지 않아서 comment 추가
ALTER TABLE SCHEDULER MODIFY TODO VARCHAR(400) comment '할 일';
*/
