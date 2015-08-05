<?php

include_once dirname(__FILE__) . '/Handler.php';
$__ErrorHandler = new ErrorHandler;
set_error_handler(array(&$__ErrorHandler, 'raiseError'));

?>