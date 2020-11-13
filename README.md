
# Интернет-магазин по продаже автомобилей<a name="русский"></a>
---
### Учебный проект по курсу "Java Web Development"
### Автор: Вербовский Сергей
#### [Go to EN](#english)
---
### Оглавление<a name="оглавление"></a> 
* [Общее описание](#общее_описание)
* [Пользователи](#пользователи)
* [Автомобили](#автомобили)
* [Заказы](#заказы)
### Общее описание<a name="общее_описание"></a> 
  Веб-приложение предоставляет возможность осуществлять онлайн-заказы автомобилей. 
  Клиент, с помощью каталога, может подобрать подходящий для себя вариант по параметрам и цене. 
  После подтверждения заказа, автомобиль доставляется в точку выдачи и пользователю на электоронную почту приходит письмо о том, что его заказ прибыл. 
  После оплаты клиентом администратор помечает сделку как состоявшуюся.
  
[⬆️Оглавление](#оглавление)
____
### Пользователи<a name="пользователи"></a> 
  Для разграничения уровней доступа и возможностей пользователей онлайн-магазина в приложении были введены роли: 
  * **Клиент**  
    У клиента в свою очередь определены три статуса: **Активирован, Неактивирован, Заблокирован**.  
    Клиенту, прошедшему процедуру регистрации присваивается роль **Клиент** и статус **Неактивирован**. На электронную почту клиента, указанную при регистрации, отправляется
    письмо с кодом подтверждения, введя который его статус меняется на **Активирован**. В случае необходимости администратор может его заблокировать,
    статус с **Разблокирован** сменится на **Заблокирован**.  
    
    Функциональные возможности:
      * **Неактивирован** 
        * Просмотр домашней страницы
        * Смена языка сайта
        * Просмотр страницы регистрации
      * **Активирован** 
        * Просмотр домашней страницы
        * Просмотр доступных к заказу автомобилей
        * Смена языка сайта
        * Заказ автомобиля
        * Просмотр всех своих заказов
      * **Заблокирован** 
        * Просмотр домашней страницы
        * Смена языка сайта 
  * **Администратор**  
    Управляет работой интернет-магазина. В его обязанности входит добавление/удаление/блокировка автомобилей на сайте,
    управление заказами клиентов, управление клиентами.
    
    Функциональные возможности:
    * Просмотр домашней страницы
    * Смена языка сайта
    * Просмотр заказов клиентов
    * Подтверждение/отмена заказа
    * Просмотр каталога автомобилей
    * Добавление нового автомобиля в каталог
    * Изменение статуса автомобиля
    * Удаление автомобиля, если он не находится в списке заказов
    * Просмотр всех клиентов
    * Изменение статуса клиента Разблокирован/Заблокирован
    * Удаление неактивированных клиентов
    
[⬆️Оглавление](#оглавление)
____
### Автомобили<a name="автомобили"></a>   
  Являются предметной областью интернет-магазина. Обладают различными параметрами, такими как: название модели, марка, тип двигателя, цвет, год выпуска, тип коробки передач.
  Так же каждому автомобилю присвоена стоимость и флаг - доступен он для заказа клиентам или нет.  
  
[⬆️Оглавление](#оглавление)
___
### Заказы<a name="заказы"></a>   
  Результатом выбора автомобиля клиентом является заказ. 
  Заказ содержит информацию об автомобиле и клиенте, заказавшем его, а также дате заявки.  
  
  Заказу присваивается один из двух статусов:
  * **В обработке**  
  Присваивается новому заказу после его оформления клиентом
  * **Выполнен**
  После оплаты клиентом статус заказа меняется на Выполнен
  
  В случае, когда Заказ выполнен, но не был оплачен, такой заказ считается недействительным и удаляется.  
  
[⬆️Оглавление](#оглавление)
____

# Online store selling cars "<a name="English"> </a>
---
### Java Web Development Teaching Project
### Author: Verbovskiy Sergei
#### [Go to EN](#русский)
---
### Table of Contents <a name="table"> </a>
* [General description](#general_description)
* [Users](#users)
* [Cars](#cars)
* [Orders](#orders)
### General Description <a name=" General_description"> </a>
  The web application provides the ability to make online car orders.
  The client, using the catalog, can choose a suitable option for himself in terms of parameters and price.
  After confirming the order, the car is delivered to the pick-up point and the user receives an e-mail message stating that his order has arrived.
  After payment, the administrator marks the deal as completed.
[⬆️Table of contents](#contents)
____
### Users<a name="users"></a> 
  To differentiate the access levels and capabilities of users of the online service, roles were introduced in the app:
  * **Guest**  
    Unauthorized user
  
    Functionality:
      * Home page view
      * View available cars for order
      * Changing the site language
      * Login / Register
  * **Client**  
    The client, in turn, has three statuses: **Pending, Active, Blocked**. 
    The guest who has passed the registration procedure is assigned the role **Client** and the status **Pending**. The client's email address specified during registration is sent
     a letter with a link by clicking on which the client's status changes to **Active**. If the client violates the rules for using the service, the administrator can block it,
     status from **Active** will change to **Blocked**.
    
    Functionality:
      * **Pending**  
        * Home page view
        * View available cars for order
        * Changing the site language
      * **Active** 
        * Home page view
        * View available cars for order
        * Changing the site language
        * Car order
        * Order payment
        * View all your orders
      * **Blocked** 
        * Home page view
        * Changing the site language 
  * ** Administrator **
      Manages the work of the store. His responsibilities include adding new cars to the catalog, managing cars,
      customer order management, customer management.
      
      Functionality:
      * View home page
      * Change site language
      * View customer orders
      * Order confirmation / cancellation
      * View car catalog
      * Adding a new car to the catalog
      * Change car status
      * removal of the car if it is not in the order list
      * View all clients
      * Change client status Active / Blocked
      * Removing non-activated clients
    
[⬆️Table of contents](#table of contents)
____
### Automobiles <a name=" Automobiles"> </a>
  Are the subject area of an online store. They have various parameters, such as model name, brand, engine type, color, year of manufacture, type of transmission.
  Also, each car is assigned a value and a flag - whether it is available for ordering by customers or not.
[⬆️Table of contents](#contents)
___
[⬆️ Table of contents](#table of contents)
___
### Orders <a name="orders"> </a>
  The result of ordering a car by a customer is an order.
  The order contains information about the car and the customer who ordered it, as well as the date of appearance.
  
  An order is assigned one of two statuses:
  * ** In processing **
  Assigned to a new order after the client has placed it
  * ** Completed **
  After payment by the client, the status of the order from being processed changes to Completed
  
  In cases when:
  * The order was completed, but was not paid
  * The order was canceled by the administrator
  
  such order is considered invalid and will be deleted.
  
[⬆️ Table of contents](#table of contents)
____

