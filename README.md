Project for Android Development - ISMIN

[![Mines St Etienne](./logo.png)](https://www.mines-stetienne.fr/)

# Shooting locations in Paris

## 📝 Goal

In this project we want to be able to access an online database handling a record of a list of movies' shooting places in the city of Paris.
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
'https://opendata.paris.fr/explore/dataset/lieux-de-tournage-a-paris/information/?disjunctive.type_tournage&disjunctive.nom_tournage&disjunctive.nom_realisateur&disjunctive.nom_producteur&disjunctive.ardt_lieu'

The remote database can be consulted at the following link:
'http://app-a420d8b0-91fa-4e22-aa10-d7b502ac5499.cleverapps.io/shootingLocations'

## What this project contains

### ShootingLocation

This file specifies the structure of the data that is displayed. Every location is specified by its unique id.

### MainActivity

This activity is the navigation root allowing a user to access different parts of the app. It contains a menu with four options allowing to display either a list of locations (default display when the app is started), or a map with pins each representing a location, or general information on the app. The last option allows to refresh the database.
The activity is associated to the 'activity_main.xml' file that determines the layout of the frame. It only contains a FrameLayout object in which the desired fragment is displayed.

### ListFragment

This fragment can be displayed in the main activity frame. It allows to show the list of available locations in the database with minimal information.
The fragment is associated to the 'fragment_list.xml' file that determines the layout of the frame. It only contains a RecyclerView allowing the listing of several rows (each row representing a location) to be displayed.

Each row's layout is determined by the 'row_location.xml' file. The row represents a single location and provides minimum information on it.
'LocationViewHolder' and 'LocationAdapter' fill these rows. LocationsViewHolder allows the items of the 'row_location.xml' file to be accessed. The according actions are handled in the 'LocationAdapter' file.
Each row is clickable. Clicking on a row will display the details of the location (see DetailActivity).

For each type of shooting (depends on the movie) a different picture is displayed as icon of the location. The same icon is displayed when the details of the abject are displayed.

### MapsFragment

This fragment can be displayed in the main activity frame. It allows to show all the locations of the database on a map. The marker on each pin corresponds to the title of the movie that was shot in the pin's location.

### AppInfoFragment

This fragment can be displayed in the main activity frame. It allows to display basic and general information such as the url providing the database, explanations concerning the displayed data as well as the libraries and licences used for the project.
The fragment is associated to the 'fragment_app_info.xml' file that determines the layout of the frame.

### DetailActivity

This activity implements the provider for complete information on a clicked location (in the list).
The activity is associated to the 'activity_detail.xml' file that determines the layout of the frame. It contains two clickable objects: the star in the top left corner and the back arrow on the bottom.  The star represents the favourite status of the displayed location. A filled star means it is among the favourites, an empty one means the opposite. Clicking on the star will change the locations status. The back arrow allows the used to return to the the displaying of the list of locations.


## Note to teacher

The web project works on its own. The same goes for the local version of the android part of the project. However the link between the two does not. To switch between the 'remote' (using the web part of the project) and 'local' (not using the web part of the project and using local data) versions simply checkout the two corresponding branches of the project.

Remote version: For a reason that we could not understand, the fetching of the remote database does not work.

Local version: all functionalities work but are of course not saved when the app is restarted.