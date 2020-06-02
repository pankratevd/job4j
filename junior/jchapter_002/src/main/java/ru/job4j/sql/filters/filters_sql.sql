-- 1. Написать запрос получение всех продуктов с типом "СЫР"

SELECT * FROM product as p
	INNER JOIN type as t ON p.type_id = t.id
	WHERE t.name = 'сыр';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
	
SELECT * FROM product
	WHERE name LIKE '%мороженое%';


-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.

SELECT * FROM product
	WHERE EXTRACT(MONTH FROM expired_date) = EXTRACT(MONTH FROM (NOW() + INTERVAL '1 MONTH'));


-- 4. Написать запрос, который выводит самый дорогой продукт.

SELECT * FROM product
ORDER BY price DESC
LIMIT 1;

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.

SELECT t.name, count(p.type_id) FROM type as t
		INNER JOIN product as p on p.type_id = t.id
		WHERE t.name = 'сыр'
		GROUP BY t.name;
	
	

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"

SELECT p.* FROM product as p
	INNER JOIN type as t ON p.type_id = t.id
	WHERE t.name = 'сыр' OR t.name = 'молоко';
	

-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  

SELECT  t.name  FROM type as t
LEFT JOIN product as p ON p.type_id = t.id
GROUP BY t.name
HAVING COUNT(p.type_id) < 10;

-- 8. Вывести все продукты и их тип.

SELECT p.name, t.name FROM product as p
LEFT JOIN type as t ON t.id = p.type_id;

