<?php
namespace Home\Controller;
use Think\Controller;
class CommonController extends Controller {
    public function __construct(){
        parent::__construct();
        $this->nav();
        $this->link();
        $this->news();
    }
    public function nav(){
    	$cate=D('cate');
    	$cateres=$cate->order('sort desc')->select();
    	$this->assign('cateres',$cateres);
    }
    public function link(){
    	$link=D('link');
    	$linkres=$link->order('sort desc')->select();
    	$this->assign('linkres',$linkres);
    }
    public function news(){
    	$article=D('article');
    	$artres=$article->select();
    	
    	$this->assign('artres',$artres);
    }
}