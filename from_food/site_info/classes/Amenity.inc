<?php
//include("Secretary.inc");
/*
 * Amenity is an action which is allowed in some stores, but not in others.
 * For instance: smoking, alcohol, gaming
 */
require_once("../../../php_objects/DB_Mgr.inc");
class Amenity
{
    // DB ID
    var $id;
    // Amenity name
    var $name;
    // Is the amentity allowed?
    var $allowed;
    // Description of amentity
    var $description;
    
    //var $log;
    function Amenity($id = "", $na = "", $allow = "")
    {
        $this->id = $id;
        $this->name = $na;
        $this->allowed = $allow;
        //$this->log = new Secretary();
    }
    
    // Query the DB for the number of occurances an ID starts with this object's ID.
    function db_num_times_id_exists($dbmgr_web)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_AMENITY_TYPE WHERE type_id LIKE '" . $this->id . "%'";
        $dbmgr_web->query($stmt);
        $rs = $dbmgr_web->fetch_row();
        return($rs[0]);
    }
    
    function db_insert_amenity($dbmgr_web)
    {        
        $stmt = "INSERT INTO SI_AMENITY_TYPE VALUES ('"
              . $this->id . "', '"
              . $this->name . "')";
        return odbc_exec($dbmgr_web, $stmt);
    }
    
    function db_amenity_name_exists($dbmgr_web)
    {
        $stmt = "SELECT count(*) FROM SI_AMENITY_TYPE WHERE name = '" . $this->name . "'";
        $dbmgr_web->query($stmt);
        $rs = $dbmgr_web->fetch_row();
        return($rs[0]);
    }
    
}
?>