# Version 1.0

> The v1 of the app will be the basic of the app. it will read, edit, delete and write the data back to the data file.

## Stage 1: Show Entertainment Modules

- read data
- show entertainment modules
- entertainment in correct modules
- shows can collapse when no data
- show no entertainment when mainlist or show episodelist is empty
- sort data based on stage name, id and, type

### Stage 1.1:

- Viewer can hide or open by clicking splitpane.

### Stage 1.2:

- Change size of app.
    - 75% width, 75% height
    - 80% width, 80% height
    - 100% width, 100% height
    - fullscreen
      > 100% and fullscreen are not the same

## Stage 2: View Entertainment

### Stage 2.1: Viewer Position

- viewer closes when there is no data or the data is same. viewer will not close when the data changes.

#### Problems:

- [Fixed] when clicking movie module, the app crashes.
- [Fixed] The close/open viewer button is broken
- [Fixed] UI Problem: The viewer when initially opened after closing has width of 0.0px instead of 300.0px
    - widths by the bug > -0.0, 136

### Stage 2.2: Correct Viewer Data

- Correct Viewer is shown
- Exception Handling V2
  > Exception handling is better. Specific Exception classes are created.

### Stage 2.3: Adding All Viewers

- Show correct Viewers: MovieViewer, ShowViewer and EpisodeViewer.
- All viewers will have all the data needed to view.

#### Problems

- [FIXED] some entertainment are out of bounds.
  > Removed to check if entertainmentId is in range of entertainmentList. This helps because a show can have many
  episodes while the list of shows is little. So if there is 3 shows and 100 episodes, the episodes will be out of range
  of entertainmentList.size() because entertainmentList.size() would be 3 for 3 shows.
    - Lego Dreamz S3

- [FIXED] Episodes are out of bounds because they are in shows
  > created a getShow() method in MainFrame to get the show and created custom view method for EpisodeViewer.

- [FIXED] Shows have same stage name because the franchise and titles are the same while season num is different
  > The fix was reworking some of the logic. for Movies and Episodes, it is the same. for shows, it takes in the stage
  name and then adds the season number so when stagenames are the same, the season number will differentiate them. the
  new stagename is in a new variable. if the new var value and old var value are different, the new var val goes to the
  old var val. generates newStageName then checks if it same as currentStageName.

#### Changes

1. the modules not have the colored border to all around. Before, the right border was transparent.
2. The shows with EntertainmentStatus Ongoing have a custom border. it is different from released border.
3. the viewer open algorithm was improved so was checking for having sameStageName for hasSameStageName.
4. CLI is converted from a separate thread to in app.
5. The SearchField in OldUI.Main.MainFrame is using RichTextFX library for custom font and colors. It replaced the
   JavaFX TextField.
6. More exception handling in app.
7. MovieViewer changed format
8. Viewer will be open if the data is not the same but will close if the data is same.

#### Additions

1. Logging is done with the help of Log4J logging library
2. The RichTextFX library is used for the cli input and search input.
3. Create ShowViewer and EpisodeViewer
4. Created new packages and folders for NewUI
5. Created new classes, interfaces, etc. for NewUI
6. Creates database folders in appdata/local/...
7. [Future] Creates new settings folder to be used. (replaces in app settings file)
8. Files are separated from each other (NewUI and OldUI).
9. [Future] AppUI can change from OldUI to NewUI using cli.

## Stage 3: EntertainmentEditor

### Create Entertainment

### Edit Entertainment

## Stage 5: Write Data

- writes only movies

## Stage 6: Store Settings

- Stores filepath
- Stores data sorting type

# Version 2.0

> This version focus is migrating the data from a .txt to a database. The database used will be SQLite for its
> serverless and embedding into the project.

1. DataBase Name: entertainment_tracker_database
    1. Table 1: entertainment
    2. Table 2: show > bool isAnime = true/false
    3. Table 3: movie
    4. Table 4: episode
    5. Table 5: entertainment_tags
    6. Table 6: movie_companies

# Version 3.0

> V2 will be focused on improving CLI for the app. it will be running on the same or different project. This will be
> used to get, change, delete or create data.

- [] Data will be put in a thread save ArrayList(Class that is similar to ArrayList but starts with "B"?)
- [Maybe] CommandLine Interface and JavaFX App Seperation

## Stage 0: Store data in a local DataBase/Thread Safe List

## Stage 1: Combine CLI and App

## Stage 2: Improve CLI Commands

# Version 4.0

> V4 will be a milestone for the app as this will try to make the app more presentable using css. this version will
> focus on creating dark/light themes for tha app. this css will be able to be changed by the user for custom themes.
