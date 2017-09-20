<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<title>JsonTest</title>
<script type="text/javascript">
	function requestJson(){
		//alert("test") ;
		$.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/requestJson.action',
			contentType:'application/json;charset=utf-8',
			//数据格式是json串
			data:'{"name":"Phone","price":5600.0}',
			success:function(data){
				//返回json结果
				alert(data.name) ;
			}
		})
	}
	
	function responseJson(){
		$.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/responseJson.action',
			//请求的是ket/value，所以不需要设定contentType
			data:'name=phone&price=1500',
			success:function(data){
				//返回json结果
				alert(data.name) ;
			}
		})
	}
</script>

</head>
<body>
	<input type="button" value="输入json" onclick="requestJson();" />
	<input type="button" value="输入key/value" onclick="responseJson();" /> 
</body>
</html>