<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="/Travelers/jquery-3.3.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<c:if test="${sessionScope.user_level ne 9}">
	<script type="text/javascript">
		<!--
			<c:redirect url="login.go"/>
		//-->
	</script>
</c:if>

<c:set var="project" value="/Travelers/adm/"/>

<c:set var="str_logout" value="로그아웃"/>

<c:set var="str_list_m" value="게시물 관리"/>
<c:set var="str_content_v" value="글보기"/>
<c:set var="str_comment_v" value="댓글보기"/>

<c:set var="str_member_m" value="회원관리"/>
<c:set var="str_member_v" value="회원보기"/>

<c:set var="str_tag_m" value="태그 관리"/>
<c:set var="str_tag_v" value="태그 보기"/>

<c:set var="str_photo_m" value="사진관리"/>
<c:set var="str_photo_v" value="사진보기"/>


<c:set var="str_select_all" value="전체 선택"/>

<c:set var="str_num" value="글번호"/>
<c:set var="str_country" value="국가"/>
<c:set var="str_title" value="제목"/>
<c:set var="str_writer" value="작성자"/>
<c:set var="str_content" value="글내용"/>
<c:set var="str_reg_date" value="작성일"/>
<c:set var="str_notice" value="공지여부"/>
<c:set var="str_readcount" value="조회수"/>

<c:set var="str_id" value="아이디"/>
<c:set var="str_name" value="이름"/>
<c:set var="str_email" value="이메일"/>
<c:set var="str_gender" value="성별"/>
<c:set var="str_join_date" value="가입일"/>
<c:set var="str_male" value="남성"/>
<c:set var="str_female" value="여성"/>


<c:set var="str_comment" value="댓글"/>

<c:set var="str_tagid" value="태그id"/>
<c:set var="str_tag" value="태그"/>

<c:set var="str_tag_add" value="태그 추가"/>
<c:set var="str_tag_mod" value="태그 수정"/>
<c:set var="str_mod_tag" value="변경할 내용"/>
<c:set var="str_type" value="태그종류"/>
<c:set var="str_tag_user" value="사용자 성향"/>
<c:set var="str_tag_city" value="도시 이름"/>

<c:set var="btn_delete" value="삭제"/>
<c:set var="btn_modify" value="수정"/>
<c:set var="btn_notice" value=" 공  지 "/>
<c:set var="btn_add" value="추가"/>
<c:set var="btn_cancel" value="취소"/>
<c:set var="btn_check" value="확인"/>

<c:set var="page_list" value="글 목 록"/>










