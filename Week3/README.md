## ✅ [3주차 과제] - DB와 연동 및 쿼리 개념 학습 (4/14 ~ 4/20)

### 🎯 목표:

- Spring Boot + MySQL 연동
- JPA를 통한 데이터 저장/조회
- SQL과 JPA의 연결 고리 이해

### 참고자료 : **생활코딩 - DATABASE2 MySQL (YOUTUBE)**

## **🙏 모든 코드는 참고만 해주세요.**

---

### 📌 과제 내용

### 1. DB 연동 설정 (`application.yml`)

```yaml
yaml
복사편집
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/yourdb
    username: youruser
    password: yourpass
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

```

### 2. 쿼리 테스트

2-(1). 게시판(Post) CRUD 시 실제로 어떤 SQL 쿼리가 나가는지 확인 (`show-sql`)

2-(2). JPA가 어떤 SQL을 대신 처리해주는지 블로그에 캡처와 함께 정리

2-(3). 다음 동작을 수행하고, 그에 따른 SQL을 캡처:

- 게시글 저장
- 게시글 전체 조회
- 게시글 수정
- 게시글 삭제

### 3. 주요 어노테이션 정리

- `@Entity`, `@Id`, `@GeneratedValue`, `@Column` 등
- 각각이 실제 SQL의 어떤 역할을 하는지 명확히 정리할 것
