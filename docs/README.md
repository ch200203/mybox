# 네이버 MYBOX 서버 만들기

## 유저 API
- [x] 유저 생성
  - [x] 유저 ID 중복 확인
  - [ ] 유저 생성시 기본 용량 30GB 할당
- [x] 유저 정보 조회
  - 필수정보 이름, 계정, 현재 사용량
- [ ] 유저 로그인관리
  - [ ] Session 인증

## 파일 API
- [x] 조회
- [ ] 업로드
  - [ ] 업로드하는 파일용량이 남은 용량보다 큰 경우 즉시 실패를 반환
- [ ] 다운로드
- [ ] 삭제
  - [ ] 파일을 삭제하는 경우 삭제한 용량만큼 증가

## 폴더 API
- [ ] 생성
  - [ ] 폴더 생성시 용량 차감은 없음
  - [ ] 같은 경로에 동일한 파일명 존재 불가
  - [ ] 폴더 depth 제한은 없음
- [ ] 삭제
- [ ] 자식 파일/폴더 리스트 조회
- [ ] 다운로드(옵션)
  - [ ] 브라우저에서 파일이 다운로드 가능해야 하며, 다운로드 진행상활 표기
  - [ ] 다운로드 시 폴더 이름으로 된 zip 파일이 다운되도록 구현


## 테이블 설계

### 유저 TABLE
- userNumber Long pk 유저_관리번호
- userId String 유저_아이디
- userPassword String 유저 비밀번호
- usedStorage Long 유저_스토리지_사용량
- userRegDate LocaleDate 유저_등록일

### 폴더 TABLE
- folderId Long pk
- userId String fk
- folderName String
- folderParentId Long 

### 파일 TABLE
- fileId pk 파일_관리번호
- fileName String 파일_이름
- folderId Long 파일_폴더_번호
- filePath String 파일_저장_Location(${userId}/ROOT/1/2/3)
- fileStorage Long 파일_용량
- fileExtension String 파일_확장자
- fileRegDate LocaleDate 파일_업로드일