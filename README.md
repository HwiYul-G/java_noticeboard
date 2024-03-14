# java_noticeboard

-----

## dependencies

---

### thymeleaf
- web과 standalone 환경 둘다에 서버 측 java 템플릿 엔진
- 브라우저에서 올바른 HTML을 보여주고, static prototypes로 일한다.


## 기능 구현 목록
### Article
- [x] id, title, content, writer, password, createdAt, updatedAt
- [x] 작성하기(Create)
- [x] 조회하기(Retrieve)
  - [x] id로 조회하기
  - [x] title로 조회하기
  - [x] writer로 조회하기
  - [x] 전체 조회하기
  - [x] 게시된 날짜로 조회하기
    - 사용자에겐 2024-03-09 형태로 받아서 yyyy-MM-dd-HH-mm-ss.zzz 형태에서 yyyy-MM-dd만을 비교한다.
    - 부적절한 게시글 관리를 위해 DB에는 정확한 게시 날짜를 보관할 필요가 있음.
    - 하지만 검색할 때는 시간을 조회하는 게 적절하지 못함.
- [] 수정하기(Update)
  - 비밀번호가 맞으면 수정가능
- [] 삭제하기(Delete)
  - 비밀번호가 맞으면 삭제한다.

### Comment
- [] id, writer, content, password, createdAt, article_id
- [] 작성하기(Create)
- [] 조회하기(Retrieve)
  - [] 특정 article에 관해 항상 조회됨
- [] 삭제하기(Delete)
  - 비밀번호가 맞으면 삭제한다.

### Member
- [] id, email, password, userName, birthday, phone, createdAt
- [] 가입하기(Create)
- [] 회원정보 변경하기(Update)
- [] 탈퇴하기(Delete)

### MemberArticle


---
