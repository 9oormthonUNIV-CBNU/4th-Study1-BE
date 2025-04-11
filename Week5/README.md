## ✅ [5주차 과제] - 로그인 구현 (5/5 ~ 5/11)

### 🎯 목표:

- 로그인 흐름 이해 및 구현
- 사용자 인증 기본 개념 체험

## **🙏 모든 코드는 참고만 해주세요.**

---

### 📌 과제 내용

### 1. Member 엔티티 설계

```java
java
복사편집
@Entity
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String email;
    private String password;
}

```

### 2. 로그인 API 구현

| 기능 | HTTP | URI |
| --- | --- | --- |
| 회원가입 | POST | `/members` |
| 로그인 | POST | `/login` |
- 로그인 시 입력한 `password`와 DB 저장값 비교

```java
java
복사편집
@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody LoginRequest request) {
    Member member = memberService.findByEmail(request.getEmail());
    if (!member.getPassword().equals(request.getPassword())) {
        return ResponseEntity.status(401).body("비밀번호 틀림");
    }
    return ResponseEntity.ok("로그인 성공");
}

```

- 실제 구현 시 비밀번호는 **암호화(Bcrypt)** 처리가 필요하지만, 이번 주차는 평문 비교 허용

### 3. 학습 정리 항목

- 로그인 흐름: 클라이언트 → 서버 → DB → 응답 구조
- 인증/인가의 차이점
- 이후 세션/토큰 방식과의 차이점 요약 정리 (기초 수준)
