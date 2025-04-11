## ✅ [4주차 과제] - RESTful API설계와 예외처리 (4/28 ~ 5/4)

### 🎯 목표:

- RESTful한 URI 구조 학습 및 적용
- 예외처리 방식 도입 (`@ControllerAdvice`)

## **🙏 모든 코드는 참고만 해주세요.**

---

### 📌 과제 내용

### 1. RESTful URI 리팩토링

- 기존 게시판 URI를 다음처럼 개선:
    - `/posts/{postId}/comments/{commentId}` 등 중첩 리소스 설계

### 2. 예외처리 도입

- 예: 존재하지 않는 ID로 조회할 경우 404 반환

```java
java
복사편집
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(Long id) {
        super("Post not found: " + id);
    }
}

```

- 전역 처리 클래스

```java
java
복사편집
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handleNotFound(PostNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

```

### 3. 정리할 개념

- RESTful 설계 원칙
- `@ExceptionHandler`, `@ControllerAdvice`의 차이점과 쓰임
