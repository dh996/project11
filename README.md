Project title : 임시 DB를 활용한 정보 유출 방지 기술을 적용시킨 간단한 게임 시뮬레이션

진행 기간 : 23년 12월 + 24년 3-5월

작성자의 개인 프로젝트임

Readme 목차
    
001. 임시 DB를 활용한 정보 유출 방지 기술
002. 간단한 게임 시뮬레이션



본론


001. 임시 DB를 활용한 정보 유출 방지 기술

-1 개요

web 환경에서는 클라이언트가 서버에서 데이터를 호출할 때 호출하는 키워드가 되는 정보를 서버로 보내는 것을 통해 데이터를 주고받는다.

이 때 가령 이를 악용하려는 한 이용자가 키워드가 되는 정보를 클라이언트를 통해 얻을 수 있다면 키워드를 통해서 개발자가 의도하지 않은 방식으로 서버에 접근해 데이터를 빼내올 수 있는 경우가 발생할 수 있다.

최근 D-Dos 문제 등으로 뒤숭숭한 시기에 작성자는 위 같은 문제 상황을 가상의 문제상황으로 정의해 이를 해결하기 위한 접근방법을 고안하였다.

클라이언트에서 서버에 접속하여 정보를 얻을 수 있는 키 값을 서버에서 임시DB를 활용해 관리 조율하여 외부로의 누출을 막아보는 방식으로 고안한 알고리즘이다.



-2 세부사항

-1) DB구조

![tempDB](https://github.com/dh996/project11/assets/139844465/2461dc7a-2cac-43b8-a280-55e046dbbd2d)

temp_tempid = 클라이언트로 보내지는 가상의 키값을 저장하는 컬럼

temp_sid = 이 키값을 통해 서버에서 가져오는 데이터의 명칭, 이 프로젝트에서는 sid라는 이름을 사용하였기에 sid라 명명

temp_ip = 가상의 키값을 발급받은 유저의 정보, 클라이언트에서 이 키값을 통해 데이터에 접근하려는 유저를 인식할 때 사용


-2) 알고리즘

![tempFN](https://github.com/dh996/project11/assets/139844465/2af7c155-410c-4498-84c1-16caf5aeaf22)


-3) 기대 효과

데이터를 담은 DB에 접근하기 위한 키 값인 sid에 대한 정보를 서버 내부에서만 다루기 때문에 클라이언트를 통한 유출을 막을 수 있다고 보여진다.

이를 통해 서버가 해킹당하지 않는 이상 서버 내부의 정보를 안전하게 보호할 수 있을 것이라 기대된다.


002. 간단한 게임 시뮬레이션

-1 개요

작성자가 java를 활용한 프로그래밍을 연습하기 위해서 작성한 프로그램으로 다양한 조건을 통한 다양한 결과값을 도출해내는 프로그램을 짜보는 것을 목적으로 제작하였다.

이러한 목적 하에 작성자의 평소 취미인 게임을 소재로 삼는 것이 적합하다고 생각하여 게임 시뮬레이션 프로그램을 제작하였다.

프로그램을 굴리는 기본 정보들을 받아오는 부분과 실제 프로그램, 클라이언트로 분할하여 소개하겠다.

프로그래밍 방식을 배우고 제작한 프로그램이 아니라 다소 비효율적인 코드가 많이 존재할 것으로 여겨진다.

최적화보다는 기능구현에 의의를 두고 진행하였다.

-2 세부사항

-1) 프로그램 구동 데이터를 다루는 부분

-1)) DB구조

![updateLogs](https://github.com/dh996/project11/assets/139844465/d81bfdf1-d094-40ba-96c5-44ed04c85378)

01/ update_logs
   
어떤 버전이 프로그램에 저장되어있는지에 대한 테이블

logs_version = 데이터의 버전 정보

logs_datetime = 버전 정보가 업데이트된 날짜

![updateChamps](https://github.com/dh996/project11/assets/139844465/c6107b95-c212-4645-b126-31903ecfbc88)

02/ update_champs
   
프로그램에서 사용되는 챔피언들의 데이터 테이블

champs_version = 데이터의 버전 정보 - logs 테이블의 version과 외래 키로 연결

champs_cid = 데이터의 고유 키값

champs_name = 데이터의 한글 이름

champs_id = 데이터의 영어 이름

![updateTags](https://github.com/dh996/project11/assets/139844465/99a66843-d58f-4803-8ec7-16b190bc3958)

03/ update_tags
   
각 챔피언들이 가지고 있는 역할군들에 대한 데이터 테이블

tags_cid = 이 태그를 가지고 있는 챔피언의 cid정보 - champs테이블의 cid와 외래 키로 연결

tags_version = 데이터의 버전 정보 - logs테이블의 version과 외래 키로 연결

champs_tags = 역할군 정보

-2)) 알고리즘

![updateFN](https://github.com/dh996/project11/assets/139844465/a5173572-40d9-4590-9b7c-683c41ccc5f0)



-2) 실제 프로그램

-1)) DB구조

![simulList](https://github.com/dh996/project11/assets/139844465/b3b7fd83-c077-4697-8227-a559a0fd7a10)

01/ simul_list
   
프로그램을 통해 실행된 시뮬레이션의 목록

list_sid = 시뮬레이션의 고유 id

list_player = 시뮬레이션을 진행한 유저

list_date = 시뮬레이션을 진행한 날짜

list_winlose = 시뮬레이션의 결과

list_version = 시뮬레이션을 진행한 프로그램의 버전


![simulData](https://github.com/dh996/project11/assets/139844465/902cdb8a-b56c-4321-a166-a2262d62fb0e)

02/ simul_data
   
시뮬레이션 결과의 상세 데이터

list 테이블과 같이 사용했어도 별 문제는 없었을텐데 초반 구상단계에서 살이 하나 둘 붙다보니 추가로 생긴 테이블

data_sid = 시뮬레이션 고유 id - list_sid를 외래 키로

이후의 컬럼은 전부 결과값이 기록된 컬럼임

![simulLogs](https://github.com/dh996/project11/assets/139844465/0a541adc-c55e-4e70-8045-f8b4d5496bb1)

03/ simul_logs

시뮬레이션 진행 과정을 기록한 테이블

logs_sid = 시뮬레이션 고유 id - list_sid를 외래 키로

logs_fid, logs_tid = 시뮬레이션의 어떤 구간에서 일어난 기록인지를 알기 위해 썼었는데 다른 방법을 채택함으로 인해 사용처가 없어짐

logs_message = 시뮬레이션 과정을 기록한 메세지

logs_type = 클라이언트에서 메세지의 스타일을 주기 위한 컬럼

logs_visible = 클라이언트에서 표시하지 않아도 될 정보를 구분하기 위한 컬럼

logs_turncount = 어떤 구간의 메세지인지 구분하기 위한 컬럼

logs_attacker = 클라이언트에서 효과를 구현하기 위한 컬럼1

logs_deffender = 클라이언트에서 효과를 구현하기 위한 컬럼2

-2)) 흐름

![simulFN](https://github.com/dh996/project11/assets/139844465/0b61bd2b-0b07-42ef-bed4-63bb70d1c990)

01/ SimulDefaultSetting

유저가 입력한 데이터를 토대, 유저가 고른 챔피언들의 역할군을 설정, 상대방 챔피언 뽑기 후 역할군 설정

02/ SimulTeamFight

최초 1회, 게임 내부 기본 데이터를 생성후 재귀함수 실행, 함수의 결과를 게임 데이터에 반영한 후 재귀함수를 다시 실행할지 결정, 실행하지 않아도 되는 경우 프로그램 종료

전투 지형 설정 기능, 전투 결과로 인한 전리품 획득 기능, 기간제 버프 관리 기능, 임의의 레벨 업 기능 구현

03/ SimulTurn

첫 번째 재귀함수, 결과값을 좀 더 다양하게 연출하기 위해 만들었으며 메인 루프인 fighting을 반복실행하며 프로그램에 데이터를 누적시킴

한 차례 전투루프가 끝날 때 마다 지속 가능 여부를 판단하는 기능 구현

04/ Fighting

선공권 수치를 참고하여 확률보정한 선제공격 정하기 기능, 지정된 전투패턴을 실행하는 기능(이 기능을 반복실행하는 것으로 다양성을 높이기 위해 재귀함수화 시킴),

각 역할군 별로 다른 대미지를 주는 기능, 중간에 챔피언이 사망하면 다음 타겟으로 변경하는 기능 구현

05/ SimulLogging

기록이 필요한 구간을 상황에 맞는 형태로 DB에 기록하는 기능 구현

-3)) 개발 의의

실무에서 사용되는 신기술을 익힌 것은 아니지만 기초적인 방식을 사용한 다양한 알고리즘 구현을 실현해봄으로써

향후 다양한 기술들을 접했을 때 알게 된 기술들 만큼 응용할 수 있는 여지를 넓히는 데에 도움이 될 것이라 여겨짐
 
-3) 클라이언트

-1)) 시뮬 메인 페이지

![simulHome1](https://github.com/dh996/project11/assets/139844465/b2753407-7087-46c5-8506-69c634022560)

- 기능

데이터 베이스에 저장된 챔피언들의 정보를 받아와 표시, 선택, 제출하여 프로기램을 실행하는 기본적인 기능 탑재

-2)) 시뮬 상세 페이지

![제목 없는 동영상](https://github.com/dh996/project11/assets/139844465/a9754684-e486-43e6-a096-f329884a7df3)

![제목 없는 동영상 (1)](https://github.com/dh996/project11/assets/139844465/40f1a353-db56-4d88-a41c-bd572851d027)

- 기능

프로그램 실행으로 저장된 데이터를 세부적으로 분류하여 출력, 각 상황에 맞는 결과를 클라이언트에 표시하는 기능 구현

-3)) 개발 의의

웹 서비스의 결과물을 제작자의 의도에 맞게 출력하는 경험을 해 봄으로써

출력하기 편한 데이터 구상 및 전해받은 데이터를 목표한 방향대로 출력하는 업무시 도움이 될 것이라 여겨짐

-4 개선점

가끔 여러번 죽는 버그가 발생

코드의 정리를 실행한다면 훨씬 가벼운 프로그램이 될 것이라 여겨짐

형상관리를 처음부터 사용하였다면 진행과정을 기록할 수 있었기에 훗날에 도움이 되었을 것이라 여겨짐
