# Rewards Application
It is a rewards program to given customers, awarding points based on each recorded purchase

A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.

- Spring Boot application
- RESTFul endpoint

### Request format:
```json

{
    "customers": [
        {
            "id": 1,
            "name": "Anna",
            "cost": 100.0,
            "month": "Jan"
        },
        {
            "id": 2,
            "name": "Anna",
            "cost": 120,
            "month": "Feb"
        },
        {
            "id": 1,
            "name": "Anna",
            "cost": 260,
            "month": "Mar"
        }
    ]
}

```


### Response format:

#### Sucess response
```json
{
    "returnCode": 200,
    "returnMessage": "Success",
    "response": [
        {
            "id": 1,
            "name": "Anna",
            "totalRewards": 420.0,
            "rewardsList": [
                {
                    "month": "Jan",
                    "rewards": 50.0
                },
                {
                    "month": "Mar",
                    "rewards": 370.0
                }
            ]
        },
        {
            "id": 2,
            "name": "Anna",
            "totalRewards": 90.0,
            "rewardsList": [
                {
                    "month": "Feb",
                    "rewards": 90.0
                }
            ]
        }
    ]
}
```

#### Bad request:
```json

{
    "returnCode": 2,
    "returnMessage": "Invalid month",
    "response": null
}

```
