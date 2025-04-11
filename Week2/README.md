## ✅ [2주차 과제] - JPA를 이용하여 게시판CRUD 만들기 (4/7 ~ 4/13)

## 🎯 목표 :

1. CRUD에 대해 공부하고 기본적인 CRUD 기능 구현해보기
2. ERD에 대해 공부하고 직접 ERD 설계 해보기

## **🙏 모든 코드는 참고만 해주세요.**

---

- **⚠️ CRUD기능 구현과 개인 블로그 링크 업로드는 노션 페이지에 13일 일요일 23:59분까지 해주세요!!**
- **⚠️ ERD와 관련된 과제는 2주차 스터디 전까지 해주세요!! 스터디 당일에 간략하게 내용 설명 후 바로 팀을 구성하여 실습을 진행할 예정입니다!**
- **⚠️ ERD 설계 실습을 위해 개인 노트북이 필요합니다!**

### 📌 과제 내용

### 1. ERD와 관련된 내용 공부해오기

- 네이밍 규칙, Type, N:M 연관관계, ERD 설계 방법 등 ERD 설계 실습을 진행하기 위한 공부를 해주세요.
- SQL vs NoSQL, 관계형 데이터 베이스, 키-값(Key-Value) 데이터 베이스, 데이터 무결성(Integrity) 보장등 자유롭게 추가적으로 더 공부를 해본다면 좋을 거 같아요!!

### 2. 게시판(Post) 엔티티 만들기

```java
@Entity
public class Post {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String content;
}
```

### 3. CRUD 기능 구현

| 기능 | HTTP | URI |
| --- | --- | --- |
| 게시글 목록 조회 | GET | `/posts` |
| 게시글 단건 조회 | GET | `/posts/{id}` |
| 게시글 작성 | POST | `/posts` |
| 게시글 수정 | PUT | `/posts/{id}` |
| 게시글 삭제 | DELETE | `/posts/{id}` |

컨트롤러, 서비스, 리포지토리 각각의 역할을 분리해서 구현할 것.

### 4. 어노테이션 개념 정리

- `@RestController`, `@GetMapping`, `@PostMapping`, `@RequestBody`, `@PathVariable` 등
- 각 패키지(controller, service, repository)에서 **무슨 어노테이션이 왜 쓰이는지 설명**

### 5. 과제 수행 내용, 공부한 내용 개인 블로그에 정리하여 노션에 올리기

---

### 1️⃣ 프로젝트 환경

- 필수 의존성 추가:
    - `Spring Web`
    - `Spring Boot DevTools`
    - `Spring Data JPA`
    - `H2 Database` (테스트용, 메모리 DB)
- Java 17, Spring Boot 3.x 기준
- JPA, H2 Database 환경 세팅

---

### 2️⃣ 게시판 엔티티 설계

```java

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt;
}

```

- 게시글은 `id`, `title`, `content`, `작성일`을 포함
- `@Column` 속성을 지정해서 DB 제약조건도 설정

---

### 3️⃣ 계층 분리 구조

```
com.example.board
 ┣ controller  → API 요청 처리
 ┣ service     → 비즈니스 로직
 ┣ repository  → DB 접근
 ┗ dto         → 요청/응답 데이터 객체

```

---

### 4️⃣ DTO 설계

```java
public class PostRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    // getter/setter
}

```

```java
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    // 생성자
}

```

- 요청과 응답을 분리하여 API 명확성 향상
- `@Valid`와 함께 유효성 검증 적용 가능

---

### 5️⃣ CRUD API 명세 및 구현

| 기능 | HTTP | URI | 요청 | 응답 |
| --- | --- | --- | --- | --- |
| 게시글 목록 조회 | GET | `/posts` | 없음 | 게시글 리스트 |
| 게시글 단건 조회 | GET | `/posts/{id}` | 없음 | 게시글 상세 |
| 게시글 작성 | POST | `/posts` | title, content | 생성된 게시글 |
| 게시글 수정 | PUT | `/posts/{id}` | title, content | 수정된 게시글 |
| 게시글 삭제 | DELETE | `/posts/{id}` | 없음 | 삭제 완료 메시지 |

### 컨트롤러 예시

```java
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDto> create(@RequestBody @Valid PostRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.createPost(request));
    }

    @GetMapping("/{id}")
    public PostResponseDto get(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping
    public List<PostResponseDto> list() {
        return postService.getAllPosts();
    }

    @PutMapping("/{id}")
    public PostResponseDto update(@PathVariable Long id, @RequestBody @Valid PostRequestDto request) {
        return postService.updatePost(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.deletePost(id);
    }
}

```

---

### 6️⃣ 예외 처리 추가

- 잘못된 ID로 조회하거나 수정할 경우 404 응답
- 전역 예외 핸들러 추가

```java
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handleNotFound(PostNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}

```

---

### 7️⃣ 테스트 (Postman or HTTPie)

- API를 실제로 테스트해보고 스크린샷 첨부
- 각 요청/응답의 Body 캡처해서 블로그에 포함

---

### 8️⃣ 어노테이션 개념 정리 (블로그 필수 포함)

| 어노테이션 | 위치 | 설명 |
| --- | --- | --- |
| `@RestController` | Controller | JSON 반환 전용 컨트롤러 |
| `@RequestMapping`, `@GetMapping` 등 | Controller | HTTP 메서드 별 매핑 |
| `@PathVariable` | Controller | URL의 `{id}`를 파라미터로 바인딩 |
| `@RequestBody` | Controller | JSON → 객체 매핑 |
| `@Valid` | DTO | 유효성 검증 적용 |
| `@Entity` | Entity | JPA 테이블과 매핑됨 |
| `@Id`, `@GeneratedValue` | Entity | PK 지정 및 자동 생성 |
