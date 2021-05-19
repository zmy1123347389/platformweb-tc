var json;
window.nicePage = {
    table: "div",
    bar: "bar",
    limit: "6",
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
function searchNotice(){
	$.ajax({
		cache : true,
		type : "POST",
		url : "/exam/selectPapersInfo",
		data : {
			"scqType":"6",
			"paperId":""
		},
		async : false,
		error : function(request) {
			layer.alert("Connection error");
		},
		success : function(data) {
			json = data;
		}
	});
}
function searchExamination(paperId,scqType){
	var flag = false;//全局变量，以便下面做判断
	var result = "0";
	$.ajax({    
         url: "/exam/qryRoundQuesAll?paperId="+paperId+"&scqType="+scqType,
         type: 'POST',    
         dataType: 'JSON',    
         cache: false,    
         processData: false,    
         contentType: false,
      	 async:false
     }).done(function(data){
		if(!isEmpty(data.respCode) && data.respCode == "-1"){
			result = "-1";
		}else{
			result = "0";
		}
		flag = true;
     });
	if(flag){
		return result;
    } 
}
function isEmpty(value){
	if(value == null || value == "" || value == "undefined" || value == undefined || value == "null"){
		return true;
    }else{
    	//防止非字符串存在
    	value += "";
    	value = value.replace(/\s/g,"");
        if(value == ""){
            return true;
        }
        return false;
    }
}
$(function() {
	searchNotice();
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
                  var jsonObj = json.concat().splice(b.curr * b.limit - b.limit, b.limit);
                  $(".piclistul").find("li").remove();
                  var examinationHtml
                  for (var i in jsonObj) {
                      var paperId = jsonObj[i].paperId;
          			var scqType = jsonObj[i].scqType;
          			
          			var startTime = jsonObj[i].startTime;
          			var endTime = jsonObj[i].endTime;
          			
          			var paperName = jsonObj[i].paperName;
          			var paperTScores = jsonObj[i].paperTScores;
          			var paperTime = jsonObj[i].paperTime;
          			var scqNum = jsonObj[i].scqNum;
          			var scqSScores = jsonObj[i].scqSScores;
          			var jqNum = jsonObj[i].jqNum;
          			var jqSScores = jsonObj[i].jqSScores;
          			var aqNum = jsonObj[i].aqNum;
          			var aqSScores = jsonObj[i].aqSScores;
          			
          			var time = paperTime/60
          			var examinationHtml =
          				"<li>"+
          				  "<div class='col-lg-4 col-md-4 col-sm-4 col-xs-12'>"+
                              "<div class='services-group wow animated fadeInLeft animated' data-wow-offset='40' style='visibility: visible;border-radius: .625rem;'>"+
                                  "<h4 class='services-title'>"+paperName+"</h4>"+
                                  "<p class='services-body'>总分"+paperTScores+"分，考试时间"+paperTime+"分钟</p>"+
                                  "<p class='services-body'>选择题"+scqNum+"题,每题"+scqSScores+"分，多选题"+jqNum+"题,每题"+jqSScores+"分，判断题"+aqNum+"题,每题"+aqSScores+"分</p>";
                                  var code = searchExamination(paperId,scqType);
               examinationHtml += "<p class='services-body'>考试时间："+startTime+"----"+endTime+"</p>";  
                  				if(code == "-1"){
               examinationHtml += "<p class='services-more'><a href='javascript:void(0);' class='btn btn-pill btn-primary mt-4'>考试时间已结束</a></p>";
                  				}else{
               examinationHtml += "<p class='services-more'><a class='btn btn-pill btn-primary mt-4' href='javascript:void(0);' onclick=\"skipExamination('"+scqType+"','"+paperId+"')\">开始考试</a></p>";   						
                  				}
          examinationHtml += "</div>"+
          	              "</div>"+
          				"<li>";
          				$(".piclistul").append(examinationHtml);
                  }
                  $(".piclistul").find("li").each(function(){
                	  if(isEmpty($(this).html())){
                		  $(this).remove();
                	  }
                  })
               
            }
        })
    })
});