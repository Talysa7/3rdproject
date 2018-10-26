### 3rdproject
### 프로젝트 초반 세팅 고려사항
  (git 관련 세팅)
  - github 기능 issues / Projects
  - diff/merge tool (kdiff3 추천)
  - 브랜치 운용 전략
  - tag 를 이용한 master 버젼관리
  
### 세팅 상황
  - develop 브랜치 추가
  - .gitignore 추가
    windows, linux, eclipse, maven, java, git
  - .gitattributes 추가
    대부분의 편집가능한 파일들 text 로 인식케끔,
    추후 개행문자에 lf 가 적용되는 파일(유닉스, 리눅스, 맥os 환경에서 추가, 수정되는 파일들)이
    있을 경우, 해당 환경의 gitconfig에 autocrlf = input 으로 수정이 필요함
  - Maven, MariaDB 적용 

**(수정)**
  - hotfix 브랜치 추가 -> 필요 때만 생성 / 삭제
  - release 브랜치 추가 -> 필요 때만 생성 / 삭제

### git 이용 순서
  1. (작업시작 전) pull 을 당긴다
  2. 작업을 한다
  3. commit 을 한다
  4. (작업 중간) pull 을 당긴다
    5. 충돌을 해결한다 -> 외부병합 툴 이용 가능
    5-1. 충돌을 처리한 이후 내역을 commit 한다
  6. 브랜치에 push 한다
  7. Pull Request 를 요청한다
