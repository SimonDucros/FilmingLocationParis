Part of Android Development - ISMIN 

Course followed by students of Mines St Etienne, ISMIN - M2 Computer Science.

[![Mines St Etienne](./logo.png)](https://www.mines-stetienne.fr/)

# TP2: Android Basics

## 📝 Goal

The goal is to discover `Activity` and Android Layout System by implementing a screen to add a book.

To do so:
- Copy `Book` and `Bookshelf` of the previous TP 
- Create a `CreateBookActivity` with in its UI the necessary fields to create a book. For instance:
  - A text input for the author
  - A text input for the title
  - A text input for the date
  - A `Save` button 
- Add a `Create book` button in the `MainActivity`
- Display `CreateBookActivity` when user clicks on the `Create book` button
- Finally, when user clicks on the `Save` button:
  - Create an instance of `Book` with UI inputs
  - Finish `CreateBookActivity` and send back the `Book` to the `MainActivity`
  - Display the created book in the `MainActivity`

## 🚀 Getting Started

 - Start Android Studio
 - Select `Open an existing Android Studio project` and pick this directory

That's it! You can code!

## 🛰 Extra:

- Validate the `date` input to be sure it is well formatted 
- Handle the `date` attribute as a Date and not a string
- Add some animations when the app is switching between the 2 activities


## 🔑 Solution

An implementation of the TP is available on `solution` branch. To switch to the solution just do:

```
# Commit or revert your local changes
# git add . && git commit -m "YOUR_MSG" 

# Update your repository
git pull

# Switch to `solution` branch
git checkout solution
```