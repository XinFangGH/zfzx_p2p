/**
 * Created by JetBrains WebStorm.
 * User: Administrator
 * Date: 12-6-28
 * Time: ����11:39
 * To change this template use File | Settings | File Templates.
 */
function preview(img, selection){
    var scaleX = 100 / (selection.width || 1);
    var scaleY = 100 / (selection.height || 1);

    //��̬Сͷ�� ��ȡ��ǰѡ�п�Ŀ�ȣ��߶ȣ���߿��ұ߿�
    $('#view').css({                          //view��Ԥ��ͼ���id
        width: Math.round(scaleX * 300) + 'px',
        height: Math.round(scaleY * 220) + 'px',
        marginLeft: '-' + Math.round(scaleX * selection.x1) + 'px',
        marginTop: '-' + Math.round(scaleY * selection.y1) + 'px'
    });
}

//����Сͷ��
$(document).ready(function () {
    $('<div><img id="view" src="#" style="position: relative;" /></div>').css({
            float: 'left',
            position: 'relative',
            overflow: 'hidden',
            width: '100px',
            height: '100px'
        }).insertAfter('#biuuu'); //���½�Ԫ�طŵ� #biuuu ֮��
    //.insertAfter($('#biuuu')); //���½�Ԫ�طŵ� #biuuu ֮��
    $("input[type='file']").change(function(){
        document.form1.path.value=this.value;
        previewImage(this);
        //$("#image_area").find("img").attr({"src":this.value});
    });
    /*$("#upload_area").find("a").click(function(){debugger
        document.form1.picpath.click();
        console.log($("#upload_area").find("a")[0]);
    });*/
	$("#select").click(function(){
		document.form1.picpath.click();
	});
    $("#submit_button").find("a").click(function(){debugger
 		$("#form1").submit();   
        //$("form:first").submit();
    });
    if(!+[1,]){
        $("#upload_area").find("a").hide();
        $("#upload_area").find("input[type='text']").hide();
        $("#picpath").css({
            width: "240px",
            height: "20px",
            filter:"alpha(opacity=100)"
        });
    }
});

//��ʼ������
$(window).load(function () {
    $('#biuuu').imgAreaSelect({
        aspectRatio: '1:1',  //��ȡ����
        show:true,
        resizable:true, //�Ƿ�ɵ����С
        autoHide: false,//ѡ���ѡ������Ƿ��Լ�ȡ��
        handles:true,
        key:true, //�Ƿ����ü��̣�Ĭ��Ϊfalse
        //x1: 75, y1: 30, x2: 225, y2: 180, //��Ҫ���������ԭʼ��
        //x1:���Ͻ�x����� y1:���Ͻ�y����� x2:���½�x����� y2:���½�y�����
        keys: { arrows: 1, ctrl: 5, shift: 'resize' }, //�������ش�С

        //onInit: function(img, selection) { ias.setSelection(100, 50, 250, 150, true); ias.update(); }, //���ó�ʼ���� ����ѡ���
        onSelectChange: preview //ѡ���ƶ�ʱ�������¼�
        //onSelectEnd: function(img, select){alert(select.width)}  //ѡ�����ʱ�������¼�

    });
});

function previewImage(file)
{
    var porImg  = $('#biuuu'),
        viewImg  = $('#view');
    if (file["files"] && file["files"][0])
    {
        var reader = new FileReader();
        reader.onload = function(evt){
            porImg.attr({src : evt.target.result});
            viewImg.attr({src : evt.target.result});
        }
        reader.readAsDataURL(file.files[0]);
    }
    else
    {
        /*var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text,
            mysrc = sFilter+src;
        porImg.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
       // porImg.attr({mysrc:"",class:"aaa"});
       */
        var ieImageDom = document.createElement("div");
        var proIeImageDom = document.createElement("div");
        $(ieImageDom).css({
            float: 'left',
            position: 'relative',
            overflow: 'hidden',
            width: '100px',
            height: '100px'
        }).attr({"id":"view"});
        $(proIeImageDom).attr({"id":"biuuu"});
        porImg.parent().prepend(proIeImageDom);
        porImg.remove();
        viewImg.parent().append(ieImageDom);
        viewImg.remove();
        file.select();
        path = document.selection.createRange().text;
        $(ieImageDom).css({"filter": "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")"});
        $(proIeImageDom).css({"filter": "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")"});
   // .style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(enabled='true',sizingMethod='scale',src=\"" + path + "\")";//ʹ���˾�Ч��
        /*var imagePath = file.value;
        porImg.attr({
            src : "file://" + imagePath
        });*/
    }
}