<?php
namespace Home\Controller;
use Think\Controller;
class CateController extends CommonController {
    public function index(){
    	$article=D('article');
    	$count=$article->where(array('cateid'=>I('id')))->count();
    	$Page=new \Think\Page($count,10);
    	$show=$Page->show();
    	$list=$article->where(array('cateid'=>I('id')))->limit($Page->firstRow.','.$page->listRows)->select();
    	$this->assign('list',$list);
    	$this->assign('page',$show);
        $this->display();
    }
}