<?php
namespace Admin\Controller;
use Think\Controller;
class LinkController extends CommonController {
    public function lst(){
    	$link=D('link');
    	$count=$link->count();
    	$Page=new \Think\Page($count,3);
    	$show=$Page->show();
    	$list=$link->order('sort desc')->limit($Page->firstRow.','.$Page->listRows)->select();
    	$this->assign('list',$list);
    	$this->assign('page',$show);
        $this->display();
    }
    public function add(){
    	$link=D('link');
    	if(IS_POST){
    		$date['title']=I('title');
    		$date['url']=I('url');
    		$date['desc']=I('desc');
    		if($link->create($date)){
    		if($link->add()){
    			$this->success('添加链接成功',U('lst'));
    		}else{
    			$this->error('添加链接失败');
    		}	
    		}else{
    			$this->error($link->getError());
    		}
    		return;
    	}
        $this->display();
    }
    public function edit(){
    	$link=D('link');
    	
    	if(IS_POST){
    		$date['id']=I('id');
    		$date['title']=I('title');
    		$date['url']=I('url');
    		$date['desc']=I('desc');
    	if($link->create($date)){
    		if($link->save()){
    			$this->success('修改链接别成功',U('lst'));
    		}else{
    			$this->error('修改链接失败');
    		}	
    		}else{
    			$this->error($link->getError());
    		}
    		return;
    	}
    	$linkr=$link->find(I('id'));
    	$this->assign('linkr',$linkr);
        $this->display();
    }
    public function del(){
    	$link=D('link');
    	if($link->delete(I('id'))){
    	$this->success('删除链接成功',U('lst'));	
    	}else{
    		$this->error('删除链接失败');
    	}
        $this->display();
    }
    public function sort(){
        $link=D('link');
        foreach ($_POST as $id=>$sort){
        	$link->where(array('id'=>$id))->setField('sort',$sort);
        }
        $this->success('排序成功',U('lst'));
    }
}