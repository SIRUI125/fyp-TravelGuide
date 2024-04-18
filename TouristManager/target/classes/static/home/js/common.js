//显示成功信息
function showSuccessMsg(msg,callback){
	$.confirm({
        title: '成功',
        content: msg,
        type: 'green',
        typeAnimated: false,
        buttons: {
            omg: {
                text: '确定！',
                btnClass: 'btn-green',
                action: function(){
                	callback();
        		}
            }
        }
    });
}
//显示错误信息
function showErrorMsg(msg){
	$.confirm({
        title: '错误',
        content: msg,
        type: 'red',
        typeAnimated: false,
        buttons: {
            omg: {
                text: '知道了！',
                btnClass: 'btn-red',
                action: function(){

        		}
            }
        }
    });
}
//显示警告信息
function showWarningMsg(msg){
	$.confirm({
        title: '警告',
        content: msg,
        type: 'red',
        typeAnimated: false,
        buttons: {
            omg: {
                text: '知道了！',
                btnClass: 'btn-red',
                action: function(){

        		}
            }
        }
    });
}
//表单验证公用方法，传表单form的id进来即可
function checkForm(formId){
	var flag = true;
	$("#"+formId).find(".required").each(function(i,e){
		if($(e).val() == ''){
			showWarningMsg($(e).attr('tips'));
			flag = false;
			return false;
		}
	});
	return flag;
}
//统一图片上传方法
function upload(showPictureImg,input){
	//formdata
	var formData = new FormData();
	formData.append('photo',document.getElementById('select-file').files[0]);
	$.ajax({
		url:'/upload/upload_photo',
		contentType:false,
		processData:false,
		data:formData,
		type:'POST',
		success:function(data){
				if(data.code == 0){
					showSuccessMsg('图片上传成功!',function(){
						$("#"+showPictureImg).attr('src','/photo/view?filename=' + data.data);
						$("#"+input).val(data.data);
					})
				}else{
					data = $.parseJSON(data);
					showErrorMsg(data.msg);
				}
			},
			error:function(data){
				alert('网络错误!');
			}
	});
}

function uploadPhoto(photo,callback){
    //formdata
    var formData = new FormData();
    formData.append('photo',photo);
    $.ajax({
        url:'/upload/upload_photo',
        contentType:false,
        processData:false,
        data:formData,
        type:'POST',
        success:function(data){
            if(data.code == 0){
                callback(data);
            }else{
                //data = $.parseJSON(data);
                showErrorMsg(data.msg);
            }
        },
        error:function(data){
            alert('网络错误!');
        }
    });
}
//统一ajax请求
function ajaxRequest(url,requestType,data,callback){
	$.ajax({
		url:url,
		type:requestType,
		data:data,
		dataType:'json',
		success:function(rst){
			if(rst.code == 0){
				callback(rst);
			}else{
				showErrorMsg(rst.msg);
			}
		},
		error:function(data){
			alert('网络错误!');
		}
	});
}


function KindEditors(id){
    KindEditor.ready(function (K) {
        K.create('#'+id, {
            uploadJson: '/upload/uploadFile',
            filePostName: 'imgFile',
            allowFileManager: true,
            allowImageUpload: true,
            imageSizeLimit:'1MB',
            width: '100%',  //编辑器的宽
            height: '300px',  //编辑器的高
            items: ['fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
                'italic', 'underline', 'removeformat', 'justifyleft', 'justifycenter', 'justifyright',
                'insertorderedlist', 'insertunorderedlist', 'emoticons','image'
            ],
            afterBlur: function () {
                this.sync();
            },
            allowImageRemote: false,
            afterUpload: function (url, data, name) { //上传文件后执行的回调函数，必须为3个参数
                if(data != "undefined"){
                    if (name == "image" || name == "multiimage") { //单个和批量上传图片时
                        var img = new Image();
                        img.src =url;
                        img.onload = function () {
                        }
                    }
                }else{
                    showErrorMsg("图片格式不正确或文件太大");
                }
            }
        });

    });
}
