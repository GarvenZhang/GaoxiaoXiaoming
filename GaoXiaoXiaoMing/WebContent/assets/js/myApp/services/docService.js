/**
 * Created by jm_hello on 2016/11/4.
 */
angular.module('xiaoMing').service('docService',['$http','$rootScope','$state',function ($http,$rootScope,$state) {
    var that=this,
        doc=document,
        oAllId=doc.getElementById('allId'),
        oDocName=doc.getElementById('docNames'),
        docData,
        publicFolderId;
    /**
     * 创建文件夹
     */
    this.createFolders=function (parent,name,isPublic) {
        $http({
            method:"POST",
            url:'folder_add.action',
            data:JSON.stringify({
                'parent':{id:parent},
                'name':name,
                'isPublic':isPublic
            })
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                console.info('创建文件夹成功！');
                //创建文件夹成功后，要执行一次查看公开文件夹的数量
                that.getAllFolder_more(1);
            }else if(data.status=='false'){
                console.info('创建文件夹失败！');
            }
        }).error(function (data,status,header,config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };

    /**
     * 获取文件夹
     */
    this.getAllFolder=function (i_p) {
        $http({
            method:'GET',
            url:'folder_openList.action',
            params:{
                'i_p':i_p
            }
        }).success(function (data,status,header,config) {
            //判断是否有公开文件夹，没有则创建一个
            if(!data.data.length){
                that.createFolders(0,'public',true);
            }
            if(data.data.length){
                console.log('获取文件夹成功！');
                //获取id
                that.getAllDoc(1,data.data[0].id);
                publicFolderId=data.data[0].id;
            }else if(data.status==false){
                console.info('获取文件夹失败！');
            }
        }).error(function (data,status,header,config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };
//单纯发送获取文件夹的请求，只在创建完文件后执行
    this.getAllFolder_more=function (i_p) {
        $http({
            method:'GET',
            url:'folder_openList.action',
            params:{
                'i_p':i_p
            }
        }).success(function (data,status,header,config) {
            //多一个限制条件，数组长度必须不为0，才可以执行代码
            if(data.data.length){
                //获取公开文件夹的id
                publicFolderId=data.data[0].id;
                that.getAllDoc(1,data.data[0].id,1);
                console.log('获取文件夹成功！');
            }else if(data.status==false){
                console.info('获取文件夹失败！');
            }
        }).error(function (data,status,header,config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };
    //获取文件
    this.getAllDoc=function (i_p,parentId) {
        $http({
            method:'GET',
            url:'folder_openList.action',
            params:{
                'parentId':parentId,
                'i_p':i_p
            }
        }).success(function (data,status,header,config) {
            if(data.status==true){
                $rootScope.$broadcast('getAllDocs',data.data.documents);
                //传出去全局做备份
                docData=data.data.documents;
            }else if(data.status==false){
                console.info('获取文件失败！');
            }
        }).error(function (data,status,header,config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };

    //删除文件
    this.deleteDoc=function (id) {
        $http({
            method:'GET',
            url:'document_delete.action?id='+id
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                //删除文件
                for(var i=docData.length;i--;){
                    if(docData[i].id==id){
                        docData.splice(i,1);
                    }
                }
            }else if(data.status=='false'){
                console.info('无法下载文件！');
            }
            console.log(111111);
        }).error(function (data,status,header,config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };
    //获取元素
    //显示模态弹框
    this.showModal=function (id) {
        $('#modification').modal('show');
        //赋值id
        oAllId.value=id;
    };
    //修改信息
    this.getDocUrl=function (name,id,isPublic) {
        $http({
            method:'POST',
            url:'document_edit.action',
            data:JSON.stringify({
                'name':name,
                'id':id,
                'isPublic':isPublic
            })
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                //遍历全局数据docData，将修改后的名字数据传到这个全局数据里的name里，保证用户一修改数据，就会不刷新在页面显示
                angular.forEach(docData,function (record) {
                    //同一个文件才修改名字
                    if(record.id==data.id){
                        record.name=data.name
                    }
                });
            }
        }).error(function (data,status,header,config) {
            console.error('网络繁忙，请稍后再试！');
        })
    };

    //确认修改信息
    this.makeSureModificaiton=function () {
        if(oDocName.value!=''){
            that.getDocUrl(oDocName.value,oAllId.value,1);
        }
    };
    //当用户有输入修改的信息时，按确认，弹框才消失
    this.makeModalDispear=function () {
        if(oDocName.value!=''){
            imitateEvent('helpBtn');
        }
    };
    //模拟点击事件
    function imitateEvent(id) {
        //创建对象
        var event=document.createEvent('MouseEvents');
        //初始化事件对象
        event.initMouseEvent('click',true,true,document.defaultView,0,0,0,0,0,false,false,false,false,0,null);
        //触发事件
        document.getElementById(id).dispatchEvent(event);
    };
    //成员的拖拽上传文件
    $(function(){
        var myDropzone = new Dropzone("#my-dropzone", {

            method: "post",
            url:"/GaoXiaoXiaoMing/document_add.action",
            acceptedFiles:'.docx'+','+'.doc'+','+'.txt'+','+'.xlxs'+','+'.xlx'+','+'.ppt'+','+'.pptx',
            // Prevents Dropzone from uploading dropped files immediately
            autoProcessQueue: false,
            init: function() {
                var submitButton = document.querySelector("#submit-all");
                myDropzone = this; // closure
                submitButton.addEventListener("click", function() {
                    myDropzone.processQueue(); // Tell Dropzone to process all queued files.
                });
                this.on("addedfile", function(file) {
                    console.log(file);
                    var mime=['application/vnd.openxmlformats-officedocument.wordprocessingml.document','application/msword',
                        'application/vnd.openxmlformats-officedocument.presentationml.presentation','application/vnd.ms-powerpoint',
                        'text/plain','application/vnd.openxmlformats-officedocument.spreadsheetml.sheet','application/vnd.ms-excel'];
                    if(file.type==mime[0] ||file.type==mime[1] ||file.type==mime[2] ||file.type==mime[3] ||file.type==mime[4] ||file.type==mime[5] ||file.type==mime[6]){
                        console.log('文件格式正确');
                    }else{
                        alert(file.name+'文件格式错误');
                    }

                });
                this.on("success", function(file,status) {
                    var aDz_file_preview_len=doc.getElementsByClassName('dz-file-preview').length,
                        i=(aDz_file_preview_len-2)>>1,
                        j=1+(aDz_file_preview_len-3)>>1;
                    if(status.status=='true'){
                        //上传文件大于2
                        if(aDz_file_preview_len>2){
                            //判断奇、偶数项，用按位与，如果是奇数项，返回0，偶数项，返回1
                            //奇数项
                            if(aDz_file_preview_len & 1)
                                do{
                                    imitateEvent('submit-all');
                                }while (j--);
                            else{
                                //偶数项
                                do{
                                    imitateEvent('submit-all');
                                }while (i--);
                            }
                        }
                        docData.push(status);
                        //广播同一个事件名，将修改后的数据docData广播下去
                        $rootScope.$broadcast('getAllDocs',docData);
                    }
                });
                this.on("sending", function(file, xhr, formData) {
                    formData.append('floderId',publicFolderId);
                    formData.append('_public',1);
                });
            },
            dictDefaultMessage: "将文件拖拽至此（或点击）",
            addRemoveLinks: true
//        dictRemoveFile: "删除"
        });
        //Dropzone.options.myDropzone = ;
        $("#example-advanced").treetable({
            expandable: true
        });
        $('.entypo-share').popover({
            template: '<div class="popover" role="tooltip">'+
            '<h3 class="popover-title"></h3>'+
            '<div class="popover-content"></div>'+
            '</div>',
            html: true,
            content: '<img src="" style="height:150px;width:150px;">',
            placement: "left",
            trigger: 'hover'
        });
    });
}]);