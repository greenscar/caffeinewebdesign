<?php
class User
{
    var $id;
    var $id_str;
    var $first_name;
    var $last_name;
    var $role_id;
    var $role_name;
    var $group_id;
    var $group_name;
    
    //sec_lvls => sec_lvls[app_id] = this_app_id's_sec_lvl
    var $sec_lvls;
    
    function User($id)
    {
      $this->id = $id;
      $this->apps = array();
      $this->sec_lvls = array();
    }
    
    function get_sec_lvl($addy)
    {
    }
    
    function db_load_user($db_mgr)
    {
        $q = "SELECT u.idStr, u.firstName, u.lastName, u.rics_userRoleID, ur.name, "
           . " u.rics_userGroupID, ug.name"
           . " FROM RICS_UserGroup ug, RICS_UserRole ur, RICS_User u"
           . " WHERE ug.id = u.rics_userGroupID"
           . " AND ur.id = u.rics_userRoleID"
           . " AND u.id = '" . $this->id . "'";
        $db_mgr->query($q);
        if($db_mgr->fetch_row())
        {
            $this->id_str = trim($db_mgr->get_field(1));
            $this->first_name = ucfirst(strtolower($db_mgr->get_field(2)));
            $this->last_name = ucfirst(strtolower($db_mgr->get_field(3)));
            $this->role_id = $db_mgr->get_field(4);
            $this->role_name = $db_mgr->get_field(5);
            $this->group_id = $db_mgr->get_field(6);
            $this->group_name = $db_mgr->get_field(7);
            return(true);
        }
        else
        {
            return(false);
        }
    }
    
    function db_load_sec_lvl($db_mgr, $path_info)
    {
        $first_slash = strpos($path_info, '/');
        //return $first_slash;
        $sec_slash = strpos($path_info, '/', ($first_slash + 1));
        //return($sec_slash);    
        $path_info =  substr($path_info, ($first_slash + 1), ($sec_slash - 1));
        
        $q = "SELECT"
           . " asl.name"
           . " FROM SH_AppAccess aa, CM_Item i, SH_AppSecLvl asl"
           . " WHERE i.isActive = 1"
           . " AND i.notes LIKE 'http://www.e-sharis.net/$path_info%'"
           . " AND i.id = aa.cm_itemID"
           . " AND aa.sec_lvl = asl.sec_lvl"
           . " AND aa.rics_idStr = '" . $this->id_str . "'";
        $db_mgr->query($q);
        if($db_mgr->fetch_row())
        {
            $sec_lvl_name = $db_mgr->get_field(1);
            if(!$this->key_exists_in_array($this->sec_lvls, $path_info))
            {
                $this->sec_lvls[$path_info] = trim($sec_lvl_name);
            }
            return($this->sec_lvls[$path_info]);
        }
        else
        {
            return(-100);
        }
    }
    function key_exists_in_array($array, $looking_for)
    {
        foreach($array as $key=>$value)
        {
            if(strcmp($key, $looking_for) == 0)
            {
                return(true);
            }
        }
        return(false);
    }
    
        
}
?>