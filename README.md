# 3rdproject
프로젝트 초반 세팅 고려사항
  git 관련 세팅
  - git tool 고려 (source tree)
  - github 기능 issues / Projects
  - diff/merge tool (kdiff3 추천)
  - 브랜치 운용 전략
  
세팅 상황
  - develop 브랜치 추가
  - hotfix 브랜치 추가
  - release 브랜치 추가

git 이용 순서
  1. (작업시작 전) pull 을 당긴다
  2. 작업을 한다
  3. commit 을 한다
  4. (작업 중간) pull 을 당긴다
    5. 충돌을 해결한다 -> 외부병합 툴 이용 가능
    5-1. 충돌을 처리한 이후 내역을 commit 한다
  6. 브랜치에 push 한다
  7. Pull Request 를 요청한다
