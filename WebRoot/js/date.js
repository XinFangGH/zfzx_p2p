
<script type="text/javascript">
     $(function () {  
         $("body").css("width", $(document).width() + "px");
         $(window).resize(function () {
             if ($(window).width() > 1001) {
                 $("body").css("width", $(window).width() + "px");
             } else {
                 $("body").css("width", $(document).width() + "px");
             }
         });

     });
</script>