<h1>LilStan Project a.k.a. xbjy</h1>
<h2>Introduction</h2>
This project is aimed on create a Java web app for a company's employees for their background communication each other. There is one page is still under development, the home page, this page contains the article posting counts and employee register count.

This project has 3 main parts, first part is the DAO layer to cope with the database based on SQL queries, second part is the back-end layer, which has their functions, getting data from database, deal with the data and put all the data to the front-end layer, the last part is the font end layer, contains all the JSP pages, JavaScript files and CSS files.

Corresponding to the functions of this web project, there are 8 parts, main page, user page, followed user page, article page, favourite article page, department page, meeting page and user info editing page.

<h2>Main Page (Not completed)</h2>

Main page is to display article posting count and user registry count in each time period with the chart and counts.

<h2>User Page</h2>
User page is a page to display users in the company and brief information of the user including if this user followed. There are six function in this page, display user, search user, paging of user list, get to user detail page, follow user and unfollow user.

Display user function is to display some brief information of all users, for examples, ID, name, gender, age, description and if followed.

Search user function is for searching users which match the pattern in the input box.

Paging of user list is to display the pages of user list, refresh when click to the specific page and check if the page out of range.

Get to user detail page is the function for when user click on other user, the page will be redirected to the specific user's user detail page.

Follow user function is when user click on the unchecked check box of other user, that user will be followed by this user, but when user want to click on themselves, the error message will be showed.

Unfollow user function is when user click on the checked check box of other user, that user will be unfollowed by this user.

<h2>Followed User Page</h2>
Followed user page is a page to display all the followed users, and there are only three major functions, which are display followed users, get to the user detail page and unfollow user.

Display followed users is a function to display all the names of followed users and do paging simultaneously, which when user click on the specific page, those user on that page will be displayed.

Get to user detail page is the function for when user click on other user, the page will be redirected to the specific user's user detail page.

Unfollow user function is when user click on the button of other user, that user will be unfollowed by this user.

<h2>Article Page</h2>

Article page is a page to display all the articles. On this page, there are few functions, search article title by putting pattern on the input box, post article, get to article detail page with view number with increment for every viewing and display all the articles with brief details including favourite count.

Search article function is to search the article whose title match the pattern on the input box.

Post article function is to get to the post article page and let user post article.

Get to article detail page is the function for when user click on any article, the page will be redirected to the specific article detail page. After redirecting to the article detail page, view number of that article will be incremented, and user who followed also favourited this article will be displayed.

Display articles function is to get some of the information of every article, including how many user favourite those article and view number of those articles.

<h2>Favourite Article Page</h2>

Favourite article page is the page to display all the article favourited by user themselves. This page has two main functions, search favourite article by title, get to the favourite article detail page.

Search article function is to search the article whose title match the pattern on the input box.

Get to article detail page is the function for when user click on any article, the page will be redirected to the specific article detail page. After redirecting to the article detail page, view number of that article will be incremented, and user who followed also favourited this article will be displayed.

<h2>Department Page</h2>

Department page displays departments with the members in these departments. In this page, there are two functions, display all the members and departments in this company with showing members who is belongs to any department, and the other function is get to user detail page.

The displaying members and departments function is to display departments in company and showing which member is under which department, when user click on the department line, department will be expanded and display all the member who is belong to this department. In normal case, members are collapsed under the department line.

Get to user detail page is the function for when user click on other user, the page will be redirected to the specific user's user detail page.

<h2>Meeting Page</h2>

Meeting page is a page to show user all the meetings including on process one, ended one and preparing one. In this page, there are five functions. First one, displaying all the meeting with brief details and its status. Second function is searching meeting. Third function is posting meeting. Fourth function, display meeting information detailly. Fifth function is for user to determine if they are going to attend this meeting or decline attendance, or start the meeting and end the meeting.

Displaying all the meeting function is to show user some brief information of this meeting, such as title, content, start time, status and which department it will be held.

Searching meeting function is for user to search the meeting by some pattern and department it belong to.

Posting meeting function is when user click on the post meeting button, it will redirect to a post new meeting page. In this page, user can hold a new meeting by filling all the information for the meeting.

Display meeting information function is when user click to the meeting it will redirect to the meeting detail page to user see the details of that meeting.

In the meeting detail page, there is another function, user can start meeting and end meeting when user is the holder, if user is not the holder, they can attend to the meeting or decline attending to the meeting.