<?php
namespace Admin\Model;
use Think\Model;
class CateModel extends Model {
    protected $_validate=array(
    array('title','require','链接名称不能为空',1,'regex',3),
    array('url','require','链接地址不能为空',1,'regex',3),
    array('title','','链接名称不能重复',1,'unique',3),
    );
}





