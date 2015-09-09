<?php
$state_array = array("OR"=>"Oregon", "WA"=>"Washington", "ID"=>"Idaho", "WY"=>"Wyoming", "CA"=>"California", "CO"=>"Colorado");

function get_first_n_cons($str, $n)
{
    $temp = preg_replace("/[AEIOU]+/", "", $str);
    $temp = substr($temp, 0, $n);
    return $temp;
}

/*
* These 2 functions are used to display the contents of any array.
* This can be single or multidimensional.
*/
function view_array($arr){
	echo "\t<TABLE CELLPADDING=\"0\" CELLSPACING=\"0\" BORDER=\"1\">\n";
	foreach ($arr as $key1 => $elem1) 
	{
		echo("\t\t<TR>\n");
		echo("\t\t\t<TD>$key1&nbsp;</TD>\n");
		if (is_array($elem1))  
			extArray($elem1);
		else  
			echo("\t\t\t<TD>". stripslashes($elem1)."&nbsp;</TD>\n"); 
		echo("\t\t</TR>\n");
	}
	echo("\t</TABLE>\n");
}
	
//This function is called from view_array to take care of multi-dimensional arrays
function extArray($arr){
	echo("\t\t\t<TD>\n");
	echo "\t<TABLE CELLPADDING=\"0\" CELLSPACING=\"0\" BORDER=\"1\">\n";
	foreach ($arr as $key => $elem) 
	{
		echo("\t\t<TR>\n");
		echo("\t\t\t<TD>$key&nbsp;</TD>\n");
		if (is_array($elem)) 
			extArray($elem);
		else 
			echo("\t\t\t<TD>". stripslashes($elem)."&nbsp;</TD>\n");
		echo("\t\t</TR>\n");
	}
	echo("\t</TABLE>\n");
	echo("</TD>\n");
}


function get_store_menu($admin, $title, $target, $order_by="loc_num")
{
    $admin->db_load_site_list($order_by);
    $str = "<!--\n\tDISPLAY THE LIST OF STORES\n-->\n"
	 . "<div class=\"center\">\n"
	 . "\t<div class=\"grey_box_90\">\n"
	 . "\t<div class=\"spacer\"></div>\n"
	 . "\t<span class=\"white_center\">\n"
	 . "\t\t<h2>$title</h2>\n"
	 . "\t</span>\n"
	 . "\t<table width=\"95%\" border=\"0\">\n"
	 . "\t\t<tr>\n"
	 . "\t\t\t<td width=\"10%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
	 . "\t\t\t\t<span class=\"white_center\">\n"
	 . "\t\t\t\t<a href=\"$target?ob=loc_num\" class=\"sl_header\">Number</a>\n"
	 . "\t\t\t\t</span>\n"
	 . "\t\t\t</td>\n"
	 . "\t\t\t<td width=\"20%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
	 . "\t\t\t\t<span class=\"white_center\">\n"
	 . "\t\t\t\t<a href=\"$target?ob=loc_name_1\" class=\"sl_header\">Name</a>\n"
	 . "\t\t\t\t</span>\n"
	 . "\t\t\t</td>\n"
	 . "\t\t\t<td width=\"20%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
	 . "\t\t\t\t<span class=\"white_center\">\n"
	 . "\t\t\t\t<a href=\"$target?ob=address\" class=\"sl_header\">Street</a>\n"
	 . "\t\t\t\t</span>\n"
	 . "\t\t\t</td>\n"
	 . "\t\t\t<td width=\"20%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
	 . "\t\t\t\t<span class=\"white_center\">\n"
	 . "\t\t\t\t<a href=\"$target?ob=city\" class=\"sl_header\">City</a>\n"
	 . "\t\t\t\t</span>\n"
	 . "\t\t\t</td>\n"
	 . "\t\t\t<td width=\"5%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
	 . "\t\t\t\t<span class=\"white_center\">\n"
	 . "\t\t\t\t<a href=\"$target?ob=state\" class=\"sl_header\">State</a>\n"
	 . "\t\t\t\t</span>\n"
	 . "\t\t\t</td>\n"
	 . "\t\t\t<td width=\"5%\" class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n"
	 . "\t\t\t\t<span class=\"white_center\">\n"
	 . "\t\t\t\t<a href=\"$target?ob=zip\" class=\"sl_header\">Zip</a>\n"
	 . "\t\t\t\t</span>\n"
	 . "\t\t\t</td>\n"
	 
	 . "\t\t</tr>\n";
    for($i=0; $i < $admin->site_list_size(); $i++)
    {
        $site = $admin->get_a_site_from_list($i);
        $str .= "\t<a href=\"$target?sn=" . $site->site_num . "\">\n";
        $str .= "\t\t<tr>\n";
        if($i % 2 == 1)
            $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
        else
            $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
        
        $str .= "\t\t\t\t<span class=\"white_center\">\n";
        $str .= "\t\t\t\t\t";
        $str .= $site->site_num;
        $str .= "\n";
        $str .= "\t\t\t\t</span>\n";
        $str .= "\t\t\t</td>\n";
        
        if($i % 2 == 1)
            $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
        else
            $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
        
        $str .= "\t\t\t\t<span class=\"white_center\">\n";
        $str .= "\t\t\t\t\t";
        $str .= $site->site_name;
        $str .= "\n";
        $str .= "\t\t\t\t</span>\n";
        $str .= "\t\t\t</td>\n";
        
        if($i % 2 == 1)
            $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
        else
            $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
        
        $str .= "\t\t\t\t<span class=\"white_center\">\n";
        $str .= "\t\t\t\t\t";
        $str .= $site->address->street;
        $str .= "\n";
        $str .= "\t\t\t\t</span>\n";
        $str .= "\t\t\t</td>\n";
	
        if($i % 2 == 1)
            $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
        else
            $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
        
        $str .= "\t\t\t\t<span class=\"white_center\">\n";
        $str .= "\t\t\t\t\t";
        $str .= $site->address->city;
        $str .= "\n";
        $str .= "\t\t\t\t</span>\n";
        $str .= "\t\t\t</td>\n";
	
        if($i % 2 == 1)
            $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
        else
            $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
        
        $str .= "\t\t\t\t<span class=\"white_center\">\n";
        $str .= "\t\t\t\t\t";
        $str .= $site->address->state;
        $str .= "\n";
        $str .= "\t\t\t\t</span>\n";
        $str .= "\t\t\t</td>\n";
	
        if($i % 2 == 1)
            $str .= "\t\t\t<td class=\"white_on_black\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='white_on_black'\">\n";
        else
            $str .= "\t\t\t<td class=\"black_on_white\" onmouseover=\"this.className='over'\" onmouseout=\"this.className='black_on_white'\">\n";
        
        $str .= "\t\t\t\t<span class=\"white_center\">\n";
        $str .= "\t\t\t\t\t";
        $str .= $site->address->zip;
        $str .= "\n";
        $str .= "\t\t\t\t</span>\n";
        $str .= "\t\t\t</td>\n";
        $str .= "\t\t</tr>\n";
        $str .= "\t</a>\n";
    }
    $str .= "</table>\n";
    $str .= "</div>\n</div>\n";
    $str .= "</div>\n";
    return $str;
}
?>