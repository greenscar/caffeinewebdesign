
// write me if you have questions: web.master@male.net

// constants
var choiceWidth = 700/5;
var menuX 	= (screen.width - 700) / 2; // x-coordinate of top left corner of dropdown menu 
var subMenuX = menuX; //x-coordinate of popup menu
var subMenuY = screen.height / 8; //y-coordinate of popup menu
var backColor   = '#FFFF00'; // the background color of dropdown menu, set empty '' for transparent
var borderColor = 'black'; // the color of dropdown menu border
var borderSize  = '1'; // the width of dropdown menu border
var itemHeight  = 20;
var xOverlap    = 5;
var yOverlap    = 10;

menuContent     = new Array ();

menuContent [0] = new Array ( 
-1, // the id of parent menu, -1 if this is a first level menu
-1, // the number of line in parent menu, -1 if this is a first level menu
170,// the width of current menu list 
menuX + 1/5 * choiceWidth, // x coordinate (absolute) of left corner of this menu list, -1 if the coordinate is defined from parent y-coordinate
subMenuY, // y coordinate (absolute) of left corner of this menu list, -1 if the coordinate is defined from parent y-coordinate
new Array (
'Create', './create_test_screen.php',
'Modify', './modify_test.php',
'Delete', './delete_test_screen.php',
'Display with Solutions', './display_test_with_solutions.php'
));

menuContent [1] = new Array ( 
-1, 
-1,
160,
menuX + 7/5 * choiceWidth,  // x coordinate (absolute) of left corner of this menu list, -1 if the coordinate is defined from parent x-coordinate
subMenuY, // y coordinate (absolute) of left corner of this menu list, -1 if the coordinate is defined from parent y-coordinate
new Array (
'Add New', './add_user_screen.php',
'Modify Existing', './menu_admin.php?scr=mus', // modify_user_screen.php',
'Drop Existing', './drop_user_screen.php',
'List All', './list_all_user_screen.php',
'Re-Activate Deleted', './reactivate_user_screen.php'
));
 
menuContent [2] = new Array (
-1, 
-1,
160,
menuX + 19/5 * choiceWidth,  // x coordinate (absolute) of left corner of this menu list, -1 if the coordinate is defined from parent x-coordinate
subMenuY, // y coordinate (absolute) of left corner of this menu list, -1 if the coordinate is defined from parent y-coordinate
new Array (
'Display Taken Test', './display_test_taken.php'
));

//menuContent [3] = new Array ( 
//0, 
//1,
//120,
//-1, // x coordinate (absolute) of left corner of this menu list, -1 if the coordinate is defined from parent x-coordinate
//-1, // y coordinate (absolute) of left corner of this menu list, -1 if the coordinate is defined from parent y-coordinate
//new Array (
//'Link 111', 'http://www.1.net',
//'Link 112', 'http://www.2.net', 
//'Link 113', 'http://www.3.net'
//));
