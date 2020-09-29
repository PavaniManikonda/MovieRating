# MovieRating
Get Movie:

http://localhost:8080/api/movie/1213

Response:

{ "movieId": "1213", "name": "ABC" }

http://localhost:8080/api/customer/12/rate/2 Response:

{ "ratingId": { "movieId": "1214", "customerId": "12" }, "rating": 2 }

http://localhost:8080/api/HighestRatedMovie

Response:

Higest Rating for all movies based on individual movie Average is =5

http://localhost:8080/api/allMovieRatings

[ { "ratingId": { "movieId": "1", "customerId": "1" }, "rating": 4, "cusName": "abc" }, { "ratingId": { "movieId": "1", "customerId": "2" }, "rating": 3, "cusName": "abc" }, { "ratingId": { "movieId": "2", "customerId": "2" }, "rating": 4, "cusName": "abc" }, { "ratingId": { "movieId": "2", "customerId": "1" }, "rating": 4, "cusName": "abc" } ]

http://localhost:8080/api/AverageRating

{ "id": "3", "name": "abc", "cusAvgRating": 4.666666666666667, "avgRating": 3.7777777777777777 }
