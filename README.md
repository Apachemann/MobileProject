# MobileProject

CS 496 Semester Project - Mobile Project (Snap 'N Study)

By Shawn Korns, Nermin Klapic, and Michael Cook

------

**Tentative Project Schedule**

**---Phase 1: Learning and Layout---**

Goals: Build the groundwork of the application. Design and stylize the user interface with minimal functionality while learning about Android development. If we have time, develop one of the smaller features of the project (vocabulary dictionary?) that works without using the camera. 

Week 1: Monday, January 24 - Sunday, January 30

Week 2: Monday, January 31 - Sunday, February 6

Completion - Week 3: Monday, February 7 - Sunday, February 13


**---Phase 2: Camera Functionality---**

Goals: Allow the user to take pictures, parse the legible data, and use the data to create study questions. Besides AI question generation, most other functions should be complete.

Week 4: Monday, February 14 - Sunday, February 20

Week 5: Monday, February 21 - Sunday, February 27

Completion - Week 6: Monday, February 28 - Sunday, March 6


**---Phase 3: AI Development---**

Goals: Create AI that generates relevant questions based on data gathered from the user's picture. Likely the most difficult portion of this whole project.

Week 7: Monday, March 7 - Sunday, March 13

Spring Break! - Week 8: Monday, March 14 - Sunday, March 20

Week 9: Monday, March 21 - Sunday, March 27

Completion - Week 10: Monday, March 28 - Sunday, April 3


**---Phase 4: Bonus Features---**

Goals: Develop one additional feature (iOS? Desktop application?) and/or touch up and improve the application. This'll also serve as a buffer for additional time in case we need to work more on a previous phase.

Week 11: Monday, April 4 - Sunday, April 10

Week 12: Monday, April 11 - Sunday, April 17

Completion - Week 13: Monday, April 18 - Sunday, April 24

**---Other Stuff---**

Presentations - Week 14: Monday, April 25 - Friday, April 29

Finals - Week 15: Monday, May 2 - Thursday, May 5


------

**Learning Resources**

https://developer.android.com/courses/kotlin-android-fundamentals/overview

https://developer.android.com/training/basics/firstapp


------


**CS 496 Project Abstract**

**Title - Snap ‘N Study, A mobile app study tool for students.**

**General Description**

The Snap ‘N Study mobile application will scan user documents using an Android smartphone. From these documents the user can generate study material using the application and the artificial intelligence integrated into the application. The application will also be continuing to progress and learn from user input after launch of the application at the end of the semester.

**Delivered Functions**

Functional
1. Phone Camera Scanning and Studying: By using the phone’s camera, the user may take a picture and the application will parse any detected legible text into data. Some recommended pictures to capture include a passage from a textbook, lecture notes, or a homework sheet. The captured data will be presented and the user is given a chance to alter the data in case of errors from the parsing process before confirming the data is correct. The user is then given several choices for how they want to change the data into a form(s) of study. Potential forms of study include flashcards, fill-in-the-blank, multiple-choice, and short answer.
For example, when the user chooses the fill-in-the-blank option, the data will be split into multiple lines with a single sentence of data residing on a line. For each line, the user will choose a word that will be the blank, or answer. After completing this process for all the available data, the user will be able to study their captured data using the fill-in-the-blank study form, where a randomized assortment of the processed sentences from earlier are presented to the user. The user must choose the correct blank to fill in the sentence, either by pressing or typing the correct choice.
2. Vocabulary Dictionary: Users can target specific words in a set of data captured from a picture and enter them into a study dictionary. The study dictionary is a collection of these words with their user-defined or online dictionary defined definitions. The user may select any number of these words inside the study dictionary to become flashcards to study.
3. Generated Quizzes: The application can generate randomized quizzes from a collection of data that has already been changed into a study form. A user may study for an exam that covers multiple chapters of a given text and capture this text using their phone into the application. After processing the data into multiple different study forms, the user has the option of generating a quiz that uses these forms of study. A quiz will consist of a user defined number of questions that use different study forms. After completing all the questions in the quiz, the user is given a score.
 
4. Mobile Application for Android: The target mobile platform that our team will be developing this application for is Android. The user may download and use the application on any eligible Android devices. The team is considering and will explore options to port the application to iOS later in the development cycle. The team is also considering a desktop application that will synchronize user data between the desktop and mobile versions.

Non-functional 

1. Intuitive Interface: Users will be greeted with an easy-to-understand interface on starting the application. Users may be students of any grade level or age and as such will have varying levels of technical expertise, requiring the interface to be simple.  New users will have helpful but unobtrusive tutorials that guide them through the interface and help familiarize them with the application’s feature set.
2. Minimal amount of user typing: The application should try to take full advantage of the phone’s touch screen to allow the user to navigate the interface quickly. For example, when processing data into a fill-in-the-blank study form, the user may tap the word that will be the blank instead of typing it. Typing will be required in some situations such as when answering a short answer question.
3. Security related to user's information: Any and all data input to the application by the user will only be accessible by the user and the application to create the study material. This ensures that any material used to create study material cannot be plagiarized or accessed by other users.

**Users**

Initial users will be the development team as the application is being created and tested. After launch of the application the development team will keep administrative permissions to fix any problems or bugs that did not appear in preliminary testing.

The target user will be anyone looking to expand their knowledge by creating study materials from a range of scannable documents. The user will also need to have access to an Android smartphone that is capable of scanning documents through the phone's camera. 

**Possible Technical Skills**

The development team will need to be familiar with Android Studio for the creation of the mobile application. The development team will also need to be familiar with implementing artificial intelligence that will be creating the study material from the scanned documents.
