<?php
namespace Admin\Model;
use Think\Model;
class CateModel extends Model {
    protected $_validate=array(
    array('catename','require','添加类别不能为空',1,'regex',3),
    array('catename','','添加不能重复',1,'unique',3),
    );
}





