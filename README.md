# Parking Lot Management System

## Problem Statement

You are building a Parking Lot Management System. As a part of this system, you need to build functionalities using which customers can generate a ticket while entering the parking lot, pay the parking fee while exiting the parking lot and get the capacity of the given parking lot.

## Requirements
#### Generate Ticket
1. A ticket will be generated for a customer when they enter the parking lot.
2. Tickets can be generated only at entry gates. If a customer tries to generate a ticket at any exit gate, then the system should throw an exception.
3. Generate ticket request will contain gate id, vehicle number, and vehicle type.
4. If the vehicle doesn't exist in our database, we need to create a new vehicle entry in the database.
5. As a part of ticket generation we also need to assign a spot to the vehicle. Following are the rules for assigning a spot to a vehicle.
   - When a vehicle arrives, the system should assign it to the floor with the least number of available spots for that vehicle type.
   - If there are multiple floors with same number of available spots, the system should assign the vehicle to the floor with the lowest floor number.
   - If a floor is operational, then only it should be considered, otherwise the system should ignore that floor.
   - Once a floor has been selected, the system should assign the vehicle to the nearest available spot of that vehicle type on that floor.
   - If there are no available spots on any floor, the system should not issue a ticket.
6. The system should return the ticket id to the customer.

#### Generate Bill
1. A customer can pay the parking fee only at exit gates. If a customer tries to pay the parking fee at any entry gate, then the system should throw an exception.
2. Generate bill request will contain ticket id and gate id.
3. The system should calculate the parking fee for all vehicle types which is 10 per hour. Eg. If a vehicle enters the parking lot at 10:00 AM and exits at 11:30 AM then the parking fee will be 20. If a vehicle enters the parking lot at 10:00 AM and exits at 11:00 AM then the parking fee will be 10.
4. The system should return the generated bill with the parking fee to the customer.
5. Once the bill is generated, the system should mark the spot as available.

#### Pay Parking Fee
1. Payment service request will contain bill id and map of amounts to be paid per payment mode.
2. The system should valid the Bill Id and if the bill is already paid then throw an exception.
3. The system should validate the total amount paid by the customer. If the total amount paid is not equal to the parking fee, then the system should throw an exception.
4. The total parking fee can be paid using different payment modes. Also one parking fee can be paid using multiple payment modes. Eg. If the parking fee is 20 and the customer pays 10 using cash and 10 using card, then the system should accept this payment.
5. If all the payments are successful, then the system should mark the bill as paid. If any payment fails, then the system should mark the bill as partially paid. If all the payments fail, then the system should mark the bill as unpaid.
6. The system should return the status of each payment mode.

#### Get Parking Lot Capacity
1. If the parking lot id is invalid, then the system should throw an exception.
2. The system should be flexible to give the overall capacity of the parking lot, or the capacity of a specific parking floor or for specific vehicle type or combination of all of these.
3. The request will always get a parking lot id. List of parking floor ids is optional, when given, the response should contain the capacity of the given parking floors. List of vehicle types is optional, when given, the response should contain details about given vehicle types.
4. Capacity should only contain the count of available spots for the given vehicle type for the operational floor.
5. The response should contain Map, outer map key is the parking floor, inner map key is the vehicle type and value is the capacity of the given vehicle type in the given parking floor. 
6. If the vehicle type is not given, then the response should contain the total capacity of the parking floor for all vehicle types.
