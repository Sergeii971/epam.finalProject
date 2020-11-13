
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
  После подтверждения заказа, автомобиль доставляется в точку выдачи, и пользователю на электоронную почту приходит письмо о том, что его заказ прибыл. 
  После оплаты клиентом выбранного автомобиля, администратор помечает сделку как состоявшуюся.
  
[⬆️Оглавление](#оглавление)
____
### Пользователи<a name="пользователи"></a>  
  Для разграничения уровней доступа и возможностей пользователей онлайн-магазина в приложении были введены роли: 
  * **Клиент**  
    У клиента в свою очередь определены три статуса: **Активирован, Неактивирован, Заблокирован**.  
    Клиенту, прошедшему процедуру регистрации присваивается роль **Клиент** и статус **Неактивирован**. На электронную почту клиента, указанную при регистрации, отправляется
    письмо с кодом подтверждения, введя который его статус меняется на **Активирован**. В случае необходимости, администратор может его заблокировать,
    статус с **Разблокирован** изменится на **Заблокирован**.  
    
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
    Управляет работой интернет-магазина. В его обязанности входит: добавление/удаление/блокировка автомобилей на сайте,
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
  Являются предметной областью интернет-магазина. Они обладают различными параметрами, такими как: название модели, марка, тип двигателя, цвет, год выпуска, тип коробки передач.
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
  
    После оплаты заказа клиентом статус меняется на Выполнен
  
  В случае, когда Заказ выполнен, но не был оплачен, он считается недействительным и удаляется.  
  
[⬆️Оглавление](#оглавление)
____

# Online store selling cars<a name="english"></a>
---
### Java Web Development Teaching Project
### Author: Verbovskiy Sergei
#### [Перейти на русский](#русский)
---
### Contents<a name="contents"></a>
* [General description](#description)
* [Users](#users)
* [Cars](#cars)
* [Orders](#orders)
### General Description <a name="description"></a>
  The web application provides the ability to make online car orders.
    The client, using the catalog, can choose a suitable option for himself in terms of parameters and price.
    After confirming the order, the car is delivered to the point of issue, and the user receives a letter by Email stating that his order has arrived.
    After the customer pays for the selected car, the administrator marks the transaction as completed.
    
[⬆️Contents](#contents)
____
### Users<a name="users"></a> 
  To differentiate the levels of access and capabilities of users of the online store, roles were introduced in the application:
  * **Client**
    
    The client, in turn, has three statuses: **Activated, Inactivated, Blocked**.
    The client who has passed the registration procedure is assigned the role **Client** and the status **Inactive**.
    A letter with a confirmation code, by entering which its status changes to **Activated**, is sent to the client's email address, which was specified during registration.
    If necessary, the administrator can block it, the status from **Unlocked** will be changed to **Locked**.
    
    Functionality:
    * **Inactive**
      * View home page
      * Change site language
      * View registration page
    * **Activated**
      * View home page
      * View available cars for order
      * Change site language
      * Car order
      * View all your orders
    * **Locked**
      * View home page
      * Change site language
  * **Administrator**
  
    Manages the work of the online store. His responsibilities include: adding / removing / blocking cars on the site,
    customer order management, customer management.
    
    Functionality:
    * View home page
    * Change site language
    * View customer orders
    * Order confirmation / cancellation
    * View car catalog
    * Adding a new car to the catalog
    * Change car status
    * Removing a car if it is not in the order list
    * View all clients
    * Change client status Unlocked / Locked
    * Removing non-activated clients
    
  [⬆️Contents](#contents)
  
### Cars<a name="cars"></a>
  Are the subject area of an online store. They have various parameters, such as: model name, brand, engine type, color, year of manufacture, type of transmission.
  Also, each car is assigned a value and a flag - whether it is available for ordering to customers or not.
  
  [⬆️Contents](#contents)
  
### Orders<a name="orders"></a>
  The result of choosing a car by a customer is an order.
  The order contains information about the vehicle and the customer who ordered it, as well as the date of the application.
  
  An order is assigned one of two statuses:
  * **In processing**
  
    Assigned to a new order after the client has placed it
  * **Completed**
  
    After the customer pays the order, the status changes to **Completed**
  
  When the Order is completed, but is not paid, it is considered invalid and is deleted.
  
  [⬆️Contents](#contents)
____    

