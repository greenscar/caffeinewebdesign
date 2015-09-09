   
function subCategory(id, name)
{
    this.id = id
    this.name = name
}


function populateCategory(catIDs,catNames, origCatID)
{
    //alert("Populating category");
    document.modOne.categoryNew.options.length=0;
    //document.modOne.categoryNew.options[0] = new Option("SELECT A CATEGORY", "0");
    for(x=0;x<catNames.length;x++)
    {   
        //var y = x+1;
        var y = x;
        //alert("name = " + catNames[x]);
        if(catIDs[x] == origCatID)
        {
            document.modOne.categoryNew.options[y] = new Option(catNames[x], catIDs[x], true, true);
        }
        else
        {
            document.modOne.categoryNew.options[y] = new Option(catNames[x], catIDs[x], false, false);
        }
    }
}
function populateSubCategory(origSubCatID)
{
    if (!optionTest) return;
    var categoryBox = document.modOne.categoryNew;
    var number = categoryBox.options[categoryBox.selectedIndex].value;
    if (!number) return;
    var subCatTemp = subCats[number - 1];
    var subCatIDs = subCatTemp.id;
    var subCatNames = subCatTemp.name;
    var subCategoryBox = document.modOne.subCategoryNew;
    subCategoryBox.options.length = 0;
    for(i=0;i<subCatIDs.length;i++)
    {
        if(subCatIDs[i] == origSubCatID)
        {
            subCategoryBox.options[i] = new Option(subCatNames[i], subCatIDs[i], true, true);
        }
        else
        {
            subCategoryBox.options[i] = new Option(subCatNames[i], subCatIDs[i], false, false);
        }
    }
}