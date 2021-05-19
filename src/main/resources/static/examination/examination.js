function searchExamination(paperId,scqType,userId){
	var flag = false;//全局变量，以便下面做判断
	var result = "0";
	$.ajax({    
         url: "/exam/qryRoundQuesAll?paperId="+paperId+"&scqType="+scqType+"&userId="+userId,
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