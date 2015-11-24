# ticket-service-rest

- [ ] The project  is written using java and jersey framework for REST web service access

- [ ] To excute the test and build the solution 

Goto  "ticket-service-rest" directory and run the following command 

mvn clean package && java -jar target/ticket-service-rest-0.0.1-SNAPSHOT.jar


- [ ]the  TicketService interface is implemented at src/main/java/com/wl/ticketServices/TicketServiceImpl

once the spring boot is running the following URI can be accessed 
(tested using postman and GET methods using chrome browser)


Method                              URI                                               Remark
- [ ] GET                   http://localhost:8080//webapi/ticket/           All available seats
- [ ] GET                   http://localhost:8080//webapi/ticket/{id}      available Seats for the provide venueLevel Id(1-4)
- [ ] POST                       http://localhost:8080/webapi/ticket/hold
                            {"numSeats": "14", "customerEmail":"papa@yahoo.com"}   hold seat for the given email address

- [ ] POST                        http://localhost:8080/ticket-services/webapi/ticket/reserve    reserve for the customer
                             {  "getSeatHoldId": number   "customerEmail": valid email  }

  

