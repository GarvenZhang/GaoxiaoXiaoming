/**
 * Created by jm_hello on 2016/10/5.
 */
angular.module('xiaoMing').service('enrollServiceM',['$rootScope','$state','$http','$location','$window',function ($rootScope,$state,$http,$location,$window) {
    var that=this;
    //解决bug
    this.rended=function () {
        $('.skin-flat input').each(function(){
            var self = $(this);
            self.iCheck({
                checkboxClass: 'icheckbox_flat-blue'
            });
        });
    };

    // $(document).ready(function () {
    //    $('#member').on('focus',function () {
    //        imitateEvent('choose_pe');
    //    });
    // });
    $('#member').on('focus',function () {
        console.log('bbbbasd');
        $('#myModel').modal('show');
    });
    /**
     *添加活动报名开始
     */
    this.addActivities=function (title,content,memberId,infos) {
        $http({
            method:'POST',
            url:'activity_save.action',
            data:JSON.stringify({
                'title':title,
                'content':content,
                'memberId':memberId,
                'infos':infos
            })
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                alert('发起活动报名成功！');
                that.askEnrollmentList(7,1,0);
            }else if(data.status=='false'){
                console.info('网络问题，无法获取数据！');
            }
        }).error(function (data,status,header,config) {
            console.error('未知错误，无法获取信息！');
        })
    };
    //得到value值
    this.getVal=function (eleId) {
       return document.getElementById(eleId).value;
    };
    //得到信息项里的值
    this.getInfoVal=function (id) {
        //获取元素
       var input=document.getElementById('tags_'+id+'_tagsinput'),
           aSpan=input.getElementsByClassName('tag'),
         //把得到的值放进数组里，因为数据库里的infos是数组，但是经过this.getValu获得的infos不是数组，只是string
        arr=[];
        //遍历
        for(var i=0,len=aSpan.length;i<len;i++){
            //获取span标签里的innerHTML
            var info= aSpan[i].getElementsByTagName('span')[0].innerHTML;
            //由于有空格&nbsp;，需要用到split,由于用了split后，字符串会变成数组，因此需将split后的数组转化为string
            var val=info.split('&',1).toString();
            //将value传到数组里
            arr.push(val);
        }
        //监听信息项的数据，监听的数组须又数组转化为字符串
        $rootScope.$broadcast('infosArr',arr.join(','));
        //把value存放到数组里
        return arr;
    };
    //未跳转页面，小输入框的键盘事件
    $('#tags_1_tag').on('keydown',function () {
        getAndJudgeInfo();
    });

    //未跳转页面，点击报名按钮，模拟触发帮助按钮的功效
    $('#startEn').on('click',function () {
        imitateEvent('enrolled');
       $rootScope.$broadcast('infosArr3',that.getInfoVal('1'))
    });
    //未跳转页面，弹框消失后，监听人员列表的数据
    $('.choose-staff').on("hidden.bs.modal",function(){
        $rootScope.$broadcast('idArr',$('.send-to').val());
    });
    //未跳转页面，点击我的报名
    $('#myEnrollment').on('click',function () {
        fresh();
    });

    //跳转页面
    $(window).on('hashchange',function () {
        $('#startEn').on('click',function () {
            imitateEvent('enrolled');
        });
        //跳转页面，点击我的报名
        $('#myEnrollment').on('click',function () {
            fresh();
        });
        //小输入框的键盘事件
        $('#tags_1_tag').on('keydown',function () {
            getAndJudgeInfo();
        });
        //弹框消失后，监听人员列表的数据
        $('.choose-staff').on("hidden.bs.modal",function(){
            $rootScope.$broadcast('idArr',$('.send-to').val());
        });

        $('#member').on('focus',function () {
            $('#myModel').modal('show');
        });
    });
    //点击其他页面，再回到发起活动报名上，model要清除
    function fresh() {
        var apply2={};
        $rootScope.$broadcast('clearApply2',apply2);
        imitateEvent('enrollF');
        //信息项的tag移除
        $('.tag').remove();
        //人员列表里的checked类移除
        $('.icheckbox_flat-blue').removeClass('checked');
        //当没有触发人员列表的model时，点击我的报名后，再回到发起活动报名，人员列表上的value值还是会存在，因此要清除掉
        $('.send-to').val('');
    }
    //信息项提示框的出现与隐藏的判断
    function getAndJudgeInfo(ev) {
        var ev=ev||window.event;
        //初始化模拟信息项的输入框的值
        var keycode=10;
        //当用户按下backspace的时候才触发
        if(ev.keyCode==8){
            //当数组为0（信息项输入框没有输入内容或被删除），小输入框没有value值时，将模拟输入框的值清空
            if(that.getInfoVal('1').length==0 && ($('#tags_1_tag').val().length<=0 ||$('#tags_1_tag').val().length==1)){
                keycode='';
                $('#tags_2').val(keycode);
            }
        }
        //广播keycode的变化，不需要再次广播信息项的数据变化
        $rootScope.$broadcast('info',keycode);
    }

    //发起报名
    var memberId=[],infos=[];
    this.makeSureEnroll=function () {
        //当用户没有选择人员的时候，memberId为字符串，否则为数组
        if(that.getVal('send-toId')==''){
            memberId=that.getVal('send-toId');
        }else{
            memberId=that.getVal('send-toId').split(',');
        }
        //当用户没有输入信息的时候，infos为字符串，否则为数组
        if(that.getInfoVal('1').length==0){
            infos=that.getInfoVal('1').toString();
        }else{
            infos=that.getInfoVal('1');
        }
        //当活动名称和内容有输入的时候，才可以发送请求
        if(that.getVal('title')!=''&& that.getVal('content')!=''){
            that.addActivities(that.getVal('title'),that.getVal('content'),memberId,infos);
        }
    };
    /**
     *添加活动报名结束
     */

    /**
     * 请求报名列表开始
     */
        //获取人员列表
    var peopleList;
    this.getPeopleList=function () {
        $http({
            method:'POST',
            url:'member_simpleList.action'
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                $rootScope.$broadcast('getPListData',data.data);
                peopleList=data.data;
            }else if(data.status=='false'){
                console.info('连接上后台但是获取不了数据')
            }
        }).error(function (data,status,header,config) {
            console.error('未知错误，无法获取信息！');
        })
    };
    //初始化总数量
    this.recordCount_C=0;
    this.recordCount_M=0;
    //请求报名列表
    this.askEnrollmentList=function (pageSize,pageNum,type) {
        $http({
            method:'POST',
            url:'activity_list.action',
            data:JSON.stringify({
                'pageSize':pageSize,
                'pageNum':pageNum,
                'type':type
            })
        }).success(function (data,status,header,config) {
            if(data.status=='true' && data.recordList){
                //同一个页面发送不同的请求，找准发送请求的不同点：type,不需要用两个控制器
               if(type===1){
                   $rootScope.$broadcast('askEnrollmentData_C',data.recordList);
                   that.recordCount_C=data.recordCount;
               } else if(type===0){
                   $rootScope.$broadcast('askEnrollmentData_M',data.recordList);
                   that.recordCount_M=data.recordCount;
                }
            }else if(data.status=='false'){
                console.info('与后台连接数据失败，无法获取数据！')
            }
        }).error(function (data,status,header,config) {
            console.error('未知错误，无法获取数据！')
        })
    };
    //时间的分割
    this.splitTime=function (time,judge) {
        if(judge==='1'){
            return time.substring(5,10);
        }else if(judge==='0'){
            return time.substring(11);
        }
    };

    //下拉事件，越滚越多，定义初始化变量，这只是一个倍数。储存下拉的数据条数（原来页面显示的+下拉滚动增加的）
    var enData_C=0,
        enData_M=0;
    //每次打开或者刷新页面，都必须把原来的倍数清空
    window.onhashchange=function () {
        enData_C=enData_M=0;
    };

    //瀑布流
    //可报名活动
    this.getWaterful_C=function () {
        //每次请求的数据
      var count=7*(enData_C+1);
        //两种情况：只获取一页的数据+下拉到底；获取大于一页的数据+下拉到底
        if(that.recordCount_C-count>=0 ||Math.abs(that.recordCount_C-count)<7 && document.getElementById('canEnrollOut_wrap').scrollTop+document.getElementById('canEnrollOut_wrap').clientHeight>=document.getElementById('canEnrollIn_wrap').scrollHeight){
            //满足要求，再次发送数据请求
            that.askEnrollmentList(count,1,1);
            enData_C++;
        }
    };
    //我发起的报名
    this.getWaterful_M=function () {
        //每次请求的数据
        var count=7*(enData_M+1);
        //两种情况：只获取一页的数据+下拉到底；获取大于一页的数据+下拉到底
        if(that.recordCount_M-count>=0 ||Math.abs(that.recordCount_M-count)<7 && document.getElementById('enrollOut_wrap').scrollTop+document.getElementById('enrollOut_wrap').clientHeight>=document.getElementById('enrollIn_wrap').scrollHeight){
            //满足要求，再次发送数据请求
            that.askEnrollmentList(count,1,0);
            enData_M++;
        }
    };

    //获取元素
    var oEnroll_box=document.getElementById('enroll_box'); //报名表单
        oCanEnroll=document.getElementById('canEnroll'),//可报名的活动信息
        oMyEwnroll=document.getElementById('myEnroll'),//我发起的活动信息
        oAcId=document.getElementById('acId');//活动的id
        aInfoLabel=document.getElementsByClassName('infoLabel'),//信息项的标签
        aDiInfos=document.getElementsByClassName('diInfos');//信息项的输入框

    var arr=[],myEnData={};
    //显示报名表，隐藏我的报名
    this.show=function (acName,content,id,info,title) {
        //显示、隐藏
        oEnroll_box.style.display='block';
        oCanEnroll.style.display='none';
        oMyEwnroll.style.display='none';

        /**
         * 处理活动报名
         */
        //获取可活动报名的数据
        myEnData={
            'name':acName,
            'content':content,
            'id':id,
            'info':info,
            'title':title
        };
        //广播
        $rootScope.$broadcast('myEnData',myEnData);

        //把原来的数据清空
        arr=[];
        //遍历json，将信息的键放到数组里
        for(var key in info){
            arr.push(key);
        }
        //监听
        $rootScope.$broadcast('showData',arr);

        // //报名按钮禁用
        // oEnSub.disabled=true;
    };

    //隐藏报名表，返回我的报名
    this.hide=function () {
        oEnroll_box.style.display='none';
        oCanEnroll.style.display='block';
        oMyEwnroll.style.display='block';
        return true;
    };
    /**
     * 请求报名列表结束
     */

    /**
     * 处理活动报名——处理信息开始
     */
    this.getMyEnrollment=function (id,info) {
        $http({
            method:'POST',
            url:'activity_enroll.action',
            data:JSON.stringify({
                'id':id,
                'info':info
            })
        }).success(function (data,status,header,config) {
            if(data.status=='true'){
                confirm('是否报名？');
            }else if(data.status=='false'){
                console.info('网络问题，无法获取数据！');
            }
        }).error(function (data,status,header,config) {
            console.error('未知错误，无法获取信息！')
        })
    };

    //确认报名——将json中的key由数字变成所需信息
    //初始化变量
    var v=[],objV={};
    this.makeSureEnrollment=function (obj) {
        //每次循环，将数组清空
        v=[];
        //删除原json中的undefined
        delete obj.undefined;
        //遍历，将传进来的的json的key存到数组v里
        for(var key in obj){
            v.push(obj[key]);
        }
        //每次循环，把json对象清空
        obj={};
        //遍历
        for(var i=0,len=aInfoLabel.length;i<len;i++){
            //为传进来的json对象重新添加属性和属性值
            obj[aInfoLabel[i].innerHTML]=v[i];

        }
        //再把修改后的json对象传递给新建的变量objV
        objV=obj;
        //发送信息处理请求
        that.getMyEnrollment(oAcId.value,objV);
        return true;
    };
    //点击报名按钮，模拟触发帮助按钮的功效
    $('#backtoRec').on('click',function () {
        imitateEvent('helpBtn');
    });


    //模拟点击事件
   function imitateEvent(id) {
        //创建对象
        var event=document.createEvent('MouseEvents');
        //初始化事件对象
        event.initMouseEvent('click',true,true,document.defaultView,0,0,0,0,0,false,false,false,false,0,null);
        //触发事件
      document.getElementById(id).dispatchEvent(event);
    };
    /**
     * 处理活动报名——处理信息结束
     */

}]);