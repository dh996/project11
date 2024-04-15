Project title : 임시 DB를 활용한 정보 유출 방지 기술을 적용시킨 간단한 게임 시뮬레이션

진행 기간 : 23년 12월 + 24년 3월 (1~2월 제외)

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



-2 도식화

-1) DB구조

![tempDB](https://github.com/dh996/project11/assets/139844465/2461dc7a-2cac-43b8-a280-55e046dbbd2d)

temp_tempid = 클라이언트로 보내지는 가상의 키값을 저장하는 컬럼

temp_sid = 이 키값을 통해 서버에서 가져오는 데이터의 명칭, 이 프로젝트에서는 sid라는 이름을 사용하였기에 sid라 명명

temp_ip = 가상의 키값을 발급받은 유저의 정보, 클라이언트에서 이 키값을 통해 데이터에 접근하려는 유저를 인식할 때 사용


-2) 알고리즘

![tempFN](https://github.com/dh996/project11/assets/139844465/b76c56f8-1c08-427d-bb7e-7f781681e424)


-3) 기대 효과

데이터를 담은 DB에 접근하기 위한 키 값인 sid에 대한 정보를 서버 내부에서만 다루기 때문에 클라이언트를 통한 유출을 막을 수 있다고 보여진다.

이를 통해 서버가 해킹당하지 않는 이상 서버 내부의 정보를 안전하게 보호할 수 있을 것이라 기대된다.


002. 간단한 게임 시뮬레이션

-1 개요

작성자가 java를 활용한 프로그래밍을 연습하기 위해서 작성한 프로그램으로 다양한 조건을 통한 다양한 결과값을 도출해내는 프로그램을 짜보는 것을 목적으로 제작하였다.

이러한 목적 하에 작성자의 평소 취미인 게임을 소재로 삼는 것이 적합하다고 생각하여 게임 시뮬레이션 프로그램을 제작하였다.

프로그래밍 방식을 배우고 제작한 프로그램이 아니라 다소 비효율적인 코드가 많이 존재할 것으로 여겨진다.


-2 도식화

-1)
