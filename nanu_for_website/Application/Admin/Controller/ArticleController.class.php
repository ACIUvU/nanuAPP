<?php
namespace Admin\Controller;
use Think\Controller;
class ArticleController extends CommonController {
public function lst(){
    	$article=D('ArticleView');
    	$count=$article->count();
    	$Page=new \Think\Page($count,3);
    	$show=$Page->show();
    	$list=$article->order('id desc')->limit($Page->firstRow.','.$Page->listRows)->select();
    	$this->assign('list',$list);
    	$this->assign('page',$show);
        $this->display();
    }
    public function add(){
    	$article=D('article');
    	
    	if(IS_POST){
    		$date['title']=I('title');
    		$date['content']=I('content');
    		$date['desc']=I('desc');
    		$date['cateid']=I('cateid');
    		$date['time']=time();
    		if($_FILES['pic']['tmp_name']!=''){
    			$upload=new \Think\Upload();
    			$upload->maxSize=3145728;
    			$upload->exts=array('jpg','gif','png','jpeg');
    			$upload->rootPath= './';
    			$upload->savePath= './Public/Uploads/';
    			$info =  $upload->uploadOne($_FILES['pic']);
    			if(!$info){
    				$this->error($upload->getError());
    		      }else{
    			$date['pic']= $info['savepath'].$info['savename'];
    		} 
    		}else{
    			
    		}
    		
    		if($article->create($date)){
    		if($article->add()){
    			$this->success('添加文章成功',U('lst'));
    		}else{
    			$this->error('添加文章失败');
    		}	
    		}else{
    			$this->error($article->getError());
    		}
    		return;
    	}
    	$cateres=D('cate')->select();
    	$this->assign('cateres',$cateres);
        $this->display();
    }
    public function edit(){
    	$article=D('article');
    	
    	if(IS_POST){
    		$date['title']=I('title');
    		$date['content']=I('content');
    		$date['desc']=I('desc');
    		$date['cateid']=I('cateid');
    		if($_FILES['pic']['tmp_name']!=''){
    			$upload=new \Think\Upload();
    			$upload->maxSize=3145728;
    			$upload->exts=array('jpg','gif','png','jpeg');
    			$upload->rootPath= './';
    			$upload->savePath= './Public/Uploads/';
    			$info =  $upload->uploadOne($_FILES['pic']);
    			if(!$info){
    				$this->error($upload->getError());
    		      }else{
    			$date['pic']= $info['savepath'].$info['savename'];
    		} 
    		}else{
    			
    		}
    		
    		if($article->create($date)){
    		if($article->add()){
    			$this->success('修改文章成功',U('lst'));
    		}else{
    			$this->error('修改文章失败');
    		}	
    		}else{
    			$this->error($article->getError());
    		}
    		return;
    	}
    	$articler=$article->find(I('id'));
    	$this->assign('articler',$articler);
    	$cateres=D('Date')->select();
    	$this->assign('cateres',$cateres);
        $this->display();
    }
    public function del(){
    	$article=D('article');
    	if($article->delete(I('id'))){
    	$this->success('删除文章成功',U('lst'));	
    	}else{
    		$this->error('删除文章失败');
    	}
        $this->display();
    }
    public function sort(){
        $article=D('article');
        foreach ($_POST as $id=>$sort){
        	$article->where(array('id'=>$id))->setField('sort',$sort);
        }
        $this->success('排序成功',U('lst'));
    }
}