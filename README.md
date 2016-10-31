# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Project #2: Mobile Commerce App

## Overview

Project 2 will really test your new skills, bringing weeks of knowledge together to create an app that integrates many different technologies and principles. This app will allow a user to search and buy products from your digital store!

**You will be working individually for this project**. The project will be spread across **two weeks**, with separate deliverables due at the end of each week. Although the deliverables will be separate, we will be incorporating them all into a single app, expanding upon the previous week's work.

Be creative! You can do whatever you want, as long as it meets the project requirements and the [student code of conduct](../code-of-conduct.md).

Also, for this project, you will be providing mock data (i.e. you will not be connecting to the internet to download data). However, to make your lives easier, you are allowed to use the [Picasso photo library](http://square.github.io/picasso/) if you want to load images from the web.

## Requirements

Required app features:
- View a list of products
- Narrow down the list of products by entering search terms
- Click on a product and go to a screen with more detail on that item
- Add and remove products from a shopping cart
- Shopping cart must maintain its state as long as the app is open (i.e. items won't disappear if you navigate to another screen and come back to the cart later)
- "Checking out" will clear the shopping cart and give the user some sort of message indicating the "purchase" was successful (no need to actually process financial transactions!)

Implementation requirements:
- Write a custom class (or classes) to represent your products. For example:
  - A bookstore app would need a `Book` class that with fields for _title_, _author_, _ISBN_, _price_, etc.
  - A music store app would need a `Song` class with fields for _artist_, _album_, _title_, _price_, etc.
- Use a **SQLite database** to persist all the products available within your app
  - Query your database to retrieve products to display
- Display your list of products in a `RecyclerView`
- Enable search to narrow down the list of products displayed
  - Allow user to search by **at least 2 different product-related criteria**, such as name, price, availability, size, description, etc.
  - Accept input via `SearchView` or optionally a [Search Dialog](https://developer.android.com/guide/topics/search/search-dialog.html)
- Show additional product detail on a separate screen when a user clicks an item
  - Can use fragments or a separate activity
  - Must include an `ImageView`, which can be populated with an actual product image or just filled with a solid background color to use as a placeholder
- Persist the state of the shopping cart while the app is open by whatever means you prefer
- Include automated testing
  - Create **Espresso tests** for each screen of your app
  - Write **unit tests** for any non-Android classes you create (no need to unit test simple getter/setter methods)
- Choose good, descriptive names for classes/variables/methods, and write **comments** explaining the functionality of your code

Additional requirements:
- Provide **user story** documentation
  - List all features of your app
  - For each feature, list the conditions that must be true for the feature to be successfully implemented
  - Write a user story for each condition
  - Indicate whether each user story is covered by an Espresso test, a unit test, or if it requires manual testing - if the latter, briefly indicate how to perform the manual test
- Include photos of your **paper prototypes**, or optionally a link to your [POP App](https://popapp.in/) prototype
- Write a **README.md** file describing how your app works and any bugs you are aware of
  - Use the [markdown format](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet) for this file
  - Embed at least one [screenshot](https://developer.android.com/studio/debug/am-screenshot.html) of your app

**Bonus:**
- Incorporate Material Design principles into your app
- Create a review system for user feedback for each product
- Add more complexity to the user's searches (possibly allowing more than one search criteria at a time)
- Incorporate images from the internet using [Picasso](http://square.github.io/picasso/) or [Glide](https://github.com/bumptech/glide)
- Implement **Master/Detail Flow** with side-by-side list and detail panes on large (i.e. landscape tablet) screens
- Allow user to filter (i.e. by product category) in addition to search
- Persist shopping cart even when app is closed if user didn't "check out" before quitting

## Necessary Deliverables

- A final, working version of your app with a polished UI - be creative!
- Your readme.md file
- Your user stories document (or a link to a google spreadsheet if you prefer - make sure to set sharing settings correctly!)
- Images of or link to your paper prototypes
- A pull request from your fork back to the original repo, including frequent commits dating back to the **very beginning** of the project - commit early, commit often!

## Suggested Timeline

**Week 5:**
- Define your features and write conditions and user stories
- Create paper prototypes - get input from other people on your prototypes!
- Write your non-Android classes to model the products for your app and unit tests as needed
- Plan out your database - exactly what table(s) and columns you'll need
- Write your `SQLiteOpenHelper` class to create your table(s) and add your data

**Week 6:**
- List out all the Android-specific classes you'll need (activities, fragments, adapters, view holders, etc.) and sketch out notes or diagrams about how they'll all interact
- Implement the UI and navigation of your app
- Use the database to populate each screen of your app with the correct data
- Make sure each feature works properly _before_ working on other features!
- Create your espresso tests
- Set aside time to write your readme, update your user stories to describe how each is tested, take screenshots, etc. - don't lose points by missing these relatively easy requirements!

## Code of Conduct

As always, your app must adhere to General Assembly's [student code of conduct guidelines](https://github.com/ga-adi-macaron/Course-Materials/blob/master/markdown/code-of-conduct.md).
If you have questions about whether or not your work adheres to these guidelines, please speak with a member of your instructional team.

## Example Deliverable

Below you can find an example of what the instructors' final product looks like. **Be creative with your own designs!**

<p align="center">
  <img src="screenshots/example-1.png" height="300px" /> <img src="screenshots/example-2.png" height="300px" />
</p>

## Additional Resources

- [Android API Reference](http://developer.android.com/reference/packages.html)
- [Android API Guides](http://developer.android.com/guide/index.html)
- [Using SQLite in Android](http://developer.android.com/guide/topics/data/data-storage.html#db)
- [Material Design details](http://www.google.com/design/spec/material-design/introduction.html#)
- [Code and Layout Templates](https://developer.android.com/studio/projects/templates.html)

---

## Licensing
1. All content is licensed under a CC­BY­NC­SA 4.0 license.
2. All software code is licensed under GNU GPLv3. For commercial use or alternative licensing, please contact [legal@ga.co](mailto:legal@ga.co).
