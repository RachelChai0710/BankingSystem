# BankingSystem
This is a banking system built with spring boot application.
Postman Collection to test: [Postman Collection](https://github.com/RachelChai0710/BankingSystem/blob/main/Banking%20System.postman_collection.json)

## Resource
There are 2 main resources in this project:
- http://localhost:8080/acc
- http://localhost:8080/cus
- http://localhost:8080/txn

## All available routes
GET:
- /acc/balance_inq/{id} (Balance Inquiry By Account ID)
- /acc/get/{cusId} (Get Account By Customer ID)
- /cus/getAll (Get All Customers)
- /cus/get/{id} (Get Customer by ID)
- /txn/history/{accId} (Get Transaction History by Account ID)

PUT:
- /cus/upd/{id} (Update Customer Details by ID)

POST:
- acc/add (Add New Account)
Request Body for Add New Customer:
```
//Saving Account Request
{
    "cusId": 1,
    "balance": 500.00,
    "accT": "SAVING" //"SAVING" or "CREDIT"
}
//Credit Account Request
{
    "cusId": 1,
    "accT": "CREDIT", //"SAVING" or "CREDIT"
    "cardNo": "1234567814561234",
    "creLim":2000.00
}
- cus/add (Add New Customer)
Request Body for Add and Update Customer:
```
{
    "name": "Test",
    "age": 24
}
```
- /txn/deposit/:accId (Deposit by Account ID)
- /txn/withdraw/:accId (Withdraw by Account ID)
Request Body for Deposit and Withdraw transaction:
```
{
    "amount": 20.00
}
```
- /txn/transfer/:accId (Transfer by Account ID)
Request Body for transfer transaction:
```
{
    "toAcc":2,
    "amount": 10.00
}
```
