
부경대학교 지인의 캡스톤 디자인의 도움을 주고자 실행한 프로젝트 입니다.
(스프링부트, mariaDB를 이용하였습니다.)

자율 안전신고 페이지이며
연구실의 위치(이름)와 신고내용을 적는 게시판을 만들었습니다.
메인 화면에서 굴아 작성된 연구실들이 리스트 형식으로 나열되어 있으며,
그 신고내용에 대해 개선할 사항/ 개선된 사항을 볼 수 있는 조회 페이지가 존재합니다

(코드 작성에 대해서는 변경 전 주제(식당 관련)의 코드를 사용하여 혼동을 야기할 수 있습니다.)

## 메인 컨트롤러

### Base Url
- /foodreview

### Endpoints

0. 페이지 템플릿 조회
- Endpoint: /foodreview/exTemplate, /foodreview/exSidebar
- HTTP Method: GET
- Description: 페이지 템플릿과 사이드바를 조회합니다.
  
#### 1. 신고서 목록 페이지 이동
- Endpoint: /foodreview, /foodreview/
- HTTP Method: GET
- Response:
  - Redirect: /foodreview/list
- Description: 기본 URL 접근 시 신고서 목록 페이지로 리디렉션합니다.

#### 2. 신고서 목록 조회
- Endpoint: /foodreview/list
- HTTP Method: GET
- Request Params:
  - page (int): 페이지 번호
  - size (int): 페이지 크기
- Response:
  - View: foodreview/list
  - Model Attributes: result (신고서 목록 결과)
- Description: 신고서 목록을 조회하여 페이지에 표시합니다.

#### 3. 신고서 등록 페이지로 이동
- Endpoint: /foodreview/register
- HTTP Method: GET
- Response:
  - View: foodreview/register
- Description: 신고서 등록 페이지로 이동합니다.

#### 4. 보고서 등록
- Endpoint: /foodreview/register
- HTTP Method: POST
- Request Body:
  - FoodReviewDTO: 등록할 신고서 데이터
- Response:
  - Redirect: /foodreview/list
  - Flash Attribute: msg (등록된 신고서의 ID)
- Description: 새로운 신고서를 등록하고 목록 페이지로 리디렉션합니다.

#### 5. 신고서 읽기 및 수정 페이지 이동
- Endpoint: /foodreview/read, /foodreview/modify
- HTTP Method: GET
- Request Params:
  - fno (Long): 신고서 번호
- Response:
  - View: foodreview/read 또는 foodreview/modify
  - Model Attributes: dto (신고서 데이터), requestDTO (페이지 요청 정보)
- Description: 특정 신고서를 조회하거나 수정 페이지로 이동합니다.

#### 6. 신고서 수정
- Endpoint: /foodreview/modify
- HTTP Method: POST
- Request Body:
  - FoodReviewDTO: 수정할 신고서 데이터
- Request Params:
  - page (int): 페이지 번호
- Response:
  - Redirect: /foodreview/read
- Redirect Params:
  - fno (Long): 리뷰 번호
  - page (int): 페이지 번호
- Description: 특정 리뷰를 수정하고 읽기 페이지로 리디렉션합니다.

<br/>

## 파일 첨부 컨트롤러

### Base Url
- /upload

### Endpoints

#### 1. 파일 업로드
- Endpoint: /uploadAjax
- HTTP Method: POST
- Request Body:
  - MultipartFile[]: 업로드할 파일들
- Response:
  - 200 OK: 파일이 성공적으로 업로드됨
  - 403 FORBIDDEN: 업로드된 파일이 이미지 파일이 아님
- Body: List<UploadResultDTO>
  - fileName (String): 파일 이름
  - uuid (String): 파일 UUID
  - folderPath (String): 파일이 저장된 폴더 경로
- Description: 이미지 파일을 업로드하고 섬네일을 생성합니다.

#### 2. 파일 표시
- Endpoint: /display
- HTTP Method: GET
- Request Params:
  - fileName (String): 파일 이름
  - size (String, optional): 1이면 원본 파일을 표시, 아니면 섬네일을 표시
- Response:
  - 200 OK: 파일이 성공적으로 조회됨
  - 500 INTERNAL SERVER ERROR: 파일 조회 중 오류 발생
  - Body: 파일의 바이트 배열
- Description: 지정된 파일을 조회합니다.

#### 3. 파일 삭제
- Endpoint: /removeFile
- HTTP Method: POST
- Request Params:
  - fileName (String): 삭제할 파일 이름
- Response:
  - 200 OK: 파일이 성공적으로 삭제됨
  - 500 INTERNAL SERVER ERROR: 파일 삭제 중 오류 발생
  - Body: Boolean (파일 삭제 성공 여부)
- Description: 지정된 파일과 해당 섬네일을 삭제합니다.

<br/><br/>

<img width="500" alt="KakaoTalk_Photo_2024-01-17-01-10-14 001" src="https://github.com/Kimhyunjin4455/pkCapstoneSupport/assets/80228088/244383a9-0c9b-4148-a7a5-6f8b801ec444">
<img width="500" alt="KakaoTalk_Photo_2024-01-17-01-10-14 002" src="https://github.com/Kimhyunjin4455/pkCapstoneSupport/assets/80228088/d42d1bad-811a-4992-b4f3-7bddcd15c894">
<img width="500" alt="KakaoTalk_Photo_2024-01-17-01-10-24" src="https://github.com/Kimhyunjin4455/pkCapstoneSupport/assets/80228088/44bc7c3c-5834-47b3-b635-eddebeb92eea">
<img width="500" alt="KakaoTalk_Photo_2024-01-17-01-11-24 001" src="https://github.com/Kimhyunjin4455/pkCapstoneSupport/assets/80228088/9894f302-b888-4294-aba7-d0cef13c9c9b">
<img width="500" alt="KakaoTalk_Photo_2024-01-17-01-15-33 001" src="https://github.com/Kimhyunjin4455/pkCapstoneSupport/assets/80228088/2e4f6adf-7f9d-4385-a0f3-f772f970de86">
<img width="500" alt="KakaoTalk_Photo_2024-01-17-01-15-33 002" src="https://github.com/Kimhyunjin4455/pkCapstoneSupport/assets/80228088/1e45944a-097a-46a5-865f-4d92907c0c9c">



