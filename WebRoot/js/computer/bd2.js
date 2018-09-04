


////// JavaScript Document
//////单选
//window.onload = function () {
//    labels = document.getElementById('rd').getElementsByTagName('label');
//    radios = document.getElementById('rd').getElementsByTagName('input');
//    for (i = 0, j = labels.length; i < j; i++) {
//        labels[i].onclick = function () {
//            if (this.className == '') {
//                for (k = 0, l = labels.length; k < l; k++) {
//                    labels[k].className = '';
//                    radios[k].checked = false;
//                }
//                this.className = 'checked';

//                alert(i);
//                alert(this.uid);
//                return;
//                try {
//                    document.getElementById(this.name).checked = true;
//                } catch (e) { }
//            }
//        }
//    }
//}


$(document).ready(function () {
    $('#repay1').click(function () {
        $(this).addClass('checked');

        $('#repay3').removeClass('checked')
        $('#repay4').removeClass('checked')
        $('#repay5').removeClass('checked')
        $('#repay6').removeClass('checked')

        $('#lblRepay1').attr('checked', true);

        $('#lblRepay3').removeAttr('checked');
        $('#lblRepay4').removeAttr('checked');
        $('#lblRepay5').removeAttr('checked');
        $('#lblRepay6').removeAttr('checked');
    });

    $('#repay3').click(function () {
        $(this).addClass('checked');

        $('#repay1').removeClass('checked')
        $('#repay4').removeClass('checked')
        $('#repay5').removeClass('checked')
        $('#repay6').removeClass('checked')

        $('#lblRepay3').attr('checked', true);

        $('#lblRepay1').removeAttr('checked');
        $('#lblRepay4').removeAttr('checked');
        $('#lblRepay5').removeAttr('checked');
        $('#lblRepay6').removeAttr('checked');
    });

    $('#repay4').click(function () {
        $(this).addClass('checked');

        $('#repay1').removeClass('checked')
        $('#repay3').removeClass('checked')
        $('#repay5').removeClass('checked')
        $('#repay6').removeClass('checked')

        $('#lblRepay4').attr('checked', true);

        $('#lblRepay1').removeAttr('checked');
        $('#lblRepay3').removeAttr('checked');
        $('#lblRepay5').removeAttr('checked');
        $('#lblRepay6').removeAttr('checked');
    });
    //等额本息
    $('#repay5').click(function () {
        $(this).addClass('checked');

        $('#repay1').removeClass('checked')
        $('#repay3').removeClass('checked')
        $('#repay4').removeClass('checked')
        $('#repay6').removeClass('checked')

        $('#lblRepay5').attr('checked', true);

        $('#lblRepay1').removeAttr('checked');
        $('#lblRepay3').removeAttr('checked');
        $('#lblRepay4').removeAttr('checked');
        $('#lblRepay6').removeAttr('checked');
    });
    //等额本息
    $('#repay6').click(function () {
        $(this).addClass('checked');

        $('#repay1').removeClass('checked')
        $('#repay3').removeClass('checked')
        $('#repay4').removeClass('checked')
        $('#repay5').removeClass('checked')

        $('#lblRepay6').attr('checked', true);

        $('#lblRepay1').removeAttr('checked');
        $('#lblRepay3').removeAttr('checked');
        $('#lblRepay4').removeAttr('checked');
        $('#lblRepay5').removeAttr('checked');
    });
});
