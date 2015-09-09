<?php
//include("Scribe.inc");
/*
 * Amenity is an action which is allowed in some stores, but not in others.
 * For instance: smoking, alcohol, gaming
 */
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
    
    //var $scribe;
    function Amenity($id = "", $na = "", $allow = "")
    {
        $this->id = $id;
        $this->name = $na;
        $this->allowed = $allow;
        //$this->scribe = new Scribe();
    }
    
    // Query the DB for the number of occurances an ID starts with this object's ID.
    function db_num_times_id_exists($si_cnx)
    {
        $toReturn = false;
        $stmt = "SELECT count(*) FROM SI_AMENITY_TYPE WHERE type_id LIKE '" . $this->id . "%'";
        $rs = odbc_exec($si_cnx, $stmt);        
        odbc_fetch_row($rs);
        $num = odbc_result($rs,0);
        return($num);
    }
    
    function db_insert_amenity($si_cnx)
    {        
        $stmt = "INSERT INTO SI_AMENITY_TYPE VALUES ('"
              . $this->id . "', '"
              . $this->name . "')";
        return odbc_exec($si_cnx, $stmt);
    }
    
    function db_amenity_name_exists($si_cnx)
    {
        $stmt = "SELECT count(*) FROM SI_AMENITY_TYPE WHERE name = '" . $this->name . "'";
        $rs = odbc_exec($si_cnx, $stmt);
        odbc_fetch_row($rs);
        return(odbc_result($rs,0));
    }
    
}
?>