<?php
namespace Admin\Controller;
use Think\Controller;
class CateController extends CommonController {
    public function lst(){
    	$cate=D('cate');
    	$cateres=$cate->order('sort')->select();
    	$this->assign('cateres',$cateres);
        $this->display();
    }
    public function add(){
    	$cate=D('cate');
    	if(IS_POST){
    		$date['catename']=I('catename');
    		if($cate->create($date)){
    		if($cate->add()){
    			$this->success('添加类别成功',U('lst'));
    		}else{
    			$this->error('添加类别失败');
    		}	
    		}else{
    			$this->error($cate->getError());
    		}
    		return;
    	}
        $this->display();
    }
    public function edit(){
    	$cate=D('cate');
    	$cater=$cate->find(I('id'));
    	$this->assign('cater',$cater);
    	if(IS_POST){
    		$date['id']=I('id');
    		$date['catename']=I('catename');
    	if($cate->create($date)){
    		if($cate->save()){
    			$this->success('修改类别成功',U('lst'));
    		}else{
    			$this->error('修改类别失败');
    		}	
    		}else{
    			$this->error($cate->getError());
    		}
    		return;
    	}
        $this->display();
    }
    public function del(){
    	$cate=D('cate');
    	if($cate->delete(I('id'))){
    	$this->success('删除类别成功',U('lst'));	
    	}else{
    		$this->error('删除类别失败');
    	}
        $this->display();
    }
    public function sort(){
        $cate=D('cate');
        foreach ($_POST as $id=>$sort){
        	$cate->where(array('id'=>$id))->setField('sort',$sort);
        }
        $this->success('排序成功',U('lst'));
    }
}