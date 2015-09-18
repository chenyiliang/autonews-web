<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第一财经宏观经济新闻自动生成演示</title>
<link rel="stylesheet" type="text/css" href="/css/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="/css/themes/icon.css" />
<script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	function query() {
		var date = $('#invalidFilter').combobox('getValue');
		$.getJSON("/autonews/generate/" + date, function(json) {
			//alert("JSON Data: " + json.article);
			$("#article").html(json.article);
		});
	}
</script>
</head>
<body>
	<center>
		<div id="p" class="easyui-panel" title="第一财经宏观经济新闻自动生成演示" style="width: 600px; height: 400px; background: #fafafa;"
			data-options="footer:'#ft'">
			<div id="cc" class="easyui-layout" data-options="fit:'true'">
				<div data-options="region:'north',border:false" style="height: 40px; background-color: #F4F4F4">
					<div id="tool" style="padding: 8px">
						选择年月: <select id="invalidFilter" class="easyui-combobox" panelHeight="auto"
							style="width: 100px; vertical-align: middle;">
							<option value="201508">2015年08月</option>
							<option value="201507">2015年07月</option>
							<option value="201506">2015年06月</option>
							<option value="201505">2015年05月</option>
							<option value="201504">2015年04月</option>
							<option value="201503">2015年03月</option>
							<option value="201502">2015年02月</option>
							<option value="201501">2015年01月</option>
						</select>
						<button onclick="query()">生成</button>
					</div>
				</div>
				<div data-options="region:'center',border:false">
					<div id="article"></div>
				</div>
			</div>
		</div>
	</center>
	<div id="ft" style="height: 30px;"></div>
</body>
</html>