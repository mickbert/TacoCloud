delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;
delete from Ingredient;
insert into Ingredient (id, name, type)
                values ('FLTO', 'Tortilla di frumento', 'WRAP');
insert into Ingredient (id, name, type)
                values ('COTO', 'Tortilla di mais', 'WRAP');
insert into Ingredient (id, name, type)
                values ('GRBF', 'Carne di manzo', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('CARN', 'Carne di maiale', 'PROTEIN');
insert into Ingredient (id, name, type)
                values ('TMTO', 'Polpa di pomodoro', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('LETC', 'Lattuga', 'VEGGIES');
insert into Ingredient (id, name, type)
                values ('CHED', 'Cheddar', 'CHEESE');
insert into Ingredient (id, name, type)
                values ('JACK', 'Emmental', 'CHEESE');
insert into Ingredient (id, name, type)
                values ('MAYO', 'Maionese', 'SAUCE');
insert into Ingredient (id, name, type)
                values ('SRCR', 'Panna acida', 'SAUCE');
insert into Ingredient (id, name, type)
                values ('KTUP', 'Ketchup', 'SAUCE');
