var json;
window.nicePage = {
    table: "div",
    bar: "bar",
    limit: "3",
    color: "#1E9FFF",
    layout: ["count", "prev", "page", "next", "limit", "skip"],
    setCfg: function(b) {
        nicePage.table = b.table;
        nicePage.bar = b.bar;
        nicePage.limit = b.limit;
        nicePage.color = b.color;
        nicePage.layout = b.layout
    },
    returnHtml: function(g, e) {
        var h = '<table class="layui-table" lay-size="sm"><colgroup>';
        for (var f in e) {
            h += " <col width=" + e[f] + ">"
        }
        h += " </colgroup><thead><tr>";
        for (var f in g) {
            h += "  <th>" + g[f] + "</th>"
        }
        h += " </tr></thead> <tbody>";
        return h
    },
    returnList: function(jsonObj) {
        return jsonObj;
    },
};
function setAffiche(){
	$.ajax({
		cache : true,
		type : "POST",
		url : "/portal/lawappProclamationInfoList",
		data : {
			"noticeType":"0",
		},// 你的formid
		async : false,
		error : function(request) {
			layer.alert("Connection error");
		},
		success : function(data) {
			json = data.rows;
		}
	});
}

$(function() {
	setAffiche();
    layui.use("laypage",
    function() {
        var a = layui.laypage;
        a.render({
            elem: nicePage.bar,
            limit: nicePage.limt,
            theme: nicePage.color,
            count: json.length,
            layout: nicePage.layout,
            jump: function(b) {
            	  b.limit = 4;
                  var packJson = json.concat().splice(b.curr * b.limit - b.limit, b.limit);
                  $(".listul").find("li").remove();
                  for(var p in packJson){
      				var id = packJson[p].id;
      				var noticeName = packJson[p].noticeName;
      				var createTime = packJson[p].createTime;
      				var noticeContent = packJson[p].noticeContent;
      				var imgUrl = packJson[p].imgUrl;
      				
      				let timearr = createTime.replace(" ", ":").replace(/\:/g, "-").split("-");
      				var yearmonth = timearr[0] + "年" + timearr[1].split("")[1] + "月";
      				var day = timearr[2] + "日";
      				var html =
      					"<li>"+
      				        "<div class='liL'>"+
      				          "<div class='day'>"+day+"</div>"+
      				          "<div class='year'>"+yearmonth+"</div>"+
      				        "</div>"+
      				        "<div class='liL afficheImage'>"+
      				          "<a href='/portal/detail?id="+id+"'><img src='http://182.92.56.101:8081/fileStorage/images/noticeImg/2020060116265062498.jpg' width='240' height='130' /></a>"+
      				        "</div>"+
      				        "<div class='liR'>"+
      				          "<a href='/portal/detail?id="+id+"' class='ccsl'>"+noticeName+"</a>"+
      				          "<div class='sub' style='overflow: auto;'>"+noticeContent+"</div>"+
      				        "</div>"+
      				        "<div class='clear'></div>"+
      			        "</li>"
      				 $(".listul").append(html)
      			}
                $(".listul").find("li").each(function(){
               	  if(isEmpty($(this).html())){
               		  $(this).remove();
               	  }
                })
               
            }
        })
    })
});