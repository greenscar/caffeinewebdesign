<?php
class Address
{
    // Description is something like "Berry Hill Shopping Center".
    // It is simply a description of the location
    var $description;
    // The street address
    var $street;
    // The city of the location
    var $city;
    // The state of the location
    var $state;
    // The zip code of the location
    var $zip;
    
    function Address($str, $c, $sta, $z, $d = "")
    {
        $this->description = $d;
        $this->street = $str;
        $this->city = $c;
        $this->state = $sta;
        $this->zip = $z;
    }
    
    function set_description($d)
    {
        $this->description = $d;
    }
    function set_street($s)
    {
        $this->street = $s;
    }
    function set_city($c)
    {
        $this->city = $c;
    }
    function set_state($s)
    {
        $this->state = $s;
    }
    function set_zip($z)
    {
        $this->zip = $z;
    }
    
    function get_description($d)
    {
        return $this->description;
    }
    function get_street($s)
    {
        return $this->street;
    }
    function get_city($c)
    {
        return $this->city;
    }
    function get_state($s)
    {
        return $this->state;
    }
    function get_zip($z)
    {
        return $this->zip;
    }
    
}
?>