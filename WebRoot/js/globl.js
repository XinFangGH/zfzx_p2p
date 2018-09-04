/**
设定全局的js基本参数
by: xieyz**/
var projName="wenandai";
var pa=window.location.href.split("/");
var basepath;
var themepath;
if(pa.length>4){
	 basepath=pa[0]+"//"+pa[2]+"/"+pa[3]+"/";     //项目部署路径       例：http://www.hurongtime.com/
 	 themepath=pa[0]+"//"+pa[2]+"/"+pa[3]+"/theme/proj_wenandai/";   //主题存放路径       例: http://www.hurongtime.com/theme/proj_xxxxxx/
}else{
	basepath=pa[0]+"//"+pa[2]+"/";     //项目部署路径       例：http://www.hurongtime.com/hurong_p2p_v3.5.1/
	themepath=pa[0]+"//"+pa[2]+"/theme/proj_"+ projName +"/";   //主题存放路径       例: http://www.hurongtime.com/hurong_p2p_v3.5.1/theme/proj_xxxxxx/
}
basepath=pa[0]+"//"+pa[2]+"/";     //项目部署路径       例：http://www.hurongtime.com/hurong_p2p_v3.5.1/
themepath=pa[0]+"//"+pa[2]+"/theme/proj_"+ projName +"/";   //主题存放路径       例: http://www.hurongtime.com/hurong_p2p_v3.5.1/theme/proj_xxxxxx/