<?php if (!defined('THINK_PATH')) exit();?><!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="http://localhost:8080/boke/Application/Admin/Public/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="http://localhost:8080/boke/Application/Admin/Public/css/main.css"/>
    <script type="text/javascript" src="http://localhost:8080/boke/Application/Admin/Public/js/libs/modernizr.min.js"></script>
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.html">首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">用户:<?php echo $_SESSION['username']; ?></a></li>
                <li><a href="/boke/index.php/Admin/Admin/add">注册</a></li>
                <li><a href="/boke/index.php/Admin/Admin/edit/id/"><?php echo $_SESSION['id']; ?>修改密码</a></li>
                <li><a href="/boke/index.php/Admin/Admin/logout">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
     <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="/boke/index.php/Admin/Article/lst"><i class="icon-font">&#xe008;</i>文章管理</a></li>
                        <li><a href="/boke/index.php/Admin/Cate/lst"><i class="icon-font">&#xe006;</i>分类管理</a></li>
                
                        <li><a href="/boke/index.php/Admin/Link/lst"><i class="icon-font">&#xe052;</i>友情链接</a></li>
                        <li><a href="/boke/index.php/Admin/Admin/lst"><i class="icon-font">&#xe033;</i>关注列表</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">作品管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="/jscss/admin/design/index" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">选择分类:</th>
                            <td>
                                <select name="search-sort" id="">
                                    <option value="">全部</option>
                                    <option value="19">精品界面</option><option value="20">推荐界面</option>
                                </select>
                            </td>
                            <th width="70">关键字:</th>
                            <td><input class="common-text" placeholder="关键字" name="keywords" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
         <div class="result-wrap">
            <form name="myform" id="myform"  action="/boke/index.php/Admin/Article/sort" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="/boke/index.php/Admin/Article/add"><i class="icon-font"></i>新增文章</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                          
                            <th>ID</th>
                            <th>文章标题</th>
                            <th>缩略图</th>
                            <th>类别</th>
                            <th>操作</th>
                        </tr>
                        <?php if(is_array($list)): $i = 0; $__LIST__ = $list;if( count($__LIST__)==0 ) : echo "" ;else: foreach($__LIST__ as $key=>$vo): $mod = ($i % 2 );++$i;?><tr>
               
                
                           <td class="tc"><?php echo ($vo["id"]); ?></td>
                            
                            <td title="{vo.title}"><a target="_blank" href="#" title="{vo.title}"><?php echo ($vo["title"]); ?></a> …
                            </td>
                            <td>
                           
                            <img src="/boke<?php echo ($vo["pic"]); ?>" height="50">
                      
                            </td>
                            <td><?php echo ($vo["catename"]); ?></td>
                            <td>
                                <a class="link-update" href="/boke/index.php/Admin/Article/edit/id/<?php echo ($vo["id"]); ?>">修改</a>
                                <a class="link-del"  onclick="return confirm('确认删除？');"  href="/boke/index.php/Admin/Article/del/id/<?php echo ($vo["id"]); ?>">删除</a>
                            </td>
                        </tr><?php endforeach; endif; else: echo "" ;endif; ?>
                    </table>
                    <div class="list-page"><?php echo ($page); ?></div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>