Project for Android Development - ISMIN

[![Mines St Etienne](./logo.png)](https://www.mines-stetienne.fr/)

# Shooting locations in Paris

## 📝 Goal

In this project we want to be able to access an online database handling a record of shooting places in the city of Paris.
This is the android part of the project.

The app contains the following:
- a menu allowing the user to navigate through the app (list/map/info)
- ability to list the shooting locations that are in the database by providing minimum information
- when clicked on a location of the list, ability to show details of all available information of the concerned location
- ability to add a location to favourites (when details are displayed)
- ability to show all locations on a map
- basic information on the app

## Resources

We used the data that can be found on the following link:
https://opendata.paris.fr/explore/dataset/lieux-de-tournage-a-paris/information/?disjunctive.type_tournage&disjunctive.nom_tournage&disjunctive.nom_realisateur&disjunctive.nom_producteur&disjunctive.ardt_lieu

## What this project contains


This app contains the following files:
- ListFragment.kt
- ListShootingLocations (not used when web part of the project is used)
- LocationAdapter
- LocationViewHolder
- MainActivity.kt
- MapsFragment
- ShootingLocation

### MainActivity

This activity is the navigation root allowing a user to access different parts of the app. 

### DetailActivity

This activity implements the provider for complete information on a clicked location (in the list).
It is associated to the activity_detail.xml file that determines the layout of the frame. It contains two clickable objects: the star in the top left corner and the back arrow on the bottom.  The star represents the favourite status of the displayed location. A filled star means it is among the favourites, an empty one means the opposite. Clicking on the star will change the locations status. The back arrow allows the used to return to the the displaying of the list of locations.

### AppInfoFragment

This fragment implements a part of the app that allows to display basic and general information such as the url providing the database, explanations concerning the displayed data as well as the libraries and licences used for the project.
It is associated to the fragment_app_info.xml file that determines the layout of the frame.



