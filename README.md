# SDAFinalProjectBackend

Scenario 5
Online store with an administration panel
Brief description of the system
As part of the project, you should create an application that allows you to add products to the store via the administration panel. The system is to enable user registration and logging, as well as placing an order.

Main system functions
Login panel.

Admin:

Adding categories for products.
Category tree overview.
Adding products.
List of products + edition.
User:

Registration.
List of products.
Product table with pagination.
Weather forecast for the user's city.
General guidelines
Build a website using Spring Boot, JPA (Hibernate) and Thymeleaf/Angular/React as the view layer. Introduce a division into the application by DAO, services, controllers (REST controllers in the case of using Angular) and placing the appropriate logic into each of them. Secure access to applications and functionalities using Spring Security.

Basic entities (Proposal)
Category
name
parent categories and categories of "children" (location in the tree)
User's account
login (email)
password (hash)
city
address (country, city, street, zip code)
logotype/thumbnail/avatar
role (ADMIN/USER - entity)
message channel preferences (standard mail/email)
Product
title
description
thumbnail (url)
category (entity)
price
product type (enum)
author (entity)
Order line
Product (entity)
Number of products
Product price
Order
User Name
Total cost
Delivery address
User address
Date of submission
Order lines (entities)
Client (entity)
Status (enum)
Author
First name
Last Name
Role
Role name
Cart (not entity)
Order lines
Functionalities
Admin functionality
Adding categories
category name
parent id
Category tree overview
category search
possibility to drag categories (change position)
Adding a product
name
description
image url
availability
price
product type (dropdown)
category (dropdown)
author (dropdown)
List of products
list of all added products with their details
button to go to the edition of the product
product search
User functionalities
User registration
entering data into the form fields + validation of these fields
Login
the ability to log in the user (after prior registration)
user logout
Weather widget
weather display based on the city of the currently logged in user
List of products
it is possible to display products as a list or as a grid
product search
adding a product to the cart
Table with products (Ajax on the GET query and inserting parameters into the url)
displaying products in a pagination board (selection of the number of products on the page)
sorting products in the table
Ajax product search
adding products to the cart
Cart
displaying products added to the cart
possibility of ordering products from the cart -> leads to a static thank-you page and reduces the condition of products
Additional tasks and extensions
possibility to edit user account (data)
review of user orders (from the user and admin level)
adding an author in the admin panel
Additional requirements
it is necessary to ensure an aesthetic and functional way of presenting data
data downloaded from users should be pre-validated
