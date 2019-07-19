# Moon API

## Common Failure

`Status: 400 Bad Request`

```json
{
  "message": "Bad Request"
}
```

`Status: 503 Service Unavailable`

```json
{
  "message": "Service Unavailable"
}
```

## List Rooms in a Day

`GET /api/days/:day/rooms`

### Response

#### Success

`Status: 200 OK`

```json
{
  "ordered": [
    "301"
  ],
  "reserved": [],
  "cleaning": [],
  "free": [
    "101",
    "102"
  ]
}
```

#### Failure

## Query a Room in a Day

`GET /api/days/:day/rooms/:room`

### Response

#### Success

`Status: 200 OK`

```json
{
  "status": "free",
  "since": "2012-03-19"
}
```

```json
{
  "status": "cleaning",
  "until": "2012-03-19"
}
```

```json
{
  "status": "reserved",
  "reserveID": "123",
  "client": {
    "name": "Moon Xiao",
    "phone": "13200000000",
    "sex": "female"
  }
}
```

```json
{
  "status": "ordered",
  "orderID": "111",
  "client": {
    "name": "Moon Xiao",
    "phone": "13200000000",
    "sex": "female",
    "idNum": "430000000000000000"
  }
}
```

#### Failure

`Status: 403 Forbidden`

```json
{
  "message": "Past Day"
}
```

`Status: 404 Not Found`

```json
{
  "message": "Room Not Found"
}
```

## Search Clients

`GET /api/clients`

### Optional Parameters

Name | Type | Description
-----|------|-------------
`clientID`|`string` | Client ID
`name`|`string` | Client's name
`phone`|`string` | Client's phone number
`sex`|`string` | Client's sex
`idNum`|`string` | Client's ID number

### Response

#### Success

`Status: 200 OK`

```json
{
  "clients": [
    {
      "clientID": "111",
      "name": "Moon Xiao",
      "phone": "13200000000",
      "sex": "female",
      "idNum": "430000000000000000"
    }
  ]
}
```

#### Failure

`Status: 403 Forbidden`

```json
{
  "message": "Too Many Clients"
}
```

`Status: 404 Not Found`

```json
{
  "message": "Client Not Found"
}
```

## Add Client

`POST /api/clients`

### Parameters

Name | Type | Description
-----|------|-------------
`name`|`string` | Client's name
`phone`|`string` | Client's phone number
`sex`|`string` | Client's sex
`idNum`|`string` | Client's ID number

### Response

#### Success

`Status: 200 OK`

```json
{
  "clientID": "111"
}
```

#### Failure

## Update Client (Advanced)

`PUT /api/clients/:client`

### Parameters

Name | Type | Description
-----|------|-------------
`name`|`string` | Client's name
`phone`|`string` | Client's phone number
`sex`|`string` | Client's sex
`idNum`|`string` | Client's ID number

### Response

#### Success

`Status: 204 No Content`

#### Failure

`Status: 404 Not Found`

```json
{
  "message": "Client Not Found"
}
```

## Reserve a Room

`POST /api/days/:day/rooms/:room/reserve`

### Parameters

Name | Type | Description
-----|------|-------------
`name`|`string` | Client's name
`phone`|`string` | Client's phone number
`sex`|`string` | Client's sex

### Example Input

```json
{
  "name": "Moon Xiao",
  "phone": "13200000000",
  "sex": "female"
}
```

### Response

#### Success

`Status: 200 OK`

```json
{
  "reserveID": "111"
}
```

#### Failure

`Status: 409 Conflict`

```json
{
  "message": "Room Taken"
}
```

`Status: 404 Not Found`

```json
{
  "message": "Room Not Found"
}
```

`Status: 403 Forbidden`

```json
{
  "message": "Past Day"
}
```

## Order a Room

`POST /api/days/:day/rooms/:room/order`

### Parameters

Name | Type | Description
-----|------|-------------
`clientID`|`string` | Client's ID

### Example Input

```json
{
  "clientID": "123"
}
```

### Response

#### Success

`Status: 200 OK`

```json
{
  "orderID": "111"
}
```

#### Failure

`Status: 409 Conflict`

```json
{
  "message": "Room Taken"
}
```

`Status: 404 Not Found`

```json
{
  "message": "Client Not Found"
}
```

```json
{
  "message": "Room Not Found"
}
```

`Status: 403 Forbidden`

```json
{
  "message": "Past Day"
}
```

## Order a Reserved Room

`POST /api/reserves/:reserve/order`

### Response

#### Success

`Status: 200 OK`

```json
{
  "orderID": "111"
}
```

#### Failure

`Status: 404 Not Found`

```json
{
  "message": "Reserve Not Found"
}
```

## Cancel a Reserve

`DELETE /api/reserves/:reserve`

### Response

#### Success

`Status: 204 No Content`

#### Failure

`Status: 404 Not Found`

```json
{
  "message": "Reserve Not Found"
}
```

## Cancel an Order

`DELETE /api/orders/:order`

### Response

#### Success

`Status: 204 No Content`

#### Failure

`Status: 404 Not Found`

```json
{
  "message": "Order Not Found"
}
```
