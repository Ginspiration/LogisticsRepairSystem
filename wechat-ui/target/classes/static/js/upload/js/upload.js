var pics = [];
var picIndex = -1;
var n = 5;//最多可传几张图
function showPicChoose(id){
	var isAndroid = navigator.userAgent.match(/Android/i);
	var isWechat = navigator.userAgent.match(/MicroMessenger/i);
	if(isAndroid && isWechat){
		var content = 
			`<div style='width:100%;text-align:center;line-height:50px;'>
				<button class="btn" id="${id}_1" style="width:40%;display:inline-block;">
					选择相册
				</button>
				<button class="btn" id="${id}_2" style="width:40%;display:inline-block;">
					拍照
				</button>
			<div>`;
		$.layer.footPage({content:content},"50px");
		$("#"+id+"_1").click(function (){
			//相册
			$('#'+id).removeAttr("capture");
			$('#'+id).attr("accept","image/*;capture=camera");
			$('#'+id).trigger('click');
		});
		
		$("#"+id+"_2").click(function (){
			//拍照
			$('#'+id).attr("accept","image/*");
			$('#'+id).attr("capture","camera");
			$('#'+id).trigger('click');
		});
	}else{
		$('#'+id).trigger('click');
	}
}
/**
 * @param id 上传input的id
 * @param previewId 预览div的id
 * @returns
 */
function changePic(id,previewId){
	var flag = true;
	if(typeof  (FileReader) != "undefined"){
		var imgPreview = $("#"+previewId);
		var regex = /(.jpg|.jpeg|.gif|.png|.bmp)$/;
		var files = $("#"+id)[0].files;
		var picNums = $("#"+previewId+" img").length+files.length;//五张
		if(picNums>n){
			$.weui.alert("最多上传"+n+"张图！");
			return;
		}
		$(files).each(function(i){
			    var file = files[i]; 
			    if (regex.test(files[i].name.toLowerCase())) { 
			    	var reader = new FileReader(); 
			        reader.onload = function (e) { 
			        	pics.push(files[i]);//顺序对应
			        	picIndex = picIndex + 1;
			            var img = "<div class='preview'><img src='"+e.target.result+"'><span class='picRemove' picIndex='"+picIndex+"'>×</span></div>"; 
			            imgPreview.append(img); 
			        } 
			        reader.readAsDataURL(files[i]); 
			    } else { 
			        $.weui.alert(files[i].name + "，图片格式有误！"); 
			        imgPreview.html(""); 
			        flag = false; 
			    } 
		});
		if(!flag){return;}
		addImg(picNums);
	} else { 
		$.weui.alert("异常！"); 
	} 
}
function addImg(nums){
	if(nums==n){
		$(".addIcon").hide();
	}else{
		$(".addIcon").show();
	}
}
$("body").on('click','.picRemove',function(){
	var picIndexTemp = $(this).attr("picIndex");
	picIndex = picIndex - 1;
	$(this).parent().remove();
	$(".picRemove").each(function(){
		var tempIndex = $(this).attr("picIndex");
		if(tempIndex>picIndexTemp){
			$(this).attr("picIndex",Number(tempIndex-1));
		}
	});
	pics.splice(picIndexTemp,1);
	addImg($(".preview>img").length);
});
function sucCompressPic(){
	var param = new FormData();  // 创建form对象
	if(pics.length==0){
		$.weui.alert("请选择上传的图片");
		return;
	}
	pics.forEach(function(file,index){
		param.append('files', file, file.name)  // 通过append向form对象添加数据
		//param.append('fileNames',file.name)
	});
    param.append('otherParam',"otherParam");  //其他参数
    $("#loadingToast").show();
    $.ajax({
	   	 url:'/upload/uploadImg',
	   	 data :param,
	   	 type:'POST',
	   	 contentType: false,    
	   	 processData: false
	}).done(function(data){
		$("#loadingToast").hide();
		console.log(data);
	});
}
