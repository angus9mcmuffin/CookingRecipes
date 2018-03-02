A basic foodApp

Goal: Should store recipes(CookBook), attempts at recipes (Diary), and recipe suggestions (Recommendations)

1. Recipe - { Ingredients: FoodA, FoodB etc; Instructions: (state machine?)}

2. Attempts - {Recipe; Date + time; Story: Journaling } 

3. Suggestions - { Recipe Database; Recipes matched on ingredients; Recipe Success scorer }

Recipe
	Name
		Title: String
	Ingredient<>
		Recipe(no instructions)
	Instruction<>
		Description: String