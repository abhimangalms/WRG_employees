# WRG_employees
Android app based on kotlin to show employee details fetched from api and stores in local db using Room and a searchView for the list. 

SearchView is working perfectly with viewmodel, livedata and Room DB.\n
But a little change is needed inside the adapter class to set the updated view after searching the list./n
Need to make the Model class type of the searched result list as same as the old list. (setData() inside the adapter class) to update the recyclerview items with search results./n
Submitting this because the time limit assigned for me has been exceeded /n
