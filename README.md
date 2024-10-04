# Ch 3. 일정 관리 앱 서버 만들기

## API 명세서

### 공통 요청(Request) 및 응답(Response) 형식
- API 요청 및 응답에 사용하는 데이터 형식은 ‘applicatoin/json’으로 제한하였습니다.
    ```
    Content-Type: application/json
    ```
- 선택한 일정 수정과 삭제는 해당 일정의 고유 식별자를 반환합니다.
  - -1: 해당 스케줄은 DB에 존재하지 않음
  - -2: 비밀번호 불일치
- DB에 저장된 날짜 관련 데이터는 모두 UTC로 저장되어 있습니다.

### API 명세서

| 기능        | Method | URL                          | Request                      | Response  | Return                      |
|-----------|--------|------------------------------|------------------------------|-----------|-----------------------------|
| 일정 생성     | POST   | /api/scheduler               | RequestBody                  | 생성된 일정 정보 | SchedulerResponseDto        |
| 전체 일정 조회  | GET    | /api/scheduler               | -                            | 모든 일정 정보  | List <SchedulerResponseDto> |
| 일정 검색     | GET    | /api/scheduler/search        | RequestParam                 | 검색된 일정 정보 | List <SchedulerResponseDto> |
| 선택한 일정 조회 | GET    | /api/scheduler/{schedulerId} | RequestVariable              | 선택한 일정 정보 | SchedulerResponseDto        |
| 선택한 일정 수정 | PUT    | /api/scheduler/{schedulerId} | RequestVariable, RequestBody | 수정된 일정 ID | Long                        |
| 선택한 일정 삭제 | DELETE | /api/scheduler/{schedulerId} | RequestVariable, RequestBody | 삭제된 일정 ID | Long                        |

#### 1. 일정 생성
- RequestBody
```
{
    "writer": "스파르타",
    "password": "sparta24",
    "todo": "공부하기"
}
```
- ResponseBody
```
{
    "id": 1005,
    "writer": "스파르타",
    "todo": "공부하기",
    "createdDate": "2024-10-04T00:17:40.000+00:00",
    "updatedDate": "2024-10-04T00:17:40.000+00:00"
}
```
<br>

#### 2. 전체 일정 조회
```
[
    {
        "id": 100,
        "writer": "스파르타",
    "todo": "공부하기",
    "createdDate": "2024-10-04T00:17:40.000+00:00",
    "updatedDate": "2024-10-04T00:17:40.000+00:00"
    },
    {
        "id": 3,
        "writer": "새벽이",
        "todo": "Bling Bling is my name",
        "createdDate": "2024-10-03T12:42:15.000+00:00",
        "updatedDate": "2024-10-03T22:42:15.000+00:00"
    },
    {
        "id": 1004,
        "writer": "베르",
        "todo": "간식 먹기",
        "createdDate": "2024-09-28T15:53:07.000+00:00",
        "updatedDate": "2024-10-03T19:44:30.000+00:00"
    },
    {
        "id": 7777777,
        "writer": "하늘",
        "todo": "행복하자~ 아프지망고",
        "createdDate": "2024-10-03T15:57:54.000+00:00",
        "updatedDate": "2024-10-03T15:57:54.000+00:00"
    },
    {
        "id": 2,
         "writer": "스티븐 잡스",
        "todo": "일하자",
        "createdDate": "2024-09-29T15:53:07.000+00:00",
        "updatedDate": "2024-10-01T23:11:22.000+00:00"
    }
]
```
<br>

#### 3. 일정 검색

<br>

#### 4. 선택한 일정 조회
- @PathVariable: 선택한 일정의 고유 식별자
- ResponseBody
```
{
    "id": 1005,
    "writer": "스파르타",
    "todo": "공부하기",
    "createdDate": "2024-10-04T00:17:40.000+00:00",
    "updatedDate": "2024-10-04T00:17:40.000+00:00"
}
```
<br>

#### 5. 선택한 일정 수정
- @PathVariable: 선택한 일정의 고유 식별자
- RequestBody
```
{
    "writer": "스파르타",
    "todo": "공부도 하고~ 쉬기도 하고~ 운동도 하고~ 룰루랄라~ 울랄라~~"
}
```
- Return: 수정된 일정의 고유 식별자

<br>

#### 6. 선택한 일정 삭제
- @PathVariable: 선택한 일정의 고유 식별자
- Return: 삭제된 일정의 고유 식별자

---
## ERD

### 1) 필수 기능 ( ~ Lv 3)
![ERD1](/img/ERD1.png)      

|    | SCHEDULE          | TYPE         |
|:--:|-------------------|--------------|
| PK | SCHEDULER_ID      | BIGINT       |
|    | WRITER            | VARCHAR(30)  |
|    | PASSWORD          | VARCHAR(20)  |
|    | TODO              | VARCHAR(400) |
|    | CREATION_DATE     | TIMESTAMP    |
|    | MODIFICATION_DATE | TIMESTAMP    |

<br>

#### SCHEDULER의 고유 식별자(ID)
    SCHEDULER_ID BIGINT
- SCHEDULER의 고유 식별자는 정수 타입으로 저장되며, 스케줄을 생성할 때 자동 생성됩니다.
- 작성 가능한 스케줄의 갯수를 설정하지 않았으므로, 최대한 많은 글을 허용할 수 있게 BIGINT로 설정하였습니다.

<br>

#### 작성자명
    WRITER VARCHAR(30)
- 작성자명은 NULL을 허용하지 않으며 최대 10자까지 허용합니다.
- 이때 가능한 입력은 이모지를 제외한 영어와 한글, 특수 기호입니다.
 
가장 큰 3바이트의 한글인 경우를 고려하여 크기를 30으로 설정하였습니다.

<br>

#### 비밀번호
    PASSWORD VARCHAR(20)
- 비밀번호는 NULL을 허용하지 않으며 최대 10자까지 허용합니다.
- 이때 모든 입력은 영어나 특수 기호로 입력받습니다.

모든 입력은 영어 및 특수 기호로 1바이트입니다. 따라서 크기를 10으로 설정하였습니다.

<br>

#### 할 일
    SCHEDULE VARCHAR(400)
- 할 일은 최대 100글자까지 허용합니다.

VARCHAR(400)으로 설정한 이유는 먼저 VARCHAR는 가변 길이 문자열을 저장하여 메모리를 효율적으로 사용하기 때문입니다.
크기를 400으로 설정한 이유는 4바이트인 이모지로만 이루어진 경우도 포함하기 위함입니다.
이런 설정은 다양한 문자와 기호를 수용할 수 있도록 하여 데이터의 유연성을 높입니다.

<br>

#### 최초 작성일 및 수정일
    CREATION_DATE TIMESTAMP
    MODIFICATION_DATE TIMESTAMP
- 날짜와 시간을 모두 포함한 형태의 데이터

날짜와 시간을 모두 포함한 형태의 데이터이어야 하므로, DATETIME과 TIMESTAMP를 사용할 수 있습니다.

DATETIME 은 1000-01-01 00:00:00 UTC 부터 9999-12-31 23:59:59 UTC 까지 표시할 수 있지만 8바이트입니다.
TIMESTAMP 는 1970-01-01 00:00:00 UTC 부터 2037-01-19 03:14:07 UTC 까지만 표시할 수 있지만 4바이트입니다.
현재 프로젝트를 진행하는 시점은 2024년이고 향후 10년을 고려해도 2034년까지의 날짜가 필요하므로, 더 적은 메모리를 필요로 하는 TIMESTAMP를 이용하였습니다.

TIMESTAMP은 행이 삽입되거나 업데이트될 때 자동으로 현재 시간을 기록할 수 있어 최초 작성일 및 수정일 관리에 용이합니다.
또한 UTC 기준으로 저장되어 시간대가 서로 다른 경우에도 일관된 시간 기록을 유지할 수 있습니다.
데이터베이스가 설정된 시간대에 따라 자동 변환되므로, 애플리케이션에서 다양한 시간대를 다루기 용이합니다.

따라서 TIMESTAMP가 메모리 효율성, 자동 시간 기록, 시간대 처리 측면에서 이번 프로젝트에 적합합니다.

---
### 2) 도전 기능 (Lv 4)
추후 계속 진행
