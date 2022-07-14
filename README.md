# spring_mvc_1
### @RestController
- `@Controller` 는 반환 값이 String 이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링 된다.
- `@RestController` 는 반환 값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다. 따라서 실행 결과로 ok 메세지를 받을 수 있다. @ResponseBody 와 관련이 있는데, 뒤에서 더 자세히 설명한다.
  
### 로그 레벨 설정을 변경해서 출력 결과를 보자.
- LEVEL: TRACE > DEBUG > INFO > WARN > ERROR
- 개발 서버는 debug 출력
- 운영 서버는 info 출력

### 로그 레벨 설정
`application.properties`
```
#전체 로그 레벨 설정(기본 info)
logging.level.root=info  

#hello.springmvc 패키지와 그 하위 로그 레벨 설정
logging.level.hello.springmvc=debug
```

### 올바른 로그 사용법
- `log.debug("data="+data)`
  - 로그 출력 레벨을 info로 설정해도 해당 코드에 있는 "data="+data가 실제 실행이 되어 버린다.
  - 결과적으로 문자 더하기 연산이 발생한다.
- `log.debug("data={}", data)`
  - 로그 출력 레벨을 info로 설정하면 아무일도 발생하지 않는다. 따라서 앞과 같은 의미없는 연산이 발생하지 않는다.
  
### 로그 사용시 장점
- 쓰레드 정보, 클래스 이름 같은 부가 정보를 함께 볼 수 있고, 출력 모양을 조정할 수 있다.
- 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영서버에서는 출력하지 않는 등 로그를 상황에 맞게 조절할 수 있다.
- 시스템 아웃 콘솔에만 출력하는 것이 아니라, 파일이나 네트워크 등, 로그를 별도의 위치에 남길 수 있다.
- 특히 파일로 남길 때는 일별, 특정 용량에 따라 로그를 분할하는 것도 가능하다.
- 성능도 일반 System.out보다 좋다. (내부 버퍼링, 멀티 쓰레드 등등) 그래서 실무에서는 꼭 로그를 사용해야 한다.

- `HttpServletRequest`
- `HttpServletResponse`
- `HttpMethod` : HTTP 메서드를 조회한다. org.springframework.http.HttpMethod
- `Locale` : Locale 정보를 조회한다.
- `@RequestHeader` MultiValueMap<String, String> headerMap
  - 모든 HTTP 헤더를 MultiValueMap 형식으로 조회한다.
- `@RequestHeader("host") String host`
  - 특정 HTTP 헤더를 조회한다.
  - 속성
    - 필수 값 여부: required
    - 기본 값 속성: defaultValue
- `@CookieValue(value = "myCookie", required = false) String cookie`
  - 특정 쿠키를 조회한다.
  - 속성
    - 필수 값 여부: required
    - 기본 값: defaultValue
    
## 클라이언트에서 서버로 요청 데이터를 전달할 때는 주로 다음 3가지 방법을 사용한다.

### GET - 쿼리 파라미터
- /url?username=hello&age=20
- 메시지 바디 없이, URL의 쿼리 파라미터에 데이터를 포함해서 전달
- 예) 검색, 필터, 페이징등에서 많이 사용하는 방식
### POST - HTML Form
- content-type: application/x-www-form-urlencoded
- 메시지 바디에 쿼리 파리미터 형식으로 전달 username=hello&age=20
- 예) 회원 가입, 상품 주문, HTML Form 사용
### `HTTP message body`에 데이터를 직접 담아서 요청
- HTTP API에서 주로 사용, JSON, XML, TEXT
- 데이터 형식은 주로 JSON 사용
- POST, PUT, PATCH

## GET, 쿼리 파라미터 전송
###예시
``` java
http://localhost:8080/request-param?username=hello&age=20
```

## POST, HTML Form 전송
### 예시
``` java
POST /request-param ...
content-type: application/x-www-form-urlencoded
username=hello&age=20
```
