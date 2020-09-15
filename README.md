# Spring Boot Microservices With IntelliJ IDEA

## Introduction

My Plant Diary allows homeowners to maintain records on plants in their yard.  They can upload photos and add notes to a plant at any time.  Plant data, including sustainability and edibility, are sourced from PlantPlaces.com master data.

Users can generate a report to show several attributes of their yard: sustainability, edibility, native, etc.  This report can be used to help sell the positive attributes of the house.

Users can interact with MyPlantDiary using either a set of RESTful service endpoints, or a simple UI, or both.

## Storyboard

[Storyboard in Invision](https://projects.invisionapp.com/prototype/Plant-Diary-ck0bict0n005bqh01aaeu8tuu)

## Requirements

1. As a homeowner, I want to be able to catalog my specimens, so that I will remember what I planted.

### Example 

**Given**:  A feed of plant data are available

**When**: The user/service selects plant Eastern Redbud

**When**: The user/service adds latitude 39.74 to an Eastern Redbud specimen

**Then**: The user’s/service’s Eastern Redbud will be saved with 39.74 latitude.

### Example 

**Given**: Specimen data are available

**When**: The user/service searches for “kajsd;luaopuidfjo;aj;sd”

**Then**: My Plant Diary will not return any results, and the user will not be able to save the specimen.

### Example 

**Given**: Specimen data are available, and specimen 83 is Eastern Redbud.

**When**: The user/service searches for the specimen with ID “83”

**Then**: My Plant Diary will return exactly one specimen record for "Eastern Redbud".

### Example 

**Given**: Specimen data are available

**When**: The user/service posts a new Specimen object with valid attributes "latitude=39.74, longitude=-84.51"

**Then**: MyPlantDiary will create a new specimen for this record, and will return this new specimen object.

2.	As a homeowner, I want to be able to upload photos of my plant at any time.

### Example 

**Given**: The user is logged in and has selected a previously-saved Eastern Redbud specimen

**When**: The user uploads a valid 640*480 photo of an Eastern Redbud Flower

**Then**: The 640*480  photo of an Eastern Redbud flower will be saved to the specimen profile, and can be viewed later.

### Example 

**Given**: The user is logged in and has selected a previously-saved Eastern Redbud specimen

**When**: The user uploads a 100GB photo

**Then**: The photo will be rejected as too large.

### Example 

**Given**: The user is logged in and has selected a previously-saved Eastern Redbud specimen

**When**: The user uploads a 1600*1200 photo

**Then**: The photo will be resized automatically to 640*480

**Then**: The 640*480 photo will be shown to the user.

3)	As a homeowner, I want to generate a report of the sustainability of my yard.
### Example 

**Given**: The user has a valid account and specimens associated to that account.

**When**: The user runs a report.

**Then**: The user will see a report of plants, dates, native, edible, and sustainability rating.

### Example 

**Given**: The user has a valid account and no specimens associated to that account.

**When**: The user runs a report.

**Then**: The user will see an error, indicating no data available for report.

## Class Diagram

![MyPlantDiaryClassDiagram](https://user-images.githubusercontent.com/2224876/93275565-40ecd880-f78b-11ea-8030-cd87987b1817.png)

### Class Diagram Description 

## JSON Schema

This is what we plan to export to another app.


> {
>  "type" : "object",
>  "properties" : {
>    "name" : {
>      "type" : "string"
>    },
>    "age" : {
>      "type" : "integer"
>    }
>  }
> }

## Team Memebers and Roles

UI Specialist: Brandan Jones
Business Logic/Persitence: Brandan Jones
DevOps/Product Owner/Scrum Master/GitHub Admin: Brandan Jones

## Milestones

[Milestone 1](https://github.com/discospiff/SpringBootMicroservicesWithIntelliJIDEA/milestone/1)

## Standup

[We meet 7:00 PM Eastern on Sunday on Teams](https://teams.microsoft.com/l/meetup-join/19%3af8fd74991d314a0987e34b7c91ed88be%40thread.tacv2/1598225206706?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%225c29be2c-6de4-49ad-a444-dfb003838da6%22%7d)



