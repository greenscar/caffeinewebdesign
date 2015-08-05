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
}
?>