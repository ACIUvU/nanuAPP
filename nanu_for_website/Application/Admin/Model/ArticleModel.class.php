<?php
namespace Admin\Model;
use Think\Model;
class ArticleModel extends Model {
    protected $_validate=array(
    array('title','require','标题不能为空',1,'regex',3),
    array('cateid','require','类别不能为空',1,'regex',3),
    array('content','require','文章不能为空',1,'regex',3)
    );
}





