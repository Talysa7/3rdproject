<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="project" value="/3rdProject/svc/"/> 

<c:set var="page_main" value="메인 페이지"/>
<c:set var="page_input" value="회원가입"/> 
<c:set var="page_login" value="로 그 인"/>
<c:set var="page_delete" value="회 원 탈 퇴"/>
<c:set var="page_modify" value="회원 정보 수정"/>
<c:set var="page_mypage" value="회원 정보"/> <!-- 이름추적 및 교체함 : mypage2 => mypage / 의미없는 숫자 2로 인함 -->

<c:set var="page_main_eng" value="Main"/>
<c:set var="page_board_eng" value="Board"/>
<c:set var="page_album_eng" value="Album"/>
<c:set var="page_calendar_eng" value="My Calendar"/>
<c:set var="page_write" value="글쓰기"/>
<c:set var="page_tripMod" value="글수정"/>
<c:set var="page_not_found" value="페이지를 찾을 수 없습니다."/>

<c:set var="msg_main" value="비회원이시면 회원가입을 해주세요"/>
<c:set var="msg_main_login" value="님 안녕하세요"/>
<c:set var="msg_passwdform" value="비밀번호를 다시 한 번 입력해주세요"/>

<c:set var="EmailId" value="아이디 찾기"/>
<c:set var="EmailPasswd" value="비밀번호 찾기"/>
<c:set var="modError_passwd" value="비밀번호가 일치하지 않습니다."/>
<c:set var="modError_noUser" value="사용자 정보가 올바르지 않습니다."/>
<c:set var="userMod_updateError" value="사용자 정보 수정에 실패했습니다."/>
<c:set var="email_auth_guide" value="메일로 전송된 인증번호를 입력하세요."/>
<c:set var="email_used" value="이미 사용 중입니다. "/>

<c:set var="str_id" value="아이디"/>
<c:set var="str_passwd" value="비밀번호"/>
<c:set var="str_nickname" value="닉네임"/>
<c:set var="str_gender" value="성별"/>
<c:set var="str_gender_f" value="여성"/>
<c:set var="str_gender_m" value="남성"/>
<c:set var="str_email" value="이메일"/>
<c:set var="str_reg_date" value="가입일자"/>
<c:set var="str_tag" value="#태 그"/>
<c:set var="str_searchTag" value="태그검색"/>
<c:set var="str_write" value="글쓰기"/>
<c:set var="str_name" value="이름"/>

<c:set var="btn_adm" value="어드민페이지"/>
<c:set var="btn_user_delete" value="회원탈퇴"/>
<c:set var="btn_login" value="로그인"/>
<c:set var="btn_cancel" value="취소"/>
<c:set var="btn_join" value="가입하기"/>
<c:set var="btn_confirm" value="중복확인"/>
<c:set var="btn_confirm_smtp" value="본인인증"/>
<c:set var="btn_ok" value="확인"/>
<c:set var="btn_modify" value="프로필 수정"/>
<c:set var="btn_delete" value="회원탈퇴"/>
<c:set var="btn_logout" value="로그아웃"/>
<c:set var="btn_mod" value="수정"/>
<c:set var="btn_delete" value="삭제"/>
<c:set var="btn_del" value="탈퇴"/>
<c:set var="btn_list" value="목록"/>
<c:set var="btn_search" value="검색"/>
<c:set var="btn_auth" value="인증"/>
<c:set var="btn_back_admin" value="뒤로"/>
<c:set var="btn_add_trip" value="일정추가"/>
<c:set var="btn_insert_tag" value="태그추가"/>

<c:set var="trip_title" value="제목"/>
<c:set var="trip_writer" value="작성자"/>
<c:set var="trip_content" value="본문"/>
<c:set var="trip_m_num" value="인원"/>
<c:set var="trip_talklink" value="대화 링크"/> <!-- 이름추적 및 교체 필요 -->
<c:set var="trip_tag" value="여행 태그"/>
<c:set var="trip_write" value="등록"/>
<c:set var="trip_location" value="장소"/>
<c:set var="trip_schedule" value="일정"/>
<c:set var="trip_entercontent" value="내용을 입력하세요"/>

<c:set var="moments" value="순간의 순간"/>
<c:set var="trip_member_list" value="참가자"/>

<c:set var="search_trip" value="여행 찾기..."/>
<c:set var="search_guide" value="검색어를 입력하세요"/>
<c:set var="search_result" value="의 검색 결과입니다."/>
<c:set var="search_no_result" value="검색 결과가 없습니다."/>
<c:set var="search_trip_schedule" value="게시물"/>

<c:set var="trip_notice_1" value="[공지]"/>
<c:set var="trip_notice_2" value="공지사항"/>

<c:set var="trip_map" value="지도"/>
<c:set var="trip_photo" value="사진"/>
<c:set var="trip_attend" value="참석"/> 
<c:set var="trip_absent" value="빠지기"/>

<%-- FIXME : 임시 주석처리, 추후 이상없으면 삭제 예정
<c:set var="page_confirm" value="아이디 중복확인"/> <!-- 쓰이는 곳 없음 -->
<c:set var="page_mypage1" value="회 원 정 보"/> <!-- 쓰이는 곳 없음 -->
<c:set var="msg_input" value="회원정보를 입력하세요"/> <!-- 쓰이는 곳 없음 -->
<c:set var="msg_confirm_x" value="는 사용할 수 없는 아이디입니다"/> <!-- 쓰이는 곳 없음 -->
<c:set var="msg_confirm_o" value="는 사용할 수 있는 아이디입니다"/> <!-- 쓰이는 곳 없음 -->
<c:set var="msg_login" value="로그인 정보를 입력하세요"/> <!-- 쓰이는 곳 없음 -->
<c:set var="msg_modify" value="수정할 정보를 입력하세요"/> <!-- 쓰이는 곳 없음 -->
<c:set var="str_repasswd" value="비밀번호 재입력"/> <!-- 쓰이는 곳 없음 -->
<c:set var="trip_talk_link" value="오픈채팅"/> <!-- 쓰이는 곳 없음 -->
<c:set var="str_manual" value="직접입력"/> <!-- 쓰이는 곳 없음 -->
<c:set var="str_jumin" value="주민등록번호"/> <!-- 쓰이는 곳 없음 -->
<c:set var="str_tel" value="전화번호"/> <!-- 쓰이는 곳 없음 -->
<c:set var="btn_join_cancel" value="가입취소"/> <!-- 쓰이는 곳 없음 -->
<c:set var="btn_confirm_cancel" value="확인취소"/> <!-- 쓰이는 곳 없음 -->
<c:set var="btn_mod_cancel" value="수정취소"/> <!-- 쓰이는 곳 없음 -->
<c:set var="btn_del_cancel" value="탈퇴취소"/> <!-- 쓰이는 곳 없음 -->

<c:set var="trip_member_myung" value="명 모집 중!"/> <!-- 쓰이는 곳 없음 -->
<c:set var="trip_from" value="부터 "/> <!-- 쓰이는 곳 없음 -->
<c:set var="trip_until" value="까지"/> <!-- 쓰이는 곳 없음 -->
<c:set var="trip_to" value="로 여행합니다."/> <!-- 쓰이는 곳 없음 -->
<c:set var="trip_go_to_chat" value="채팅하러 가기"/> <!-- 쓰이는 곳 없음 -->
<c:set var="btn_trip_detail" value="번째 여행지"/> <!-- 쓰이는 곳 없음 -->
<c:set var="trip_talk" value="오픈채팅"/> <!-- 쓰이는 곳 없음 -->
<c:set var="place_for_member" value="member list"/> <!-- 쓰이는 곳 없음 -->
 --%>