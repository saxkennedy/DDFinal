#D&D Character Generator
![Image](https://media.dnd.wizards.com/styles/second_hubpage_banner/public/images/characters/EN_Classes_Fighter_Header.jpg)

Developers:\
Joshua Kennedy, Colin Thomas, Spencer Naugler, Joshua Wright

##About This Program:

This is a program designed for veteran and new D&D player who are wanting to jump right
into a game with a level 1 fighter but appreciate being able to customize their characters
without having to go through the hundreds of pages in the books of D&D character creation.
We also provide a savable pdf that will contain your filled out character sheet for your 
newly created character!

##Iteration 1:

This Iteration may require you to change your Gradle JVM to 11 and back to 13 to
run our application.

This is the first iteration of the Level 1 Fighter D&D Character generator, it
includes features such as; traversable UI, selectable races, selectable fighting
styles, selectable subraces, randomized dice rolling, and a pdf generator in which
you can save your file to any location desired. Provided under "User Stories" is 
the criteria in which we modeled our first iteration after. These are essential
features to the program and were our top priority when building a working program.
Under the "Acceptance Test Case" is a unique test designed by us to see if the
common person can traverse through our program and complete the requested goals.
Finally under "Acceptance Test Peer Thoughts" are a handful of responses we have
collected after asking the subject to perform the Acceptance Test mentioned previously.
This is insight that we will take into consideration when modeling the future iterations.


###User Story:

#####As a 5E D&D player, I want to make a level 1 fighter
- I want the program to create random stats for me using traditional die-rolling methods.
- I want to choose a name.
- I want to choose a race from core 5E PHB.
    - I want to choose a subrace if applicable
- I want to choose my fighting style.
- I would like to have the option to leave customization blank if I am undecided on something
- I would like to go back and change something if I change my mind
#####As a Dnd player, I require a character sheet to be filled out for me
- I want to choose whether to save this character sheet or not
    - I want to choose the location to save this file

###Acceptance Test Case:
1.  Can you create a character named “Trash Boat” that is a “Hill Dwarf” with 16 dexterity 
and has the “Archery” fighting style. Can you go back and change your character's name 
to “Dr.Pepper” and change your race to “Dragonborn”
2.  With a program that is already running and a character already created, can you save
 the generated pdf to the desktop

###Acceptance Test Peer Thoughts
Acceptance Tester 1:

**Erik Arteaga**\
I was able to fully complete both portions of the acceptance test. I especially appreciated 
the PNGs not having a fixed color background. I enjoyed being able to go back and change 
my previously selected character attributes.

Acceptance Tester 2:

**Chloe Martinez**\
There was no problem in completing both scenarios I was given. My favorite part of this 
program was being able to save the character stats onto a document in a pdf form. It 
allows me to not calculate stats by myself. However, I would like to change the name 
of the document when I save it.

Acceptance Tester 3:

**Noah Hensley**\
I was able to use the program to follow the instructions successfully. Also, it’s not 
entirely clear on what the dice roll numbers are for when you pick your stats. If you 
could select only from the randomly generated values (with a chance to override them 
later), I think it would make more sense to those unfamiliar with 5th edition D&D. I 
do like that it plugs everything into a printable sheet for the user at the end.

