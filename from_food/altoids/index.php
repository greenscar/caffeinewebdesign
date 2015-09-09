<HTML>
<HEAD>
<TITLE>Some photos</TITLE>
</HEAD>
<BODY>
<?php
require("some_useful_functions.inc");
$dir_name = "images";
$image_array = array();
$image_array = filelist($dir_name);
view_array($image_array);
display_images($image_array, $dir_name);

echo("</BODY></HTML>");

function filelist ($currentdir, $startdir=NULL, $files=array()) {
	@chdir ($currentdir);
	// remember where we started from
	if (!$startdir) {
		$startdir = $currentdir;
	}
	$d = opendir (".");
	
	//list the files in the dir
	while ($file = readdir ($d)) {
		if ($file != ".." && $file != ".") {
			if (is_dir ($file)) {
				// If $file is a directory take a look inside
				$files = filelist (getcwd().'/'.$file, getcwd(), $files);
			} else {
				// If $ file is not a directory then add it to our output array
				$files[] = $file;
			}
		}
	}
	closedir ($d);
	@chdir ($startdir);
	return $files;
}

function display_images($img_array, $dir_name){
	for($i = 0; $i < sizeof($img_array); $i++){
		echo("<IMG SRC=\"$dir_name/" . $img_array[$i] . "\"><BR>\n");
	}
}
?>