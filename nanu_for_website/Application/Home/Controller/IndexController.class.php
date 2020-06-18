<?php
namespace Home\Controller;
use Think\Controller;
class IndexController extends CommonController {
    public function index(){
        $article=D('article');
    	$count=$article->count();
    	$Page=new \Think\Page($count,3);
    	$show=$Page->show();
    	$list=$article->limit($Page->firstRow.','.$page->listRows)->select();
    	$this->assign('list',$list);
    	$this->assign('page',$show);
        $this->display();
    }
}