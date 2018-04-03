Documentation for the access and storage of the recipes in a recipe journal => many entries for a single recipe and many images for single recipe. 
What is a entry? A description? A modification of instructions?

Data layer
You will need to abstract a recipe to a table, which is as follows:

A recipe consists of name, steps to create, and ingredients
A recipe can therefore be made of many recipes to prepare one recipe all bundled under a name
This implies that the recipe must have a many to many relationship with other recipes
Instructions are one to one to a recipe and names are unique (should probably not be)
To repre a many to many, you need a table of unique recipes, a table for ingredients (a table to join recipe against recipes that use it and how many)

Interface



Frontend
Ideally I want a main page => feed, a way to retrieve previous attempts at the recipe => journal, and a suggestions for new recipes => (does this new its own page?) a menu?
ideally I want it to take only 2-3? pages that 
The ui will include a scroll feed of images of your recipe

If I want to support a camera, I need to implement a new activity to support the camera feed, then which it must be added to a controller logic that transforms the byte
stream into a file input stored at some file name. The file name is then stored into the database under along with the recipe entry, or maybe if one to many, another table with many entries
corresponding to each recipe_id. 

If I want to support retrieving images from the internet, I would need to implement the contract required by Android's WebView layer, then embed it within my own desirable activity. However, 
I would be putting this as the cover picture, so I would have to change the list view from a simple ListView to an ImageView with an expandable view from pressing on the image.

These should all be viewable from my main page!
