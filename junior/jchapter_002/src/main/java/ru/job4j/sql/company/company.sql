/*
1) Retrieve in a single query:
// - names of all persons that are NOT in the company with id = 5
// - company name for each person
*/

SELECT p.name as person_name, c.name as company_name
FROM person as p
LEFT JOIN company as c ON c.id = p.company_id
WHERE c.id != 5;


--  Select the name of the company with the maximum number of persons + number of persons in this company
SELECT c.name, COUNT(p.company_id) as number_persons
FROM person as p
JOIN company as c ON c.id = p.company_id
GROUP BY c.name
ORDER BY number_persons DESC
LIMIT 1;